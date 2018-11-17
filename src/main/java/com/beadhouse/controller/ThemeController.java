package com.beadhouse.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.beadhouse.out.BasicData;
import com.beadhouse.service.ThemeService;

@RestController
public class ThemeController {
	
	@Autowired
	private ThemeService themeService;

    /**
     * 获取logo及主题色
     * @param request
     * @return
     */
    @RequestMapping("theme")
    @ResponseBody
    public BasicData theme(HttpServletRequest request){
        return themeService.theme();
    }

}
