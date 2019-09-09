package com.yiyn.ask.xcx.user.po;

import com.yiyn.ask.base.po.BasePo;

public class UserEvalPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_b_no;
	private String user_c_no;
	private String consultation_id;
	private int stars;
	private String eva_desc;
	private String is_hidden;
	public String getUser_b_no() {
		return user_b_no;
	}
	public void setUser_b_no(String user_b_no) {
		this.user_b_no = user_b_no;
	}
	public String getUser_c_no() {
		return user_c_no;
	}
	public void setUser_c_no(String user_c_no) {
		this.user_c_no = user_c_no;
	}
	public String getConsultation_id() {
		return consultation_id;
	}
	public void setConsultation_id(String consultation_id) {
		this.consultation_id = consultation_id;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public String getEva_desc() {
		return eva_desc;
	}
	public void setEva_desc(String eva_desc) {
		this.eva_desc = eva_desc;
	}
	public String getIs_hidden() {
		return is_hidden;
	}
	public void setIs_hidden(String is_hidden) {
		this.is_hidden = is_hidden;
	}
}
