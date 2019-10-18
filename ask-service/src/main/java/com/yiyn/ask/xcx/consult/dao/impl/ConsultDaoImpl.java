package com.yiyn.ask.xcx.consult.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.constants.YesOrNoType;
import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.base.utils.StringUtils;
import com.yiyn.ask.xcx.consult.po.ConsultPo;
@Repository("consultDao")
public class ConsultDaoImpl extends BaseDao<ConsultPo> {
	/**
	 * B端用户获取咨询单列表
	 * @param m
	 * @return
	 * @throws Exception
	 */
	public List<ConsultPo> getConsultList(Map<String,Object> m) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".getConsultList", m);
	}
	
	/**
	 * C端用户获取咨询单列表
	 * @param m
	 * @return
	 * @throws Exception
	 */
	public List<ConsultPo> getConsultCList(Map<String,Object> m) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".getConsultCList", m);
	}
	
	public ConsultPo getConsultInfo(String id) throws Exception {
		return this.getSqlSession().selectOne(
				this.getNameStatement() + ".getConsultInfo", id);
	}
	
	public ConsultPo getConsultByOdd_Num(String odd_num) throws Exception {
		return this.getSqlSession().selectOne(
				this.getNameStatement() + ".getConsultByOdd_Num", odd_num);
	}
	
	public void updateStatus(ConsultPo p)throws Exception {
		this.getSqlSession().update(this.getNameStatement() + ".updateStatus",p);
	}
	
	public void updConsult(ConsultPo p)throws Exception {
		this.getSqlSession().update(this.getNameStatement() + ".updConsult",p);
	}
	
	public String getNameStatement() {
		return "yiyin.consult";
	}
}
