package com.yiyn.ask.sys.dao.impl;

import java.util.List;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.sys.po.RolePo;

public class RoleDaoImpl extends BaseDao<RolePo>{
	
	public List<RolePo> searchByConditions(PaginationUtils paramPage){
		return this.getSqlSession().selectList(this.getNameStatement() + ".searchByConditions", paramPage);
	}
	
	public Integer searchCountByConditions(PaginationUtils paramPage) throws Exception{
		return this.getSqlSession().selectOne(this.getNameStatement() + ".searchCountByConditions", paramPage);
	}

	@Override
	public String getNameStatement() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
