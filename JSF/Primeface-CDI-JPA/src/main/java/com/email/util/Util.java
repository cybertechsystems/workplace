package com.email.util;

import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;


public class Util extends StringUtils {
	public static String getLastToken(String tokenString, String delimeter) {
		StringTokenizer token = new StringTokenizer(tokenString, delimeter);

		String returnValue = null;
		while (token.hasMoreTokens()) {
			returnValue = token.nextToken();
		}

		return blankToNull(returnValue);
	}

	public static String nullToBlank(String checkForNull) {
		return (checkForNull == null) ? "" : checkForNull;
	}

	public static boolean isNull(String stringToCheck) {
		return (stringToCheck == null || "".equals(stringToCheck.trim()));
	}

	public static boolean isNotNull(String stringToCheck) {
		return !isNull(stringToCheck);
	}
	public static boolean isNull(Object input) {
		return (input == null);
	}

	public static String blankToNull(String checkForBlank) {
		if (checkForBlank != null) {
			checkForBlank = checkForBlank.trim();
		}
		return ("".equals(checkForBlank)) ? null : checkForBlank;
	}
}
