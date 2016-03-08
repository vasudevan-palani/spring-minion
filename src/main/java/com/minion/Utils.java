package com.minion;

import java.util.Date;

import org.springframework.boot.logging.LogLevel;

public class Utils {

	public static void log(LogLevel debug,String msg){
		Date date = new Date();
		System.out.println(date+", "+debug.toString()+":"+msg);
	}
}