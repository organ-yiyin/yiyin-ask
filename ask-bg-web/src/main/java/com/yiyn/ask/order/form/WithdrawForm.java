package com.yiyn.ask.order.form;

import com.yiyn.ask.base.constants.AttachmentTypeEnum;
import com.yiyn.ask.base.constants.WithDrawStatusEnum;
import com.yiyn.ask.base.constants.WithDrawTypeEnum;

public class WithdrawForm {

	private WithDrawTypeEnum[] withdrawTypes = WithDrawTypeEnum.values();

	private WithDrawStatusEnum[] withdrawStatus = WithDrawStatusEnum.values();

	private AttachmentTypeEnum[] attachmentTypes = AttachmentTypeEnum.values();

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

	public AttachmentTypeEnum[] getAttachmentTypes() {
		return attachmentTypes;
	}

	public void setAttachmentTypes(AttachmentTypeEnum[] attachmentTypes) {
		this.attachmentTypes = attachmentTypes;
	}

}
