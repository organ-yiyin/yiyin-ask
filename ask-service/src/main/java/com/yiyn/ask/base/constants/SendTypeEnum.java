package com.yiyn.ask.base.constants;

public enum SendTypeEnum {
	
	customer("customer","客户"),
	server("server","咨询服务人员");
	
	String code;
	String name;
	
	private SendTypeEnum(String code, String name){
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
