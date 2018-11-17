package com.beadhouse.domen;

import java.io.Serializable;

public class Quest implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -4394337749651081673L;
	private Integer questId;
	private String quest;
	private Integer sort;

	public Integer getQuestId() {
		return questId;
	}
	public void setQuestId(Integer questId) {
		this.questId = questId;
	}
	public String getQuest() {
		return quest;
	}
	public void setQuest(String quest) {
		this.quest = quest;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
