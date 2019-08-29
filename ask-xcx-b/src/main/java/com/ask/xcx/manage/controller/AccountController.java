package com.ask.xcx.manage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.yiyn.ask.base.constants.WithDrawStatusEnum;
import com.yiyn.ask.base.constants.WithDrawTypeEnum;
import com.yiyn.ask.base.utils.DateUtils;
import com.yiyn.ask.xcx.account.po.AccountWithDrawPo;
import com.yiyn.ask.xcx.account.service.impl.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountService accountService;

	/**
	 * 初始化账号页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/initAccount.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String initAccount(HttpServletRequest request,
			HttpServletResponse response,String user_no)
			throws Exception {
		logger.info("initAccount");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//获取账户余额以及累计收益和上月收益以及是否有可提现的金额
		resultMap.put("info", accountService.getAccountInfo(user_no));
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * 初始化账号页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchStatAccount.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String searchStatAccount(HttpServletRequest request,
			HttpServletResponse response,
			String user_no,String year)
			throws Exception {
		logger.info("searchStatAccount");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user_no", user_no);
		param.put("year", year);
		
		resultMap.put("reList", accountService.getAcountStatM(param));
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * 查找详细流失
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAccountFlow.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getAccountFlow(HttpServletRequest request,
			HttpServletResponse response,
			String user_no,String month)
			throws Exception {
		logger.info("getAccountFlow");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user_no", user_no);
		param.put("month", month);
		
		resultMap.put("reList", accountService.findAccountFlowList(param));
		return new Gson().toJson(resultMap);
	}
	
	/**
	 * 提现
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insWithDraw.x", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String insWithDraw(HttpServletRequest request,
			HttpServletResponse response,
			String account_id,double withdraw,double balance,String user_no)
			throws Exception {
		logger.info("insWithDraw");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String today = DateUtils.format(new Date(),"dd");
		boolean sfktx = false;
		int d = Integer.parseInt(today.substring(1,2));
		if("0".equals(today.substring(0,1)) &&  d >=1 && d <= 5){
			sfktx = true;
		}
		// 提现金额大于余额
		if(withdraw > balance){
			resultMap.put("status", "2");
		// 提现金额不足
		}else if(withdraw == 0.00){
			resultMap.put("status", "3");
		// 提现日期不对
		}else if(!sfktx){
			resultMap.put("status", "4");
		}else{
			try{
				AccountWithDrawPo p = new AccountWithDrawPo();
				p.setAccount_id(account_id);
				p.setWithdraw(withdraw);
				p.setWithdraw_act(withdraw * 0.7);
				p.setService_charge(withdraw * 0.3);
				p.setWithdraw_type(WithDrawTypeEnum.OFFLINE.getCode());
				p.setStatus(WithDrawStatusEnum.REVIEW_WAIT.getCode());
				p.setCreated_by(user_no);
				accountService.insetAccountWithDraw(p);
				resultMap.put("status", "1");
			}catch(Exception e){
				e.printStackTrace();
				resultMap.put("status", "-1");
			}
		}
		
		
		return new Gson().toJson(resultMap);
	}
}
