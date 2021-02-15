package com.hmz.main;

import com.hmz.collections.Array;
import com.hmz.collections.Organizer;
import com.hmz.collections.Vecrray;
import com.hmz.operations.ListConverter;
import com.hmz.operations.MapConverter;
import com.hmz.test.Tester;

import java.util.*;

class CollectionTester extends Tester {
	
	final int MAX = 10000;

	public CollectionTester(int testIndex) {
		super(testIndex);
	}
	
	void test1() {
		ArrayList<Integer> array = new ArrayList<>();
		for (int i = 0; i < MAX; i++) {
			array.add(i);
		}
	} 
	
	void test2() {
		Vector<Integer> array = new Vector<>();
		for (int i = 0; i < MAX; i++) {
			array.add(i);
		}
	}
	
	void test3() {
		Array<Integer> array = new Array<>();
		for (int i = 0; i < MAX; i++) {
			array.add(i);
		}
	}
	
	void test4() {
		Vecrray<Integer> array = new Vecrray<>();
		for (int i = 0; i < MAX; i++) {
			array.add(i);
		}
	}
	
	void test5() {
		Integer [] list = new Integer[10];
		for (int i = 0; i < list.length; i++) {
			list[i] = i+1;
		}
		Organizer<String> o = new Organizer<>("hamza", "said", "khalid", "balid", "ahmed");
		System.out.println(o.sort());
	}
	
	void test6() {
		Organizer<String> o = new Organizer<String>("hamza", "rita", "alae", "jawad", "ibtissam", "yahya", "houssam");
		System.out.println(o.associate("Adapter", "Bridge", "Composite", "Decorator", "Facade", "Flyweight", "Proxy"));
	}
	
	void test7() {
		Organizer<Integer> o = new Organizer<Integer>(1, 2, 3, 4, 5);
		o.associate("hamza", "rita", "alae", "jawad", "ibtissam");
		System.out.println("jelf,el");
	}
	
	void test8() {
		Vecrray<String> list = new Vecrray<String>("h1", "h2", "h3");
		ListConverter<String> convert = new ListConverter<String>();
		System.out.println(Arrays.toString(convert.toArray(list)));
	}
	
	void test9() {
		Map<Object, Integer> map = new HashMap<Object, Integer>();
		map.put("1", 10);
		map.put("2", 50);
		map.put("3", 80);
		map.put("4", 100);
		MapConverter<Integer> convert = new MapConverter<Integer>();
		System.out.println(Arrays.toString(convert.toArray(map)));
	}
	
	void test10() {
		Organizer<String> list = new Organizer<String>("jean", "diluc", "qiqi", "keqin", "mona");
		for(int i=0; i<10; i++) {
			System.out.println(list.getRandomValue());
		}
	}
	
}
