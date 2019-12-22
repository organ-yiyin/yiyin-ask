package com.yiyn.ask.market.po;

import java.util.Date;

import com.yiyn.ask.base.po.BasePo;

public class CouponPo extends BasePo{
	
	private static final long serialVersionUID = -4647467678541826825L;

	private String name;
	
	private String coupon_no;
	
	private String coupon_type;
	
	private String coupon_range;
	
	private Integer total_amount;
	
	private Integer amount;
	
	private Date start_date;
	
	private Date end_date;
	
	private Date post_start;
	
	private Date post_end;
	
	private String status;
	
	private String user_list;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoupon_no() {
		return coupon_no;
	}

	public void setCoupon_no(String coupon_no) {
		this.coupon_no = coupon_no;
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

	public Date getPost_start() {
		return post_start;
	}

	public void setPost_start(Date post_start) {
		this.post_start = post_start;
	}

	public Date getPost_end() {
		return post_end;
	}

	public void setPost_end(Date post_end) {
		this.post_end = post_end;
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
