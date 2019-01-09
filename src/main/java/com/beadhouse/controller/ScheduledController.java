package com.beadhouse.controller;

import com.beadhouse.dao.UserMapper;
import com.beadhouse.domen.User;
import com.beadhouse.utils.AsyncTask;
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
    @Resource
    private AsyncTask asyncTask;

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
            asyncTask.asyncToSendPush(user, time);
        }
    }

    public static void main(String args[]) {
        new ScheduledController().pushDataScheduled();
    }
}
