package com.yiyn.ask.xcx.center.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.center.po.DistributorsPo;
@Repository("distributorsDao")
public class DistributorsDaoImpl extends BaseDao<DistributorsPo> {
	public List<DistributorsPo> findDisList() throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".findDisList");
	}
	
	public DistributorsPo findDisInfo(String id) throws Exception {
		return this.getSqlSession().selectOne(
				this.getNameStatement() + ".findDisInfo", id);
	}

	public String getNameStatement() {
		return "yiyin.dis";
	}
}
