package com.hmz.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.Vector;

@SuppressWarnings("unchecked")
public class Array<T> implements Serializable {
	
	private static final long serialVersionUID = 101L;
	
	
	private T[] list;
	
	// PRIVATE METHODS --------------------------------------------------------------------------------------------------
	
	private void copy(T[] dest, final T[] source) {
		copy(dest, source, 0, source.length);
	}
	
	private void copy(T[] dest, final T[] source, boolean reverse) {
		if(reverse) {
			for (int i = 0; i < dest.length; i++) {
				dest[i] = source[i];
			}
		}
		else copy(dest, source);
	}
	
	// CONSTRUCTORS --------------------------------------------------------------------------------------------------

	public Array(T ...items) {
		clear();
		add(items);
	}
	
	public Array(Array<T> array) {
		clear();
		add(array);
	}
	
	public Array(Collection<T> collection) {
		clear();
		add(collection);
	}
	
	// PUBLIC METHODS --------------------------------------------------------------------------------------------------
	
	public synchronized void copy(T[] dest, T[] src, int debut, int fin) {
		for (int i = debut, j = 0; i < fin; i++, j++) {
			dest[i] = src[j];
		}
	}
	
	public synchronized void add(T ...items) {
		T [] saveList = (T[]) new Object[size()];
		copy(saveList, list);
		list = (T[]) new Object[size() + items.length];
		copy(list, saveList);
		copy(list, items, saveList.length, size());
	}
	
	public synchronized void add(Array<T> array) {
		add(array.list);
	}
	
	public synchronized void add(Collection<? extends T> collection) {
		add((T[]) collection.toArray());
	}
	
	public synchronized void remove(int index) {
		for (int i = index; i < list.length - 1; i++) {
			list[i] = list[i+1];
		}
		T [] saveList = (T[]) new Object[size()];
		copy(saveList, list);
		list = (T[]) new Object[size() - 1];
		copy(list, saveList, true);
	}
	
	public synchronized void clear() {
		list = (T[]) new Object[0];
	}
	
	public synchronized boolean contains(T item) {
		boolean ret = false;
		for(T itm : list) {
			if(itm.equals(item)) ret = true;
		}
		return ret;
	}
	
	public T firstElement() {
		return get(0);
	}
	
	public T lastElement() {
		return get(size() - 1);
	}
	
	public synchronized T get(int index) {
		return list[index];
	}
	
	public synchronized void set(int index, T value) {
		list[index] = value;
	}
	
	public synchronized boolean isEmpty() {
		return (list.length == 0);
	}
	
	public synchronized int size() {
		return list.length;
	}
	
	public String toString() {
		String ret = "";
		for(T item : list) {
			ret += ", " + item.toString();
		}
		if(!isEmpty()) ret = ret.substring(1).trim();
		return "[" + ret + "]";
	}
	
	public synchronized Collection<T> toCollection(){
		Collection<T> ret = new Vector<>();
		for(T value : list) {
			ret.add(value);
		}
		return ret;
	}	
	
}
