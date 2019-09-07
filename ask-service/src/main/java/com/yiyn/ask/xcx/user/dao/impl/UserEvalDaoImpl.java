package com.yiyn.ask.xcx.user.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.xcx.user.po.UserEvalPo;
@Repository("userEvalDao")
/**
 * 用户评价
 * @author tupz
 *
 */
public class UserEvalDaoImpl extends BaseDao<UserEvalPo> {
	
	public List<Map> searchByConditions_bg(PaginationUtils paramPage){
		return this.getSqlSession().selectList(this.getNameStatement() + ".searchByConditions_bg", paramPage);
	}
	
	public Integer searchCountByConditions_bg(PaginationUtils paramPage) throws Exception{
		return this.getSqlSession().selectOne(this.getNameStatement() + ".searchCountByConditions_bg", paramPage);
	}
	
	public List<UserEvalPo> findUserEval(String userno) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".findUserEval", userno);
	}
	
	public void updateByAdmin_bg(UserEvalPo userPo) throws Exception{
		this.initUpdateInfo(userPo);
		this.getSqlSession().update(this.getNameStatement() + ".updateByAdmin_bg", userPo);
	}
	
	public String getNameStatement() {
		return "yiyin.eval";
	}
}
