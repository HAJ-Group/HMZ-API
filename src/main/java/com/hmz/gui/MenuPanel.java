package com.hmz.gui;

import com.hmz.gui.listeners.ColorHover;
import com.hmz.gui.listeners.Exiter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

/** PANEL WITH MULTIPLE CHOICES AS A MENU PAGE
 * 
 * @author hamza
 *
 */
public class MenuPanel extends com.hmz.gui.Panel {
	// BUILTED VERTICAL MENU PANEL 
	private static final long serialVersionUID = 1L;
	private com.hmz.gui.Panel container;
	private Vector<JButton> titlesL = new Vector<>();
	private JButton exit;
	
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------
	
	/** CUSTOMIZED CONSTRUCTOR
	 * 
	 * @param menuTitle MENU TITLE
	 * @param titles LIST OF OPTIONS
	 */
	public MenuPanel(String menuTitle, String ...titles) {
		container = new com.hmz.gui.Panel();
		container.setLayout(new BoxLayout(container,BoxLayout.PAGE_AXIS));
		
		addTitles(titles);
		
		JLabel menu = new JLabel(menuTitle);
		menu.setForeground(Color.YELLOW);
		menu.setFont(new Font("Calibri Light", Font.BOLD, 50));
		com.hmz.gui.Panel p = new com.hmz.gui.Panel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		com.hmz.gui.Panel ex = new com.hmz.gui.Panel();
		ex.setLayout(new BorderLayout());
		setExitButton();
		setExitAction();
		ex.add(exit, BorderLayout.SOUTH);
		
		p.add(menu);
		add(p);
		add(container);
		add(ex);
	}
	
	// ADD SECTION ----------------------------------------------------------------------------------------------
	
	public void addTitles(String ...titles) {
		for (int i = 0; i < titles.length; i++) {
			com.hmz.gui.Panel p = new Panel();
			p.setLayout(new FlowLayout(FlowLayout.CENTER));
			JButton key = new JButton(titles[i]);
			key.setBorder(BorderFactory.createEmptyBorder());
			key.setFont(new Font("Calibri Light", Font.BOLD, 20));
			key.setForeground(Color.WHITE);
			key.setBackground(getBackground());
		    key.setPreferredSize(new Dimension(650, key.getPreferredSize().height));
		    key.addMouseListener(new ColorHover(key, Color.YELLOW, key.getForeground()));
			p.add(key);
			titlesL.add(key);
			container.add(p);
		}
	}
	
	public void addActionListener(ActionListener listener) {
		for(JButton b : titlesL) {
			b.addActionListener(listener);
		}
	}
	
	public void addTitleActionListener(int index, ActionListener listener) {
		getTitle(index).addActionListener(listener);
	}
	
	public void addTitleActionListener(String title, ActionListener listener) {
		getTitle(title).addActionListener(listener);
	}
	
	// GET SECTION ----------------------------------------------------------------------------------------------
	
	public JButton getTitle(int index) {
		return titlesL.get(index);
	}
	
	public JButton getTitle(String name) {
		JButton ret = null;
		for(JButton b : titlesL) {
			if(b.getText().equals(name)) ret = b;
		}
		return ret;
	}
	
	public Vector<JButton> getTitles() {
		return titlesL;
	}
	
	public JButton getExit() {
		return exit;
	}
	
	// SET SECTION ----------------------------------------------------------------------------------------------

	public void setTitleValue(String value, int index) {
		getTitle(index).setText(value);
	}
	
	public void setExitButton() {
		exit = new JButton("QUIT");
		exit.setBorder(BorderFactory.createEmptyBorder());
		exit.setFont(new Font("Calibri Light", Font.BOLD, 20));
		exit.setForeground(Color.WHITE);
		exit.setBackground(getBackground());
	    exit.setPreferredSize(new Dimension(500, exit.getPreferredSize().height*5));
	    exit.addMouseListener(new ColorHover(exit, Color.YELLOW, exit.getForeground()));
	}
	
	public void setExitAction() {
		setExitAction(new Exiter());
	}
	
	public void setExitAction(ActionListener listener) {
		getExit().addActionListener(listener);
	}
}
