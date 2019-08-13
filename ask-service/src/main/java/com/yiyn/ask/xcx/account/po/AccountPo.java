package com.yiyn.ask.xcx.account.po;

import com.yiyn.ask.base.po.BasePo;

public class AccountPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String balance;
	private String withdraw;
	private String user_type;
	private String user_name;
	private String user_no;
	
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(String withdraw) {
		this.withdraw = withdraw;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
}
