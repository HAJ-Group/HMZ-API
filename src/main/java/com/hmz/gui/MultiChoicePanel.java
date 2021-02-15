package com.hmz.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

/** FORM COMPONENT OBJECT : PANEL OF MULTIPLE CHOICES
 * 
 * @author hamza
 *
 */
public class MultiChoicePanel extends Panel {
	// FORM COMPONENT OBJECT
	private static final long serialVersionUID = 1L;
	
	private Vector<JCheckBox> checkboxes;
	
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------

	public MultiChoicePanel(String label, String... choices) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		if(!label.contains(":")) label += " : ";
		JLabel jlabel = new JLabel(label);
		jlabel.setForeground(Color.YELLOW);
		add(jlabel);
		
		checkboxes = new Vector<>();
		addChoices(choices);
	}
	
	public MultiChoicePanel(String label, int labelwidth, String... choices) {
		this(label, choices);
		JLabel l1 = (JLabel)getComponent(0);
		l1.setPreferredSize(new Dimension(labelwidth, l1.getPreferredSize().height));
	}
	
	// ADD SECTION ----------------------------------------------------------------------------------------------
	
	public void addChoices(String ...choices) {
		for (String choice : choices) {
			JCheckBox cb = new JCheckBox(choice);
			cb.setBackground(Color.DARK_GRAY);
			cb.setForeground(Color.WHITE);
			add(cb);
			checkboxes.add(cb);
		}
	}
	
	public String removeChoice(int index) {
		String ret = null;
		index++;
		if(getComponent(index) instanceof JCheckBox) {
			JCheckBox cb = (JCheckBox)getComponent(index);
			remove(cb);
			checkboxes.remove(cb);
		}
		return ret;
	}
	
	public void addActionListener(ActionListener listener) {
		for(JCheckBox cb : checkboxes) {
			cb.addActionListener(listener);
		}
	}
	
	public void addChoiceActionListener(int index, ActionListener listener) {
		getChoice(index).addActionListener(listener);
	}
	
	public void addChoiceActionListener(String name, ActionListener listener) {
		getChoice(name).addActionListener(listener);
	}
	
	// GET SECTION ----------------------------------------------------------------------------------------------
	
	public List<String> getCheckedValues() {
		List<String> ret = new Vector<>();
		for(JCheckBox cb : checkboxes) {
			if(cb.isSelected()) ret.add(cb.getText());
		}
		return ret;
	}
	
	public JCheckBox getChoice(int index) {
		return checkboxes.get(index);
	}
	
	public JCheckBox getChoice(String name) {
		JCheckBox ret = null;
		for(JCheckBox c : checkboxes) {
			if(c.getText().equals(name)) ret = c;
		}
		return ret;
	}
	
	public Vector<JCheckBox> getCheckboxes() {
		return checkboxes;
	}
	
	// SET SECTION ----------------------------------------------------------------------------------------------
	
	public void setCheckedValues(Object ...values) {
		for(Object o : values) {
			for(JCheckBox cb : checkboxes) 
				if(cb.getText().equals(o)) cb.setSelected(true);
		}
	}
	
	public void setChoiceValue(String value, int index) {
		getChoice(index).setText(value);
	}

}
