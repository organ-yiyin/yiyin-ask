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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yiyn.ask.base.constants.ObjectTypeEnum;
import com.yiyn.ask.base.dao.impl.AttachmentDaoImpl;
import com.yiyn.ask.base.po.AttachmentPo;
import com.yiyn.ask.base.utils.DwzResponseForm;
import com.yiyn.ask.base.utils.OSSClientUtils;
import com.yiyn.ask.consultant.convert.ConsultantAttachmentConvert;
import com.yiyn.ask.consultant.form.ConsultantAttachmentForm;
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
	private OSSClientUtils ossclientUtils;
	
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
		
		ConsultantAttachmentForm attachmentForm = new ConsultantAttachmentForm();
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
			HttpServletResponse response, ConsultantAttachmentForm attachmentForm) throws Exception {
		logger.info("save");
		
		AttachmentPo convertToPo = ConsultantAttachmentConvert.convertToPo(attachmentForm);
		attachmentDao.insert(convertToPo);
		
		DwzResponseForm responseForm = DwzResponseForm.createCloseCurrentResponseForm();
		responseForm.setNavTabId("attachmentManagement");
		return new Gson().toJson(responseForm);
	}
	
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	@ResponseBody
	public String uploadAttachment(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("head_image_file") MultipartFile head_image_file) throws Exception {
		
		logger.info("uploadAttachment");
		
		if(head_image_file.getInputStream().available() > 1000 * 2000){
			DwzResponseForm rf = DwzResponseForm.createFailResponseForm("文件大小不能超过2000K");
			return new Gson().toJson(rf);
		}
		
		String fileUrl = ossclientUtils.uploadFile("B端服务人员头像", head_image_file.getInputStream(), 
				head_image_file.getOriginalFilename());
		
		DwzResponseForm rf = DwzResponseForm.createSuccessResponseForm();
		rf.setData(fileUrl);
		
		return new Gson().toJson(rf); //ResponseForm.createSuccessResponseForm();
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("id") Long id) throws Exception {
		logger.info("delete");
		
		AttachmentPo findById = this.attachmentDao.findById(id);
		attachmentDao.deleteById_logic(findById);
		
		return this.forwardManagementPage(request, response, findById.getObject_id());
	}
}
