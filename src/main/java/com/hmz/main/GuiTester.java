package com.hmz.main;

import com.hmz.collections.Vecrray;
import com.hmz.gui.*;
import com.hmz.models.Person;
import com.hmz.operations.Age;
import com.hmz.test.Tester;
import com.hmz.xml.parsers.DomXMLParser;
import com.hmz.xml.parsers.FormParser;
import com.hmz.xml.parsers.MenuBarParser;
import com.hmz.xml.parsers.TablePanelParser;
import org.w3c.dom.Node;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class GuiTester extends Tester {
	
	Window window;

	public GuiTester(int testIndex) {
		super(testIndex);
	}
	
	void test1() {
		DataPrinter printer = new DataPrinter("my data", new String [] {"title1", "title2"}, "value 1", "value 2");
		DataShow viewer;
		viewer = new DataShow(printer);
		
		//viewer = new DataShow("val1", "val2", "val3");
		//viewer = new DataShow(new String [] {"t1", "t2", "t3"},"val1", "val2", "val3");
		//System.out.println(viewer.getDataLength());
		window.setContentPane(viewer);
	}
	
	void test2() {
		Object [][] data = {
				{"t1", "value 1"},
				{"t2", "value 2"},
				{"t3", "value 3"},
				{"t4", "value 4"}
		};
		DataShow viewer = new DataShow(data);
		DataShow v2 = new DataShow(new String[] {"1", "2", "3", "4"} ,1, 2, 3, 4);
		viewer.addFields(null, "value 5", "value 6", "value 7", "value 8", "value 9", 90, 11);
		viewer.addFields(null, "hehooo");
		DataPrinter printer = new DataPrinter("", new String[] {"zido"}, "ha zyada");
		viewer.addFields(printer);
		viewer.addFields(v2);
		window.setContentPane(viewer);
	}
	
	void test3() {
		String [][] bars = {
				{"file", "open", "save", "quit"}
		};
		MenuBar menu = new MenuBar(bars);
		Menu m = new Menu("menu2", "1", new Menu("menu3", "1", new Menu("menu4")));
		
		menu.add(m);
		window.setJMenuBar(menu);
	}
	
	void test4() {
		// XML WITH GUI
		DomXMLParser parser = new DomXMLParser();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(parser.getNode().getNodeName());
		// LIST OF DOCUMENTS
		List<Node> documents = parser.getChildren("document");
		for(Node doc : documents) {
			// BUILD THE DOCUMENT NODE
			DefaultMutableTreeNode document = new DefaultMutableTreeNode(doc.getNodeName());
			List<String> data = parser.getData(doc, "@id", "title", "price");
			document.add(new DefaultMutableTreeNode("ID : " + data.get(0)));
			document.add(new DefaultMutableTreeNode("Title : " + data.get(1)));
			document.add(new DefaultMutableTreeNode("Price : " + data.get(2)));
			// AUTHORS
			DefaultMutableTreeNode authorsRoot = new DefaultMutableTreeNode("authors");
			List<Node> authors = parser.getChildren(doc, new Vecrray<>("authors", "author"));
			for(Node aut : authors) {
				DefaultMutableTreeNode author = new DefaultMutableTreeNode(aut.getNodeName());
				data = parser.getData(aut, "@id", "name", "year-born", "country");
				author.add(new DefaultMutableTreeNode("ID : " + data.get(0)));
				author.add(new DefaultMutableTreeNode("Name : " + data.get(1)));
				author.add(new DefaultMutableTreeNode("Year Born : " + data.get(2)));
				author.add(new DefaultMutableTreeNode("Country : " + data.get(3)));
				authorsRoot.add(author);
			}
			document.add(authorsRoot);
			// ADD DOCUMENT NODE TO ROOT
			root.add(document);
		}
		
		JTree tree = new JTree(root);
		window.setContentPane(tree);
	}
	
	void test5() {
		FormParser parser = new FormParser();
		MenuBarParser mparser = new MenuBarParser();
		parser.parse();
		mparser.parse();
		window.setContentPane(parser.getForm());
		window.setJMenuBar(mparser.getMenubar());
	}
	
	void test6() {
		Person p1 = new Person(1, "hamza", new Age(1, 9, 1998), "", "hamza@hotmail.com", "student");
		Person p2 = new Person(2, "said");
		Person p3 = new Person(3, "khalid");
		
		List<Object> list = new Vecrray<Object>(p1, p2, p3);
		
		TablePanel tp = new TablePanel(list);
		
		window.setContentPane(tp);
	}
	
	void test7() {
		SetupForm form = new SetupForm("MY FORM", 120, "add", "remove", "delete");
		form.setText("hello", "delete");
		//form.setButtons(false);
		window.setContentPane(form);
	}
	
	void test8() {
		TablePanelParser parser = new TablePanelParser();
		parser.parse();
		window.setContentPane(parser.getTable());
	}
	
	void test9() {
		ComboPanel p = new ComboPanel("list", "1", "2", "3");
		p.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> c = (JComboBox<String>)e.getSource();
				System.out.println(c.getSelectedItem());
				
			}
		});
		window.setContentPane(p);
	}
	
	void test10() {
		String [] headers = {"1", "2", "3"};
		String [][] data = {
				{"A", "B", "C"},
				{"a", "b", "c"}
		};
		window.setContentPane(new TablePanel(data, headers));
	}

}
