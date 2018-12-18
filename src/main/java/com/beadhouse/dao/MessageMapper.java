package com.beadhouse.dao;

import java.util.List;

import com.beadhouse.in.GetMessageParam;
import com.beadhouse.out.ChatHistoryOut;
import org.apache.ibatis.annotations.Mapper;
import com.beadhouse.domen.ChatHistory;

@Mapper
public interface MessageMapper {
    void insertChatHistory(ChatHistory param);

    List<ChatHistoryOut> selectChatHistory(GetMessageParam getMessageParam);

    List<ChatHistoryOut> selectElderChatHistory(GetMessageParam getMessageParam);

    void updateQuestion(ChatHistory chatHistory);

    void updateAnswer(ChatHistory chatHistory);

    void updateMessageText(ChatHistory chatHistory);

    void updateAnswerText(ChatHistory chatHistory);

    ChatHistory selectChatByChatid(Integer chatid);

    List<ChatHistoryOut> getwaitquests(ChatHistory chatHistory);

    ChatHistoryOut getQuestById(ChatHistory chatHistory);
}
