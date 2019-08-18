package com.yiyn.ask.xcx.account.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.xcx.account.po.AccountWithDrawPo;


@Repository("userWithdrawDao")
public class UserWithdrawDaoImpl extends BaseDao<AccountWithDrawPo> {

	public List<Map> searchByConditions_bg(PaginationUtils paramPage) {
		return this.getSqlSession().selectList(this.getNameStatement() + ".searchByConditions_bg", paramPage);
	}

	public Integer searchCountByConditions_bg(PaginationUtils paramPage) throws Exception {
		return this.getSqlSession().selectOne(this.getNameStatement() + ".searchCountByConditions_bg", paramPage);
	}

	public String getNameStatement() {
		return "yiyin.withdraw";
	}
}
