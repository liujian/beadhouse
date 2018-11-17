package com.beadhouse.domen;

import java.io.Serializable;
import java.util.Date;

public class Contact implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer loginUserId;
	
	private String firstName;
	
	private String lastName;
	
	private String userAvatar;
	
	private Integer elderUserId;
	
	private String elderFirstName;
	
	private String elderLastName;
	
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

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
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

	private Date createDate;
	
	private String elderAvatar;

	public String getElderAvatar() {
		return elderAvatar;
	}

	public void setElderAvatar(String elderAvatar) {
		this.elderAvatar = elderAvatar;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

}
