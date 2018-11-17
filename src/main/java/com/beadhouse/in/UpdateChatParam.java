package com.beadhouse.in;

public class UpdateChatParam extends BasicIn {
	private String token;
	
	private Integer elderUserId;
	
	private Integer chatid;
	
	private String voicequest;
	
	private String elderUserResponse;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getElderUserId() {
		return elderUserId;
	}

	public void setElderUserId(Integer elderUserId) {
		this.elderUserId = elderUserId;
	}

	public Integer getChatid() {
		return chatid;
	}

	public void setChatid(Integer chatid) {
		this.chatid = chatid;
	}

	public String getVoicequest() {
		return voicequest;
	}

	public void setVoicequest(String voicequest) {
		this.voicequest = voicequest;
	}

	public String getElderUserResponse() {
		return elderUserResponse;
	}

	public void setElderUserResponse(String elderUserResponse) {
		this.elderUserResponse = elderUserResponse;
	}
	
	
	
}
