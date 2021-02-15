package com.hmz.gui.listeners;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ColorHover extends Hover {

	private Color enterFg = null;
	private Color exitFg = null;
	private Color enterBg = null;
	private Color exitBg = null;
	
	public ColorHover() {
		super();
	}
	
	public ColorHover(Component component, Color enter, Color exit) {
		this(component, enter, exit, true);
	}
	
	public ColorHover(Component component, Color enter, Color exit, boolean foreground) {
		super(component);
		if(foreground) {
			this.enterFg = enter;
			this.exitFg = exit;
		} else {
			this.enterBg = enter;
			this.exitBg = exit;
		}
	}
	
	public ColorHover(Component component, Color enterFg, Color exitFg, Color enterBg, Color exitBg) {
		super(component);
		this.enterFg = enterFg;
		this.exitFg = exitFg;
		this.enterBg = enterBg;
		this.exitBg = exitBg;
	}

	public void mouseEntered(MouseEvent e) {
		if(enterFg != null) component.setForeground(enterFg);
		if(enterBg != null) component.setBackground(enterBg);
	}

	public void mouseExited(MouseEvent e) {
		if(exitFg != null) component.setForeground(exitFg);
		if(exitBg != null) component.setBackground(exitBg);
	}

}
