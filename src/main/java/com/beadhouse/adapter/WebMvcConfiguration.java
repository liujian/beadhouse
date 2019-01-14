package com.beadhouse.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
    @Value("${Theme.logo}")
    private String logo;
    
    @Value("${schedule.activity}")
    private String activity;
    
    @Value("${Advertising.backgroundPath}")
    private String backgroundPath;
    
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/logo/**").addResourceLocations(logo);
        registry.addResourceHandler("/backgroundpath/**").addResourceLocations(backgroundPath);
        registry.addResourceHandler("/schedule/**").addResourceLocations(activity);
        
    }
}
