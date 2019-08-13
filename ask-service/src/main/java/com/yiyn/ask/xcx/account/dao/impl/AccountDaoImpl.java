package com.yiyn.ask.xcx.account.dao.impl;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.account.po.AccountPo;
@Repository("accountDao")
public class AccountDaoImpl extends BaseDao<AccountPo> {
	public AccountPo getAccountInfo(String userno) throws Exception {
		return this.getSqlSession().selectOne(
				this.getNameStatement() + ".getAccountInfo", userno);
	}

	public String getNameStatement() {
		return "yiyin.account";
	}
}
