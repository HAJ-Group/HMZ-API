package com.hmz.collections;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

/**
 * Modified Vector Constructors To Match Array with multiple values constructors
 * 
 * @author hamza
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class Vecrray<T> extends Vector<T> {

	private static final long serialVersionUID = 1L;

	public Vecrray(T ...items) {
		super();
		add(items);
	}

	public Vecrray(Collection<? extends T> arg0) {
		super(arg0);
	}

	public Vecrray(int arg0, int arg1) {
		super(arg0, arg1);
	}

	public Vecrray(int arg0) {
		super(arg0);
	}
	
	public synchronized void add(T ...items) {
		for(T item : items) add(item);
	}
	
	public T firstElement() {
		return get(0);
	}
	
	public T lastElement() {
		return get(size() - 1);
	}
	
	public static boolean isMixed(List<Object> list) {
		boolean ret = false;
		for (int i = 1; i < list.size(); i++) {
			if(!list.get(i-1).getClass().getName().equals(list.get(i).getClass().getName())) {
				ret = true;
				break;
			}
		}
		return ret;
	}
	
}
