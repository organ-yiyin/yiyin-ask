package com.yiyn.ask.base.constants;


public enum AttachmentTypeEnum {
	
	IDENTIFY_CERT("idnetify_cert","身份证明"),
	QUALIFICATION("qualification","资质证明"),
	OTHER("other","其他");
	
	String code;
	String name;
	
	private AttachmentTypeEnum(String code, String name){
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
