package com.rentalhouse.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class DateUtils {
	private static Calendar calendar = Calendar.getInstance();	
	private static final List<Integer> DAYS = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31);
	private static final List<Integer> MONTHS = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);
	private static final List<Integer> YEARS = buildYearsList();
	private static SimpleDateFormat sdf = new SimpleDateFormat();
	
		
	public static Date fromIntegertoDate(Integer date, Integer month, Integer year){
		if (date == null || month == null || year == null){
			throw new IllegalArgumentException();
		}
		calendar.set(Calendar.DATE, date);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.YEAR, year);
		
		return new Date(calendar.getTimeInMillis());		
	}
	public static Integer getYear(Date date){
		if (date == null ) throw new IllegalArgumentException();		
		calendar.setTimeInMillis(date.getTime());
		return calendar.get(Calendar.YEAR);
	}
	public static Integer getMonth(Date date){
		if (date == null ) throw new IllegalArgumentException();
		calendar.setTimeInMillis(date.getTime());
		return calendar.get(Calendar.MONTH + 1);
	}
	public static Integer getDate(Date date){
		if (date == null ) throw new IllegalArgumentException();
		calendar.setTimeInMillis(date.getTime());
		return calendar.get(Calendar.DATE);
	}
	public static List<Integer> getDays(){
		return DAYS;
	}
	public static List<Integer> getMonths(){
		return MONTHS;
	}
	private static List<Integer> buildYearsList(){
		List<Integer> years = new ArrayList<Integer>(90);
		int anio = calendar.get(Calendar.YEAR);
		for (int i = 0; i < 90; i++) {
			years.add(anio - i);
		}
		return years;
	}
	public static List<Integer> getYears(){
		return YEARS;
	}
	public static String fromDateToString(Date date, String pattern){
		sdf.applyPattern(pattern);
		return sdf.format(date);
	}
	
}
