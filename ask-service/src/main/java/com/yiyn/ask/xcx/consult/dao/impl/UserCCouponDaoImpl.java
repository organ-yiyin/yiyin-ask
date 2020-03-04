package com.yiyn.ask.xcx.consult.dao.impl;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.consult.po.UserCCouponPo;

@Repository("userCCouponDao")
public class UserCCouponDaoImpl extends BaseDao<UserCCouponPo>  {

	@Override
	public String getNameStatement() {
		return "yiyin.userCCoupon";
	}

}
