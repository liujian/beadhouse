package com.beadhouse.service;

import java.util.List;

import com.beadhouse.in.*;
import com.beadhouse.out.ChatHistoryOut;
import com.beadhouse.out.ChatHistoryOutList;

import org.springframework.web.multipart.MultipartFile;

import com.beadhouse.domen.ChatHistory;
import com.beadhouse.out.BasicData;

public interface SendMessageService {
	
	/**
	 * 发送消息
	 */
	BasicData sendMessage(SendMessageParam param,String fileName,String fileText);

	/**
	 * 回答问题
	 */
	BasicData answerQuestion(AnswerQuestParam param, String fileName, String fileText);
	
	
	/**
	 * 获取历史聊天记录
	 */
	 BasicData<ChatHistoryOutList> getChatHistory(GetMessageParam param );
	 /**
	  * 获取老人端聊天记录
	  */
	 BasicData<ChatHistoryOutList> getElderChatHistory(GetMessageParam param);
	 
	 /**
	  * 修改聊天记录
	  */
	 
	 BasicData updateChatHistory(UpdateChatParam param);

	/**
	 * 老人修改聊天记录
	 */

	BasicData updateChatHistoryByElder(UpdateChatParam param);

	/**
	 * 家人再问一次这个问题
	 */

	BasicData askAgain(AskAgainParam param);

}
