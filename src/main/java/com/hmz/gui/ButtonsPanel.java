package com.hmz.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

/** PANEL OF BUTTONS WITH PRESONALIZED METHODS
 * 
 * @author hamza
 *
 */
public class ButtonsPanel extends Panel {
	
	private static final long serialVersionUID = 1L;
	private int flow;
	private Color bc;
	private Color fc;
	private Vector<JButton> buttonsL = new Vector<>();
	
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------

	public ButtonsPanel(String ...buttons) {
		this(FlowLayout.RIGHT, buttons);
	}
	
	public ButtonsPanel(int flow, String ...buttons) {
		this(flow, Color.DARK_GRAY, Color.WHITE, buttons);
	}

	public ButtonsPanel(int flow, Color bc, Color fc, String ...buttons) {
		this.flow = flow;
		this.bc = bc;
		this.fc = fc;
		setLayout(new FlowLayout(this.flow));
		addButtons(buttons);
	}
	
	// ADD SECTION ----------------------------------------------------------------------------------------------
	
	public void add(JButton button) {
		button.setBackground(this.bc);
		button.setForeground(this.fc);
		add((Component)button);
	}
	
	public void addButtons(String ...buttons) {
		for (String string : buttons) {
			JButton b = new JButton(string);
			add(b);
			buttonsL.add(b);
		}
	}
	
	public void addActionListener(ActionListener listener) {
		for(JButton b : buttonsL) {
			b.addActionListener(listener);
		}
	}
	
	public void addButtonActionListener(int index, ActionListener listener) {
		getButton(index).addActionListener(listener);
	}
	
	public void addButtonActionListener(String name, ActionListener listener) {
		getButton(name).addActionListener(listener);
	}
	
	// GET SECTION ----------------------------------------------------------------------------------------------
	
	public JButton getButton(int index) {
		return buttonsL.get(index);
	}
	
	public JButton getButton(String name) {
		JButton ret = null;
		for(JButton b : buttonsL) {
			if(b.getText().equals(name)) ret = b;
		}
		return ret;
	}
	
	public Vector<JButton> getButtons() {
		return buttonsL;
	}
	
	// SET SECTION ----------------------------------------------------------------------------------------------

	public void setButtonValue(String value, int index) {
		getButton(index).setText(value);
	}

}
