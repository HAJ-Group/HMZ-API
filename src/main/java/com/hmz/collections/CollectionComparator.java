package com.hmz.collections;

import java.util.Comparator;

public class CollectionComparator<T> implements Comparator<T> {

	public int compare(T val1, T val2) {
		if(val1 instanceof Integer && val2 instanceof Integer) {
			Integer num1 = (Integer)val1;
			Integer num2 = (Integer)val2;
			return (num1 - num2);
		}
		return (val1.toString().compareTo(val2.toString()));
	}

}
