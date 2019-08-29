package com.yiyn.ask.xcx.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.user.po.UserColPo;
@Repository("userColDao")
public class UserColDaoImpl extends BaseDao<UserColPo> {
	public UserColPo sfRel(UserColPo col) throws Exception {
		return this.getSqlSession().selectOne(
				this.getNameStatement() + ".sfRel", col);
	}
	
	public void delCol(UserColPo col)throws Exception {
		this.getSqlSession().delete(this.getNameStatement() + ".delCol", col);
	}
	
	public String getNameStatement() {
		return "yiyin.usercol";
	}
}
