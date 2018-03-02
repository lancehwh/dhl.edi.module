package dhl.edi.module.importdata.mdb;

import java.io.IOException;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.coretronic.edi.entity.Workflow;
import com.coretronic.util.JaxbUtil;
import dhl.edi.module.importdata.session.DhlImportDataReqBean;;


/**
 * Message-Driven Bean implementation class for: InvoicePackingMDB
 */
@MessageDriven(activationConfig = {
	    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
	    @ActivationConfigProperty(propertyName = "destination", propertyValue = "/queue/workflow"),
	    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
	    @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "WORKFLOW_NAME = 'DHL_IMPORT_REQ'")
	})
public class DhlImportDataReqMDB implements MessageListener {

	private static final Logger logger = Logger.getLogger(DhlImportDataReqMDB.class);
	@EJB
	DhlImportDataReqBean DhlImportDataReqBean;
    @Resource
    private MessageDrivenContext mdc;
    /**
     * Default constructor. 
     */
    public DhlImportDataReqMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        
    	Workflow workFlow = null;
    	TextMessage textMessage = null;
    	
    	if(message instanceof TextMessage){
    		
    		try {
        		textMessage = (TextMessage) message;
				workFlow = (Workflow) JaxbUtil.unmarshal(Workflow.class, textMessage.getText());
//				
				logger.debug("DHL InvoicePacking MDB receive message: WF_ID: " + workFlow.getId());
				DhlImportDataReqBean.execute(workFlow);
	    		
			} catch (JAXBException e) {
				
				logger.error(e);
				mdc.setRollbackOnly();
				
			} catch (JMSException e) {
				
				logger.error(e);
				mdc.setRollbackOnly();
				
			} 
//    		catch (IOException e) {
//				
//				logger.error(e);
//				mdc.setRollbackOnly();
//				
//			}
    		catch (Exception e) {
				
				logger.error(e);
				mdc.setRollbackOnly();
			}

    		
    	}else {
			
    		logger.error("DHL DhlImportDataReqMDB receves message which is not TextMessage");
		}
        
    }

}