package com.beadhouse.dao;

import org.apache.ibatis.annotations.Mapper;

import com.beadhouse.domen.Collection;

@Mapper
public interface CollectionMapper {

	Collection selectCollection(Collection collection);
	
	void insertCollection(Collection collection); 
	
	void deleteCollection(Collection collection);
	
	void delElderCollection(Collection collection);
	
}
