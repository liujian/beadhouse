package com.beadhouse.out;

public enum ErrorMsg {
	E200("Success", 200),
	E400("服务器不理解请求的语法。", 400), 
	E401("请求要求进行身份验证。", 401), 
	E403("服务器拒绝请求。",403), 
	E404("服务器找不到请求的接口。", 404), 
	E900("The current account is abnormal, please log in again!", 900),
	E901("操作失败", 901),
	E902("系统错误", 902),
	E903("没有权限", 903),
	E904("调用核心系统失败",904),
	E905("Access cache failed！",905);
	// 成员变量
	private String msg;
	private int code;

	// 构造方法
	private ErrorMsg(String msg, int code) {
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
