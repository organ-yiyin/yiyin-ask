package com.yiyn.ask.base.constants;

public enum AdPositionEnum {
	
	INDEX_AD("ad-index-scroll","首页滚动广告");
	
	String code;
	String name;
	
	private AdPositionEnum(String code, String name){
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
