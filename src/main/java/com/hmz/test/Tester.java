package com.hmz.test;

import com.hmz.collections.Vecrray;

import java.lang.reflect.Method;

public abstract class Tester {
	
	public static Vecrray<String> errors = new Vecrray<>();
	private int testIndex;
	private String prefix;
	
	// CONSTRUCTORS
	
	public Tester() {
		this(1);
	}
	
	public Tester(int testIndex) {
		this(testIndex, "test");
	}
	
	public Tester(int testIndex, String prefix) {
		System.out.println("TEST STARTED");
		System.out.println("--------------------------------------------------------------------");
		long startTime = System.currentTimeMillis();
		try {
			this.testIndex = testIndex;
			this.prefix = prefix;
			test();
		} catch (Exception e) {
			errors.add(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("--------------------------------------------------------------------");
		if(isSucceeded()) System.out.print("TEST FINNISHED :: ");
		else System.err.print("TEST FAILED :: " + errors.get(0) + " :: ");
		System.out.println((System.currentTimeMillis() - startTime) + "Ms");
	}
	
	// MAIN TEST FUNCTION
	
	public void test() throws Exception {
		try {
			Method method = getClass().getDeclaredMethod(prefix + getTestIndex());
			method.setAccessible(true);
			method.invoke(this);
			method.setAccessible(false);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Maybe no test method is defined ! please try to define a test method as test1, test2... then try again");
		}
	}
	
	// GETTERS AND SETTERS
	
	public int getTestIndex() {
		return testIndex;
	}

	public void setTestIndex(int testIndex) {
		this.testIndex = testIndex;
	}

	public static Vecrray<String> getErrors() {
		return errors;
	}

	// OTHER 
	
	public boolean isSucceeded() {
		return (errors.isEmpty());
	}
}
