package dhl.edi.module.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "XOCD")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlDhlExportFormat {
	
	@XmlElement(name = "DeclarationForm")
	private ArrayList<XmlDhlExportHeader> ExportHeader;
	

	public  ArrayList<XmlDhlExportHeader> getXmlDhlExportHeader() {
		return ExportHeader;
	}

	public void setXmlDhlExportHeader( ArrayList<XmlDhlExportHeader> ExportHeader) {
		this.ExportHeader = ExportHeader;
	}

	
}