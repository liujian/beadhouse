package com.beadhouse.exception;

public class GlobalRuntimeException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2186035662191197848L;
	private CodeMsg cm;
	
	public GlobalRuntimeException(CodeMsg cm) {
		super(cm.toString());
		this.cm = cm;
	}
	
	public GlobalRuntimeException(String msg) {
		super(msg);
		CodeMsg cm = new CodeMsg(CodeMsg.COMMON_ERROR_CODE, msg);	
		this.cm = cm;
	}

	public CodeMsg getCm() {
		return cm;
	}

}
