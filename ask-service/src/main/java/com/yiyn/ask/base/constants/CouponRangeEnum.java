package com.yiyn.ask.base.constants;

import org.apache.commons.lang3.StringUtils;

public enum CouponRangeEnum {
	ALL("1", "全场通用"),
	PART("2", "部分咨询师");

	String code;
	String name;

	private CouponRangeEnum(String code, String name) {
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

	public static CouponRangeEnum buildConsulantStatusByCode(String code) {
		if (StringUtils.isEmpty(code)) {
			return null;
		}
		for (CouponRangeEnum status : CouponRangeEnum.values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}
}
