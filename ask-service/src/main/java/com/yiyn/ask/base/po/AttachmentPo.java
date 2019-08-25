package com.yiyn.ask.base.po;

public class AttachmentPo extends BasePo {

	private static final long serialVersionUID = -4386376421798546355L;
	
	/**
	 * @see com.yiyn.ask.base.constants.ObjectTypeEnum
	 */
	private Integer object_type;

	private Long object_id;

	private String attachment_name;
	
	/**
	 * @see com.yiyn.ask.base.constants.AttachmentTypeEnum
	 */
	private String attachment_type;

	private String oss_url;

	private String remark;

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

}
