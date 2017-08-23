package dhl.edi.module.exportdata.session;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import dhl.edi.module.exportdata.entity.CtcEcShuttleExportL;

/**
 * Session Bean implementation class QueryEIPNumbers
 */
@Stateless
@LocalBean
public class QueryEIPNumbers {

	private static final Logger logger = Logger.getLogger(QueryEIPNumbers.class);
	@PersistenceContext(unitName = "ERP_DB")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public QueryEIPNumbers() {
        // TODO Auto-generated constructor stub
    }
    
    public List<String> execute(int ouId, List<String> invoiceNumbers){
    	
    	Set<String> hashSetList = new HashSet<String>();
    	Query query = null;
    	String queryResult = null;
    	String resultCode = null;
    	String eipNum = null;
    	List<String> eipNumbers = new ArrayList<String>();
    	
       	//Â∞ãÊâæunique invoice number,?éª?ô§?õ∏??åÁ?Ñinvoice number
    	for(String temp:invoiceNumbers){
    		hashSetList.add(temp);
    	}
    	
    	//for loop ?ü•Ë©? EIP number
    	for(String invoiceNum : hashSetList){
    		
    		logger.debug("Query EIP number: OU_ID: " + ouId + " INV_NO: " + invoiceNum);
        	
        	query = em.createNativeQuery("select coredis.xx_edi_wsh_pkg.delivery_no_get_ie_infor(?,?,?) eipNum from dual");   		
        	query.setParameter(1, ouId);
        	query.setParameter(2, invoiceNum);
        	query.setParameter(3, "SN_NO");
        	queryResult = (String) query.getSingleResult();
        	
        	if(queryResult == null){
        		
        		logger.error("Can't query any EIP number. INV_NO: " + invoiceNum);       		
        		continue;
    		}
        	
        	//coredis.xx_edi_wsh_pkg.delivery_no_get_ie_infor(?,?,?) ??ûÂÇ≥??
        	//??ûÂÇ≥ 'Y' + ‰∏??†ºÁ©∫ÁôΩ + EIP number
        	//?Ö∂ÂÆÉÊ?ÖÊ??, ??áÂ?ûÂÇ≥ 'N'+ ‰∏??†ºÁ©∫ÁôΩ +?åØË™§Ë?äÊÅØ
        	resultCode = queryResult.substring(0, 1);
        	
        	logger.debug("coredis.xx_edi_wsh_pkg.delivery_no_get_ie_infor return: " + queryResult);
        	
        	//??âÊü•Ë©¢Âà∞ EIP number
        	if("Y".equals(resultCode)){
        		
        		eipNum = queryResult.substring(2);
        		eipNumbers.add(eipNum);
        		
        	}else if ("N".equals(resultCode)) {
    			
        		logger.error("Can't query any EIP number. Message: " + queryResult.substring(2));
        		continue;
        		
    		}else {
    			
    			logger.error("ERP DB Function return is not supported. Return: " + queryResult);
    			continue;
    		}
        	

    	}	
    	
    	return eipNumbers;
    }

}
