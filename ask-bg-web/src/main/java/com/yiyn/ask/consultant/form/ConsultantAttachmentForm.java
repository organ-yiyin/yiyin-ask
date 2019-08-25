package com.yiyn.ask.consultant.form;

import com.yiyn.ask.base.constants.AttachmentTypeEnum;
import com.yiyn.ask.base.form.BaseForm;

public class ConsultantAttachmentForm extends BaseForm{

	private static final long serialVersionUID = 826117806278824251L;
	
	private Integer object_type;
	
	private Long object_id;
	
	private String attachment_name;
	
	private String attachment_type;
	
	private String oss_url;
	
	private String remark;
	
	private AttachmentTypeEnum[] attachmentTypes = AttachmentTypeEnum.values();

	public AttachmentTypeEnum[] getAttachmentTypes() {
		return attachmentTypes;
	}

	public void setAttachmentTypes(AttachmentTypeEnum[] attachmentTypes) {
		this.attachmentTypes = attachmentTypes;
	}

	public String getAttachment_name() {
		return attachment_name;
	}

	public void setAttachment_name(String attachment_name) {
		this.attachment_name = attachment_name;
	}

	public String getAttachment_type() {
		return attachment_type;
	}

	public void setAttachment_type(String attachment_type) {
		this.attachment_type = attachment_type;
	}

	public String getOss_url() {
		return oss_url;
	}

	public void setOss_url(String oss_url) {
		this.oss_url = oss_url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getObject_type() {
		return object_type;
	}

	public void setObject_type(Integer object_type) {
		this.object_type = object_type;
	}

	public Long getObject_id() {
		return object_id;
	}

	public void setObject_id(Long object_id) {
		this.object_id = object_id;
	}
	
}
