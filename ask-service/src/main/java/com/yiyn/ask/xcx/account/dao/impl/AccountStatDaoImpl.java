package com.yiyn.ask.xcx.account.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.account.po.AccountStatPo;
@Repository("accountStatDao")
public class AccountStatDaoImpl extends BaseDao<AccountStatPo> {
	public List<AccountStatPo> getAcountStatM(Map<String,Object> m) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".getAcountStatM", m);

	}
	public String getNameStatement() {
		return "yiyin.accountStat";
	}
}
