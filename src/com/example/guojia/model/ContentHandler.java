package com.example.guojia.model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ContentHandler extends DefaultHandler {
	private String nodeName;
	private String city;
	private String district;
	private StringBuilder value;
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		value.append(ch,start,length);
		if(!"".equals(value.toString().trim())){
			if("city".equals(nodeName)){
				city=value.toString().trim();
			}else if("district".equals(nodeName)){
				district=value.toString().trim();
			}
		}
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		value.setLength(0);
	}

	@Override
	public void startDocument() throws SAXException {
		value=new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		nodeName=localName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
