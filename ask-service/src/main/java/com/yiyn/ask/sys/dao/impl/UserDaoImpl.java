package com.yiyn.ask.sys.dao.impl;

import java.util.List;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.sys.po.UserPo;

public class UserDaoImpl extends BaseDao<UserPo>{
	
	public List<UserPo> searchByConditions(PaginationUtils paramPage){
		return this.getSqlSession().selectList(this.getNameStatement() + ".searchByConditions", paramPage);
	}
	
	public Integer searchCountByConditions(PaginationUtils paramPage) throws Exception{
		return this.getSqlSession().selectOne(this.getNameStatement() + ".searchCountByConditions", paramPage);
	}
	
	public UserPo findByUsername(String user_name){
		return this.getSqlSession().selectOne(this.getNameStatement() + ".findByUsername", user_name);
	}
	
	public void updatePasswordById(UserPo userPo){
		this.initUpdateInfo(userPo);
		this.getSqlSession().update(this.getNameStatement() + ".updatePasswordById", userPo);
	}

	@Override
	public String getNameStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
