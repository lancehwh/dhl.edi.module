package dhl.edi.module.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Item")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlDhlExportLine {
	
	@XmlElement(name = "CustCode")
	private String CustCode = "Coretronic"; // 客戶代號 (Unique key)

	@XmlElement(name = "ItemNo")
	private String ItemNo; // 出口報單項次 LINE_NO
	
	@XmlElement(name = "InvItemNo")
	private BigDecimal InvItemNo; // 客戶項次 ITEM
	
	@XmlElement(name = "ItemMark")
	private String ItemMark; // 商標 BRAND

	@XmlElement(name = "ItemIbAppNo")
	private String ItemIbAppNo; // 原進口報單 OR_DECL
	
	@XmlElement(name = "Hawb")
	private String Hawb; // 小提單號  HAWB

	@XmlElement(name = "ItemPoNo")
	private String ItemPoNo; // 買方料號 BUYER_PART_NO
	
	@XmlElement(name = "ItemSoNo")
	private String ItemSoNo; // 賣方料號  SELLER_PART_NO
	
	@XmlElement(name = "ItemName")
	private String ItemName; // 品名 PART_NAME
	
	@XmlElement(name = "ItemOffCD1")
	private String ItemOffCD1; // YB/NB YB_NB
	
	@XmlElement(name = "ItemUnitPrice")
	private BigDecimal ItemUnitPrice; // 單價 UNIT_PRICE 
	
	@XmlElement(name = "ItemForAmt")
	private BigDecimal ItemForAmt; // 離岸價格 (外幣) 
	
	@XmlElement(name = "ItemCurrencyCode")
	private String ItemCurrencyCode; // 幣別  CURRENCY

	@XmlElement(name = "ItemQTY")
	private BigDecimal ItemQTY; // 數量 QTY
	
	@XmlElement(name = "ItemUnit")
	private String ItemUnit; // 數量單位 UOM
	
	@XmlElement(name = "ItemExpNo")
	private String ItemExpNo; // 輸出許口證號碼 BOM

	@XmlElement(name = "ItemCCCCode")
	private String ItemCCCCode; // CCC_CODE CCC_CODE
	
	@XmlElement(name = "ItemStcWay")
	private String ItemStcWay; // 統計方式  STAT_METHOD
	
	@XmlElement(name = "ItemNetWgt")
	private BigDecimal ItemNetWgt; // 淨重  NET_WEIGHT
	
	@XmlElement(name = "InvNo")
	private String InvNo; // ITEM_INV_NO INV_NO

	public String getItemNo() {
		return ItemNo;
	}

	public void setItemNo(String itemNo) {
		this.ItemNo = itemNo;
	}

	public String getItemPoNo() {
		return ItemPoNo;
	}

	public void setItemPoNo(String itemPoNo) {
		this.ItemPoNo = itemPoNo;
	}

	public String getItemSoNo() {
		return ItemSoNo;
	}

	public void setItemSoNo(String itemSoNo) {
		this.ItemSoNo = itemSoNo;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		this.ItemName = itemName;
	}

	public String getItemOffCD1() {
		return ItemOffCD1;
	}

	public void setItemOffCD1(String itemOffCD1) {
		this.ItemOffCD1 = itemOffCD1;
	}

	public BigDecimal getItemUnitPrice() {
		return ItemUnitPrice;
	}

	public void setItemUnitPrice(BigDecimal ItemUnitPrice) {
		this.ItemUnitPrice = ItemUnitPrice;
	}

	public BigDecimal getItemQTY() {
		return ItemQTY;
	}

	public void setItemQTY(BigDecimal ItemQTY) {
		this.ItemQTY = ItemQTY;
	}

	public String getItemUnit() {
		return ItemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.ItemUnit = itemUnit;
	}

	public String getItemCCCCode() {
		return ItemCCCCode;
	}

	public void setItemCCCCode(String itemCCCCode) {
		this.ItemCCCCode = itemCCCCode;
	}

	public String getItemStcWay() {
		return ItemStcWay;
	}

	public void setItemStcWay(String itemStcWay) {
		this.ItemStcWay = itemStcWay;
	}

	public BigDecimal getItemNetWgt() {
		return ItemNetWgt;
	}

	public void setItemNetWgt(BigDecimal ItemNetWgt) {
		this.ItemNetWgt = ItemNetWgt;
	}

	public String getInvNo() {
		return InvNo;
	}

	public void setInvNo(String InvNo) {
		this.InvNo = InvNo;
	}
	
	public BigDecimal getInvItemNo() {
		return InvItemNo;
	}

	public void setInvItemNo(BigDecimal invItemNo) {
		InvItemNo = invItemNo;
	}

	public String getItemMark() {
		if( ItemMark == null )
		{
			ItemMark = "NO BRAND";
		}
		
		return ItemMark;
	}

	public void setItemMark(String itemMark) {
		ItemMark = itemMark;
	}

	public String getItemIbAppNo() {
		return ItemIbAppNo;
	}

	public void setItemIbAppNo(String itemIbAppNo) {
		ItemIbAppNo = itemIbAppNo;
	}

	public String getHawb() {
		return Hawb;
	}

	public void setHawb(String hawb) {
		Hawb = hawb;
	}
	
	public String getItemCurrencyCode() {
		return ItemCurrencyCode;
	}

	public void setItemCurrencyCode(String itemCurrencyCode) {
		ItemCurrencyCode = itemCurrencyCode;
	}
	
	public BigDecimal getItemForAmt() {
		return ItemForAmt;
	}

	public void setItemForAmt(BigDecimal itemForAmt) {
		ItemForAmt = itemForAmt;
	}

	public String getCustCode() {
		
		return CustCode;
	}

	public void setCustCode(String custCode) {
		CustCode = custCode;
	}
	
	public String getItemExpNo() {
		return ItemExpNo;
	}

	public void setItemExpNo(String itemExpNo) {
		ItemExpNo = itemExpNo;
	}
	
	
}
