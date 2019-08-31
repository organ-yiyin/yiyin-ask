package com.yiyn.ask.xcx.consult.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.consult.po.ConsultLogPo;
@Repository("consultLogDao")
public class ConsultLogDaoImpl extends BaseDao<ConsultLogPo> {
	
	public List<ConsultLogPo> findByConsultId(Long consult_id){
		return this.getSqlSession().selectList(this.getNameStatement() + ".findByConsultId", consult_id);
	}
	
	public String getNameStatement() {
		return "yiyin.consultLog";
	}
}
