package com.hmz.operations;

import com.hmz.collections.Organizer;
import com.hmz.collections.Vecrray;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 
 * @author hamza
 *
 */
public class Converter {
	
	private static final String ERR_CONV = "CONVERSION FAILED";
	private Object data;
	
	public Converter() {}
	
	public Converter(Object data) {
		this.data = data.toString().trim();
	}
	
	// INTERN METHODS
	
	public Integer toInteger() {
		return toInteger(data);
	}
	
	public Long toLong() {
		return toLong(data);
	}
	
	public Byte toByte() {
		return toByte(data);
	}
	
	public Double toDouble() {
		return toDouble(data);
	}
	
	public Boolean toBoolean() {
		return toBoolean(data);
	}
		
	public Date toDate() {
		return toDate(data);
	}
	
	public Color toColor() {
		return toColor(data);
	}
	
	public String toString() {
		return data.toString();
	}
	
	public List<Character> toCharList() {
		return toCharList(data);
	}
	
	// STATIC METHODS
	
	public static Integer toInteger(Object data) {
		Integer ret = 0;
		try {
			ret = Integer.parseInt(data.toString().trim());
		} catch (Exception e) {
			splash(e);
		}
		return ret;
	}
	
	public static Long toLong(Object data) {
		Long ret = 0l;
		try {
			ret = Long.parseLong(data.toString().trim());
		} catch (Exception e) {
			splash(e);
		}
		return ret;
	}
	
	public static Byte toByte(Object data) {
		Byte ret = 0;
		try {
			ret = Byte.parseByte(data.toString().trim());
		} catch (Exception e) {
			splash(e);
		}
		return ret;
	}
			
	public static Boolean toBoolean(Object data) {
		Boolean ret = false;
		try {
			ret = Boolean.parseBoolean(data.toString().trim());
		} catch (Exception e) {
			splash(e);
		}
		return ret;
	}
	
	public static Double toDouble(Object data) {
		Double ret = 0.;
		try {
			ret = Double.parseDouble(data.toString().trim());
		} catch (Exception e) {
			splash(e);
		}
		return ret;
	}
	
	public static Date toDate(Object data) {
		Date ret = null;
		try {
			ret = Date.parseDate(data.toString().trim(), Date.getSimplePattern(data.toString()));
		} catch (Exception e) {
			splash(e);
		}
		return ret;
	}
	
	public static Color toColor(Object data) {
		Color ret = null;
		String colorName = data.toString().trim().toUpperCase();
		try {
			Class<?> c = Class.forName("java.awt.Color");
			Field f = c.getDeclaredField(colorName);
			ret = (Color)f.get(null);
		} catch (Exception e) {
			splash(e);
		}
		return ret;
	}
		
	public static String toString(Object data) {
		return data.toString().trim();
	}
	
	// LISTS
	
	public static List<Integer> toIntList(Object data){
		List<Integer> ret = new Vecrray<>();
		for (int i = 0; i < data.toString().length(); i++) {
			ret.add(toInteger(data.toString().charAt(i)));
		}
		return ret;
	}
	
	public static List<Character> toCharList(Object data) {
		List<Character> ret = new Vecrray<>();
		for (int i = 0; i < data.toString().length(); i++) {
			ret.add(data.toString().charAt(i));
		}
		return ret;
	}
	
	public static List<Object> toList(Object [] data) {
		return new Vecrray<>(data);
	}
	
	public static String toPath(String [] data) {
		return toSequence(data, '/');
	}
	
	public static String toSequence(Object [] data) {
		return toSequence(data, ',');
	}
	
	public static String toSequence(Object [] data, char separator) {
		String ret = "";
		for(Object d : data) {
			ret  += separator + d.toString();
		}
		return ret.substring(1);
	}
	
	// REVERSE BINARY CONVERTING
	
	public static Integer fromBinary(String bin) {
		return fromBinary(bin, 2);
	}
	
	public static Integer fromBinary(String bin, int base) {
		Integer ret = 0;
		Calculator c = new Calculator();
		// REVERSE THE STRING
		Organizer<Character> o = new Organizer<>(toCharList(bin));
		o = o.reverse();
		for (int i = 0; i < o.size(); i++) {
			String exp = o.get(i) + " x " + base + "^" + i;
			c.calculate(exp);
			ret += (int)c.getResult();
		}
		return ret;
	}
	
	// PRIVATE METHODS
	
	private static void splash(Exception e) {
		System.err.println(ERR_CONV + " !: " + e.getMessage());
	}

}
