package com.yiyn.ask.xcx.account.po;

import com.yiyn.ask.base.po.BasePo;

public class AccountWithDrawPo extends BasePo {
	/**
	 * 点击提现后插入提现信息
	 */
	private static final long serialVersionUID = 1L;
	private String account_id;
	private double withdraw; //提现金额
	private double withdraw_act; // 提现金额*30%
	private double service_charge;//手续费
	private String withdraw_type; // 提现状态  1：微信  2：线下
	private String status; // 提现状态

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public double getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(double withdraw) {
		this.withdraw = withdraw;
	}

	public double getWithdraw_act() {
		return withdraw_act;
	}

	public void setWithdraw_act(double withdraw_act) {
		this.withdraw_act = withdraw_act;
	}

	public double getService_charge() {
		return service_charge;
	}

	public void setService_charge(double service_charge) {
		this.service_charge = service_charge;
	}

	public String getWithdraw_type() {
		return withdraw_type;
	}

	public void setWithdraw_type(String withdraw_type) {
		this.withdraw_type = withdraw_type;
	}
}
