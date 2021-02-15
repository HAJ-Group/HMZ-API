package com.hmz.gui.listeners;

import com.hmz.gui.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopMessage implements ActionListener {
	
	private String message;
	private Window window;
	
	public PopMessage(Window parent, String message) {
		this.window = parent;
		this.message = message;
	}

	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(window, message);
	}

}
