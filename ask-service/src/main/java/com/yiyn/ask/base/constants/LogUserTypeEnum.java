package com.yiyn.ask.base.constants;

public enum LogUserTypeEnum {
	
	USER_B("B","B端用户"),USER_C("C","C端用户"),USER_BG("BG","后台管理员");
	
	String code;
	String name;
	
	private LogUserTypeEnum(String code, String name){
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
