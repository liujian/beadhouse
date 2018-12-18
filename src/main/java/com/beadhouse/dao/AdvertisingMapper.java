package com.beadhouse.dao;

import com.beadhouse.domen.Advertising;
import com.beadhouse.domen.Theme;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdvertisingMapper {

	List<Advertising> selectAdvertising();
	
}
