package com.yiyn.ask.consultant.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yiyn.ask.base.constants.UserTypeEnum;
import com.yiyn.ask.base.utils.DwzResponseForm;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.base.utils.date.SPDateUtils;
import com.yiyn.ask.consultant.form.ConsultantManagementForm;
import com.yiyn.ask.consultant.service.ConsultantManager;
import com.yiyn.ask.sys.convert.UserBConvert;
import com.yiyn.ask.sys.dao.impl.UserBDaoImpl;
import com.yiyn.ask.sys.form.UserBForm;
import com.yiyn.ask.sys.po.UserBPo;
import com.yiyn.ask.xcx.account.dao.impl.AccountDaoImpl;
import com.yiyn.ask.xcx.account.po.AccountPo;
import com.yiyn.ask.xcx.user.dao.impl.UserTagDaoImpl;
import com.yiyn.ask.xcx.user.po.UserTagPo;

@Controller
@RequestMapping("/consultant")
public class ConsultantManagmentController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String FOLDER_PATH = "/yiyn/consultant";

	public static final String URL_PATH_PREFIX = "/consultant";
	
	@Resource(name="userBDao_bg")
	private UserBDaoImpl userBDao;
	
	@Autowired
	private AccountDaoImpl accountDao;
	
	@Autowired
	  private UserTagDaoImpl userTagDao;
	
	@Autowired
	private ConsultantManager consultantManager;
	
	@RequestMapping(value = "/management.do", method = RequestMethod.GET)
	public ModelAndView forwardManagement(HttpServletRequest request,
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
			@RequestParam("advice_type") Integer advice_type,
			@RequestParam("pageNum") String pageNum,
			@RequestParam("numPerPage") String numPerPage) throws Exception {
		logger.info("searchUserList");

		PaginationUtils paramPage = new PaginationUtils(
				Integer.parseInt(numPerPage), Integer.parseInt(pageNum));
		paramPage.getParamMap().put("user_no", user_no);
		paramPage.getParamMap().put("user_name", user_name);
		paramPage.getParamMap().put("advice_type", advice_type);
		
		int totalCount = this.userBDao.searchCountByConditions(paramPage);
		List<UserBPo> pos =  this.userBDao.searchByConditions(paramPage);
		
		ConsultantManagementForm returnPage = new ConsultantManagementForm();
		BeanUtils.copyProperties(paramPage, returnPage);
		returnPage.setTotalCount(totalCount);
		returnPage.setData(UserBConvert.listConvertToForm(pos));
		
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/consultantManagement.jsp");
		mv.addObject("info", returnPage);

		return mv;
	}
	
	@RequestMapping(value = "/forwardNewDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardNewDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("forwardNewDetails");
		
		UserBForm userForm = new UserBForm();
		ModelAndView mv = new ModelAndView( FOLDER_PATH + "/consultantDetails.jsp");
		mv.addObject("info", userForm);
		
		return mv;
	}
	
	@RequestMapping(value = "/forwardUpdateDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardUpdateDetails(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("id") Long id) throws Exception {
		logger.info("forwardUpdateDetails");
		
		UserBPo userBPo = this.userBDao.findById(id);
		UserBForm userForm = UserBConvert.convertToForm(userBPo);
		List<UserTagPo> tagList = userTagDao.getUserTagList(userBPo.getUser_no());
		
		ModelAndView mv = new ModelAndView( FOLDER_PATH + "/consultantUpdateDetails.jsp");
		mv.addObject("info", userForm);
		mv.addObject("tagList", tagList);
		
		return mv;
	}
	
	@RequestMapping(value = "/save.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String save(HttpServletRequest request,
			HttpServletResponse response, UserBForm userForm) throws Exception {
		logger.info("save");
		
		UserBPo convertToPo = UserBConvert.convertToPo(userForm);
		convertToPo.setUser_password(DigestUtils.md5Hex(userForm.getOriginal_password()));
		Long insertId = this.userBDao.save(convertToPo);
		
		AccountPo accountPo = new AccountPo();
		accountPo.setUser_b_id(insertId);
		accountPo.setUser_no(convertToPo.getUser_no());
		accountPo.setBalance(BigDecimal.ZERO);
		accountPo.setWithdraw(BigDecimal.ZERO);
		accountPo.setUser_type(UserTypeEnum.SERVER.getCode());
		accountPo.setUser_name(convertToPo.getUser_name());
		accountDao.insert(accountPo);
		
		DwzResponseForm responseForm = DwzResponseForm.createCloseCurrentResponseForm();
		return new Gson().toJson(responseForm);
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String update(HttpServletRequest request,
			HttpServletResponse response, UserBForm userForm) throws Exception {
		logger.info("update");
		
		UserBPo convertToPo = UserBConvert.convertToPo(userForm);
		this.userBDao.updateByIdInBg(convertToPo);
		
		DwzResponseForm responseForm = DwzResponseForm.createCloseCurrentResponseForm();
		return new Gson().toJson(responseForm);
	}

	@RequestMapping(value = "/forwardResetPass.do", method = RequestMethod.GET)
	public ModelAndView forwardResetPass(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("id") Long id) throws Exception {
		
		logger.info("forwardResetPass");
		
		UserBPo userPo = this.userBDao.findById(id);
		UserBForm userForm = UserBConvert.convertToForm(userPo);
		
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/consultantResetPass.jsp");
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
		
		UserBPo userPo = this.userBDao.findById(id);
		userPo.setUser_password(DigestUtils.md5Hex(original_password));
		this.userBDao.updatePasswordById(userPo);
		
		return new Gson().toJson(DwzResponseForm.createCloseCurrentResponseForm());
	}
	
	@RequestMapping(value = "/downloadConsults.do", method = RequestMethod.GET)
	public void downloadConsults(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("user_no") String user_no,
			@RequestParam("user_name") String user_name,
			@RequestParam("advice_type") Integer advice_type) throws Exception {
		
		String fileName = "user_b_" + SPDateUtils.formatDateDefault(new Date());
		
		response.setContentType( "application/vnd.ms-excel" );
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
		ServletOutputStream os = response.getOutputStream();
		
		try {
			PaginationUtils paramPage = new PaginationUtils(9999,1);
			paramPage.getParamMap().put("user_no", user_no);
			paramPage.getParamMap().put("user_name", user_name);
			paramPage.getParamMap().put("advice_type", advice_type);

			Workbook workbook = this.consultantManager.downloadWithdrawExcel(paramPage);
			workbook.write( os );
			
			os.flush();
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
		finally {
			os.close();
		}
	}
}
