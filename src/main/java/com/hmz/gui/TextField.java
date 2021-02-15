package com.hmz.gui;

import javax.swing.*;
import java.awt.*;

/** BOLDER JTEXTFIELD COMPONENT
 * 
 * @author hamza
 *
 */
public class TextField extends JTextField {
	// CUSTOMIZED JTEXTFIELD OBJECT
	private static final long serialVersionUID = 1L;
	
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------
	
	public TextField(int size) {
		super(size);
		setOpaque(false);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setFont(new Font("Calibri Light", Font.BOLD, 30));
		setHorizontalAlignment(JTextField.CENTER);
	}
	
	// FINAL SECTION ----------------------------------------------------------------------------------------------

	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

}
