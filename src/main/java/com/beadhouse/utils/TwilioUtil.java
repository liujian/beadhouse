package com.beadhouse.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioUtil {
    @Value("${twilio.country}")
    private String TWILIO_COUNTRY;
    @Value("${twilio.account}")
    private String ACCOUNT_SID;
    @Value("${twilio.token}")
    private String AUTH_TOKEN;

    public static void main(String[] args) {
//        sendMessage("", "");
    }

    public void sendMessage(String phone, String body) {
        System.out.println("TWILIO_COUNTRY = " + TWILIO_COUNTRY + ",ACCOUNT_SID = " + ACCOUNT_SID);
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(TWILIO_COUNTRY + phone),
                new PhoneNumber("+13852437755"), body).create();

        System.out.println(message.getSid());
    }


}
