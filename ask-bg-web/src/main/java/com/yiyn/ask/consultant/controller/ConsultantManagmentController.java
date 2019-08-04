package com.yiyn.ask.consultant.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yiyn.ask.base.constants.UserTypeEnum;
import com.yiyn.ask.base.utils.DwzResponseForm;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.consultant.form.ConsultantManagementForm;
import com.yiyn.ask.sys.convert.UserBConvert;
import com.yiyn.ask.sys.dao.impl.UserBDaoImpl;
import com.yiyn.ask.sys.form.UserBForm;
import com.yiyn.ask.sys.po.UserBPo;

@Controller
@RequestMapping("/consultant")
public class ConsultantManagmentController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String FOLDER_PATH = "/yiyn/consultant";

	public static final String URL_PATH_PREFIX = "/consultant";
	
	@Autowired
	private UserBDaoImpl userBDao;
	
	@RequestMapping(value = "/management.do", method = RequestMethod.GET)
	public ModelAndView forwardManagementPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("forwardManagementPage");
		
		ConsultantManagementForm returnPage = new ConsultantManagementForm();
		ModelAndView mv = new ModelAndView( FOLDER_PATH + "/consultantManagement.jsp");
		mv.addObject("info", returnPage);
		
		return mv;
	}
	
	@RequestMapping(value = "/search.do", method = RequestMethod.POST)
	public ModelAndView searchUserList(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam("user_no") String user_no,
			@RequestParam("user_name") String user_name,
			@RequestParam("pageNum") String pageNum,
			@RequestParam("numPerPage") String numPerPage) throws Exception {
		logger.info("searchUserList");

		PaginationUtils paramPage = new PaginationUtils(
				Integer.parseInt(numPerPage), Integer.parseInt(pageNum));
		paramPage.getParamMap().put("user_no", user_no);
		paramPage.getParamMap().put("user_name", user_name);
		paramPage.getParamMap().put("user_type", UserTypeEnum.SERVER.getCode());
		
		int totalCount = this.userBDao.searchCountByConditions(paramPage);
		List<UserBPo> pos =  this.userBDao.searchByConditions(paramPage);
		
		PaginationUtils page = new PaginationUtils();
		page.setTotalCount(totalCount);
		page.setData(UserBConvert.listConvertToForm(pos));
		
		ConsultantManagementForm returnUserPage = new ConsultantManagementForm();
		returnUserPage.setTotalCount(totalCount);
		returnUserPage.setData(UserBConvert.listConvertToForm(pos));
		
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/consultantManagement.jsp");
		mv.addObject("info", returnUserPage);

		return mv;
	}
	
	@RequestMapping(value = "/forwardUpdateDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardUpdateDetails(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("user_id") Long user_id) throws Exception {
		logger.info("forwardUpdateDetails");
		
		UserBPo userBPo = this.userBDao.findById(user_id);
		UserBForm userForm = UserBConvert.convertToForm(userBPo);
		
		ModelAndView mv = new ModelAndView( FOLDER_PATH + "/consultantDetails.jsp");
		mv.addObject("info", userForm);
		
		return mv;
	}
	
	@RequestMapping(value = "/save.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String save(HttpServletRequest request,
			HttpServletResponse response, UserBForm userForm) throws Exception {
		logger.info("save");
		
		UserBPo convertToPo = UserBConvert.convertToPo(userForm);
		this.userBDao.updateByIdInBg(convertToPo);
		
		DwzResponseForm responseForm = DwzResponseForm.createCloseCurrentResponseForm();
		return new Gson().toJson(responseForm);
	}

}
