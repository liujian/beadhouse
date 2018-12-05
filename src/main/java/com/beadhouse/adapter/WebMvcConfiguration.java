package com.beadhouse.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
	
    @Value("${Theme.logo}")
    private String logo;
    
    @Value("${Advertising.backgroundPath}")
    private String backgroundPath;
    
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/logo/**").addResourceLocations(logo);
        registry.addResourceHandler("/backgroundpath/**").addResourceLocations(backgroundPath);
        
        super.addResourceHandlers(registry);
    }
}
