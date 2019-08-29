package com.yiyn.ask.base.constants;

public enum WithDrawTypeEnum {
	
	WECHAT("wechat","微信"),
	OFFLINE("offline","线下");
	
	String code;
	String name;
	
	private WithDrawTypeEnum(String code, String name){
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
