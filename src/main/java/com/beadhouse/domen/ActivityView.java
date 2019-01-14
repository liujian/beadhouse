package com.beadhouse.domen;

import java.io.Serializable;
import java.util.Date;

public class ActivityView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5783567914558746095L;
	
	private Integer id;
	
	private String activedate;
	
	private String activeview;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivedate() {
		return activedate;
	}

	public void setActivedate(String activedate) {
		this.activedate = activedate;
	}

	public String getActiveview() {
		return activeview;
	}

	public void setActiveview(String activeview) {
		this.activeview = activeview;
	}
	
	

}
