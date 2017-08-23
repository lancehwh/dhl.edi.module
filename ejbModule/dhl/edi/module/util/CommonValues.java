package dhl.edi.module.util;

public class CommonValues {
	
	private String info = "[INFO]";
	private String error = "[ERROR]";
	private String importRelease = "[ImportRelease]";
	private String importSigned = "[ImportSigned]";
	private String export = "[Export]";
	private String invPack = "[InvPack]";
	private String invPackAck = "[InvPackAck]";
	private String systemError = " System Error";
	private String inconsistentString = " 出口報單回寫資料比對錯誤";
	
	private String mailGroupNameImportRelease = ".Import.Release";
	private String mailGroupNameImportSigned = ".Import.Signed";
	private String mailGroupNameExport = ".Export";
	private String mailGroupNameInvPack = ".InvPack";
	private String mailGroupNameCustom = ".Custom";
	private String mailGroupNameCoretronicImportExport = "Coretronic.ImportExport";
	private String mailGroupNameSystemError = ".System.Error";
	
	//Mail Subj Error
	private String mailSubjErrorImportRelease = null;
	private String mailSubjErrorImportSigned = null;
	private String mailSubjErrorExport = null;
	private String mailSubjErrorInvPack = null;
	private String mailSubjErrorInvPackAck = null;
	
	//Mail Subj Info
	private String mailSubjInfoImportRelease = null;
	private String mailSubjInfoImportSigned = null;
	private String mailSubjInfoExport = null;
	private String mailSubjInfoInvPack = null;
	private String mailSubjInfoInvPackAck = null;
	
	//Mail Subj System Error
	private String mailSubjSystemErrorExport = null;
	private String mailSubjSystemErrorImportRelease = null;
	private String mailSubjSystemErrorImportSigned = null;
	
	//Mail Subj Inconsistent Error
	private String mailSubjInconsistentErrorExport = null;
	
	//Mail Group
	private String mailGroupImportRelease = null;
	private String mailGroupImportSigned = null;
	private String mailGroupExport = null;
	private String mailGroupInvPack = null;
	private String mailGroupCustom = null;
	private String mailGroupCoretronicImportExport = null;
	private String mailGroupSystemError = null;
	
	
	public String getMailSubjErrorImportRelease() {
		return mailSubjErrorImportRelease;
	}
	public void setMailSubjErrorImportRelease(String mailSubjErrorImportRelease) {
		if(!mailSubjErrorImportRelease.contains(error)){
			mailSubjErrorImportRelease = error + "[" + mailSubjErrorImportRelease + "]" + importRelease;
		}
		this.mailSubjErrorImportRelease = mailSubjErrorImportRelease;
	}
	public String getMailSubjErrorImportSigned() {
		return mailSubjErrorImportSigned;
	}
	public void setMailSubjErrorImportSigned(String mailSubjErrorImportSigned) {
		if(!mailSubjErrorImportSigned.contains(error)){
			mailSubjErrorImportSigned = error + "[" + mailSubjErrorImportSigned + "]" + importSigned;
		}
		this.mailSubjErrorImportSigned = mailSubjErrorImportSigned;
	}
	public String getMailSubjErrorExport() {
		return mailSubjErrorExport;
	}
	public void setMailSubjErrorExport(String mailSubjErrorExport) {
		if(!mailSubjErrorExport.contains(error)){
			mailSubjErrorExport = error + "[" + mailSubjErrorExport + "]" + export;
		}
		this.mailSubjErrorExport = mailSubjErrorExport;
	}
	public String getMailSubjErrorInvPack() {
		return mailSubjErrorInvPack;
	}
	public void setMailSubjErrorInvPack(String mailSubjErrorInvPack) {
		if(!mailSubjErrorInvPack.contains(error)){
			mailSubjErrorInvPack = error + "[" + mailSubjErrorInvPack + "]" + invPack;
		}
		this.mailSubjErrorInvPack = mailSubjErrorInvPack;
	}
	public String getMailSubjErrorInvPackAck() {
		return mailSubjErrorInvPackAck;
	}
	public void setMailSubjErrorInvPackAck(String mailSubjErrorInvPackAck) {
		if(!mailSubjErrorInvPackAck.contains(error)){
			mailSubjErrorInvPackAck = error + "[" + mailSubjErrorInvPackAck + "]" + invPackAck;
		}
		this.mailSubjErrorInvPackAck = mailSubjErrorInvPackAck;
	}
	public String getMailSubjInfoImportRelease() {
		return mailSubjInfoImportRelease;
	}
	public void setMailSubjInfoImportRelease(String mailSubjInfoImportRelease) {
		if(!mailSubjInfoImportRelease.contains(info)){
			mailSubjInfoImportRelease = info + "[" + mailSubjInfoImportRelease + "]" + importRelease;
		}
		this.mailSubjInfoImportRelease = mailSubjInfoImportRelease;
	}
	public String getMailSubjInfoImportSigned() {
		return mailSubjInfoImportSigned;
	}
	public void setMailSubjInfoImportSigned(String mailSubjInfoImportSigned) {
		if(!mailSubjInfoImportSigned.contains(info)){
			mailSubjInfoImportSigned = info + "[" + mailSubjInfoImportSigned + "]" + importSigned;
		}
		this.mailSubjInfoImportSigned = mailSubjInfoImportSigned;
	}
	public String getMailSubjInfoExport() {
		return mailSubjInfoExport;
	}
	public void setMailSubjInfoExport(String mailSubjInfoExport) {
		if(!mailSubjInfoExport.contains(info)){
			mailSubjInfoExport = info + "[" + mailSubjInfoExport + "]" + export;
		}
		this.mailSubjInfoExport = mailSubjInfoExport;
	}
	public String getMailSubjInfoInvPack() {
		return mailSubjInfoInvPack;
	}
	public void setMailSubjInfoInvPack(String mailSubjInfoInvPack) {
		if(!mailSubjInfoInvPack.contains(info)){
			mailSubjInfoInvPack = info + "[" + mailSubjInfoInvPack + "]" + invPack;
		}
		this.mailSubjInfoInvPack = mailSubjInfoInvPack;
	}
	public String getMailSubjInfoInvPackAck() {
		return mailSubjInfoInvPackAck;
	}
	public void setMailSubjInfoInvPackAck(String mailSubjInfoInvPackAck) {
		if(!mailSubjInfoInvPackAck.contains(info)){
			mailSubjInfoInvPackAck = info + "[" + mailSubjInfoInvPackAck + "]" + invPackAck;
		}
		this.mailSubjInfoInvPackAck = mailSubjInfoInvPackAck;
	}
	public String getMailSubjSystemErrorExport() {
		return mailSubjSystemErrorExport;
	}
	public void setMailSubjSystemErrorExport(String mailSubjSystemErrorExport) {
		if(!mailSubjSystemErrorExport.contains(error)){
			mailSubjSystemErrorExport = error + "[" + mailSubjSystemErrorExport + "]" + export + systemError;
		}
		this.mailSubjSystemErrorExport = mailSubjSystemErrorExport;
	}
	public String getMailSubjSystemErrorImportRelease() {
		return mailSubjSystemErrorImportRelease;
	}
	public void setMailSubjSystemErrorImportRelease(
			String mailSubjSystemErrorImportRelease) {
		if(!mailSubjSystemErrorImportRelease.contains(error)){
			mailSubjSystemErrorImportRelease = error + "[" + mailSubjSystemErrorImportRelease + "]" + importRelease + systemError;
		}
		this.mailSubjSystemErrorImportRelease = mailSubjSystemErrorImportRelease;
	}
	public String getMailSubjSystemErrorImportSigned() {
		return mailSubjSystemErrorImportSigned;
	}
	public void setMailSubjSystemErrorImportSigned(
			String mailSubjSystemErrorImportSigned) {
		if(!mailSubjSystemErrorImportSigned.contains(error)){
			mailSubjSystemErrorImportSigned = error + "[" + mailSubjSystemErrorImportSigned + "]" + importSigned + systemError;
		}
		this.mailSubjSystemErrorImportSigned = mailSubjSystemErrorImportSigned;
	}
	public String getMailSubjInconsistentErrorExport() {
		return mailSubjInconsistentErrorExport;
	}
	public void setMailSubjInconsistentErrorExport(
			String mailSubjInconsistentErrorExport) {
		if(!mailSubjInconsistentErrorExport.contains(inconsistentString)){
			mailSubjInconsistentErrorExport = "[" + mailSubjInconsistentErrorExport + "]" + inconsistentString;
		}
		this.mailSubjInconsistentErrorExport = mailSubjInconsistentErrorExport;
	}
	public String getMailGroupImportRelease() {
		return mailGroupImportRelease;
	}
	public void setMailGroupImportRelease(String mailGroupImportRelease) {
		if(!mailGroupImportRelease.contains(mailGroupNameImportRelease)){
			mailGroupImportRelease = mailGroupImportRelease + mailGroupNameImportRelease;
		}
		this.mailGroupImportRelease = mailGroupImportRelease;
	}
	public String getMailGroupImportSigned() {
		return mailGroupImportSigned;
	}
	public void setMailGroupImportSigned(String mailGroupImportSigned) {
		if(!mailGroupImportSigned.contains(mailGroupNameImportSigned)){
			mailGroupImportSigned = mailGroupImportSigned + mailGroupNameImportSigned;
		}
		this.mailGroupImportSigned = mailGroupImportSigned;
	}
	public String getMailGroupExport() {
		return mailGroupExport;
	}
	public void setMailGroupExport(String mailGroupExport) {
		if(!mailGroupExport.contains(mailGroupNameExport)){
			mailGroupExport = mailGroupExport + mailGroupNameExport;
		}
		this.mailGroupExport = mailGroupExport;
	}
	public String getMailGroupInvPack() {
		return mailGroupInvPack;
	}
	public void setMailGroupInvPack(String mailGroupInvPack) {
		if(!mailGroupInvPack.contains(mailGroupNameInvPack)){
			mailGroupInvPack = mailGroupInvPack + mailGroupNameInvPack;
		}
		this.mailGroupInvPack = mailGroupInvPack;
	}
	public String getMailGroupCustom() {
		return mailGroupCustom;
	}
	public void setMailGroupCustom(String mailGroupCustom) {
		if(!mailGroupCustom.contains(mailGroupNameCustom)){
			mailGroupCustom = mailGroupCustom + mailGroupNameCustom;
		}
		this.mailGroupCustom = mailGroupCustom;
	}
	public String getMailGroupCoretronicImportExport() {
		return mailGroupCoretronicImportExport;
	}
	public void setMailGroupCoretronicImportExport(
			String mailGroupCoretronicImportExport) {
		if(!mailGroupCoretronicImportExport.contains(mailGroupNameCoretronicImportExport)){
			mailGroupCoretronicImportExport = mailGroupNameCoretronicImportExport;
		}
		this.mailGroupCoretronicImportExport = mailGroupCoretronicImportExport;
	}
	public String getMailGroupSystemError() {
		return mailGroupSystemError;
	}
	public void setMailGroupSystemError(String mailGroupSystemError) {
		if(!mailGroupSystemError.contains(mailGroupNameSystemError)){
			mailGroupSystemError = mailGroupSystemError + mailGroupNameSystemError;
		}
		this.mailGroupSystemError = mailGroupSystemError;
	}

}
