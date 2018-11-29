package com.beadhouse.service.impl;

import com.beadhouse.dao.ElderUserMapper;
import com.beadhouse.dao.ScheduleMapper;
import com.beadhouse.dao.ThemeMapper;
import com.beadhouse.domen.ElderUser;
import com.beadhouse.domen.Schedule;
import com.beadhouse.domen.Theme;
import com.beadhouse.in.TokenParam;
import com.beadhouse.out.BasicData;
import com.beadhouse.out.ScheduleOut;
import com.beadhouse.service.ScheduleService;
import com.beadhouse.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private ElderUserMapper elderUserMapper;


    @Override
    public BasicData getSchedule(TokenParam param) {
        ElderUser elderUser = elderUserMapper.selectByToken(param.getToken());
        if (elderUser == null) return BasicData.CreateErrorInvalidUser();
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);// 将时分秒,毫秒域清零
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        ScheduleOut schedule = scheduleMapper.getScheduleByDate(calendar.getTime());
        schedule.setActivityList(scheduleMapper.getActivityList(schedule.getScheduleId()));
        return BasicData.CreateSucess(schedule);
    }

}
