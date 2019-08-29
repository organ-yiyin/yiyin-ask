package com.yiyn.ask.xcx.account.po;

import com.yiyn.ask.base.po.BasePo;

public class AccountStatPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_no;
	private String stat_month;
	private String stat_month_f; // 月份用1-12表示
	private double income_total;//累计收益 （收益 - 提现 - 退款）
	private double income; // 收益
	private int income_num; // 单数
	private double withdraw; // 提现
	private int withdraw_num; // 提现次数
	private double refund; // 提现
	private int refund_num; // 退款次数
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	public String getStat_month() {
		return stat_month;
	}
	public void setStat_month(String stat_month) {
		this.stat_month = stat_month;
	}
	public double getIncome_total() {
		return income_total;
	}
	public void setIncome_total(double income_total) {
		this.income_total = income_total;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public int getIncome_num() {
		return income_num;
	}
	public void setIncome_num(int income_num) {
		this.income_num = income_num;
	}
	public double getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(double withdraw) {
		this.withdraw = withdraw;
	}
	public int getWithdraw_num() {
		return withdraw_num;
	}
	public void setWithdraw_num(int withdraw_num) {
		this.withdraw_num = withdraw_num;
	}
	public double getRefund() {
		return refund;
	}
	public void setRefund(double refund) {
		this.refund = refund;
	}
	public int getRefund_num() {
		return refund_num;
	}
	public void setRefund_num(int refund_num) {
		this.refund_num = refund_num;
	}
	public String getStat_month_f() {
		return stat_month_f;
	}
	public void setStat_month_f(String stat_month_f) {
		this.stat_month_f = stat_month_f;
	}
}
