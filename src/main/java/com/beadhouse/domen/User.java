package com.beadhouse.domen;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable{
	
	
    /**
	 * 用户信息表
	 */
	private static final long serialVersionUID = 762548305000257725L;

	
	private Integer id;
	
	private String EmailAddress;
	
	private String Password;
	
	private String firstName;
	
	private String lastName;
	
	private String phone;

	private String birthday;
	
	private String token;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	private Date createDate;
	
	private Date updateDate;
	private String userAvatar;

	private String fireBaseToken;

	private String googleLoginId;

	private String faceBookLoginId;

	private int notifyType;

	private Date notifyDate;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmailAddress() {
		return EmailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public String getFireBaseToken() {
		return fireBaseToken;
	}

	public void setFireBaseToken(String fireBaseToken) {
		this.fireBaseToken = fireBaseToken;
	}

	public String getGoogleLoginId() {
		return googleLoginId;
	}

	public void setGoogleLoginId(String googleLoginId) {
		this.googleLoginId = googleLoginId;
	}

	public String getFaceBookLoginId() {
		return faceBookLoginId;
	}

	public void setFaceBookLoginId(String faceBookLoginId) {
		this.faceBookLoginId = faceBookLoginId;
	}

	public int getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(int notifyType) {
		this.notifyType = notifyType;
	}

	public Date getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}
}