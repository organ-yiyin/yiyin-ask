package com.yiyn.ask.sys.po;

import java.util.List;

import com.yiyn.ask.base.po.BasePo;

public class UserPo extends BasePo {

	private static final long serialVersionUID = 1186567579748240993L;

	private String user_no;
	
	private String user_name;
	
	private String user_password;
	
	private String user_phone;
	
	private String enabled;
	
	private List<RolePo> roles;

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public List<RolePo> getRoles() {
		return roles;
	}

	public void setRoles(List<RolePo> roles) {
		this.roles = roles;
	}
	
}
