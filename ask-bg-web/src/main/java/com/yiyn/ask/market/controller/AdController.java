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
import com.yiyn.ask.market.convert.AdConvert;
import com.yiyn.ask.market.dao.impl.AdDaoImpl;
import com.yiyn.ask.market.form.AdForm;
import com.yiyn.ask.market.form.AdManagementForm;
import com.yiyn.ask.market.po.AdPo;

@Controller
@RequestMapping("/market/ad")
public class AdController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final String FOLDER_PATH = "/yiyn/market/ad";
	
	public static final String URL_PATH_PREFIX = "/market/ad";
	
	@Autowired
	private AdDaoImpl adDao;
	
	@RequestMapping(value = "/management.do", method = RequestMethod.GET)
	public ModelAndView management(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("management");
		
		AdManagementForm returnPage = new AdManagementForm();
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/adManagement.jsp");
		mv.addObject("info", returnPage);
		
		return mv;
	}
	
	@RequestMapping(value = "/search.do", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam("ad_position") String ad_position,
			@RequestParam("ad_title") String ad_title,
			@RequestParam("delete_flag") String delete_flag,
			@RequestParam("pageNum") String pageNum,
			@RequestParam("numPerPage") String numPerPage) throws Exception {
		logger.info("searchUserList");

		PaginationUtils paramPage = new PaginationUtils(
				Integer.parseInt(numPerPage), Integer.parseInt(pageNum));
		paramPage.getParamMap().put("ad_position", ad_position);
		paramPage.getParamMap().put("ad_title", ad_title);
		paramPage.getParamMap().put("delete_flag", delete_flag);
		
		int totalCount = this.adDao.searchCountByConditions(paramPage);
		List<AdPo> pos =  this.adDao.searchByConditions(paramPage);
		
		AdManagementForm returnPage = new AdManagementForm();
		BeanUtils.copyProperties(paramPage, returnPage);
		returnPage.setTotalCount(totalCount);
		returnPage.setData(AdConvert.listConvertToForm(pos));
		
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/adManagement.jsp");
		mv.addObject("info", returnPage);

		return mv;
	}
	
	@RequestMapping(value = "/forwardNewDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardNewDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("forwardNewDetails");
		
		AdForm adForm = new AdForm();
		
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/adDetails.jsp");
		mv.addObject("info", adForm);
		
		return mv;
	}
	
	@RequestMapping(value = "/forwardUpdateDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardUpdateDetails(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("id") Long id) throws Exception {
		
		logger.info("forwardUpdateDetails");
		AdPo adPo = this.adDao.findById(id);
		AdForm adForm = AdConvert.convertToForm(adPo);
		
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/adDetails.jsp");
		mv.addObject("info", adForm);
		
		return mv;
	}
	
	@RequestMapping(value = "/save.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String save(HttpServletRequest request,
			HttpServletResponse response, AdForm adForm) throws Exception {
		logger.info("saveAd");
		
		AdPo convertToPo = AdConvert.convertToPo(adForm);
		Long insertId = this.adDao.save(convertToPo);

		DwzResponseForm responseForm = DwzResponseForm.createCloseCurrentResponseForm();
		return new Gson().toJson(responseForm);
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String update(HttpServletRequest request,
			HttpServletResponse response, AdForm adForm) throws Exception {
		logger.info("updateAd");
		
		AdPo convertToPo = AdConvert.convertToPo(adForm);
		Long insertId = this.adDao.save(convertToPo);
		
		DwzResponseForm responseForm = DwzResponseForm.createCloseCurrentResponseForm();
		return new Gson().toJson(responseForm);
	}
	
}
