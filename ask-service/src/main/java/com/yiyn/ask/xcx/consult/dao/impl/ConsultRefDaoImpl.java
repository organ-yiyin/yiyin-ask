package com.yiyn.ask.xcx.consult.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.consult.po.ConsultRefPo;
@Repository("consultRefDao")
public class ConsultRefDaoImpl extends BaseDao<ConsultRefPo> {
	/**
	 * 用户端咨询人信息列表
	 * @param m
	 * @return
	 * @throws Exception
	 */
	public List<ConsultRefPo> getRefList(Map<String,Object> m) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".getRefList", m);
	}
	
	/**
	 * 根据咨询单关联咨询人信息
	 * @param m
	 * @return
	 * @throws Exception
	 */
	public ConsultRefPo getConsultRefInfo(Map<String,Object> m) throws Exception {
		return this.getSqlSession().selectOne(
				this.getNameStatement() + ".getConsultRefInfo", m);
	}

	public String getNameStatement() {
		return "yiyin.consultRef";
	}
}
