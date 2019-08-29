package com.yiyn.ask.xcx.account.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.account.po.AccountFlowPo;
@Repository("accountFlowDao")
public class AccountFlowDaoImpl extends BaseDao<AccountFlowPo> {
	public List<AccountFlowPo> findAccountFlowList(Map<String,Object> m) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".findAccountFlowList", m);
	}

	public String getNameStatement() {
		return "yiyin.accountFlow";
	}
}
