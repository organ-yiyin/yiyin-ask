package com.yiyn.ask.market.form;

import com.yiyn.ask.base.constants.CouponRangeEnum;
import com.yiyn.ask.base.constants.CouponStatuEnum;
import com.yiyn.ask.base.constants.CouponTypeEnum;
import com.yiyn.ask.base.utils.PaginationUtils;

public class CouponManagementForm extends PaginationUtils{
	
	private CouponStatuEnum[] couponStatusList = CouponStatuEnum.values();
	
	private CouponTypeEnum[] couponTypeList = CouponTypeEnum.values();
	
	private CouponRangeEnum[] couponRangeList = CouponRangeEnum.values();
	
	public CouponManagementForm(){
		super();
	}

	public CouponStatuEnum[] getCouponStatusList() {
		return couponStatusList;
	}

	public void setCouponStatusList(CouponStatuEnum[] couponStatusList) {
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
