package com.yiyn.ask.base.po;

import java.util.Date;

public class BasePo implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String delete_flag;
	
	private String created_by;
	
	private Date created_time;
	
	private String created_time_format;
	
	private String updated_by;
	
	private Date updated_time;
	
	private String updated_time_format;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public Date getUpdated_time() {
		return updated_time;
	}

	public void setUpdated_time(Date updated_time) {
		this.updated_time = updated_time;
	}

	public String getCreated_time_format() {
		return created_time_format;
	}

	public void setCreated_time_format(String created_time_format) {
		this.created_time_format = created_time_format;
	}

	public String getUpdated_time_format() {
		return updated_time_format;
	}

	public void setUpdated_time_format(String updated_time_format) {
		this.updated_time_format = updated_time_format;
	}
}
