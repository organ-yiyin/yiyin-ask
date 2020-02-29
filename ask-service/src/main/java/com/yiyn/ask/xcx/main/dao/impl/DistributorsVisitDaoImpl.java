package com.yiyn.ask.xcx.main.dao.impl;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.main.po.DistributorsVisitPo;

@Repository("distributorsVisitDaoImpl")
public class DistributorsVisitDaoImpl extends BaseDao<DistributorsVisitPo> {
	
	public DistributorsVisitPo findByOpenId(String openId) throws Exception {
		return this.getSqlSession().selectOne(
				this.getNameStatement() + ".findByOpenId", openId);
	}
	
	public String getNameStatement() {
		return "yiyin.disVisit";
	}
}
