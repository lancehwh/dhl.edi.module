
package dhl.edi.module.exportdata.session;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.coretronic.edi.entity.Workflow;
import com.coretronic.edi.service.WorkflowRemote;
import com.coretronic.edi.util.CloseUtil;
import com.coretronic.edi.util.WorkflowUtil;

import edi.service.MailServiceRemote;
import edi.service.entity.MailMessage;
import dhl.edi.module.exportdata.entity.CtcEcShuttleExportBuilder;
import dhl.edi.module.exportdata.entity.CtcEcShuttleExportH;
import dhl.edi.module.model.CSVExportData;
import dhl.edi.module.model.XmlDhlExportFormat;

import dhl.edi.module.util.Constant;
import dhl.edi.module.util.CSVReader;
import dhl.edi.module.util.CommonValues;

/**
 * Session Bean implementation class ExportDataInServiceBean
 */
@Stateless
@LocalBean
public class DhlExportDataInServiceBean{

	@PersistenceContext(unitName = "ERP_DB")
	private EntityManager em;
	private static final Logger logger = Logger.getLogger(DhlExportDataInServiceBean.class);
	private InitialContext ctx;
	private MailServiceRemote mailManager;
	private WorkflowRemote workflowManager;
	
    /**
     * Default constructor. 
     */
    public DhlExportDataInServiceBean() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ExportDataInService#execute()
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Workflow execute(File file, String partnerName, CommonValues commonValues, String encoding) throws Exception{
    	
    	Workflow workflow;
    	
    	try {
        	initResource();
        	workflow = process(file, partnerName, commonValues, encoding);
		} finally{
			closeResource();
		}
        
    	return workflow;
    }
    
    private Workflow process(File file, String partnerName, CommonValues commonValues, String encoding) throws Exception{
    	
    	List<CSVExportData> exportDatas = null;
    	CtcEcShuttleExportBuilder exportDataBuilder = null;
    	CtcEcShuttleExportH ctcEcShuttleExportH = null;
    	String entryNo = null; //³ø³æ¸¹½X
    	Workflow workflow = null;
    	
    	logger.info("read export csv: " + file.getName());    	
		exportDatas = CSVReader.readExportCSV(file, encoding);
		entryNo = exportDatas.get(0).getEntryNo();
		
		//void export data
		logger.info("Void export data: " + file.getName());
		Query query = em.createNamedQuery("VoidExportData");
		query.setParameter("voidDate", new Date());
		query.setParameter("entryNo", entryNo);
		query.executeUpdate();
		
		//insert export data
		logger.info("insert export data: " + file.getName());		
		exportDataBuilder = new CtcEcShuttleExportBuilder();
		ctcEcShuttleExportH = exportDataBuilder.build(exportDatas);
		ctcEcShuttleExportH.setCreationDate(new Date());
		em.persist(ctcEcShuttleExportH);
		
		//void workflow which has the same document name (entryNo)
		workflow = createWorkflow(entryNo, partnerName);
		workflowManager.voidWorkflow(workflow);
		
		//insert workflow
		//å°‡export header ID insert ?ï¿½ï¿½??attribute10æ¬„ï¿½??
		logger.info("insert workflow: " + file.getName());
		workflow.setAttribute10(String.valueOf(ctcEcShuttleExportH.getId()));
		workflow = workflowManager.create(workflow);
		
		logger.debug("WF_ID: " + workflow.getId());
		
		//mail report
		logger.info("Mail report: " + file.getName());
		mailReport(workflow, commonValues);
		
		return workflow;

    }
    
    private void mailReport(Workflow workflow, CommonValues commonValues){
    	
    	MailMessage message = new MailMessage();
    	message.setMailSubject(commonValues.getMailSubjInfoExport() + " " + "Export data is receved. EntryNO: " + workflow.getDocumentName());
    	message.setMailContent("WF_ID: " + workflow.getId());
    	message.setMailId(commonValues.getMailGroupExport());
    	
    	try {
			mailManager.send(message);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
    }
    
    private Workflow createWorkflow(String documentName, String partnerName){
        
    	Workflow workflow = WorkflowUtil.create(partnerName, 
    			Constant.TRANSACTION_IN, 
    			Constant.TRANSACTION_SET_EXPORT, 
    			documentName);
    	workflow.setEdiStandard("Z");
    	return workflow;
    }
    
    private void closeResource(){
		
		CloseUtil.close(ctx);
    }
    
    private void initResource() throws NamingException{
    			
		ctx = new InitialContext();
		mailManager = (MailServiceRemote) ctx.lookup("java:global/edi/service/Mail");
		workflowManager = (WorkflowRemote) ctx.lookup(WorkflowUtil.JNDI_NAME);

    }

}