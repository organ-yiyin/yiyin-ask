package com.ask.xcx.manage.wechat.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yiyn.ask.base.utils.http.HttpClientGetUtils;
import com.yiyn.ask.base.utils.http.HttpClientPostUtils;
import com.yiyn.ask.wechat.config.WeixinConfig;

/**
 * 第一次打开小程序的的时候，进行微信授权，可以获得用户id等用于支付请求
 * 
 * @author Administrator
 *
 */
@Service("oAuthService")
public class XcxOAuthService {
	// 查看小程序资料https://blog.csdn.net/qq_33616529/article/details/79080141
	private static Logger logger = LoggerFactory.getLogger(XcxOAuthService.class);
	
	@Value("#{configProperties['wechat.url']}")
	private String wechat_url;
	
	@Value("#{configProperties['wechat.oauth.enable']}")
	private String wechat_oauth_enable;
	
	public static String getsessionUrl = "https://api.weixin.qq.com/sns/jscode2session";
	
	public static String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
	@Autowired
	private WeixinConfig weixinConfig;
	
	/**
	 * 获得token
	 * 
	 * @param code
	 * @throws Exception
	 */
	private JsonObject getToken() throws Exception {
		logger.info("getAccessToken");
		StringBuilder url = new StringBuilder();
		url.append(accessTokenUrl + "?");
		url.append("appid=" + weixinConfig.appId);
		url.append("&secret=").append(weixinConfig.appsecret);
		url.append("&grant_type=client_credential");
		logger.info(url.toString());
		String content = HttpClientGetUtils.getHttpGetContent(url.toString(), "utf-8");
		logger.info(content);
		JsonObject o = new JsonParser().parse(content).getAsJsonObject();
		return o;
	}
	
	/**
	 * 获得token
	 * 
	 * @param code
	 * @throws Exception
	 */
	public InputStream getEwm(String user_no) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("path", "pages/user/user_about/user_about?user_no="+ user_no);//要跳转的页面
		params.put("width", "430");//二维码宽度
		logger.info("getEwm");
		JsonObject tokenObj = this.getToken();
		String token = tokenObj.get("access_token").getAsString();
		StringBuilder url = new StringBuilder();
		url.append("https://api.weixin.qq.com/wxa/getwxacode" + "?");
		url.append("access_token=" + token);
		StringEntity entity = new StringEntity(new Gson().toJson(params));
		entity.setContentType("image/png");
		logger.info(url.toString());
		HttpResponse content = HttpClientPostUtils.getHttpPostContentByEntityForEwm(url.toString(), entity,"utf-8");
		return content.getEntity().getContent();
	}
}
