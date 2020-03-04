package com.yiyn.ask.xcx.account.dao.impl;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.account.po.AccountPo;
@Repository("accountDao")
public class AccountDaoImpl extends BaseDao<AccountPo> {
	public AccountPo getAccountInfo(String userno) throws Exception {
		return this.getSqlSession().selectOne(
				this.getNameStatement() + ".getAccountInfo", userno);
	}
	
	public AccountPo getAccountInfoByUserNo(String userno) throws Exception {
		return this.getSqlSession().selectOne(
				this.getNameStatement() + ".getAccountInfoByUserNo", userno);
	}
	
	public AccountPo findByUserBId(Long user_b_id) throws Exception {
		return this.getSqlSession().selectOne(
				this.getNameStatement() + ".findByUserBId", user_b_id);
	}
	
	
	public void updWithDraw(AccountPo p)throws Exception {
		this.getSqlSession().update(this.getNameStatement() + ".updWithDraw", p);
	}
	
	public void updateByIdAftetTransfer(AccountPo p)throws Exception {
		this.initUpdateInfo(p);
		this.getSqlSession().update(this.getNameStatement() + ".updateByIdAftetTransfer", p);
	}
	
	public void updateByIdAfterCancel(AccountPo p)throws Exception {
		this.initUpdateInfo(p);
		this.getSqlSession().update(this.getNameStatement() + ".updateByIdAfterCancel", p);
	}
	
	public String getNameStatement() {
		return "yiyin.account";
	}
}
