package com.beadhouse.domen;

import java.io.Serializable;
import java.util.Date;

public class ElderUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4679949845737084980L;

	private Integer elderUserId;

	private String elderUserEmail;
	
	private String elderFirstName;
	
	private String elderLastName;
	
	private String elderBirthday;
	
	private Integer elderUserAge;
	
	private String elderUserAddress;
	
	private String elderUserPassword;
	
	private String elderUserPhone;
	
	private String elderUserOther;
	
	private Date createDate;
	
	private String createUser;
	
	private String modificationUser;
	
	private Date modificationDate;

	private String token;
	
	private String elderAvatar;

	private String fireBaseToken;
	

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

	public String getElderBirthday() {
		return elderBirthday;
	}

	public void setElderBirthday(String elderBirthday) {
		this.elderBirthday = elderBirthday;
	}

	public String getElderAvatar() {
		return elderAvatar;
	}

	public void setElderAvatar(String elderAvatar) {
		this.elderAvatar = elderAvatar;
	}

	public Integer getElderUserId() {
		return elderUserId;
	}

	public void setElderUserId(Integer elderUserId) {
		this.elderUserId = elderUserId;
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

	public String getElderUserAddress() {
		return elderUserAddress;
	}

	public void setElderUserAddress(String elderUserAddress) {
		this.elderUserAddress = elderUserAddress;
	}

	

	public String getElderUserPassword() {
		return elderUserPassword;
	}

	public void setElderUserPassword(String elderUserPassword) {
		this.elderUserPassword = elderUserPassword;
	}

	public String getElderUserPhone() {
		return elderUserPhone;
	}

	public void setElderUserPhone(String elderUserPhone) {
		this.elderUserPhone = elderUserPhone;
	}

	public String getElderUserOther() {
		return elderUserOther;
	}

	public void setElderUserOther(String elderUserOther) {
		this.elderUserOther = elderUserOther;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getModificationUser() {
		return modificationUser;
	}

	public void setModificationUser(String modificationUser) {
		this.modificationUser = modificationUser;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public String getFireBaseToken() {
		return fireBaseToken;
	}

	public void setFireBaseToken(String fireBaseToken) {
		this.fireBaseToken = fireBaseToken;
	}
}
