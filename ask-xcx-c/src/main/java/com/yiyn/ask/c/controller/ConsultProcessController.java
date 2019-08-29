package com.yiyn.ask.c.controller;

import java.io.FileNotFoundException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.yiyn.ask.base.utils.OSSClientUtils;
import com.yiyn.ask.base.utils.StringUtils;
import com.yiyn.ask.c.wechat.controller.XcxOAuthService;
import com.yiyn.ask.wechat.dto.WechatXcxDto;
import com.yiyn.ask.xcx.center.service.impl.CodeService;
import com.yiyn.ask.xcx.consult.service.impl.ConsultService;
import com.yiyn.ask.xcx.user.po.UserColPo;
import com.yiyn.ask.xcx.user.po.UserPo;
import com.yiyn.ask.xcx.user.service.impl.UserService;

@Controller
@RequestMapping("/consultProcess")
public class ConsultProcessController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ConsultService consultService;
	
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private OSSClientUtils ossclientUtils;
	/**
	 * 获取咨询师列表--根据名称，排序，咨询类型区分
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserBList.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getUserBList(HttpServletRequest request,
			HttpServletResponse response, String name,String advice_type,String pxtype)
			throws Exception {
		logger.info("getUserBList");
		// 新建成功返回
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		Map<String,Object> param = new HashMap<String,Object>();
		if(!StringUtils.isEmptyString(name)){
			param.put("name",name);
		}
		
		if(!StringUtils.isEmptyString(advice_type)){
			param.put("advice_type",advice_type);
		}
		
		param.put("pxtype",pxtype);
		
		//查找咨询师列表
		resultMap.put("reList", userService.getConsultList(param));
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * 获取咨询师列表--根据名称，排序，咨询类型区分
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserInfo.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getUserInfo(HttpServletRequest request,
			HttpServletResponse response, String user_no,String sessionid)
			throws Exception {
		logger.info("getUserInfo");
		// 新建成功返回
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("user_no",user_no);
		param.put("userc_no",dto.getDb_open_id());
		
		UserPo userP = userService.findByUserno(user_no);
		// 查找咨询师列表
		resultMap.put("info", userP);
		
		// 评价
		resultMap.put("evalList",userService.findUserEval(user_no));
		
		//具备技能列表
		if(!StringUtils.isEmptyString(userP.getSkilled())){
			resultMap.put("skillList", userP.getSkilled().split(";"));
		}
		
		//获取用户是否关注
		UserColPo p = new UserColPo();
		p.setUser_no_b(user_no);
		p.setUser_no_c(dto.getDb_open_id());
		resultMap.put("attention", userService.sfRel(p));
		
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * 点击关注和取消关注时更新数据库信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveAttenTion.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String saveAttenTion(HttpServletRequest request,
			HttpServletResponse response, String user_no,String sessionid,boolean type)
			throws Exception {
		logger.info("saveAttenTion");
		// 新建成功返回
		Map<String,Object> resultMap = new HashMap<String,Object>();
		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);
		
		//获取用户是否关注
		UserColPo p = new UserColPo();
		p.setUser_no_b(user_no);
		p.setUser_no_c(dto.getDb_open_id());
		
		//当前为关注，则点击取消关注
		if(type){
			userService.insUserCol(p);
		//当前无关注，增加关注记录
		}else{
			userService.delUserCol(p);
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
	@RequestMapping(value = "/initQusetionDetail.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String initQusetionDetail(HttpServletRequest request,
			HttpServletResponse response, String sessionid)
			throws Exception {
		logger.info("initQusetionDetail");
		// 新建成功返回
		Map<String,Object> resultMap = new HashMap<String,Object>();
		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("no", dto.getDb_open_id());
		resultMap.put("reList", userService.getRefList(param));
		resultMap.put("queTypeList", codeService.findCodeList("QUS_TYPE"));
		return new Gson().toJson(resultMap);
	}
	

	/**
	 * 支付前最后确认信息是否正确
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSheetInfo.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getSheetInfo(HttpServletRequest request,
			HttpServletResponse response, String user_no,String refid)
			throws Exception {
		logger.info("getSheetInfo");
		// 新建成功返回
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		resultMap.put("userInfo", userService.findUserInfo(user_no));
		resultMap.put("refInfo", userService.getRefDetail(refid));
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * 上传图片和视频
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upFile.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String upFile(HttpServletRequest request,
			HttpServletResponse response, String sessionid)
			throws Exception {
		logger.info("upFile");
		// 新建成功返回
		MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
		MultipartFile files = req.getFile("file"); 
		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);
		String attr = uploadImg2Oss(files,dto.getDb_open_id());
		return attr;
	}
	
	
	/**
	 * 上传图片
	 * 
	 * @param url
	 */
	public String uploadImg2Oss(MultipartFile newfile,String user_no) throws Exception{
		try {
			String fileUrl = ossclientUtils.uploadFile("C端创建咨询单/" + user_no, newfile.getInputStream(), 
					newfile.getOriginalFilename());
			return fileUrl;
		} catch (FileNotFoundException e) {
			throw new Exception("上传失败");
		} 
	}
	
	/**
	 * oss上文件删除
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delTempFile.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String delTempFile(HttpServletRequest request,
			HttpServletResponse response, String files)
			throws Exception {
		logger.info("delTempFile");
		// 新建成功返回
		//Map<String,Object> resultMap = new HashMap<String,Object>();
		if(!StringUtils.isEmptyString(files)){
			String[] temfiles = files.split(",");
			
			for(String tempfile:temfiles){
				ossclientUtils.deleteFile(tempfile);
			}
		}
		return null;
	}
}
