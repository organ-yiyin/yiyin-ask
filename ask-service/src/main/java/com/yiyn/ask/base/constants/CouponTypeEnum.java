package com.yiyn.ask.base.constants;

import org.apache.commons.lang3.StringUtils;

public enum CouponTypeEnum {
	MJ_COUPON("1", "满减优惠券");

	String code;
	String name;

	private CouponTypeEnum(String code, String name) {
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

	public static CouponTypeEnum buildConsulantStatusByCode(String code) {
		if (StringUtils.isEmpty(code)) {
			return null;
		}
		for (CouponTypeEnum status : CouponTypeEnum.values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}
}
