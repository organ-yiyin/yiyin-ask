package com.yiyn.ask.base.constants;

import org.apache.commons.lang3.StringUtils;

public enum DistributorSourceEnum {
	
	WECHAT("WECHAT", "公众号"),
	S_PROCODE("S_PROCODE", "小程序二维码");

	String code;
	String name;

	private DistributorSourceEnum(String code, String name) {
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
	
	public static DistributorSourceEnum findByCode(String code) {
		if (StringUtils.isEmpty(code)) {
			return null;
		}
		for (DistributorSourceEnum status : DistributorSourceEnum.values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}
}
