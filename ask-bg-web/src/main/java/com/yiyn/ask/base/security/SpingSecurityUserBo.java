package com.yiyn.ask.base.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.yiyn.ask.sys.po.AuthorityPo;
import com.yiyn.ask.sys.po.UserPo;

public class SpingSecurityUserBo extends User{
	
	private UserPo po;
	
	private List<AuthorityPo> authorityPos;
	
	public SpingSecurityUserBo(UserPo po, List<AuthorityPo> authorityPos, Collection<? extends GrantedAuthority> authorities) {
		super(po.getUser_name(),po.getPassword(), authorities);
		this.authorityPos = authorityPos;
		this.po = po;
    }
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserPo getPo() {
		return po;
	}

	public void setPo(UserPo po) {
		this.po = po;
	}

	public List<AuthorityPo> getAuthorityPos() {
		return authorityPos;
	}

	public void setAuthorityPos(List<AuthorityPo> authorityPos) {
		this.authorityPos = authorityPos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
