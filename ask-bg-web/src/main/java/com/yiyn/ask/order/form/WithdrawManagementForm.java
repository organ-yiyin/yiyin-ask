package com.yiyn.ask.order.form;

import com.yiyn.ask.base.constants.WithdrawStatusEnum;
import com.yiyn.ask.base.constants.WithdrawTypeEnum;
import com.yiyn.ask.base.utils.PaginationUtils;

public class WithdrawManagementForm extends PaginationUtils {
	
	private WithdrawTypeEnum[] withdrawTypes = WithdrawTypeEnum.values();
	
	private WithdrawStatusEnum[] withdrawStatus = WithdrawStatusEnum.values();

	public WithdrawTypeEnum[] getWithdrawTypes() {
		return withdrawTypes;
	}

	public void setWithdrawTypes(WithdrawTypeEnum[] withdrawTypes) {
		this.withdrawTypes = withdrawTypes;
	}

	public WithdrawStatusEnum[] getWithdrawStatus() {
		return withdrawStatus;
	}

	public void setWithdrawStatus(WithdrawStatusEnum[] withdrawStatus) {
		this.withdrawStatus = withdrawStatus;
	}
	
}
