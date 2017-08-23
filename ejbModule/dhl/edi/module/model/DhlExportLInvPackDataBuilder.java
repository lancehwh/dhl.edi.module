package dhl.edi.module.model;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dhl.edi.module.invpack.entity.CtcEcShuttleInvoicePack;



public class DhlExportLInvPackDataBuilder {
	
	
	private static final Logger logger = Logger.getLogger(DhlExportLInvPackDataBuilder.class);
//	public XmlDhlExportFormat buildXmlInvPackData(List<CtcEcShuttleInvoicePack> ctcEcDhlInvoicePacks){
//		
//		XmlDhlExportFormat XmlDhlExportData = new XmlDhlExportFormat();
//		XmlDhlExportLine DhlExportLinetemp = null;
//		ArrayList<XmlDhlExportLine> XmlDhlExportLine = new ArrayList<XmlDhlExportLine>();
//		XmlDhlExportHeader XmlDhlExportDataHeader = buildXmlHeaderInvPackData(ctcEcDhlInvoicePacks.get(0));
//		
//		for( CtcEcShuttleInvoicePack  ctcEcDhlInvoicePack : ctcEcDhlInvoicePacks )
//		{
//			DhlExportLinetemp = new XmlDhlExportLine();
//			DhlExportLinetemp.setInvNo(ctcEcDhlInvoicePack.getInvno());
//			DhlExportLinetemp.setHawb(ctcEcDhlInvoicePack.getHawb());
//			DhlExportLinetemp.setItemForAmt(ctcEcDhlInvoicePack.getTotal());
//			DhlExportLinetemp.setInvItemNo(ctcEcDhlInvoicePack.getItem());
//			DhlExportLinetemp.setItemSoNo(ctcEcDhlInvoicePack.getSucm());
//			DhlExportLinetemp.setItemPoNo(ctcEcDhlInvoicePack.getBycm());
//			DhlExportLinetemp.setItemMark(ctcEcDhlInvoicePack.getBrand());
//			DhlExportLinetemp.setItemName( StringDataTransfer(ctcEcDhlInvoicePack.getDescp(), 25 ));
//			DhlExportLinetemp.setItemUnitPrice(ctcEcDhlInvoicePack.getPrice());
//			DhlExportLinetemp.setItemQTY(ctcEcDhlInvoicePack.getQty());
// 			DhlExportLinetemp.setItemUnit(ctcEcDhlInvoicePack.getQtyu());
//			DhlExportLinetemp.setItemOffCD1(ctcEcDhlInvoicePack.getBond());
//			DhlExportLinetemp.setItemNetWgt(ctcEcDhlInvoicePack.getNw());
//			DhlExportLinetemp.setItemIbAppNo(ctcEcDhlInvoicePack.getOrDecl());
//			DhlExportLinetemp.setItemCCCCode(ctcEcDhlInvoicePack.getTaxRuleCode());
//			DhlExportLinetemp.setItemCurrencyCode(ctcEcDhlInvoicePack.getCurr());
//			DhlExportLinetemp.setItemForAmt(DhlExportLinetemp.getItemUnitPrice().multiply(DhlExportLinetemp.getItemQTY()));
//			XmlDhlExportLine.add(DhlExportLinetemp);
//			
//		}
//		
//		XmlDhlExportDataHeader.setExportLine(XmlDhlExportLine);		
//		XmlDhlExportData.setXmlDhlExportHeader(XmlDhlExportDataHeader);
//		
//		
//		return XmlDhlExportData;
//	}
	
	public XmlDhlExportFormat buildXmlInvPackData(List<CtcEcShuttleInvoicePack> ctcEcDhlInvoicePacks, String ShipperCer ){
		
		XmlDhlExportFormat XmlDhlExportData = new XmlDhlExportFormat();
		ArrayList<XmlDhlExportHeader> ExportHeaderList =  new ArrayList<XmlDhlExportHeader>();
		
		for( CtcEcShuttleInvoicePack  ctcEcDhlInvoicePack : ctcEcDhlInvoicePacks )
		{
			XmlDhlExportHeader XmlDhlExportDataHeader = buildXmlHeaderInvPackData(ctcEcDhlInvoicePack);
			//set ²Î¤@½s¸¹
			XmlDhlExportDataHeader.setShipperCer(ShipperCer);
			
			XmlDhlExportLine DhlExportLinetemp = null;
			
			DhlExportLinetemp = new XmlDhlExportLine();
			DhlExportLinetemp.setInvNo(ctcEcDhlInvoicePack.getInvno());
			DhlExportLinetemp.setHawb(ctcEcDhlInvoicePack.getHawb());
			DhlExportLinetemp.setItemForAmt(ctcEcDhlInvoicePack.getTotal());
			DhlExportLinetemp.setInvItemNo(ctcEcDhlInvoicePack.getItem());
			DhlExportLinetemp.setItemSoNo(ctcEcDhlInvoicePack.getSucm());
			DhlExportLinetemp.setItemPoNo(ctcEcDhlInvoicePack.getBycm());
			DhlExportLinetemp.setItemMark(ctcEcDhlInvoicePack.getBrand());
			DhlExportLinetemp.setItemName( StringDataTransfer(ctcEcDhlInvoicePack.getDescp(), 25 ));
			DhlExportLinetemp.setItemUnitPrice(ctcEcDhlInvoicePack.getPrice());
			DhlExportLinetemp.setItemQTY(ctcEcDhlInvoicePack.getQty());
 			DhlExportLinetemp.setItemUnit(ctcEcDhlInvoicePack.getQtyu());
			DhlExportLinetemp.setItemOffCD1(ctcEcDhlInvoicePack.getBond());
			DhlExportLinetemp.setItemStcWay(ctcEcDhlInvoicePack.getDhlStatistics());
			DhlExportLinetemp.setItemNetWgt(ctcEcDhlInvoicePack.getNw());
			DhlExportLinetemp.setItemIbAppNo(ctcEcDhlInvoicePack.getOrDecl());
			DhlExportLinetemp.setItemCCCCode(ctcEcDhlInvoicePack.getTaxRuleCode());
			DhlExportLinetemp.setItemCurrencyCode(ctcEcDhlInvoicePack.getCurr());
			DhlExportLinetemp.setItemForAmt(DhlExportLinetemp.getItemUnitPrice().multiply(DhlExportLinetemp.getItemQTY()));
			XmlDhlExportDataHeader.setExportLine(DhlExportLinetemp);		
			ExportHeaderList.add(XmlDhlExportDataHeader);
			
		}
		
		
		XmlDhlExportData.setXmlDhlExportHeader(ExportHeaderList);
		
		return XmlDhlExportData;
	}
	
	private XmlDhlExportHeader buildXmlHeaderInvPackData(CtcEcShuttleInvoicePack CtcEcShuttleInvoicePack )
	{
		SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yy");
		XmlDhlExportHeader XmlDhlExportDataHeader = new XmlDhlExportHeader();
		XmlDhlExportDataHeader.setInvNo(CtcEcShuttleInvoicePack.getInvno());
		XmlDhlExportDataHeader.setAppType(CtcEcShuttleInvoicePack.getDeclType());
		XmlDhlExportDataHeader.setShipperEname(CtcEcShuttleInvoicePack.getTdname());
		XmlDhlExportDataHeader.setShipperEaddr1(CtcEcShuttleInvoicePack.getTdaddr());
		XmlDhlExportDataHeader.setShipperCtm(CtcEcShuttleInvoicePack.getTdcode());
		XmlDhlExportDataHeader.setConsigneeName(CtcEcShuttleInvoicePack.getBsname());
		XmlDhlExportDataHeader.setConsigneeAddr1(CtcEcShuttleInvoicePack.getBsaddr());
		XmlDhlExportDataHeader.setOrigCty(CtcEcShuttleInvoicePack.getBscountry());
		XmlDhlExportDataHeader.setConsigneeCtm(CtcEcShuttleInvoicePack.getBscode());
		XmlDhlExportDataHeader.setShipToName(CtcEcShuttleInvoicePack.getStname());
		XmlDhlExportDataHeader.setShipToAddr(CtcEcShuttleInvoicePack.getStaddr());
		XmlDhlExportDataHeader.setDestCty(CtcEcShuttleInvoicePack.getStcountry());
		XmlDhlExportDataHeader.setMawb(CtcEcShuttleInvoicePack.getMawb());
		XmlDhlExportDataHeader.setHawb(CtcEcShuttleInvoicePack.getHawb());
		XmlDhlExportDataHeader.setIncoTerm(CtcEcShuttleInvoicePack.getTerms());
		XmlDhlExportDataHeader.setCurrencyCode(CtcEcShuttleInvoicePack.getCurr());
		XmlDhlExportDataHeader.setFobFor(CtcEcShuttleInvoicePack.getPrice().multiply(CtcEcShuttleInvoicePack.getQty()));
		XmlDhlExportDataHeader.setFlightFee(CtcEcShuttleInvoicePack.getFrtfee());
		XmlDhlExportDataHeader.setInsuranceFee(CtcEcShuttleInvoicePack.getIsfee());
		XmlDhlExportDataHeader.setAddFee(CtcEcShuttleInvoicePack.getAddfee());
		XmlDhlExportDataHeader.setSubFee(CtcEcShuttleInvoicePack.getSubfee());
		XmlDhlExportDataHeader.setTotPce(CtcEcShuttleInvoicePack.getApkg());
		XmlDhlExportDataHeader.setUnit(CtcEcShuttleInvoicePack.getPkgu());
		XmlDhlExportDataHeader.setTotGrs(CtcEcShuttleInvoicePack.getTllgw());
		XmlDhlExportDataHeader.setTotalNW076(CtcEcShuttleInvoicePack.getTllnw());
		XmlDhlExportDataHeader.setFlightNo(CtcEcShuttleInvoicePack.getFlno());
		if( CtcEcShuttleInvoicePack.getIeDate() != null )
		{
			XmlDhlExportDataHeader.setAppDate(sdFormat.format(CtcEcShuttleInvoicePack.getIeDate()));
		}
		XmlDhlExportDataHeader.setMark( StringDataTransfer(CtcEcShuttleInvoicePack.getRemark(), 35 ));
		XmlDhlExportDataHeader.setOther( StringDataTransfer(CtcEcShuttleInvoicePack.getOtherDeclaration() , 35));
		
		
		return XmlDhlExportDataHeader;
	}
	
//	private String StringDataTransfer(String Data, int s ) 
//	{
//		String result =null;
//		
//		if( Data != null )
//		{
//			StringBuffer sb = new StringBuffer();  
//			String[] DataList = Data.split("\r\n|\r|\n|\n\r");
//			for( String temp : DataList )
//			{
//				String[] tempList = temp.split(" ");
//				
//				if( temp.getBytes().length <= s )
//				{
//					sb.append(temp);
//					for( int i = ( temp.getBytes().length % s ) ; i < s; i++ )
//					{
//						sb.append(" ");
//					}
//				}
//				else
//				{
//					boolean isChinese;
//					int count = 0;
//					int ByteLength = temp.getBytes().length;
//			         
//			        for(int i=0; i<temp.length();i++){
//			        	
//			        	isChinese = isChineseCharacter(temp.charAt(i));
//			        	if(count == s)
//			        	{
//			        		count = 0;
//			        	}
//			        	
//			             if( isChinese )
//			             {   
//			                 if( (count+2) > s )
//			                 {
//			                	 sb.append(" ");
//			                	 ByteLength++;
//			                	 count = 0;
//			                 }
//			                 
//			                 count = count+2;
//			             }
//			             else
//			             {
//			            	 
//			            	 count++;
//			             }
//	
//			             sb.append(temp.charAt(i));
//			             
//			             System.out.println(temp.charAt(i)+" : "+isChinese);
//			        }
//			        
//			        for( int i = (ByteLength % s ) ; i < s; i++ )
//					{
//						sb.append(" ");
//					}
//				}
//				
//			}
//			
//			result = sb.toString();
//		}
//		return result;
//	}
	
	
	private String StringDataTransfer(String Data, int s ) 
	{
		String result =null;
		
		if( Data != null )
		{
			StringBuffer sb = new StringBuffer();  
			String[] DataList = Data.split("\r\n|\r|\n|\n\r");
			for( String temp : DataList )
			{
					
				String[] tempList = temp.split(" ");
				
				int LineCount = 0;
				
				for( String word : tempList )
				{
					
					if( LineCount != 0 && LineCount < s )
					{
						sb.append(" ");
						LineCount++;
					}
					
					if( LineCount == s )
					{
						LineCount = 0;
					}
					
					
					if( word.getBytes().length <= s )
					{
						
						if( ! word.startsWith("BOM:") )
						{
							if( LineCount + word.getBytes().length <= s )
							{
								sb.append(word);
								LineCount = LineCount + word.getBytes().length ;
							}
							else if(  LineCount + word.getBytes().length > s )
							{
								for( int i = ( LineCount % s ) ; i < s; i++ )
								{
									sb.append(" ");
								}
								
								LineCount =  word.getBytes().length;
								sb.append(word);
							}
						}
						else
						{
							for( int i = ( LineCount % s ) ; i < s; i++ )
							{
								sb.append(" ");
							}
							
							LineCount =  word.getBytes().length;
							sb.append(word);
						}
					}
					else
					{
						boolean isChinese;
						int count = 0;
//						int ByteLength = word.getBytes().length;
				         
				        for(int i=0; i<word.length();i++){
				        	
				        	isChinese = isChineseCharacter(word.charAt(i));
				        	if(LineCount == s)
				        	{
				        		LineCount = 0;
				        	}
				        	
				             if( isChinese )
				             {   
				                 if( (LineCount+2) > s )
				                 {
				                	 sb.append(" ");
//				                	 ByteLength++;
				                	 LineCount = 0;
				                 }
				                 
				                 LineCount = LineCount+2;
				             }
				             else
				             {
				            	 
				            	 LineCount++;
				             }
		
				             sb.append(word.charAt(i));
				             
				             System.out.println(word.charAt(i)+" : "+isChinese);
				        }    
					}
				
				}
				
				for( int i = ( LineCount % s ) ; i < s; i++ )
				{
					sb.append(" ");
				}
			}
			
			result = sb.toString();
		}
			
		return result;
	}
	

	private boolean isChineseCharacter (char c){  
        return (19968<=(int)c)&&((int)c<=171941);
    }

}
