package com.yiyn.ask.base.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.yiyn.ask.sys.po.UserBPo;

public class SpingSecurityUserBo extends User{
	
	private static final long serialVersionUID = 1L;
	
	private UserBPo po;
	
	public SpingSecurityUserBo(UserBPo po, Collection<? extends GrantedAuthority> authorities) {
		super(po.getUser_name(),po.getUser_password(), authorities);
		this.po = po;
    }

	public UserBPo getPo() {
		return po;
	}

	public void setPo(UserBPo po) {
		this.po = po;
	}
	
	
}
