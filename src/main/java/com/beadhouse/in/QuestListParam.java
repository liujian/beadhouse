package com.beadhouse.in;

public class QuestListParam extends BasicIn{
	private String token;
	private int elderUserId;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getElderUserId() {
		return elderUserId;
	}

	public void setElderUserId(int elderUserId) {
		this.elderUserId = elderUserId;
	}
}
