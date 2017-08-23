package dhl.edi.module.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.coretronic.edi.storedprocedure.ParameterType;
import com.coretronic.edi.storedprocedure.SPInfomation;
import com.coretronic.edi.storedprocedure.SPParameter;

/*
COREFIN.IEFEE_EDI_UPDATE (P_STATUS OUT VARCHAR2,
               P_ERR_TYPE OUT NUMBER,      
               P_CUSTOM_AREA OUT NUMBER,   
               P_WF_ID  IN NUMBER)
               
è³‡æ?™æ­£ç¢?: P_STATUS ??žå‚³ "OK"
è³‡æ?™éŒ¯èª?: P_STATUS ??žå‚³ "ERROR"

P_ERR_TYPE => 1 å¯„çµ¦äº”å´§??‡é?²å‡º?£äººå“¡
P_ERR_TYPE => 2 å¯„çµ¦?²å‡º?£äººå“¡
P_ERR_TYPE => 3 å¯„çµ¦ç³»çµ±ç®¡ç?†è??

P_CUSTOM_AREA ==>  1  ?–°ç«? (ç«¹ç??)
P_CUSTOM_AREA ==>  2  é«˜é?? (??—ç??)
*/
public class StoredProcedureBuilder {
	
	private static final Logger logger = Logger.getLogger(StoredProcedureBuilder.class);
	private static final String ERPDB_DATASOURCE_JNDI = "java:/jdbc/non_xa/Coretronic_EDI_DB";
//	private static final String ERP_STORED_PROCEDURE = "COREFIN.IEFEE_EDI_UPDATE(?,?,?,?)";
	private static final String ERP_STORED_PROCEDURE = "coredis.xx_edi_wsh_pkg.edi_update_iefee(?,?,?,?)";
	
	public SPInfomation buildSPInformation(){
		
    	SPInfomation spInfomation = new SPInfomation();
		spInfomation.setDataSourceJNDI(ERPDB_DATASOURCE_JNDI);
		spInfomation.setStoredProcedureName(ERP_STORED_PROCEDURE);

		return spInfomation;
	}
	
	public List<SPParameter> buildSPInputParameter(long workflowId) {
		
		SPParameter status = new SPParameter();
		status.setParameterType(ParameterType.OUT);
		status.setParameterName(Constant.SP_PARAMETER_OUT_STATUS);
		status.setParameterSQLType(java.sql.Types.VARCHAR);
		
		SPParameter errorType = new SPParameter();
		errorType.setParameterType(ParameterType.OUT);
		errorType.setParameterName(Constant.SP_PARAMETER_OUT_ERROR_TYPE);
		errorType.setParameterSQLType(java.sql.Types.INTEGER);

		SPParameter customArea = new SPParameter();
		customArea.setParameterType(ParameterType.OUT);
		customArea.setParameterName(Constant.SP_PARAMETER_OUT_CUSTOM_AREA);
		customArea.setParameterSQLType(java.sql.Types.INTEGER);
		
		SPParameter wfId = new SPParameter();
		wfId.setParameterType(ParameterType.IN);
		wfId.setParameterName(Constant.SP_PARAMETER_IN_WFID);
		wfId.setParameterSQLType(java.sql.Types.BIGINT);
		wfId.setParameterValue(workflowId);
		
		
		
		List<SPParameter> inputParameterList = new ArrayList<SPParameter>();
		inputParameterList.add(status);
		inputParameterList.add(errorType);
		inputParameterList.add(customArea);
		inputParameterList.add(wfId);
		
		return inputParameterList;
	}
	
	public StoredProcedureResult getStoredProcedureResult(List<SPParameter> procedureResults) throws Exception{
		
		String erpImportStatus = null;
		int importErrorCode = 0;
		int customArea = 0;
		StoredProcedureResult storedProcedureResult = new StoredProcedureResult();
		
		//get stored procedure result
		if(procedureResults!=null && procedureResults.size()>0){
			
			for(SPParameter temp:procedureResults){
				
				if(Constant.SP_PARAMETER_OUT_STATUS.equals(temp.getParameterName())){
					
					erpImportStatus = (String) temp.getParameterValue();
					logger.debug("erpImportStatus: " + erpImportStatus);
					
				}else if(Constant.SP_PARAMETER_OUT_ERROR_TYPE.equals(temp.getParameterName())) {
					
					if(temp.getParameterValue() != null){
						importErrorCode = (Integer) temp.getParameterValue();
					}
					
				}else if(Constant.SP_PARAMETER_OUT_CUSTOM_AREA.equals(temp.getParameterName())){
					
					if(temp.getParameterValue() != null){
						customArea = (Integer) temp.getParameterValue();
					}
					
				}
				
			}
			
			storedProcedureResult.setStatus(erpImportStatus);
			storedProcedureResult.setErrorType(importErrorCode);
			storedProcedureResult.setCustomArea(customArea);
			
		}else {
			
			throw new Exception("Call ERP stored procedure and get null result");
		}
		
		return storedProcedureResult;
	}

}
