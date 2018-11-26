package com.beadhouse.dao;

import org.apache.ibatis.annotations.Mapper;

import com.beadhouse.domen.Collection;

@Mapper
public interface CollectionMapper {

	Collection selectCollectionByLoginUserId(Collection collection);

	Collection selectCollectionByElderUserId(Collection collection);
	
	void insertCollection(Collection collection); 
	
	void deleteCollection(Collection collection);
	
	void delElderCollection(Collection collection);
	
}
