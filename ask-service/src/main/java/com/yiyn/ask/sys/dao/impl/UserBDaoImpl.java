package com.yiyn.ask.sys.dao.impl;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.sys.po.UserBPo;

public class UserBDaoImpl extends BaseDao<UserBPo>{
	
	public UserBPo findByUserNo(String user_no){
		return this.getSqlSession().selectOne(this.getNameStatement() + ".findByUserNo", user_no);
	}
	
	@Override
	public String getNameStatement() {
		// TODO Auto-generated method stub
		return "sys.userb";
	}
}
