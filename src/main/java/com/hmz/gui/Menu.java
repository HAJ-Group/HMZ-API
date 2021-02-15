package com.hmz.gui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

/** PERSONALIZED JMENU OBJECT WITH MULTIPLE SERVICES
 * 
 * @author hamza
 *
 */
public class Menu extends JMenu {
	// CONFIGURATED JMENU COMPONENT
	private static final long serialVersionUID = 1L;
	
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------------
	
	/** DEFAULT JMENU CONSTRUCTOR */
	public Menu() {
		super();
	}
	
	/** TITLED JMENU CONSTRUCTOR
	 * @param menuTitle Title of the Menu */
	public Menu(String menuTitle) {
		super(menuTitle);
	}
	
	/** PERSONALIZED JMENU CONSTRUCTOR 
	 * @param menuTitle Title of the Menu
	 * @param items Items : Strings or Menus */
	public Menu(String menuTitle, Object ...items) {
		super(menuTitle);
		addItems(items);
	}
	
	// ADD SECTION ----------------------------------------------------------------------------------------------------
	
	/** ADDING NEW ITEM
	 * @param items Items : Strings or Menus */
	public void addItems(Object ...items) {
		for(Object item : items) {
			if(item.equals("-")) addSeparator();
			else {
				if(item instanceof Menu) add((Menu)item);
				else add(item.toString());
			}
		}
	}
	
	/** ADDING ACTION LISTENER ON ALL MENU ITEMS 
	 * @param listener Action Listener Object*/
	public void addActionListener(ActionListener listener) {
		for (int i = 0; i < getItemCount(); i++) {
			addMenuItemActionListener(i, listener);
		}
	}
	
	/** ADDING ACTION LISTENER ON A SPECIFIED MENU ITEM 
	 * @param index INDEX OF THE MENU ITEM
	 * @param listener Action Listener Object*/
	public void addMenuItemActionListener(int index, ActionListener listener) {
		if(getItem(index) != null && getItem(index) instanceof JMenuItem)
			getItem(index).addActionListener(listener);
	}
	
	public void addMenuItemActionListener(String itemName, ActionListener listener) {
		if(getItem(itemName) != null && getItem(itemName) instanceof JMenuItem)
			getItem(itemName).addActionListener(listener);
	}
	
	// GET SECTION ----------------------------------------------------------------------------------------------------

	/** GETTING MENU ITEM BY NAME
	 * @param itemName Name of the Item */
	public JMenuItem getItem(String itemName) {
		JMenuItem ret = null;
		for (int i = 0; i < getItemCount(); i++) {
			if(getItem(i) != null && getItem(i).getText().equals(itemName)) {
				ret = getItem(i);
			}
		}
		return ret;
	}
	
	/** GETTING ALL MENU ITEMS AS A LIST */
	public List<JMenuItem> getItems(){
		List<JMenuItem> ret = new Vector<>();
		for (int i = 0; i < getItemCount(); i++) {
			if(getItem(i) != null) {
				ret.add(getItem(i));
			}
		}
		return ret;
	}
	
	/** GETTING A SUB MENU INSIDE THE CURRENT MENU BY NAME
	 * @param menuName Name of the Sub Menu label */
	public Menu getSubmenu(String menuName) {
		Menu ret = null;
		if(getItem(menuName) instanceof Menu) {
			ret = (Menu)getItem(menuName);
		}
		return ret;
	}
	
	/** GETTING A SUB MENU INSIDE THE CURRENT MENU BY INDEX
	 * @param index Index of the Sub Menu label */
	public Menu getSubmenu(int index) {
		Menu ret = null;
		if(getItem(index) instanceof Menu) {
			ret = (Menu)getItem(index);
		}
		return ret;
	}
	
	/** GETTING ALL SUBMENUS AS A LIST */
	public List<Menu> getSubmenus(){
		List<Menu> ret = new Vector<>();
		for (int i = 0; i < getItemCount(); i++) {
			if(getSubmenu(i) != null) ret.add(getSubmenu(i));
		}
		return ret;
	}
	
	// SET SECTION ----------------------------------------------------------------------------------------------------

	/** SETTING ICONS FOR ALL ITEMS ASSOCIATED BY ITEM NAMES
	 * @param path Image Path
	 * @param suffix Image Extension */
	public void setIcons(String path, String suffix) {
		if(!suffix.contains(".")) suffix = "." + suffix.trim();
		for (int i = 0; i < getItemCount(); i++) {
			if(getItem(i) != null) {
				ImageIcon icon = new ImageIcon(path + getItem(i).getText() + suffix);
				getItem(i).setIcon(icon);
			}
		}
	}
	
	

}
