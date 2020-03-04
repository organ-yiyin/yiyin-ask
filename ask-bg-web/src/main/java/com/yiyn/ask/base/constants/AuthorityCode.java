package com.yiyn.ask.base.constants;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import com.yiyn.ask.base.security.SpingSecurityUserBo;
import com.yiyn.ask.sys.po.UserAuthorityPo;	

public enum AuthorityCode {
	
	order_management("order.management","服务订单管理","服务订单管理"),
	order_withdraw_management("order.withdraw.management","提现管理","提现管理"),
	order_comments_management("order.comments.management","评价管理","评价管理"),
	
	userb_management("userb.management","服务人员管理","服务人员管理"),
	
	customer_management("customer.management","客户管理","客户管理"),
	
	ad_management("ad.management","广告管理","广告管理"),
	coupon_management("coupon.management","优惠活动管理","优惠活动管理"),
	
	sys_user_management("sys.user.management","用户管理","用户管理");
	
	String code;
	String text;
	String short_text;
	
	private AuthorityCode(String code, String text, String shortText){
		this.code = code;
		this.text = text;
		this.short_text = shortText;
	}
	
	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getShort_text() {
		return short_text;
	}
	public void setShort_text(String short_text) {
		this.short_text = short_text;
	}

	
	public static AuthorityCode findByCode(String code) {
		if(StringUtils.isEmpty(code)) {
			return null;
		}
		
		for(AuthorityCode item : AuthorityCode.values()) {
			if(item.getCode().equals(code)) {
				return item;
			}
		}
		return null;
	}
	
	public static boolean isAuthorized(AuthorityCode... authority_codes){
		SpingSecurityUserBo userDetails = (SpingSecurityUserBo) (SecurityContextHolder.getContext()  
			    .getAuthentication()  
			    .getPrincipal());
		if(isSuperAdmin()){
			return true;
		}
		
		for(AuthorityCode authority_code : authority_codes){
			//return userDetails.getUserAuthoritys().stream().anyMatch(e -> e.getAuthority_code().equals(authority_code.getCode()));
			for(UserAuthorityPo abo : userDetails.getUserAuthoritys()){
				if(abo.getAuthority_code().equals(authority_code.getCode())){
					return true;
				}
			}
			
		}
		return false;
	}
	
	public static boolean isSuperAdmin(String user_no) {
		if(StringUtils.isEmpty(user_no)) {
			return false;
		}
		
		// 超级管理员，super_admin
		if("super_admin".equals(user_no)){
			return true;
		}
		return false;
	}
	
	public static boolean isSuperAdmin(){
		SpingSecurityUserBo userDetails = (SpingSecurityUserBo) (SecurityContextHolder.getContext()  
			    .getAuthentication()  
			    .getPrincipal());

		return isSuperAdmin(userDetails.getPo().getUser_no());
	}
}
