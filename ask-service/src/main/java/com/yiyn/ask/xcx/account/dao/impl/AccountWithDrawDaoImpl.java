package com.yiyn.ask.xcx.account.dao.impl;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.account.po.AccountWithDrawPo;
@Repository("accountWithDrawDao")
public class AccountWithDrawDaoImpl extends BaseDao<AccountWithDrawPo> {

	public String getNameStatement() {
		return "yiyin.withdraw";
	}
}
