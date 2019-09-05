package com.yiyn.ask.consultant.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yiyn.ask.base.constants.ObjectTypeEnum;
import com.yiyn.ask.base.convert.AttachmentConvert;
import com.yiyn.ask.base.dao.impl.AttachmentDaoImpl;
import com.yiyn.ask.base.form.AttachmentForm;
import com.yiyn.ask.base.po.AttachmentPo;
import com.yiyn.ask.base.utils.DwzResponseForm;
import com.yiyn.ask.consultant.form.ConsultantAttachmentManagementForm;
import com.yiyn.ask.sys.convert.UserBConvert;
import com.yiyn.ask.sys.dao.impl.UserBDaoImpl;
import com.yiyn.ask.sys.form.UserBForm;
import com.yiyn.ask.sys.po.UserBPo;

@Controller
@RequestMapping("/consultant/attachment")
public class ConsultantAttachmentManagmentController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String FOLDER_PATH = "/yiyn/consultant/attachment";

	public static final String URL_PATH_PREFIX = "/consultant/attachment";
	
	public static final String oss_url = "";
	
	@Resource(name="userBDao_bg")
	private UserBDaoImpl userBDao;
	
	@Autowired
	private AttachmentDaoImpl attachmentDao;

	@RequestMapping(value = "/management.do", method = RequestMethod.POST)
	public ModelAndView forwardManagementPage_POSE(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("id") Long id) throws Exception {
		return this.forwardManagementPage(request, response, id);
	}
	
	@RequestMapping(value = "/management.do", method = RequestMethod.GET)
	public ModelAndView forwardManagementPage(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("id") Long id) throws Exception {
		logger.info("forwardManagementPage");
		
		UserBPo userBPo = this.userBDao.findById(id);
		UserBForm userForm = UserBConvert.convertToForm(userBPo);
		
		List<AttachmentPo> attachments = this.attachmentDao.findAllByObject(ObjectTypeEnum.CONSULTANT_ATTACHMENT.getCode(), id);
		ConsultantAttachmentManagementForm returnPage = new ConsultantAttachmentManagementForm();
		returnPage.setData(attachments);
		
		ModelAndView mv = new ModelAndView( FOLDER_PATH + "/attachmentManagement.jsp");
		mv.addObject("info", returnPage);
		mv.addObject("userForm", userForm);

		return mv;
	}
	
	@RequestMapping(value = "/forwardNewDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardNewDetails(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("object_id") Long id) throws Exception {
		logger.info("forwardNewDetails");
		
		AttachmentForm attachmentForm = new AttachmentForm();
		attachmentForm.setObject_type(ObjectTypeEnum.CONSULTANT_ATTACHMENT.getCode());
		attachmentForm.setObject_id(id);
		
		ModelAndView mv = new ModelAndView( FOLDER_PATH + "/attachmentDetails.jsp");
		mv.addObject("info", attachmentForm);
		
		return mv;
	}
	
	@RequestMapping(value = "/save.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String save(HttpServletRequest request,
			HttpServletResponse response, AttachmentForm attachmentForm) throws Exception {
		logger.info("save");
		
		AttachmentPo convertToPo = AttachmentConvert.convertToPo(attachmentForm);
		attachmentDao.insert(convertToPo);
		
		DwzResponseForm responseForm = DwzResponseForm.createCloseCurrentResponseForm();
		responseForm.setNavTabId("attachmentManagement");
		return new Gson().toJson(responseForm);
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String delete(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("id") Long id) throws Exception {
		logger.info("delete");
		
		AttachmentPo findById = this.attachmentDao.findById(id);
		attachmentDao.deleteById_logic(findById);
		
		DwzResponseForm responseForm = DwzResponseForm.createForwardResponseForm(request,URL_PATH_PREFIX + "/management.do?id=" + findById.getObject_id());
		return new Gson().toJson(responseForm);
	}
}
