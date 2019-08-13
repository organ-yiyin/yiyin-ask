package com.yiyn.ask.xcx.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.user.po.UserCPo;
@Repository("userCDao")
public class UserCDaoImpl extends BaseDao<UserCPo> {
	public UserCPo findByUserno(String userno) throws Exception {
		return this.getSqlSession().selectOne(
				this.getNameStatement() + ".findByUserno", userno);
	}
	
	public void updByUser_no(UserCPo p) throws Exception {
	   this.getSqlSession().update(
				this.getNameStatement() + ".updByUser_no", p);
	}
	public String getNameStatement() {
		return "yiyin.userb";
	}
}
