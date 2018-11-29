package com.beadhouse.domen;

import java.io.Serializable;
import java.util.Date;

public class Image implements Serializable {
	/**
	 *
	 */
	private Integer imageId;
	private String imageUrl;
	private int loginUserId;

	public Image(String imageUrl, int loginUserId) {
		this.imageUrl = imageUrl;
		this.loginUserId = loginUserId;
	}

	public Image(Integer imageId, String imageUrl, int loginUserId) {
		this.imageId = imageId;
		this.imageUrl = imageUrl;
		this.loginUserId = loginUserId;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(int loginUserId) {
		this.loginUserId = loginUserId;
	}
}
