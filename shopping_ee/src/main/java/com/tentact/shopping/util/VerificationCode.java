package com.tentact.shopping.util;

public class VerificationCode {
	// 生成验证码的方法
	public static String createCode() {
		char[] arr = new char[62];
		// 前26个 存储a-z
		for(int i=0;i<26;i++) {
			arr[i] = (char)(97+i);
		}
		// 27-52存A-Z
		for(int i=26;i<52;i++) {
			arr[i] = (char)(65+i-26);
		}
		// 数字 0-9
		for(int i=52;i<62;i++) {
			arr[i] = (char)(48+i-52);
		}
		String code = "";
		// 获取验证码
		for(int i=1;i<=4;i++) {
			int index = (int)(Math.random()*62);
			code+=arr[index];
		}
		return code;
	}
	public static void main(String[] args) {
		VerificationCode verificationCode = new VerificationCode();
		System.out.println(verificationCode.createCode());
	}
}
