package dhl.edi.module.exportdata.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CTC_EC_SHUTTLE_EXPORT_L database table.
 * 
 */
@Entity
@Table(name="CTC_EC_SHUTTLE_EXPORT_L")
@Cacheable(false)
public class CtcEcShuttleExportL implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CTC_EC_SHUTTLE_EXPORT_L_ID_GENERATOR", sequenceName="CTC_EC_SHUTTLE_EXPORT_L_S", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CTC_EC_SHUTTLE_EXPORT_L_ID_GENERATOR")
	private long id;
	
	private BigDecimal amount;

	@Column(name="BUYER_PART_NO")
	private String buyerPartNo;

	@Column(name="CCC_CODE")
	private String cccCode;

	@Column(name="DUTIABLE_VALUE")
	private BigDecimal dutiableValue;

	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="EXPORT_HEADER_ID")
	private CtcEcShuttleExportH exportHeader;

	@Column(name="LINE_NO")
	private long lineNo;

	@Column(name="PART_NAME")
	private String partName;

	private BigDecimal qty;

	@Column(name="SELLER_PART_NO")
	private String sellerPartNo;

	@Column(name="STAT_METHOD")
	private String statMethod;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;

	private String uom;

	@Column(name="YB_NB")
	private String ybNb;
	
	@Column(name="NET_WEIGHT")
	private BigDecimal netWeight;
	
	@Column(name="INV_NO")
	private String invNo;
	
	@Column(name="EDI_MESSAGE")
	private String ediMessage;
	

	
	


	public BigDecimal getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public String getEdiMessage() {
		return ediMessage;
	}

	public void setEdiMessage(String ediMessage) {
		this.ediMessage = ediMessage;
	}

	public CtcEcShuttleExportH getExportHeader() {
		return exportHeader;
	}

	public void setExportHeader(CtcEcShuttleExportH exportHeader) {
		this.exportHeader = exportHeader;
	}
	
	public CtcEcShuttleExportL() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBuyerPartNo() {
		return this.buyerPartNo;
	}

	public void setBuyerPartNo(String buyerPartNo) {
		this.buyerPartNo = buyerPartNo;
	}

	public String getCccCode() {
		return this.cccCode;
	}

	public void setCccCode(String cccCode) {
		this.cccCode = cccCode;
	}

	public BigDecimal getDutiableValue() {
		return this.dutiableValue;
	}

	public void setDutiableValue(BigDecimal dutiableValue) {
		this.dutiableValue = dutiableValue;
	}


	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLineNo() {
		return this.lineNo;
	}

	public void setLineNo(long lineNo) {
		this.lineNo = lineNo;
	}

	public String getPartName() {
		return this.partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public BigDecimal getQty() {
		return this.qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public String getSellerPartNo() {
		return this.sellerPartNo;
	}

	public void setSellerPartNo(String sellerPartNo) {
		this.sellerPartNo = sellerPartNo;
	}

	public String getStatMethod() {
		return this.statMethod;
	}

	public void setStatMethod(String statMethod) {
		this.statMethod = statMethod;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUom() {
		return this.uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getYbNb() {
		return this.ybNb;
	}

	public void setYbNb(String ybNb) {
		this.ybNb = ybNb;
	}

}