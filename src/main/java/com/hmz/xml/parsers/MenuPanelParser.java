package com.hmz.xml.parsers;

import com.hmz.gui.MenuPanel;
import com.hmz.gui.listeners.PopMessage;
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

public class MenuPanelParser extends DefaultHandler {

	public static final String DEFAULT_SOURCE = "resources/xml/menuPanel.xml";
	public static final String DEFAULT_SCHEMA = "resources/xml/schemas/xsd/menu_panel.xsd";
	
	private SAXParserFactory factory;
	private String source;
	private String tag = null;
	
	// GUI COMPONENTS
	private MenuPanel menu;
	private String title;
			
	public MenuPanelParser() {
		this(DEFAULT_SOURCE);
	}
	
	public MenuPanelParser(String source) {
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
		
		if("menu-panel".equals(tag)) {
			title = attlist.getValue("title");
			menu = new MenuPanel(title);
		}
		
		if("title".equals(tag)) {
			String value = attlist.getValue("value");
			menu.addTitles(value);
			menu.addTitleActionListener(value, new PopMessage(null, value));
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
	
	public MenuPanel getMenu() {
		return menu;
	}

}
