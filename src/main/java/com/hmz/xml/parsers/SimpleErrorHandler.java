package com.hmz.xml.parsers;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {

	public void error(SAXParseException e) throws SAXException {
		System.err.println("Error::" + e.getMessage());
	}

	public void fatalError(SAXParseException e) throws SAXException {
		System.err.println("Fatal Error::" + e.getMessage());

	}

	public void warning(SAXParseException e) throws SAXException {
		System.err.println("Warning::" + e.getMessage());
	}

}
