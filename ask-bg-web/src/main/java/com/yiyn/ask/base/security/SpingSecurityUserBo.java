package com.yiyn.ask.base.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.yiyn.ask.sys.po.UserPo;

public class SpingSecurityUserBo extends User{
	
	private static final long serialVersionUID = 1L;
	
	private UserPo po;
	
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
	
}
