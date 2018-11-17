package com.beadhouse.in;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class GetCodeParam extends BasicIn {
//	@Size(max=10, min=12)
	@NotNull
	private String emailAddress;
	
	private String type;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	




}
