package dhl.edi.module.model;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dhl.edi.module.importdata.entity.CtcEcDhlImportH;
import dhl.edi.module.importdata.entity.CtcEcDhlImportL;
import dhl.edi.module.model.XCCA.DeclarationForm;
import dhl.edi.module.model.XCCA.DeclarationForm.Item;


public class DhlImportDataBuilder {
	
	private static final Logger logger = Logger.getLogger(DhlImportDataBuilder.class);
	
	public XCCA buildXmlImportData( CtcEcDhlImportH DhlImportHeader) throws Exception
	{
		XCCA XmlImportData = new XCCA();
		XmlImportData.declarationForm = new ArrayList<XCCA.DeclarationForm>();
		
		List<CtcEcDhlImportL> CtcEcDhlImportLinelist = DhlImportHeader.getCtcEcDhlImportLineList();
		XCCA.DeclarationForm declarationFormTemp = new DeclarationForm();
		declarationFormTemp.item = new ArrayList<XCCA.DeclarationForm.Item>();
		
		for( CtcEcDhlImportL temp : CtcEcDhlImportLinelist )
		{

			XCCA.DeclarationForm.Item ItemTemp = new Item();
			declarationFormTemp.setHawb(DhlImportHeader.getHawb());
			declarationFormTemp.setAppType(DhlImportHeader.getAppType());
			declarationFormTemp.setConsigneeID(DhlImportHeader.getConsigneeId());
			declarationFormTemp.setConsigneeName(DhlImportHeader.getConsigneeName());
			declarationFormTemp.setShipperName(DhlImportHeader.getShipperName());
			declarationFormTemp.setTradeType(DhlImportHeader.getTradeType());
			
			
			ItemTemp.setItemNo(temp.getItemNo().toString());
			ItemTemp.setTradeType(DhlImportHeader.getTradeType());
			ItemTemp.setItemUnitPrice(temp.getItemUniprice().toString());
			ItemTemp.setItemCurrencyCode(temp.getItemCurrency());
			ItemTemp.setItemQTY(temp.getItemQty().toString());
			ItemTemp.setItemUnit(temp.getItemUnit());
			
			ItemTemp = ItemDescConverter( ItemTemp,  temp.getItemDesc() );
			declarationFormTemp.item.add(ItemTemp);		
			
		}
		
		XmlImportData.declarationForm.add(declarationFormTemp);
		
		
		
		return XmlImportData;
	}
	
	
	private Item ItemDescConverter( Item ItemTemp,  String Descript  )
	{
		Item ConverterResult = ItemTemp;
		

		String [] DescSpilit = new String [12] ;
		
		int length = 0;
		int count = 0;
		
		
		for( int i = 0; i < Descript.length(); i++ )
		{
			
			
			
			if( isChineseCharacter (Descript.charAt(i)) )
			{
				if( length + 2  > 30 )
				{
					count++;
					length = 0;
				}
				
				length = length + 2;
			}
			else
			{
				if( length + 1  > 30 )
				{
					count++;
					length = 0;
				}
				
				length++;
			}
			
			if(count < 12)
			{
				if( DescSpilit[count] == null )
				{
					DescSpilit[count] = ""+ Descript.charAt(i);
				}
				else
				{
					DescSpilit[count] = DescSpilit[count]+ Descript.charAt(i);
				}
			}
			
			
		}
		
		
		ConverterResult.setItemDesc2(DescSpilit[0]);
		ConverterResult.setItemDesc3(DescSpilit[1]);
		ConverterResult.setItemName1(DescSpilit[2]);
		ConverterResult.setItemName2(DescSpilit[3]);
		ConverterResult.setItemName3(DescSpilit[4]);
		ConverterResult.setItemName4(DescSpilit[5]);
		ConverterResult.setItemName5(DescSpilit[6]);
		ConverterResult.setItemName6(DescSpilit[7]);
		ConverterResult.setItemName7(DescSpilit[8]);
		ConverterResult.setItemName8(DescSpilit[9]);
		ConverterResult.setItemName9(DescSpilit[10]);
		ConverterResult.setItemName10(DescSpilit[11]);

				

		
		
		return ConverterResult;
		
	}
	
	public static boolean isChineseCharacter (char c){  
        return (19968<=(int)c)&&((int)c<=171941);
    }
	

}
