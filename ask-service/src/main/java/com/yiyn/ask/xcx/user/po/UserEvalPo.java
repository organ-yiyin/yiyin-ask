package com.yiyn.ask.xcx.user.po;

import com.yiyn.ask.base.po.BasePo;

public class UserEvalPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_b_no;
	private String user_c_no;
	private String user_b_name;
	private String consultation_id;
	private int stars;
	private String eva_desc;
	private String old_desc;
	private String is_hidden;
	private String update_flag;
	private String btn_show; // 是否显示可修改的按钮，当前默认超过15天或评价三颗星以上不可修改评价 1：可修改 0：不可修改
	
	//咨询师头像
	private String user_b_img;
	
	public String getUser_b_img() {
		return user_b_img;
	}
	public void setUser_b_img(String user_b_img) {
		this.user_b_img = user_b_img;
	}
	public String getUpdate_flag() {
		return update_flag;
	}
	public void setUpdate_flag(String update_flag) {
		this.update_flag = update_flag;
	}
	public String getBtn_show() {
		return btn_show;
	}
	public void setBtn_show(String btn_show) {
		this.btn_show = btn_show;
	}
	public String getOld_desc() {
		return old_desc;
	}
	public void setOld_desc(String old_desc) {
		this.old_desc = old_desc;
	}
	public String getUser_b_name() {
		return user_b_name;
	}
	public void setUser_b_name(String user_b_name) {
		this.user_b_name = user_b_name;
	}
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
