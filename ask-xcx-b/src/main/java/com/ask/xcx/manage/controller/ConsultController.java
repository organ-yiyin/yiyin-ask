package com.ask.xcx.manage.controller;

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

import com.ask.xcx.manage.wechat.controller.XcxOAuthService;
import com.google.gson.Gson;
import com.yiyn.ask.base.constants.ConsultStatuEnum;
import com.yiyn.ask.base.constants.ProcessContentTypeEnum;
import com.yiyn.ask.base.utils.OSSClientUtils;
import com.yiyn.ask.base.utils.SMSUtils;
import com.yiyn.ask.xcx.center.po.FormIdPo;
import com.yiyn.ask.xcx.center.service.impl.CenterResponseService;
import com.yiyn.ask.xcx.center.service.impl.FormIdService;
import com.yiyn.ask.xcx.consult.po.ConsultLogPo;
import com.yiyn.ask.xcx.consult.po.ConsultPo;
import com.yiyn.ask.xcx.consult.po.ConsultProcessPo;
import com.yiyn.ask.xcx.consult.service.impl.ConsultService;

@Controller
@RequestMapping("/consult")
public class ConsultController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ConsultService consultService;
	
	@Autowired
	private CenterResponseService centerResponseService;
	
	@Autowired
	private OSSClientUtils ossclientUtils;
	
	@Autowired
	private XcxOAuthService oAuthService;
	
	@Autowired
	private SMSUtils smsUtils;
	
	@Autowired
	private FormIdService formIdService;
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getConsultList.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getConsultList(HttpServletRequest request,
			HttpServletResponse response, String no,String type)
			throws Exception {
		logger.info("getConsultList");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("no", no);
		param.put("type", type);
		resultMap = consultService.getConsultList(param);
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getConsultInfo.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getConsultInfo(HttpServletRequest request,
			HttpServletResponse response, String id,String userno)
			throws Exception {
		logger.info("getConsultInfo");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("info", consultService.getConsultInfo(id));
		resultMap.put("reList", consultService.getConsultProcessList(id));
		
		// 回复模版
		resultMap.put("responseList", centerResponseService.findResponseList(userno));
		
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insConsultProcess.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String insConsultProcess(HttpServletRequest request,
			HttpServletResponse response, String id,String content)
			throws Exception {
		logger.info("insConsultProcess");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();

		ConsultProcessPo insP = new ConsultProcessPo();
		insP.setConsultation_id(id);
		insP.setContent(content);
		insP.setContent1("");
		insP.setContent_type("text");
		insP.setSend_type("server");
		
		try{
			consultService.insConsultProcess(insP);
			resultMap.put("status", "1");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("status", "-1");
		}
		
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * 消息撤回
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updPorocess.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String updPorocess(HttpServletRequest request,
			HttpServletResponse response, String id)
			throws Exception {
		logger.info("updPorocess");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			ConsultProcessPo insP = new ConsultProcessPo();
	    	insP.setId(new Long(id));
	    	insP.setUpdated_by("用户撤回");
	    	insP.setDelete_flag("Y");
	    	consultService.updConsultProcess(insP);
	    	resultMap.put("status", "1");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("status", "-1");
		}
		
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * 服务人员退单
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updConsult.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String updConsult(HttpServletRequest request,
			HttpServletResponse response, String id,String status,String user_no,String desc)
			throws Exception {
		logger.info("updConsult");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String statu = "";
		if("refund".equals(status)){
			statu = "3";
		}
		try{
			ConsultPo updP = new ConsultPo();
			updP.setId(new Long(id));//咨询单id
			updP.setStatus(statu); //咨询单状态
			updP.setUpdated_by(user_no);// 咨询单操作人
			// 先更新咨询单状态
			consultService.updConsult(updP);
			
			// 插入咨询单操作日志
			ConsultLogPo logP = new ConsultLogPo();
			logP.setLog_type(statu);
			logP.setLog_desc(desc);
			logP.setConsult_id(id);
			logP.setLog_user_type("B");
			logP.setCreated_by(user_no);
			consultService.insConsultLog(logP);
			
			resultMap.put("status", "1");
			resultMap.put("desc", "您的退款申请已提交！");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("status", "-1");
			resultMap.put("desc", "退款暂时无法申请，请联系工作人员！");
		}
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * 聊天插入文件和内容
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/savePorcess.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String savePorcess(HttpServletRequest request,
			HttpServletResponse response, String id,String content,String user_no,String status,String fileupd)
			throws Exception {
		logger.info("savePorcess");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ConsultProcessPo insP = new ConsultProcessPo();
		insP.setConsultation_id(id);
		insP.setContent_type(fileupd);
		insP.setCreated_by(user_no);
		insP.setSend_type("server");
		insP.setContent1("");
		// 文件上传
		String attr = "";
		if(ProcessContentTypeEnum.IMG.getName().equals(fileupd) || ProcessContentTypeEnum.VIDEO.getName().equals(fileupd) 
				|| ProcessContentTypeEnum.AUDIO.getName().equals(fileupd)){
			MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
			MultipartFile files = req.getFile("file"); 
			attr = uploadImg2Oss(files,user_no);
			insP.setContent(attr);
			//音频的话还要插入音频的时长
			if(ProcessContentTypeEnum.AUDIO.getName().equals(fileupd)){
				insP.setContent1(content);
			}
		}else{
			insP.setContent(content);
		}
		
		// 根据咨询单状态查找咨询单，如果咨询单状态为申请退款或者已退款的情况下，则不改变此状态
		boolean sfupd = true;
		if("3".equals(status) || "4".equals(status)){
			sfupd = false;
		}
		ConsultPo updP = new ConsultPo();
		updP.setId(new Long(id));
		updP.setStatus(ConsultStatuEnum.ANS.getCode());
		updP.setUpdated_by(user_no);
		try{
			Long reid = consultService.insConsultProcess(insP);
			if(sfupd){
				consultService.updConsultStatus(updP);
			}
			resultMap.put("status", "1");
			resultMap.put("reid", reid);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("status", "-1");
		}
		
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * 保存完后，如果没发过通知的发通知
	 * 
	 * @param url
	 */
	@RequestMapping(value = "/sendMsg.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String sendMsg(String open_id,String user_phone,String user_b_name) throws Exception{
		// 先发送短信
		smsUtils.sendNotice("【嘉问养育】您好，咨询师已经回复您消息了，请登陆嘉问养育小程序查看", user_phone);
		
//		// 获取到的form_id用来保存发送用
//		// 后发通知
//		Map<String,String> param = new HashMap<String,String>();
//		FormIdPo p = formIdService.getFormId(open_id);
//		if(p != null){
//			// 真是的form_id从用户取得
//			param.put("open_id", open_id);
//			param.put("form_id", p.getForm_id());
//			param.put("hfr", user_b_name);
//			param.put("url", "pages/order/order");
//			boolean f = oAuthService.sendMsg(param);
//			
//			// 发送成功更新次数
//			if(f){
//				formIdService.updateById(p);
//			}else{
//				formIdService.delForm(p);
//			}
//		}
		return null;
	}
				
	/**
	 * 上传图片
	 * 
	 * @param url
	 */
	public String uploadImg2Oss(MultipartFile newfile,String user_no) throws Exception{
		try {
			String fileUrl = ossclientUtils.uploadFile("咨询窗口/" + user_no, newfile.getInputStream(), 
					newfile.getOriginalFilename());
			return fileUrl;
		} catch (FileNotFoundException e) {
			throw new Exception("上传失败");
		} 
	}
}
