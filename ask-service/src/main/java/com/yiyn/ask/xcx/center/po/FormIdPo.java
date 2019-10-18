package com.yiyn.ask.xcx.center.po;

import com.yiyn.ask.base.po.BasePo;

public class FormIdPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String source;
	private String form_id;
	private String rel_user;
	private String get_time;
	private String expires_time;
	private int send_num;
	private String is_valid;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getForm_id() {
		return form_id;
	}
	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}
	public String getRel_user() {
		return rel_user;
	}
	public void setRel_user(String rel_user) {
		this.rel_user = rel_user;
	}
	public String getGet_time() {
		return get_time;
	}
	public void setGet_time(String get_time) {
		this.get_time = get_time;
	}
	public String getExpires_time() {
		return expires_time;
	}
	public void setExpires_time(String expires_time) {
		this.expires_time = expires_time;
	}
	public String getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}
	public int getSend_num() {
		return send_num;
	}
	public void setSend_num(int send_num) {
		this.send_num = send_num;
	}
}
