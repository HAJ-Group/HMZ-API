package com.hmz.gui;

import javax.swing.*;

/** CONFIGURATED JDIALOG COMPONENET
 * 
 * @author hamza
 *
 */
public class Dialog extends JDialog {
	// CONFIGURATED JDIALOG COMPONENT
	private static final long serialVersionUID = 1L;
	
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------
	
	public Dialog() {
		super();
	}
	
	public Dialog(JFrame parent, boolean modal) {
		super(parent, modal);
	}
	
	// FINAL SECTION ----------------------------------------------------------------------------------------------
	
	public void config() {
		setTitle("HMZ Dialog");
		pack();
		setResizable(false);
		setLocationRelativeTo(getParent());
	}

}
