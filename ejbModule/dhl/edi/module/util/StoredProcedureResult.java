package dhl.edi.module.util;

public class StoredProcedureResult {
	
	public String status = null; 
	public int errorType = 0;
	public int customArea = 0;
	
	
	public int getCustomArea() {
		return customArea;
	}
	public void setCustomArea(int customArea) {
		this.customArea = customArea;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getErrorType() {
		return errorType;
	}
	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}
	
	

}
