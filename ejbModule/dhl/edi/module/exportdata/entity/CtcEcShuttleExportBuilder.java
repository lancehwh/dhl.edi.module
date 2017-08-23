package dhl.edi.module.exportdata.entity;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dhl.edi.module.model.CSVExportData;

public class CtcEcShuttleExportBuilder {

	public CtcEcShuttleExportH build(List<CSVExportData> csvExportDatas) throws ParseException{
		
		CtcEcShuttleExportH ctcEcShuttleExportH = new CtcEcShuttleExportH();
		
		//excel前面幾個欄位的值都相同(從進出口欄位至簽收日期)，取第1筆當作header
		CSVExportData temp = csvExportDatas.get(0);
		
		ctcEcShuttleExportH.setEntryNo(temp.getEntryNo());
		ctcEcShuttleExportH.setTransactionType(temp.getTransactionType());
		ctcEcShuttleExportH.setFowarder(temp.getForwarder());
		ctcEcShuttleExportH.setEntryCate(temp.getEntryCate());
		ctcEcShuttleExportH.setInvoiceNum(temp.getInvoiceNum());
		ctcEcShuttleExportH.setMawb(temp.getMawb());
		ctcEcShuttleExportH.setHawb(temp.getHawb());
		ctcEcShuttleExportH.setCustomer(temp.getCustomer());
		ctcEcShuttleExportH.setCountryCode(temp.getCountryCode());
		ctcEcShuttleExportH.setDeparturePort(temp.getDeparturePort());
		ctcEcShuttleExportH.setVoyNum(temp.getVoyNum());
		ctcEcShuttleExportH.setQty(new BigDecimal(temp.getQty()));
		ctcEcShuttleExportH.setUom(temp.getUom());
		ctcEcShuttleExportH.setGrossWeight(new BigDecimal(temp.getGrossWeight()));
		ctcEcShuttleExportH.setCurrency(temp.getCurrency());
		ctcEcShuttleExportH.setTradeTerms(temp.getTradeTerms());
		ctcEcShuttleExportH.setFob(new BigDecimal(temp.getFob()));
		ctcEcShuttleExportH.setServiceFee( new BigDecimal(temp.getServiceFee()) );
		ctcEcShuttleExportH.setTotalAmt( new BigDecimal(temp.getTotalAmt()) );
		ctcEcShuttleExportH.setCompany(temp.getCompany());
		ctcEcShuttleExportH.setCustomType(temp.getCustomType());
		ctcEcShuttleExportH.setContainerNo(temp.getContainerNo());
			
		Date delarationDate = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		
		delarationDate = (temp.getDeclarationDate()==null?null:dateFormat.parse(temp.getDeclarationDate()));
		ctcEcShuttleExportH.setDeclarationDate(delarationDate);
		
		
		Date exportDate = (temp.getExportDate()==null?null:dateFormat.parse(temp.getExportDate()));
		ctcEcShuttleExportH.setExportDate(exportDate);
				
		ctcEcShuttleExportH.setFobOri(new BigDecimal(temp.getFobORI()));
		
		Date clearanceDate = (temp.getClearanceDate()==null?null:dateFormat.parse(temp.getClearanceDate()));
		ctcEcShuttleExportH.setClearanceDate(clearanceDate);
		ctcEcShuttleExportH.setSignatory(temp.getSignatory());
		
		Date receiptDate = (temp.getReceiptDate()==null?null:dateFormat.parse(temp.getReceiptDate()));
		ctcEcShuttleExportH.setReceiptDate(receiptDate);
		
		ctcEcShuttleExportH.setCtcEcShuttleExportLs(buildLines(csvExportDatas, ctcEcShuttleExportH));
		
		return ctcEcShuttleExportH;
	}
	
	private List<CtcEcShuttleExportL> buildLines(List<CSVExportData> csvExportDatas, CtcEcShuttleExportH exportHeader){
		
		List<CtcEcShuttleExportL> ctcEcShuttleExportLs = new ArrayList<CtcEcShuttleExportL>();
		CtcEcShuttleExportL ctcEcShuttleExportL = null;
		
		for(CSVExportData temp:csvExportDatas){
			
			ctcEcShuttleExportL = new CtcEcShuttleExportL();
			ctcEcShuttleExportL.setLineNo(Long.parseLong(temp.getLineNo()));
			ctcEcShuttleExportL.setBuyerPartNo(temp.getBuyerPartNo());
			ctcEcShuttleExportL.setSellerPartNo(temp.getSellerPartNo());
			ctcEcShuttleExportL.setPartName(temp.getPartName());
			ctcEcShuttleExportL.setYbNb(temp.getYbNB());
			ctcEcShuttleExportL.setUnitPrice( new BigDecimal(temp.getUnitPrice()) );
			ctcEcShuttleExportL.setQty( new BigDecimal(temp.getQtyOfPart()) );
			ctcEcShuttleExportL.setUom(temp.getUomOfPart());
			ctcEcShuttleExportL.setAmount( new BigDecimal(temp.getAmount()) );
			ctcEcShuttleExportL.setDutiableValue( new BigDecimal(temp.getDutiableValue()) );
			ctcEcShuttleExportL.setCccCode(temp.getCccCode());
			ctcEcShuttleExportL.setStatMethod(temp.getStatMethod());
			ctcEcShuttleExportL.setExportHeader(exportHeader);
			ctcEcShuttleExportL.setNetWeight(new BigDecimal(temp.getNetWeight()));
			ctcEcShuttleExportL.setInvNo(temp.getInvoiceNumOfLine());
			
			ctcEcShuttleExportLs.add(ctcEcShuttleExportL);
		}
		
		return ctcEcShuttleExportLs;
	}
}
