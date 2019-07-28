package com.yiyn.ask.sys.po;

import java.util.List;

import com.yiyn.ask.base.po.BasePo;

public class UserPo extends BasePo {
	
	private int user_id;
	
	private int centre_id;
	
	private String user_name;
	
	private String full_name;
	
	private String password;
	
	private String enabled;
	
	private List<RolePo> roles;
	
	private String isadmin;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getCentre_id() {
		return centre_id;
	}

	public void setCentre_id(int centre_id) {
		this.centre_id = centre_id;
	}

	public String getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}
	
}
