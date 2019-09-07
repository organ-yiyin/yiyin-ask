package com.yiyn.ask.order.form;

import com.yiyn.ask.base.constants.AttachmentTypeEnum;
import com.yiyn.ask.base.constants.ConsultStatuEnum;
import com.yiyn.ask.base.form.BaseForm;

public class OrderForm extends BaseForm{

	private static final long serialVersionUID = -9051878305444203630L;

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
