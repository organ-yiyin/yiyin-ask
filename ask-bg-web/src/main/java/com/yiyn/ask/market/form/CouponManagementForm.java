package com.yiyn.ask.market.form;

import com.yiyn.ask.base.constants.CouponRangeEnum;
import com.yiyn.ask.base.constants.CouponStatusEnum;
import com.yiyn.ask.base.constants.CouponTypeEnum;
import com.yiyn.ask.base.utils.PaginationUtils;

public class CouponManagementForm extends PaginationUtils{
	
	private CouponStatusEnum[] couponStatusList = CouponStatusEnum.values();
	
	private CouponTypeEnum[] couponTypeList = CouponTypeEnum.values();
	
	private CouponRangeEnum[] couponRangeList = CouponRangeEnum.values();
	
	public CouponManagementForm(){
		super();
	}

	public CouponStatusEnum[] getCouponStatusList() {
		return couponStatusList;
	}

	public void setCouponStatusList(CouponStatusEnum[] couponStatusList) {
		this.couponStatusList = couponStatusList;
	}

	public CouponTypeEnum[] getCouponTypeList() {
		return couponTypeList;
	}

	public void setCouponTypeList(CouponTypeEnum[] couponTypeList) {
		this.couponTypeList = couponTypeList;
	}

	public CouponRangeEnum[] getCouponRangeList() {
		return couponRangeList;
	}

	public void setCouponRangeList(CouponRangeEnum[] couponRangeList) {
		this.couponRangeList = couponRangeList;
	}
	
}
