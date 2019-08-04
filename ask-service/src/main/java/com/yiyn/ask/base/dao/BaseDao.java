package com.yiyn.ask.base.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.yiyn.ask.base.constants.YesOrNoType;
import com.yiyn.ask.base.po.BasePo;
import com.yiyn.ask.base.utils.PaginationUtils;

public abstract class BaseDao<T extends BasePo> extends SqlSessionDaoSupport{
	
	public void insert(T t) throws Exception{
		if(StringUtils.isEmpty(t.getDelete_flag())){
			t.setDelete_flag(YesOrNoType.NO.getValue());
		}
		this.initUpdateInfo(t);
		this.getSqlSession().insert(this.getNameStatement() + ".insert", t);
	}
	
	public T findById(Long id) throws Exception {
		return this.getSqlSession().selectOne(this.getNameStatement() + ".findById", id);
	}
	
	public T findByIdOnLock(Long id) throws Exception {
		return this.getSqlSession().selectOne(this.getNameStatement() + ".findByIdOnLock", id);
	}
	
	public void deleteById(Long id) throws Exception {
		this.getSqlSession().delete(this.getNameStatement() + ".deleteById", id);
	}
	
	public void deleteById_logic(Long id) throws Exception{
		this.getSqlSession().delete(this.getNameStatement() + ".deleteById_logic", id);
	}
	
	public void deleteByIds(Long[] ids) throws Exception{
		this.getSqlSession().delete(this.getNameStatement() + ".deleteByIds", ids);
	}
	
	public List<T> findAll() throws Exception{
		return this.getSqlSession().selectList(this.getNameStatement() + ".findAll");
	}
	
	public void updateById(T t) throws Exception{
		this.initUpdateInfo(t);
		this.getSqlSession().update(this.getNameStatement() + ".updateById", t);
	}
	
	public List<T> searchByConditions(PaginationUtils paramPage){
		return this.getSqlSession().selectList(this.getNameStatement() + ".searchByConditions", paramPage);
	}
	
	public Integer searchCountByConditions(PaginationUtils paramPage) throws Exception{
		return this.getSqlSession().selectOne(this.getNameStatement() + ".searchCountByConditions", paramPage);
	}
	
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
				t.setCreated_by(user.getUsername());
				t.setUpdated_by(user.getUsername());
			}
		}
		else{
			t.setCreated_by("system");
			t.setUpdated_by("system");
		}
		t.setCreated_time(new Date());			
		t.setUpdated_time(new Date());
	}
}
