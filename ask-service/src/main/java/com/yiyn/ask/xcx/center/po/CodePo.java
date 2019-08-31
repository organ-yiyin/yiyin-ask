package com.yiyn.ask.xcx.center.po;

import com.yiyn.ask.base.po.BasePo;

public class CodePo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code_type;
	private String value;
	private String name;
	private String content1;
	public String getCode_type() {
		return code_type;
	}
	public void setCode_type(String code_type) {
		this.code_type = code_type;
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
	public String getContent1() {
		return content1;
	}
	public void setContent1(String content1) {
		this.content1 = content1;
	}
}
