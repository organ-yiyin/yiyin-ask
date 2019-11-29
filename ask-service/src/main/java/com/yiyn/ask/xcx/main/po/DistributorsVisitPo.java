package com.yiyn.ask.xcx.main.po;

import com.yiyn.ask.base.po.BasePo;

public class DistributorsVisitPo extends BasePo {
	/**
	 * 渠道商访问入库
	 */
	private static final long serialVersionUID = 1L;
	private String dis_code;
	private String source;
	private String openid;
	private String unionid;
	public String getDis_code() {
		return dis_code;
	}
	public void setDis_code(String dis_code) {
		this.dis_code = dis_code;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
}
