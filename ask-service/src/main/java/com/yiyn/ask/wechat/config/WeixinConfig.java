package com.yiyn.ask.wechat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeixinConfig {
	
	/**
	public static String appId = "wx5ae52c0fb04b76e8";
	public static String appsecret = "cdf3f83a9c0edc0389d561763601fbf9";
	
	
	public static String security_key = "1234567890YIYING1234567890YIYING";
	public static String mch_id = "1241108102";
	**/
	// 公众账号ID
	@Value("#{configProperties['wechat.appId']}")
	public String appId = "";
	
	@Value("#{configProperties['wechat.appsecret']}")
	public String appsecret = "";
	
	@Value("#{configProperties['wechat.security_key']}")
	public String security_key = "";
	
	@Value("#{configProperties['wechat.gzhappId']}")
	public String gzhappId = "";
	
	// 商户号
	@Value("#{configProperties['wechat.mch_id']}")
	public String mch_id = "";

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getSecurity_key() {
		return security_key;
	}

	public void setSecurity_key(String security_key) {
		this.security_key = security_key;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getGzhappId() {
		return gzhappId;
	}

	public void setGzhappId(String gzhappId) {
		this.gzhappId = gzhappId;
	}
}
