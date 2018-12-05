package com.beadhouse.controller;

import com.beadhouse.dao.UserMapper;
import com.beadhouse.domen.User;
import com.beadhouse.utils.Constant;
import com.beadhouse.utils.FireBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
@EnableScheduling
public class ScheduledController {

    @Autowired
    private UserMapper userMapper;
    @Resource
    private FireBaseUtil fireBaseUtil;

    @Scheduled(cron = "0 0 9 * * ?")
    public void pushDataScheduled() {
        System.out.println("1111111111111111111111111111111111");
        List<User> userList = userMapper.selectUserList();
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);// 将时分秒,毫秒域清零
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long time = calendar.getTime().getTime();
        for (User user : userList) {
            switch (user.getNotifyType()) {
                case Constant.NOTIFY_TYPE_NEVER:
                    break;
                case Constant.NOTIFY_TYPE_ONE_DAY:
                    if (time == user.getNotifyDate().getTime() || time - 24 * 60 * 60 * 1000 == user.getNotifyDate().getTime()) {
                        if (user.getFireBaseToken() != null) {
                            sendPush(user.getFireBaseToken());
                            user.setNotifyDate(new Date());
                            userMapper.updateNotifyDate(user);
                        }
                    }
                    break;
                case Constant.NOTIFY_TYPE_TWO_DAY:
                    if (time - 2 * 24 * 60 * 60 * 1000 == user.getNotifyDate().getTime()) {
                        if (user.getFireBaseToken() != null) {
                            sendPush(user.getFireBaseToken());
                            user.setNotifyDate(new Date());
                            userMapper.updateNotifyDate(user);
                        }
                    }
                    break;
                case Constant.NOTIFY_TYPE_ONE_WEEK:
                    if (time - 7 * 24 * 60 * 60 * 1000 == user.getNotifyDate().getTime()) {
                        if (user.getFireBaseToken() != null) {
                            sendPush(user.getFireBaseToken());
                            user.setNotifyDate(new Date());
                            userMapper.updateNotifyDate(user);
                        }
                    }
                    break;
                case Constant.NOTIFY_TYPE_SURPRISE:
                    Random random = new Random();
                    int num = random.nextInt(2);
                    if (num == 1) {
                        if (user.getFireBaseToken() != null) {
                            sendPush(user.getFireBaseToken());
                            user.setNotifyDate(new Date());
                            userMapper.updateNotifyDate(user);
                        }
                    }
                    break;
            }
        }
    }

    private void sendPush(String fireBaseToken) {
        try {
            String body = "Please ask a question to love ones";
            fireBaseUtil.pushFCMNotification(Constant.PUSH_TYPE_REMINDER, "", body, fireBaseToken);
        } catch (IOException e) {
            System.out.println("e = " + e);
        }
    }

    public static void main(String args[]){
        new ScheduledController().pushDataScheduled();
    }
}
