package com.hmz.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

/** FORM COMPONENT OBJECT : LIST OF OPTIONS 
 * 
 * @author hamza
 *
 */
public class SelectPanel extends Panel {
	// FORM COMPONENT OBJECT
	private static final long serialVersionUID = 1L;
	private JList<String> list;
	
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------
	
	public SelectPanel(String label, String ...options) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		if(!label.contains(":")) label += " : ";
		JLabel jlabel = new JLabel(label);
		jlabel.setForeground(Color.YELLOW);
		add(jlabel);
		
		list = new JList<>(options);
		add(new JScrollPane(list));
	}
	
	// ADD SECTION ----------------------------------------------------------------------------------------------
	
	public void addOption(String option) {
		ListModel<String> model = list.getModel();
		// ALLOCATING NEW LIST
		String [] ndata = new String[model.getSize()+1];
		for (int i = 0; i < model.getSize(); i++) {
			ndata[i] = model.getElementAt(i);
		}
		ndata[model.getSize()] = option;
		list.setListData(ndata);
	}
	
	public void removeOption(int index) {
		ListModel<String> model = list.getModel();
		// ALLOCATING NEW LIST
		Vector<String> ndata = new Vector<>();
		for (int i = 0; i < model.getSize(); i++) {
			ndata.add(model.getElementAt(i));
		}
		ndata.remove(index);
		list.setListData(ndata);
	}
	
	
	// SET SECTION ----------------------------------------------------------------------------------------------

	public void setSelectedValues(Object ...values) {
		int  indices [] = new int[values.length], tmp = 0;
		for(Object o : values) {
			ListModel<String> model = list.getModel();
			for (int i = 0; i < model.getSize(); i++) 
				if(model.getElementAt(i).equals(o)) indices[tmp++] = i;
		}
		list.setSelectedIndices(indices);
	}
	
	// GET SECTION ----------------------------------------------------------------------------------------------
	
	public List<String> getSelectedValues() {
		return list.getSelectedValuesList();
	}
	
	public JList<String> getList() {
		return list;
	}

}
