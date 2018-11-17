package com.beadhouse.in;

public class MessageParam extends BasicIn {
	//当前页
	private int currentPage;
	
	private int offset;
	//每页行数
	private int pageSize;
	
	private String EmailAddress;
	private String elderUserEmail;
	//查询参数
	private String search;
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getOffset() {
		return pageSize* (currentPage-1);
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getEmailAddress() {
		return EmailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}
	public String getElderUserEmail() {
		return elderUserEmail;
	}
	public void setElderUserEmail(String elderUserEmail) {
		this.elderUserEmail = elderUserEmail;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	
	
	
}
