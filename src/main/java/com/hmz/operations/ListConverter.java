package com.hmz.operations;

import java.util.List;

/**
 * 
 * @author hamza
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class ListConverter<T> extends Converter {

	public ListConverter() {
		super();
	}
	
	public T [] toArray(List<T> data) {
		T [] ret = (T[]) new Object[data.size()];
		for(int i = 0; i<data.size(); i++) {
			ret[i] = data.get(i);
		}
		return ret;
	}
	
	public static String [] toStringArray(List<?> data) {
		String [] ret = new String[data.size()];
		for(int i = 0; i<data.size(); i++) {
			ret[i] = data.get(i) + "";
		}
		return ret;
	}

}
