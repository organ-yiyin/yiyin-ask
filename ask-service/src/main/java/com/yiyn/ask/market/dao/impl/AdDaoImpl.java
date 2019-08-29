package com.yiyn.ask.market.dao.impl;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.market.po.AdPo;

@Repository("adDao")
public class AdDaoImpl extends BaseDao<AdPo>{

	@Override
	public String getNameStatement() {
		return "market.ad";
	}

}
