package com.yiyn.ask.base.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.yiyn.ask.sys.po.UserAuthorityPo;
import com.yiyn.ask.sys.po.UserPo;

public class SpingSecurityUserBo extends User{
	
	private static final long serialVersionUID = 1L;
	
	private UserPo po;
	
	private List<UserAuthorityPo> userAuthoritys = new ArrayList<>();
	
	public SpingSecurityUserBo(UserPo po, Collection<? extends GrantedAuthority> authorities) {
		super(po.getUser_no(),po.getUser_password(), authorities);
		this.po = po;
    }

	public UserPo getPo() {
		return po;
	}

	public void setPo(UserPo po) {
		this.po = po;
	}

	public List<UserAuthorityPo> getUserAuthoritys() {
		return userAuthoritys;
	}

	public void setUserAuthoritys(List<UserAuthorityPo> userAuthoritys) {
		this.userAuthoritys = userAuthoritys;
	}
	

}
