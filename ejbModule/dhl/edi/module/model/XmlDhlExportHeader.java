package dhl.edi.module.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DeclarationForm")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlDhlExportHeader {
	
	
	@XmlElement(name = "CustCode")
	private String CustCode = "Coretronic" ; // 客戶代號 (Unique key)
	
	@XmlElement(name = "AppNo")
	private String AppNo; // 報單號碼  ENTRY_NO
	
	@XmlElement(name = "AppType")
	private String AppType; // 報單類別  ENTRY_CATE
	
	@XmlElement(name = "InvNo")
	private String InvNo; // INV_NO INVOICE_NUM
	
	@XmlElement(name = "Mawb")
	private String Mawb; // 大提單號  MAWB
	
	@XmlElement(name = "Hawb")
	private String Hawb; // 小提單號  HAWB
	
	@XmlElement(name = "HawbE")
	private String HawbE = "1"; // 小提單號  HawbE

	@XmlElement(name = "ShipperEaddr1")
	private String ShipperEaddr1; // 中強集團-地址 TDADDR
	
	@XmlElement(name = "ShipperEaddr2")
	private String ShipperEaddr2; // 中強集團-地址 TDADDR
	
	@XmlElement(name = "ShipperEaddr3")
	private String ShipperEaddr3; // 中強集團-地址 TDADDR
	
	@XmlElement(name = "ShipperCtm")
	private String ShipperCtm; // 中強集團-監管編號 TDCODE
	
	@XmlElement(name = "ShipperCer")
	private String ShipperCer; // 賣方統一編號
		
	@XmlElement(name = "ConsigneeName")
	private String ConsigneeName; // 交易方-公司名 BSNAME
	
	@XmlElement(name = "ConsigneeAddr1")
	private String ConsigneeAddr1; // 交易方-地址 BSADDR
	
	@XmlElement(name = "ConsigneeAddr2")
	private String ConsigneeAddr2; // 交易方-地址 BSADDR
	
	@XmlElement(name = "ConsigneeAddr3")
	private String ConsigneeAddr3; // 交易方-地址 BSADDR
	
	@XmlElement(name = "OrigCty")
	private String OrigCty; // 交易方-國別 BSCOUNTRY
	
	@XmlElement(name = "ConsigneeCtm")
	private String ConsigneeCtm; // 交易方-監管編號 BSCODE
	
	@XmlElement(name = "ShipToAddr")
	private String ShipToAddr; // 收/發貨人-地址 STADDR
	
	@XmlElement(name = "FlightFee")
	private BigDecimal FlightFee; // 運費 FRTFEE
	
	@XmlElement(name = "InsuranceFee")
	private BigDecimal InsuranceFee; // 保險費 ISFEE
	
	@XmlElement(name = "AddFee")
	private BigDecimal AddFee; // 應加費用 ADDFEE
	
	@XmlElement(name = "SubFee")
	private BigDecimal SubFee; // 應減費用  SUBFEE
	
	@XmlElement(name = "TotalNW076")
	private BigDecimal TotalNW076; // 總淨重 TLLNW
	
	
	@XmlElement(name = "Mark")
	private String Mark; // 標記 REMARK
	
	@XmlElement(name = "Other")
	private String Other; // 其他申報事項 OTHER_DECLARATION
	

	@XmlElement(name = "ShipToName") 
	private String ShipToName; // 交易方名稱  CUSTOMER
	
	@XmlElement(name = "DestCty")
	private String DestCty; // 交易方國別  COUNTRY_CODE
	
	@XmlElement(name = "OutStation") 
	private String OutStation; // 輸出口岸代碼  DEPARTURE_PORT
	
	@XmlElement(name = "FlightNo")
	private String FlightNo; // 班次  VOY_NUM
	
	@XmlElement(name = "TotPce")
	private BigDecimal TotPce; // 總件數  QTY
	
	@XmlElement(name = "Unit")
	private String Unit; // 單位 UOM
	
	@XmlElement(name = "TotGrs")
	private BigDecimal TotGrs; // 總毛重  GROSS_WEIGHT
	
	@XmlElement(name = "CurrencyCode")
	private String CurrencyCode; // 幣別  CURRENCY
	
	@XmlElement(name = "IncoTerm")
	private String IncoTerm; // 貿易條件  TRADE_TERMS
	
	@XmlElement(name = "FobFor")
	private BigDecimal FobFor; // 離岸價格(原幣) FOB_ORI
	
	@XmlElement(name = "FobTwd")
	private BigDecimal FobTwd; // 離岸價格(TWD) FOB
	
	@XmlElement(name = "AppDate")
	private String AppDate; // 報關日期  DECLARATION_DATE
	
	@XmlElement(name = "TotalDutyAmount076")
	private String TotalDutyAmount076; // 稅費合計 TOTAL_AMT
	
	@XmlElement(name = "ShipperEname")
	private String ShipperEname; // 公司別 COMPANY
	
	@XmlElement(name = "Item")
	private XmlDhlExportLine ExportLine;
	
	
	public String getCustCode() {
		
		return CustCode;
	}

	public void setCustCode(String custCode) {
		CustCode = custCode;
	}

	public String getAppNo() {
		return AppNo;
	}

	public void setAppNo(String appNo) {
		this.AppNo = appNo;
	}

	public String getAppType() {
		
		if( AppType == null )
		{
			AppType = "G5";
		}
		
		return AppType;
	}

	public void setAppType(String appType) {
		this.AppType = appType;
	}

	public String getInvNo() {
		return InvNo;
	}

	public void setInvNo(String invNo) {
		this.InvNo = invNo;
	}

	public String getMawb() {
		return Mawb;
	}

	public void setMawb(String mawb) {
		this.Mawb = mawb;
	}

	public String getHawb() {
		return Hawb;
	}

	public void setHawb(String hawb) {
		this.Hawb = hawb;
	}

	public String getShipToName() {
		return ShipToName;
	}

	public void setShipToName(String shipToName) {
		this.ShipToName = shipToName;
	}

	public String getDestCty() {
		return DestCty;
	}

	public void setDestCty(String destCty) {
		this.DestCty = destCty;
	}

	public String getOutStation() {
		return OutStation;
	}

	public void setOutStation(String outStation) {
		this.OutStation = outStation;
	}

	public String getFlightNo() {
		return FlightNo;
	}

	public void setFlightNo(String flightNo) {
		this.FlightNo = flightNo;
	}

	public BigDecimal getTotPce() {
		return TotPce;
	}

	public void setTotPce(BigDecimal TotPce) {
		this.TotPce = TotPce;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		this.Unit = unit;
	}

	public BigDecimal getTotGrs() {
		return TotGrs;
	}

	public void setTotGrs(BigDecimal TotGrs) {
		this.TotGrs = TotGrs;
	}

	public String getCurrencyCode() {
		return CurrencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.CurrencyCode = currencyCode;
	}

	public String getIncoTerm() {
		return IncoTerm;
	}

	public void setIncoTerm(String incoTerm) {
		this.IncoTerm = incoTerm;
	}

	public BigDecimal getFobFor() {
		return FobFor;
	}

	public void setFobFor(BigDecimal FobFor) {
		this.FobFor = FobFor;
	}

	public BigDecimal getFobTwd() {
		return FobTwd;
	}

	public void setFobTwd(BigDecimal FobTwd) {
		this.FobTwd = FobTwd;
	}

	public String getAppDate() {
		return AppDate;
	}

	public void setAppDate(String AppDate) {
		this.AppDate = AppDate;
	}

	public String getTotalDutyAmount076() {
		return TotalDutyAmount076;
	}

	public void setTotalDutyAmount076(String totalDutyAmount076) {
		this.TotalDutyAmount076 = totalDutyAmount076;
	}

	public String getShipperEname() {
		return ShipperEname;
	}

	public void setShipperEname(String shipperEname) {
		this.ShipperEname = shipperEname;
	}
	
	public XmlDhlExportLine getExportLine() {
		return ExportLine;
	}

	public void setExportLine( XmlDhlExportLine exportLine) {
		this.ExportLine = exportLine;
	}


	public String getShipperEaddr1() {
		return ShipperEaddr1;
	}

	public void setShipperEaddr1(String shipperEaddr1) {
		this.ShipperEaddr1 = shipperEaddr1;
	}

	public String getShipperEaddr2() {
		return ShipperEaddr2;
	}

	public void setShipperEaddr2(String shipperEaddr2) {
		this.ShipperEaddr2 = shipperEaddr2;
	}

	public String getShipperEaddr3() {
		return ShipperEaddr3;
	}

	public void setShipperEaddr3(String shipperEaddr3) {
		this.ShipperEaddr3 = shipperEaddr3;
	}

	public String getShipperCtm() {
		return ShipperCtm;
	}

	public void setShipperCtm(String shipperCtm) {
		this.ShipperCtm = shipperCtm;
	}

	public String getConsigneeName() {
		return ConsigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.ConsigneeName = consigneeName;
	}

	public String getConsigneeAddr1() {
		return ConsigneeAddr1;
	}

	public void setConsigneeAddr1(String consigneeAddr1) {
		this.ConsigneeAddr1 = consigneeAddr1;
	}

	public String getConsigneeAddr2() {
		return ConsigneeAddr2;
	}

	public void setConsigneeAddr2(String consigneeAddr2) {
		this.ConsigneeAddr2 = consigneeAddr2;
	}

	public String getConsigneeAddr3() {
		return ConsigneeAddr3;
	}

	public void setConsigneeAddr3(String consigneeAddr3) {
		this.ConsigneeAddr3 = consigneeAddr3;
	}

	public String getOrigCty() {
		return OrigCty;
	}

	public void setOrigCty(String origCty) {
		this.OrigCty = origCty;
	}

	public String getConsigneeCtm() {
		return ConsigneeCtm;
	}

	public void setConsigneeCtm(String consigneeCtm) {
		this.ConsigneeCtm = consigneeCtm;
	}

	public String getShipToAddr() {
		return ShipToAddr;
	}

	public void setShipToAddr(String shipToAddr) {
		this.ShipToAddr = shipToAddr;
	}

	public BigDecimal getFlightFee() {
		return FlightFee;
	}

	public void setFlightFee(BigDecimal FlightFee) {
		if( FlightFee == null )
		{
			FlightFee = new BigDecimal("0");
		}
		
		this.FlightFee = FlightFee;
	}

	public BigDecimal getInsuranceFee() {
		return InsuranceFee;
	}

	public void setInsuranceFee(BigDecimal InsuranceFee) {
		if( InsuranceFee == null )
		{
			InsuranceFee = new BigDecimal("0");
		}
		
		this.InsuranceFee = InsuranceFee;
	}

	public BigDecimal getAddFee() {
		return AddFee;
	}

	public void setAddFee(BigDecimal addFee) {
		if( addFee == null )
		{
			addFee = new BigDecimal("0");
		}
		
		this.AddFee = addFee;
	}

	public BigDecimal getSubFee() {
		return SubFee;
	}

	public void setSubFee(BigDecimal subFee) {
		this.SubFee = subFee;
	}

	public BigDecimal getTotalNW076() {
		return TotalNW076;
	}

	public void setTotalNW076(BigDecimal TotalNW076) {
		this.TotalNW076 = TotalNW076;
	}

	public String getMark() {
		return Mark;
	}

	public void setMark(String mark) {
		this.Mark = mark;
	}

	public String getShipperCer() {
		return ShipperCer;
	}

	public void setShipperCer(String shipperCer) {
		ShipperCer = shipperCer;
	}

	public String getOther() {
		return Other;
	}

	public void setOther(String other) {
		this.Other = other;
	}

	public String getHawbE() {
		
		return HawbE;
	}

	public void setHawbE(String hawbE) {
		HawbE = hawbE;
	}
	
	
}