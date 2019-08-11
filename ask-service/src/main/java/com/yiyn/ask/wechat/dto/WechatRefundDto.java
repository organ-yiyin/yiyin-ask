package com.yiyn.ask.wechat.dto;

import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;

import com.yiyn.ask.base.utils.ParamUtils;
import com.yiyn.ask.wechat.config.WeixinConfig;

/**
 * 退款dto
 * 
 * @author Administrator
 *
 */
public class WechatRefundDto extends WechatBaseDto{
	

	// 设备号
	private String device_info = "";
	// 随机字符串
	private String nonce_str = "";
	// 签名
	private String sign = "";
	// 微信订单号
	private String transaction_id = "";
	// 商户订单号
	private String out_trade_no = "";
	// 商户退款单号
	private String out_refund_no = "";
	// 总金额
	private int total_fee;
	// 退款金额
	private int refund_fee;
	
	/**
	public WechatRefundDto(){
		this.appid = WeixinConfig.appId;
		this.mch_id = WeixinConfig.mch_id;
		this.op_user_id = WeixinConfig.mch_id;
	}**/
	

	public WechatRefundDto(WeixinConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}
	
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public int getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}
	
	public String createSign(){
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", this.getWeixinConfig().appId);  
		packageParams.put("mch_id", this.getWeixinConfig().mch_id);  
		packageParams.put("device_info", device_info);  
		packageParams.put("nonce_str", nonce_str);  
		//packageParams.put("attach", attach);  
		packageParams.put("transaction_id", transaction_id);
		packageParams.put("out_trade_no", out_trade_no);   
		packageParams.put("out_refund_no", out_refund_no);  
		packageParams.put("total_fee", String.valueOf(total_fee));  
		packageParams.put("refund_fee", String.valueOf(refund_fee));  
		packageParams.put("op_user_id", this.getWeixinConfig().mch_id);  
		
		String param_str = ParamUtils.buildParams(packageParams);
		param_str = param_str + "&key=" + this.getWeixinConfig().security_key;
		
		System.out.println("WechatPrepayRequestDto md5 sb:" + param_str);
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
				+ "<device_info>" + this.getDevice_info() + "</device_info>"
				+ "<nonce_str>" + this.getNonce_str() + "</nonce_str>"
				+ "<transaction_id>" + this.getTransaction_id() + "</transaction_id>"
				+ "<out_trade_no>" + this.getOut_trade_no() + "</out_trade_no>"
				+ "<out_refund_no>" + this.getOut_refund_no() + "</out_refund_no>"
				+ "<total_fee>" + this.getTotal_fee() + "</total_fee>"
				+ "<refund_fee>" + this.getRefund_fee() + "</refund_fee>"
				+ "<op_user_id>" + this.getWeixinConfig().mch_id + "</op_user_id>"
				+ "<sign>" + this.createSign() + "</sign>"
				+ "</xml>";
		return xmlStr;
	}
}
