package com.yiyn.ask.base.utils;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.yiyn.ask.base.utils.http.HttpClientGetUtils;

@Component
public class SMSUtils{
	//String url = "http://222.73.117.158/msg/HttpBatchSendSM?account=jiekou-zcs-06&pswd=Tch147369&mobile=13918876232&msg=尊敬的用户：您的验证码是675765，请您注意查收。&needstatus=true";
	
	@Value("#{configProperties['sms.username']}")
	private String sms_username;
	
	@Value("#{configProperties['sms.password']}")
	private String sms_password;
	
	@Value("#{configProperties['sms.url.get']}")
	private String sms_url_get;
	
	@Value("#{configProperties['sms.url.post']}")
	private String sms_url_post;
	
	public static String account_key = "account";
	public static String pswd_key = "pswd";
	public static String mobile_key = "mobile";
	public static String msg_key = "msg";
	public static String needstatus_key = "needstatus";
	public static String product_key = "product";
	public static String extno_key = "extno";
	
	/**
	 * 发送短信
	 * 
	 * @param message
	 * @param customerBo
	 */
	public void sendNotice(String message, String phone) throws Exception{
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put(account_key, sms_username);
		paramMap.put(pswd_key, sms_password);
		paramMap.put(mobile_key, phone);
		paramMap.put(msg_key, message);
		paramMap.put(needstatus_key, "true");
		HttpClientGetUtils.sendHttpGet(sms_url_get, paramMap);
	}
	
	public static void main(String[] args) throws Exception{
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put(account_key, "yiyn521");
		paramMap.put(pswd_key, "Ma19980527");
		paramMap.put(mobile_key, "13777844405");
		paramMap.put(msg_key, "【嘉问养育】您好，您收到一条付费咨询，请登陆嘉问养育小程序查看");
		paramMap.put(needstatus_key, "true");
		HttpClientGetUtils.sendHttpGet("http://222.73.117.156:80/msg/HttpBatchSendSM", paramMap);
	}
}
