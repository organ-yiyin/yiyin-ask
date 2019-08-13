package com.yiyn.ask.xcx.consult.dao.impl;


import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.consult.po.ConsultLogPo;
@Repository("consultLogDao")
public class ConsultLogDaoImpl extends BaseDao<ConsultLogPo> {
	
	public String getNameStatement() {
		return "yiyin.consultLog";
	}
}
