package dhl.edi.module.model;

public class CSVImportData {

	String transactionType = null; // ?≤Âá∫?è£
	String forwarder = null; // ?†±??úË?åÂ?çÁ®±
	String entryNo = null; // ?†±?ñÆ??üÁ¢º
	String entryCate = null; // ?†±?ñÆÈ°ûÂà•
	String invoiceNum = null; // INV_NO
	String mawb = null; // Â§ßÊ?êÂñÆ???
	String hawb = null; // Â∞èÊ?êÂñÆ???
	String customer = null; // ‰∫§Ê?ìÊñπ??çÁ®±
	String countryCode = null; // ‰∫§Ê?ìÊñπ??ãÂà•
	String departurePort = null; // Ëµ∑È?ãÂè£Â≤∏‰ª£Á¢?
	String voyNum = null; // ?è≠Ê¨?
	String qty = null; // Á∏Ω‰ª∂?ï∏
	String uom = null; // ?ñÆ‰Ω?
	String grossWeight = null; // Á∏ΩÊ?õÈ??
	String currency = null; // Âπ??à•
	String tradeTerms = null; // Ë≤øÊ?ìÊ?ù‰ª∂
	String cifORI = null; // ??üÂπ£Ëµ∑Â≤∏?Éπ?†º
	String cif = null; // ?õ¢Â≤∏ÂÉπ?†º(TWD)
	String declarationDate = null; // ?†±??úÊó•???
	String importDate = null; // ?≤Âè£?ó•???
	String clearanceDate = null; // ?îæË°åÊó•???
	String signatory = null; // Á∞ΩÊî∂‰∫?
	String receiptDate = null; // Á∞ΩÊî∂?ó•???
	String lineNo = null; // ??ÖÊ¨°
	String buyerPartNo = null;// Ë≤∑Êñπ??ôË??
	String sellerPartNo = null;// Ë≥??ñπ??ôË??
	String partName = null;// ??ÅÂ??
	String ybNB = null; // YB/NB
	String unitPrice = null; // ?ñÆ?Éπ
	String qtyOfPart= null; // ?ï∏???
	String uomOfPart = null; // ?ï∏??èÂñÆ‰Ω?
	String amount = null; // ??ëÈ??(?ñÆ?Éπ*?ï∏???)
	String dutiableValue = null; // ÂÆåÁ?ÖÂÉπ?†º(TWD)
	String cccCode = null; // CCC_CODE
	String taxTerms = null; //Á¥çÁ?ÖËæ¶Ê≥?
	String taxRate = null; //?≤Âè£ÂæûÂÉπÁ®ÖÁ??
	String serviceFee = null; // ?é®Ë≤øË≤ª
	String importTax = null; //?≤Âè£Á®?
	String businessTax = null; //??üÊ•≠Á®?
	String commodityTax = null; //Ë≤®Áâ©Á®?
	String totalAmt = null; // Á®ÖË≤ª??àË??
	String businessTaxBase = null; //??üÊ•≠Á®ÖÂü∫
	String netWeight = null; //Ê∑®È??
	String invoiceNumOfLine = null; //INV_NO of line
	String company = null; //?Ö¨?è∏?à•
	String customType = null; //Êµ∑È?úÂà•
	String containerNo = null; //Ê´ÉË??
	
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
	public String getCifORI() {
		return cifORI;
	}
	public void setCifORI(String cifORI) {
		this.cifORI = cifORI;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getImportDate() {
		return importDate;
	}
	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}
	public String getTaxTerms() {
		return taxTerms;
	}
	public void setTaxTerms(String taxTerms) {
		this.taxTerms = taxTerms;
	}
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	public String getImportTax() {
		return importTax;
	}
	public void setImportTax(String importTax) {
		this.importTax = importTax;
	}
	public String getBusinessTax() {
		return businessTax;
	}
	public void setBusinessTax(String businessTax) {
		this.businessTax = businessTax;
	}
	public String getCommodityTax() {
		return commodityTax;
	}
	public void setCommodityTax(String commodityTax) {
		this.commodityTax = commodityTax;
	}
	public String getBusinessTaxBase() {
		return businessTaxBase;
	}
	public void setBusinessTaxBase(String businessTaxBase) {
		this.businessTaxBase = businessTaxBase;
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
	public String getDeclarationDate() {
		return declarationDate;
	}
	public void setDeclarationDate(String declarationDate) {
		this.declarationDate = declarationDate;
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
	public String getContainerNo() {
		return containerNo;
	}
	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}

	

}
