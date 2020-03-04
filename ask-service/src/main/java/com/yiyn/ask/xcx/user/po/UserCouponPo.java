package com.yiyn.ask.xcx.user.po;

import com.yiyn.ask.base.po.BasePo;

/**
 * 用户获取的优惠券
 * @author tupz
 *
 */
public class UserCouponPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_c_no;
	private String coupon_id;
	private String coupon_type; // 1：满减优惠券
	private String coupon_range; // 1：全场通用  2：部分咨询师
	private int total_amount;
	private int amount;
	private String start_date;
	private String end_date;
	
	private String status;
	private String user_list;
	public String getUser_c_no() {
		return user_c_no;
	}
	public void setUser_c_no(String user_c_no) {
		this.user_c_no = user_c_no;
	}
	public String getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(String coupon_id) {
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
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
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
