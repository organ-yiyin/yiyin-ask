package com.yiyn.ask.xcx.user.dao.impl;


import java.util.List;
import java.util.Map;

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
	
	public List<UserPo> findRecommendList() throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".findRecommendList");
	}
	
	public List<UserPo> getConsultList(Map<String,Object> m) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".getConsultList",m);
	}
	
	/**
	 * c端用户关注的咨询师
	 * @param m
	 * @return
	 * @throws Exception
	 */
	public List<UserPo> getCollectionConsultList(String user_no) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".getCollectionConsultList",user_no);
	}
	
	public void updInfo(UserPo p) throws Exception {
	   this.getSqlSession().update(
				this.getNameStatement() + ".updInfo", p);
	}
	public String getNameStatement() {
		return "yiyin.userb";
	}
}
