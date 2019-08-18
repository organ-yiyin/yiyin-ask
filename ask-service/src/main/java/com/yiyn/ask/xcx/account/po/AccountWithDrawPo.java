package com.yiyn.ask.xcx.account.po;

import java.math.BigDecimal;

import com.yiyn.ask.base.po.BasePo;

public class AccountWithDrawPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long account_id;
	private Long order_id;
	private BigDecimal withdraw; // 提现金额
	/**
	 * 转账方式 当前默认是 1：微信
	 * 
	 * @see com.yiyn.ask.base.constants.WithdrawTypeEnum
	 */
	private String withdraw_type;
	/**
	 * 提现状态
	 * 
	 * @see com.yiyn.ask.base.constants.WithdrawStatusEnum
	 */
	private Integer status;

	public Long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public BigDecimal getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(BigDecimal withdraw) {
		this.withdraw = withdraw;
	}

	public String getWithdraw_type() {
		return withdraw_type;
	}

	public void setWithdraw_type(String withdraw_type) {
		this.withdraw_type = withdraw_type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
