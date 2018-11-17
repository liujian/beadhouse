package com.beadhouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beadhouse.dao.ThemeMapper;
import com.beadhouse.domen.Theme;
import com.beadhouse.out.BasicData;
import com.beadhouse.service.ThemeService;
@Service
public class ThemeServiceImpl implements ThemeService{

	@Autowired
	private ThemeMapper themeMapper;
	
	
	
	@Override
	public BasicData theme() {
		Theme theme = themeMapper.selectTheme();
		return BasicData.CreateSucess(theme);
	}

}
