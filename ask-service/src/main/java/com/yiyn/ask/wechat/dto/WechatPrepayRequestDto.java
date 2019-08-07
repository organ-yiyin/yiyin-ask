package com.yiyn.ask.wechat.dto;

import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;

import com.yiyn.ask.base.utils.ParamUtils;
import com.yiyn.ask.wechat.config.WeixinConfig;

public class WechatPrepayRequestDto extends WechatBaseDto{
	
	public WechatPrepayRequestDto(WeixinConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}

	// 随机字符串
	private String nonce_str;
	
	// 签名
	//private String sign;
	
	// 商品描述
	private String body;
	
	// 商户订单号
	private String out_trade_no;
	
	// 总金额
	private int total_fee;
	
	// 终端IP
	private String spbill_create_ip;
	
	// 通知地址
	private String notify_url;
	
	//交易类型
	private String trade_type;
	
	// 用户标识
	private String openid;

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	//public String getSign() {
	//	return sign;
	//}

	//public void setSign(String sign) {
	//	this.sign = sign;
	//}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public String createSign(){
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", this.getWeixinConfig().appId);  
		packageParams.put("mch_id", this.getWeixinConfig().mch_id);  
		packageParams.put("nonce_str", nonce_str);  
		packageParams.put("body", body);  
		//packageParams.put("attach", attach);  
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("total_fee", String.valueOf(this.total_fee));   
		packageParams.put("spbill_create_ip", spbill_create_ip);  
		packageParams.put("notify_url", notify_url);  
		packageParams.put("trade_type", trade_type);  
		packageParams.put("openid", openid);  
		
		String param_str = ParamUtils.buildParams(packageParams);
		param_str = param_str + "&key=" + this.getWeixinConfig().security_key;
		
		System.out.println("WechatPrepayRequestDto md5 sb:" + param_str);
		//String sign = MD5Util.MD5Encode(param_str, "utf-8").toUpperCase();
		String sign = DigestUtils.md5Hex(param_str).toUpperCase();
		System.out.println("WechatPrepayRequestDto sign:" + sign);
		return sign;
	}
	
	public String getWechatXml(){
		
		String xmlStr = "";
		xmlStr = xmlStr 
				+ "<xml>"
				+ "<appid>" +  this.getWeixinConfig().appId + "</appid>"
				+ "<mch_id>" + this.getWeixinConfig().mch_id + "</mch_id>"
				+ "<nonce_str>" + this.getNonce_str() + "</nonce_str>"
				+ "<sign>" + this.createSign() + "</sign>"
				+ "<body><![CDATA[" + this.getBody() + "]]></body>"
				+ "<out_trade_no>" + this.getOut_trade_no() + "</out_trade_no>"
				+ "<total_fee>" + this.getTotal_fee() + "</total_fee>"
				+ "<spbill_create_ip>" + this.getSpbill_create_ip() + "</spbill_create_ip>"
				+ "<notify_url>" + this.getNotify_url() + "</notify_url>"
				+ "<trade_type>" + this.getTrade_type() + "</trade_type>"
				+ "<openid>" + this.getOpenid() + "</openid>"
				+ "</xml>";
		return xmlStr;
	}
}
