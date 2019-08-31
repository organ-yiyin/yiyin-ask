package com.yiyn.ask.base.constants;

public enum WithDrawStatusEnum {
	
	WAITING_APPROVE(1,"待审核"),
	APPROVED(2,"已审核"),
	PAID(3,"已打款"),
	REJECT(4,"驳回");
	
	Integer code;
	String name;
	
	private WithDrawStatusEnum(Integer code, String name){
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
	
	public static WithDrawStatusEnum findByCode(Integer code) {
		for(WithDrawStatusEnum item : WithDrawStatusEnum.values()) {
			if(item.getCode().equals(code)) {
				return item;
			}
		}
		return null;
	}
}
