package dhl.edi.module.invpack.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CTC_EC_SHUTTLE_INVOICE_PACKS database table.
 * 
 */
@Entity
@Table(name="CTC_EC_SHUTTLE_INVOICE_PACKS")
@NamedQuery(name="CtcEcShuttleInvoicePack.findAll", query="SELECT c FROM CtcEcShuttleInvoicePack c")
public class CtcEcShuttleInvoicePack implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal addfee;

	private BigDecimal apkg;

	private String bom;

	private String bond;

	private String brand;

	private String bsaddr;

	private String bscode;

	private String bscountry;

	private String bsname;

	private String bycm;

	private String byship;

	@Column(name="CREATED_BY")
	private BigDecimal createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE")
	private Date creationDate;

	private String curr;

	@Column(name="DECL_TYPE")
	private String declType;

	private String descp;

	private String flno;

	private BigDecimal frtfee;

	private String fwd;

	private String hawb;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="IE_DATE")
	private Date ieDate;

	@Column(name="IE_TYPE")
	private String ieType;

	private String invno;

	private BigDecimal isfee;

	private BigDecimal item;

	private String lwh;

	private String lwhu;

	private String mawb;

	@Column(name="\"MONTH\"")
	private String month;

	private BigDecimal nw;

	@Column(name="OR_DECL")
	private String orDecl;

	@Column(name="OTHER_DECLARATION")
	private String otherDeclaration;

	@Column(name="PAK_ITEM")
	private String pakItem;

	private String pkgu;

	@Temporal(TemporalType.DATE)
	@Column(name="PR_DATE")
	private Date prDate;

	private BigDecimal price;

	private BigDecimal qty;

	private String qtyu;

	private String remark;

	@Column(name="SHUTTLE_INVOICE_PACKS_GROUP_ID")
	private BigDecimal shuttleInvoicePacksGroupId;

	@Id
	@Column(name="SHUTTLE_INVOICE_PACKS_ID")
	private BigDecimal shuttleInvoicePacksId;

	@Column(name="SO_LINE_NO")
	private String soLineNo;

	@Column(name="SO_NO")
	private BigDecimal soNo;

	private String staddr;

	private String stcountry;

	private String stname;

	private BigDecimal subfee;

	private String sucm;

	@Column(name="TAX_RULE_CODE")
	private String taxRuleCode;
	
	@Column(name="DHL_STATISTICS")
	private String dhlStatistics;

	@Column(name="DHL_DECLARATION")
	private String dhlDeclaration;

	private String tdaddr;

	private String tdcode;

	private String tdcountry;

	private String tdname;

	private String terms;

	private BigDecimal tllgw;

	private BigDecimal tllnw;

	private BigDecimal total;

	public CtcEcShuttleInvoicePack() {
	}

	public BigDecimal getAddfee() {
		return this.addfee;
	}

	public void setAddfee(BigDecimal addfee) {
		this.addfee = addfee;
	}

	public BigDecimal getApkg() {
		return this.apkg;
	}

	public void setApkg(BigDecimal apkg) {
		this.apkg = apkg;
	}

	public String getBom() {
		return this.bom;
	}

	public void setBom(String bom) {
		this.bom = bom;
	}

	public String getBond() {
		return this.bond;
	}

	public void setBond(String bond) {
		this.bond = bond;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBsaddr() {
		return this.bsaddr;
	}

	public void setBsaddr(String bsaddr) {
		this.bsaddr = bsaddr;
	}

	public String getBscode() {
		return this.bscode;
	}

	public void setBscode(String bscode) {
		this.bscode = bscode;
	}

	public String getBscountry() {
		return this.bscountry;
	}

	public void setBscountry(String bscountry) {
		this.bscountry = bscountry;
	}

	public String getBsname() {
		return this.bsname;
	}

	public void setBsname(String bsname) {
		this.bsname = bsname;
	}

	public String getBycm() {
		return this.bycm;
	}

	public void setBycm(String bycm) {
		this.bycm = bycm;
	}

	public String getByship() {
		return this.byship;
	}

	public void setByship(String byship) {
		this.byship = byship;
	}

	public BigDecimal getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCurr() {
		return this.curr;
	}

	public void setCurr(String curr) {
		this.curr = curr;
	}

	public String getDeclType() {
		return this.declType;
	}

	public void setDeclType(String declType) {
		this.declType = declType;
	}

	public String getDescp() {
		return this.descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getFlno() {
		return this.flno;
	}

	public void setFlno(String flno) {
		this.flno = flno;
	}

	public BigDecimal getFrtfee() {
		return this.frtfee;
	}

	public void setFrtfee(BigDecimal frtfee) {
		this.frtfee = frtfee;
	}

	public String getFwd() {
		return this.fwd;
	}

	public void setFwd(String fwd) {
		this.fwd = fwd;
	}

	public String getHawb() {
		return this.hawb;
	}

	public void setHawb(String hawb) {
		this.hawb = hawb;
	}

	public Date getIeDate() {
		return this.ieDate;
	}

	public void setIeDate(Date ieDate) {
		this.ieDate = ieDate;
	}

	public String getIeType() {
		return this.ieType;
	}

	public void setIeType(String ieType) {
		this.ieType = ieType;
	}

	public String getInvno() {
		return this.invno;
	}

	public void setInvno(String invno) {
		this.invno = invno;
	}

	public BigDecimal getIsfee() {
		return this.isfee;
	}

	public void setIsfee(BigDecimal isfee) {
		this.isfee = isfee;
	}

	public BigDecimal getItem() {
		return this.item;
	}

	public void setItem(BigDecimal item) {
		this.item = item;
	}

	public String getLwh() {
		return this.lwh;
	}

	public void setLwh(String lwh) {
		this.lwh = lwh;
	}

	public String getLwhu() {
		return this.lwhu;
	}

	public void setLwhu(String lwhu) {
		this.lwhu = lwhu;
	}

	public String getMawb() {
		return this.mawb;
	}

	public void setMawb(String mawb) {
		this.mawb = mawb;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getNw() {
		return this.nw;
	}

	public void setNw(BigDecimal nw) {
		this.nw = nw;
	}

	public String getOrDecl() {
		return this.orDecl;
	}

	public void setOrDecl(String orDecl) {
		this.orDecl = orDecl;
	}

	public String getOtherDeclaration() {
		return this.otherDeclaration;
	}

	public void setOtherDeclaration(String otherDeclaration) {
		this.otherDeclaration = otherDeclaration;
	}

	public String getPakItem() {
		return this.pakItem;
	}

	public void setPakItem(String pakItem) {
		this.pakItem = pakItem;
	}

	public String getPkgu() {
		return this.pkgu;
	}

	public void setPkgu(String pkgu) {
		this.pkgu = pkgu;
	}

	public Date getPrDate() {
		return this.prDate;
	}

	public void setPrDate(Date prDate) {
		this.prDate = prDate;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getQty() {
		return this.qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public String getQtyu() {
		return this.qtyu;
	}

	public void setQtyu(String qtyu) {
		this.qtyu = qtyu;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getShuttleInvoicePacksGroupId() {
		return this.shuttleInvoicePacksGroupId;
	}

	public void setShuttleInvoicePacksGroupId(BigDecimal shuttleInvoicePacksGroupId) {
		this.shuttleInvoicePacksGroupId = shuttleInvoicePacksGroupId;
	}

	public BigDecimal getShuttleInvoicePacksId() {
		return this.shuttleInvoicePacksId;
	}

	public void setShuttleInvoicePacksId(BigDecimal shuttleInvoicePacksId) {
		this.shuttleInvoicePacksId = shuttleInvoicePacksId;
	}

	public String getSoLineNo() {
		return this.soLineNo;
	}

	public void setSoLineNo(String soLineNo) {
		this.soLineNo = soLineNo;
	}

	public BigDecimal getSoNo() {
		return this.soNo;
	}

	public void setSoNo(BigDecimal soNo) {
		this.soNo = soNo;
	}

	public String getStaddr() {
		return this.staddr;
	}

	public void setStaddr(String staddr) {
		this.staddr = staddr;
	}

	public String getStcountry() {
		return this.stcountry;
	}

	public void setStcountry(String stcountry) {
		this.stcountry = stcountry;
	}

	public String getStname() {
		return this.stname;
	}

	public void setStname(String stname) {
		this.stname = stname;
	}

	public BigDecimal getSubfee() {
		return this.subfee;
	}

	public void setSubfee(BigDecimal subfee) {
		this.subfee = subfee;
	}

	public String getSucm() {
		return this.sucm;
	}

	public void setSucm(String sucm) {
		this.sucm = sucm;
	}

	public String getTaxRuleCode() {
		return this.taxRuleCode;
	}

	public void setTaxRuleCode(String taxRuleCode) {
		this.taxRuleCode = taxRuleCode;
	}

	public String getTdaddr() {
		return this.tdaddr;
	}

	public void setTdaddr(String tdaddr) {
		this.tdaddr = tdaddr;
	}

	public String getTdcode() {
		return this.tdcode;
	}

	public void setTdcode(String tdcode) {
		this.tdcode = tdcode;
	}

	public String getTdcountry() {
		return this.tdcountry;
	}

	public void setTdcountry(String tdcountry) {
		this.tdcountry = tdcountry;
	}

	public String getTdname() {
		return this.tdname;
	}

	public void setTdname(String tdname) {
		this.tdname = tdname;
	}

	public String getTerms() {
		return this.terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public BigDecimal getTllgw() {
		return this.tllgw;
	}

	public void setTllgw(BigDecimal tllgw) {
		this.tllgw = tllgw;
	}

	public BigDecimal getTllnw() {
		return this.tllnw;
	}

	public void setTllnw(BigDecimal tllnw) {
		this.tllnw = tllnw;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public String getDhlStatistics() {
		return dhlStatistics;
	}

	public void setDhlStatistics(String dhlStatistics) {
		this.dhlStatistics = dhlStatistics;
	}

	public String getDhlDeclaration() {
		return dhlDeclaration;
	}

	public void setDhlDeclaration(String dhlDeclaration) {
		this.dhlDeclaration = dhlDeclaration;
	}

}