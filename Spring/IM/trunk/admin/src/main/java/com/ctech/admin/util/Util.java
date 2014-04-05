package com.ctech.admin.util;

public class Util {

	public static boolean isNumeric(String s) {
	    return (s == null) ? false : s.matches("[-+]?\\d*\\.?\\d+");  
	} 
	
	public static int convertToNumber(String s) {
	    return (s == null) ? 0 : Integer.parseInt(s);  
	} 
}
