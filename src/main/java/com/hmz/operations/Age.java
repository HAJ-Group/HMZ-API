package com.hmz.operations;

import org.joda.time.Period;

public class Age extends Date {
	
	private int current_years;
	private int current_months;
	private int current_days;
	
	private Period period;
	
	public Age(int day, int month, int year) {
		super(day, month, year);
		try {
			period = getPeriod(toString());
			current_days = period.getDays();
			current_months = period.getMonths();
			current_years = period.getYears();
			
			out = formatPeriod(period);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// GETTERS AND SETTERS
	
	public int getCurrent_years() {
		return current_years;
	}

	public void setCurrent_years(int current_year) {
		this.current_years = current_year;
	}

	public int getCurrent_months() {
		return current_months;
	}

	public void setCurrent_months(int current_month) {
		this.current_months = current_month;
	}

	public int getCurrent_days() {
		return current_days;
	}

	public void setCurrent_days(int current_day) {
		this.current_days = current_day;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

}
