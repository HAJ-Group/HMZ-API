package com.hmz.main;

import com.hmz.collections.Organizer;
import com.hmz.operations.Age;
import com.hmz.operations.Calculator;
import com.hmz.operations.Converter;
import com.hmz.operations.Date;
import com.hmz.test.Tester;

class OperationTester extends Tester {

	public OperationTester(int testIndex) {
		super(testIndex);
	}
	
	void test1() {
		Calculator cal = new Calculator();
		cal.calculate("5x4-1");
		System.out.println(cal.getResult());
		cal.calculate("exp(0)+100");
		System.out.println(cal.getResult());
	}
	
	void test2() {
		System.out.println(Converter.toColor("red"));
	}
	
	void test3() {
		String d = "12/12/2019 12:30:59";
		try {
			System.out.println(Date.getSimplePattern(d));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void test4() {
		Age a = new Age(1, 9, 1998);
		System.out.println(a.getCurrent_years());
		//
		Calculator calculator = new Calculator();
		calculator.calculate("5 x 3!");
		System.out.println(calculator.getResult());
	}
	
	void test5() {
		String message = "484F572041524520594F55";
		Organizer<Character> o = new Organizer<>(false, Converter.toCharList(message));
		for(Organizer<Character> co : o.partitions(message.length()/2)) {
			int code = Integer.parseInt(co.get(0) + "" + co.get(1), 16);
			System.out.print((char)code);
		}
	}

}
