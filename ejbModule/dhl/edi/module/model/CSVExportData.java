package dhl.edi.module.model;

public class CSVExportData {

	String transactionType = null; // �i�X�f
	String forwarder = null; // ������W��
	String entryNo = null; // ���渹�X
	String entryCate = null; // �������O
	String invoiceNum = null; // INV_NO
	String mawb = null; // �j���渹
	String hawb = null; // �p���渹
	String customer = null; // �����W��
	String countryCode = null; // ������O
	String departurePort = null; // �_�B�f���N�X
	String voyNum = null; // �Z��
	String qty = null; // �`���
	String uom = null; // ���
	String grossWeight = null; // �`��
	String currency = null; // ���O
	String tradeTerms = null; // �T������
	String fobORI = null; // ��������(���)
	String fob = null; // ��������(TWD)
	String declarationDate = null; // �������
	String exportDate = null; // �X�f���
	String clearanceDate = null; // �����
	String signatory = null; // ñ���H
	String receiptDate = null; // ñ�����
	String lineNo = null; // ����
	String buyerPartNo = null;// �R��Ƹ�
	String sellerPartNo = null;// ���Ƹ�
	String partName = null;// �~�W
	String ybNB = null; // YB/NB
	String unitPrice = null; // ���
	String qtyOfPart= null; // �ƶq
	String uomOfPart = null; // �ƶq���
	String amount = null; // ���B(���*�ƶq)
	String dutiableValue = null; // ���|����(TWD)
	String cccCode = null; // CCC_CODE
	String statMethod = null; // �έp�覡
	String serviceFee = null; // ���T�O
	String totalAmt = null; // �|�O�X�p
	String netWeight = null; //�b��
	String invoiceNumOfLine = null; //INV_NO of line
	String company = null; //���q�O
	String customType = null; //�����O
	String containerNo = null; //�d��
	
	
	
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
