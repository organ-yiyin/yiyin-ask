package com.yiyn.ask.xcx.user.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.user.po.UserEvalPo;
@Repository("userEvalDao")
/**
 * 用户评价
 * @author tupz
 *
 */
public class UserEvalDaoImpl extends BaseDao<UserEvalPo> {
	
	public List<UserEvalPo> findUserEval(String userno) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".findUserEval", userno);
	}
	
	public String getNameStatement() {
		return "yiyin.eval";
	}
}
