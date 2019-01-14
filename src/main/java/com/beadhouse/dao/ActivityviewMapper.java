package com.beadhouse.dao;

import org.apache.ibatis.annotations.Mapper;

import com.beadhouse.domen.ActivityView;

@Mapper
public interface ActivityviewMapper {

	
	ActivityView getActivityviewByActiveDate(String activedate);

}
