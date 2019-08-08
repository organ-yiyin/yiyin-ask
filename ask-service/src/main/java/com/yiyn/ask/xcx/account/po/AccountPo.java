package com.yiyn.ask.xcx.account.po;

import com.yiyn.ask.base.po.BasePo;

public class AccountPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String balance;
	private String withdraw;
	
	private String income_total; // 累计收益
	private String income_last_m; //上月收益
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
}
