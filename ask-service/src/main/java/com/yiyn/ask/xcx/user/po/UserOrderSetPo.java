package com.yiyn.ask.xcx.user.po;

import com.yiyn.ask.base.po.BasePo;

public class UserOrderSetPo extends BasePo {
	/**
	 * 接单日志设定
	 */
	private static final long serialVersionUID = 1L;
	private String user_no;
	private String set_type;
	
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	public String getSet_type() {
		return set_type;
	}
	public void setSet_type(String set_type) {
		this.set_type = set_type;
	}
}
