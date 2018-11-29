package com.beadhouse.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.beadhouse.controller.MessageController;
import com.beadhouse.dao.CollectionMapper;
import com.beadhouse.dao.ElderUserMapper;
import com.beadhouse.dao.QuestMapper;
import com.beadhouse.dao.UserMapper;
import com.beadhouse.domen.DefineQuest;
import com.beadhouse.domen.ElderUser;
import com.beadhouse.domen.User;
import com.beadhouse.in.AnswerQuestParam;
import com.beadhouse.in.GetMessageParam;
import com.beadhouse.out.ChatHistoryOut;
import com.beadhouse.out.ChatHistoryOutList;
import com.beadhouse.redis.RedisService;

import com.beadhouse.utils.FireBaseUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beadhouse.dao.MessageMapper;
import com.beadhouse.domen.ChatHistory;
import com.beadhouse.in.SendMessageParam;
import com.beadhouse.in.UpdateChatParam;
import com.beadhouse.out.BasicData;
import com.beadhouse.service.SendMessageService;

@Service
public class SendMessageServiceImpl implements SendMessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ElderUserMapper elderUserMapper;
    @Autowired
    private QuestMapper questMapper;
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private RedisService redisService;
    @Value("${google.SERVER_IMAGE}")
    private String SERVER_IMAGE;

    private static final Logger logger = LoggerFactory.getLogger(SendMessageServiceImpl.class);

    @Override
    @Transactional
    public BasicData sendMessage(SendMessageParam param, String fileName, String fileText) {
        logger.info(param.getToken());
        User user = userMapper.selectByToken(param.getToken());
        if (user == null) {
            return BasicData.CreateErrorInvalidUser();
        }
        // if (fileName == null) return BasicData.CreateErrorMsg("文件上传失败");

        ChatHistory chatHistory = new ChatHistory();
        chatHistory.setLoginUserId(user.getId());
        chatHistory.setElderUserId(param.getElderUserId());
        if (param.getQuestId() != -1 && param.getQuestId() != 0) {
            chatHistory.setQuestId(param.getQuestId());
            chatHistory.setQuest(questMapper.getQuest(param.getQuestId()).getQuest());
        } else if (param.getDefineQuest() != null && !param.getDefineQuest().isEmpty()) {
            DefineQuest defineQuest = new DefineQuest();
            defineQuest.setCreateUserId(user.getId());
            defineQuest.setDefineQuest(param.getDefineQuest());
            defineQuest.setCreateDate(new Date());
            questMapper.insertDefineQuest(defineQuest);
            chatHistory.setDefineQuestId(defineQuest.getDefineQuestId());
            chatHistory.setQuest(param.getDefineQuest());
        }
        chatHistory.setVoicequest(fileText);
        chatHistory.setQuestDate(new Date());
        if (fileName != null) chatHistory.setUserVoiceUrl(SERVER_IMAGE + fileName);
        messageMapper.insertChatHistory(chatHistory);
        ElderUser elderUser = elderUserMapper.selectById(param.getElderUserId());
        try {
            ChatHistoryOut chatHistoryOut = messageMapper.getQuestById(chatHistory);
            String body = user.getFirstName() + " " + user.getLastName() + ":" + chatHistoryOut.getQuest();
            FireBaseUtil.pushFCMNotification("2", new Gson().toJson(chatHistoryOut), body, elderUser.getFireBaseToken());
            return BasicData.CreateSucess(chatHistory);
        } catch (IOException e) {
            System.out.println("e = " + e);
        }
        return BasicData.CreateSucess(chatHistory);
    }


    @Value("${redis.chatsexpiry}")
    private Long expiry;

    @Override
    public BasicData<ChatHistoryOutList> getChatHistory(GetMessageParam param) {
        System.out.println(param.getToken());
        User user = userMapper.selectByToken(param.getToken());
        //  ElderUser elderUser = elderUserMapper.selectByToken(param.getToken());
        if (user == null) {
            return BasicData.CreateErrorInvalidUser();
        }

        param.setLoginUserId(user.getId());
        Integer total = param.getTotal();
        Integer page = param.getPage();

        if (0 == total && 0 == param.getIsCollection()) {
            List<ChatHistoryOut> list = messageMapper.selectChatHistory(param);
            redisService.set(param.getElderUserId() + "|" + param.getToken() + list.size(), list, expiry);
            redisService.set(param.getElderUserId() + "|" + param.getToken() + list.size() + "size", list.size(), expiry);
            total = list.size();
        }
        if (0 == total && 1 == param.getIsCollection()) {
            List<ChatHistoryOut> list = messageMapper.selectChatHistory(param);
            redisService.set(param.getElderUserId() + param.getToken() + list.size(), list, expiry);
            redisService.set(param.getElderUserId() + param.getToken() + list.size() + "size", list.size(), expiry);
            total = list.size();
        }

        String key = null;
        if (1 == param.getIsCollection()) {
            key = param.getElderUserId() + param.getToken() + total;
        } else {
            key = param.getElderUserId() + "|" + param.getToken() + total;
        }

        if (!(redisService.exists(key) && redisService.exists(key + "size"))) {
            return BasicData.CreateErrorInvalidchat();
        }
        Integer totalsize = (Integer) redisService.getObj(key + "size");
        Integer startpage = null;
        Integer endpage = null;
        List<ChatHistoryOut> list = new ArrayList<ChatHistoryOut>();
        ChatHistoryOutList chatHistoryOutList = new ChatHistoryOutList();
        chatHistoryOutList.setTotal(total);
        if (page * 10 > totalsize) {
            chatHistoryOutList.setChatHistoryOuts(list);
            return BasicData.CreateSucess(chatHistoryOutList);
        } else {
            startpage = page * 10;
            endpage = page * 10 + 10;
            if ((page * 10 + 10) > totalsize) {
                endpage = totalsize;
            }
            List<ChatHistoryOut> chatHistoryOuts = (List) redisService.getObj(key);
            list = chatHistoryOuts.subList(startpage, endpage);
        }

        chatHistoryOutList.setChatHistoryOuts(list);

        return BasicData.CreateSucess(chatHistoryOutList);
    }


    @Override
    public BasicData<ChatHistoryOutList> getElderChatHistory(GetMessageParam param) {
        ElderUser user = elderUserMapper.selectByToken(param.getToken());
        if (user == null) {
            return BasicData.CreateErrorInvalidUser();
        }

        param.setElderUserId(user.getElderUserId());
        Integer total = param.getTotal();
        Integer page = param.getPage();

        if (0 == total && 0 == param.getIsCollection()) {
            List<ChatHistoryOut> list = messageMapper.selectElderChatHistory(param);
            redisService.set(param.getElderUserId() + "|" + param.getToken() + list.size(), list, expiry);
            redisService.set(param.getElderUserId() + "|" + param.getToken() + list.size() + "size", list.size(), expiry);
            total = list.size();
        }
        if (0 == total && 1 == param.getIsCollection()) {
            List<ChatHistoryOut> list = messageMapper.selectElderChatHistory(param);
            redisService.set(param.getElderUserId() + param.getToken() + list.size(), list, expiry);
            redisService.set(param.getElderUserId() + param.getToken() + list.size() + "size", list.size(), expiry);
            total = list.size();
        }

        String key = null;
        if (1 == param.getIsCollection()) {
            key = param.getElderUserId() + param.getToken() + total;
        } else {
            key = param.getElderUserId() + "|" + param.getToken() + total;
        }

        if (!(redisService.exists(key) && redisService.exists(key + "size"))) {
            return BasicData.CreateErrorInvalidchat();
        }
        Integer totalsize = (Integer) redisService.getObj(key + "size");
        Integer startpage = null;
        Integer endpage = null;
        List<ChatHistoryOut> list = new ArrayList<ChatHistoryOut>();
        ChatHistoryOutList chatHistoryOutList = new ChatHistoryOutList();
        chatHistoryOutList.setTotal(total);
        if (page * 10 > totalsize) {
            chatHistoryOutList.setChatHistoryOuts(list);
            return BasicData.CreateSucess(chatHistoryOutList);
        } else {
            startpage = page * 10;
            endpage = page * 10 + 10;
            if ((page * 10 + 10) > totalsize) {
                endpage = totalsize;
            }
            List<ChatHistoryOut> chatHistoryOuts = (List) redisService.getObj(key);
            list = chatHistoryOuts.subList(startpage, endpage);
        }

        chatHistoryOutList.setChatHistoryOuts(list);

        return BasicData.CreateSucess(chatHistoryOutList);
    }


    @Override
    @Transactional
    public BasicData answerQuestion(AnswerQuestParam param, String fileName, String fileText) {
        ElderUser elderUser = elderUserMapper.selectByToken(param.getToken());
        if (elderUser == null) {
            return BasicData.CreateErrorInvalidUser();
        }
//        if (fileName == null) return BasicData.CreateErrorMsg("文件上传失败");
//        if (fileText == null) return BasicData.CreateErrorMsg("翻译失败");

        ChatHistory chatHistory = new ChatHistory();
        chatHistory.setChatId(param.getChatId());
        chatHistory.setResponseDate(new Date());
        chatHistory.setElderUserVoiceUrl(SERVER_IMAGE + fileName);
        chatHistory.setElderUserResponse(fileText);
        messageMapper.updateAnswer(chatHistory);

        ChatHistoryOut chatHistoryOut = messageMapper.getQuestById(chatHistory);

        try {
            String body = elderUser.getElderFirstName() + " " + elderUser.getElderLastName() + ":" + chatHistoryOut.getElderUserResponse();
            FireBaseUtil.pushFCMNotification("3", new Gson().toJson(chatHistoryOut), body, userMapper.selectById(chatHistoryOut.getLoginUserId()).getFireBaseToken());
            return BasicData.CreateSucess(chatHistory);
        } catch (IOException e) {
            System.out.println("e = " + e);
        }

        return BasicData.CreateSucess(chatHistory);
    }


    @Override
    public BasicData updateChatHistory(UpdateChatParam param) {
        User user = userMapper.selectByToken(param.getToken());
        if (user == null) {
            return BasicData.CreateErrorInvalidUser();
        }
        ChatHistory chat = messageMapper.selectChatByChatid(param.getChatid());
        if (chat != null && chat.getElderUserId().equals(param.getElderUserId()) && chat.getLoginUserId().equals(user.getId())) {
            chat.setVoicequest(param.getVoicequest());
            chat.setElderUserResponse(param.getElderUserResponse());
            messageMapper.updatechat(chat);
        } else {
            return BasicData.CreateErrorMsg("You can only change the chat between yourself and the elderly!");
        }
        return BasicData.CreateSucess(chat);
    }


    @Override
    public BasicData updateChatHistoryByElder(UpdateChatParam param) {
        ElderUser user = elderUserMapper.selectByToken(param.getToken());
        if (user == null) {
            return BasicData.CreateErrorInvalidUser();
        }
        ChatHistory chat = messageMapper.selectChatByChatid(param.getChatid());
        if (chat != null && chat.getElderUserId().equals(user.getElderUserId())) {
            chat.setVoicequest(param.getVoicequest());
            chat.setElderUserResponse(param.getElderUserResponse());
            messageMapper.updatechat(chat);
        } else {
            return BasicData.CreateErrorMsg("You can only modify your own chat history!");
        }
        return BasicData.CreateSucess(chat);
    }


}
