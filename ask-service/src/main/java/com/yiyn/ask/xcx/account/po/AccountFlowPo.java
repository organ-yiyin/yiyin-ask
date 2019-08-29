package com.yiyn.ask.xcx.account.po;

import com.yiyn.ask.base.po.BasePo;

public class AccountFlowPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long account_id;
	private double journal_money;
	private String journal_dir;
	private String journal_type;
	private String order_id;//微信订单id
	private String pay_type;
	private String pay_time;
	private String pay_time_format;
	private String journal_remark;
	private String pay_status;
	private String pay_channel_no;
	public String getPay_channel_no() {
		return pay_channel_no;
	}
	public void setPay_channel_no(String pay_channel_no) {
		this.pay_channel_no = pay_channel_no;
	}
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}
	public Long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}
	
	public double getJournal_money() {
		return journal_money;
	}
	public void setJournal_money(double journal_money) {
		this.journal_money = journal_money;
	}
	public String getJournal_dir() {
		return journal_dir;
	}
	public void setJournal_dir(String journal_dir) {
		this.journal_dir = journal_dir;
	}
	public String getJournal_type() {
		return journal_type;
	}
	public void setJournal_type(String journal_type) {
		this.journal_type = journal_type;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getPay_time_format() {
		return pay_time_format;
	}
	public void setPay_time_format(String pay_time_format) {
		this.pay_time_format = pay_time_format;
	}
	public String getJournal_remark() {
		return journal_remark;
	}
	public void setJournal_remark(String journal_remark) {
		this.journal_remark = journal_remark;
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
}
