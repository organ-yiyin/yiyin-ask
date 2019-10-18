package com.yiyn.ask.base.constants;

public enum ContentTypeEnum {
	
	text("text","文字"),
	image("image","图片"),
	video("video","视频"),
	audio("audio","音频");
	
	String code;
	String name;
	
	private ContentTypeEnum(String code, String name){
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
