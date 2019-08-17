package com.yiyn.ask.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.sys.po.UserBPo;

@Repository("userBDao_bg")
public class UserBDaoImpl extends BaseDao<UserBPo>{
	
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
