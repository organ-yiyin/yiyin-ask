package com.yiyn.ask.base.constants;

public enum UserTypeEnum {
	
	MANAGER(1,"管理员"),SERVER(2,"服务人员");
	
	Integer code;
	String name;
	
	private UserTypeEnum(Integer code, String name){
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
