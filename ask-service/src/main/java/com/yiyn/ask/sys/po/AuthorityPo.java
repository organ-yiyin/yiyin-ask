package com.yiyn.ask.sys.po;

import com.yiyn.ask.base.po.BasePo;

public class AuthorityPo extends BasePo {

	private int authority_id;
	
	private String authority_name;
	
	private String authority_desc;
	
	private String authority_code;

	public int getAuthority_id() {
		return authority_id;
	}

	public void setAuthority_id(int authority_id) {
		this.authority_id = authority_id;
	}

	public String getAuthority_name() {
		return authority_name;
	}

	public void setAuthority_name(String authority_name) {
		this.authority_name = authority_name;
	}

	public String getAuthority_desc() {
		return authority_desc;
	}

	public void setAuthority_desc(String authority_desc) {
		this.authority_desc = authority_desc;
	}

	public String getAuthority_code() {
		return authority_code;
	}

	public void setAuthority_code(String authority_code) {
		this.authority_code = authority_code;
	}
	
}
