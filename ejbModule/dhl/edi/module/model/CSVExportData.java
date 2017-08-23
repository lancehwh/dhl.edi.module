package dhl.edi.module.model;

public class CSVExportData {

	String transactionType = null; // 進出口
	String forwarder = null; // 報關行名稱
	String entryNo = null; // 報單號碼
	String entryCate = null; // 報單類別
	String invoiceNum = null; // INV_NO
	String mawb = null; // 大提單號
	String hawb = null; // 小提單號
	String customer = null; // 交易方名稱
	String countryCode = null; // 交易方國別
	String departurePort = null; // 起運口岸代碼
	String voyNum = null; // 班次
	String qty = null; // 總件數
	String uom = null; // 單位
	String grossWeight = null; // 總毛重
	String currency = null; // 幣別
	String tradeTerms = null; // 貿易條件
	String fobORI = null; // 離岸價格(原幣)
	String fob = null; // 離岸價格(TWD)
	String declarationDate = null; // 報關日期
	String exportDate = null; // 出口日期
	String clearanceDate = null; // 放行日期
	String signatory = null; // 簽收人
	String receiptDate = null; // 簽收日期
	String lineNo = null; // 項次
	String buyerPartNo = null;// 買方料號
	String sellerPartNo = null;// 賣方料號
	String partName = null;// 品名
	String ybNB = null; // YB/NB
	String unitPrice = null; // 單價
	String qtyOfPart= null; // 數量
	String uomOfPart = null; // 數量單位
	String amount = null; // 金額(單價*數量)
	String dutiableValue = null; // 完稅價格(TWD)
	String cccCode = null; // CCC_CODE
	String statMethod = null; // 統計方式
	String serviceFee = null; // 推貿費
	String totalAmt = null; // 稅費合計
	String netWeight = null; //淨重
	String invoiceNumOfLine = null; //INV_NO of line
	String company = null; //公司別
	String customType = null; //海關別
	String containerNo = null; //櫃號
	
	
	
	public String getContainerNo() {
		return containerNo;
	}
	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCustomType() {
		return customType;
	}
	public void setCustomType(String costomType) {
		this.customType = costomType;
	}
	public String getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}
	public String getInvoiceNumOfLine() {
		return invoiceNumOfLine;
	}
	public void setInvoiceNumOfLine(String invoiceNumOfLine) {
		this.invoiceNumOfLine = invoiceNumOfLine;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getForwarder() {
		return forwarder;
	}
	public void setForwarder(String forwarder) {
		this.forwarder = forwarder;
	}
	public String getEntryNo() {
		return entryNo;
	}
	public void setEntryNo(String entryNo) {
		this.entryNo = entryNo;
	}
	public String getEntryCate() {
		return entryCate;
	}
	public void setEntryCate(String entryCate) {
		this.entryCate = entryCate;
	}
	public String getInvoiceNum() {
		return invoiceNum;
	}
	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}
	public String getMawb() {
		return mawb;
	}
	public void setMawb(String mawb) {
		this.mawb = mawb;
	}
	public String getHawb() {
		return hawb;
	}
	public void setHawb(String hawb) {
		this.hawb = hawb;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getDeparturePort() {
		return departurePort;
	}
	public void setDeparturePort(String departurePort) {
		this.departurePort = departurePort;
	}
	public String getVoyNum() {
		return voyNum;
	}
	public void setVoyNum(String voyNum) {
		this.voyNum = voyNum;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getTradeTerms() {
		return tradeTerms;
	}
	public void setTradeTerms(String tradeTerms) {
		this.tradeTerms = tradeTerms;
	}
	public String getFobORI() {
		return fobORI;
	}
	public void setFobORI(String fobORI) {
		this.fobORI = fobORI;
	}
	public String getFob() {
		return fob;
	}
	public void setFob(String fob) {
		this.fob = fob;
	}
	public String getDeclarationDate() {
		return declarationDate;
	}
	public void setDeclarationDate(String declarationDate) {
		this.declarationDate = declarationDate;
	}
	public String getExportDate() {
		return exportDate;
	}
	public void setExportDate(String exportDate) {
		this.exportDate = exportDate;
	}
	public String getClearanceDate() {
		return clearanceDate;
	}
	public void setClearanceDate(String clearanceDate) {
		this.clearanceDate = clearanceDate;
	}
	public String getSignatory() {
		return signatory;
	}
	public void setSignatory(String signatory) {
		this.signatory = signatory;
	}
	public String getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getBuyerPartNo() {
		return buyerPartNo;
	}
	public void setBuyerPartNo(String buyerPartNo) {
		this.buyerPartNo = buyerPartNo;
	}
	public String getSellerPartNo() {
		return sellerPartNo;
	}
	public void setSellerPartNo(String sellerPartNo) {
		this.sellerPartNo = sellerPartNo;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getYbNB() {
		return ybNB;
	}
	public void setYbNB(String ybNB) {
		this.ybNB = ybNB;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getQtyOfPart() {
		return qtyOfPart;
	}
	public void setQtyOfPart(String qtyOfPart) {
		this.qtyOfPart = qtyOfPart;
	}
	public String getUomOfPart() {
		return uomOfPart;
	}
	public void setUomOfPart(String uomOfPart) {
		this.uomOfPart = uomOfPart;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDutiableValue() {
		return dutiableValue;
	}
	public void setDutiableValue(String dutiableValue) {
		this.dutiableValue = dutiableValue;
	}
	public String getCccCode() {
		return cccCode;
	}
	public void setCccCode(String cccCode) {
		this.cccCode = cccCode;
	}
	public String getStatMethod() {
		return statMethod;
	}
	public void setStatMethod(String statMethod) {
		this.statMethod = statMethod;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	

}
