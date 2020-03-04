package com.yiyn.ask.base.constants;

import org.apache.commons.lang3.StringUtils;

public enum CouponStatusEnum {
	UN_EFFECTIVE("0", "未发布"), EFFECTIVE("1", "已发布"), ROLLBACK("2", "撤回"), EXPIREDS("3", "到期下线");

	String code;
	String name;

	private CouponStatusEnum(String code, String name) {
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

	public static CouponStatusEnum buildConsulantStatusByCode(String code) {
		if (StringUtils.isEmpty(code)) {
			return null;
		}
		for (CouponStatusEnum status : CouponStatusEnum.values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}
}
