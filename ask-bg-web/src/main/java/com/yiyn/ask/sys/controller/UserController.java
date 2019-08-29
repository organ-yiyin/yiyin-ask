package com.yiyn.ask.sys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yiyn.ask.base.security.SpingSecurityUserBo;
import com.yiyn.ask.base.utils.DwzResponseForm;
import com.yiyn.ask.base.utils.DwzResponseForm.StatusCode;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.sys.convert.UserConvert;
import com.yiyn.ask.sys.dao.impl.UserDaoImpl;
import com.yiyn.ask.sys.form.UserForm;
import com.yiyn.ask.sys.form.UserManagementForm;
import com.yiyn.ask.sys.po.UserPo;

@Controller
@RequestMapping("/sys/user")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final String FOLDER_PATH = "/yiyn/sys";
	
	public static final String URL_PATH_PREFIX = "/sys/user";
	
	@Resource(name="userDao")
	private UserDaoImpl userDao;
	
	
	
	@RequestMapping(value = "/management.do", method = RequestMethod.GET)
	public ModelAndView forwardUserManagementPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("forwardUserManagementPage");
		
		UserManagementForm returnPage = new UserManagementForm();
		ModelAndView mv = new ModelAndView( FOLDER_PATH + "/userManagement.jsp");
		mv.addObject("info", returnPage);
		
		return mv;
	}
	
	@RequestMapping(value = "/search.do", method = RequestMethod.POST)
	public ModelAndView searchUserList(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam("user_no") String user_no,
			@RequestParam("user_name") String user_name,
			@RequestParam("enabled") String enabled,
			@RequestParam("pageNum") String pageNum,
			@RequestParam("numPerPage") String numPerPage) throws Exception {
		logger.info("searchUserList");

		PaginationUtils paramPage = new PaginationUtils(
				Integer.parseInt(numPerPage), Integer.parseInt(pageNum));
		paramPage.getParamMap().put("user_no", user_no);
		paramPage.getParamMap().put("user_name", user_name);
		paramPage.getParamMap().put("enabled", enabled);
		
		int totalCount = this.userDao.searchCountByConditions(paramPage);
		List<UserPo> pos =  this.userDao.searchByConditions(paramPage);
		
		UserManagementForm returnPage = new UserManagementForm();
		BeanUtils.copyProperties(paramPage, returnPage);
		returnPage.setTotalCount(totalCount);
		returnPage.setData(UserConvert.listConvertToForm(pos));
		
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/userManagement.jsp");
		mv.addObject("info", returnPage);

		return mv;
	}
	
	@RequestMapping(value = "/forwardNewDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardNewUserDetailsPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("forwardNewUserDetailsPage");
		
		UserForm userForm = new UserForm();
		
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/userDetails.jsp");
		mv.addObject("info", userForm);
		
		return mv;
	}
	
	@RequestMapping(value = "/forwardUpdateDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardUpdateDetails(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("id") Long id) throws Exception {
		
		logger.info("forwardUpdateDetails");
		UserPo userPo = this.userDao.findById(id);
		UserForm userForm = UserConvert.convertToForm(userPo);
		
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/userUpdateDetails.jsp");
		mv.addObject("info", userForm);
		
		return mv;
	}
	
	@RequestMapping(value = "/save.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String save(HttpServletRequest request,
			HttpServletResponse response, UserForm userForm) throws Exception {
		logger.info("saveUser");
		
		UserPo convertToPo = UserConvert.convertToPo(userForm);
		convertToPo.setUser_password(DigestUtils.md5Hex(userForm.getOriginal_password()));
		Long insertId = this.userDao.save(convertToPo);

		DwzResponseForm responseForm = DwzResponseForm.createCloseCurrentResponseForm();
		return new Gson().toJson(responseForm);
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String update(HttpServletRequest request,
			HttpServletResponse response, UserForm userForm) throws Exception {
		logger.info("saveUser");
		
		UserPo dbUserPo = this.userDao.findById(userForm.getId());
		dbUserPo.setUser_name(userForm.getUser_name());
		dbUserPo.setEnabled(userForm.getEnabled());

		Long insertId = this.userDao.save(dbUserPo);

		DwzResponseForm responseForm = DwzResponseForm.createCloseCurrentResponseForm();
		return new Gson().toJson(responseForm);
	}
	
	@RequestMapping(value = "/forwardResetPass.do", method = RequestMethod.GET)
	public ModelAndView forwardResetPass(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("id") Long id) throws Exception {
		
		logger.info("forwardResetPass");
		
		UserPo userPo = this.userDao.findById(id);
		UserForm userForm = UserConvert.convertToForm(userPo);
		
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/userResetPass.jsp");
		mv.addObject("info", userForm);
		
		return mv;
	}
	
	@RequestMapping(value = "/resetPassword.do", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public String resetPassword(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("id") Long id,
			@RequestParam("original_password") String original_password) throws Exception {
		logger.info("resetPassword");
		
		UserPo userPo = this.userDao.findById(id);
		userPo.setUser_password(DigestUtils.md5Hex(original_password));
		this.userDao.updatePasswordById(userPo);
		
		return new Gson().toJson(DwzResponseForm.createCloseCurrentResponseForm());
	}
	
	@RequestMapping(value = "/forwardPassword.do", method = RequestMethod.GET)
	public ModelAndView forwardPassword(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("searchUserList");

		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/updatePassword.jsp");
		SpingSecurityUserBo userDetails = (SpingSecurityUserBo) SecurityContextHolder.getContext()  
			    .getAuthentication()  
			    .getPrincipal();
		mv.addObject("id",userDetails.getPo().getId());

		return mv;
	}
	
	@RequestMapping(value = "/updatePassword.do", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public String updatePassword(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("password") String password,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("newPassword_confirm") String newPassword_confirm) throws Exception {
		logger.info("searchUserList");

		SpingSecurityUserBo userDetails = (SpingSecurityUserBo) SecurityContextHolder.getContext()  
			    .getAuthentication()  
			    .getPrincipal();
		
		UserPo userPo = this.userDao.findById(userDetails.getPo().getId());
		if(!userPo.getUser_password().equals(DigestUtils.md5Hex(password))){
			DwzResponseForm repForm = new DwzResponseForm();
			repForm.setStatusCode(StatusCode.FAIL.getValue());
			repForm.setMessage("原密码不正确");
			
			return new Gson().toJson(repForm);
		}
		else{
			userPo.setUser_password(DigestUtils.md5Hex(newPassword));
			this.userDao.updatePasswordById(userPo);
			return new Gson().toJson(DwzResponseForm.createCloseCurrentResponseForm());
		}
	}
	
}
