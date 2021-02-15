package com.hmz.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;


/** PANEL FOR SHWOING SHORT DATA ON A DIALOG OR FRAME
 * 
 * @author hamza
 *
 */
public class DataPrinter extends com.hmz.gui.Panel {
	// PANEL FOR SHOWING DATA ON A DIALOG OR A FRAME
	private static final long serialVersionUID = 1L;
	private Vector<JLabel> labels = new Vector<>();
	
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------
	
	public DataPrinter() {
		super();
	}
	
	/** CUSTOMIZED CONSTRUCTOR
	 * 
	 * @param dataTitle TITLE OF THE DATA
	 * @param titles LABELS OF EACH PRINTED DATA
	 * @param values PRINTED DATA
	 */
	public DataPrinter(String dataTitle, String[] titles, Object ...values) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JLabel lt = new JLabel(dataTitle);
		lt.setForeground(Color.YELLOW);
		lt.setFont(new Font("Calibri Light", Font.BOLD, 50));
		lt.setPreferredSize(new Dimension(500, lt.getPreferredSize().height));
		add(lt);
		
		addFields(titles, values);
	}
	
	// ADD SECTION ----------------------------------------------------------------------------------------------
	
	public void addFields(String[] titles, Object ...values) {
		if(titles.length == values.length) {
			for (int i = 0; i<titles.length; i++) {
				com.hmz.gui.Panel p = new Panel();
				p.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel l1 = new JLabel(titles[i]+ " : " + values[i]);
				labels.add(l1);
				l1.setFont(new Font("Calibri Light", Font.BOLD, 18));
				l1.setForeground(Color.white);
				l1.setPreferredSize(new Dimension(300, l1.getPreferredSize().height));
				l1.setHorizontalAlignment(JTextField.LEFT);
				p.add(l1);
				add(p);
			}
		}
	}
	
	// GET SECTION ----------------------------------------------------------------------------------------------
	
	public JLabel getField(int index) {
		return labels.get(index);
	}
	
	public JLabel getField(String name) {
		JLabel ret = null;
		for(JLabel l : labels) {
			if(l.getText().equals(name)) ret = l;
		}
		return ret;
	}
	
	public Vector<JLabel> getLabels() {
		return labels;
	}
	
	// SET SECTION ----------------------------------------------------------------------------------------------

	public void setFieldValue(String label, String value, int index) {
		getField(index).setText(label + " : " + value);
	}
}
