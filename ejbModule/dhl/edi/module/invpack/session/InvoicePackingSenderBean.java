package dhl.edi.module.invpack.session;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;

import com.coretronic.edi.entity.Workflow;
import com.coretronic.edi.service.WorkflowRemote;
import com.coretronic.edi.util.CloseUtil;
import com.coretronic.edi.util.WorkflowUtil;
import com.coretronic.util.DateUtil;
import com.coretronic.util.FileUtil;
import com.coretronic.util.JaxbUtil;

import edi.service.MailException;
import edi.service.MailServiceRemote;
import edi.service.entity.MailMessage;
import dhl.edi.module.invpack.entity.CtcEcShuttleInvoicePack;
import dhl.edi.module.model.DhlExportLInvPackDataBuilder;
import dhl.edi.module.model.XmlDhlExportFormat;
import dhl.edi.module.util.Constant;
import dhl.edi.module.util.FTPConnection;
import dhl.edi.module.util.PropertiesLoad;
import dhl.edi.module.util.CommonValues;

/**
 * Session Bean implementation class InvoicePackingOutSchedulerBean
 */
@Stateless
@LocalBean
public class InvoicePackingSenderBean{

	@PersistenceContext(unitName = "ERP_DB")
	private EntityManager em;
	private static final Logger logger = Logger.getLogger(InvoicePackingSenderBean.class);
	private Properties properties = null; 
	private FTPConnection ftpConnection = null;
	InitialContext ctx;
	private WorkflowRemote workflowService;
	private MailServiceRemote mailManager;
	private PropertiesLoad propertiesLoad;
	private CommonValues commonValues;
    /**
     * Default constructor. 
     */
    public InvoicePackingSenderBean() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @throws Exception 
	 * @see InvoicePackingSendor#execute()
     */
    public void execute(Workflow workflow) throws Exception {
    	

        try {
        	logger.info("InvoicePacking Start, Partner Name: " + workflow.getPartnerName());
        	
        	init(workflow.getPartnerName());
			process(workflow);
			
		} catch (IOException e) {
			
			logger.error(e);
			mailError(e, workflow.getId());
			
			throw e;
			
		} catch (NamingException e) {
			
			logger.error(e);
			mailError(e, workflow.getId());
			
			throw e;
			
		} catch (Exception e) {
			
			logger.error(e);
			mailError(e, workflow.getId());
			
			throw e;
			
		} finally{
			close();
		}
    }
    
    private void process(Workflow workflow) throws Exception{
    	
    	//ERP team will insert shuttle_invoice_group_id into attribute10 column
    	String invoiceGroupId = workflow.getAttribute10();
    	
    	//ERP 統一班號放在WF attribute05
    	String ShipperCer = workflow.getAttribute05();
    	
    	//ERP team will insert ERP OU_ID into attribute9 column
    	//104->中強光電　714->揚昇　1955->揚光綠能
    	String custcode = null;
    	
    	String hawbNo = null;
    	
    	File invPackFile = null;
    	
    	if(invoiceGroupId==null){
    		
    		logger.error("invoice_group_id in Attribute10 of workflow is null");
    		throw new Exception("Attribute10 of workflow is null. ERP should fill in invoice_group_id in it.");
    	}
    	
    	
    	//query invoice packing from database by shuttle_invoice_group_id
    	logger.debug("Query Invoice Packing data: WF_ID: " + workflow.getId());
    	List<CtcEcShuttleInvoicePack> invoicePacks = null;
    	Query query = em.createNamedQuery("FindInvoicePack");
    	query.setParameter("shuttleInvoicePacksGroupId", Long.parseLong(invoiceGroupId));
    	invoicePacks = query.getResultList();
    	
    	if( invoicePacks.size() > 0 )
    	{
    		hawbNo = invoicePacks.get(0).getHawb();
    	}
    	
    	//write invoice packing.csv file
    	String backupFolder = properties.getProperty("local.backup.path.invpack");
    	String timePattern = DateUtil.getNowString(properties.getProperty("backup.timepattern.invpack"));
	    String localBackupPath = FileUtil.concat(backupFolder, timePattern);
	    
	    //Create folders
		File localOutFolder = new File(localBackupPath);
		if(localOutFolder.exists()!=true)
			localOutFolder.mkdirs();
		
		//ERP will put document number to document name column	
	    String invPackFileName = getInvPackFileName(custcode, hawbNo);

		DhlExportLInvPackDataBuilder DhlExportLInvPackDataBuilder = new DhlExportLInvPackDataBuilder();
		XmlDhlExportFormat XmlDhlExportFormat = DhlExportLInvPackDataBuilder.buildXmlInvPackData(invoicePacks, ShipperCer);
		

		
		try {
		    invPackFile =  saveXMLFile( XmlDhlExportFormat,  localBackupPath,  invPackFileName);
			logger.debug("Write Invoice Packing XML: " + invPackFile.getAbsolutePath());
	    	
		} catch (IOException e) {
			logger.error(e.getMessage());
			mailError(e, workflow.getId());
			return;

			
		} catch (JAXBException e) {
			logger.error(e.getMessage());
			mailError(e, workflow.getId());
			return;
		}
    	
    	//upload XML file to ftp
    	String remotePath = properties.getProperty("core.ftp.path.invpack");
    	ftpConnection.uploadFile(invPackFile, remotePath, invPackFile.getName());
    	logger.info("File is upload to FTP: " + invPackFile.getName());
    	
    	//update workflow status to finished
    	//insert file name to attribute8 column
    	updateWorkflow(workflow, WorkflowUtil.FINISH, invPackFileName);
    	logger.info("Workflow is finished. WF_ID: " + workflow.getId());
    	
    	//mail report
    	logger.info("Mail report. WF_ID: " + workflow.getId());
    	mailReport(workflow);
    }
    
    private String getInvPackFileName(String custcode, String hawbno) throws Exception{
    	
    	String fileName = null;
    	
    	fileName = Constant.PARTNER_NAME_DHL + "_" + Constant.EXPORT_FILE_TYPE + "_" + hawbno + "_" + getCurrentTimeStr() + Constant.FILE_EXTENSTION_XML;
    	
    	return fileName ;
    }
    
    private File saveXMLFile(XmlDhlExportFormat XmlDhlExportFormat, String localBackupPath, String invPackFileName) throws IOException, JAXBException{
    	
    	FileOutputStream fileOutputStream = null;
    	File file = null;
    	String filePath = null;
    	   	
    	filePath = FilenameUtils.concat(localBackupPath, invPackFileName);
    	
    	file = new File(filePath);
    	try {
			fileOutputStream = FileUtils.openOutputStream(file);
	    	JaxbUtil.marshal(XmlDhlExportFormat.class, XmlDhlExportFormat, fileOutputStream);
	    	
		} catch (IOException e) {
			throw e;
			
		} catch (JAXBException e) {
			throw e;
			
		} finally{
			if(fileOutputStream!=null)
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

    	return file;
    }
    
    //return current time string, format is YYYYMMDDHHMMSS
    private String getCurrentTimeStr(){
    	
    	String dateFormat = "yyyyMMddHHmmss";
    	String currentTimeString = null;
    	
    	currentTimeString = DateFormatUtils.format(Calendar.getInstance().getTimeInMillis()  , dateFormat);
    	
    	return currentTimeString;
    }
    
    private void mailReport(Workflow workflow){
    	
    	MailMessage message = new MailMessage();
    	message.setMailSubject(commonValues.getMailSubjInfoInvPack() + " " + "INVPACK is sent out. NO: " + workflow.getDocumentName());
    	message.setMailContent("WF_ID: " + workflow.getId());
    	message.setMailId(commonValues.getMailGroupInvPack());
    	
    	try {
			mailManager.send(message);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
    }
    
    private Workflow updateWorkflow(Workflow workflow, String status, String fileName) {
    	
        workflow.setStatusFlag(status);
        workflow.setAttribute08(fileName);
        workflowService.update(workflow);

        return workflow;

    }
    
    private void mailError(Exception ex, long workId){
    	
    	MailMessage message = new MailMessage();
    	StringWriter sw = new StringWriter();
	    ex.printStackTrace(new PrintWriter(sw));
	    message.setMailContent(sw.toString());
    	message.setMailSubject(commonValues.getMailSubjErrorInvPack() + " WF_ID: " + workId);
    	message.setMailId("EDI.DHL.ERROR");
    	
    	try {
			mailManager.send(message);
		} catch (MailException e) {
			logger.debug(e.getMessage());
		}
    }
    
    private void init(String workflowPartnerName) throws Exception{
    	
    	commonValues = null;
    	commonValues = new CommonValues();   	
		
    	ctx = new InitialContext();
    	mailManager = (MailServiceRemote) ctx.lookup("java:global/edi/service/Mail");
    	
    	String propertiesFileName = "/" + "EDI_"+workflowPartnerName+".properties";
    	propertiesLoad = new PropertiesLoad(propertiesFileName);
    	
    	properties = propertiesLoad.getProperties();
		String host = properties.getProperty("core.ftp.host");
		String port = properties.getProperty("core.ftp.port");
		String user = properties.getProperty("core.ftp.user");
		String pwd = properties.getProperty("core.ftp.pwd");
    	ftpConnection = new FTPConnection();
		ftpConnection.connect(host, Integer.parseInt(port), user, pwd);
		
	    workflowService = (WorkflowRemote) ctx.lookup(WorkflowUtil.JNDI_NAME);
	    
	    String propertiesPartnerName = properties.getProperty("core.partner.name");
	    logger.info("InvoicePacking, Properties Partner Name: " + propertiesPartnerName);
	    commonValues.setMailSubjInfoInvPack(propertiesPartnerName);
    	commonValues.setMailGroupInvPack(propertiesPartnerName);
    	commonValues.setMailSubjErrorInvPack(propertiesPartnerName);

    }
    
    private void close(){
    	
		if(ftpConnection != null)
			ftpConnection.disConnect();
		
		CloseUtil.close(ctx);
    }

}