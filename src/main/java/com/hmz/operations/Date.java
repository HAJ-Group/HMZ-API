package com.hmz.operations;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.GregorianCalendar;

public class Date {
	
	// PATTERNS
	/**
	 * Day/Month/Year
	 */
	public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
	
	/**
	 * Day-Month-Year
	 */
	public static final String DASHED_DATE_PATTERN = "dd-MM-yyyy";
	
	/**
	 * Day/Month/Year
	 */
	public static final String SHORT_DATE_PATTERN = "dd/MM/yy";
	
	/**
	 * Day Month Year
	 */
	public static final String LONG_DATE_PATTERN = "dd MMMM yyyy";
	
	/**
	 * Day/Month/Year Hour:Minutes:Seconds
	 */
	public static final String TIMED_DATE_PATTERN = "dd/MM/yyyy HH:mm:ss";
	
	/**
	 * Hour:Minutes:Seconds
	 */
	public static final String TIME_PATTERN = "HH:mm:ss";
	
	public static final DateTime NOW = new DateTime();
	
	// PROPERTIES
	private String datePattern;
	protected String out;
	
	private int day;
	private int month;
	private int year;
	private int hours;
	private int minutes;
	private int seconds;
	
	/**
	 * DEFAULT CONSTRUCTORS CURRENT DATE AND TIME
	 */
	public Date() {
		this(NOW);
	}
	
	/** ASSOCIATED CONSTUCTOR WITH JODA DATE TIME OBJECT
	 * 
	 * @param dateTime
	 */
	public Date(DateTime dateTime) {
		this(dateTime.getDayOfMonth(), dateTime.getMonthOfYear(), dateTime.getYear(), dateTime.getHourOfDay(), dateTime.getMinuteOfHour(), dateTime.getSecondOfMinute());
	}
	
	/** ASSOCIATED CONSTRUCTOR WITH GREGORIAN CALENDAR OBJECT
	 * 
	 * @param calendar
	 */
	public Date(GregorianCalendar calendar) {
		this(calendar.get(GregorianCalendar.DAY_OF_MONTH),
				calendar.get(GregorianCalendar.MONTH),
				calendar.get(GregorianCalendar.YEAR),
				calendar.get(GregorianCalendar.HOUR),
				calendar.get(GregorianCalendar.MINUTE),
				calendar.get(GregorianCalendar.SECOND));
	}
	
	/** FIELD CONSTRUCTOR
	 * 
	 * @param day
	 * @param month
	 * @param year
	 */
	public Date(int day, int month, int year) {
		this(day, month, year, -1, -1, -1);
	}
	
	/** FIELD CONSTRUCTOR
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @param hours
	 * @param minutes
	 * @param seconds
	 */
	public Date(int day, int month, int year, int hours, int minutes, int seconds) {
		
		this.day = day;
		this.month = month;
		this.year = year;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		
		setDatePattern(DEFAULT_DATE_PATTERN);
	}

	// SETTERS AND GETTERS
	
	private void setFormat() {
		try {
			out = format(datePattern, this.day, this.month, this.year, this.hours, this.minutes, this.seconds);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
		setFormat();
	}
	
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
		setFormat();
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
		setFormat();
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
		setFormat();
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
		setFormat();
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
		setFormat();
	}

	public int getSeconds() {
		return seconds;
	}
	
	public void setSeconds(int seconds) {
		this.seconds = seconds;
		setFormat();
	}

	// OTHER

	public String toString() {
		return out;
	}
	
	/** COMBINING DATE AND TIME PATTERN
	 * 
	 * @param pattern1
	 * @param pattern2
	 */
	public void combine(String datePattern, String timePattern) {
		datePattern = datePattern + " " + timePattern;
		setFormat();
	}
	
	/** CONVERT TO JODA DATE TIME OBJECT
	 * 
	 * @return
	 */
	public DateTime toDateTime() {
		DateTime ret = null;
		String date = out;
		DateTimeFormatter dtf = DateTimeFormat.forPattern(datePattern);
		ret = dtf.parseDateTime(date);
		return ret;
	}
	
	// STATIC METHODS
	
	public static String formatTimeColumn(int timeColumn) {
		return ((timeColumn + "").length() == 1) ? "0" + timeColumn : timeColumn + "";
	}
	
	/** SIMPLE VERSION OF BUILD PATTERN WITH ONLY DAY MONTH YEAR
	 * 
	 * @param pattern
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 * @throws Exception
	 */
	public static String format(String pattern, int day, int month, int year) throws Exception {
		return format(pattern, day, month, year, -1, -1, -1);
	}
	
	/** NAME OF THE NUMERIC VALUE OF A MONTH
	 * 
	 * @param month
	 * @return
	 */
	public static String getNamedMonth(int month) {
		String ret = null;
		String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", 
				"October", "November", "December"};
		ret = months[month-1];
		return ret;
	}
	
	/** FORMATING DATE WITH DAY MONTH YEAR HOURS MINUTES SECONDS PARAMETERS
	 * 
	 * @param pattern 
	 * @param day
	 * @param month
	 * @param year
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @return
	 * @throws Exception
	 */
	public static String format(String pattern, int day, int month, int year, int hours, int minutes, int seconds) 
			throws Exception {
		String ret = null;
		if(day*month*year != 0 && day <= 366 && month <= 31 && (year + "").length() <= 4 && minutes <= 59 && seconds <= 59) {
			if(pattern.contains("dd")) pattern = pattern.replaceFirst("dd", formatTimeColumn(day));
			if(pattern.contains("MMMM")) pattern = pattern.replaceFirst("MMMM", getNamedMonth(month));
			else if(pattern.contains("MM")) pattern = pattern.replaceFirst("MM", formatTimeColumn(month));
			if(pattern.contains("yyyy")) pattern = pattern.replaceFirst("yyyy", year + "");
			else if(pattern.contains("yy")) pattern = pattern.replaceFirst("yy", year + "");
			if(hours >= 0) if(pattern.contains("HH")) pattern = pattern.replaceFirst("HH", formatTimeColumn(hours));
			if(minutes >= 0) if(pattern.contains("mm")) pattern = pattern.replaceFirst("mm", formatTimeColumn(minutes));
			if(seconds >= 0) if(pattern.contains("ss")) pattern = pattern.replaceFirst("ss", formatTimeColumn(seconds));
			ret = pattern;
		} else throw new Exception("Error input of date Values");
		return ret;
	}
	
	/** PARSING STRING TO DATE WITH A DETERMINATED PATTERN
	 * 
	 * @param date STRING WELL FORMED RELATED TO THE PATTERN
	 * @param pattern THE SPECIFIED PATTERN
	 * @return DATE OBJECT
	 */
	public static Date parseDate(String date, String pattern) throws Exception {
		Date ret = null;
		DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
		DateTime dateTime = dtf.parseDateTime(date);
		ret = new Date(dateTime);
		return ret;
	}
	
	/** DATES COMPARE FUNCTION 0 EQUAL 1 D1 > D2 -1 D1 < D2
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int compare(Date d1, Date d2) {
		int ret = 0;
		if(d1.getYear() > d2.getYear()) ret = 1;
		else if(d1.getYear() < d2.getYear()) ret = -1;
		else {
			if(d1.getMonth() > d2.getMonth()) ret = 1;
			else if(d1.getMonth() < d2.getMonth()) ret = -1;
			else {
				if(d1.getDay() > d2.getDay()) ret = 1;
				else if(d1.getDay() < d2.getDay()) ret = -1;
				else ret = 0;
			}
		}
		return ret;
	}
	
	/** GET PERIOD DATE BETWEEN A SPECIFIED DATE AND NOW
	 * 
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	public static Period getPeriod(String date) throws Exception {
		return getPeriod(date, getSimplePattern(date));
	}
	
	/** GET PERIOD DATE BETWEEN A SPECIFIED DATE AND NOW
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static Period getPeriod(String date, String pattern) throws Exception {
		Date current_date = new Date();
		Date last_date = Date.parseDate(date, pattern);
		// PERIOD
		return new Period(last_date.toDateTime(), current_date.toDateTime());
	}
	
	/** GET FORMATED PERIOD 
	 * 
	 * @param period
	 */
	public static String formatPeriod(Period period) {
		String ret = null;
		PeriodFormatter pformater = new PeriodFormatterBuilder().
				appendYears().appendSuffix(" year ", " years ").
				appendMonths().appendSuffix(" months ").
				appendDays().appendSuffix(" day ", " days ").
				appendHours().appendSuffix(" hour ", " hours ").
				appendMinutes().appendSuffix(" minute ", " minutes ").
				appendSeparator(" and ").
				appendSeconds().appendSuffix(" second ", " seconds ").
				toFormatter();
		ret = pformater.print(period);
		return ret;
	}
	
	/** GUESS THE DATE PATTERN OF THE STRING SPECIFIED
	 * NOTE THAT THIS FUNCTION CAN ONLY GUESS THE PATTERNS PROPOSED BY THE DATE CLASS SO IT WILL BE USELESS WITH CUSTOMIZED PATTERNS
	 * 
	 * @param dateExp
	 * @return
	 */
	public static String getSimplePattern(String dateExp) {
		String ret = null;
		if(dateExp.contains("/")) {
			ret = DEFAULT_DATE_PATTERN;
			if(dateExp.contains(":")) ret = TIMED_DATE_PATTERN;
			String year = null;
			if(ret.equals(TIMED_DATE_PATTERN)) {
				year = dateExp.substring(dateExp.lastIndexOf("/") + 1, dateExp.indexOf(":") - 2).trim();
			}
			else year = dateExp.substring(dateExp.lastIndexOf("/") + 1).trim();
			if(year.length() == 2) ret = SHORT_DATE_PATTERN;
		}
		else if(dateExp.contains("-")) ret = DASHED_DATE_PATTERN;
		else if(dateExp.contains(":")) ret = TIME_PATTERN;
		else ret = LONG_DATE_PATTERN;
		return ret;
	}
	  
}
