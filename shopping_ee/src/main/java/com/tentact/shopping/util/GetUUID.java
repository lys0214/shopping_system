package com.tentact.shopping.util;

import java.util.UUID;

public class GetUUID {
	// 获取uuid
	public static String getUuidCode() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	public static void main(String[] args) {
		System.out.println(getUuidCode());
	}
}
