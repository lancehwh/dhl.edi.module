
package dhl.edi.module.exportdata.session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Email;

import com.coretronic.edi.entity.Workflow;
import com.coretronic.edi.service.WorkflowRemote;
import com.coretronic.edi.storedprocedure.ParameterType;
import com.coretronic.edi.storedprocedure.SPCallerInterface;
import com.coretronic.edi.storedprocedure.SPInfomation;
import com.coretronic.edi.storedprocedure.SPParameter;
import com.coretronic.edi.util.CloseUtil;
import com.coretronic.edi.util.WorkflowUtil;

import edi.service.MailServiceRemote;
import edi.service.entity.MailGroup;
import edi.service.entity.MailMessage;

import dhl.edi.module.exportdata.entity.CtcEcShuttleExportH;
import dhl.edi.module.exportdata.entity.CtcEcShuttleExportL;
import dhl.edi.module.mail.EmailTaskHandler;
import dhl.edi.module.util.Constant;
import dhl.edi.module.util.HtmlBuilder;
import dhl.edi.module.util.OUIdBuilder;
import dhl.edi.module.util.CommonValues;
import dhl.edi.module.util.StoredProcedureBuilder;
import dhl.edi.module.util.StoredProcedureResult;

/**
 * Session Bean implementation class ERPImportExportDataService
 */
@Stateless
@LocalBean
public class DhlExportDataInformService {

	@PersistenceContext(unitName = "ERP_DB")
	private EntityManager em;
	InitialContext ctx = null;
	private MailServiceRemote mailManager;
	private SPCallerInterface storedProcedureCaller;
	private static final Logger logger = Logger.getLogger(DhlExportDataInformService.class);
	private WorkflowRemote workflowManager;
	@EJB
	QueryEIPNumbers queryEIPNumbersService;
	
	/**
     * Default constructor. 
     */
    public DhlExportDataInformService() {
        // TODO Auto-generated constructor stub
    }

    public void execute(Workflow workflow, CommonValues commonValues, String mailTestOption) throws Exception{
    	
    	try {
			initResource();
			process(workflow, commonValues, mailTestOption);
			
		} catch (NamingException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally{
			closeResource();
		}
    }
    
    private void process(Workflow workflow, CommonValues commonValues, String mailTestOption) throws Exception{
    	
    	List<SPParameter> storedProcedureResults = null;
    	StoredProcedureBuilder storedProcedureBuilder = new StoredProcedureBuilder();
    	StoredProcedureResult storedProcedureResult = null;
		
		//call ERP stored procedure to validate data
		List<SPParameter> inputParameterList = storedProcedureBuilder.buildSPInputParameter(workflow.getId());
		SPInfomation spInfomation = storedProcedureBuilder.buildSPInformation();
		storedProcedureResults = storedProcedureCaller.callStoredProcedure(spInfomation, inputParameterList);
		
		//get stored procedure result
		storedProcedureResult = storedProcedureBuilder.getStoredProcedureResult(storedProcedureResults);
		
		//test
		//storedProcedureResult = new StoredProcedureResult();
		//storedProcedureResult.setStatus(Constant.STATUS_ERROR);
		//storedProcedureResult.setErrorType(Constant.ERROR_CODE_EXPORT_DATA_INCONSISTENT);
		
		logger.debug("stored procedure result: P_STATUS: " + storedProcedureResult.getStatus());
		logger.debug("stored procedure result: P_ERR_TYPE: " + storedProcedureResult.getErrorType());
		logger.debug("stored procedure result: P_CUSTOM_AREA: " + storedProcedureResult.getCustomArea());
		
		//test
		//storedProcedureResult.setStatus("ERROR");
		
		if(Constant.STATUS_OK.equals(storedProcedureResult.getStatus())){
			
			//update worflow to finished
			workflow.setStatusFlag(WorkflowUtil.FINISH);
			workflowManager.update(workflow);
		
			//mail alert
			mailSuccess(workflow, commonValues);
			
		}else if(Constant.STATUS_ERROR.equals(storedProcedureResult.getStatus())){
			
			//update workflow to error
			workflow.setStatusFlag(WorkflowUtil.ERROR);
			workflowManager.update(workflow);
			
			//mail alert
			mailValidateError(workflow, storedProcedureResult, commonValues, mailTestOption);
						
		}else {
			
			throw new Exception("Stored Procedure COREFIN.IEFEE_EDI_UPDATE() reponse is not supported. P_STATUS: " + storedProcedureResult.getStatus());
		}
		
    	
    }
    
    private void mailSuccess(Workflow workflow, CommonValues commonValues){
    	
    	MailMessage message = new MailMessage();
    	message.setMailSubject(commonValues.getMailSubjInfoExport() + " " + "Export data is validated by ERP. EntryNO: " + workflow.getDocumentName());
    	message.setMailContent("WF_ID: " + workflow.getId());
    	message.setMailId(commonValues.getMailGroupExport());
    	
    	try {
			mailManager.send(message);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
    }
    
    private void mailValidateError(Workflow workflow, StoredProcedureResult procedureResult, CommonValues commonValues, String mailTestOption) throws Exception{
    	
    	int errorType = procedureResult.getErrorType();

    	String mailContent = null;
    	String mailSubject = null;
    	List<String> toMailList = null;
    	List<String> ccMailList = null;
    	
		//query mail alert content
		long exportHeaderId = Long.parseLong(workflow.getAttribute10());		
		CtcEcShuttleExportH exportHeader = em.find(CtcEcShuttleExportH.class, exportHeaderId);
				
		HtmlBuilder htmlBuilder = new HtmlBuilder();
		EmailTaskHandler emailTaskHandler = new EmailTaskHandler();
		
		//test
		//errorType = 1;
		
    	switch (errorType) {
    	
    	    //ERP stored procedure import data error
			case Constant.ERROR_CODE_EXPORT_DATA_SYSTEM_ERROR:
				
				//將system 管理者加至 mail to list
				MailGroup systemErrorGroup = mailManager.find(commonValues.getMailGroupSystemError());
				List<String> systemErrorMails = emailTaskHandler.splitMailListString(systemErrorGroup.getTo());
				if(systemErrorMails != null && systemErrorMails.size()>0){
					toMailList = new ArrayList<String>();
					toMailList.addAll(systemErrorMails);
				}
				
				mailSubject = commonValues.getMailSubjSystemErrorExport();
				mailContent = "WF_ID: " + workflow.getId() + " ErrorMessage: " + exportHeader.getErrorMessage();
				
				break;
			
			//出口資料比對錯誤,通知五崧與中光電進出口
			case Constant.ERROR_CODE_EXPORT_DATA_INCONSISTENT:	
				
				String company = exportHeader.getCompany();
				int ouId = OUIdBuilder.getOuIdByCompany(company);
				String ieUserMail = null;
				ieUserMail = queryIEUserMail(ouId, exportHeader);
				
				//將該報單負責進出口人員加入 mail to List
				toMailList = new ArrayList<String>();
				if(ieUserMail!=null){
					if(mailTestOption.equals("N")){
						toMailList.add(ieUserMail);
						logger.info("將該報單負責進出口人員加入 Mail List");
					}
				}
				
				//將 Shuttle 的 mail 加入 mail CC list
				ccMailList = new ArrayList<String>();
				MailGroup shuttleMailGroup = mailManager.find(commonValues.getMailGroupCustom());
				List<String> shuttleMails = emailTaskHandler.splitMailListString(shuttleMailGroup.getTo());				
				if(shuttleMails != null && shuttleMails.size()>0){
					ccMailList.addAll(shuttleMails);
				}
				logger.debug("Dhl.Custom mail group: " + shuttleMailGroup.getTo());
				
				//將 Coretronic 進出口部門主管(ex, sherry tung, Marie)加入 mail CC list
				MailGroup coretronicImportExport = mailManager.find(commonValues.getMailGroupCoretronicImportExport());
				List<String> coretronicMails = emailTaskHandler.splitMailListString(coretronicImportExport.getTo());
				if(coretronicMails != null && coretronicMails.size()>0){
					ccMailList.addAll(coretronicMails);
				}
				logger.debug("Coretronic.ImportExport mail group: " + coretronicImportExport.getTo());
				
				//查詢此出口報單對應的出貨通知EIP編號
				List<String> invoiceNumbers = getInvoiceNumbers(exportHeader);
				List<String> eipNumbers = queryEIPNumbersService.execute(ouId, invoiceNumbers);
				String eipNumbersString = buildEIPNumberString(eipNumbers);
				
				mailContent = htmlBuilder.buildExportDataAlertHtml(exportHeader, ieUserMail);										
				mailSubject = commonValues.getMailSubjInconsistentErrorExport() + " EIP_NO:" + eipNumbersString;
				
				break;
				
			default:
				throw new Exception("Stored Procedure COREFIN.IEFEE_EDI_UPDATE() reponse is not supported. P_ERR_TYPE: " + errorType);
		}
 
		emailTaskHandler.sendHtmlMail(mailContent, mailSubject, toMailList, ccMailList, null);
		
}
    //將EIP numbers 組成字串(1234 1234 1234)，以放在郵件主旨
    private String buildEIPNumberString(List<String> eipNumbers){
    	
    	if(eipNumbers==null || eipNumbers.size()==0){
    		return "";
    	}
    	
    	String resultString = null;
    	StringBuilder stringBuilder = new StringBuilder();
    	
    	for(String temp : eipNumbers){
    		stringBuilder.append(temp);
    		stringBuilder.append(" ");
    	}
    	
    	resultString = stringBuilder.toString();
    	
    	return resultString;
    	
    }

    private List<String> getInvoiceNumbers(CtcEcShuttleExportH exportHeader){
    	
    	List<String> invoiceNumbers = new ArrayList<String>();
    	
    	for(CtcEcShuttleExportL temp:exportHeader.getCtcEcShuttleExportLs()){
    		
    		if(temp.getInvNo() != null){
    			invoiceNumbers.add(temp.getInvNo());
    		}
    	}
    	
    	return invoiceNumbers;
    }

    /*  呼叫ERP function(provided by Peggy) 以　Invoice Number查詢負責進出口人員的mail
     * 
     *  function delivery_no_get_ie_infor ( p_ou_id         in  number
                                       , p_delivery_no   in  varchar2
                                       , p_infor_type    in  varchar2  )
     	return varchar2;
     */
    private String queryIEUserMail(int ouId, CtcEcShuttleExportH exportHeader) throws Exception{
    	
    	//出口報單上的所有Invoice只會對應到一個進出口人員
		//以invoice number查詢該報單負責的進出口人員名稱與mail
    	List<CtcEcShuttleExportL> exportLines = exportHeader.getCtcEcShuttleExportLs();
    	String invoiceNum = null;
    	String mail = null;
    	Query mailQuery = null;
    	List<Object> mails = null;
    	
    	//for loop 找 invoice number
    	for(CtcEcShuttleExportL temp:exportLines){
    	
    		invoiceNum = temp.getInvNo();
    		
    		if(invoiceNum == null){
    			continue;  			
    		}else {
				break;
			}
        	
    	}
    	

    	
    	try {
    		logger.debug("Query IE User Mail: OU_ID: " + ouId + " INV_NO: " + invoiceNum);
    		
    		mailQuery = em.createNativeQuery("select coredis.xx_edi_wsh_pkg.delivery_no_get_ie_infor(?,?,?) mail from dual");   		
        	mailQuery.setParameter(1, ouId);
        	mailQuery.setParameter(2, invoiceNum);
        	mailQuery.setParameter(3, "EMAIL_ADDR");
        	mails = mailQuery.getResultList();
        	
        	if(mails == null || mails.size() == 0){
        		throw new Exception("Can't query any IE User mail. INV_NO: " + invoiceNum);
    		}
        	
        	//coredis.xx_edi_wsh_pkg.delivery_no_get_ie_infor(?,?,?) 回傳值
        	//當出貨通知單上 mail to 欄位有值時, 視同有e-mail address資料, 所以回傳 'Y' + 一格空白 + e-mail address
        	//其它情況, 則回傳 'N'+ 一格空白 +錯誤訊息
        	String queryResult = (String) mails.get(0);
        	String hasMail = queryResult.substring(0, 1);
        	
        	logger.debug("coredis.xx_edi_wsh_pkg.delivery_no_get_ie_infor return: " + queryResult);
        	
        	if("Y".equals(hasMail)){
        		
        		mail = queryResult.substring(2);
        		
        	}else if ("N".equals(hasMail)) {
				
        		throw new Exception("Can't query any IE User mail. Message: " + queryResult.substring(2));
        		
			}else {
				
				throw new Exception("ERP DB Function return is not supported. Return: " + queryResult);
			}
        	
        	logger.debug("Query IE User Mail result: " + mail);
        	
		} catch(SQLException sqlException){
			throw sqlException;
		} catch (Exception e) {
			throw e;
		}
    	
    	return mail;
    }
    
    private void closeResource(){
    
    	CloseUtil.close(ctx);
    }
    
    private void initResource() throws NamingException, SQLException{
		
    	ctx = new InitialContext();
    	mailManager = (MailServiceRemote) ctx.lookup("java:global/edi/service/Mail");
    	storedProcedureCaller = (SPCallerInterface) ctx.lookup("java:global/edi/service/StoredProcedureCaller");
    	workflowManager = (WorkflowRemote) ctx.lookup(WorkflowUtil.JNDI_NAME);
    	
    }
}