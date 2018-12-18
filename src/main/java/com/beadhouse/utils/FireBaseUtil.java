package com.beadhouse.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import com.alibaba.fastjson.JSONObject;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * fireBase消息推送
 *
 * @author liujian
 * @notes
 * @date 2018年11月9日
 * @class FireBaseUtil.java
 */
@Service
public class FireBaseUtil {

//    public final static String AUTH_KEY_FCM = "AIzaSyBTCu4PgkrKq1SXrZ5_1_63j1Yhlq4Enxo";//app服务密钥

    @Value("${fireBase.projectId}")
    private String PROJECT_ID;
    @Value("${fireBase.baseUrl}")
    private String BASE_URL;
    @Value("${fireBase.message.scope}")
    private String MESSAGING_SCOPE;
    private String[] SCOPES = new String[1];
    @Value("${fireBase.title}")
    private String TITLE;
    public String MESSAGE_KEY = "message";

    public static void main(String[] args) {
//        try {
//            pushFCMNotification("hello camile", "eQSkd0dzAEU:APA91bFgSEM_AkiVl3RPS9Trv-g34ed5R5aX-Rp055uESgbXhSDgrI1hi687sQ8UdM2IfezTDeT1aVQH2ylH5pgnKpTHspxoP0mste4r039R-3xf3WDNW2eWoM8u2Z6Sbpo-B4gDOL05");
//        } catch (IOException e) {
//            System.out.println("e = " + e);
//        }
    }

    /**
     * Pretty print a JsonObject.
     *
     * @param jsonObject JsonObject to pretty print.
     */
    private static void prettyPrint(JsonObject jsonObject) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(jsonObject) + "\n");
    }

    /**
     * Construct the body of a notification message request.
     *
     * @return JSON of notification message.
     */
    private JsonObject buildNotificationMessage(String type, String json, String body, String fireBaseToken) {
        JsonObject jNotification = new JsonObject();
        jNotification.addProperty("title", TITLE);
        jNotification.addProperty("body", body);
        JsonObject jData = new JsonObject();
        jData.addProperty("type", type);
        jData.addProperty("json", json);

        JsonObject jMessage = new JsonObject();
        jMessage.add("notification", jNotification);
        jMessage.add("data", jData);

        jMessage.addProperty("token", fireBaseToken);
        JsonObject jFcm = new JsonObject();
        jFcm.add(MESSAGE_KEY, jMessage);
        prettyPrint(jFcm);
        return jFcm;
    }

    /**
     * Retrieve a valid access token that can be use to authorize requests to the FCM REST
     * API.
     *
     * @return Access token.
     * @throws IOException
     */
    // [START retrieve_access_token]
    private String getAccessToken() throws IOException {
        SCOPES[0] = MESSAGING_SCOPE;
        GoogleCredential googleCredential = GoogleCredential
                .fromStream(new FileInputStream("D:\\file\\family-217806-firebase-adminsdk-02g0j-066b629a66.json"))
                .createScoped(Arrays.asList(SCOPES));
        googleCredential.refreshToken();
        return googleCredential.getAccessToken();
    }
    // [END retrieve_access_token]

    /**
     * Create HttpURLConnection that can be used for both retrieving and publishing.
     *
     * @return Base HttpURLConnection.
     * @throws IOException
     */
    private HttpURLConnection getConnection() throws IOException {
        // [START use_access_token]
        String FCM_SEND_ENDPOINT = "/v1/projects/" + PROJECT_ID + "/messages:send";
        URL url = new URL(BASE_URL + FCM_SEND_ENDPOINT);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", "Bearer " + getAccessToken());
        httpURLConnection.setRequestProperty("Content-Type", "application/json; UTF-8");
        return httpURLConnection;
        // [END use_access_token]
    }

    public boolean pushFCMNotification(String type, String json, String body, String fireBaseToken) throws IOException {
        JsonObject fcmMessage = buildNotificationMessage(type, json, body, fireBaseToken);
        HttpURLConnection connection = getConnection();
        connection.setDoOutput(true);
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes(fcmMessage.toString());
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            String response = inputstreamToString(connection.getInputStream());
            System.out.println("Message sent to Firebase for delivery, response:");
            System.out.println(response);
            return true;
        } else {
            System.out.println("Unable to send message to Firebase:");
            String response = inputstreamToString(connection.getErrorStream());
            System.out.println(response);
            return false;
        }
    }

    /**
     * Read contents of InputStream into String.
     *
     * @param inputStream InputStream to read.
     * @return String containing contents of InputStream.
     * @throws IOException
     */
    private static String inputstreamToString(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            stringBuilder.append(scanner.nextLine());
        }
        return stringBuilder.toString();
    }

}
