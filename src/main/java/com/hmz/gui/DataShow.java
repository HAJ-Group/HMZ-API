package com.hmz.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/** PANEL FOR PRINTING LONG TYPE OF TEXT DATA
 * 
 * @author hamza
 *
 */
public class DataShow extends DataPrinter {

	private static final long serialVersionUID = 1L;
	
	private JTable dataContainer;
	private DefaultTableModel model;
	private Object [][] data, buffer;
	private boolean titled = false;
	
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------
	
	public DataShow(Object... values) {
		this(null, values);
	}
	
	public DataShow(String[] titles, Object... values) {
		titled = (titles != null);
		model = new DefaultTableModel();
		addFields(titles, values);
		dataContainer = new JTable(model);
		setPane();
	}
	
	public DataShow(Object [][] data) {
		titled = (data != null && data.length > 0 && data[0].length == 2);
		model = new DefaultTableModel();
		addFields(data);
		dataContainer = new JTable(model);
		setPane();
	}
	
	public DataShow(DataPrinter printer) {
		if(printer instanceof DataShow) titled = ((DataShow)printer).isTitled();
		else titled = true;
		model = new DefaultTableModel();
		addFields(printer);
		dataContainer = new JTable(model);
		setPane();
	}
	
	// ADD SECTION ----------------------------------------------------------------------------------------------
	
	public void addFields(String[] titles, Object... values) {
		setData(titles, values);
		setModel();
	}
	
	public void addFields(Object [][] data) {
		this.data = data;
		setModel();
	}
	
	public void addFields(DataPrinter printer) {
		setData(printer);
		setModel();
	}
		
	// GET SECTION ----------------------------------------------------------------------------------------------
	
	public JLabel getField(int index) {
		JLabel ret = null;
		if(data[0].length == 2) {
			for (int i = 0; i < data.length; i++) if(i == index) ret = new JLabel(data[i][1] + "");
		}
		else {
			for (int j = 0; j < data.length; j++) if(j == index) ret = new JLabel(data[j][0] + "");
		}		
		return ret;
	}
	
	public JLabel getField(String name) {
		return new JLabel(name);
	}
	
	public Vector<JLabel> getLabels() {
		Vector<JLabel> ret = new Vector<>();
		for (int i = 0; i < data.length; i++) {
			ret.add(getField(i));
		}
		return ret;
	}
	
	public int getDataLength() {
		return data.length;
	}
	
	public Object[][] getData() {
		return data;
	}
	
	public DefaultTableModel getModel() {
		return model;
	}
	
	// SET SECTION ----------------------------------------------------------------------------------------------
	
	public void setFieldValue(String value, int index) {
		setFieldValue(null, value, index);
	}
	
	public void setFieldValue(String label, String value, int index) {
		if(data[0].length == 2) {
			for (int i = 0; i < data.length; i++) if(i == index) {
				if(label != null) data[i][0] = label;
				data[i][1] = value;
			}
		}
		else {
			for (int j = 0; j < data.length; j++) if(j == index) data[j][0] = value;
		}	
		setModel();
	}
	
	// FINAL SECTION ----------------------------------------------------------------------------------------------
	
	public boolean isTitled() {
		return titled;
	}
	
	private void setPane() {
		setLayout(new BorderLayout());
		add(new JScrollPane(dataContainer), BorderLayout.CENTER);
	}
	
	private void setData(String[] titles, Object... values) {
		if(values == null) return;
		if(data != null) saveData();
		if(titles != null && titles.length == values.length) {
			if(titled) {
				data = new Object[values.length][2];
				for (int i = 0; i < data.length; i++) {
					data[i][0] = titles[i];
					data[i][1] = values[i];
				}
			} else {
				data = new Object[values.length][1];
				for (int i = 0; i < data.length; i++) {
					data[i][0] = values[i];
				}
			}
		} else {
			data = new Object[values.length][1];
			for (int i = 0; i < data.length; i++) {
				data[i][0] = values[i];
			}
		}
		if(buffer != null) groupData();
	}
	
	private void setData(DataPrinter printer) {
		int size;
		String [] titles = null;
		Object [] values = null;
		if(printer instanceof DataShow) {
			DataShow viewer = (DataShow)printer;
			size = viewer.getDataLength();
			titles = new String[size];
			values = new Object[size];
			if(viewer.titled) {
				for (int i = 0; i < size; i++) {
					titles[i] = viewer.data[i][0] + "";
					values[i] = viewer.data[i][1];
				}
			} else {
				for (int i = 0; i < size; i++) {
					values[i] = viewer.data[i][0];
				}
			}
		} else {
			size = printer.getLabels().size();
			titles = new String[size];
			values = new Object[size];
			for (int i = 0; i < size; i++) {
				titles[i] = printer.getLabels().get(i).getText().split(":")[0].trim();
				values[i] = printer.getLabels().get(i).getText().split(":")[1].trim();
			}
		}
		setData(titles, values);
	}
	
	private void saveData() {
		buffer = new Object[data.length][data[0].length];
		for (int i = 0; i < buffer.length; i++) {
			for (int j = 0; j < buffer[i].length; j++) {
				buffer[i][j] = data[i][j];
			}
		}
	}
	
	private void groupData() {
		Object [][] newData = new Object[data.length + buffer.length][buffer[0].length];
		for (int i = 0; i < buffer.length; i++) {
			for (int j = 0; j < buffer[0].length; j++) {
				newData[i][j] = buffer[i][j];
			}
		}
		int cmp = 0;
		if(titled) {
			for (int i = buffer.length; i < newData.length; i++) {
				newData[i][0] = "t" + (i+1);
				newData[i][1] = data[cmp++][0];
			}
		} else {
			for (int i = buffer.length; i < newData.length; i++) {
				for (int j = 0; j < newData[0].length; j++) {
					newData[i][j] = data[cmp++][j];
				}
			}
		}
		data = newData;
	}
	
	private void setModel() {
		Object [] headers;
		if(data[0].length == 2) headers = new Object[] {"DATA TITLE", "DATA SHOW"};
		else headers = new Object[] {"DATA SHOW"};
		model.setDataVector(data, headers);
		model.fireTableDataChanged();
	}
	
}
