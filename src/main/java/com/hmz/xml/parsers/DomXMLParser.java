package com.hmz.xml.parsers;

import com.hmz.collections.Vecrray;
import com.hmz.operations.Converter;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DomXMLParser implements XMLParser {
	
	public static String default_source = "resources/xml/documents.xml";
	
	private Node node;
	private String source;
	private DocumentBuilderFactory factory;
	
	/**
	 * DEFAULT CONSTRUCTOR : NODE IS THE ROOT ELEMENT 
	 */
	public DomXMLParser() {
		this(default_source, false);
	}

	public DomXMLParser(String source) {
		this(source, false);
	}
	
	public DomXMLParser(boolean validate) {
		this(default_source, validate);
	}

	public DomXMLParser(String source, boolean validate) {
		this.source = source;
		factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(validate);
		node = getRoot();
	}
	
	public Node getRoot() {
		Node ret = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setErrorHandler(new SimpleErrorHandler());
			Document document = builder.parse(source);
			ret = document.getDocumentElement();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public Node getChild(String name) {
		Node ret = null;
		List<Node> list = getChildren(name);
		if(list != null && list.size() > 0 && list.get(0) != null) ret = list.get(0);
		return ret;
	}
	
	public Node getChild(Node parent, String name) {
		setNode(parent);
		return getChild(name);
	}
	
	public Node getAttribute(String name) {
		Node ret = null;
		if(node != null) ret = node.getAttributes().getNamedItem(name);
		return ret;
	}
	
	public Node getAttribute(Node parent, String name) {
		setNode(parent);
		return getAttribute(name);
	}
	
	public List<Node> getChildren(String name) {
		List<Node> ret = new Vecrray<>();
		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			if(nodes.item(i).getNodeName().equals(name)) {
				ret.add(nodes.item(i));
			}
		}
		return ret;
	}
	
	public List<Node> getChildren(Node parent, String name) {
		setNode(parent);
		return getChildren(name);
	}
	
	public List<Node> getChildren(List<String> path) {
		return getChildren(getRoot(), path);
	}
	
	public List<Node> getChildren(Node parent, List<String> path) {
		List<Node> ret = new Vecrray<>();
		int selected = 0; 
		String element = path.get(0);
		if(element.contains("(")) {
			selected = Converter.toInteger(element.substring(element.indexOf("(")+1, element.indexOf(")")));
			element = element.substring(0, element.indexOf("("));
		}
		ret = getChildren(parent, element);
		path.remove(0);
		if(!path.isEmpty()) ret = getChildren(ret.get(selected), path);
		return ret;
	}
	
	public List<String> getData(Node node, String ...properties) {
		List<String> ret = new Vecrray<>();
		setNode(node);
		for(String p : properties) {
			if(p.startsWith("@")) ret.add(getAttribute(p.substring(1)).getNodeValue());
			else ret.add(getChild(p).getTextContent());
		}
		return ret;
	}
	
	public TreeNode getTree() {
		return getTree(getRoot());
	}
	
	public TreeNode getTree(Node node) {
		DefaultMutableTreeNode ret = null;
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			ret = new DefaultMutableTreeNode(node.getNodeName());
			// ATTRIBUTES
			NamedNodeMap attlist = node.getAttributes();
			if(attlist != null && attlist.getLength() > 0) {
				for (int i = 0; i < attlist.getLength(); i++) {
					Node attr = attlist.item(i);
					ret.add(new DefaultMutableTreeNode("@" + attr.getNodeName() + " = " + attr.getNodeValue()));
				}
			}
			// CHILDREN
			NodeList childs = node.getChildNodes();
			for (int i = 0; i < childs.getLength(); i++) {
				Node child = childs.item(i);
				if(child.getNodeType() == Node.ELEMENT_NODE) {
					DefaultMutableTreeNode tNode = new DefaultMutableTreeNode(child.getNodeName());
					if(child.getChildNodes().getLength() == 1 && child.getTextContent() != null) {
						tNode.add(new DefaultMutableTreeNode(child.getTextContent()));
						ret.add(tNode);
					}
					else ret.add((DefaultMutableTreeNode)getTree(child));
				}
			}
		}
		return ret;
	}
	
	public List<String> getPrinter() {
		return getPrinter(getRoot());
	}
	
	public List<String> getPrinter(Node node) {
		List<String> ret = new Vecrray<>();
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			ret.add(node.getNodeName());
			//ATTRIBUTES
			NamedNodeMap attlist = node.getAttributes();
			if(attlist != null && attlist.getLength() > 0) {
				for (int i = 0; i < attlist.getLength(); i++) {
					Node attr = attlist.item(i);
					ret.add("@" + attr.getNodeName() + " = " + attr.getNodeValue());
				}
			}
			//CHILDREN
			NodeList childs = node.getChildNodes();
			for (int i = 0; i < childs.getLength(); i++) {
				Node child = childs.item(i);
				if(child.getNodeType() == Node.ELEMENT_NODE) {
					if(child.getChildNodes().getLength() == 1 && child.getTextContent() != null) {
						ret.add("\n\t" + child.getNodeName() + " : " + child.getTextContent());
					}
					else ret.add("\n\t" + getPrinter(child));
				}
			}
		}
		return ret;
	}
	
	// GETTERS AND SETTERS
	
	@Override
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
		node = getRoot();
	}
	
	public void setNode(Node node) {
		this.node = node;
	}

	public Node getNode() {
		return node;
	}
		
	public void setSchema(String source) throws Exception {
		if(!factory.isValidating()) {
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File(source));
			factory.setSchema(schema);
			node = getRoot();
		} else throw new SAXException("Validating with DTD is enabled :: can't validate document with XSD");
	}
	
	public void setValidating(boolean validate) {
		factory.setValidating(validate);
		node = getRoot();
	}

	public String toString() {
		return getPrinter(getRoot()).toString();
	}

}
