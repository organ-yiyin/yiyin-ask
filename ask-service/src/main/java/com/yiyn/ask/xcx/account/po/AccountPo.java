package com.yiyn.ask.xcx.account.po;

import java.math.BigDecimal;

import com.yiyn.ask.base.po.BasePo;

public class AccountPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long user_b_id;
	private String user_no;
	private BigDecimal balance;
	private BigDecimal withdraw;
	private Integer user_type;
	private String user_name;
	private Integer revision;
	
	private String income_total; // 累计收益
	private String income_last_m; //上月收益
	
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(BigDecimal withdraw) {
		this.withdraw = withdraw;
	}
	public Integer getUser_type() {
		return user_type;
	}
	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getIncome_total() {
		return income_total;
	}
	public void setIncome_total(String income_total) {
		this.income_total = income_total;
	}
	public String getIncome_last_m() {
		return income_last_m;
	}
	public void setIncome_last_m(String income_last_m) {
		this.income_last_m = income_last_m;
	}
	public Long getUser_b_id() {
		return user_b_id;
	}
	public void setUser_b_id(Long user_b_id) {
		this.user_b_id = user_b_id;
	}
	public Integer getRevision() {
		return revision;
	}
	public void setRevision(Integer revision) {
		this.revision = revision;
	}
	
}
