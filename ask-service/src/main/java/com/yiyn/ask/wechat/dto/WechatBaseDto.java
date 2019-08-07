package com.yiyn.ask.wechat.dto;

import com.yiyn.ask.wechat.config.WeixinConfig;

public class WechatBaseDto {
	
	private WeixinConfig weixinConfig;
	
	public WechatBaseDto(WeixinConfig config){
		this.weixinConfig = config;
	}

	public WeixinConfig getWeixinConfig() {
		return weixinConfig;
	}

	public void setWeixinConfig(WeixinConfig weixinConfig) {
		this.weixinConfig = weixinConfig;
	}
	
}
