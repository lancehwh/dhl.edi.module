package dhl.edi.module.importdata.entity;

import java.io.Serializable;
import javax.persistence.*;

import dhl.edi.module.importdata.entity.CtcEcDhlImportH;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CTC_EC_DHL_IMPORT_L database table.
 * 
 */
@Entity
@Table(name="CTC_EC_DHL_IMPORT_L")
@NamedQuery(name="CtcEcDhlImportL.findAll", query="SELECT c FROM CtcEcDhlImportL c")
public class CtcEcDhlImportL implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

//	@Column(name="HEADER_ID")
//	private BigDecimal headerId;

	@Column(name="ITEM_CURRENCY")
	private String itemCurrency;

	@Column(name="ITEM_DESC")
	private String itemDesc;

	@Column(name="ITEM_NO")
	private BigDecimal itemNo;

	@Column(name="ITEM_QTY")
	private BigDecimal itemQty;

	@Column(name="ITEM_UNIPRICE")
	private BigDecimal itemUniprice;

	@Column(name="ITEM_UNIT")
	private String itemUnit;

	@Column(name="TRADE_TYPE")
	private String tradeType;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "HEADER_ID")
	private CtcEcDhlImportH DhlImportHeader;

	public CtcEcDhlImportL() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	public BigDecimal getHeaderId() {
//		return this.headerId;
//	}
//
//	public void setHeaderId(BigDecimal headerId) {
//		this.headerId = headerId;
//	}

	public String getItemCurrency() {
		return this.itemCurrency;
	}

	public void setItemCurrency(String itemCurrency) {
		this.itemCurrency = itemCurrency;
	}

	public String getItemDesc() {
		return this.itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public BigDecimal getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(BigDecimal itemNo) {
		this.itemNo = itemNo;
	}

	public BigDecimal getItemQty() {
		return this.itemQty;
	}

	public void setItemQty(BigDecimal itemQty) {
		this.itemQty = itemQty;
	}

	public BigDecimal getItemUniprice() {
		return this.itemUniprice;
	}

	public void setItemUniprice(BigDecimal itemUniprice) {
		this.itemUniprice = itemUniprice;
	}

	public String getItemUnit() {
		return this.itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	public String getTradeType() {
		return this.tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public CtcEcDhlImportH getDhlImportHeader() {
		return DhlImportHeader;
	}

	public void setDhlImportHeader(CtcEcDhlImportH dhlImportHeader) {
		DhlImportHeader = dhlImportHeader;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
	

}