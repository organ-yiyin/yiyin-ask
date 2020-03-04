package com.yiyn.ask.base.constants;

import java.math.BigDecimal;

public enum UserTypeEnum {
	
	MANAGER(1,"内部", BigDecimal.valueOf(0.3)),SERVER(2,"外部", BigDecimal.valueOf(0.7));
	
	Integer code;
	String name;
	BigDecimal percent;
	
	private UserTypeEnum(Integer code, String name, BigDecimal percent){
		this.code = code;
		this.name = name;
		this.percent = percent;
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
	
	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
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
