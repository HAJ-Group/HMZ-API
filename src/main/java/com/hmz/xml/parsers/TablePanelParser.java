package com.hmz.xml.parsers;

import com.hmz.collections.Vecrray;
import com.hmz.gui.TablePanel;
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

public class TablePanelParser extends DefaultHandler {

	public static final String DEFAULT_SOURCE = "resources/xml/table.xml";
	public static final String DEFAULT_SCHEMA = "resources/xml/schemas/xsd/table.xsd";
	
	private SAXParserFactory factory;
	private String source;
	private String tag = null;
	private Vecrray<Object> row;
	
	// GUI COMPONENTS
	private TablePanel table;
			
	public TablePanelParser() {
		this(DEFAULT_SOURCE);
	}
	
	public TablePanelParser(String source) {
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
		
		if("table".equals(tag)) {
			table = new TablePanel();
		}
		
		if("header".equals(tag)) {
			row = new Vecrray<>();
		}
		
		if("row".equals(tag)) {
			row = new Vecrray<>();
		}
		
	}
	
	public void characters(char[] data, int start, int end) throws SAXException {
		String val = new String(data, start, end).trim();
		
		if("column".equals(tag)) {
			row.add(val);
		}
	}
	
	public void endElement(String uri, String lname, String qname) throws SAXException {
		tag = null;
		if("header".equals(qname)) {
			table.setHeaders(row.toArray());
		}
		
		if("row".equals(qname)) {
			table.addRow(row.toArray());
		}
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
	
	public TablePanel getTable() {
		return table;
	}
	
}
