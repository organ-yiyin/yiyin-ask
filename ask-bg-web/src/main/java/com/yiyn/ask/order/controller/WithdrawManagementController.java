package com.yiyn.ask.order.controller;

import java.math.BigDecimal;
import java.util.Date;
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
import com.yiyn.ask.base.constants.ObjectTypeEnum;
import com.yiyn.ask.base.constants.WithDrawStatusEnum;
import com.yiyn.ask.base.convert.AttachmentConvert;
import com.yiyn.ask.base.dao.impl.AttachmentDaoImpl;
import com.yiyn.ask.base.form.AttachmentForm;
import com.yiyn.ask.base.po.AttachmentPo;
import com.yiyn.ask.base.utils.DwzResponseForm;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.base.utils.date.SPDateUtils;
import com.yiyn.ask.order.form.WithdrawForm;
import com.yiyn.ask.order.form.WithdrawManagementForm;
import com.yiyn.ask.sys.dao.impl.UserBDaoImpl;
import com.yiyn.ask.sys.po.UserBPo;
import com.yiyn.ask.xcx.account.dao.impl.AccountDaoImpl;
import com.yiyn.ask.xcx.account.dao.impl.AccountFlowDaoImpl;
import com.yiyn.ask.xcx.account.dao.impl.UserWithdrawDaoImpl;
import com.yiyn.ask.xcx.account.po.AccountFlowPo;
import com.yiyn.ask.xcx.account.po.AccountPo;
import com.yiyn.ask.xcx.account.po.AccountWithDrawPo;

@Controller
@RequestMapping("/withdraw")
public class WithdrawManagementController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String FOLDER_PATH = "/yiyn/order/withdraw";

	public static final String URL_PATH_PREFIX = "/withdraw";
	
	@Autowired
	private UserWithdrawDaoImpl userWithdrawDao;
	
	@Autowired
	private AccountDaoImpl accountDao;
	
	@Autowired
	private AccountFlowDaoImpl accountFlowDao;
	
	@Resource(name="userBDao_bg")
	private UserBDaoImpl userBDao;
	
	@Autowired
	private AttachmentDaoImpl attachmentDao;
	
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
	
	@RequestMapping(value = "/forwardDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") Long id) throws Exception {
		logger.info("forwardDetails");
		
		AccountWithDrawPo withdrawPo = this.userWithdrawDao.findById(id);
		AccountPo accountPo = accountDao.findById(Long.valueOf(withdrawPo.getAccount_id()));
		UserBPo userBPo = this.userBDao.findById(accountPo.getUser_b_id());
		List<AttachmentPo> attachments = attachmentDao.findAllByObject(ObjectTypeEnum.WITHDRAW_ATTACHMENT.getCode(), id);
		
		WithdrawForm withdrawForm = new WithdrawForm();
		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/withdrawDetails.jsp");
		mv.addObject("info", withdrawForm);
		mv.addObject("withdrawPo", withdrawPo);
		mv.addObject("userBPo", userBPo);
		mv.addObject("attachments", attachments);

		return mv;
	}
	
	@RequestMapping(value = "/forwardDetails.do", method = RequestMethod.POST)
	public ModelAndView forwardDetails_POST(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") Long id) throws Exception {
		return this.forwardDetails(request, response, id);
	}
	
	@RequestMapping(value = "/adminConfirmWithdraw.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String adminConfirmWithdraw(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") Long id) throws Exception {

		logger.info("adminConfirmWithdraw,id={}", id);
		AccountWithDrawPo withdrawPo = this.userWithdrawDao.findById(id);
		WithDrawStatusEnum withdrawStatusEnum = WithDrawStatusEnum.findByCode(Integer.parseInt(withdrawPo.getStatus()));

		if (withdrawStatusEnum == null) {
			DwzResponseForm responseForm = DwzResponseForm.createFailResponseForm("当前提现状态为空，无法做后续处理");
			return new Gson().toJson(responseForm);
		}
		if (!WithDrawStatusEnum.WAITING_APPROVE.getCode().equals(withdrawStatusEnum.getCode())) {
			DwzResponseForm responseForm = DwzResponseForm
					.createFailResponseForm(String.format("当前提现状态为%s，无法审核通过", withdrawStatusEnum.getName()));
			return new Gson().toJson(responseForm);
		}
		
		// 修改状态
		withdrawPo.setStatus(WithDrawStatusEnum.APPROVED.getCode().toString());
		userWithdrawDao.updateStatusById(withdrawPo);
		
		// 修改账户表数据
		// 并对user_account更新余额以及真实的提现金额

		DwzResponseForm responseForm = DwzResponseForm.createSuccessResponseForm("提现申请已审核通过，请进行后续打款操作");
		responseForm.setNavTabId("withdrawDetails");
		return new Gson().toJson(responseForm);
	}
	
	@RequestMapping(value = "/adminTransfer.do", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String adminTransfer(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") Long id) throws Exception {

		logger.info("adminTransfer,id={}", id);
		AccountWithDrawPo withdrawPo = this.userWithdrawDao.findById(id);
		WithDrawStatusEnum withdrawStatusEnum = WithDrawStatusEnum.findByCode(Integer.parseInt(withdrawPo.getStatus()));
		AccountPo accountPo = accountDao.findById(Long.valueOf(withdrawPo.getAccount_id()));

		if (withdrawStatusEnum == null) {
			DwzResponseForm responseForm = DwzResponseForm.createFailResponseForm("当前提现状态为空，无法做后续处理");
			return new Gson().toJson(responseForm);
		}
		if (!WithDrawStatusEnum.APPROVED.getCode().equals(withdrawStatusEnum.getCode())) {
			DwzResponseForm responseForm = DwzResponseForm
					.createFailResponseForm(String.format("当前提现状态为%s，无法打款", withdrawStatusEnum.getName()));
			return new Gson().toJson(responseForm);
		}
		
		// 修改状态
		withdrawPo.setStatus(WithDrawStatusEnum.PAID.getCode().toString());
		userWithdrawDao.updateStatusById(withdrawPo);
		
		// 修改账户表数据
		// 并对user_account更新余额以及真实的提现金额
		accountPo.setBalance(accountPo.getBalance().subtract(withdrawPo.getWithdraw()));
		accountPo.setWithdraw(accountPo.getWithdraw().add(withdrawPo.getWithdraw()));
		accountDao.updateByIdAftetTransfer(accountPo);
		
		// 插入日志
		AccountFlowPo flowP = new AccountFlowPo();
		flowP.setAccount_id(accountPo.getId());
		flowP.setJournal_money(withdrawPo.getWithdraw().doubleValue());
		flowP.setJournal_dir("2");// 1：流入，2：流出
		flowP.setJournal_type("2");// 1：用户支付，2：提现：3：退款
		//flowP.setOrder_id());
		flowP.setPay_type("转账");
		flowP.setPay_time(SPDateUtils.formatDateTimeDefault(new Date()));
		flowP.setJournal_remark("用户提现");
		flowP.setPay_status("1");//1:成功；2：待处理
		flowP.setPay_channel_no("WXXCX");
		accountFlowDao.insert(flowP);

		DwzResponseForm responseForm = DwzResponseForm.createSuccessResponseForm("打款操作成功");
		responseForm.setNavTabId("withdrawDetails");
		return new Gson().toJson(responseForm);
	}
	
	@RequestMapping(value = "/attachment/forwardNewDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardAttachmentNewDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("withdraw_id") Long withdraw_id) throws Exception {
		logger.info("forwardNewDetails");

		AttachmentForm attachmentForm = new AttachmentForm();
		attachmentForm.setObject_type(ObjectTypeEnum.WITHDRAW_ATTACHMENT.getCode());
		attachmentForm.setObject_id(withdraw_id);

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
		responseForm.setNavTabId("withdrawDetails");
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
