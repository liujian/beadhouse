package com.beadhouse.dao;

import org.apache.ibatis.annotations.Mapper;
import com.beadhouse.domen.Theme;

@Mapper
public interface ThemeMapper {

	Theme selectTheme();
	
}
