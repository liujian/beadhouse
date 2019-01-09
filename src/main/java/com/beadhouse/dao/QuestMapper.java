package com.beadhouse.dao;

import java.util.List;

import com.beadhouse.domen.DefineQuest;
import com.beadhouse.domen.RecordQuest;
import org.apache.ibatis.annotations.Mapper;
import com.beadhouse.domen.Quest;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QuestMapper {
	List<Quest> getQuestList();

	Quest getQuest(int questId);

	void insertDefineQuest(DefineQuest defineQuest);

	void insertRecordQuest(RecordQuest recordQuest);

	List<RecordQuest> getRecordQuestList(RecordQuest recordQuest);
}
