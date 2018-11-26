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
import com.google.gson.JsonObject;

/**
 * fireBase消息推送
 *
 * @author liujian
 * @notes
 * @date 2018年11月9日
 * @class FireBaseUtil.java
 */
public class FireBaseUtil {

    public final static String AUTH_KEY_FCM = "AIzaSyBTCu4PgkrKq1SXrZ5_1_63j1Yhlq4Enxo";//app服务密钥

    private static final String PROJECT_ID = "family-217806";
    private static final String BASE_URL = "https://fcm.googleapis.com";
    private static final String FCM_SEND_ENDPOINT = "/v1/projects/" + PROJECT_ID + "/messages:send";

    private static final String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
    private static final String[] SCOPES = {MESSAGING_SCOPE};

    private static final String TITLE = "FCM Notification";
    private static final String BODY = "hello camile";
    public static final String MESSAGE_KEY = "message";

    public static void main(String[] args) {
        JsonObject notificationMessage = buildNotificationMessage();
        System.out.println("FCM request body for message using common notification object:");
        prettyPrint(notificationMessage);
        try {
            pushFCMNotification(notificationMessage);
        } catch (IOException e) {
            System.out.println("e = " + e);
        }
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
    private static JsonObject buildNotificationMessage() {
        JsonObject jNotification = new JsonObject();
        jNotification.addProperty("title", TITLE);
        jNotification.addProperty("body", BODY);

        JsonObject jMessage = new JsonObject();
        jMessage.add("notification", jNotification);
//        jMessage.addProperty("topic", "notification");
        jMessage.addProperty("token","dcTpiPEE5xQ:APA91bFCvDE4PBfeBLcjGAMQwjPzn-EakhCWkfgYq-oQFDOnelU-5ARkTDtL0M-OfI3OCWN-8smesZi7hMOcYqo143fOHhcz7vRxdwKmFRTR8i0wY3kpiiyeljUIG5hMGxcaJIBfF8cw");

        JsonObject jFcm = new JsonObject();
        jFcm.add(MESSAGE_KEY, jMessage);

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
    private static String getAccessToken() throws IOException {
        GoogleCredential googleCredential = GoogleCredential
                .fromStream(new FileInputStream("D:\\family-217806-firebase-adminsdk-02g0j-066b629a66.json"))
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
    private static HttpURLConnection getConnection() throws IOException {
        // [START use_access_token]
        URL url = new URL(BASE_URL + FCM_SEND_ENDPOINT);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", "Bearer " + getAccessToken());
        httpURLConnection.setRequestProperty("Content-Type", "application/json; UTF-8");
        return httpURLConnection;
        // [END use_access_token]
    }

    public static void pushFCMNotification(JsonObject fcmMessage) throws IOException {
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
        } else {
            System.out.println("Unable to send message to Firebase:");
            String response = inputstreamToString(connection.getErrorStream());
            System.out.println(response);
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
