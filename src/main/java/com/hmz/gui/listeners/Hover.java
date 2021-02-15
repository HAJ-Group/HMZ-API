package com.hmz.gui.listeners;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Hover extends MouseAdapter {

	protected Component component;
	
	public Hover() {
		super();
	}
	
	public Hover(Component component) {
		this.component = component;
	}
	
	public abstract void mouseEntered(MouseEvent arg0);
	
	public abstract void mouseExited(MouseEvent arg0);

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

}
