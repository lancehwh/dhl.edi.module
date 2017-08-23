package dhl.edi.module.util;

import org.apache.log4j.Logger;

public class OUIdBuilder {
	
	private static final Logger logger = Logger.getLogger(OUIdBuilder.class);

	public static int getOuIdByCompany(String company) throws Exception{
		
		int ouId = 0;
		
		if(Constant.ABBREVIATION_CORETRONIC.equals(company)){
			ouId = Constant.OUID_CORETRONIC;
		}else if (Constant.ABBREVIATION_YOUNGLIGHTING.equals(company)) {
			ouId = Constant.OUID_YOUNGLIGHTING;
		}else if (Constant.ABBREVIATION_YOUNGGREEN.equals(company)) {
			ouId = Constant.OUID_YOUNGGREEN;
		}else{
    		
    		logger.error("The company abbreviation is not supported. Company: " + company);
    		throw new Exception("The company abbreviation is not supported. Company: " + company);
    	}
		
		return ouId;
	}
	
	public static String getAbbreviationByOuId(int erpOUId) throws Exception{
		
		String abbreviation = null;
		
		if(Constant.OUID_CORETRONIC == erpOUId){
    		
			abbreviation = Constant.ABBREVIATION_CORETRONIC;
    		
    	}else if(Constant.OUID_YOUNGLIGHTING == erpOUId){
    		
    		abbreviation = Constant.ABBREVIATION_YOUNGLIGHTING;
    		
    	}else if(Constant.OUID_YOUNGGREEN == erpOUId){
    		
    		abbreviation = Constant.ABBREVIATION_YOUNGGREEN;
    		
    	}else{
    		
    		logger.error("The ERP OU ID is not supported. ERP OU ID: " + erpOUId);
    		throw new Exception("The ERP OU ID is not supported. ERP OU ID: " + erpOUId);
    	}
		
		return abbreviation;
	}
}
