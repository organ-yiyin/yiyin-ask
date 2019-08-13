package com.yiyn.ask.wechat.dto;

import java.util.SortedMap;
import java.util.TreeMap;

import com.yiyn.ask.base.utils.MD5Util;
import com.yiyn.ask.base.utils.ParamUtils;
import com.yiyn.ask.wechat.config.WeixinConfig;

/**
 * 支付请求
 * 
 * @author Administrator
 *
 */
public class WechatPayDto extends WechatBaseDto{
	
	private String timeStamp;
	
	private String nonceStr;
	
	private String package_str;
	
	private String signType;
	
	private String paySign;
	
	public WechatPayDto(WeixinConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackage_str() {
		return package_str;
	}

	public void setPackage_str(String package_str) {
		this.package_str = package_str;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}
	
	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public String createSign(){
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appId", this.getWeixinConfig().appId);  
		packageParams.put("timeStamp", timeStamp);  
		packageParams.put("nonceStr", nonceStr);  
		packageParams.put("package", package_str);    
		packageParams.put("signType", signType);
		
		String param_str = ParamUtils.buildParams(packageParams);
		param_str = param_str + "&key=" + this.getWeixinConfig().security_key;
		System.out.println("WechatPayDto md5 sb:" + param_str);
		String sign = MD5Util.MD5Encode(param_str, "utf-8").toUpperCase();
		System.out.println("WechatPayDto sign:" + sign);
		return sign;
	}
	
}

