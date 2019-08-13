package com.yiyn.ask.xcx.account.po;

import com.yiyn.ask.base.po.BasePo;

public class AccountStatPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String stat_month;
	private String income; // 累计收益
	private String income_totax; // 税前收益
	private String tax; //税款
	private String order_num;
	public String getStat_month() {
		return stat_month;
	}
	public void setStat_month(String stat_month) {
		this.stat_month = stat_month;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getIncome_totax() {
		return income_totax;
	}
	public void setIncome_totax(String income_totax) {
		this.income_totax = income_totax;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
}
