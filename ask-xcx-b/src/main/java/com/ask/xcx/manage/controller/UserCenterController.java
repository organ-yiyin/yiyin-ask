package com.ask.xcx.manage.controller;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ask.xcx.manage.wechat.controller.XcxOAuthService;
import com.google.gson.Gson;
import com.yiyn.ask.base.utils.MD5Util;
import com.yiyn.ask.base.utils.OSSClientUtils;
import com.yiyn.ask.base.utils.StringUtils;
import com.yiyn.ask.wechat.dto.WechatXcxDto;
import com.yiyn.ask.xcx.account.service.impl.AccountService;
import com.yiyn.ask.xcx.center.po.CenterResponsePo;
import com.yiyn.ask.xcx.center.po.CodePo;
import com.yiyn.ask.xcx.center.service.impl.CenterResponseService;
import com.yiyn.ask.xcx.center.service.impl.CodeService;
import com.yiyn.ask.xcx.user.po.UserPo;
import com.yiyn.ask.xcx.user.po.UserTagPo;
import com.yiyn.ask.xcx.user.service.impl.UserService;

@Controller
@RequestMapping("/usercenter")
public class UserCenterController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService userService;
	
	@Autowired
	private CenterResponseService centerResponseService;
	
	@Autowired
	private OSSClientUtils ossclientUtils;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CodeService codeService;

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserInfo.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getUserInfo(HttpServletRequest request,
			HttpServletResponse response, String user_no)
			throws Exception {
		logger.info("getUserInfo");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserPo po = userService.findUserInfo(user_no);
		resultMap.put("info",po);
		resultMap.put("evalList",userService.findUserEval(user_no));
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getResponseList.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getResponseList(HttpServletRequest request,
			HttpServletResponse response, String user_no)
			throws Exception {
		logger.info("getResponseList");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("reList",centerResponseService.findResponseList(user_no));
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveResponse.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String saveResponse(HttpServletRequest request,
			HttpServletResponse response, String id,String user_no,String content)
			throws Exception {
		logger.info("saveResponse");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();

		CenterResponsePo p = new CenterResponsePo();
		p.setResponse_desc(content);
		p.setUser_no(user_no);
		try{
			if(StringUtils.isEmptyString(id)){
				centerResponseService.insetRes(p);
			}else{
				p.setId(new Long(id));
				centerResponseService.updRes(p);
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
	@RequestMapping(value = "/savePas.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String savePas(HttpServletRequest request,
			HttpServletResponse response, String newpas,String user_no)
			throws Exception {
		logger.info("savePas");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();

		UserPo p = new UserPo();
		p.setType("pas");
		p.setUser_password(MD5Util.MD5Encode(newpas, "UTF-8"));
		p.setUser_no(user_no);
		try{
			userService.updInfo(p);
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
	@RequestMapping(value = "/initConsultSet.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String initConsultSet(HttpServletRequest request,
			HttpServletResponse response, String user_no)
			throws Exception {
		logger.info("initConsultSet");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("info", userService.findUserInfo(user_no));
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveConsultSet.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String saveConsultSet(HttpServletRequest request,
			HttpServletResponse response, String val,String num,String user_no)
			throws Exception {
		logger.info("saveConsultSet");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();

		UserPo p = new UserPo();
		p.setType("consult");
		p.setAdvice_val(val);
		p.setAdvice_num(num);
		p.setUser_no(user_no);
		try{
			userService.updInfo(p);
			resultMap.put("status", "1");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("status", "-1");
		}
		
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * 接单设置，顺便插入接单日志
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveOrderSet.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String saveOrderSet(HttpServletRequest request,
			HttpServletResponse response, String order_set,String user_no)
			throws Exception {
		logger.info("saveOrderSet");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();

		UserPo updP = new UserPo();
        updP.setType("orderset");
        updP.setUser_no(user_no);
        updP.setOrder_set(order_set);
		try{
			userService.updInfo(updP);
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
	@RequestMapping(value = "/initUserInfo.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String initUserInfo(HttpServletRequest request,
			HttpServletResponse response,
			String user_no)
			throws Exception {
		logger.info("initUserInfo");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserPo p = userService.getUserInfo(user_no);
		resultMap.put("info", p);
		resultMap.put("tagCodeList", userService.getTagCodeList());
		
		//具备技能列表
		if(!StringUtils.isEmptyString(p.getSkilled())){
			resultMap.put("skillList", p.getSkilled().split("；"));
		}
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveUserInfo.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String saveUserInfo(HttpServletRequest request,
			HttpServletResponse response, String work_year,String name,String desc,String tags,String skilled,
			String user_no,String fileupd)
			throws Exception {
		logger.info("saveUserInfo");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 头像上传
		String attr = "";
		UserPo p = new UserPo();
		p.setType("user");
		p.setUser_name(URLDecoder.decode(name,"UTF-8"));
		p.setWork_year(work_year);
		p.setUser_desc(URLDecoder.decode(desc,"UTF-8"));
		p.setSkilled(URLDecoder.decode(skilled,"UTF-8"));
		p.setUser_no(user_no);
		
		try{
			if("1".equals(fileupd)){
				MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
				MultipartFile files = req.getFile("image"); 
				attr = uploadImg2Oss(files,user_no);
				p.setUser_headimg(attr);
			}	
			userService.updInfo(p);
			// 更新标签
			List<UserTagPo> tagList = new ArrayList<UserTagPo>();
			
			if(!StringUtils.isEmptyString(tags)){
				for(String tag:tags.split(",")){
					UserTagPo t = new UserTagPo();
					t.setValue(tag);
					t.setUser_no(user_no);
					tagList.add(t);
				}
				userService.updUserTag(tagList, user_no);
			}
			
			resultMap.put("status", "1");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("status", "-1");
		}
		
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * 上传图片
	 * 
	 * @param url
	 */
	public String uploadImg2Oss(MultipartFile newfile,String user_no) throws Exception{
		// 解码，然后将字节转换为文件
//        byte[] bytes = Base64.decodeBase64(base64Img);   //将字符串转换为byte数组
//        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
//		
//		// 上传
//		InputStream inputStream = 
		try {
			String fileUrl = ossclientUtils.uploadFile("B端服务人员头像/" + user_no, newfile.getInputStream(), 
					newfile.getOriginalFilename());
			return fileUrl;
		} catch (FileNotFoundException e) {
			throw new Exception("图片上传失败");
		} 
	}
	
	@Autowired
	private XcxOAuthService oAuthService;
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getShare.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getShare(HttpServletRequest request,
			HttpServletResponse response,
			String user_no)
			throws Exception {
		logger.info("getShare");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//查看二维码是否已经存在
		UserPo p = userService.findByUserno(user_no);
		
		if(!StringUtils.isEmptyString(p.getShare_link())){
			resultMap.put("link", p.getShare_link());
		}else{
			InputStream inputStream = oAuthService.getEwm(user_no);
	        String fileUrl = ossclientUtils.uploadFile("小程序二维码/" + user_no,inputStream, 
						"aaa.png");
	        UserPo updP = new UserPo();
	        updP.setType("share");
	        updP.setUser_no(user_no);
	        updP.setShare_link(fileUrl);
	        userService.updInfo(updP);
	        
	        resultMap.put("link", fileUrl);
		}
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
