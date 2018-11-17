package com.beadhouse.out;

import java.util.Date;


public class Basic {
	
	private int status;
	private String msg;
	private Date time ;
	public Basic(){
		this.status=200;
		this.msg="成功";
	}
	
	public Basic(int status,String msg){
		this.status=status;
		this.msg=msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	


}
