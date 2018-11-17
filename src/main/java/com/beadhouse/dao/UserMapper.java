package com.beadhouse.dao;

import org.apache.ibatis.annotations.Mapper;

import com.beadhouse.domen.User;
@Mapper
public interface UserMapper {
	
	User selectByEmailAddress(String EmailAddress);
	User selectByToken(String token);
    void insertUser(User user);
    void updateToken(User user);
    void updatePassword(User user);
    void updateUserInfo(User user);
    void updateUserAvatar(User user);
}