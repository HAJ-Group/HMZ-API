package com.hmz.operations;

/**
 * 
 * @author hamza
 *
 */
public class Random {
	
	private int min;
	private int max;
	
	public Random(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	public double getRandom() {
		return ((Math.random() * (max - min)) + min);
	}
	
	public int getIntRandom() {
		return (int) getRandom();
	}
	
	public double getRandom(int min, int max) {
		this.min = min;
		this.max = max;
		return getRandom();
	}
	
	public int getIntRandom(int min, int max) {
		return (int) getRandom(min, max);
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

}
