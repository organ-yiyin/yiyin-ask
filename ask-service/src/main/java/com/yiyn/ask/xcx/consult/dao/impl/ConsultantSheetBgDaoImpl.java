package com.yiyn.ask.xcx.consult.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.xcx.consult.po.ConsultPo;

@Repository("consultSheetDao_bg")
public class ConsultantSheetBgDaoImpl extends BaseDao<ConsultPo> {
	
	public List<Map> searchByConditions_bg(PaginationUtils paramPage){
		return this.getSqlSession().selectList(this.getNameStatement() + ".searchByConditions", paramPage);
	}
	
	public Integer searchCountByConditions_bg(PaginationUtils paramPage) throws Exception{
		return this.getSqlSession().selectOne(this.getNameStatement() + ".searchCountByConditions", paramPage);
	}
	
	public void updateStatusById(ConsultPo t) throws Exception{
		this.initUpdateInfo(t);
		this.getSqlSession().update(this.getNameStatement() + ".updateStatusById", t);
	}
	
	@Override
	public String getNameStatement() {
		// TODO Auto-generated method stub
		return "yiyin.consult.bg";
	}

}
