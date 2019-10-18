package com.yiyn.ask.c.wechat.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yiyn.ask.base.constants.SourceEnum;
import com.yiyn.ask.base.utils.http.HttpClientGetUtils;
import com.yiyn.ask.base.utils.http.HttpClientPostUtils;
import com.yiyn.ask.wechat.config.WeixinConfig;
import com.yiyn.ask.wechat.dto.WechatXcxDto;
import com.yiyn.ask.xcx.user.po.UserCPo;
import com.yiyn.ask.xcx.user.service.impl.UserService;

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
	
	@Value("#{configProperties['wechat.appId_b']}")
	private String appId_b;
	
	@Value("#{configProperties['wechat.appsecret_b']}")
	private String appsecret_b;
	
	@Value("#{configProperties['wechat.messageMb']}")
	private String messageMb;
	
	public static String getsessionUrl = "https://api.weixin.qq.com/sns/jscode2session";
	
	public static String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
	
	public static String sendMsgUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send";
	@Autowired
	private WeixinConfig weixinConfig;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 通用
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public Map<String,String> validateOAuth(String code) throws Exception{
		Map<String,String> re = new HashMap<String,String>();
		// 微信token存放，刷新时间比微信7200秒短
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
	    ServletContext application = webApplicationContext.getServletContext(); 
		JsonObject tokenObj = this.getToken(code);
		String sessionid="";
		String phone="";
		String headimg = "";
		// 如果token过期了，重新获取微信access_token
        if(tokenObj.get("openid")!=null&&!tokenObj.get("openid").equals("")){  
        	logger.info("是否在session中设置token！");
        	String openid = tokenObj.get("openid").getAsString();
        	String session_key  = tokenObj.get("session_key").getAsString();
        	String unionid  = ""; //统一微信开放平台的unindid
        	if(tokenObj.get("unionid") != null){
        		unionid  = tokenObj.get("unionid").getAsString();
        	}
        	String wxtype="";// 账号来源是微信公众号还是小程序
        	boolean sfcj = false;
        	String db_openid = ""; // 如果多个关联，登录一次就不用登录了。
    		// 如果关联id为空，根据openid查找是否创建过用户，如无，直接创建
    		UserCPo ouser = userService.findUserCInfo(openid);
    		// 没创建过则直接创建用户
    		if(ouser == null){
    			sfcj=false;
    		}else{
    			db_openid = ouser.getUser_no();
    			wxtype = ouser.getUser_type();
    			phone = ouser.getUser_phone();
    			headimg = ouser.getUser_headimg();
    			sfcj=true;
    		}
    		//没创建去创建
        	if(!sfcj){
    			// 获取微信用户信息
				UserCPo cUser = new UserCPo();
				cUser.setUser_no(openid);
				cUser.setUser_type(SourceEnum.WXXCX.getName());// 微信小程序建立的用户
				cUser.setUnionid(unionid);
				userService.insCust(cUser);
				db_openid = openid;//关联的openid
    			wxtype = SourceEnum.WXXCX.getName();
        	}
    		
    		// 刷新时间比微信短，以免请求时token失效
        	sessionid = openid + session_key;
        	application.setAttribute(sessionid, new WechatXcxDto(session_key,openid,unionid,db_openid,wxtype));  
        }
        
        re.put("sessionid", sessionid);
        re.put("phone", phone);
        re.put("user_headimg", headimg);
        return re;
	}
	
	
	public static void main(String[] args) throws Exception{
		//System.out.println(getAccessToken("021Yi18y0pA5Kj1LUX6y0RNc8y0Yi18S"));
	}
	
	/**
	 * 获取小程序的openid以及session_key(不保存在客户端，保存到服务器端)
	 * 
	 * 参照：https://developers.weixin.qq.com/ebook?action=get_post_info&token=935589521&volumn=1&lang=zh_CN&book=miniprogram&docid=000cc48f96c5989b0086ddc7e56c0a
	 * 
	 * @return
	 * @throws Exception
	 */
	private JsonObject getToken(String jscode) throws Exception{
		logger.info("getToken");
		StringBuilder url = new StringBuilder();
		url.append(getsessionUrl + "?");
		url.append("grant_type=authorization_code");
		url.append("&appid=" + weixinConfig.appId);
		url.append("&secret=" + weixinConfig.appsecret);
		url.append("&js_code=" + jscode);
		
		logger.info(url.toString());
		String content = HttpClientGetUtils.getHttpGetContent(url.toString(), "utf-8");
		logger.info(content);
		
		JsonObject o = new JsonParser().parse(content).getAsJsonObject();
		return o;
	}
	
	/**
	 * 通过生成的seesionid或者保存在内存中的小程序openid等信息
	 * @param sessionid
	 * @return
	 */
	public static WechatXcxDto getWechatXcxDto(String sessionid){
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
		ServletContext application = webApplicationContext.getServletContext();  
		WechatXcxDto dto = (WechatXcxDto)application.getAttribute(sessionid);
		return dto;
	}
	
	/**
	 * 获得token
	 * 
	 * @param code
	 * @throws Exception
	 */
	private JsonObject getBToken() throws Exception {
		logger.info("getAccessToken");
		StringBuilder url = new StringBuilder();
		url.append(accessTokenUrl + "?");
		url.append("appid=" + appId_b);
		url.append("&secret=").append(appsecret_b);
		url.append("&grant_type=client_credential");
		logger.info(url.toString());
		String content = HttpClientGetUtils.getHttpGetContent(url.toString(), "utf-8");
		logger.info(content);
		JsonObject o = new JsonParser().parse(content).getAsJsonObject();
		return o;
	}
	
	/**
	 * 
	 * C端用户发起提问后推送消息给咨询师
	 * @param openid
	 * @param url
	 */
	public boolean sendMsg(Map<String,String> param) throws Exception{
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("touser", param.get("open_id"));
		m.put("template_id", messageMb);
		m.put("page", param.get("url"));// 回调url
		m.put("form_id", param.get("form_id"));// 回调url
		//组织模版
		Map<String,Map<String,String>> dataMap = new HashMap<String,Map<String,String>>();
				
		Map<String,String> keyword1Map = new HashMap<String,String>();
		keyword1Map.put("value", "收到新的咨询信息，请点击查看。");
		dataMap.put("keyword1", keyword1Map);
		
		Map<String,String> keyword2Map = new HashMap<String,String>();
		keyword2Map.put("value", param.get("zxr"));
		dataMap.put("keyword2", keyword2Map);
		
		Map<String,String> keyword3Map = new HashMap<String,String>();
		keyword3Map.put("value", param.get("zxlx"));
		dataMap.put("keyword3", keyword3Map);
		
		Map<String,String> keyword4Map = new HashMap<String,String>();
		keyword4Map.put("value", param.get("zxsj"));
		dataMap.put("keyword4", keyword4Map);
		
		Map<String,String> keyword5Map = new HashMap<String,String>();
		keyword5Map.put("value", param.get("zxnr"));
		dataMap.put("keyword5", keyword5Map);
		
		m.put("data", dataMap);
		JsonObject tokenObj = this.getBToken();
		String token = tokenObj.get("access_token").getAsString();
		StringBuilder url_send = new StringBuilder();
		url_send.append(sendMsgUrl);
		url_send.append("?access_token=" + token);
		logger.info("param ===== :" + new Gson().toJson(m));
		String content = HttpClientPostUtils.getHttpPostContentByEntity(
				url_send.toString(), new StringEntity(new Gson().toJson(m), "UTF-8"), "UTF-8");
		logger.info("wechatResponseXml:" + content);
		
		JsonObject o = new JsonParser().parse(content).getAsJsonObject();
		
		if(o.get("errcode").getAsInt() == 0){
			return true;
		}else{
			return false;
		}
	}
}
