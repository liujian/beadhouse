package com.beadhouse.service.impl;

import com.beadhouse.out.ElderThemeOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.beadhouse.dao.ThemeMapper;
import com.beadhouse.domen.Theme;
import com.beadhouse.out.BasicData;
import com.beadhouse.service.ThemeService;

@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeMapper themeMapper;
    @Value("${version.code}")
    private int versionCode;


    @Override
    public BasicData theme() {
        Theme theme = themeMapper.selectTheme();
        return BasicData.CreateSucess(theme);
    }

    @Override
    public BasicData getVersionCode() {
        return BasicData.CreateSucess(versionCode);
    }

}
