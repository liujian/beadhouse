package com.beadhouse.utils;

import java.security.SecureRandom;
import java.util.UUID;

public class Utils {
		
	/**
	 * 生成token
	 * @param charCount
	 * @return
	 */
	public static String getToken() {
        return generateValue(UUID.randomUUID().toString());
    }
	
	public static String subUserId(String userId){
		if(userId.length()>10){
			return userId.substring(userId.length()-10);
		}
		return userId;
	}
	
	public static String generateValue(String param) {
        return UUID.fromString(UUID.nameUUIDFromBytes(param.getBytes()).toString()).toString().replaceAll("-", "");
    }
	
	/**
	 * 获取随机验证码
	 * @param charCount
	 * @return
	 */
	public static String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }
	
	public static int randomInt(int from, int to) {
//      Random r = new Random();
      SecureRandom r = new SecureRandom();
      return from + r.nextInt(to - from);
  }
	
}
