package com.beadhouse.service;

import com.beadhouse.in.TokenParam;
import com.beadhouse.out.BasicData;


public interface ScheduleService {

	/**
	 * 获取日程
	 * @return
	 */
	BasicData getSchedule(TokenParam param);
}
