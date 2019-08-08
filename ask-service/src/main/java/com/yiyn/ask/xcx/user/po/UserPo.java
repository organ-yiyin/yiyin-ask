package com.yiyn.ask.xcx.user.po;

import java.util.List;

import com.yiyn.ask.base.po.BasePo;
import com.yiyn.ask.base.utils.StringUtils;

public class UserPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_no;
	
	private String user_name;
	
	private String user_password;
	
	private String user_phone;
	
	private String user_type;
	
	private String user_headimg;
	
	private String work_year;
	private String user_desc;
	private String order_set;
	private String skilled;
	private List<UserTagPo> tagList;
	private String evaluate; // 评价
	private String consultnum;// 咨询量
	private String advice_val;// 咨询单价
	private String advice_num;// 咨询数量
	private String type; // 更新数据库的类型
	
	public String getSkilled() {
		return skilled;
	}
	public void setSkilled(String skilled) {
		this.skilled = skilled;
	}
	public String getAdvice_val() {
		return advice_val;
	}
	public void setAdvice_val(String advice_val) {
		this.advice_val = StringUtils.subZeroAndDot(advice_val);
	}
	public String getAdvice_num() {
		return advice_num;
	}
	public void setAdvice_num(String advice_num) {
		this.advice_num = StringUtils.subZeroAndDot(advice_num);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrder_set() {
		return order_set;
	}
	public void setOrder_set(String order_set) {
		this.order_set = order_set;
	}
	public List<UserTagPo> getTagList() {
		return tagList;
	}
	public void setTagList(List<UserTagPo> tagList) {
		this.tagList = tagList;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public String getConsultnum() {
		return consultnum;
	}
	public void setConsultnum(String consultnum) {
		this.consultnum = consultnum;
	}
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
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getUser_headimg() {
		return user_headimg;
	}
	public void setUser_headimg(String user_headimg) {
		this.user_headimg = user_headimg;
	}
	public String getWork_year() {
		return work_year;
	}
	public void setWork_year(String work_year) {
		this.work_year = work_year;
	}
	public String getUser_desc() {
		return user_desc;
	}
	public void setUser_desc(String user_desc) {
		this.user_desc = user_desc;
	}
}