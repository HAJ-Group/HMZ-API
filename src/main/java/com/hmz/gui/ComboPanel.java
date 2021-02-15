package com.hmz.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/** FORM COMPONENT OBJECT : PANEL LIST OF CHOICES WITH PERSONALIZED METHODS
 * 
 * @author hamza
 *
 */
public class ComboPanel extends Panel {
	// FORM COMPONENT OBJECT
	private static final long serialVersionUID = 1L;
	private JComboBox<String> combos;
	
	public ComboPanel(String label, String ...choices) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		if(!label.contains(":")) label += " : ";
		JLabel jlabel = new JLabel(label);
		jlabel.setForeground(Color.YELLOW);
		add(jlabel);
		
		addChoices(choices);
	}
	
	public ComboPanel(String label, int labelwidth, String ...choices) {
		this(label, choices);
		JLabel l1 = (JLabel)getComponent(0);
		l1.setPreferredSize(new Dimension(labelwidth, l1.getPreferredSize().height));
	}
	
	// ADD SECTION ----------------------------------------------------------------------------------------------
	
	public void addChoices(String ...choices) {
		combos = new JComboBox<>(choices);
		add(combos);
	}
	
	public String removeChoice(int index) {
		String ret = combos.getItemAt(index);
		combos.remove(index);
		return ret;
	}
	
	public String getChoice(int index) {
		return combos.getItemAt(index);
	}
	
	public void addActionListener(ActionListener listener) {
		combos.addActionListener(listener);
	}
	
	// GET SECTION ----------------------------------------------------------------------------------------------
	
	public String getSelectedValue() {
		return combos.getSelectedItem().toString();
	}
	
	public JComboBox<String> getCombos() {
		return combos;
	}
	
}
