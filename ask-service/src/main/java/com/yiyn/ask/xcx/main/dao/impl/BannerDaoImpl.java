package com.yiyn.ask.xcx.main.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.main.po.BannerPo;
@Repository("bannerDao")
public class BannerDaoImpl extends BaseDao<BannerPo> {
	public List<BannerPo> getBannerList() throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".getBannerList");
	}

	public String getNameStatement() {
		return "yiyin.banner";
	}
}
