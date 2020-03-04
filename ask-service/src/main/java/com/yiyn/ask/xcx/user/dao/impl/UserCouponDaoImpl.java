package com.yiyn.ask.xcx.user.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.user.po.UserCouponPo;
@Repository("userCouponDao")
public class UserCouponDaoImpl extends BaseDao<UserCouponPo> {
	
	public List<UserCouponPo> getCouponExsitList(Map<String,Object> m) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".getCouponExsitList",m);
	}
	
	public List<UserCouponPo> getCouponCList(Map<String,Object> m) throws Exception {
		return this.getSqlSession().selectList(
				this.getNameStatement() + ".getCouponCList",m);
	}
	
	public void updUserCoupon(UserCouponPo p) throws Exception {
	   this.initUpdateInfo(p);
	   this.getSqlSession().update(
				this.getNameStatement() + ".updUserCoupon", p);
	}
	
	public String getNameStatement() {
		return "yiyin.usercoupon";
	}
}
