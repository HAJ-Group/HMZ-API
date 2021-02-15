package com.hmz.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** CONFIGURATED JFRAME OBJECT AS THE MAIN HMZ COMPOENENT
 * 
 * @author hamza
 *
 */
public class Window extends JFrame {
	//CONFIGURATED JFRAME COMPONENT
	private static final long serialVersionUID = 1L;
	
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------
	
	// MAIN CONSTRUCT FOR RUNNING TESTS
	public Window() {
		super();
	}
	
	// FINAL SECTION ----------------------------------------------------------------------------------------------
	
	/**
	 * DEMONSTRATION OF ALL CUSTOMIZED COMPONENTS
	 */
	public void demonstrate() {
		MenuBar bar = new MenuBar("Panels", "Form Components", "Other");
		
		// PANELS : BASED ON INITIALIZING OF DATA ONCE
		Menu panels = bar.getMenu(0);
		panels.addItems(
				"BUTTONS PANEL",
				"IMAGE PANEL",
				"MENU PANEL",
				"SEARCH PANEL",
				"-",
				"DATA PRINTER",
				"SETUP FORM"
		);
		
		final ButtonsPanel bp = new ButtonsPanel("Button 1", "Button 2", "Button 3");
		final ImagePanel ip = new ImagePanel("C:\\Users\\hamza\\Pictures\\121648.jpg");
		final MenuPanel mp = new MenuPanel("HMZ MENU", "OPTION 1", "OPTION 2", "OPTION 3");
		final SearchPanel srp = new SearchPanel("DESCRIPTION");
		final DataPrinter dp = new DataPrinter("HMZ DATA", new String[] {"LABEL 1", "LABEL 2", "LABEL 3"},  "DATA 1", "DATA 2", "DATA 3");
		final SetupForm sf = new SetupForm("HMZ SETUP", 100, "LABEL 1", "LABEL 2", "LABEL 3");
		
		panels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String action = ((JMenuItem)e.getSource()).getText();
				System.out.println(action);
				switch (action) {
					case "BUTTONS PANEL":
						Dialog d = new Dialog();
						d.setContentPane(bp);
						d.config();
						d.setVisible(true);
						break;
						
					case "IMAGE PANEL":
						d = new Dialog();
						d.setContentPane(ip);
						d.setSize(800, 600);
						d.setVisible(true);
						break;
						
					case "MENU PANEL":
						d = new Dialog();
						d.setContentPane(mp);
						d.setSize(400, 500);
						d.setVisible(true);
						break;
						
					case "SEARCH PANEL":
						d = new Dialog();
						d.setContentPane(srp);
						d.config();
						d.setVisible(true);
						break;
						
					case "DATA PRINTER":
						d = new Dialog();
						d.setContentPane(dp);
						d.config();
						d.setVisible(true);
						break;
						
					default:
						d = new Dialog();
						d.setContentPane(sf);
						d.config();
						d.setVisible(true);
						break;
					}
			}
		});
		
		// FORM COMPONENETS : BASED ON FLEXIBLE CHANGE OF DATA
		Menu formComponents = bar.getMenu(1);
		formComponents.addItems(
				"Labeled Text Field", 
				"Labeled Text Area", 
				"-", 
				"Choice Panel", 
				"Multiple Choice Panel", 
				"-",
				"Select Panel", 
				"-",
				"Form"
		);
		
		// INITIALIZE FORM COMPONENTS OBJECTS
		final LabeledTextField ltf = new LabeledTextField("LABEL", 20);
		final LabeledTextArea lta = new LabeledTextArea("LABEL", 4, 20);
		final ChoicePanel cp = new ChoicePanel("LABEL", "CHOICE 1", "CHOICE 2", "CHOICE 3");
		final MultiChoicePanel mcp = new MultiChoicePanel("LABEL", "CHOICE 1", "CHOICE 2", "CHOICE 3");
		final SelectPanel sp = new SelectPanel("LABEL", "OPTION 1", "OPTION 2", "OPTION 3");
		final Form form = new Form("HMZ FORM");
		
		formComponents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String action = ((JMenuItem)e.getSource()).getText();
				System.out.println(action);
				switch (action) {
					case "Labeled Text Field":
						ltf.setValue("Write Your Text Here");
						Dialog d = new Dialog();
						d.setContentPane(ltf);
						d.config();
						d.setVisible(true);
						break;
						
					case "Labeled Text Area":
						lta.setValue("Write Your Text Here");
						d = new Dialog();
						d.setContentPane(lta);
						d.config();
						d.setVisible(true);
						break;
						
					case "Choice Panel":
						cp.setCheckedValue("CHOICE 1");
						d = new Dialog();
						d.setContentPane(cp);
						d.config();
						d.setVisible(true);
						break;
						
					case "Multiple Choice Panel":
						mcp.setCheckedValues("CHOICE 1", "CHOICE 2"); 
						d = new Dialog();
						d.setContentPane(mcp);
						d.config();
						d.setVisible(true);
						break;
						
					case "Select Panel":
						sp.setSelectedValues("OPTION 1", "OPTION 2");
						d = new Dialog();
						d.setContentPane(sp);
						d.config();
						d.setVisible(true);
						break;
						
					default:
						form.addFormComponents(ltf, lta, cp, mcp, sp);
						form.setValue(0, "Write Your Text Here");
						form.setValue(1, "Write Your Text Here");
						form.setValue(2, "CHOICE 1");
						form.setValues(3, "CHOICE 1", "CHOICE 2");
						form.setValues(4, "OPTION 1", "OPTION 2");
						d = new Dialog();
						d.setContentPane(form);
						d.config();
						d.setVisible(true);
						break;
					}
			}
		});
		
		//OTHER
		Menu other = bar.getMenu("Other");
		other.addItems("Exit");
		other.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		JLabel label = new JLabel("HMZ GUI COMPONENTS");
		label.setFont(new Font("Calibri Light", Font.BOLD, 50));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.YELLOW);
		label.setPreferredSize(new Dimension(label.getPreferredSize().width*2, label.getPreferredSize().height*3));
		
		bar.setIcons();
		setContentPane(new Panel(label));
		setJMenuBar(bar);
		config();
	}
	
	/** WINDOW CONFIGURATION */
	public void config() {
		setTitle("HMZ Window");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	void test1() {
		Panel p = new Panel();
		p.showLayouts("gl");
		setContentPane(p);
	}
	
	void test2() {
		Panel p = new Panel();
		p.setLayout(new BorderLayout());
		ButtonsPanel bp = new ButtonsPanel(FlowLayout.CENTER, Color.DARK_GRAY, Color.YELLOW, "Next", "Back", "Exit");
		p.add(bp, BorderLayout.SOUTH);
		setContentPane(p);
	}
	
	void test3() {
		final SetupForm s = new SetupForm("SETUP FORM", 100, "Name", "Age", "Date");
		s.getButton(0).addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("DATA :");
				System.out.println(s.getTextF(0).getText());
				System.out.println(s.getTextF(1).getText());
				System.out.println(s.getTextF(2).getText());
			}
			
		});
		setContentPane(s);
	}
	
	void test4() {
		MenuPanel m = new MenuPanel("MENU PANEL", "BUTTON 1", "BUTTON 2", "BUTTON 3", "BUTTON 4", "BUTTON 5", "BUTTON 5", "BUTTON 5");
		setContentPane(m);
	}
	
	void test5() {
		SearchPanel s = new SearchPanel("Enter Value");
		setContentPane(s);
	}
	
	void test6() {
		ImagePanel imp = new ImagePanel("C:\\Users\\hamza\\Pictures\\121596.jpg");
		setContentPane(imp);
	}
	
	void test7() {
		JPanel container = new JPanel();
		ImageIcon image = new ImageIcon("blassa fin limage kayna");
		JLabel pic_label = new JLabel(image);
		container.add(pic_label);
		// WHAD LCONTAINER AJOUTIH FLOWEL DIAL LPANE PRINCIPALE LI 3NDEK WSAF
	}
	
	void test8() {
		final Form form = new Form("My Form");
		form.addLabeledTextField("Name", 20);
		form.addChoiceField("Gender", "Male", "Female");
		form.addLabeledTextArea("Adresse", 3, 20);
		form.addSelectField("How Many Languages you speak ?", "French", "English", "Arabic", "German", "Spanish");
		form.addMultiChoiceField("How many grams ?", "1g", "2g", "3g");
		form.setButtons("Validate", "Disable", "Enable", "Cancel");
		
		form.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String action = ((JButton)arg0.getSource()).getText();
				switch (action) {
				case "Validate":
					System.out.println("User Infos : ");
					System.out.println("Your name is " + form.getValue(0));
					System.out.println("Your gender is " + form.getValue(1));
					System.out.println("Your adresse is " + form.getValue(2));
					System.out.print("The Languages You speak are :");
					for(String lan : form.getValues(3)) {
						System.out.print(lan + ", ");
					}
					System.out.print("\nThe grams are : ");
					for (String gram : form.getValues(4)) {
						System.out.print(gram + ", ");
					}
					break;
				
				case "Disable":
					for (int i = 0; i < form.getFormComponentsCount(); i++) {
						form.disableField(i);
					}
					break;
					
				case "Enable":
					for (int i = 0; i < form.getFormComponentsCount(); i++) {
						form.enableField(i);
					}
					break;
				default:
					dispose();
					break;
				}
			}
		});
		setContentPane(form);
	}
	
	void test9() {
		String [][] bars = {
				{"file", "New", "Open", "Save", "-", "Quit"},
				{"edit"},
				{"refactor"},
				{"navidate"}
		};
		MenuBar menu = new MenuBar(bars);
		//menu.addSubMenu("file", menu.buildMenu("Manage", "Add", "Remove"));
		//ACTIONS
		for(Component c: menu.getItems("file")) {
			if(c instanceof JMenuItem) {
				JMenuItem item = (JMenuItem)c;
				item.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String action = ((JMenuItem)e.getSource()).getText();
						switch (action) {
						case "New":
							System.out.println(action);
							break;
						case "Open":
							System.out.println(action);
							break;
						case "Save":
							System.out.println(action);
							break;
						
						default:
							dispose();
							break;
						}
					}
				});
			}
			if(c instanceof JMenu) {
				JMenu men = (JMenu)c;
				for (int i = 0; i < men.getItemCount(); i++) {
					System.out.println(men.getItem(i).getText());
				}
			}
		}
		setJMenuBar(menu);
	}
	
	void test10() {
		MenuBar menu = new MenuBar("file", "edit", "refactor", "navigate");
		menu.addSubItems("file", "New", "Open", "Save", "-", new Menu("Manage", "Add", "Remove"), "Quit");
		// ACTIONS
		menu.setIcons();
		Menu manage = menu.getSubmenu("file", "Manage");
		manage.add(new Menu("Others", "sort", "start"));
		for(Menu sm : manage.getSubmenus()) {
			System.out.println(sm.getText());
		}
		setJMenuBar(menu);
	}
	
	void test11() {
		MenuBar bar = new MenuBar("File", "Edit", "Source", "Window");
		Menu file = bar.getMenu("File");
		file.addItems("New", "Open", "Save", "Exit");
		// ...
		Menu window = bar.getMenu("Window");
		window.addItems	(
				"New Window", 
				new Menu("Editor", "Clone", "Toggle"),
				new Menu("Appearance", "Show", "Hide"),
				"-",
				new Menu("Show View", "Console", "Ant"),
				new Menu("Perspective", new Menu("Open Perpective", "Debig"), "-", "Customize"),
				"-",
				new Menu("Navigation", "Next View"),
				"Preferences"
		);
		// FILE ACTIONS
		file.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String action = ((JMenuItem)arg0.getSource()).getText();
				if(action.equals("Exit")) dispose();
				
			}
		});
		bar.setIcons();
		setJMenuBar(bar);
	}
	
	void test16() {
		
		MenuBar menu = new MenuBar();
		
		Menu file = new Menu("file", "Open", "New", "Save", "Exit");
		Menu edit = new Menu("edit", "heho", new Menu("Perspective", new Menu("Open Perspective", "Open", "Close")));
		
		menu.addMenus(file, edit);
		menu.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(((JMenuItem)arg0.getSource()).getText());
				
			}
		});
		
		
		setJMenuBar(menu);
		config();
	}
	
	// MAIN SECTION ----------------------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		new Window();
	}
}
