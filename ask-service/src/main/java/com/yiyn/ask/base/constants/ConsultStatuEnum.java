package com.yiyn.ask.base.constants;

public enum ConsultStatuEnum {
	PAY_WAIT("1","待支付"),PAY("2","已支付"),REFUND_WAIT("3","待退款"),
	REFUND("4","已退款"),ANS("5","已回答"),END("6","已结束");
	
	String code;
	String name;
	
	private ConsultStatuEnum(String code, String name){
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
