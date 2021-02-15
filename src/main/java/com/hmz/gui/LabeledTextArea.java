package com.hmz.gui;

import javax.swing.*;
import java.awt.*;

/** FORM COMPONENET OBJECT : LABEL WITH TEXTFIELD PANEL
 * 
 * @author hamza
 *
 */
public class LabeledTextArea extends Panel {
	// FORM COMPONENT OBJECT
	private static final long serialVersionUID = 1L;
	private JTextArea textarea;
	
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------
	
	public LabeledTextArea(String label, int rows, int cols) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		if(!label.contains(":")) label += " : ";
		JLabel jlabel = new JLabel(label);
		jlabel.setForeground(Color.YELLOW);
		add(jlabel);
		
		textarea = new JTextArea(rows, cols);
		add(textarea);
	}
	
	public LabeledTextArea(String label, int rows, int cols, int labelwidth) {
		this(label, rows, cols);
		JLabel l1 = (JLabel)getComponent(0);
		l1.setPreferredSize(new Dimension(labelwidth, l1.getPreferredSize().height));
	}
	
	// SET SECTION ----------------------------------------------------------------------------------------------
	
	public void setValue(Object value) {
		textarea.setText(value + "");
	}
	
	// GET SECTION ----------------------------------------------------------------------------------------------
	
	public String getValue() {
		return textarea.getText();
	}

	public JTextArea getTextarea() {
		return textarea;
	}
	
	
}
