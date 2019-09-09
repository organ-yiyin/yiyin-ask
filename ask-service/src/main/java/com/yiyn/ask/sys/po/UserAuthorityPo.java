package com.yiyn.ask.sys.po;

import com.yiyn.ask.base.po.BasePo;

public class UserAuthorityPo extends BasePo {

	private static final long serialVersionUID = -4159157672731381566L;

	private Long user_id;
	
	private String authority_code;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getAuthority_code() {
		return authority_code;
	}

	public void setAuthority_code(String authority_code) {
		this.authority_code = authority_code;
	}
	
	
	
}
