package com.yiyn.ask.c.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yiyn.ask.base.utils.WechatDecryptDataUtil;
import com.yiyn.ask.c.wechat.controller.XcxOAuthService;
import com.yiyn.ask.wechat.dto.WechatXcxDto;
import com.yiyn.ask.xcx.main.po.DistributorsVisitPo;
import com.yiyn.ask.xcx.main.service.impl.MainService;
import com.yiyn.ask.xcx.user.service.impl.UserService;

@Controller
@RequestMapping("/main")
public class MainController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MainService mainService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private XcxOAuthService oAuthService;
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/initMain.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String initMain(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("initMain");
		// 新建成功返回
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		//查找首页banner条
		resultMap.put("bannerList", mainService.getBannerList());
		//查找首页大咖推荐
		resultMap.put("userList", userService.findRecommendList());
		
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * 打开首页时根据扫描的对象查看是否是渠道商引流，如果是则插入统计数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insDis.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String insDis(HttpServletRequest request,
			HttpServletResponse response,String sessionid,String dis_code,String source) throws Exception {
		logger.info("insDis");
		
		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);
		
		DistributorsVisitPo insp = new DistributorsVisitPo();
		insp.setDis_code(dis_code);
		insp.setSource(source);
		if(dto != null){
			insp.setOpenid(dto.getOpen_id());
			insp.setUnionid(dto.getUnionid());
			mainService.insDis(insp);
			
			logger.info(dis_code + "渠道商统计成功+1");
		}
		
		return null;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getXcxMsg.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getXcxMsg(HttpServletRequest request,
			HttpServletResponse response,String code) throws Exception {
		logger.info("getXcxMsg");
		// 新建成功返回
		Map<String,String> resultMap = oAuthService.validateOAuth(code);
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPhoneNumber.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getPhoneNumber(HttpServletRequest request,
			HttpServletResponse response,String encryptDataB64,String sessionid, String ivB64) throws Exception {
		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);
		// 新建成功返回
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			String res = WechatDecryptDataUtil.decryptData(encryptDataB64, dto.getSession_key(), ivB64);
			JsonObject o = new JsonParser().parse(res).getAsJsonObject();
			
			logger.info("获取后的解码参数为:" + o);
			resultMap.put("userPhone", o.get("phoneNumber").getAsString());
			
			// 更新数据库用户手机号码
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("user_no", dto.getDb_open_id());
			param.put("user_phone", o.get("phoneNumber").getAsString());
			userService.updPhone(param);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Gson().toJson(resultMap);	
	}
}
