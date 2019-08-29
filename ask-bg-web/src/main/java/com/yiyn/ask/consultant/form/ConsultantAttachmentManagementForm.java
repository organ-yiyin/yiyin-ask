package com.yiyn.ask.consultant.form;

import com.yiyn.ask.base.constants.AttachmentTypeEnum;
import com.yiyn.ask.base.utils.PaginationUtils;

public class ConsultantAttachmentManagementForm extends PaginationUtils{
	
	private AttachmentTypeEnum[] attachmentTypes = AttachmentTypeEnum.values();

	public AttachmentTypeEnum[] getAttachmentTypes() {
		return attachmentTypes;
	}

	public void setAttachmentTypes(AttachmentTypeEnum[] attachmentTypes) {
		this.attachmentTypes = attachmentTypes;
	}

}
