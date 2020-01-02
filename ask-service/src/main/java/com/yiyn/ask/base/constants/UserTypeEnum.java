package com.yiyn.ask.base.constants;

public enum UserTypeEnum {
	
	MANAGER(1,"内部"),SERVER(2,"外部");
	
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

	public static UserTypeEnum findEnumByCode(Integer code) {
		if(code == null) {
			return null;
		}
		for(UserTypeEnum status : UserTypeEnum.values()) {
			if(status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}
	
}
