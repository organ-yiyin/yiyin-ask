package com.yiyn.ask.order.form;

import com.yiyn.ask.base.constants.AttachmentTypeEnum;
import com.yiyn.ask.base.constants.ConsultStatuEnum;

public class OrderForm {
	
	private ConsultStatuEnum[] consultStatusList = ConsultStatuEnum.values();
	
	private AttachmentTypeEnum[] attachmentTypes = AttachmentTypeEnum.values();

	public ConsultStatuEnum[] getConsultStatusList() {
		return consultStatusList;
	}

	public void setConsultStatusList(ConsultStatuEnum[] consultStatusList) {
		this.consultStatusList = consultStatusList;
	}

	public AttachmentTypeEnum[] getAttachmentTypes() {
		return attachmentTypes;
	}

	public void setAttachmentTypes(AttachmentTypeEnum[] attachmentTypes) {
		this.attachmentTypes = attachmentTypes;
	}

}
