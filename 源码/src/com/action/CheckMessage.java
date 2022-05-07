package com.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckMessage implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	public CheckMessage() {}
			
	//用户名格式检测
	public boolean isUserName(String username) {
		int num = 0,alp = 0; //num用于验证是否含数字，alp用于验证是否含字母
		if(username.length() >= 2) {
			for(int i = 0; i < username.length(); i++) {
				if(username.charAt(i) >= '0' && username.charAt(i) <= '9') {
					num += 1;
				}
				if((username.charAt(i) >= 'a' && username.charAt(i) <= 'z') || 
						(username.charAt(i) >= 'A' && username.charAt(i) <= 'Z')) {
					alp += 1;
				}
			}
			if(num >= 1 && alp >= 1 && (num + alp) == username.length()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isPassword(String password) {
		int num = 0,alp = 0; //num用于验证是否含数字，alp用于验证是否含字母
		if(password.length() >= 6 && password.length() <=20) {
			for(int i = 0; i < password.length(); i++) {
				if(password.charAt(i) >= '0' && password.charAt(i) <= '9') {
					num += 1;
				}
				if((password.charAt(i) >= 'a' && password.charAt(i) <= 'z') || 
						(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z')) {
					alp += 1;
				}
			}
			if(num >= 1 && alp >= 1) {
				return true;
			}
		}
		return false;
	}
	
	//电话格式检测
	public boolean isPhoneNum(String phonenum) {
		if(phonenum.length() != 11) {
			return false;
		}
				
		/*电话号码的正则表达式
		* 前三位根据手机号规定设置
		* 后八位随机选定即可
		*/
		String Norm = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
		Pattern phone = Pattern.compile(Norm);
		Matcher matcher = phone.matcher(phonenum);
		return matcher.matches();
	}
	
	public boolean isBookISBN(String number) {
		if(number.length() != 13) {
			return false;
		}
		
		int num = 0;
		for(int i = 0; i < number.length(); i++) {
			if(number.charAt(i) >= '0' && number.charAt(i) <= '9') {
				num += 1;
			}
		}
		if(num == number.length()) {
			return true;
		}
		return false;
	}
		
}
