package dhl.edi.module.importdata.entity;

import java.io.Serializable;
import javax.persistence.*;

import dhl.edi.module.importdata.entity.CtcEcDhlImportL;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CTC_EC_DHL_IMPORT_H database table.
 * 
 */
@Entity
@Table(name="CTC_EC_DHL_IMPORT_H")
@NamedQuery(name="CtcEcDhlImportH.findAll", query="SELECT c FROM CtcEcDhlImportH c")
public class CtcEcDhlImportH implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="APP_TYPE")
	private String appType;

	@Column(name="CONSIGNEE_ID")
	private String consigneeId;

	@Column(name="CONSIGNEE_NAME")
	private String consigneeName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE")
	private Date creationDate;

	private String hawb;

	@Column(name="SHIPPER_NAME")
	private String shipperName;

	@Column(name="TRADE_TYPE")
	private String tradeType;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "DhlImportHeader" ,orphanRemoval=true)
	@OrderBy("itemNo")
	private List<CtcEcDhlImportL> CtcEcDhlImportLineList ;

	public CtcEcDhlImportH() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAppType() {
		return this.appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getConsigneeId() {
		return this.consigneeId;
	}

	public void setConsigneeId(String consigneeId) {
		this.consigneeId = consigneeId;
	}

	public String getConsigneeName() {
		return this.consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public Date getCreateDate() {
		return this.creationDate;
	}

	public void setCreateDate(Date createDate) {
		this.creationDate = createDate;
	}

	public String getHawb() {
		return this.hawb;
	}

	public void setHawb(String hawb) {
		this.hawb = hawb;
	}

	public String getShipperName() {
		return this.shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public String getTradeType() {
		return this.tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public List<CtcEcDhlImportL> getCtcEcDhlImportLineList() {
		return CtcEcDhlImportLineList;
	}

	public void setCtcEcDhlImportLineList(List<CtcEcDhlImportL> ctcEcDhlImportLineList) {
		CtcEcDhlImportLineList = ctcEcDhlImportLineList;
	}

}