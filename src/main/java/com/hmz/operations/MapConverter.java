package com.hmz.operations;

import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("unchecked")
public class MapConverter<T> extends Converter {

	public MapConverter() {
		super();
	}
	
	public T [] toArray(Map<Object, T> map) {
		T [] ret = (T[]) new Object[map.values().size()];
		Iterator<T> it = map.values().iterator();
		int i = 0;
		while(it.hasNext()) {
			ret[i++] = it.next();
		}
		return ret;
	}

}
