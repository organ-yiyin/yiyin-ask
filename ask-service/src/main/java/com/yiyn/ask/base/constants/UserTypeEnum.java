package com.yiyn.ask.base.constants;

public enum UserTypeEnum {
	
	MANAGER(1,"管理员"),SERVER(2,"服务人员");
	
	Integer code;
	String text;
	
	private UserTypeEnum(Integer code, String text){
		this.code = code;
		this.text = text;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
