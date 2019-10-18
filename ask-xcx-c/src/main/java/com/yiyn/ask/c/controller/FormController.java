package com.yiyn.ask.c.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiyn.ask.c.wechat.controller.XcxOAuthService;
import com.yiyn.ask.wechat.dto.WechatXcxDto;
import com.yiyn.ask.xcx.center.po.FormIdPo;
import com.yiyn.ask.xcx.center.service.impl.FormIdService;

@Controller
@RequestMapping("form")
public class FormController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FormIdService formIdService;
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveFormId.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String saveFormId(HttpServletRequest request,
			HttpServletResponse response,String sessionid,String form_id) throws Exception {
		logger.info("saveFormId");
		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);
		
		if(dto != null){
			FormIdPo insP = new FormIdPo();
			insP.setForm_id(form_id);
			insP.setRel_user(dto.getDb_open_id());
			insP.setSource("C");
			insP.setSend_num(1);// 普通form只能发送一次
			formIdService.insert(insP);
		}
		return null;
	}
}
