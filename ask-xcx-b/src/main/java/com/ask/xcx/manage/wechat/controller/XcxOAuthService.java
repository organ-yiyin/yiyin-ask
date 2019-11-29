package com.ask.xcx.manage.wechat.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
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
import com.yiyn.ask.base.utils.DateUtils;
import com.yiyn.ask.base.utils.StringUtils;
import com.yiyn.ask.base.utils.http.HttpClientGetUtils;
import com.yiyn.ask.base.utils.http.HttpClientPostUtils;
import com.yiyn.ask.wechat.config.WeixinConfig;
import com.yiyn.ask.wechat.dto.WechatXcxDto;
import com.yiyn.ask.xcx.user.po.UserCPo;
import com.yiyn.ask.xcx.user.po.UserPo;
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
	
	@Value("#{configProperties['wechat.messageMb']}")
	private String messageMb;
	
	@Value("#{configProperties['wechat.appId_c']}")
	private String appId_c;
	
	@Value("#{configProperties['wechat.appsecret_c']}")
	private String appsecret_c;
	
	public static String getsessionUrl = "https://api.weixin.qq.com/sns/jscode2session";
	
	public static String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
	
	public static String sendMsgUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send";
	@Autowired
	private WeixinConfig weixinConfig;
	
	@Autowired
	private UserService userService;
	/**
	 * 获取微信的唯一性标识更新到B端用户表，发模版消息用
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void validateOAuth(String code,String user_no) throws Exception{
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
	    ServletContext application = webApplicationContext.getServletContext(); 
		JsonObject tokenObj = this.getBToken(code);
		String sessionid="";
		// 如果token过期了，重新获取微信access_token
        if(tokenObj.get("openid")!=null&&!tokenObj.get("openid").equals("")){  
        	String openid = tokenObj.get("openid").getAsString();
        	String session_key  = tokenObj.get("session_key").getAsString();
        	String unionid = "";
        	// 如果关注过公众号，unionid不为空
        	if(tokenObj.get("unionid") != null){
        		unionid = tokenObj.get("unionid").getAsString();
        	}
    		// 更新B端用户表，新增open_id
        	if(!StringUtils.isEmptyString(openid)){
        		UserPo insPo = new UserPo();
            	insPo.setUser_no(user_no);
            	insPo.setOpen_id(openid);
            	insPo.setUnionid(unionid);
            	insPo.setType("loginopen");
            	userService.updInfo(insPo);
        	}
        	
    		// 刷新时间比微信短，以免请求时token失效
        	sessionid = openid + session_key;
        	application.setAttribute(sessionid, new WechatXcxDto(session_key,openid,"","",""));  
        }
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
	private JsonObject getBToken(String jscode) throws Exception{
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
	private JsonObject getToken() throws Exception {
		logger.info("getAccessToken");
		StringBuilder url = new StringBuilder();
		url.append(accessTokenUrl + "?");
		url.append("appid=" + appId_c);
		url.append("&secret=").append(appsecret_c);
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
		params.put("page", "pages/counselor/detail/couns_detail");//要跳转的页面" +
		params.put("scene", user_no);//二维码参数
		params.put("width", "430");//二维码宽度
		logger.info("getEwm");
		JsonObject tokenObj = this.getToken();
		String token = tokenObj.get("access_token").getAsString();
		StringBuilder url = new StringBuilder();
		url.append("https://api.weixin.qq.com/wxa/getwxacodeunlimit" + "?");
		url.append("access_token=" + token);
		StringEntity entity = new StringEntity(new Gson().toJson(params));
		entity.setContentType("image/png");
		logger.info(url.toString());
		HttpResponse content = HttpClientPostUtils.getHttpPostContentByEntityForEwm(url.toString(), entity,"utf-8");
		return content.getEntity().getContent();
	}
	
	/**
	 * 获得各渠道商的二维码
	 * 
	 * @param code
	 * @throws Exception
	 */
	public InputStream getQdsEwm(String dis_code) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("page", "pages/index/index");//要跳转的页面" +
		params.put("scene", dis_code);//二维码宽度
		params.put("width", "430");//二维码宽度
		logger.info("新生成渠道商" + dis_code + "的小程序码");
		JsonObject tokenObj = this.getToken();
		String token = tokenObj.get("access_token").getAsString();
		StringBuilder url = new StringBuilder();
		url.append("https://api.weixin.qq.com/wxa/getwxacodeunlimit" + "?");
		url.append("access_token=" + token);
		StringEntity entity = new StringEntity(new Gson().toJson(params));
		entity.setContentType("image/png");
		logger.info(url.toString());
		HttpResponse content = HttpClientPostUtils.getHttpPostContentByEntityForEwm(url.toString(), entity,"utf-8");
		return content.getEntity().getContent();
	}
	
	/**
	 * 用户回答后发送信息给用户
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
		keyword1Map.put("value", "收到新回复，请点击查看");
		dataMap.put("keyword1", keyword1Map);
		
		Map<String,String> keyword2Map = new HashMap<String,String>();
		keyword2Map.put("value", param.get("hfr"));
		dataMap.put("keyword2", keyword2Map);
		
		Map<String,String> keyword3Map = new HashMap<String,String>();
		keyword3Map.put("value", "请及时查看回答，24小时候后此订单结束。");
		dataMap.put("keyword3", keyword3Map);
		
		Map<String,String> keyword4Map = new HashMap<String,String>();
		keyword4Map.put("value", DateUtils.getNowTime(DateUtils.DATE_FULL_STR));
		dataMap.put("keyword4", keyword4Map);
		
		m.put("data", dataMap);
		JsonObject tokenObj = this.getToken();
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
