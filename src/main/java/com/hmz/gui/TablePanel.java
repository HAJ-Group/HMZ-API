package com.hmz.gui;

import com.hmz.collections.Vecrray;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;

/** PANEL ENVLOPPS JTABLE COMPONENT FOR ADDING A TABLED PANEL VIEW
 * 
 * @author hamza
 *
 */
public class TablePanel extends Panel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private Object [] headers;
	private Object [][] data;
	
	// CONSTRUCTORS
	
	/** DEFAULT CONSTRUCTOR FROM A LIST OF HEADERS
	 * 
	 * @param headers
	 */
	public TablePanel(String ...headers) {
		model = new DefaultTableModel();
		setHeaders(headers);
		table = new JTable(model);
		table.setSize(200, HEIGHT);
		add(new JScrollPane(table));
	}
	
	/** CONSTRUCTOR FROM A LIST OF OBJECTS USING REFLECTION
	 * 
	 * @param list
	 */
	public TablePanel(List<Object> list) {
		this();
		if(!Vecrray.isMixed(list)) {
			setHeaders(list.get(0).getClass());
			for(Object o : list) 
				try {
					addRow(o);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
	
	
	/** DELEGATE CONSTRUCTOR FOR SETTING TABLE VECTOR DATA
	 * 
	 * @param data
	 * @param headers
	 */
	public TablePanel(Object [][] data, String [] headers) {
		this(headers);
		setData(data);
	}
	
	/** CONSTRUCTOR FOR SETTING HEADERS FROM A CLASS USING REFLECTION
	 * 
	 * @param c
	 */
	public TablePanel(Class<?> c) {
		this();
		setHeaders(c);
	}
	
	// ADD
	
	/** ADDING A ROW FROM AN OBJECT FIELDS USING REFLECTION
	 * 
	 * @param row
	 * @throws Exception
	 */
	public void addRow(Object row) throws Exception {
		Class<?> c = row.getClass();
		Field [] fields = c.getDeclaredFields();
		if(fields.length == headers.length) {
			Vecrray<Object> dataRow = new Vecrray<>();
			for(Field f : fields) {
				f.setAccessible(true);
				dataRow.add(f.get(row));
				f.setAccessible(false);
			}
			addRow(dataRow.toArray());
		} else {
			throw new Exception("Error : Object is not compatible with table data structure");
		}
	}
	
	/** ADDING A ROW TO THE TABLE 
	 * 
	 * @param row
	 */
	public void addRow(Object [] row) {
		model.addRow(row);
		model.fireTableDataChanged();
		try {
			resizeColumnWidth(table);
		} catch (Exception e) {}
	}
	
	// SETTERS AND GETTERS
	
	public void setData(Object[][] data) {
		this.data = data;
		setModel();
	}
	
	public void setHeaders(Object[] headers) {
		this.headers = headers;
		setModel();
	}
	
	public void setHeaders(Class<?> c) {
		Field [] fields = c.getDeclaredFields();
		headers = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			headers[i] = fields[i].getName();
		}
		setHeaders(headers);
	}
	
	// PRIVATE METHODS
	
	private void setModel() {
		model.setDataVector(data, headers);
		model.fireTableDataChanged();
		try {
			resizeColumnWidth(table);
		} catch (Exception e) {}
		
	}
	
	// STATIC METHODS
	
	public static void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 15; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}

}
