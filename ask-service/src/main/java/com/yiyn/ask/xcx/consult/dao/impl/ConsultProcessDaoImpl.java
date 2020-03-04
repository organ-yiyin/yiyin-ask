package com.yiyn.ask.xcx.consult.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.constants.YesOrNoType;
import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.base.utils.StringUtils;
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
	
	public Long insPro(ConsultProcessPo p) throws Exception{
		int num = this.getSqlSession().insert(this.getNameStatement() + ".insert", p);
		return p.getId();
	}
	
	public String getNameStatement() {
		return "yiyin.consultProcess";
	}
}
