package com.beadhouse.dao;

import java.util.List;

import com.beadhouse.domen.DefineQuest;
import org.apache.ibatis.annotations.Mapper;
import com.beadhouse.domen.Quest;

@Mapper
public interface QuestMapper {
	List<Quest> getquestlist();

	Quest getQuest(int questId);

	void insertDefineQuest(DefineQuest defineQuest);
}
