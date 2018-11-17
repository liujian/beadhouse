package com.beadhouse.in;

public class GetMessageParam extends BasicIn {
	private String token;
	
	private Integer loginUserId;

	private Integer elderUserId;
	
	private String search;
	
	private Integer isCollection;
	
	private Integer page;
	
	private Integer total;


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getElderUserId() {
		return elderUserId;
	}

	public void setElderUserId(int elderUserId) {
		this.elderUserId = elderUserId;
	}

	public int getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(int loginUserId) {
		this.loginUserId = loginUserId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Integer getIsCollection() {
		return isCollection;
	}

	public void setIsCollection(Integer isCollection) {
		this.isCollection = isCollection;
	}
	
	
}
