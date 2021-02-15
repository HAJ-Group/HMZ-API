package com.hmz.gui;

import javax.swing.*;
import java.awt.*;

/** FORM COMPONENT OBJECT : LABEL WITH TEXTAREA PANEL
 * 
 * @author hamza
 *
 */
public class LabeledTextField extends Panel {
	// FORM COMPONENT OBJECT
	private static final long serialVersionUID = 1L;
	private JTextField textfield;
	
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------
	
	public LabeledTextField(String label, int size) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		if(!label.contains(":")) label += " : ";
		JLabel jlabel = new JLabel(label);
		jlabel.setForeground(Color.YELLOW);
		add(jlabel);
		
		textfield = new JTextField(size);
		add(textfield);		
	}
	
	public LabeledTextField(String label, int size, int labelwidth) {
		this(label, size);
		JLabel l1 = (JLabel)getComponent(0);
		l1.setPreferredSize(new Dimension(labelwidth, l1.getPreferredSize().height));
	}
	
	// SET SECTION ----------------------------------------------------------------------------------------------
	
	public void setValue(Object value) {
		textfield.setText(value + "");	
	}
	
	// GET SECTION ----------------------------------------------------------------------------------------------

	public String getValue() {
		return textfield.getText();
	}
	
	public JTextField getTextfield() {
		return textfield;
	}

}
