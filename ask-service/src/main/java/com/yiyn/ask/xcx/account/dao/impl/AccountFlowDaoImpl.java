package com.yiyn.ask.xcx.account.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.account.po.AccountFlowPo;
@Repository("accountFlowDao")
public class AccountFlowDaoImpl extends BaseDao<AccountFlowPo> {
	public List<AccountFlowPo> findResponseList(String userno) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".findResponseList", userno);
	}

	public String getNameStatement() {
		return "yiyin.accountFlow";
	}
}
