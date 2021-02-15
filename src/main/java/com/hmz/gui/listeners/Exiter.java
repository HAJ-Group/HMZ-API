package com.hmz.gui.listeners;

import com.hmz.gui.Dialog;
import com.hmz.gui.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exiter implements ActionListener {
	
	public static final int UNKNOWN_FRAME_EXITED = 0;
	private Object frame;
	
	public Exiter() {
		this(null);
	}

	public Exiter(Object frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		if(frame instanceof Window) ((Window)frame).dispose();
		else if(frame instanceof JFrame) ((JFrame)frame).dispose();
		else if(frame instanceof Dialog) ((Dialog)frame).dispose();
		else if(frame instanceof JDialog) ((JDialog)frame).dispose();
		else System.exit(UNKNOWN_FRAME_EXITED);
	}

}
