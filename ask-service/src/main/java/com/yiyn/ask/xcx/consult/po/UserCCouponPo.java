package com.yiyn.ask.xcx.consult.po;

import java.util.Date;

import com.yiyn.ask.base.po.BasePo;

public class UserCCouponPo extends BasePo{

	private static final long serialVersionUID = -1166336993622880579L;
	
	private String user_c_no;
	
	private Integer coupon_id;
	
	private String coupon_type;
	
	private String coupon_range;
	
	private Integer total_amount;
	
	private Integer amount;
	
	private Date start_date;
	
	private Date end_date;
	
	private String status;
	
	private String user_list;

	public String getUser_c_no() {
		return user_c_no;
	}

	public void setUser_c_no(String user_c_no) {
		this.user_c_no = user_c_no;
	}

	public Integer getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(Integer coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getCoupon_type() {
		return coupon_type;
	}

	public void setCoupon_type(String coupon_type) {
		this.coupon_type = coupon_type;
	}

	public String getCoupon_range() {
		return coupon_range;
	}

	public void setCoupon_range(String coupon_range) {
		this.coupon_range = coupon_range;
	}

	public Integer getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Integer total_amount) {
		this.total_amount = total_amount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUser_list() {
		return user_list;
	}

	public void setUser_list(String user_list) {
		this.user_list = user_list;
	}
	
}
