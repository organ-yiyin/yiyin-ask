package com.yiyn.ask.market.dao.impl;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.market.po.CouponPo;

@Repository("couponDao")
public class CouponDaoImpl extends BaseDao<CouponPo> {
	
	@Override
	public String getNameStatement() {
		return "market.coupon";
	}
	
}
