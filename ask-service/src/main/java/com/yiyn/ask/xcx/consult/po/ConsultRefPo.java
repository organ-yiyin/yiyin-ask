package com.yiyn.ask.xcx.consult.po;

import com.yiyn.ask.base.po.BasePo;

public class ConsultRefPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_no;
	private String name_m;
	private String age_m;
	private String birthday_m;
	private String special_m; // 妈妈特殊情况
	private String sex_b;
	private String age_b;
	private String birthday_b;
	private String edc_b; // 宝宝预产期
	private String birth_weight_b;
	private String special_b;
	private String birth_week;//出生孕周
	private String premie; //是否早产儿
	public String getName_m() {
		return name_m;
	}
	public String getAge_m() {
		return age_m;
	}
	public String getBirthday_m() {
		return birthday_m;
	}
	public String getSpecial_m() {
		return special_m;
	}
	public String getSex_b() {
		return sex_b;
	}
	public String getAge_b() {
		return age_b;
	}
	public String getBirthday_b() {
		return birthday_b;
	}
	public String getEdc_b() {
		return edc_b;
	}
	public String getBirth_weight_b() {
		return birth_weight_b;
	}
	public String getSpecial_b() {
		return special_b;
	}
	public void setName_m(String name_m) {
		this.name_m = name_m;
	}
	public void setAge_m(String age_m) {
		this.age_m = age_m;
	}
	public void setBirthday_m(String birthday_m) {
		this.birthday_m = birthday_m;
	}
	public void setSpecial_m(String special_m) {
		this.special_m = special_m;
	}
	public void setSex_b(String sex_b) {
		this.sex_b = sex_b;
	}
	public void setAge_b(String age_b) {
		this.age_b = age_b;
	}
	public void setBirthday_b(String birthday_b) {
		this.birthday_b = birthday_b;
	}
	public void setEdc_b(String edc_b) {
		this.edc_b = edc_b;
	}
	public void setBirth_weight_b(String birth_weight_b) {
		this.birth_weight_b = birth_weight_b;
	}
	public void setSpecial_b(String special_b) {
		this.special_b = special_b;
	}
	public String getBirth_week() {
		return birth_week;
	}
	public void setBirth_week(String birth_week) {
		this.birth_week = birth_week;
	}
	public String getPremie() {
		return premie;
	}
	public void setPremie(String premie) {
		this.premie = premie;
	}
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
}
