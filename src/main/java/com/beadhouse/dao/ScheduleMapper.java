package com.beadhouse.dao;

import com.beadhouse.domen.Activity;
import com.beadhouse.domen.Schedule;
import com.beadhouse.domen.Theme;
import com.beadhouse.out.ScheduleOut;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface ScheduleMapper {

	ScheduleOut getScheduleByDate(Date date);
	List<Activity> getActivityList(int scheduleId);
}
