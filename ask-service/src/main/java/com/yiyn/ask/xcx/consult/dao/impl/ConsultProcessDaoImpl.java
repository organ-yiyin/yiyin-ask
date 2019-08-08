package com.yiyn.ask.xcx.consult.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.consult.po.ConsultProcessPo;
@Repository("consultProcessDao")
public class ConsultProcessDaoImpl extends BaseDao<ConsultProcessPo> {
	/**
	 * 咨询进程列表对话框
	 * @param m
	 * @return
	 * @throws Exception
	 */
	public List<ConsultProcessPo> getConsultProcessList(String v) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".getConsultProcessList", v);
	}
	
	public String getNameStatement() {
		return "yiyin.consultProcess";
	}
}
