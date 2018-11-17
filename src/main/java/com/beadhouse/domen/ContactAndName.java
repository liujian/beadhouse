package com.beadhouse.domen;

import java.io.Serializable;

public class ContactAndName implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer elderUserId;

	private String elderUserName;

	private String elderUserEmail;

	private Integer elderUserAge;

	public Integer getElderUserId() {
		return elderUserId;
	}

	public void setElderUserId(Integer elderUserId) {
		this.elderUserId = elderUserId;
	}

	public String getElderUserName() {
		return elderUserName;
	}

	public void setElderUserName(String elderUserName) {
		this.elderUserName = elderUserName;
	}

	public String getElderUserEmail() {
		return elderUserEmail;
	}

	public void setElderUserEmail(String elderUserEmail) {
		this.elderUserEmail = elderUserEmail;
	}

	public Integer getElderUserAge() {
		return elderUserAge;
	}

	public void setElderUserAge(Integer elderUserAge) {
		this.elderUserAge = elderUserAge;
	}
}
