package com.yiyn.ask.xcx.account.po;

import com.yiyn.ask.base.po.BasePo;

public class AccountWithDrawPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String withdraw; //提现金额
	
	private String status; // 提现状态

	public String getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(String withdraw) {
		this.withdraw = withdraw;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
