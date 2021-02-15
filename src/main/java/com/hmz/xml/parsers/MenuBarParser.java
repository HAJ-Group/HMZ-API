package com.hmz.xml.parsers;


import com.hmz.gui.Menu;
import com.hmz.gui.MenuBar;
import com.hmz.gui.listeners.Exiter;
import com.hmz.gui.listeners.PopMessage;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;


public class MenuBarParser extends DefaultHandler {

	public static final String DEFAULT_SOURCE = "resources/xml/menubar.xml";
	public static final String DEFAULT_SCHEMA = "resources/xml/schemas/xsd/menubar.xsd";
	
	private SAXParserFactory factory;
	private String source;
	private String tag = null;
	
	// GUI COMPONENTS
	private MenuBar menubar;
	private String iconsPath = null;
	
	private Menu menu;
	private String title = null;
	
	private JMenuItem item;
	private String label = null;
	private String icon = null;
	private String action = null;
	
	public MenuBarParser() {
		this(DEFAULT_SOURCE);
	}
	
	public MenuBarParser(String source) {
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
		
		if("menubar".equals(tag)) {
			iconsPath =  attlist.getValue("icons-path");
			menubar = new MenuBar();
		}
		
		if("menu".equals(tag)) {
			title = attlist.getValue("title");
			menu = new Menu(title);
			menubar.addMenus(menu);
		}
		
		if("item".equals(tag)) {
			label = attlist.getValue("label");
			if(attlist.getValue("icon") != null) {
				icon = iconsPath + attlist.getValue("icon");
			}
			if(attlist.getValue("action") != null) {
				action = attlist.getValue("action");
			}
			if(icon != null) item = new JMenuItem(label, new ImageIcon(icon));
			else item = new JMenuItem(label);
			//ACTIONS
			if("pop".equals(action)) {
				item.addActionListener(new PopMessage(null, label));
			}
			if("exit".equals(action)) {
				item.addActionListener(new Exiter());
			}
			menu.add(item);
		}
		
		if("s".equals(tag)) {
			menu.addSeparator();
		}
	}
	
	public void characters(char[] data, int start, int end) throws SAXException {
		String val = new String(data, start, end).trim();
		
		if("item".equals(tag)) {
			item.setToolTipText(val);
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
	
	public MenuBar getMenubar() {
		return menubar;
	}

}
