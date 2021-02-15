package com.hmz.xml.parsers;

import org.w3c.dom.Node;

import javax.swing.tree.TreeNode;
import java.util.List;

public interface XMLParser {
	
	/**
	 * GET ROOT ELEMENT OF THE XML ELEMENTS
	 */
	Node getRoot();
	/**
	 * GET THE CHILD OF CURRENT NODE BY NAME
	 */
	Node getChild(String name);
	Node getChild(Node parent, String name);
	/**
	 * GET THE ATTRIBUTE OF THE CURRENT NODE BY NAME
	 */
	Node getAttribute(String name);
	Node getAttribute(Node parent, String name);
	/**
	 * GET CHILDS OF THE CURRENT NODE BY NAME
	 */
	List<Node> getChildren(String name);
	List<Node> getChildren(Node parent, String name);
	/**
	 * GET CHILDS OF THE CURRENT NODE BY A PATH DEFAULT ROOT/ELEMENT(INDEX)/CHILD1/...
	 */
	List<Node> getChildren(List<String> path);
	List<Node> getChildren(Node parent, List<String> path);
	/** SET THE CURRENT NODE
	 * 
	 * @param node
	 */
	void setNode(Node node);
	/** GET NODES DATA PROPERTIES
	 * 
	 * @param node
	 * @param properties
	 * @return
	 */
	List<String> getData(Node node, String... properties);
	
	/** USED TO RETURN A NODE TREE SWING COMPONENTS THAT CAN BE VISUALIZED BY JTREE OBJECT
	 * 
	 * @param node
	 * @return
	 */
	TreeNode getTree();
	TreeNode getTree(Node node);
	
	/** SIMILAR FUNCTIONALITY OF nodeTree METHOD BUT THIS ONE RETURNS A VIEWABLE LIST THAT CONTAINS ALL XML DATA
	 * 
	 * @param node
	 * @return
	 */
	List<String> getPrinter();
	List<String> getPrinter(Node node);
	
	/**
	 * GET THE SOURCE FILE
	 * @return
	 */
	String getSource();
	
}
