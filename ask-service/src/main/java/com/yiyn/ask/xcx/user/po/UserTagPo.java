package com.yiyn.ask.xcx.user.po;

import com.yiyn.ask.base.po.BasePo;

public class UserTagPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String value;
	private String name;
	private String user_no;
	private String type;
	private String sel; // 1为选中，否则不选中
	
	public String getSel() {
		return sel;
	}
	public void setSel(String sel) {
		this.sel = sel;
	}
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
