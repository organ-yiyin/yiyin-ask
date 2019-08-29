package com.yiyn.ask.xcx.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.user.po.UserOrderSetPo;
@Repository("userOrderSetDao")
public class UserOrderSetDaoImpl extends BaseDao<UserOrderSetPo> {
	public String getNameStatement() {
		return "yiyin.userOrderSet";
	}
}
