package com.beadhouse.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
public class BasicData<T> extends Basic {
	public BasicData(T data){
		super();
		this.data = data;	
	}

	public BasicData(){}
	public BasicData(int status,String msg){super(status,msg);}
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private T data;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public static BasicData CreateSucess() {
		return new BasicData(ErrorMsg.E200.getCode(),ErrorMsg.E200.getMsg());
	}
	public static<T> BasicData<T> CreateSucess(T data) {
		BasicData basicData = new BasicData(ErrorMsg.E200.getCode(),ErrorMsg.E200.getMsg());
		basicData.setData(data);
		return basicData;
	}
	public static BasicData CreateError() {
		BasicData basic = new BasicData(ErrorMsg.E901.getCode(),ErrorMsg.E901.getMsg());
		return basic;
	}
	public static BasicData CreateErrorMsg(String msg) {
		BasicData basic = new BasicData(ErrorMsg.E901.getCode(),msg);
		return basic;
	}
	public static BasicData CreateErrorInvalidUser() {
		BasicData basic = new BasicData(ErrorMsg.E900.getCode(),ErrorMsg.E900.getMsg());
		return basic;
	}
	
	public static BasicData CreateErrorInvalidchat() {
		BasicData basic = new BasicData(ErrorMsg.E905.getCode(),ErrorMsg.E905.getMsg());
		return basic;
	}
	@JsonIgnore
	public boolean isError() {
		return 200!=this.getStatus();
	}
}
