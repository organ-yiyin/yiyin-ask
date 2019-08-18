package com.yiyn.ask.sys.form;

import com.yiyn.ask.base.form.BaseForm;

public class UserForm extends BaseForm{

	private static final long serialVersionUID = -6790539637785013005L;

	private String user_no;

	private String user_name;
	
	private String user_password;

	private String user_phone;
	
	private String enabled;
	
	private String original_password;

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

	public String getOriginal_password() {
		return original_password;
	}

	public void setOriginal_password(String original_password) {
		this.original_password = original_password;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}
