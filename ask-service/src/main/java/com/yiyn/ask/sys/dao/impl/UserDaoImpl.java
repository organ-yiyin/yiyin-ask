package com.yiyn.ask.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.sys.po.UserPo;

@Repository("userDao")
public class UserDaoImpl extends BaseDao<UserPo>{
	
	public Long save(UserPo userPo) throws Exception{
		if(userPo.getId() == null) {
			this.insert(userPo);
		}
		else {
			this.updateById(userPo);
		}
		return userPo.getId();
	}
	
	public List<UserPo> searchByConditions(PaginationUtils paramPage){
		return this.getSqlSession().selectList(this.getNameStatement() + ".searchByConditions", paramPage);
	}
	
	public Integer searchCountByConditions(PaginationUtils paramPage) throws Exception{
		return this.getSqlSession().selectOne(this.getNameStatement() + ".searchCountByConditions", paramPage);
	}
	
	public UserPo findByUserNo(String user_no) throws Exception{
		return this.getSqlSession().selectOne(this.getNameStatement() + ".findByUserNo", user_no);
	}
	
	public void updatePasswordById(UserPo userPo){
		this.initUpdateInfo(userPo);
		this.getSqlSession().update(this.getNameStatement() + ".updatePasswordById", userPo);
	}

	@Override
	public String getNameStatement() {
		// TODO Auto-generated method stub
		return "sys.user";
	}

}
