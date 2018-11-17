package com.beadhouse.utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;

/**
 * fireBase消息推送
 * @notes 
 *
 * @author liujian
 * @date 2018年11月9日
 * @class FireBaseUtil.java
 */
public class FireBaseUtil {
	
	public final static String AUTH_KEY_FCM= "111";//app服务密钥
	
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";//谷歌推送地址
	
	public static void main(String[] args) {
		pushFCMNotification();
	}
	public static void pushFCMNotification() {
		try {
			URL url = new URL(API_URL_FCM);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization","key="+AUTH_KEY_FCM);
		conn.setRequestProperty("Content-Type","application/json");//不设置默认发送文本格式。设置就是json
		 //方法一
		JSONObject json = new JSONObject();
		json.put("to","此处填写您的客户端app token");//推送到哪台客户端机器，方法一推一个token
		 //方法二，批量推送 ，最多1000个token ，此处的tokens是一个token JSONArray数组json.put("registration_ids", tokens);
//		JSONObject info = new JSONObject();
//		info.put("title","Notification Tilte");
//		info.put("body", "Hello Test notification");
//		info.put("icon", "myicon");
//		json.put("notification", info);//json 还可以put其他你想要的参数
		
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(json.toString());
		wr.flush();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = null;
		 while ((line = reader.readLine())!= null) {
			 System.out.println(line);
		    }
		  wr.close();
		  reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
