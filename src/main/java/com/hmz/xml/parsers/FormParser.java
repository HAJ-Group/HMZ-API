package com.hmz.xml.parsers;

import com.hmz.collections.Vecrray;
import com.hmz.gui.*;
import com.hmz.gui.listeners.ColorHover;
import com.hmz.gui.listeners.Exiter;
import com.hmz.gui.listeners.PopMessage;
import com.hmz.operations.Converter;
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
import java.awt.*;
import java.io.File;
import java.util.List;

/** THIS IS A FORM XML PARSER : THE XML FILE MUST BE VALIDATED BY THE DEFAULT SCHEMA WHICH IS AVAILABLE IN RESOURCES FOLDER
 * 
 * @author hamza	
 *
 */
public class FormParser extends DefaultHandler {
	
	public static final String DEFAULT_SOURCE = "resources/xml/form.xml";
	public static final String DEFAULT_SCHEMA = "resources/xml/schemas/xsd/form.xsd";
	
	private SAXParserFactory factory;
	private String source;
	private String tag = null;
	
	// GUI COMPONENTS
	private Form form;
	private String title;
	private int labelwidth;
	
	private LabeledTextField ltf;
	private LabeledTextArea lta;
	
	private ChoicePanel cp;
	private MultiChoicePanel mcp;
	private SelectPanel sp;
	private boolean multiple;
	private List<String> mulchecked = new Vecrray<>();
	private boolean choice;
	
	private ButtonsPanel bp = new ButtonsPanel();
	private String action;
	private boolean hover;
	private boolean disabled;
	
	private boolean checked;
	private JLabel l;
	
	// CONSTRUCTORS
	
	public FormParser() {
		this(DEFAULT_SOURCE);
	}
	
	public FormParser(String source) {
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
		
		if("form".equals(tag)) {
			title = attlist.getValue("title");
			labelwidth = Converter.toInteger(attlist.getValue("label-width"));
			
			form = new Form(title, labelwidth);
			form.setButtons(bp);
		}
		
		if("label".equals(tag)) {
			l = new JLabel();
			form.addFormComponents(l);
		}
		
		if("text".equals(tag)) {
			String label = attlist.getValue("label");
			int size = Converter.toInteger(attlist.getValue("size"));
			int rows = Converter.toInteger(attlist.getValue("rows"));
			if(rows > 1) {
				// TEXTAREA
				lta = new LabeledTextArea(label, rows, size, labelwidth);
				form.addFormComponents(lta);
			} else {
				// TEXTFIELD
				ltf = new LabeledTextField(label, size, labelwidth);
				form.addFormComponents(ltf);
			}
		}
		
		if("choice".equals(tag)) {
			String label = attlist.getValue("label");
			multiple = Converter.toBoolean(attlist.getValue("multiple"));
			if(multiple) {
				mcp = new MultiChoicePanel(label);
				form.addFormComponents(mcp);
			} else {
				cp = new ChoicePanel(label);
				form.addFormComponents(cp);
			}
			choice = true;
		}
		
		if("select".equals(tag)) {
			String label = attlist.getValue("label");
			sp = new SelectPanel(label);
			form.addFormComponents(sp);
			choice = false;
		}
		
		if("option".equals(tag)) {
			checked = Converter.toBoolean(attlist.getValue("checked"));
		}
		
		if("s".equals(tag)) {
			form.addSeparator();
		}
		
		
		if("button".equals(tag)) {
			action = attlist.getValue("action");
			hover = Converter.toBoolean(attlist.getValue("hover"));
			disabled = Converter.toBoolean(attlist.getValue("disabled"));
		}
	}
	
	public void characters(char[] data, int start, int end) throws SAXException {
		String val = new String(data, start, end).trim();
		
		if("text".equals(tag)) {
			if(ltf == null) lta.setValue(val);
			else ltf.setValue(val);
		}
		
		if("option".equals(tag)) {
			if(choice) {
				if(multiple) {
					mcp.addChoices(val);
					if(checked) mulchecked.add(val);
					mcp.setCheckedValues(mulchecked.toArray());
				} else {
					cp.addChoices(val);
					if(checked) cp.setCheckedValue(val);
				}
			}
			else {
				sp.addOption(val);
				if(checked) mulchecked.add(val);
				sp.setSelectedValues(mulchecked.toArray());
			}
		}
		
		if("label".equals(tag)) {
			l.setText(val);
			l.setForeground(Color.yellow);
		}
		
		if("button".equals(tag)) {
			bp.addButtons(val);
			JButton b = bp.getButton(val);
			if(action.equals("pop")) {
				b.addActionListener(new PopMessage(null, val));
			} else {
				b.addActionListener(new Exiter());
			}
			if(hover) {
				b.addMouseListener(new ColorHover(b, Color.yellow, b.getForeground()));
			}
			if(disabled) {
				b.setEnabled(false);
			}
		}
	}
	
	public void endElement(String uri, String lname, String qname) throws SAXException {
		tag = null;
		ltf = null;
		lta= null;
		if("choice".equals(qname) || "select".equals(qname)) mulchecked = new Vecrray<>();
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
	
	public Form getForm() {
		return form;
	}

}
