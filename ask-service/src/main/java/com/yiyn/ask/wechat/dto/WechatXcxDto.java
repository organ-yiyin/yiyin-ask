package com.yiyn.ask.wechat.dto;

public class WechatXcxDto implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String session_key;
	private String open_id;
	private String unionid;
	private String db_open_id; // 如果不同的小程序关联同一主体的话，通过unionid相关，支付的时候用使用的小程序。
	private String type;// 10 微信公众号 20 支付宝小程序 30 微信小程序

	public WechatXcxDto(String session_key, String open_id, String unionid,
			String db_open_id, String type) {
		this.session_key = session_key;
		this.open_id = open_id;
		this.unionid = unionid;
		this.db_open_id = db_open_id;
		this.type = type;
	}

	public String getSession_key() {
		return this.session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getOpen_id() {
		return this.open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getUnionid() {
		return this.unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getDb_open_id() {
		return db_open_id;
	}

	public void setDb_open_id(String db_open_id) {
		this.db_open_id = db_open_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}