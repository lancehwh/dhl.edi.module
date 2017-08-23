package dhl.edi.module.exportdata.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CTC_EC_SHUTTLE_EXPORT_H database table.
 * 
 */
@Entity
@Table(name="CTC_EC_SHUTTLE_EXPORT_H")
@Cacheable(false)
public class CtcEcShuttleExportH implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CTC_EC_SHUTTLE_EXPORT_H_ID_GENERATOR", sequenceName="CTC_EC_SHUTTLE_EXPORT_H_S", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CTC_EC_SHUTTLE_EXPORT_H_ID_GENERATOR")
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CLEARANCE_DATE")
	private Date clearanceDate;

	@Column(name="COUNTRY_CODE")
	private String countryCode;

	private String currency;

	private String customer;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DECLARATION_DATE")
	private Date declarationDate;

	@Column(name="DEPARTURE_PORT")
	private String departurePort;

	@Column(name="ENTRY_CATE")
	private String entryCate;

	@Column(name="ENTRY_NO")
	private String entryNo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EXPORT_DATE")
	private Date exportDate;

	private BigDecimal fob;

	@Column(name="FOB_ORI")
	private BigDecimal fobOri;

	private String fowarder;

	@Column(name="GROSS_WEIGHT")
	private BigDecimal grossWeight;

	private String hawb;

	@Column(name="INVOICE_NUM")
	private String invoiceNum;

	private String mawb;

	private BigDecimal qty;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="RECEIPT_DATE")
	private Date receiptDate;

	private String signatory;

	@Column(name="TRADE_TERMS")
	private String tradeTerms;

	@Column(name="TRANSACTION_TYPE")
	private String transactionType;

	private String uom;

	@Column(name="VOY_NUM")
	private String voyNum;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
	@OneToMany(mappedBy="exportHeader", cascade = CascadeType.PERSIST)
	@OrderBy("lineNo ASC")
	private List<CtcEcShuttleExportL> ctcEcShuttleExportLs;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="VOID_DATE")
	private Date voidDate;

	@Column(name="SERVICE_FEE")
	private BigDecimal serviceFee;
	
	@Column(name="TOTAL_AMT")
	private BigDecimal totalAmt;
	
	@Column(name="COMPANY")
	private String company;
	
	@Column(name="CUSTOM_TYPE")
	private String customType;
	
	@Column(name="ERROR_MESSAGE")
	private String errorMessage;
	
	@Column(name="CONTAINER_NO")
	private String containerNo;
	
	
	
	public String getContainerNo() {
		return containerNo;
	}

	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

	public void setCustomType(String customType) {
		this.customType = customType;
	}
	
	public Date getVoidDate() {
		return voidDate;
	}
	
	

	public BigDecimal getServiceFee() {
		return serviceFee;
	}



	public void setServiceFee(BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
	}



	public BigDecimal getTotalAmt() {
		return totalAmt;
	}



	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}



	public void setVoidDate(Date voidDate) {
		this.voidDate = voidDate;
	}



	public CtcEcShuttleExportH() {
	}

	
	
	public Date getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



	public List<CtcEcShuttleExportL> getCtcEcShuttleExportLs() {
		return ctcEcShuttleExportLs;
	}

	public void setCtcEcShuttleExportLs(
			List<CtcEcShuttleExportL> ctcEcShuttleExportLs) {
		this.ctcEcShuttleExportLs = ctcEcShuttleExportLs;
	}



	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getClearanceDate() {
		return this.clearanceDate;
	}

	public void setClearanceDate(Date clearanceDate) {
		this.clearanceDate = clearanceDate;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Date getDeclarationDate() {
		return this.declarationDate;
	}

	public void setDeclarationDate(Date declarationDate) {
		this.declarationDate = declarationDate;
	}

	public String getDeparturePort() {
		return this.departurePort;
	}

	public void setDeparturePort(String departurePort) {
		this.departurePort = departurePort;
	}

	public String getEntryCate() {
		return this.entryCate;
	}

	public void setEntryCate(String entryCate) {
		this.entryCate = entryCate;
	}

	public String getEntryNo() {
		return this.entryNo;
	}

	public void setEntryNo(String entryNo) {
		this.entryNo = entryNo;
	}

	public Date getExportDate() {
		return this.exportDate;
	}

	public void setExportDate(Date exportDate) {
		this.exportDate = exportDate;
	}

	public BigDecimal getFob() {
		return this.fob;
	}

	public void setFob(BigDecimal fob) {
		this.fob = fob;
	}

	public BigDecimal getFobOri() {
		return this.fobOri;
	}

	public void setFobOri(BigDecimal fobOri) {
		this.fobOri = fobOri;
	}

	public String getFowarder() {
		return this.fowarder;
	}

	public void setFowarder(String fowarder) {
		this.fowarder = fowarder;
	}

	public BigDecimal getGrossWeight() {
		return this.grossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getHawb() {
		return this.hawb;
	}

	public void setHawb(String hawb) {
		this.hawb = hawb;
	}

	public String getInvoiceNum() {
		return this.invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public String getMawb() {
		return this.mawb;
	}

	public void setMawb(String mawb) {
		this.mawb = mawb;
	}

	public BigDecimal getQty() {
		return this.qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public Date getReceiptDate() {
		return this.receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getSignatory() {
		return this.signatory;
	}

	public void setSignatory(String signatory) {
		this.signatory = signatory;
	}

	public String getTradeTerms() {
		return this.tradeTerms;
	}

	public void setTradeTerms(String tradeTerms) {
		this.tradeTerms = tradeTerms;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getUom() {
		return this.uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getVoyNum() {
		return this.voyNum;
	}

	public void setVoyNum(String voyNum) {
		this.voyNum = voyNum;
	}

}