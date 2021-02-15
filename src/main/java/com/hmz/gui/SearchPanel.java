package com.hmz.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/** PANEL USED FOR INDEXING DATA BY ONE INPUT
 * 
 * @author hamza
 *
 */
public class SearchPanel extends Panel {
	// PANEL USED FOR SEARCHING DATA BY ONLY ONE INPUT
	private static final long serialVersionUID = 1L;
	private com.hmz.gui.TextField textfield;
	
	public static final String SEARCH_BUTTON = "Search";
	public static final String BACK_BUTTON = "Back";
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------
	
	public SearchPanel(String desc) {
		this(desc, 20);
	}
	
	public SearchPanel(String desc, int size) {
		JLabel label = new JLabel(desc);
		label.setHorizontalAlignment(JTextField.CENTER);
		label.setFont(new Font("Calibri Light", Font.BOLD, 30));
		label.setForeground(Color.white);
		label.setPreferredSize(new Dimension(500, label.getPreferredSize().height));
		add(label);
		
		textfield = new com.hmz.gui.TextField(size);
		add(textfield);
		
		ButtonsPanel b = new ButtonsPanel(FlowLayout.CENTER, SEARCH_BUTTON, BACK_BUTTON);
		add(b);
	}
	
	// ADD SECTION ----------------------------------------------------------------------------------------------

	public void addActionListener(ActionListener listener) {
		((ButtonsPanel)getComponent(2)).addActionListener(listener);
	}
	
	// GET SECTION ----------------------------------------------------------------------------------------------
	
	public TextField getTextfield() {
		return textfield;
	}
	
	public JLabel getDescription() {
		return (JLabel)getComponent(0);
	}
	
	public JButton getButton(int index) {
		JButton ret = null;
		ButtonsPanel b = (ButtonsPanel)this.getComponent(2);
		ret = (JButton)b.getButton(index);
		return ret;
	}
	
	public JButton getButton(String name) {
		JButton ret = null;
		ButtonsPanel b = (ButtonsPanel)this.getComponent(2);
		ret = (JButton)b.getButton(name);
		return ret;
	}
	
	
	/** DEFAULT BUTTONS ARE SEARCH BUTTON AND BACK BUTTON
	 * 
	 * @return DEFAULT BUTTONS LABEL VALUES
	 */
	public String [] getDefaultButtons() {
		return new String[] {SEARCH_BUTTON, BACK_BUTTON};
	}
	
	
	// SET SECTION ----------------------------------------------------------------------------------------------
	
	public void setDescriptionValue(String value) {
		getDescription().setText(value);
	}
	
	public void setTextfieldValue(String value) {
		getTextfield().setText(value);
	}
	
	public void setDefaultButtons(String action1, String action2) {
		ButtonsPanel b = ((ButtonsPanel)getComponent(2));
		b.setButtonValue(action1, 0);
		b.setButtonValue(action2, 1);
	}

}
