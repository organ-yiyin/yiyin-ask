package com.yiyn.ask.market.form;

import com.yiyn.ask.base.constants.AdPositionEnum;
import com.yiyn.ask.base.form.BaseForm;

public class AdForm  extends BaseForm{

	private static final long serialVersionUID = -6178060893146739238L;
	// 约定的使用位置
	private String ad_position;
	// 图片标题
	private String ad_title;
	// 点击图片跳转的地址
	private String ad_url;
	// 图片地址
	private String pic_url;
	// 排序,数字越小排前面
	private Integer order_num;
	// 描述
	private String description;
	// 点击次数
	private int visit_count;
	
	private AdPositionEnum[] adPositions = AdPositionEnum.values();

	public String getAd_position() {
		return ad_position;
	}
	public void setAd_position(String ad_position) {
		this.ad_position = ad_position;
	}
	public String getAd_title() {
		return ad_title;
	}
	public void setAd_title(String ad_title) {
		this.ad_title = ad_title;
	}
	public String getAd_url() {
		return ad_url;
	}
	public void setAd_url(String ad_url) {
		this.ad_url = ad_url;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getVisit_count() {
		return visit_count;
	}
	public void setVisit_count(int visit_count) {
		this.visit_count = visit_count;
	}
	public AdPositionEnum[] getAdPositions() {
		return adPositions;
	}
	public void setAdPositions(AdPositionEnum[] adPositions) {
		this.adPositions = adPositions;
	}
	public Integer getOrder_num() {
		return order_num;
	}
	public void setOrder_num(Integer order_num) {
		this.order_num = order_num;
	}

}
