
package dhl.edi.module.exportdata.session;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.jboss.ejb3.annotation.TransactionTimeout;

import com.coretronic.edi.entity.Workflow;
import com.coretronic.edi.util.CloseUtil;
import com.coretronic.util.DateUtil;
import com.coretronic.util.FileUtil;

import edi.service.MailException;
import edi.service.MailServiceRemote;
import edi.service.entity.MailMessage;
//import dhl.edi.module.util.AckFileBuilder;
import dhl.edi.module.util.Constant;
import dhl.edi.module.util.FTPConnection;
import dhl.edi.module.util.PropertiesLoad;
import dhl.edi.module.util.CommonValues;

/**
 * Session Bean implementation class ExportDataScheduler
 */
@Stateless
@LocalBean
public class DhlExportDataScheduler {

	private Properties properties = null;
	private FTPConnection ftpConnection = null;
	private InitialContext ctx;
	private MailServiceRemote mailManager;
	private static final Logger logger = Logger.getLogger(DhlExportDataScheduler.class);
	private PropertiesLoad propertiesLoad;
	private CommonValues commonValues;
	@EJB
	DhlExportDataInServiceBean exportDataInServiceBean;
	
	@EJB
	DhlExportDataInformService exportDataInformService;
	
//	@EJB
//	AckFileBuilder ackFileBuilder;
    /**
     * Default constructor. 
     */
    public DhlExportDataScheduler() {
        // TODO Auto-generated constructor stub
    }
    
	@Asynchronous
	@TransactionTimeout(3600)
    public void execute(String propertiesFileName){
    	
    	try {
			initResource(propertiesFileName);
	    	process();
		} catch (NamingException e) {
			
			logger.error(e);
			mailError(e, "");
			
		} catch (IOException e) {
			
			logger.error(e);
			mailError(e, "");
			
		} catch (Exception e) {
			
			logger.error(e);
			mailError(e, "");
			
		} finally{

			closeResource();
		}
    }
    
    private void process() throws IOException{
    	
    	//download file
    	String remoteFolder = properties.getProperty("core.ftp.path.exportdata");
	    String timePatternIn = DateUtil.getNowString(properties.getProperty("backup.timepattern.exportdata"));
	    String localBackupPath = FileUtil.concat(properties.getProperty("local.backup.path.exportdata"), timePatternIn);
	    
	    //Get partner name
	    String partnerName = properties.getProperty("workflow.partner.name");
	    
	    ArrayList<File> downloadFileList;
	    Workflow workflow;
	    
	    File ackFile = null;
	    String ackBackupFolder = properties.getProperty("local.backup.path.exportdata.ack");
    	String ackTimePattern = DateUtil.getNowString(properties.getProperty("backup.timepattern.exportdata.ack"));
    	String ackRemotePath = properties.getProperty("core.ftp.path.exportdata.ack");
    	String encoding = properties.getProperty("csv.file.encoding");
    	String ackOption = properties.getProperty("partner.ack.option");
    	String mailTestOption = properties.getProperty("mail.test.option");
    	
    	if(mailTestOption.equals("N")){
    		logger.info("mailTestOption: N");
    	}
    	
    	logger.info("mailTestOption: " + mailTestOption);
	    
		try {
			downloadFileList = ftpConnection.downloadFiles(remoteFolder, localBackupPath);
		} catch (IOException e1) {
			throw e1;
		}

		if(downloadFileList==null || downloadFileList.size() == 0){
			
			logger.info("No export data file in the ftp");
			return;
			
		}
		
		//deal with files
		for(File file:downloadFileList){
			

			try {
				
				logger.info("Deal with file: " + file.getName());
				workflow = exportDataInServiceBean.execute(file, partnerName, commonValues, encoding);
				
				logger.info("Call ERP stored procedure: " + file.getName());
				exportDataInformService.execute(workflow, commonValues, mailTestOption);
				
			} catch (Exception e) {
				
				logger.error(e);
				mailError(e, file.getName());
				continue;
			}
			
			//delete file in the ftp
			logger.info("Delete file from FTP: " + file.getName());
			ftpConnection.deleteFile(remoteFolder, file.getName());
			
//			if(ackOption.equals("Y")){
//				try {
////					ackFile = ackFileBuilder.execute(ackBackupFolder, ackTimePattern, workflow, file.getName());
//					logger.info("Upload ack-file to ftp: " + ackFile.getName());
//					ftpConnection.uploadFile(ackFile, ackRemotePath, ackFile.getName());
//				} catch (Exception e) {
//					
//					mailError(e, file.getName());
//					continue;
//					
//				}
//			}
		}
    }
    
    private void initResource(String propertiesFileName) throws Exception{
    	
    	commonValues = null;
    	commonValues = new CommonValues();
    	
		ctx = new InitialContext();
		mailManager = (MailServiceRemote) ctx.lookup("java:global/edi/service/Mail");
		
		propertiesLoad = new PropertiesLoad(propertiesFileName);
		
    	properties = propertiesLoad.getProperties();
    	ftpConnection = new FTPConnection();
		String host = properties.getProperty("core.ftp.host");
		String port = properties.getProperty("core.ftp.port");
		String user = properties.getProperty("core.ftp.user");
		String pwd = properties.getProperty("core.ftp.pwd");
		ftpConnection.connect(host, Integer.parseInt(port), user, pwd);
		
		String propertiesPartnerName = properties.getProperty("core.partner.name");
	    logger.info("Export Data In Start, Properties Partner Name: " + propertiesPartnerName);
		
		commonValues.setMailSubjErrorExport(propertiesPartnerName);
		commonValues.setMailSubjInfoExport(propertiesPartnerName);
		commonValues.setMailSubjSystemErrorExport(propertiesPartnerName);
		commonValues.setMailSubjInconsistentErrorExport(propertiesPartnerName);
		commonValues.setMailGroupExport(propertiesPartnerName);
		commonValues.setMailGroupSystemError(propertiesPartnerName);
		commonValues.setMailGroupCustom(propertiesPartnerName);
		commonValues.setMailGroupCoretronicImportExport(propertiesPartnerName);
		

    }
    
    private void closeResource(){
    	
		if(ftpConnection != null)
			ftpConnection.disConnect();
		
		CloseUtil.close(ctx);
    }
    
    private void mailError(Exception ex, String fileName){
    	
    	MailMessage message = new MailMessage();
    	StringWriter sw = new StringWriter();
	    ex.printStackTrace(new PrintWriter(sw));
	    message.setMailContent(sw.toString());
    	message.setMailSubject(commonValues.getMailSubjErrorExport() + " File: " + fileName);
    	message.setMailId("EDI.ERROR");
    	
    	try {
			mailManager.send(message);
		} catch (MailException e) {
			logger.debug(e.getMessage());
		}
    }

}