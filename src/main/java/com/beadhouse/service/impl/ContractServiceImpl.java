package com.beadhouse.service.impl;

import java.util.Date;
import java.util.List;

import com.beadhouse.domen.DefineQuest;
import com.beadhouse.in.DefineQuestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beadhouse.dao.ContactsMapper;
import com.beadhouse.dao.QuestMapper;
import com.beadhouse.dao.UserMapper;
import com.beadhouse.domen.Contact;
import com.beadhouse.domen.Quest;
import com.beadhouse.domen.User;
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

    @Override
    public BasicData<List<Contact>> getcontacts(ContactsParam param) {

        User user = userMapper.selectByToken(param.getToken());

        if (user == null) {
            return BasicData.CreateErrorInvalidUser();
        }

        List<Contact> list = contactsMapper.selectByLoginUserId(user.getId());

        return BasicData.CreateSucess(list);
    }

    @Override
    public BasicData<List<Quest>> getquestlist(ContactsParam param){
        User user = userMapper.selectByToken(param.getToken());

        if (user == null) {
            return BasicData.CreateErrorInvalidUser();
        }

        List<Quest> list = questMapper.getquestlist();
        return BasicData.CreateSucess(list);
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
