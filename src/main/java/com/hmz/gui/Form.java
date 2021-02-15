package com.hmz.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;

/** PANEL AS A CONSTRUCTOR OF MANY FORM COMPONENETS WITH MULTIPLE SERVICES
 * 
 * @author hamza
 *
 */
public class Form extends com.hmz.gui.Panel {
	
	private static final long serialVersionUID = 1L;
	private int labelwith = 120;
	protected com.hmz.gui.Panel container;
	protected ButtonsPanel buttonPanel;
	
	//CONSTRUCT SECTION---------------------------------------------------------------------------------------------------------------
	
	/** MAIN CONSTRUCTOR */
	public Form() {
		setLayout(new BorderLayout());
		com.hmz.gui.Panel pack = new com.hmz.gui.Panel();
		pack.setLayout(new FlowLayout(FlowLayout.LEFT));
		add("Center", pack);
		
		container = new Panel();
		pack.add(container);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
	}
	
	
	/** FIELD CONSTRUCTOR
	 * @param title ADDING A TITLED BORDER */
	public Form(String title) {
		this(title, Color.YELLOW);
	}
	
	/** FIELD CONSTRUCTOR
	 * 
	 * @param title
	 * @param borderColor
	 */
	public Form(String title, Color borderColor) {
		this();
		if(title != null) {
			if(!title.contains(":")) title += " : ";
			container.setBorder(BorderFactory.createTitledBorder(new LineBorder(borderColor), " " + title));
		}
		
	}
	
	/** FIELD CONSTRUCTOR
	 * @param labelwith LABEL WIDTH DATA */
	public Form(int labelwith) {
		this();
		if(labelwith != 0) this.labelwith = labelwith;
	}
	
	/** FIELDS CONSTRUCTOR 
	 * @param title ADDING A TITLED BORDER
	 * @param labelwith LABEL WIDTH DATA
	 * */
	public Form(String title, int labelwith) {
		this(title);
		if(labelwith != 0) this.labelwith = labelwith;
	}
	
	/** FIELDS CONSTRUCTOR
	 * 
	 * @param title
	 * @param labelwith
	 * @param borderColor
	 */
	public Form(String title, int labelwith, Color borderColor) {
		this(title, borderColor);
		if(labelwith != 0) this.labelwith = labelwith;
	}
	
	/**
	 * 
	 * @param components LIST OF COMPONENTS TO ADD
	 */
	public Form(Component ...components) {
		this();
		addFormComponents(components);
	}
	
	 /**
	  * 
	  * @param title title ADDING A TITLED BORDER
	  * @param components LIST OF COMPONENTS TO ADD
	  */
	public Form(String title, Component ...components) {
		this(title);
		addFormComponents(components);
	}
	
	/**
	 * 
	 * @param labelwidth LABEL WIDTH DATA
	 * @param components LIST OF COMPONENTS TO ADD
	 */
	public Form(int labelwidth, Component ...components) {
		this(labelwidth);
		addFormComponents(components);
	}
	
	/**
	 * 
	 * @param title ADDING A TITLED BORDER
	 * @param labelwidth LABEL WIDTH DATA
	 * @param components LIST OF COMPONENTS TO ADD
	 */
	public Form(String title, int labelwidth, Component ...components) {
		this(title, labelwidth);
		addFormComponents(components);
	}
	
	
	
	//ADD SECTION--------------------------------------------------------------------------------------------------------------------
	
	/** ADDING FORM COMPONENTS
	 * 
	 * @param components LIST OF COMPONENTS TO ADD
	 */
	public void addFormComponents(Component ...components) {
		for(Component c : components) container.add(c);
	}
	
	/** ADDING JSEPARATOR OBJECT */
	public void addSeparator() {
		container.add(new JSeparator());
	}
	
	/** ADDING LABELED TEXT FIELD 
	 * @param label LABEL TEXT
	 * @param size TEXTFIELD CHARACTERS SIZE*/
	public void addLabeledTextField(String label, int size) {
		LabeledTextField lt1 = new LabeledTextField(label, size, labelwith);
		container.add(lt1);
	}
	
	/** ADDING LABELED TEXT AREA 
	 * @param label LABEL TEXT
	 * @param size TEXTFIELD CHARACTERS SIZE*/
	public void addLabeledTextArea(String label, int rows, int cols) {
		LabeledTextArea lta = new LabeledTextArea(label, rows, cols, labelwith);
		container.add(lta);
	}
	
	/** ADDING CHOICEFIELD RADIO BUTTONS BASED
	 * @param label LABEL TEXT
	 * @param choices LIST OF CHOICES*/
	public void addChoiceField(String label, String ...choices) {
		ChoicePanel cp1 = new ChoicePanel(label, labelwith, choices);
		container.add(cp1);
	}
	
	/** ADDING MUTI CHOICEFIELD CHECKBOXES BASED
	 * @param label LABEL TEXT
	 * @param choices LIST OF CHOICES*/
	public void addMultiChoiceField(String label, String ...choices) {
		MultiChoicePanel mcp1 = new MultiChoicePanel(label, labelwith, choices);
		container.add(mcp1);
	}
	
	/** ADDING SELECTFIELD
	 * @param label LABEL TEXT
	 * @param values LIST OF VALUES */
	public void addSelectField(String label, String ...values) {
		SelectPanel sp1 = new SelectPanel(label, values);
		container.add(sp1);
	}
	
	/** ADDING BUTTONS
	 * 
	 * @param buttons
	 */
	public void addButtons(String ...buttons) {
		buttonPanel.addButtons(buttons);
	}
	
	/** ADDING ACTION LISTENER FOR FORM BUTTONS
	 * @param listener ACTION LISTENER OBJECT */
	public void addActionListener(ActionListener listener) {
		buttonPanel.addActionListener(listener);
	}
	
	//SET SECTION--------------------------------------------------------------------------------------------------------------------
	
	/** SETTING THE LABEL WIDTH
	 * @param labelwith LABEL WIDTH DATA */
	public void setLabelwith(int labelwith) {
		this.labelwith = labelwith;
	}
	
	/** SETTING BUTTONS FOR FORM
	 * @param buttons LIST OF BUTTONS TEXT */
	public void setButtons(String ...buttons) {
		if(this.buttonPanel == null) {
			buttonPanel = new ButtonsPanel(buttons);
			add("South", buttonPanel);
		}
	}
	
	/** SETTING BUTTONS FOR FORM
	 * 
	 * @param buttonPanel
	 */
	public void setButtons(ButtonsPanel buttonPanel) {
		if(this.buttonPanel == null) {
			this.buttonPanel = buttonPanel;
			add("South", this.buttonPanel);
		}
	}
	
	/** SETTING FIELD VALUE 
	 * @param index INDEX OF THE FORM COMPONENT
	 * @param value VALUE */
	public void setValue(int index, Object value) {
		if(getFormComponent(index) instanceof LabeledTextField) {
			LabeledTextField f = (LabeledTextField)container.getComponent(index);
			f.setValue(value);
		}
		if(getFormComponent(index) instanceof LabeledTextArea) {
			LabeledTextArea f = (LabeledTextArea)container.getComponent(index);
			f.setValue(value);
		}
		if(getFormComponent(index) instanceof ChoicePanel) {
			ChoicePanel f = (ChoicePanel)container.getComponent(index);
			f.setCheckedValue(value);
		}
		if(getFormComponent(index) instanceof MultiChoicePanel) {
			MultiChoicePanel f = (MultiChoicePanel)container.getComponent(index);
			f.setCheckedValues(value);
		}
		if(getFormComponent(index) instanceof SelectPanel) {
			SelectPanel f = (SelectPanel)container.getComponent(index);
			f.setSelectedValues(value);
		}
	}
	
	/** SETTING FIELD MULTIPLE VALUES
	 * @param index INDEX OF THE FORM COMPONENT
	 * @param values VALUES */
	public void setValues(int index, Object ...values) {
		if(getFormComponent(index) instanceof SelectPanel) {
			SelectPanel f = (SelectPanel)container.getComponent(index);
			f.setSelectedValues(values);
		}
		if(getFormComponent(index) instanceof MultiChoicePanel) {
			MultiChoicePanel f = (MultiChoicePanel)container.getComponent(index);
			f.setCheckedValues(values);
		}
	}
	
	//GET SECTION--------------------------------------------------------------------------------------------------------------------
	
	/** GETTING THE FORM COMPONENT
	 * @param index INDEX OF THE FORM COMPONENT */
	public Component getFormComponent(int index) {
		return container.getComponent(index);
	}
	
	/** GETTING FIELD VALUE
	 * @param index INDEX OF THE FORM COMPONENT */
	public String getValue(int index) {
		String ret = null;
		if(getFormComponent(index) instanceof LabeledTextField) {
			LabeledTextField f = (LabeledTextField)container.getComponent(index);
			ret = f.getValue();
		}
		if(getFormComponent(index) instanceof LabeledTextArea) {
			LabeledTextArea f = (LabeledTextArea)container.getComponent(index);
			ret = f.getValue();
		}
		if(getFormComponent(index) instanceof ChoicePanel) {
			ChoicePanel f = (ChoicePanel)container.getComponent(index);
			ret = f.getCheckedValue();
		}
		if(getFormComponent(index) instanceof MultiChoicePanel) {
			MultiChoicePanel f = (MultiChoicePanel)container.getComponent(index);
			ret = f.getCheckedValues().get(0);
		}
		if(getFormComponent(index) instanceof SelectPanel) {
			SelectPanel f = (SelectPanel)container.getComponent(index);
			ret = f.getSelectedValues().get(0);
		}
		return ret;
	}
	
	/** GETTING FIELD MULTIPLE VALUES
	 * @param index INDEX OF THE FORM COMPONENT */
	public List<String> getValues(int index) {
		List<String> ret = null;
		if(getFormComponent(index) instanceof SelectPanel) {
			SelectPanel f = (SelectPanel)container.getComponent(index);
			ret = f.getSelectedValues();
		}
		if(getFormComponent(index) instanceof MultiChoicePanel) {
			MultiChoicePanel f = (MultiChoicePanel)container.getComponent(index);
			ret = f.getCheckedValues();
		}
		return ret;
	}
	
	/** GETTING FIELD VALUE AS AN INTEGER
	 * @param index INDEX OF THE FORM COMPONENT */
	public int getIntValue(int index) throws Exception {
		int ret = 0;
		try {
			ret = Integer.parseInt(getValue(index));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return ret;
	}
	
	/** GETTING FIELD VALUE AS A DOUBLE
	 * @param index INDEX OF THE FORM COMPONENT */
	public double getDoubleValue(int index) throws Exception {
		double ret = 0;
		try {
			ret = Double.parseDouble(getValue(index));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return ret;
	}
	
	/** GETTING THE NUMBER OF FORM COMPONENTS */
	public int getFormComponentsCount() {
		return container.getComponentCount();
	}
	
	//FINAL SECTION--------------------------------------------------------------------------------------------------------------------
	
	/** DISABLING FORM COMPONENT
	 * @param index INDEX OF THE FORM COMPONENT */
	public void disableField(int index) {
		toggleField(index, false);
	}
	
	/** ENABLING FORM COMPONENT
	 * @param index INDEX OF THE FORM COMPONENT */
	public void enableField(int index) {
		toggleField(index, true);
	}
	
	/** TOGGLING FORM COMPONENT
	 * @param index INDEX OF THE FORM COMPONENT */
	public void toggleField(int index, boolean enable) {
		if(getFormComponent(index) instanceof LabeledTextField) {
			LabeledTextField f = (LabeledTextField)container.getComponent(index);
			JTextField tf = f.getTextfield();
			tf.setEnabled(enable);
		}
		if(getFormComponent(index) instanceof LabeledTextArea) {
			LabeledTextArea f = (LabeledTextArea)container.getComponent(index);
			JTextArea tf = f.getTextarea();
			tf.setEnabled(enable);
		}
		if(getFormComponent(index) instanceof ChoicePanel) {
			ChoicePanel f = (ChoicePanel)container.getComponent(index);
			ButtonGroup tf = f.getGroup();
			Enumeration<AbstractButton> buttons = tf.getElements();
			while(buttons.hasMoreElements()) {
				buttons.nextElement().setEnabled(enable);
			}
		}
		if(getFormComponent(index) instanceof MultiChoicePanel) {
			MultiChoicePanel f = (MultiChoicePanel)container.getComponent(index);
			List<JCheckBox> tf = f.getCheckboxes();
			for(JCheckBox cb : tf) {
				cb.setEnabled(enable);
			}
		}
		if(getFormComponent(index) instanceof SelectPanel) {
			SelectPanel f = (SelectPanel)container.getComponent(index);
			JList<String> tf = f.getList();
			tf.setEnabled(enable);
		}
	}
	
	
	
}
