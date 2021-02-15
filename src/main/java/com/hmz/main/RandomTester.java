package com.hmz.main;

import com.hmz.operations.Random;
import com.hmz.test.Tester;

public class RandomTester extends Tester {

	public RandomTester(int testIndex) {
		super(testIndex);
	}

	void test1() {
		Random r = new Random(0, 12);
		while(true) {
			int value = r.getIntRandom();
			System.out.println(value);
			if(value == 12) break;
		}
	}

}
