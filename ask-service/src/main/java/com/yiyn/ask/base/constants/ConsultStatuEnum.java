package com.yiyn.ask.base.constants;

import org.apache.commons.lang3.StringUtils;

public enum ConsultStatuEnum {
	PAY_WAIT("1","待支付"),PAY("2","已支付"),REFUND_WAIT("3","待退款"),
	REFUND("4","已退款"),ANS("5","已回答"),END("6","已结束"),TIME_OUT("7","超时退款");
	
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
	
	public static ConsultStatuEnum buildConsulantStatusByCode(String code) {
		if(StringUtils.isEmpty(code)) {
			return null;
		}
		for(ConsultStatuEnum status : ConsultStatuEnum.values()) {
			if(status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}
}
