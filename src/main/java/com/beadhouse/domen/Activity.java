package com.beadhouse.domen;

import java.io.Serializable;

public class Activity implements Serializable {
	/**
	 *
	 */
	private Integer activityId;
	private String activity;
	private Integer scheduleId;

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}
}
