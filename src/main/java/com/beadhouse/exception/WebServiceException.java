package com.beadhouse.exception;

public class WebServiceException extends Exception {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9206431811370675586L;
	private String code;
	private String msg;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public WebServiceException(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	
	

}
