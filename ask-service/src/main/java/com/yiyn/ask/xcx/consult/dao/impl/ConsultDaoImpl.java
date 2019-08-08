package com.yiyn.ask.xcx.consult.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.consult.po.ConsultPo;
@Repository("consultDao")
public class ConsultDaoImpl extends BaseDao<ConsultPo> {
	public List<ConsultPo> getConsultList(Map<String,Object> m) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".getConsultList", m);
	}
	
	public ConsultPo getConsultInfo(String id) throws Exception {
		return this.getSqlSession().selectOne(
				this.getNameStatement() + ".getConsultInfo", id);
	}

	public void updStatus(Map<String,Object> m) throws Exception {
		this.getSqlSession().update(this.getNameStatement() + ".updStatus", m);
	}
	public String getNameStatement() {
		return "yiyin.consult";
	}
}
