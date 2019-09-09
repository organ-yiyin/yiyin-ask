package com.yiyn.ask.base.constants;

public enum CommentsStarEnum {
	
	star_0(0,"0星"),
	star_1(1,"1星"),
	star_2(2,"2星"),
	star_3(3,"3星"),
	star_4(4,"4星"),
	star_5(5,"5星");

	Integer code;
	String name;
		
	private CommentsStarEnum(Integer code, String name){
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
