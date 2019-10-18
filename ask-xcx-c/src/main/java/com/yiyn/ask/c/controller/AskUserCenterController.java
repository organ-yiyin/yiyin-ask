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
import com.yiyn.ask.base.utils.StringUtils;
import com.yiyn.ask.c.wechat.controller.XcxOAuthService;
import com.yiyn.ask.wechat.dto.WechatXcxDto;
import com.yiyn.ask.xcx.center.service.impl.CodeService;
import com.yiyn.ask.xcx.consult.po.ConsultRefPo;
import com.yiyn.ask.xcx.user.service.impl.UserService;

@Controller
@RequestMapping("/askUserCenter")
public class AskUserCenterController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CodeService codeService;
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserRef.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getUserRef(HttpServletRequest request,
			HttpServletResponse response,String sessionid) throws Exception {
		logger.info("getUserRef==参数sessionid为" + sessionid);
		// 新建成功返回
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//获取微信小程序信息
		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("no", dto.getDb_open_id());
		resultMap.put("reList", userService.getRefList(param));
		
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getRefInfo.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getRefInfo(HttpServletRequest request,
			HttpServletResponse response,String id) throws Exception {
		logger.info("关联人信息查找getRefInfo");
		// 新建成功返回
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//获取微信小程序信息
		resultMap.put("info", userService.getRefInfo(id));
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveRefInfo.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String saveRefInfo(HttpServletRequest request,
			HttpServletResponse response,String id,String name_m,String birth_m,String spec_m,String name_b,String sex_b,
			String birth_b,String edc_b,String weight_b,String spec_b,String sessionid) throws Exception {
		// 新建成功返回
		logger.info("关联人信息保存saveRefInfo");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		try{
			ConsultRefPo p = new ConsultRefPo();
			p.setName_m(name_m);
			p.setBirthday_m(birth_m);
			p.setSpecial_m(spec_m);
			p.setName_b(name_b);
			p.setSex_b(sex_b);
			p.setBirthday_b(birth_b);
			p.setEdc_b(edc_b);
			p.setBirth_weight_b(weight_b);
			p.setSpecial_b(spec_b);
			// 修改关联人信息
			if(!StringUtils.isEmptyString(id)){
				p.setId(new Long(id));
				userService.updRef(p);
			}else{
				//获取微信小程序信息
				WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);
				p.setUser_no(dto.getDb_open_id());
				userService.insRef(p);
			}
			resultMap.put("status", "1");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("status", "-1");
		}
		
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserBList.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getUserBList(HttpServletRequest request,
			HttpServletResponse response,String sessionid) throws Exception {
		logger.info("getUserBList");
		// 新建成功返回
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//获取微信小程序信息
		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);
		//查找c端用户关注咨询师列表
		resultMap.put("reList", userService.getCollectionConsultList(dto.getDb_open_id()));
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * 初始化开始提问页面，加载咨询人关联信息以及问题类型
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/initAbout.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String initAbout(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		logger.info("initAbout");
		// 新建成功返回
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("reList", codeService.findCodeList("ABOUT"));
		return new Gson().toJson(resultMap);
	}
}
