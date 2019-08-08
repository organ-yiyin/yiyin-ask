package com.yiyn.ask.xcx.center.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.center.po.CodePo;
@Repository("codeDao")
public class CodeDaoImpl extends BaseDao<CodePo> {
	public List<CodePo> findCodeList(String code_type) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".findCodeList", code_type);
	}

	public String getNameStatement() {
		return "yiyin.code";
	}
}
