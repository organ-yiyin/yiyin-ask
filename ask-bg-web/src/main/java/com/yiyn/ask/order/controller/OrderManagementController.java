package com.yiyn.ask.order.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
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
import com.yiyn.ask.base.constants.ConsultStatuEnum;
import com.yiyn.ask.base.constants.LogUserTypeEnum;
import com.yiyn.ask.base.constants.ObjectTypeEnum;
import com.yiyn.ask.base.convert.AttachmentConvert;
import com.yiyn.ask.base.dao.impl.AttachmentDaoImpl;
import com.yiyn.ask.base.form.AttachmentForm;
import com.yiyn.ask.base.po.AttachmentPo;
import com.yiyn.ask.base.utils.DwzResponseForm;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.base.utils.date.SPDateUtils;
import com.yiyn.ask.order.convert.ConsultationSheetConvert;
import com.yiyn.ask.order.form.ConsultationSheetForm;
import com.yiyn.ask.order.form.OrderManagementForm;
import com.yiyn.ask.order.service.OrderManager;
import com.yiyn.ask.sys.dao.impl.UserBDaoImpl;
import com.yiyn.ask.sys.po.UserBPo;
import com.yiyn.ask.wechat.dto.WechatRefundResponseDto;
import com.yiyn.ask.wechat.dto.WechatResultDto;
import com.yiyn.ask.wechat.service.WechatRefundServiceImpl;
import com.yiyn.ask.xcx.center.dao.impl.CodeDaoImpl;
import com.yiyn.ask.xcx.center.po.CodePo;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultLogDaoImpl;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultProcessDaoImpl;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultRefDaoImpl;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultantSheetBgDaoImpl;
import com.yiyn.ask.xcx.consult.po.ConsultLogPo;
import com.yiyn.ask.xcx.consult.po.ConsultPo;
import com.yiyn.ask.xcx.consult.po.ConsultProcessPo;
import com.yiyn.ask.xcx.consult.po.ConsultRefPo;
import com.yiyn.ask.xcx.user.dao.impl.UserCDaoImpl;
import com.yiyn.ask.xcx.user.po.UserCPo;

@Controller
@RequestMapping("/order")
public class OrderManagementController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String FOLDER_PATH = "/yiyn/order/sheet";

	public static final String URL_PATH_PREFIX = "/order";

	@Resource(name = "consultSheetDao_bg")
	private ConsultantSheetBgDaoImpl consultantSheetBgDao;

	@Autowired
	private UserBDaoImpl userBDao;
	
	@Autowired
	private UserCDaoImpl userCDao;

	@Autowired
	private ConsultLogDaoImpl consultLogDao;

	@Autowired
	private AttachmentDaoImpl attachmentDao;
	
	@Autowired
	private WechatRefundServiceImpl wechatRefundService;
	
	@Autowired
	private ConsultRefDaoImpl consultRefDao;
	
	@Autowired
	private ConsultProcessDaoImpl consultProceessDao;
	
	@Autowired
	private CodeDaoImpl codeDao;
	
	@Autowired
	private OrderManager orderManager;

	@RequestMapping(value = "/management.do", method = RequestMethod.GET)
	public ModelAndView forwardManagementPage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("forwardManagementPage");

		OrderManagementForm returnPage = new OrderManagementForm();
		returnPage.setNumPerPage(50);
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/orderManagement.jsp");
		mv.addObject("info", returnPage);

		return mv;
	}

	@RequestMapping(value = "/search.do", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("user_c_phone") String user_c_phone, @RequestParam("odd_num") String odd_num,
			@RequestParam("status") String status, @RequestParam("start_booking_time") String start_booking_time,
			@RequestParam("end_booking_time") String end_booking_time, @RequestParam("pageNum") String pageNum,
			@RequestParam("numPerPage") String numPerPage) throws Exception {
		logger.info("search");

		PaginationUtils paramPage = new PaginationUtils(Integer.parseInt(numPerPage), Integer.parseInt(pageNum));
		paramPage.getParamMap().put("user_c_phone", user_c_phone);
		paramPage.getParamMap().put("odd_num", odd_num);
		paramPage.getParamMap().put("status", status);
		paramPage.getParamMap().put("start_booking_time", start_booking_time);
		paramPage.getParamMap().put("end_booking_time", end_booking_time);

		int totalCount = this.consultantSheetBgDao.searchCountByConditions_bg(paramPage);
		List<Map> pos = this.consultantSheetBgDao.searchByConditions_bg(paramPage);

		OrderManagementForm returnPage = new OrderManagementForm();
		BeanUtils.copyProperties(paramPage, returnPage);
		returnPage.setTotalCount(totalCount);
		returnPage.setData(pos);

		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/orderManagement.jsp");
		mv.addObject("info", returnPage);

		return mv;
	}

	@RequestMapping(value = "/forwardDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") Long id) throws Exception {
		logger.info("forwardDetails");
		
		ConsultPo consultPo = this.consultantSheetBgDao.findById(id);
		ConsultationSheetForm consultantSheet = ConsultationSheetConvert.convertToForm(consultPo);
		List<CodePo> qus_types = codeDao.findCodeList("QUS_TYPE");
		consultantSheet.setQus_types(qus_types);
		
		UserBPo userB = userBDao.findByUserNo(consultPo.getUser_b_no());
		UserCPo userC = userCDao.findByUserno(consultPo.getUser_c_no());
		ConsultRefPo consultRef = consultRefDao.findById(Long.valueOf(consultPo.getUser_ref_id()));		
		List<AttachmentPo> attachments = attachmentDao.findAllByObject(ObjectTypeEnum.ORDER_ATTACHMENT.getCode(), id);
		List<ConsultLogPo> logs = this.consultLogDao.findByConsultId(id);
		List<ConsultProcessPo> consultProcessList = consultProceessDao.getConsultProcessList(id.toString());

		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/orderDetails.jsp");
		mv.addObject("consultantSheet",consultantSheet);
		mv.addObject("consultProcessList", consultProcessList);
		mv.addObject("userB", userB);
		mv.addObject("userC", userC);
		mv.addObject("consultRef", consultRef);
		mv.addObject("attachments", attachments);
		mv.addObject("logs", logs);

		return mv;
	}

	@RequestMapping(value = "/forwardDetails.do", method = RequestMethod.POST)
	public ModelAndView forwardDetails_POST(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") Long id) throws Exception {
		return this.forwardDetails(request, response, id);
	}

	/**
	 * 管理员主动取消订单
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminCancel.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String adminCancel(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Long id)
			throws Exception {

		logger.info("adminCancel,id={}", id);
		ConsultPo consultPo = this.consultantSheetBgDao.findById(id);
		ConsultStatuEnum consultantStatus = ConsultStatuEnum.buildConsulantStatusByCode(consultPo.getStatus());
		if (consultantStatus == null) {
			DwzResponseForm responseForm = DwzResponseForm.createFailResponseForm("当前订单状态为空，无法做后续处理");
			return new Gson().toJson(responseForm);
		}
		if (!ConsultStatuEnum.PAY.getCode().equals(consultPo.getStatus())) {
			DwzResponseForm responseForm = DwzResponseForm
					.createFailResponseForm(String.format("当前订单状态为%s，无法取消订单", consultantStatus.getName()));
			return new Gson().toJson(responseForm);
		}

		 WechatResultDto<WechatRefundResponseDto> refund = wechatRefundService.refund(consultPo.getOdd_num(),
		 NumberUtils.createBigDecimal(consultPo.getPrice()),
		 NumberUtils.createBigDecimal(consultPo.getPrice()));
		 if(refund.isSuccess()) {
			// 修改订单状态
			consultPo.setStatus(ConsultStatuEnum.REFUND.getCode());
			this.consultantSheetBgDao.updateStatusById(consultPo);
			
			// 记录日志
			ConsultLogPo t = new ConsultLogPo();
			t.setLog_type(ConsultStatuEnum.REFUND.getCode());
			t.setLog_desc("管理员取消订单");
			t.setConsult_id(id.toString());
			t.setLog_user_type(LogUserTypeEnum.USER_BG.getCode());
			consultLogDao.insert(t);
				
			DwzResponseForm responseForm = DwzResponseForm.createSuccessResponseForm(
				String.format("订单%s已取消，总共退款金额为%s",consultPo.getOdd_num(),consultPo.getPrice()));
			responseForm.setNavTabId("orderDetails");
		 	return new Gson().toJson(responseForm);
		 }
		 else {
			 DwzResponseForm responseForm =DwzResponseForm.createFailResponseForm(refund.getMessage());
			 return new Gson().toJson(responseForm);
		 }
	}

	/**
	 * 管理员同意取消
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminConfirmCancel.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String adminConfirmCancel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") Long id) throws Exception {

		logger.info("adminConfirmCancel,id={}", id);
		ConsultPo consultPo = this.consultantSheetBgDao.findById(id);
		ConsultStatuEnum consultantStatus = ConsultStatuEnum.buildConsulantStatusByCode(consultPo.getStatus());
		if (consultantStatus == null) {
			DwzResponseForm responseForm = DwzResponseForm.createFailResponseForm("当前订单状态为空，无法做后续处理");
			return new Gson().toJson(responseForm);
		}
		if (!ConsultStatuEnum.REFUND_WAIT.getCode().equals(consultPo.getStatus())) {
			DwzResponseForm responseForm = DwzResponseForm
					.createFailResponseForm(String.format("当前订单状态为%s，无法同意取消订单", consultantStatus.getName()));
			return new Gson().toJson(responseForm);
		}
		
		WechatResultDto<WechatRefundResponseDto> refund = wechatRefundService.refund(consultPo.getOdd_num(),
		NumberUtils.createBigDecimal(consultPo.getPrice()),
		NumberUtils.createBigDecimal(consultPo.getPrice()));
		if(refund.isSuccess()) {
			// 修改订单状态
			consultPo.setStatus(ConsultStatuEnum.REFUND.getCode());
			this.consultantSheetBgDao.updateStatusById(consultPo);
			
			// 记录日志
			ConsultLogPo t = new ConsultLogPo();
			t.setLog_type(ConsultStatuEnum.REFUND.getCode());
			t.setLog_desc("管理员同意取消订单");
			t.setConsult_id(id.toString());
			t.setLog_user_type(LogUserTypeEnum.USER_BG.getCode());
			
			consultLogDao.insert(t);
				
			DwzResponseForm responseForm = DwzResponseForm.createSuccessResponseForm(
					String.format("订单%s已同意取消，总共退款金额为%s", consultPo.getOdd_num(), consultPo.getPrice()));
			responseForm.setNavTabId("orderDetails");
			return new Gson().toJson(responseForm);
		}
		else {
			 DwzResponseForm responseForm =DwzResponseForm.createFailResponseForm(refund.getMessage());
			 return new Gson().toJson(responseForm);
		}
	}

	/**
	 * 管理员驳回取消
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminRejectCancel.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String adminRejectCancel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") Long id) throws Exception {

		logger.info("adminRejectCancel,id={}", id);
		ConsultPo consultPo = this.consultantSheetBgDao.findById(id);
		ConsultStatuEnum consultantStatus = ConsultStatuEnum.buildConsulantStatusByCode(consultPo.getStatus());
		if (consultantStatus == null) {
			DwzResponseForm responseForm = DwzResponseForm.createFailResponseForm("当前订单状态为空，无法做后续处理");
			return new Gson().toJson(responseForm);
		}
		if (!ConsultStatuEnum.REFUND_WAIT.getCode().equals(consultPo.getStatus())) {
			DwzResponseForm responseForm = DwzResponseForm
					.createFailResponseForm(String.format("当前订单状态为%s，无法驳回", consultantStatus.getName()));
			return new Gson().toJson(responseForm);
		}

		// 修改订单状态
		consultPo.setStatus(ConsultStatuEnum.PAY.getCode());
		this.consultantSheetBgDao.updateStatusById(consultPo);
		
		// 记录日志
		ConsultLogPo t = new ConsultLogPo();
		t.setLog_type(ConsultStatuEnum.PAY.getCode());
		t.setLog_desc("管理员驳回取消订单");
		t.setConsult_id(id.toString());
		t.setLog_user_type(LogUserTypeEnum.USER_BG.getCode());
		consultLogDao.insert(t);

		DwzResponseForm responseForm = DwzResponseForm.createSuccessResponseForm("驳回取消订单");
		responseForm.setNavTabId("orderDetails");
		return new Gson().toJson(responseForm);
	}
	
	@RequestMapping(value = "/forwardWechatDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardWechatDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") Long id) throws Exception {
		logger.info("forwardWechatDetails");
		
		ConsultPo consultPo = this.consultantSheetBgDao.findById(id);
		ConsultationSheetForm consultantSheet = ConsultationSheetConvert.convertToForm(consultPo);
		List<ConsultProcessPo> consultProcessList = consultProceessDao.getConsultProcessList(id.toString());

		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/orderWechat.jsp");
		mv.addObject("consultantSheet", consultantSheet);
		mv.addObject("consultProcessList", consultProcessList);
		return mv;
	}
	
	
	@RequestMapping(value = "/downloadOrders.do", method = RequestMethod.GET)
	public void downloadOrders(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("user_c_phone") String user_c_phone, @RequestParam("odd_num") String odd_num,
			@RequestParam("status") String status, @RequestParam("start_booking_time") String start_booking_time,
			@RequestParam("end_booking_time") String end_booking_time) throws Exception {
		
		String fileName = "orders_" + SPDateUtils.formatDateDefault(new Date());
		
		response.setContentType( "application/vnd.ms-excel" );
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
		ServletOutputStream os = response.getOutputStream();
		
		try {
			PaginationUtils paramPage = new PaginationUtils(9999,1);
			paramPage.getParamMap().put("user_c_phone", user_c_phone);
			paramPage.getParamMap().put("odd_num", odd_num);
			paramPage.getParamMap().put("status", status);
			paramPage.getParamMap().put("start_booking_time", start_booking_time);
			paramPage.getParamMap().put("end_booking_time", end_booking_time);
			
			Workbook workbook = this.orderManager.downloadOrdersExcel(paramPage);
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


	@RequestMapping(value = "/attachment/forwardNewDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardAttachmentNewDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("consult_sheet_id") Long consult_sheet_id) throws Exception {
		logger.info("forwardNewDetails");

		AttachmentForm attachmentForm = new AttachmentForm();
		attachmentForm.setObject_type(ObjectTypeEnum.ORDER_ATTACHMENT.getCode());
		attachmentForm.setObject_id(consult_sheet_id);

		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/attachmentDetails.jsp");
		mv.addObject("info", attachmentForm);

		return mv;
	}

	@RequestMapping(value = "/attachment/save.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String save(HttpServletRequest request, HttpServletResponse response, AttachmentForm attachmentForm)
			throws Exception {
		logger.info("save");

		AttachmentPo convertToPo = AttachmentConvert.convertToPo(attachmentForm);
		attachmentDao.insert(convertToPo);

		DwzResponseForm responseForm = DwzResponseForm.createCloseCurrentResponseForm();
		responseForm.setNavTabId("orderDetails");
		return new Gson().toJson(responseForm);
	}

	@RequestMapping(value = "/attachment/delete.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String delete(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Long id)
			throws Exception {
		logger.info("delete");

		AttachmentPo findById = this.attachmentDao.findById(id);
		attachmentDao.deleteById_logic(findById);

		DwzResponseForm responseForm = DwzResponseForm.createForwardResponseForm(request,
				URL_PATH_PREFIX + "/forwardDetails.do?id=" + findById.getObject_id());
		return new Gson().toJson(responseForm);
	}

}
