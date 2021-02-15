package com.hmz.xml.parsers;


import com.hmz.gui.SetupForm;
import com.hmz.operations.Converter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class SetupFromParser extends DefaultHandler {

	public static final String DEFAULT_SOURCE = "resources/xml/miniForm.xml";
	public static final String DEFAULT_SCHEMA = "resources/xml/schemas/xsd/mini_form.xsd";
	
	private SAXParserFactory factory;
	private String source;
	private String tag = null;
	
	// GUI COMPONENTS
	private SetupForm form;
	private String title;
	private int labelwidth;
	private boolean buttons;
		
	private static int fcounter = 0;
	
	public SetupFromParser() {
		this(DEFAULT_SOURCE);
	}
	
	public SetupFromParser(String source) {
		this.source = source;
		factory = SAXParserFactory.newInstance();
		try {
			setSchema();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// PARSING
	
	public void parse() {
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(source, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void parse(String source) {
		this.source = source;
		parse();
	}
	
	// MANAGING
	
	public void startElement(String uri, String lname, String qname, Attributes attlist) throws SAXException {
		tag = qname;
		
		if("mini-form".equals(tag)) {
			title = attlist.getValue("title");
			labelwidth = Converter.toInteger(attlist.getValue("label-width"));
			buttons = Converter.toBoolean(attlist.getValue("buttons"));
			
			form = new SetupForm(title, labelwidth, buttons);
			form.setButtons(buttons);
		}
		
		if("field".equals(tag)) {
			String label = attlist.getValue("label");
			form.addFields(label);
		}
		
	}
	
	public void characters(char[] data, int start, int end) throws SAXException {
		String val = new String(data, start, end).trim();
		
		if("field".equals(tag)) {
			form.setText(val, fcounter++);
		}
		
	}
	
	public void endElement(String uri, String lname, String qname) throws SAXException {
		tag = null;
	}
	
	public void error(SAXParseException e) throws SAXException {
		e.printStackTrace();
	}
	
	// GETTERS AND SETTERS
	
	public String getSource() {
		return source;
	}

	public void setSchema() throws Exception {
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(new File(DEFAULT_SCHEMA));
		factory.setSchema(schema);
	}
	
	public SetupForm getForm() {
		return form;
	}


}
