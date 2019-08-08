package com.yiyn.ask.xcx.center.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.center.po.CenterResponsePo;
@Repository("centerResponseDao")
public class CenterResponseDaoImpl extends BaseDao<CenterResponsePo> {
	public List<CenterResponsePo> findResponseList(String userno) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".findResponseList", userno);
	}

	public String getNameStatement() {
		return "yiyin.center";
	}
}
