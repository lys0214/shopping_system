package com.tentact.shopping.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetDateTime {
	// 获取当前系统时间
	public static String getNowTime(String pattern) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
		LocalDateTime localDateTime = LocalDateTime.now();
		String str = localDateTime.format(dtf);
		return str;
	}
	// 获取时间戳
	public static String getTimeStamp() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		LocalDateTime localDateTime = LocalDateTime.now();
		String str = localDateTime.format(dtf);
		return str;
	}
	public static void main(String[] args) {
//		System.out.println(GetDateTime.getNowTime());
		System.out.println(GetDateTime.getTimeStamp());
	}
}
