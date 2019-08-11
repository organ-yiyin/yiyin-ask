package com.yiyn.ask.wechat.dto;

public class WechatPrepayResponseDto extends WechatBaseResponseDto{
	
	// 交易类型，取值为：JSAPI，NATIVE，APP等
	private String trade_type;
	// 微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
	private String prepay_id;
	// trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付。注意：code_url的值并非固定，使用时按照URL格式转成二维码即可
	private String code_url;

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getCode_url() {
		return code_url;
	}

	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	
}
