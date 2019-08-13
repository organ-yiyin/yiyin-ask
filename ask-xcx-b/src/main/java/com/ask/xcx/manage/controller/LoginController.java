package com.ask.xcx.manage.controller;

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
import com.yiyn.ask.base.utils.MD5Util;
import com.yiyn.ask.xcx.user.po.UserPo;
import com.yiyn.ask.xcx.user.service.impl.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService userService;

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String loginYys(HttpServletRequest request,
			HttpServletResponse response, String username, String pas)
			throws Exception {
		logger.info("login");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();

		UserPo po = userService.findByUserno(username);
		if(po == null){
			resultMap.put("status", "-1");
		}else{
			// 根据用户密码获取加密过的密码
			boolean sfmatch = this.match(pas, po.getUser_password());
			
			if(sfmatch){
				resultMap.put("status", "0");
				resultMap.put("user_no", po.getUser_no());
				resultMap.put("user_name", po.getUser_name());
			}else{
				resultMap.put("status", "-2");
			}
		}

		return new Gson().toJson(resultMap);
	}
	
//	/**
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/reg.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
//	@ResponseBody
//	public String reg(HttpServletRequest request,
//			HttpServletResponse response, String username, String pas)
//			throws Exception {
//		logger.info("reg");
//		// 新建成功返回
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//
//		UserPo insp = new UserPo();
//		insp.setUser_no("TUPZ");
//		insp.setUser_password(encode("TUPZ","1234"));
//		userService.insetUser(insp);
//		return new Gson().toJson(resultMap);
//	}
	
  public boolean match(String pas, String encodedPassword) {
	String encodePas = MD5Util.MD5Encode(pas, "UTF-8");
			
	if(encodePas.equals(encodedPassword)){
		return true;
	}else{
		return false;
	}
  }

//	private final String SEPARATOR_USERNAME_AND_PASSWORD = "_@_@_";
//
//    public boolean match(String username,String pas, String encodedPassword) {
//    	CharSequence s = username + SEPARATOR_USERNAME_AND_PASSWORD + pas;
//    	BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
//        return encode.matches(s, encodedPassword);
//    }
//    
//    public String encode(String username,String pas) {
//    	CharSequence s = username + SEPARATOR_USERNAME_AND_PASSWORD + pas;
//    	BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
//        return encode.encode(s);
//    }
}
