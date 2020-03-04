package com.yiyn.ask.xcx.consult.po;

import com.yiyn.ask.base.po.BasePo;

public class ConsultSheetSmsPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sms_phone;
	
	private String operation_type;
	
	private Long consult_id;
	
	private String status;

	public String getSms_phone() {
		return sms_phone;
	}

	public void setSms_phone(String sms_phone) {
		this.sms_phone = sms_phone;
	}

	public String getOperation_type() {
		return operation_type;
	}

	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}

	public Long getConsult_id() {
		return consult_id;
	}

	public void setConsult_id(Long consult_id) {
		this.consult_id = consult_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
