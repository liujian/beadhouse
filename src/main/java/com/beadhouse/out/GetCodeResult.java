package com.beadhouse.out;

import java.io.Serializable;


public class GetCodeResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6991195194956670203L;
	private boolean flag ;
	private String code="";
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
