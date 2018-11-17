package com.beadhouse.in;

import java.util.List;

public class ElderSendCodeParam extends BasicIn {


    private String token;
    
    private List<String> list;
    
    private String code;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
	
	
}
