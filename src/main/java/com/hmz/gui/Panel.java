package com.hmz.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** CONFIGURATED JPANEL OBJECT ASSOCIATED WITH ALL HMZ COMPONENTS
 * 
 * @author hamza
 *
 */
public class Panel extends JPanel {
	// CONFIGURATED JPANEL COMPONENT
	private static final long serialVersionUID = 1L;
	private int index = 0;
	
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------
	
	// SETTING A DARK GRAY BACKGROUND
	public Panel() {
		super();
		setBackground(Color.DARK_GRAY);
	}
	
	/** CUSTOMIZED CONSTRUCTOR
	 * 
	 * @param components LIST OF COMPONENTS TO ADD
	 */
	public Panel(Component ...components) {
		this();
		for(Component c : components) add(c);
	}
	
	/** CUSTOMIZED CONSTRUCTOR
	 * 
	 * @param layout LAYOUT OF THE PANEL
	 * @param components LIST OF COMPONENTS TO ADD
	 */
	public Panel(LayoutManager layout, Component ...components) {
		this();
		setLayout(layout);
		for(Component c : components) add(c);
	}
	
	// FINAL SECTION ----------------------------------------------------------------------------------------------
	
	/** METHODE FOR SHOWING DIFFERENT LAYOUTS VIEW EXAMPLE
	 * @param layout_name NAME OF THE WANTED LAYOUT :
	 * <p>bl  = Border Layout</p>
	 * <p>gl  = Grid Layout</p>
	 * <p>bxl = Box Layout</p>
	 * <p>cl  = Card Layout</p>
	 * <p>fl  = Flow Layout</p>*/
	public void showLayouts(String layout_name) {
		switch (layout_name) {
		case "bl":
			borderL();
			break;
		case "gl" :
			gridL();
			break;
		case "bxl" :
			boxL();
			break;
		case "cl" :
			cardL();
			break;
		case "fl" :
			flowL();
			break;
		default:
			break;
		}
	}
	
	/** GRID LAYOUT DEMONSTRATION */
	void gridL() {
		setLayout(new GridLayout(3, 2));
		add(new JButton("1"));
		add(new JButton("2"));
		add(new JButton("3"));
		add(new JButton("4"));
		add(new JButton("5"));
	}
	
	/** BOX LAYOUT DEMONSTRATION */
	void boxL() {
		JPanel p1 = new JPanel(), p2 = new JPanel(), p3 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.LINE_AXIS));
		p1.add(new JButton("Button 1"));
		p2.setLayout(new BoxLayout(p2, BoxLayout.LINE_AXIS));
		p2.add(new JButton("Button 2"));
		p2.add(new JButton("Button 3"));
		p3.setLayout(new BoxLayout(p3, BoxLayout.LINE_AXIS));
		p3.add(new JButton("Button 4"));
		p3.add(new JButton("Button 5"));
		p3.add(new JButton("Button 6"));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(p1);
		add(p2);
		add(p3);
	}
	
	/** CARD LAYOUT DEMONSTRATION */
	void cardL() {
		final CardLayout cl = new CardLayout();
		final JPanel content = new JPanel();
		JPanel buttonPane = new JPanel();
		JPanel card1 = new JPanel();
		card1.setBackground(Color.BLUE);
		JPanel card2 = new JPanel();
		card2.setBackground(Color.RED);
		JPanel card3 = new JPanel();
		card3.setBackground(Color.GREEN);
		final String[] listContent = {"CARD_1", "CARD_2", "CARD_3"};
		JButton b1 = new JButton("Next Content");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.next(content);
			}
		});
		JButton b2 = new JButton("Content By Index");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(++index > 2) index = 0;
				cl.show(content, listContent[index]);
			}
		});
		buttonPane.add(b1);
		buttonPane.add(b2);
		content.setLayout(cl);
		content.add(card1, listContent[0]);
		content.add(card2, listContent[1]);
		content.add(card3, listContent[2]);
		setLayout(new BorderLayout());
		add(buttonPane, BorderLayout.NORTH);
		add(content, BorderLayout.CENTER);
	}
	
	/** FLOW LAYOUT DEMONSTRATION */
	void flowL() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(new JButton("Button 1"));
		add(new JButton("Button 2"));
		add(new JButton("Button 3"));
		add(new JButton("Button 4"));
		add(new JButton("Button 5"));
	}
	
	/** BORDER LAYOUT DEMONSTRATION */
	void borderL() {
		setLayout(new BorderLayout());
		add(new JButton("NORTH"), BorderLayout.NORTH);
		add(new JButton("SOUTH"), BorderLayout.SOUTH);
		add(new JButton("CENTER"), BorderLayout.CENTER);
		add(new JButton("EAST"), BorderLayout.EAST);
		add(new JButton("WEST"), BorderLayout.WEST);
	}

}
