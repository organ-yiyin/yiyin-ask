package com.yiyn.ask.xcx.center.po;

import com.yiyn.ask.base.po.BasePo;

public class CenterResponsePo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_no;
	private String response_desc;

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getResponse_desc() {
		return response_desc;
	}

	public void setResponse_desc(String response_desc) {
		this.response_desc = response_desc;
	}
}
