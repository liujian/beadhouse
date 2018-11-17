package com.beadhouse.redis;

public class IdentifyCodeUtil {
	
	 public static String getRandom() {
	        String num = "";
	        for (int i = 0; i < 6; i++) {
	            num = num + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
	        }
	        return num;
	    }

}
