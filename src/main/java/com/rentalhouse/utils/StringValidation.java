package com.rentalhouse.utils;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;


public class StringValidation {
	
	public static final boolean isValidString(String value, int minSize, int maxSize){
		return StringUtils.hasLength(value) && value.length() >= minSize && value.length() <= maxSize;				
	}
	public static final boolean isValidString(String value, int minSize, int maxSize, String pattern){
		return  isValidString(value, minSize, maxSize) && Pattern.matches(pattern, value);		
	}

}
