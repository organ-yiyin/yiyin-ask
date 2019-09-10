package com.yiyn.ask.customer.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
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
import com.yiyn.ask.base.utils.date.SPDateUtils;
import com.yiyn.ask.customer.form.CustomerManagementForm;
import com.yiyn.ask.customer.service.CustomerManager;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultRefDaoImpl;
import com.yiyn.ask.xcx.consult.po.ConsultRefPo;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String FOLDER_PATH = "/yiyn/customer";

	public static final String URL_PATH_PREFIX = "/customer";
	
	@Autowired
	private ConsultRefDaoImpl consultRefDao;
	
	@Autowired
	private CustomerManager customerManager;
	
	@RequestMapping(value = "/management.do", method = RequestMethod.GET)
	public ModelAndView forwardManagement(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("forwardManagementPage");
		
		CustomerManagementForm returnPage = new CustomerManagementForm();
		ModelAndView mv = new ModelAndView( FOLDER_PATH + "/customerManagement.jsp");
		mv.addObject("info", returnPage);
		
		return mv;
	}
	
	@RequestMapping(value = "/search.do", method = RequestMethod.POST)
	public ModelAndView searchUserList(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam("usre_c_phone") String usre_c_phone,
			@RequestParam("name_m") String name_m,
			@RequestParam("name_b") String name_b,
			@RequestParam("pageNum") String pageNum,
			@RequestParam("numPerPage") String numPerPage) throws Exception {
		logger.info("searchUserList");

		PaginationUtils paramPage = new PaginationUtils(
				Integer.parseInt(numPerPage), Integer.parseInt(pageNum));
		paramPage.getParamMap().put("usre_c_phone", usre_c_phone);
		paramPage.getParamMap().put("name_m", name_m);
		paramPage.getParamMap().put("name_b", name_b);
		
		int totalCount = this.consultRefDao.searchCountByConditions_bg(paramPage);
		List<Map> consultRefPos = this.consultRefDao.searchByConditions_bg(paramPage);
		
		CustomerManagementForm returnPage = new CustomerManagementForm();
		BeanUtils.copyProperties(paramPage, returnPage);
		returnPage.setTotalCount(totalCount);
		returnPage.setData(consultRefPos);
		
		ModelAndView mv = new ModelAndView( FOLDER_PATH + "/customerManagement.jsp");
		mv.addObject("info", returnPage);

		return mv;
	}
	
	@RequestMapping(value = "/forwardDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") Long id) throws Exception {
		logger.info("forwardDetails");

		ConsultRefPo consultRef = consultRefDao.findById(id);

		ModelAndView mv = new ModelAndView( FOLDER_PATH + "/customerDetails.jsp");
		mv.addObject("info", consultRef);

		return mv;
	}
	
	@RequestMapping(value = "/downloadCustomers.do", method = RequestMethod.GET)
	public void downloadConsults(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("usre_c_phone") String usre_c_phone,
			@RequestParam("name_m") String name_m,
			@RequestParam("name_b") String name_b) throws Exception {
		
		String fileName = "customer_" + SPDateUtils.formatDateDefault(new Date());
		
		response.setContentType( "application/vnd.ms-excel" );
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
		ServletOutputStream os = response.getOutputStream();
		
		try {
			PaginationUtils paramPage = new PaginationUtils(9999,1);
			paramPage.getParamMap().put("usre_c_phone", usre_c_phone);
			paramPage.getParamMap().put("name_m", name_m);
			paramPage.getParamMap().put("name_b", name_b);

			Workbook workbook = this.customerManager.downloadCustomers(paramPage);
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
