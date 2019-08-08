package com.yiyn.ask.xcx.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.user.po.UserTagPo;
@Repository("userTagDao")
public class UserTagDaoImpl extends BaseDao<UserTagPo> {
	public List<UserTagPo> getUserTagList(String userno) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".getUserTagList", userno);
	}
	
	public List<UserTagPo> getTagCodeList() throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".getTagCodeList");
	}
	
	/**
	 * 批量插入
	 * @param p
	 * @throws Exception
	 */
	public void insTag(List<UserTagPo> p) throws Exception {
	   this.getSqlSession().insert(
				this.getNameStatement() + ".insTag", p);
	}
	
	// 删除
	public void delTag(String user_no) throws Exception {
		   this.getSqlSession().delete(
					this.getNameStatement() + ".delTag", user_no);
		}
	public String getNameStatement() {
		return "yiyin.usertag";
	}
}
