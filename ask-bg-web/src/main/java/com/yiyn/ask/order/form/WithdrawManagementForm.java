package com.yiyn.ask.order.form;

import com.yiyn.ask.base.constants.WithDrawStatusEnum;
import com.yiyn.ask.base.constants.WithDrawTypeEnum;
import com.yiyn.ask.base.utils.PaginationUtils;

public class WithdrawManagementForm extends PaginationUtils {
	
	private WithDrawTypeEnum[] withdrawTypes = WithDrawTypeEnum.values();
	
	private WithDrawStatusEnum[] withdrawStatus = WithDrawStatusEnum.values();

	public WithDrawTypeEnum[] getWithdrawTypes() {
		return withdrawTypes;
	}

	public void setWithdrawTypes(WithDrawTypeEnum[] withdrawTypes) {
		this.withdrawTypes = withdrawTypes;
	}

	public WithDrawStatusEnum[] getWithdrawStatus() {
		return withdrawStatus;
	}

	public void setWithdrawStatus(WithDrawStatusEnum[] withdrawStatus) {
		this.withdrawStatus = withdrawStatus;
	}
	
}
