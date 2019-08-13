package com.yiyn.ask.xcx.main.po;

import com.yiyn.ask.base.po.BasePo;

public class BannerPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String banner_img;
	private String banner_link;
	private String banner_desc;
	private String xh;
	public String getBanner_img() {
		return banner_img;
	}
	public void setBanner_img(String banner_img) {
		this.banner_img = banner_img;
	}
	public String getBanner_link() {
		return banner_link;
	}
	public void setBanner_link(String banner_link) {
		this.banner_link = banner_link;
	}
	public String getBanner_desc() {
		return banner_desc;
	}
	public void setBanner_desc(String banner_desc) {
		this.banner_desc = banner_desc;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
}
