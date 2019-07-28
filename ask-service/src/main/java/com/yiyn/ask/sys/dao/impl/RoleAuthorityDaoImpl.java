package com.yiyn.ask.sys.dao.impl;

import java.util.List;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.sys.po.RoleAuthorityPo;

public class RoleAuthorityDaoImpl extends BaseDao<RoleAuthorityPo>{
	
	
	public List<RoleAuthorityPo> findByRoleId(int roleId){
		return this.getSqlSession().selectList(this.getNameStatement() + ".findByRoleId", roleId);
	}
	
	
	public void deleteAuthorityByRoleId(int roleId) {
		this.getSqlSession().delete(this.getNameStatement() + ".deleteAuthorityByRoleId", roleId);
	}

	@Override
	public String getNameStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
