package com.yiyn.ask.sys.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.sys.po.UserBPo;

public class UserBDaoImpl extends BaseDao<UserBPo>{
	
	public Long save(UserBPo userPo) throws Exception{
		if(userPo.getId() == null) {
			this.insert(userPo);
		}
		else {
			this.updateById(userPo);
		}
		return userPo.getId();
	}
	
	public UserBPo findByUserNo(String user_no) throws Exception{
		return this.getSqlSession().selectOne(this.getNameStatement() + ".findByUserNo", user_no);
	}
	
	public void updatePasswordById(UserBPo userPo) throws Exception{
		this.initUpdateInfo(userPo);
		this.getSqlSession().update(this.getNameStatement() + ".updatePasswordById", userPo);
	}
	
	public void updateByIdInBg(UserBPo userPo) throws Exception{
		this.initUpdateInfo(userPo);
		this.getSqlSession().update(this.getNameStatement() + ".updateByIdInBg", userPo);
	}

	@Override
	public String getNameStatement() {
		// TODO Auto-generated method stub
		return "sys.userb";
	}
}
