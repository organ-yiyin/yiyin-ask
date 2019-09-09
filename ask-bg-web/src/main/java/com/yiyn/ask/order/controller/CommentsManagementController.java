package com.yiyn.ask.order.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.yiyn.ask.base.utils.DwzResponseForm;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.order.convert.UserEvalConvert;
import com.yiyn.ask.order.form.CommentsManagementForm;
import com.yiyn.ask.order.form.UserEvalForm;
import com.yiyn.ask.sys.dao.impl.UserBDaoImpl;
import com.yiyn.ask.sys.po.UserBPo;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultantSheetBgDaoImpl;
import com.yiyn.ask.xcx.consult.po.ConsultPo;
import com.yiyn.ask.xcx.user.dao.impl.UserCDaoImpl;
import com.yiyn.ask.xcx.user.dao.impl.UserEvalDaoImpl;
import com.yiyn.ask.xcx.user.po.UserCPo;
import com.yiyn.ask.xcx.user.po.UserEvalPo;

@Controller
@RequestMapping("/comments")
public class CommentsManagementController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String FOLDER_PATH = "/yiyn/order/comments";

	public static final String URL_PATH_PREFIX = "/comments";
	
	@Autowired
	private UserEvalDaoImpl userEvalDao;
	
	@Resource(name = "consultSheetDao_bg")
	private ConsultantSheetBgDaoImpl consultantSheetBgDao;

	@Autowired
	private UserBDaoImpl userBDao;
	
	@Autowired
	private UserCDaoImpl userCDao;
	
	@RequestMapping(value = "/management.do", method = RequestMethod.GET)
	public ModelAndView forwardManagementPage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("forwardManagementPage");

		CommentsManagementForm returnPage = new CommentsManagementForm();
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/commentsManagement.jsp");
		mv.addObject("info", returnPage);
		
		return mv;
	}
	
	@RequestMapping(value = "/search.do", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("odd_num") String odd_num, 
			@RequestParam("user_c_phone") String user_c_phone,
			@RequestParam("user_b_name") String user_b_name, 
			@RequestParam("user_b_phone") String user_b_phone,
			@RequestParam("start_star") Integer start_star,
			@RequestParam("end_star") Integer end_star, 
			@RequestParam("pageNum") String pageNum,
			@RequestParam("numPerPage") String numPerPage) throws Exception {
		logger.info("search");

		PaginationUtils paramPage = new PaginationUtils(Integer.parseInt(numPerPage), Integer.parseInt(pageNum));
		paramPage.getParamMap().put("odd_num", odd_num);
		paramPage.getParamMap().put("user_c_phone", user_c_phone);
		paramPage.getParamMap().put("user_b_name", user_b_name);
		paramPage.getParamMap().put("user_b_phone", user_b_phone);
		paramPage.getParamMap().put("start_star", start_star);
		paramPage.getParamMap().put("end_star", end_star);

		int totalCount = this.userEvalDao.searchCountByConditions_bg(paramPage);
		List<Map> pos = this.userEvalDao.searchByConditions_bg(paramPage);

		CommentsManagementForm returnPage = new CommentsManagementForm();
		BeanUtils.copyProperties(paramPage, returnPage);
		returnPage.setTotalCount(totalCount);
		returnPage.setData(pos);

		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/commentsManagement.jsp");
		mv.addObject("info", returnPage);

		return mv;
	}
	
	@RequestMapping(value = "/forwardDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") Long id) throws Exception {
		logger.info("forwardDetails");

		UserEvalPo userEval = userEvalDao.findById(id);
		UserBPo userB = this.userBDao.findByUserNo(userEval.getUser_b_no());
		UserCPo userC = this.userCDao.findByUserno(userEval.getUser_c_no());
		ConsultPo consultantSheet = this.consultantSheetBgDao.findById(Long.valueOf(userEval.getConsultation_id()));
		
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/commentsDetails.jsp");
		mv.addObject("info", UserEvalConvert.convertToForm(userEval));
		mv.addObject("userEval", userEval);
		mv.addObject("userB", userB);
		mv.addObject("userC", userC);
		mv.addObject("consultantSheet", consultantSheet);

		return mv;
	}
	
	@RequestMapping(value = "/save.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String save(HttpServletRequest request,
			HttpServletResponse response, UserEvalForm userForm) throws Exception {
		logger.info("save");
		
		UserEvalPo convertToPo = UserEvalConvert.convertToPo(userForm);
		this.userEvalDao.updateByAdmin_bg(convertToPo);

		DwzResponseForm responseForm = DwzResponseForm.createCloseCurrentResponseForm();
		return new Gson().toJson(responseForm);
	}
	
}
