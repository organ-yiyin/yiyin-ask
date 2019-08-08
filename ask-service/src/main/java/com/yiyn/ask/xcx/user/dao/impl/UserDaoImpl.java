package com.yiyn.ask.xcx.user.dao.impl;


import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.user.po.UserPo;
@Repository("userBDao")
public class UserDaoImpl extends BaseDao<UserPo> {
	public UserPo findByUserno(String userno) throws Exception {
		return this.getSqlSession().selectOne(
				this.getNameStatement() + ".findByUserno", userno);
	}
	
	public UserPo findUserInfo(String userno) throws Exception {
		return this.getSqlSession().selectOne(
				this.getNameStatement() + ".findUserInfo", userno);
	}
	
	public void updInfo(UserPo p) throws Exception {
	   this.getSqlSession().update(
				this.getNameStatement() + ".updInfo", p);
	}
	public String getNameStatement() {
		return "yiyin.userb";
	}
}
