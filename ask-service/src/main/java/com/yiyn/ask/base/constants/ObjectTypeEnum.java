package com.yiyn.ask.base.constants;

public enum ObjectTypeEnum {
	
	CONSULTANT_ATTACHMENT(1,"雇员资料","object_id与user_b.id进行关联"),
	ORDER_ATTACHMENT(2,"订单附件","object_id与consultation_sheet.id进行关联");
	
	Integer code;
	String name;
	String description;
	
	private ObjectTypeEnum(Integer code, String name, String description){
		this.code = code;
		this.name = name;
		this.name = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
