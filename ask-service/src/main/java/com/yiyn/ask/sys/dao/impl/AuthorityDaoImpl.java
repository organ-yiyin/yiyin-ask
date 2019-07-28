package com.yiyn.ask.sys.dao.impl;

import java.util.List;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.sys.po.AuthorityPo;

public class AuthorityDaoImpl extends BaseDao<AuthorityPo>{
	
	public List<AuthorityPo> searchByConditions(PaginationUtils paramPage){
		return this.getSqlSession().selectList(this.getNameStatement() + ".searchByConditions", paramPage);
	}
	
	public Integer searchCountByConditions(PaginationUtils paramPage) throws Exception{
		return this.getSqlSession().selectOne(this.getNameStatement() + ".searchCountByConditions", paramPage);
	}
	
	public List<AuthorityPo> findAuthByUserd(int user_id) throws Exception{
		return this.getSqlSession().selectList(this.getNameStatement() + ".findAuthByUserd", user_id);
	}
	
	public List<AuthorityPo> findAuthByRoleId(int role_id) throws Exception{
		return this.getSqlSession().selectList(this.getNameStatement() + ".findAuthByRoleId", role_id);
	}

	@Override
	public String getNameStatement() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
