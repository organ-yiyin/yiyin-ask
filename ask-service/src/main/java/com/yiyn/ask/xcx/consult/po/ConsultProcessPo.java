package com.yiyn.ask.xcx.consult.po;

import com.yiyn.ask.base.po.BasePo;

public class ConsultProcessPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String consultation_id;
	
	private String content;//内容
	
	private String content_type;//内容类型
	
	private String send_type;// 下订单时宝宝的年龄

	public String getConsultation_id() {
		return consultation_id;
	}

	public void setConsultation_id(String consultation_id) {
		this.consultation_id = consultation_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public String getSend_type() {
		return send_type;
	}

	public void setSend_type(String send_type) {
		this.send_type = send_type;
	}
}
