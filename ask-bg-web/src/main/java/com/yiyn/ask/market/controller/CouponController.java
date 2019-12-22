package com.yiyn.ask.market.controller;

import java.util.List;

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
import com.yiyn.ask.market.convert.CouponConvert;
import com.yiyn.ask.market.dao.impl.CouponDaoImpl;
import com.yiyn.ask.market.form.CouponForm;
import com.yiyn.ask.market.form.CouponManagementForm;
import com.yiyn.ask.market.po.CouponPo;

@Controller
@RequestMapping("/market/coupon")
public class CouponController {
	
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final String FOLDER_PATH = "/yiyn/market/coupon";
	
	public static final String URL_PATH_PREFIX = "/market/coupon";
	
	@Autowired
	private CouponDaoImpl couponDao;
	
	@RequestMapping(value = "/management.do", method = RequestMethod.GET)
	public ModelAndView management(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("management");
		
		CouponManagementForm returnPage = new CouponManagementForm();
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/couponManagement.jsp");
		mv.addObject("info", returnPage);
		
		return mv;
	}
	
	@RequestMapping(value = "/search.do", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam("name") String name,
			@RequestParam("coupon_type") String coupon_type,
			@RequestParam("coupon_range") String coupon_range,
			@RequestParam("coupon_status") String coupon_status,
			@RequestParam("pageNum") String pageNum,
			@RequestParam("numPerPage") String numPerPage) throws Exception {
		logger.info("searchUserList");

		PaginationUtils paramPage = new PaginationUtils(
				Integer.parseInt(numPerPage), Integer.parseInt(pageNum));
		paramPage.getParamMap().put("name", name);
		paramPage.getParamMap().put("coupon_type", coupon_type);
		paramPage.getParamMap().put("coupon_range", coupon_range);
		paramPage.getParamMap().put("coupon_status", coupon_status);
		
		int totalCount = this.couponDao.searchCountByConditions(paramPage);
		List<CouponPo> pos =  this.couponDao.searchByConditions(paramPage);
		
		CouponManagementForm returnPage = new CouponManagementForm();
		BeanUtils.copyProperties(paramPage, returnPage);
		returnPage.setTotalCount(totalCount);
		returnPage.setData(CouponConvert.listConvertToForm(pos));
		
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/couponManagement.jsp");
		mv.addObject("info", returnPage);

		return mv;
	}
	
	@RequestMapping(value = "/forwardNewDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardNewDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("forwardNewDetails");
		
		CouponForm adForm = new CouponForm();
		
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/couponDetails.jsp");
		mv.addObject("info", adForm);
		
		return mv;
	}
	
	@RequestMapping(value = "/forwardUpdateDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardUpdateDetails(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("id") Long id) throws Exception {
		
		logger.info("forwardUpdateDetails");
		CouponPo adPo = this.couponDao.findById(id);
		CouponForm adForm = CouponConvert.convertToForm(adPo);
		
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/couponDetails.jsp");
		mv.addObject("info", adForm);
		
		return mv;
	}
	
	@RequestMapping(value = "/save.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String save(HttpServletRequest request,
			HttpServletResponse response, CouponForm adForm) throws Exception {
		logger.info("saveAd");
		
		CouponPo convertToPo = CouponConvert.convertToPo(adForm);
		Long insertId = this.couponDao.save(convertToPo);

		DwzResponseForm responseForm = DwzResponseForm.createCloseCurrentResponseForm();
		return new Gson().toJson(responseForm);
	}

}
