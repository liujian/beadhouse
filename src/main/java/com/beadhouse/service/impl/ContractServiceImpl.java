package com.beadhouse.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.beadhouse.dao.ElderUserMapper;
import com.beadhouse.domen.*;
import com.beadhouse.in.DefineQuestParam;
import com.beadhouse.in.QuestListParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beadhouse.dao.ContactsMapper;
import com.beadhouse.dao.QuestMapper;
import com.beadhouse.dao.UserMapper;
import com.beadhouse.in.ContactsParam;
import com.beadhouse.out.BasicData;
import com.beadhouse.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContactsMapper contactsMapper;

    @Autowired
    private QuestMapper questMapper;

    @Autowired
    private UserMapper userMapper;

    public BasicData<List<Contact>> getContacts(ContactsParam param) {

        User user = userMapper.selectByToken(param.getToken());

        if (user == null) {
            return BasicData.CreateErrorInvalidUser();
        }

        List<Contact> list = contactsMapper.selectByLoginUserId(user.getId());

        return BasicData.CreateSucess(list);
    }

    @Override
    public BasicData<List<Quest>> getQuestList(QuestListParam param) {
        User user = userMapper.selectByToken(param.getToken());
        if (user == null) {
            return BasicData.CreateErrorInvalidUser();
        }
        List<Quest> questList = questMapper.getQuestList();
        List<Quest> resultQuestList = new ArrayList<>();
        resultQuestList.addAll(questList);
        RecordQuest saveRecordQuest = new RecordQuest();
        saveRecordQuest.setLoginUserId(user.getId());
        saveRecordQuest.setElderUserId(param.getElderUserId());
        List<RecordQuest> recordQuestList = questMapper.getRecordQuestList(saveRecordQuest);
        for (RecordQuest recordQuest : recordQuestList) {
            for (int i = 0; i < questList.size(); i++) {
                if (questList.get(i).getQuestId() == recordQuest.getQuestId()) {
                    resultQuestList.remove(questList.get(i));
                    break;
                }
            }
        }
        return BasicData.CreateSucess(resultQuestList);
    }

    @Override
    public BasicData saveDefineQuest(DefineQuestParam param) {
        User user = userMapper.selectByToken(param.getToken());
        if (user == null) {
            return BasicData.CreateErrorInvalidUser();
        }
        if (param.getDefineQuest() == null || param.getDefineQuest().isEmpty()) {
            return BasicData.CreateErrorMsg("问题不能为空");
        }

        DefineQuest defineQuest = new DefineQuest();
        defineQuest.setCreateUserId(user.getId());
        defineQuest.setDefineQuest(param.getDefineQuest());
        defineQuest.setCreateDate(new Date());
        questMapper.insertDefineQuest(defineQuest);
        return BasicData.CreateSucess(defineQuest.getDefineQuestId());
    }
}
