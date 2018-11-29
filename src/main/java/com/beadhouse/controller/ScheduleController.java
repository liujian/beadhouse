package com.beadhouse.controller;

import com.beadhouse.in.TokenParam;
import com.beadhouse.out.BasicData;
import com.beadhouse.service.ScheduleService;
import com.beadhouse.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;

    /**
     * 获取日程
     * @param request
     * @return
     */
    @RequestMapping("elder/getSchedule")
    @ResponseBody
    public BasicData getSchedule(@Valid @RequestBody TokenParam param, HttpServletRequest request){
        return scheduleService.getSchedule(param);
    }

}
