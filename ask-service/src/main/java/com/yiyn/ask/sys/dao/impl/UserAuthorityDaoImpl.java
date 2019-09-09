package com.yiyn.ask.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.sys.po.UserAuthorityPo;

@Repository
public class UserAuthorityDaoImpl extends BaseDao<UserAuthorityPo>{
	
	public Long save(UserAuthorityPo userAuthorityPo) throws Exception{
		if(userAuthorityPo.getId() == null) {
			this.insert(userAuthorityPo);
		}
		else {
			this.updateById(userAuthorityPo);
		}
		return userAuthorityPo.getId();
	}
	
	public List<UserAuthorityPo> findByUserId(Long user_id) throws Exception{
		return this.getSqlSession().selectList(this.getNameStatement() + ".findByUserId", user_id);
	}
	
	public void deleteByUserId(Long user_id) {
		this.getSqlSession().delete(this.getNameStatement() + ".deleteByUserId", user_id);
	}


	@Override
	public String getNameStatement() {
		// TODO Auto-generated method stub
		return "sys.user.authority";
	}

}
