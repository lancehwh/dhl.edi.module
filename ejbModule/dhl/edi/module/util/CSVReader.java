package dhl.edi.module.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.quote.AlwaysQuoteMode;

import dhl.edi.module.model.CSVExportData;
import dhl.edi.module.model.CSVImportData;
import dhl.edi.module.model.CSVInvPackAckData;

public class CSVReader {
	
	private static final Logger log = Logger.getLogger(CSVReader.class);
	
	public synchronized static List<CSVImportData> readImportCSV(File file, String encoding) throws IOException{
		
		//the mapping of CSV column and java bean properties
        final String[] mappingOfCSVandBean = {"transactionType","forwarder","entryNo","entryCate","invoiceNum", 
								      		  "mawb", "hawb", "customer", "countryCode", "departurePort",
								      		  "voyNum", "qty", "uom", "grossWeight", "currency",
								      		  "tradeTerms","cifORI","cif","declarationDate","importDate",
								      		  "clearanceDate","signatory","receiptDate","lineNo","buyerPartNo",
								      		  "sellerPartNo","partName","ybNB","unitPrice","qtyOfPart",
								      		  "uomOfPart","amount","dutiableValue", "cccCode", "taxTerms",
								      		  "taxRate","serviceFee", "importTax", "businessTax", "commodityTax",
								      		  "totalAmt", "businessTaxBase","netWeight","invoiceNumOfLine",
								      		  "company","customType","containerNo"
			     							 };

        
        ICsvBeanReader beanReader = null;
		List<CSVImportData> importDatas = new ArrayList<CSVImportData>();
        final CsvPreference csvPreference = new CsvPreference.Builder(CsvPreference.STANDARD_PREFERENCE).useQuoteMode(new AlwaysQuoteMode()).build();
		final CellProcessor[] processors = getImportDataProcessors();
		CSVImportData importData = null;
		InputStreamReader inputStreamReader = null;
		FileInputStream fileInputStream = null;
		
        try {
        	fileInputStream = new FileInputStream(file);
        	inputStreamReader = new InputStreamReader(fileInputStream, encoding);
			beanReader = new CsvBeanReader(inputStreamReader, csvPreference);		
			beanReader.getHeader(false);
			
			while((importData = beanReader.read(CSVImportData.class, mappingOfCSVandBean, processors)) != null){
				
				importDatas.add(importData);
				
			}
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (UnsupportedEncodingException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally{
			
			 if( beanReader != null ) {
                 try {
					beanReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 
			 if(inputStreamReader!= null){
				 inputStreamReader.close();
			 }
			 
			 if(fileInputStream != null){
				 fileInputStream.close();
			 }
		}
        
        return importDatas;
	}
			
	public synchronized static List<CSVExportData> readExportCSV(File file, String encoding) throws IOException{
		
		//the mapping of CSV column and java bean properties
        final String[] mappingOfCSVandBean = {"transactionType","forwarder","entryNo","entryCate","invoiceNum", 
								      		  "mawb", "hawb", "customer", "countryCode", "departurePort",
								      		  "voyNum", "qty", "uom", "grossWeight", "currency",
								      		  "tradeTerms","fobORI","fob","declarationDate","exportDate",
								      		  "clearanceDate","signatory","receiptDate","lineNo","buyerPartNo",
								      		  "sellerPartNo","partName","ybNB","unitPrice","qtyOfPart",
								      		  "uomOfPart","amount","dutiableValue", "cccCode", "statMethod",
								      		  "serviceFee","totalAmt","netWeight","invoiceNumOfLine","company",
								      		  "customType","containerNo"
											 };
        
        ICsvBeanReader beanReader = null;
		List<CSVExportData> exportDatas = new ArrayList<CSVExportData>();
        final CsvPreference csvPreference = new CsvPreference.Builder(CsvPreference.STANDARD_PREFERENCE).useQuoteMode(new AlwaysQuoteMode()).build();
		final CellProcessor[] processors = getExportDataProcessors();
		CSVExportData exportData = null;
		InputStreamReader inputStreamReader = null;
		FileInputStream fileInputStream = null;
		
        try {
        	fileInputStream = new FileInputStream(file);
        	inputStreamReader = new InputStreamReader(fileInputStream, encoding);
			beanReader = new CsvBeanReader(inputStreamReader, csvPreference);		
			beanReader.getHeader(false);
			
			while((exportData = beanReader.read(CSVExportData.class, mappingOfCSVandBean, processors)) != null){
				
				exportDatas.add(exportData);
				
			}
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (UnsupportedEncodingException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally{
			
			 if( beanReader != null ) {
                 try {
					beanReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 
			 if(inputStreamReader!= null){
				 inputStreamReader.close();
			 }
			 
			 if(fileInputStream != null){
				 fileInputStream.close();
			 }
		}
        

		return exportDatas;
	}
	
	public static CSVInvPackAckData readInvPackAckCsv(File file, String encoding) throws IOException{
		
		//the mapping of CSV column and java bean properties
        final String[] mappingOfCSVandBean = {"shipNo"};
        
        final CsvPreference csvPreference = new CsvPreference.Builder(CsvPreference.STANDARD_PREFERENCE).useQuoteMode(new AlwaysQuoteMode()).build();
		final CellProcessor[] processors = getInvPackAckProcessors();
		InputStreamReader inputStreamReader = null;
		FileInputStream fileInputStream = null;
		ICsvBeanReader beanReader = null;
		CSVInvPackAckData invPackAckData = null;
		
		try {
			fileInputStream = new FileInputStream(file);
	    	inputStreamReader = new InputStreamReader(fileInputStream, encoding);
			beanReader = new CsvBeanReader(inputStreamReader, csvPreference);		
			beanReader.getHeader(false);
			
			invPackAckData = beanReader.read(CSVInvPackAckData.class, mappingOfCSVandBean, processors);
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (UnsupportedEncodingException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally{
			
			 if( beanReader != null ) {
                try {
					beanReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 
			 if(inputStreamReader!= null){
				 try {
					inputStreamReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 
			 if(fileInputStream != null){
				 try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}

		return invPackAckData;
	}
	
	private static CellProcessor[] getInvPackAckProcessors(){
		
		final CellProcessor[] processors = new CellProcessor[] {
				new NotNull() //invoice number
		};
		
		return processors;
	}
	
	private static CellProcessor[] getImportDataProcessors() {
	  		
  		final CellProcessor[] processors = new CellProcessor[] { 
  				new Optional(), //進出口
  	  			new Optional(), //報關行名稱
  	  			new NotNull(), //報單號碼
  	  			new Optional(), //報單類別
  	  			new Optional(), //INV_NO
  	  			new Optional(), //大提單號
  	  			new Optional(), //小提單號
  	  			new Optional(), //交易方名稱
  	  			new Optional(), //交易方國別
  	  			new Optional(), //起運口岸代碼
  	  			new Optional(), //班次
  	  			new Optional(), //總件數
  	  			new Optional(), //單位
  	  			new Optional(), //總毛重
  	  			new Optional(), //幣別
  	  			new Optional(), //貿易條件
  	  			new Optional(), //離岸價格(原幣)
  	  			new Optional(), //離岸價格(TWD)
  	  			new Optional(), //報關日期
  	  			new Optional(), //進口日期
  	  			new Optional(), //放行日期
  	  			new Optional(), //簽收人
  	  			new Optional(), //簽收日期
  	  			new NotNull(), //項次
  	  			new Optional(), //買方料號
  	  			new Optional(), //賣方料號
  	  			new Optional(), //品名
  	  			new Optional(), //YB/NB
  	  			new Optional(), //單價
  	  			new Optional(), //數量
  	  			new Optional(), //數量單位
  	  			new Optional(), //金額(單價*數量)
  	  			new Optional(), //完稅價格(TWD)
  	  			new Optional(), //CCC_CODE
  	  			new Optional(), //納稅辦法
  	  			new Optional(), //進口從價稅率
  	  			new Optional(), //推貿費
  	  			new Optional(), //進口稅
  	  			new Optional(), //營業稅
  	  			new Optional(), //貨物稅
  	  			new Optional(), //稅費合計
  	  			new Optional(), //營業稅基
  	  			new Optional(), //淨重
  	  			new Optional(), //INV_NO of line
  	  			new Optional(), //公司別
  	   			new Optional(), //海關別
  	  			new Optional()  //櫃號
  		};
  		
  		return processors;
  	}
	
	private static CellProcessor[] getExportDataProcessors() {
  		  		
  		final CellProcessor[] processors = new CellProcessor[] { 
  				new Optional(), //進出口
  	  			new Optional(), //報關行名稱
  	  			new NotNull(), //報單號碼
  	  			new Optional(), //報單類別
  	  			new NotNull(), //INV_NO
  	  			new Optional(), //大提單號
  	  			new Optional(), //小提單號
  	  			new Optional(), //交易方名稱
  	  			new Optional(), //交易方國別
  	  			new Optional(), //起運口岸代碼
  	  			new Optional(), //班次
  	  			new Optional(), //總件數
  	  			new Optional(), //單位
  	  			new Optional(), //總毛重
  	  			new Optional(), //幣別
  	  			new Optional(), //貿易條件
  	  			new Optional(), //離岸價格(原幣)
  	  			new Optional(), //離岸價格(TWD)
  	  			new Optional(), //報關日期
  	  			new Optional(), //出口日期
  	  			new Optional(), //放行日期
  	  			new Optional(), //簽收人
  	  			new Optional(), //簽收日期
  	  			new NotNull(), //項次
  	  			new Optional(), //買方料號
  	  			new Optional(), //賣方料號
  	  			new Optional(), //品名
  	  			new Optional(), //YB/NB
  	  			new Optional(), //單價
  	  			new Optional(), //數量
  	  			new Optional(), //數量單位
  	  			new Optional(), //金額(單價*數量)
  	  			new Optional(), //完稅價格(TWD)
  	  			new Optional(), //CCC_CODE
  	  			new Optional(), //統計方式
  	  			new Optional(), //推貿費
  	  			new Optional(), //稅費合計
  	  			new Optional(), //淨重
  	  			new Optional(), //INV_NO of line
  	  			new Optional(), //公司別
  	   			new Optional(), //海關別
  	   			new Optional()  //櫃號
  		};
  		
  		return processors;
  	}

}
