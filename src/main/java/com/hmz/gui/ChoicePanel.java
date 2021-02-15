package com.hmz.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Enumeration;

/** FORM COMPONENT OBJECT : PANEL OF ONE CHEKCED CHOICES WITH PERSONALIZED METHODS 
 * 
 * @author hamza
 *
 */
public class ChoicePanel extends Panel {
	// FORM COMPONENT OBJECT
	private static final long serialVersionUID = 1L;
	
	private ButtonGroup group;
	
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------
	
	public ChoicePanel(String label, String ...choices) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		if(!label.contains(":")) label += " : ";
		JLabel jlabel = new JLabel(label);
		jlabel.setForeground(Color.YELLOW);
		add(jlabel);
		
		group = new ButtonGroup();
		addChoices(choices);
	}
	
	public ChoicePanel(String label, int labelwidth, String ...choices) {
		this(label, choices);
		JLabel l1 = (JLabel)getComponent(0);
		l1.setPreferredSize(new Dimension(labelwidth, l1.getPreferredSize().height));
	}
	
	// ADD SECTION ----------------------------------------------------------------------------------------------
	
	public void addChoices(String ...choices) {
		for(String choice : choices) {
			JRadioButton r = new JRadioButton(choice);
			r.setBackground(Color.DARK_GRAY);
			r.setForeground(Color.WHITE);
			add(r);
			group.add(r);
		}
	}
	
	public String removeChoice(int index) {
		String ret = null;
		index++;
		if(getComponent(index) instanceof JRadioButton) {
			JRadioButton r = (JRadioButton)getComponent(index);
			ret = r.getText();
			remove(r);
			group.remove(r);
		}
		return ret;
	}
	
	public void addActionListener(ActionListener listener) {
		Enumeration<AbstractButton> buttons = group.getElements();
		while (buttons.hasMoreElements()) {
			AbstractButton abstractButton = (AbstractButton) buttons.nextElement();
			abstractButton.addActionListener(listener);
		}
	}
	
	public void addChoiceActionListener(int index, ActionListener listener) {
		getChoice(index).addActionListener(listener);
	}
	
	public void addChoiceActionListener(String name, ActionListener listener) {
		getChoice(name).addActionListener(listener);
	}
	
	// GET SECTION ----------------------------------------------------------------------------------------------

	public String getCheckedValue() {
		String ret = null;
		Enumeration<AbstractButton> buttons = group.getElements();
		while (buttons.hasMoreElements()) {
			AbstractButton abstractButton = (AbstractButton) buttons.nextElement();
			if(abstractButton.isSelected()) ret = abstractButton.getText();
		}
		return ret;
	}
	
	public JRadioButton getChoice(int index) {
		JRadioButton ret = null;
		int i = 0;
		Enumeration<AbstractButton> buttons = group.getElements();
		while (buttons.hasMoreElements()) {
			if(i++ == index) ret = (JRadioButton)buttons.nextElement();
		}
		return ret;
	}
	
	public JRadioButton getChoice(String name) {
		JRadioButton ret = null;
		Enumeration<AbstractButton> buttons = group.getElements();
		while (buttons.hasMoreElements()) {
			if(buttons.nextElement().getText().equals(name)) ret = (JRadioButton)buttons.nextElement();
		}
		return ret;
	}
	
	public ButtonGroup getGroup() {
		return group;
	}
	
	// SET SECTION ----------------------------------------------------------------------------------------------
	
	public void setCheckedValue(Object value) {
		Enumeration<AbstractButton> buttons = group.getElements();
		while (buttons.hasMoreElements()) {
			AbstractButton abstractButton = (AbstractButton) buttons.nextElement();
			if(abstractButton.getText().equals(value)) abstractButton.setSelected(true);
		}
	}
	
	public void setChoiceValue(String value, int index) {
		getChoice(index).setText(value);
	}

}
