package com.yiyn.ask.base.constants;

public enum OrderSetEnum {
	
	Y_ORDER(1,"接单"),N_ORDER(2,"不接单");
	
	Integer code;
	String text;
	
	private OrderSetEnum(Integer code, String text){
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
