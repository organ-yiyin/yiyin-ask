package com.yiyn.ask.c.controller;

import java.util.HashMap;
import java.util.List;
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
import com.yiyn.ask.base.constants.UserCouponStatusEnum;
import com.yiyn.ask.base.utils.StringUtils;
import com.yiyn.ask.base.utils.WechatDecryptDataUtil;
import com.yiyn.ask.c.wechat.controller.XcxOAuthService;
import com.yiyn.ask.wechat.dto.WechatXcxDto;
import com.yiyn.ask.xcx.main.po.DistributorsVisitPo;
import com.yiyn.ask.xcx.main.service.impl.MainService;
import com.yiyn.ask.xcx.user.po.UserCPo;
import com.yiyn.ask.xcx.user.po.UserCouponPo;
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
			HttpServletResponse response,String sessionid,String dis_code,String source,String newuser) throws Exception {
		logger.info("insDis");
		
		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);
		
		String openid = dto.getOpen_id();
		if(dto != null && !StringUtils.isEmptyString(openid)){
			boolean sfcj = false;
			// 如果是新创建用户的话，渠道商进入肯定要插入渠道商统计表，并且更新用户渠道商信息
			if("1".equals(newuser)){
				sfcj = true;
				Map<String,Object> insM = new HashMap<String,Object>();
				insM.put("user_no", openid);
				insM.put("dis_no", dis_code);
				userService.updDis(insM);
			}else{
				// 如果老用户的话，渠道商扫码进来，要查看此用户是否属于渠道商，如果属于，则插入渠道商数据
				// 获取用户渠道商数据
				UserCPo p = userService.findUserCInfo(openid);
				if(!StringUtils.isEmptyString(p.getDis_no())){
					sfcj = true;
				}
			}
			
			if(sfcj){
				DistributorsVisitPo insp = new DistributorsVisitPo();
				insp.setDis_code(dis_code);
				insp.setSource(source);
				insp.setOpenid(openid);
				insp.setUnionid(dto.getUnionid());
				mainService.insDis(insp);
				logger.info(dis_code + "渠道商统计成功+1");
			}
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
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/initMainCoupon.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String initMainCoupon(HttpServletRequest request,
			HttpServletResponse response,String sessionid) throws Exception {
		logger.info("initMainCoupon");
		// 新建成功返回
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//获取微信小程序信息
		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);
		
		//查找c端用户关注咨询师列表
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("user_c_no", dto.getDb_open_id());
		List<UserCouponPo> reList = userService.getCouponExsitList(m);
		resultMap.put("couponList", reList);
		String ids = "";
		for(UserCouponPo c:reList){
			ids += c.getCoupon_id() + ",";
		}
		resultMap.put("ids", StringUtils.isEmptyString(ids)?"":ids.substring(0, ids.length()-1));
		resultMap.put("couponL", reList.size());
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * 首页用户点击领取优惠券时触发
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insCoupon.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String insCoupon(HttpServletRequest request,
			HttpServletResponse response,String ids,String sessionid) throws Exception {
		// 新建成功返回
		logger.info("领取优惠券");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//获取微信小程序信息
		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);
		try{
			String[] idArr = ids.split(",");
			for(String id:idArr){
				UserCouponPo p = new UserCouponPo();
				p.setCoupon_id(id);
				p.setUser_c_no(dto.getDb_open_id());
				p.setStatus(UserCouponStatusEnum.NO_USERD.getName());
				userService.insertCouponC(p);
			}
			
			resultMap.put("status", "1");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("status", "-1");
		}
		return new Gson().toJson(resultMap);
	}
}
