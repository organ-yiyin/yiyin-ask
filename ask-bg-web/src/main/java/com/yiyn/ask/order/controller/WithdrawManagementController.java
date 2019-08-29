package com.yiyn.ask.order.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.order.form.WithdrawManagementForm;
import com.yiyn.ask.xcx.account.dao.impl.UserWithdrawDaoImpl;

@Controller
@RequestMapping("/withdraw")
public class WithdrawManagementController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String FOLDER_PATH = "/yiyn/order";

	public static final String URL_PATH_PREFIX = "/withdraw";
	
	@Autowired
	private UserWithdrawDaoImpl userWithdrawDao;
	
	@RequestMapping(value = "/management.do", method = RequestMethod.GET)
	public ModelAndView forwardManagementPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("forwardManagementPage");
		
		WithdrawManagementForm returnPage = new WithdrawManagementForm();
		ModelAndView mv = new ModelAndView( FOLDER_PATH + "/withdrawManagement.jsp");
		mv.addObject("info", returnPage);
		
		return mv;
	}
	
	@RequestMapping(value = "/search.do", method = RequestMethod.POST)
	public ModelAndView searchUserList(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam("user_no") String user_no,
			@RequestParam("withdraw_type") String withdraw_type,
			@RequestParam("status") String status,
			@RequestParam("pageNum") String pageNum,
			@RequestParam("numPerPage") String numPerPage) throws Exception {
		logger.info("search");

		PaginationUtils paramPage = new PaginationUtils(
				Integer.parseInt(numPerPage), Integer.parseInt(pageNum));
		paramPage.getParamMap().put("user_no", user_no);
		paramPage.getParamMap().put("withdraw_type", withdraw_type);
		paramPage.getParamMap().put("status", status);
		
		int totalCount = this.userWithdrawDao.searchCountByConditions_bg(paramPage);
		List<Map> pos =  this.userWithdrawDao.searchByConditions_bg(paramPage);
		
		WithdrawManagementForm returnPage = new WithdrawManagementForm();
		BeanUtils.copyProperties(paramPage, returnPage);
		returnPage.setTotalCount(totalCount);
		returnPage.setData(pos);
		
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/withdrawManagement.jsp");
		mv.addObject("info", returnPage);

		return mv;
	}

}
