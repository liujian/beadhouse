package com.beadhouse.domen;

import java.io.Serializable;

public class Theme implements Serializable {
	
	private static final long serialVersionUID = -4394337749651081673L;
	private String logo;
	private String color;

	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}


}
