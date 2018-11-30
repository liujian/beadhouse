package com.beadhouse.out;
import java.io.Serializable;
import java.util.Date;

public class ChatHistoryOut implements Serializable {

	private Integer chatId;

	private Integer loginUserId;
	
	private Integer elderUserId;
	
	private Integer questId;

	private Integer defineQuestId;
	
	private Integer collectionid;

	private String quest;
	
	private String userVoiceUrl;
	
	private String voicequest;
	
	private Date questDate;
	
	private String elderUserResponse;
	
	private String elderUserVoiceUrl;
	
	private Date responseDate;

	private String firstName;

	private String lastName;
	
	private String elderFirstName;
	
	private String elderLastName;
	
	private String elderAvatar;
	
	private String userAvatar;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getCollectionid() {
		return collectionid;
	}

	public void setCollectionid(Integer collectionid) {
		this.collectionid = collectionid;
	}

	public String getVoicequest() {
		return voicequest;
	}

	public void setVoicequest(String voicequest) {
		this.voicequest = voicequest;
	}

	public String getElderFirstName() {
		return elderFirstName;
	}

	public void setElderFirstName(String elderFirstName) {
		this.elderFirstName = elderFirstName;
	}

	public String getElderLastName() {
		return elderLastName;
	}

	public void setElderLastName(String elderLastName) {
		this.elderLastName = elderLastName;
	}

	public String getElderAvatar() {
		return elderAvatar;
	}

	public void setElderAvatar(String elderAvatar) {
		this.elderAvatar = elderAvatar;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	
	
}
