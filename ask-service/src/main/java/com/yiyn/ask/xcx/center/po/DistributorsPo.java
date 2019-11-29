package com.yiyn.ask.xcx.center.po;

import com.yiyn.ask.base.po.BasePo;

public class DistributorsPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dis_code;
	private String dis_name;
	private String link_url;
	private String wechat_url;
	public String getDis_code() {
		return dis_code;
	}
	public void setDis_code(String dis_code) {
		this.dis_code = dis_code;
	}
	public String getDis_name() {
		return dis_name;
	}
	public void setDis_name(String dis_name) {
		this.dis_name = dis_name;
	}
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	public String getWechat_url() {
		return wechat_url;
	}
	public void setWechat_url(String wechat_url) {
		this.wechat_url = wechat_url;
	}
}
