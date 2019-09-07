package com.yiyn.ask.xcx.consult.po;

import com.yiyn.ask.base.po.BasePo;

public class ConsultLogPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String log_type;
	
	private String consult_id;
	
	private String log_desc;
	
	private String log_user_type;

	public String getLog_type() {
		return log_type;
	}

	public void setLog_type(String log_type) {
		this.log_type = log_type;
	}

	public String getConsult_id() {
		return consult_id;
	}

	public void setConsult_id(String consult_id) {
		this.consult_id = consult_id;
	}

	public String getLog_desc() {
		return log_desc;
	}

	public void setLog_desc(String log_desc) {
		this.log_desc = log_desc;
	}

	public String getLog_user_type() {
		return log_user_type;
	}

	public void setLog_user_type(String log_user_type) {
		this.log_user_type = log_user_type;
	}
}
