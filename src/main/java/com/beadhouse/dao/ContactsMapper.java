package com.beadhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.beadhouse.domen.Contact;

@Mapper
public interface ContactsMapper {

	void insertContact(Contact contact); 
	Contact selectBycontacts(Contact contact);
	List<Contact> selectByLoginUserId(Integer loginUserId);
	List<Contact> selectByElderUserId(Integer elderUserId);
}
