package com.yiyn.ask.base.constants;

public enum OrderSetEnum {
	
	Y_ORDER(1,"不接单"),N_ORDER(2,"接单");
	
	Integer code;
	String name;
	
	private OrderSetEnum(Integer code, String name){
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
