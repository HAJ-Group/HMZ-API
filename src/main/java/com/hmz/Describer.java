package com.hmz;

import com.hmz.collections.Vecrray;

/** DESCRIPTION FOR EVERY HMZ OBJECT
 * 
 * @author hamza
 *
 */
public enum Describer {
	
	COLLECTION("Array                : An Array Based on Manual Implementation which wrapp a table with multiple access services",
			   "CollectionComparator : Implementation of the Interface Comparator which can be associated to most of collections",
			   "Vecrray              : Extension of the class Vector with mutiple values initialization and adding services",
			   "Organizer            : Extension of the class Vecrray with sorting, randomzing, organazing services "
	),
	GUI("Window               : Extension of the class JFrame with such a standart configurations and demonstration methods",
		"Dialog               : Extension of the class JDialog with such a standart configurations", 
		"Panel                : Extension of the class JPanel with a dark background and Layouts demonstration methods", 
		"MenuBar              : Extension of the class JMenuBar with usefull added services associated with the class Menu", 
		"Menu                 : Extension of the class Jmenu with multiple initializing and adding services and listeners", 
		"MenuPanel            : Extension of the class Panel with ability to create a Vertical buttoned menu options panel", 
		"DataPrinter          : Extension of the class Panel with ability to create a Printer of short tittled data as title : data", 
		"DataShow             : Extension of the class DataPrinter with improving the strategy of viewing data with JTable Object that can support even long data with titles or not", 
		"SearchPanel          : Extension of the class Panel with ability to create a data Finder panel with one textfield and one description", 
		"ImagePanel           : Extension of the class Panel with ability to show an image on the panel", 
		"SetupForm            : Extension of the class Panel with ability to create a labeled text fields as a Mini Form (AS A LOGIN)",
		"TablePanel           : Extension of the class Panel with ability to craete a table sheet of data using JTable object",
		"Form                 : Extension of the class Panel with ability to create multidifferent form components for data setup with multiple usefull services", 
		"ButtonsPanel         : Extension of the class Panel with ability to create a list of buttons with listeners services", 
		"ChoicePanel          : Extension of the class Panel with ability to create a label with multiple choices based on radio buttoms (MONO SELECT)", 
		"MultiChoicePanel     : Extension of the class Panel with ability to create a label with multiple choices based on check boxes (MULTI SELECT)", 
		"SelectPanel          : Extension of the class Panel with ability to create a label with multiple choices based on JList Object (MULTI SELECT)", 
		"LabeledTextField     : Extension of the class Panel with ability to create a label with textfield configurated with a specified labelwith", 
		"LabeledTextArea      : Extension of the class Panel with ability to create a label with textarea configurated with a specified labelwith", 
		"TextField            : Extension of the class JTextField with some width and height configurations",
		"listeners/Hover      : Extension of the class MouseAdapter as an abstract class for Mouse enter and exit actions",
		"listeners/ColorHover : Extension of the class Hover for Mouse Color enter and exit Actions used with foregrounds and backgrounds",
		"listeners/Exiter     : Implementation of the interface ActionListener for exiting actions",
		"listeners/PopMessage : Implementation of the interface ActionListener for displaying as simple text message using JOptionPane Object"
	),
	IO("FileManager        : Interface with file management services write, read, append, copy",
	   "BinaryFileManager  : Implementation of the interface FileManager for Binary operations on files",
	   "TextFileManager    : Implementation of the interface FileManager for Textual operations on files",
	   "dev/FileManager    : Extension of the class File with binary reading, writing, copying services"),
	OPERATION("Calculator  : An operational class to process some complex arithmetic expressions",
			  "Converter   : An operational class to convert from a String Object to Integer, Long, Boolean, Double, Byte, Date... (Statically)",
			  "Date        : An operational class for managing dates data which envlopes GregorianCalendar and Joda API Objects for usefull and easy accessable services",
			  "Age         : Extension of the class Date with personalized services to manage age periods"),
	XML("XMLParser          : Interface with xml parsing services getRoot, GetChild, GetAttribute, GetChilds, nodeData...",
		"DomXMLParser       : Implementation of the interface XMLParser for DOM Parsing operations on xml files",
		"SimpleErrorHander  : Implementation of the interface ErrorHandler in order to make a simple dom parsing error managmenet",
		"FormParser         : Extension of the class DefaultHandler with associated XSD Schema for order to parse any validated xml file using SAX",
		"MenuBarParser      : Extension of the class DefaultHandler with associated XSD Schema for order to parse any validated xml file using SAX",
		"MenuPanelParser    : Extension of the class DefaultHandler with associated XSD Schema for order to parse any validated xml file using SAX",
		"SetupFormParser    : Extension of the class DefaultHandler with associated XSD Schema for order to parse any validated xml file using SAX",
		"TablePanelParser   : Extension of the class DefaultHandler with associated XSD Schema for order to parse any validated xml file using SAX"),
	JDBC("DBManager         : An operational class for managing database operations such as select, insert, update, and delete with usefull services",
		 "PoolDBManager     : Extension of the class DBManager with ability to create a pool of multiple connextions to the database"),
	MODELS("Person          : Class Model (ID, NAME, AGE, ADRESSE, EMAIL, JOB)");
	
	private String [] items;
	private String [] objects;
	private String [] descs;
	
	private Describer(String ...items) {
		this.items = items;
		objects = new String[items.length];
		descs = new String[items.length];
		for (int i = 0; i < items.length; i++) {
			objects[i] = items[i].split(":")[0].trim();
			descs[i] = items[i].split(":")[1].trim();
		}
	}
	
	public Vecrray<String> getObjects() {
		return new Vecrray<>(objects);
	}
	
	public String getDescription(int objectIndex) {
		return objects[objectIndex] + " : " + descs[objectIndex];
	}
	
	public String getDescription(String object) {
		String ret = null;
		for (int i = 0; i < descs.length; i++) {
			if(objects[i].toLowerCase().equals(object.toLowerCase())) {
				ret = objects[i] + " : " + descs[i];
			}
		}
		return ret;
	}
	
	public void describe() {
		for(String item : items)
			System.out.println(item);
	}
}
