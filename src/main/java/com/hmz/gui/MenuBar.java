package com.hmz.gui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

/** PERSONALIZED JMENBAR OBJECT ASSOCIATED WITH MENU SERVICES
 * 
 * @author hamza
 *
 */
public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private String path = "resources/icons/";
	private String suffix = ".png";
	private Vector<Menu> menusL = new Vector<>();
	
	// CONSTRUCT SECTION--------------------------------------------------------------------------------------------------------
	
	/** DEFAULT MENU BAR CONSTUCTOR
	 * 
	 */
	public MenuBar() {
		super();
	}
	
	/** MENU CONSTRUCTOR USING TWO DIMENSION TABLE FOR BUILDING 2 LEVELS MENU
	 * @param bars BARS AND ITEMS TWO DIMENSION LIST OF DATA */
	public MenuBar(String [][] bars) {
		for (int i = 0; i < bars.length; i++) {
			addMenu(bars[i]);
		}
	}
	
	/** MENU CONSTRUCTOR USING LIST OF TOP BARS FOR BUILDING 1 LEVEL MENU
	 * 
	 * @param topbars List of Menu Bars Labels
	 */
	public MenuBar(String ...topbars) {
		for(String s: topbars) {
			Menu m = new Menu(s);
			add(m);
			menusL.add(m);
		}
	}
	
	/** MENU CONSTRUCTOR USING LIST OF MENUS
	 * 
	 * @param menus LIST OF MENUS
	 */
	public MenuBar(Menu ...menus) {
		addMenus(menus);
	}
	
	// ADD SECTION--------------------------------------------------------------------------------------------------------
	
	/** ADDING A NEW 2 LEVELS MENU TO THE MENUBAR USING A LIST OF DATA
	 *  
	 * @param t LIST OF DATA CONTAINS THE MENU BAR LABEL NAME AS THE FIRST LIST ELEMENT AND THE LIST OF ITEMS 
	 */
	public void addMenu(String t[]) {
		Object [] items = new String[t.length - 1];
		for (int i = 1, j = 0; i < t.length; i++, j++) {
			items[j] = t[i]; 
		}
		Menu m = new Menu(t[0], items);
		add(m);
		menusL.add(m);
	}
	
	/** ADDING A NEW 2 LEVELS MENU TO THE MENUBAR USING A LIST OF DATA 
	 * 
	 * @param menuTitle MENU BAR LABEL NAME
	 * @param items LIST OF ITEMS
	 */
	public void addMenu(String menuTitle, Object ...items) {
		add(new Menu(menuTitle, items));
		menusL.add(new Menu(menuTitle, items));
	}
	
	public void addMenus(Menu ...menus) {
		for(Menu m : menus) {
			add(m);
			menusL.add(m);
		}
	}
	
	/** ADDING ITEMS TO A SPECIFIED MENU BY NAME
	 * 
	 * @param parent NAME OF THE PARENT MENU
	 * @param items LIST OF ITEMS : MENU ITEMS OR MENUS
	 */
	public void addSubItems(String parent, Object ...items) {
		Menu m = getMenu(parent);
		m.addItems(items);
	}
	
	/** ADD ACTION LISTENER TO ALL MENUS
	 * 
	 * @param index INDEX OF THE MENU
	 */
	public void addActionListener(ActionListener listener) {
		for (int i = 0; i < getMenuCount(); i++) {
			addMenuActionListener(i, listener);
		}
	}
	
	/** ADD ACTION LISTENER TO SPECIFIED MENU
	 * 
	 * @param index INDEX OF THE MENU
	 * @param listener THE LISTENER OBJECT
	 */
	public void addMenuActionListener(int index, ActionListener listener) {
		getMenu(index).addActionListener(listener);
	}
	
	public void addMenuActionListener(String name, ActionListener listener) {
		getMenu(name).addActionListener(listener);
	}
	
	// GET SECTION--------------------------------------------------------------------------------------------------------
	
	/** GETTING MENU BY INDEX
	 * 
	 * @param index INDEX OF THE WANTED MENU
	 * @return MENU OBJECT
	 */
	public Menu getMenu(int index) {
		return menusL.get(index);
	}
	
	/** GETTING MENU BY NAME
	 * 
	 * @param name NAME OF THE WANTED MENU
	 * @return MENU OBJECT
	 */
	public Menu getMenu(String name) {
		Menu ret = null;
		for (int i = 0; i < getMenuCount(); i++) {
			if(getMenu(i).getText().equals(name)) ret = getMenu(i);
		}
		return ret;
	}
	
	/** GETTING ITEM BY NAME FROM A SPECIFIED MENU BY NAME
	 * 
	 * @param menuName NAME OF THE WANTED MENU
	 * @param itemName NAME OF THE WANTED ITEM
	 * @return JMENUITEM OBJECT
	 */
	public JMenuItem getItem(String menuName, String itemName) {
		return getMenu(menuName).getItem(itemName);
	}
	
	/** GETTING ITEM BY NAME FROM A SPECIFIED MENU BY INDEX
	 * 
	 * @param menuName NAME OF THE WANTED MENU
	 * @param index INDEX OF THE WANTED ITEM
	 * @return JMENUITEM OBJECT
	 */
	public JMenuItem getItem(String menuName, int index) {
		return getMenu(menuName).getItem(index);
	}
	
	/** GETTING ITEM BY INDEX FROM A SPECIFIED MENU BY INDEX
	 * 
	 * @param menuIndex INDEX OF THE WANTED MENU
	 * @param itemIndex INDEX OF THE WANTED ITEM
	 * @return JMENUITEM OBJECT
	 */
	public JMenuItem getItem(int menuIndex, int itemIndex) {
		return getMenu(menuIndex).getItem(itemIndex);
	}
	
	/** GETTING LIST OF ALL THE SPECIFIED MENU ITEM BY NAME
	 * 
	 * @param menuName NAME OF THE WANTED MENU
	 * @return LIST OF JMENUITEM OBJECT
	 */
	public List<JMenuItem> getItems(String menuName){
		return getMenu(menuName).getItems();
	}
	
	/** GETTING LIST OF ALL THE SPECIFIED MENU ITEM BY INDEX
	 * 
	 * @param index INDEX OF THE WANTED MENU
	 * @return LIST OF JMENUITEM OBJECT
	 */
	public List<JMenuItem> getItems(int index){
		return getMenu(index).getItems();
	}
	
	/** GETTING SUBMENU BY NAME OF THE SPECIFIED MENU ITEM BY NAME (JUST LEVEL 1 BARS)
	 * 
	 * @param parent NAME OF THE WANTED MENU
	 * @param submenuName NAME OF THE WANTED SUBMENU
	 * @return MENU OBJECT
	 */
	public Menu getSubmenu(String parent, String submenuName) {
		return getMenu(parent).getSubmenu(submenuName);
	}
	
	/** GETTING SUBMENU BY NAME OF THE SPECIFIED MENU ITEM BY INDEX (JUST LEVEL 1 BARS)
	 * 
	 * @param parent NAME OF THE WANTED MENU
	 * @param index INDEX OF THE WANTED SUBMENU
	 * @return MENU OBJECT
	 */
	public Menu getSubmenu(String parent, int index) {
		return getMenu(parent).getSubmenu(index);
	}
	
	/** GETTING SUBMENU BY INDEX OF THE SPECIFIED MENU ITEM BY INDEX (JUST LEVEL 1 BARS)
	 * 
	 * @param menuIndex NAME OF THE WANTED MENU
	 * @param submenuIndex INDEX OF THE WANTED SUBMENU
	 * @return MENU OBJECT
	 */
	public Menu getSubmenu(int menuIndex, int submenuIndex) {
		return getMenu(menuIndex).getSubmenu(submenuIndex);
	}
	
	/** GETTING ALL SUBMENUS OF THE SPECIFIED MENU BY NAME
	 * 
	 * @param parent NAME OF THE WANTED MENU
	 * @return LIST OF MENU OBJECT
	 */
	public List<Menu> getSubmenus(String parent){
		return getMenu(parent).getSubmenus();
	}
	
	/** GETTING ALL SUBMENUS OF THE SPECIFIED MENU BY INDEX
	 * 
	 * @param index INDEX OF THE WANTED MENU
	 * @return LIST OF MENU OBJECT
	 */
	public List<Menu> getSubmenus(int index){
		return getMenu(index).getSubmenus();
	}
	
	/** GETTING ALL MENUS OF THE CURRENT MENU BAR (JUST 1 LEVEL)
	 * 
	 * @return VECTOR OF MENU OBJECT
	 */
	public Vector<Menu> getMenus() {
		return menusL;
	}
	
	// SET SECTION--------------------------------------------------------------------------------------------------------
	
	/** SETTING ICONS ASSOCIATED WITH EACH MENU ON THE CURRENT MENU BAR (JUST 1 LEVEL)
	 * 
	 */
	public void setIcons() {
		for (int i = 0; i < getMenuCount(); i++) {
			getMenu(i).setIcons(path, suffix);
		}
	}
	

}
