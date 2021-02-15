package com.hmz.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** PANEL FOR FAST INPUTED INFORMATION USED ON A DIALOG OR A FRAME (MINI FORM)
 * 
 * @author hamza
 *
 */
public class SetupForm extends com.hmz.gui.Panel {
	// PANEL FOR FAST INPUTED INFORMATION USED ON A DIALOG OR A FRAME (MINI FORM)
	private static final long serialVersionUID = 1L;
	private com.hmz.gui.Panel container;
	private ButtonsPanel bp;
	private int labelwidth;
	
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------
	
	/** CUSTORMIZED CONSTUCTORE
	 * 
	 * @param title FORM TITLE 
	 * @param labelWidh WIDTH BETWEEN LABELS
	 * @param labels LIST OF DATA LABELS
	 */
	public SetupForm(String title, int labelWidh, String ...labels) {
		this(title, labelWidh, true, labels);
	}
	
	/** CUSTORMIZED CONSTUCTORE
	 * 
	 * @param title FORM TITLE 
	 * @param labelWidh WIDTH BETWEEN LABELS
	 * @param buttons ENABLE/DISABLE BUTTONS  
	 * @param labels LIST OF DATA LABELS
	 */
	public SetupForm(String title, int labelWidh, boolean buttons, String ...labels) {
		this.labelwidth = labelWidh;
		setLayout(new BorderLayout());
		com.hmz.gui.Panel in_container = new com.hmz.gui.Panel();
		in_container.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lt = new JLabel(title);
		lt.setForeground(Color.YELLOW);
		lt.setFont(new Font("Calibri Light", Font.BOLD, 50));
		in_container.add(lt);
		add(in_container, BorderLayout.NORTH);
		
		container = new com.hmz.gui.Panel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		addFields(labels);
		add(container, BorderLayout.CENTER);
		
		setButtons(buttons);
	}
	
	// ADD SECTION ----------------------------------------------------------------------------------------------
	
	public void addActionListener(ActionListener listener) {
		bp.addActionListener(listener);
	}
	
	public void addFields(String ...labels) {
		for (String string : labels) {
			if(!string.contains(":")) string += " : ";
			com.hmz.gui.Panel c2 = new com.hmz.gui.Panel();
			c2.setLayout(new FlowLayout(FlowLayout.CENTER));
			JLabel l = new JLabel(string);
			l.setForeground(Color.WHITE);
			l.setPreferredSize(new Dimension(labelwidth, l.getPreferredSize().height));
			l.setFont(new Font("Calibri Light", Font.BOLD, 20));
			com.hmz.gui.TextField t = new TextField(10);
			t.setPreferredSize(new Dimension(l.getPreferredSize().width, 35));
			t.setFont(new Font("Calibri Light", Font.BOLD, 30));
			c2.add(l);
			c2.add(t);
			container.add(c2);
		}
	}
	
	// GET SECTION ----------------------------------------------------------------------------------------------
		
	public void setButtons(boolean buttons) {
		if (buttons) { 
			bp = new ButtonsPanel(FlowLayout.RIGHT, "Submit", "Back", "Quit");
			JButton b = (JButton)bp.getComponent(2);
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			add(bp, BorderLayout.SOUTH);
		} else {
			try {
				remove(bp);
			} catch (Exception e) {}
			bp = null;
		}
	}
	
	public void setText(String value, int index) {
		getTextF(index).setText(value);
	}
	
	public void setText(String value, String label) {
		getTextF(label).setText(value);
	}
	
	// GET SECTION ----------------------------------------------------------------------------------------------
	
	public JButton getButton(int index) {
		JButton ret = null;
		ButtonsPanel p = (ButtonsPanel)this.getComponent(0);
		ret = (JButton)p.getButton(index);
		return ret;
	}
	
	public JButton getButton(String name) {
		JButton ret = null;
		ButtonsPanel p = (ButtonsPanel)this.getComponent(0);
		ret = (JButton)p.getButton(name);
		return ret;
	}
	
	public com.hmz.gui.Panel getFieldPanel() {
		return container;
	}
	
	public ButtonsPanel getButtonsPanel() {
		return bp;
	}
	
	public JTextField getTextF(int index) {
		JTextField ret = null;
		com.hmz.gui.Panel p = (com.hmz.gui.Panel)container.getComponent(index);
		ret = (JTextField)p.getComponent(1);
		return ret;
	}
	
	public JTextField getTextF(String label) {
		JTextField ret = null;
		for(Component comp : container.getComponents()) {
			if(comp instanceof com.hmz.gui.Panel) {
				com.hmz.gui.Panel p = (Panel)comp;
				JLabel l = (JLabel)p.getComponent(0);
				if(l.getText().equals(label + " : ")) {
					ret = (JTextField)p.getComponent(1);
					break;
				}
			}
		}
		return ret;
	}
	
	
	
}
