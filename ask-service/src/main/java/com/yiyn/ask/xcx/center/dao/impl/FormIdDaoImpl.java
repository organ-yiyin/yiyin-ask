package com.yiyn.ask.xcx.center.dao.impl;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.center.po.FormIdPo;

@Repository("formIdDao")
public class FormIdDaoImpl extends BaseDao<FormIdPo> {
	
	public FormIdPo getFormId(String rel_user) throws Exception {
		return this.getSqlSession().selectOne(
				this.getNameStatement() + ".getFormId", rel_user);
	}
	public String getNameStatement() {
		return "yiyin.formid";
	}
}
