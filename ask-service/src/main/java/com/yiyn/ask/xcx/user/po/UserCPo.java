package com.yiyn.ask.xcx.user.po;

import java.util.List;

import com.yiyn.ask.base.po.BasePo;
import com.yiyn.ask.base.utils.StringUtils;

public class UserCPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_no;
	
	private String user_type;
	
	private String user_phone;
	
	private String user_headimg;
	
	private String user_name;
	
	private String reg_time_format;
	
	private String unionid;
	
	public String getUser_headimg() {
		return user_headimg;
	}

	public void setUser_headimg(String user_headimg) {
		this.user_headimg = user_headimg;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getReg_time_format() {
		return reg_time_format;
	}

	public void setReg_time_format(String reg_time_format) {
		this.reg_time_format = reg_time_format;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
}
