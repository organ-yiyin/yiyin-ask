package com.yiyn.ask.base.constants;

public enum ConsultingTypeEnum {
	
	BUYU(1,"哺乳"),ZAOCHANER(2,"早产儿"),ALL(9,"所有");
	
	Integer code;
	String text;
	
	private ConsultingTypeEnum(Integer code, String text){
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
