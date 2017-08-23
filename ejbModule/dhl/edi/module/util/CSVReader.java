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
  				new Optional(), //�i�X�f
  	  			new Optional(), //������W��
  	  			new NotNull(), //���渹�X
  	  			new Optional(), //�������O
  	  			new Optional(), //INV_NO
  	  			new Optional(), //�j���渹
  	  			new Optional(), //�p���渹
  	  			new Optional(), //�����W��
  	  			new Optional(), //������O
  	  			new Optional(), //�_�B�f���N�X
  	  			new Optional(), //�Z��
  	  			new Optional(), //�`���
  	  			new Optional(), //���
  	  			new Optional(), //�`��
  	  			new Optional(), //���O
  	  			new Optional(), //�T������
  	  			new Optional(), //��������(���)
  	  			new Optional(), //��������(TWD)
  	  			new Optional(), //�������
  	  			new Optional(), //�i�f���
  	  			new Optional(), //�����
  	  			new Optional(), //ñ���H
  	  			new Optional(), //ñ�����
  	  			new NotNull(), //����
  	  			new Optional(), //�R��Ƹ�
  	  			new Optional(), //���Ƹ�
  	  			new Optional(), //�~�W
  	  			new Optional(), //YB/NB
  	  			new Optional(), //���
  	  			new Optional(), //�ƶq
  	  			new Optional(), //�ƶq���
  	  			new Optional(), //���B(���*�ƶq)
  	  			new Optional(), //���|����(TWD)
  	  			new Optional(), //CCC_CODE
  	  			new Optional(), //�ǵ|��k
  	  			new Optional(), //�i�f�q���|�v
  	  			new Optional(), //���T�O
  	  			new Optional(), //�i�f�|
  	  			new Optional(), //��~�|
  	  			new Optional(), //�f���|
  	  			new Optional(), //�|�O�X�p
  	  			new Optional(), //��~�|��
  	  			new Optional(), //�b��
  	  			new Optional(), //INV_NO of line
  	  			new Optional(), //���q�O
  	   			new Optional(), //�����O
  	  			new Optional()  //�d��
  		};
  		
  		return processors;
  	}
	
	private static CellProcessor[] getExportDataProcessors() {
  		  		
  		final CellProcessor[] processors = new CellProcessor[] { 
  				new Optional(), //�i�X�f
  	  			new Optional(), //������W��
  	  			new NotNull(), //���渹�X
  	  			new Optional(), //�������O
  	  			new NotNull(), //INV_NO
  	  			new Optional(), //�j���渹
  	  			new Optional(), //�p���渹
  	  			new Optional(), //�����W��
  	  			new Optional(), //������O
  	  			new Optional(), //�_�B�f���N�X
  	  			new Optional(), //�Z��
  	  			new Optional(), //�`���
  	  			new Optional(), //���
  	  			new Optional(), //�`��
  	  			new Optional(), //���O
  	  			new Optional(), //�T������
  	  			new Optional(), //��������(���)
  	  			new Optional(), //��������(TWD)
  	  			new Optional(), //�������
  	  			new Optional(), //�X�f���
  	  			new Optional(), //�����
  	  			new Optional(), //ñ���H
  	  			new Optional(), //ñ�����
  	  			new NotNull(), //����
  	  			new Optional(), //�R��Ƹ�
  	  			new Optional(), //���Ƹ�
  	  			new Optional(), //�~�W
  	  			new Optional(), //YB/NB
  	  			new Optional(), //���
  	  			new Optional(), //�ƶq
  	  			new Optional(), //�ƶq���
  	  			new Optional(), //���B(���*�ƶq)
  	  			new Optional(), //���|����(TWD)
  	  			new Optional(), //CCC_CODE
  	  			new Optional(), //�έp�覡
  	  			new Optional(), //���T�O
  	  			new Optional(), //�|�O�X�p
  	  			new Optional(), //�b��
  	  			new Optional(), //INV_NO of line
  	  			new Optional(), //���q�O
  	   			new Optional(), //�����O
  	   			new Optional()  //�d��
  		};
  		
  		return processors;
  	}

}
