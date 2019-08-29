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
import com.yiyn.ask.base.constants.ConsultStatuEnum;
import com.yiyn.ask.base.constants.ProcessContentTypeEnum;
import com.yiyn.ask.base.constants.ProcessSendTypeEnum;
import com.yiyn.ask.base.utils.StringUtils;
import com.yiyn.ask.c.wechat.controller.XcxOAuthService;
import com.yiyn.ask.wechat.dto.WechatXcxDto;
import com.yiyn.ask.xcx.consult.po.ConsultLogPo;
import com.yiyn.ask.xcx.consult.po.ConsultPo;
import com.yiyn.ask.xcx.consult.po.ConsultProcessPo;
import com.yiyn.ask.xcx.consult.service.impl.ConsultService;
import com.yiyn.ask.xcx.user.po.UserEvalPo;
import com.yiyn.ask.xcx.user.service.impl.UserService;

@Controller
@RequestMapping("/consultSheet")
public class ConsultSheetController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ConsultService consultService;
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getConsultList.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getConsultList(HttpServletRequest request,
			HttpServletResponse response, String sessionid,String type)
			throws Exception {
		logger.info("getConsultList");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//获取微信小程序信息
		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("no", dto.getDb_open_id());
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
			HttpServletResponse response, String id)
			throws Exception {
		logger.info("getConsultInfo");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("info", consultService.getConsultInfoByC(id));
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
			HttpServletResponse response, String id,String status,String sessionid,String desc)
			throws Exception {
		logger.info("updConsult");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//获取微信小程序信息
		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);
		String statu = "";
		if("refund".equals(status)){
			statu = ConsultStatuEnum.REFUND_WAIT.getCode();
		}
		try{
			ConsultPo updP = new ConsultPo();
			updP.setId(new Long(id));//咨询单id
			updP.setStatus(statu); //咨询单状态
			updP.setUpdated_by(dto.getDb_open_id());// 咨询单操作人
			// 先更新咨询单状态
			consultService.updConsult(updP);
			
			// 插入咨询单操作日志
			ConsultLogPo logP = new ConsultLogPo();
			logP.setLog_type(statu);
			logP.setLog_desc(desc);
			logP.setConsult_id(id);
			logP.setCreated_by(dto.getDb_open_id());
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
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getConsultDetail.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getConsultDetail(HttpServletRequest request,
			HttpServletResponse response, String id)
			throws Exception {
		logger.info("getConsultDetail");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("info", consultService.getConsultInfo(id));
		resultMap.put("reList", consultService.getConsultProcessList(id));
		
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insEval.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String insEval(HttpServletRequest request,
			HttpServletResponse response, String id,String eval_desc,int eval_stars)
			throws Exception {
		logger.info("insEval");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{
			UserEvalPo insP = new UserEvalPo();
			insP.setConsultation_id(id);
			insP.setStars(eval_stars);
			insP.setEva_desc(eval_desc);
			consultService.insEval(insP);
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
	@RequestMapping(value = "/insPorocess.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String insPorocess(HttpServletRequest request,
			HttpServletResponse response, String id,String text,String files,String videos)
			throws Exception {
		logger.info("insPorocess");
		// 新建成功返回
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			if(!StringUtils.isEmptyString(text)){
				ConsultProcessPo insP = new ConsultProcessPo();
		    	insP.setConsultation_id(String.valueOf(id));
		    	insP.setContent(text);
		    	insP.setContent_type(ProcessContentTypeEnum.TEXT.getName());
		    	insP.setSend_type(ProcessSendTypeEnum.CUSTOMER.getName());
		    	consultService.insConsultProcess(insP);
			}
			if(!StringUtils.isEmptyString(files)){
				String[] insfiles = files.split(",");
				for(String insfile:insfiles){
					ConsultProcessPo insP = new ConsultProcessPo();
			    	insP.setConsultation_id(String.valueOf(id));
			    	insP.setContent(insfile);
			    	insP.setContent_type(ProcessContentTypeEnum.IMG.getName());
			    	insP.setSend_type(ProcessSendTypeEnum.CUSTOMER.getName());
			    	consultService.insConsultProcess(insP);
				}
			}
			if(!StringUtils.isEmptyString(videos)){
				String[] insfiles = videos.split(",");
				for(String insfile:insfiles){
					ConsultProcessPo insP = new ConsultProcessPo();
			    	insP.setConsultation_id(String.valueOf(id));
			    	insP.setContent(insfile);
			    	insP.setContent_type(ProcessContentTypeEnum.vedio.getName());
			    	insP.setSend_type(ProcessSendTypeEnum.CUSTOMER.getName());
			    	consultService.insConsultProcess(insP);
				}
			}
			
			// 增加追问次数和修改状态为已支付并插入操作日志表
			ConsultPo updP = new ConsultPo();
			updP.setStatus(ConsultStatuEnum.PAY.getCode());
			updP.setId(new Long(id));
			updP.setUpdated_by("add_ques_num");
			consultService.updConsultQuesNum(updP);
			resultMap.put("status", "1");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("status", "-1");
		}
		
		return new Gson().toJson(resultMap);
	}
}
