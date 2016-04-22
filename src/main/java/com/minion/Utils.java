package com.minion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.logging.LogLevel;

public class Utils {

	public static void log(LogLevel debug, String msg) {
		Date date = new Date();
		System.out.println(date + ", " + debug.toString() + ":" + msg);
	}

	public static java.sql.Date getSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static java.sql.Date getSqlDate(String date, String format) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return new java.sql.Date(formatter.parse(date).getTime());
	}

	public static boolean notEmpty(Object item) {
		return item != null && !item.toString().equalsIgnoreCase("");
		
	}
	public static boolean notEmpty(java.sql.Date startDate) {
		return startDate != null && !startDate.toString().equalsIgnoreCase("");
		
	}
}