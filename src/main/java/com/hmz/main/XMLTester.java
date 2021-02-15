package com.hmz.main;

import com.hmz.collections.Vecrray;
import com.hmz.test.Tester;
import com.hmz.xml.parsers.DomXMLParser;
import com.hmz.xml.parsers.FormParser;
import org.w3c.dom.Node;

import java.util.List;

class XMLTester extends Tester {

	public XMLTester(int testIndex) {
		super(testIndex);
	}
	
	
	void test1() {
		// PARSING THE DOCUMENT
		DomXMLParser p = new DomXMLParser();
		List<Node> documents = p.getChildren("document");
		for(Node doc : documents) {
			System.out.println(p.getData(doc, "@id", "title", "price"));
		}
	}
	
	void test2() {
		DomXMLParser p = new DomXMLParser();
		List<String> path = new Vecrray<>("document(1)", "authors", "author");
		for(Node n : p.getChildren(path)) {
			System.out.println(p.getData(n, "@id", "name", "year-born"));
		}
	}
	
	// XML AND GUI
	void test3() {
		FormParser parser = new FormParser();
		parser.parse();
	}
}
