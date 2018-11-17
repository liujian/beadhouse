package com.beadhouse.domen;

import java.io.Serializable;
import java.util.Date;

public class ChatHistory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9162824339801302149L;
	
	private Integer chatId;

	private Integer loginUserId;
	
	private Integer elderUserId;
	
	private Integer questId;

	private Integer defineQuestId;
	
	private String quest;
	
	private String userVoiceUrl;
	
	private String voicequest;
	
	private Date questDate;
	
	private String elderUserResponse;
	
	private String elderUserVoiceUrl;
	
	private Date responseDate;

	public Integer getChatId() {
		return chatId;
	}

	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}

	public Integer getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(Integer loginUserId) {
		this.loginUserId = loginUserId;
	}

	public Integer getElderUserId() {
		return elderUserId;
	}

	public void setElderUserId(Integer elderUserId) {
		this.elderUserId = elderUserId;
	}

	public Integer getQuestId() {
		return questId;
	}

	public void setQuestId(Integer questId) {
		this.questId = questId;
	}

	public Integer getDefineQuestId() {
		return defineQuestId;
	}

	public void setDefineQuestId(Integer defineQuestId) {
		this.defineQuestId = defineQuestId;
	}


	public String getQuest() {
		return quest;
	}

	public void setQuest(String quest) {
		this.quest = quest;
	}

	public String getUserVoiceUrl() {
		return userVoiceUrl;
	}

	public void setUserVoiceUrl(String userVoiceUrl) {
		this.userVoiceUrl = userVoiceUrl;
	}

	public Date getQuestDate() {
		return questDate;
	}

	public void setQuestDate(Date questDate) {
		this.questDate = questDate;
	}

	public String getElderUserResponse() {
		return elderUserResponse;
	}

	public void setElderUserResponse(String elderUserResponse) {
		this.elderUserResponse = elderUserResponse;
	}

	public String getElderUserVoiceUrl() {
		return elderUserVoiceUrl;
	}

	public void setElderUserVoiceUrl(String elderUserVoiceUrl) {
		this.elderUserVoiceUrl = elderUserVoiceUrl;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public String getVoicequest() {
		return voicequest;
	}

	public void setVoicequest(String voicequest) {
		this.voicequest = voicequest;
	}

	
	
	
}
