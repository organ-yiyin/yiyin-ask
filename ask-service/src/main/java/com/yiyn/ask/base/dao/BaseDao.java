package com.yiyn.ask.base.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.yiyn.ask.base.constants.YesOrNoType;
import com.yiyn.ask.base.po.BasePo;

public abstract class BaseDao<T extends BasePo> extends SqlSessionDaoSupport{
	
	public void insert(T t) throws Exception{
		if(StringUtils.isEmpty(t.getDelete_flag())){
			t.setDelete_flag(YesOrNoType.NO.getValue());
		}
		this.initUpdateInfo(t);
		this.getSqlSession().insert(this.getNameStatement() + ".insert", t);
	}
	
	public T findById(int id) throws Exception {
		return this.getSqlSession().selectOne(this.getNameStatement() + ".findById", id);
	}
	
	public T findByIdOnLock(int id) throws Exception {
		return this.getSqlSession().selectOne(this.getNameStatement() + ".findByIdOnLock", id);
	}
	
	public void deleteById(int id) throws Exception {
		this.getSqlSession().delete(this.getNameStatement() + ".deleteById", id);
	}
	
	public void deleteById_logic(int id) throws Exception{
		this.getSqlSession().delete(this.getNameStatement() + ".deleteById_logic", id);
	}
	
	public void deleteByIds(int[] ids) throws Exception{
		this.getSqlSession().delete(this.getNameStatement() + ".deleteByIds", ids);
	}
	
	public List<T> findAll() throws Exception{
		return this.getSqlSession().selectList(this.getNameStatement() + ".findAll");
	}
	
	public void updateById(T t) throws Exception{
		this.initUpdateInfo(t);
		this.getSqlSession().update(this.getNameStatement() + ".updateById", t);
	}
	
	//public abstract void saveOrUpdate(T t) throws Exception;
	
	public abstract String getNameStatement();
	
	/**
	 * 修改数据的时候，填充修改信息
	 * 
	 * @param t
	 */
	public void initUpdateInfo(T t){
		
		if(SecurityContextHolder.getContext().getAuthentication() != null 
			&& SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
			
			if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String){
			}
			else{
				User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				t.setCreator(user.getUsername());
				t.setUpdater(user.getUsername());
			}
		}
		else{
			t.setCreator("system");
			t.setUpdater("system");
		}
		t.setCreate_time(new Date());			
		t.setUpdate_time(new Date());
	}
}
