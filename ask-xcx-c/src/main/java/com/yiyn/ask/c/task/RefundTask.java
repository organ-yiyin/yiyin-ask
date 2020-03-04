package com.yiyn.ask.c.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yiyn.ask.base.constants.ConsultStatuEnum;
import com.yiyn.ask.base.constants.LogUserTypeEnum;
import com.yiyn.ask.base.utils.DateUtils;
import com.yiyn.ask.base.utils.StringUtils;
import com.yiyn.ask.base.utils.date.SPDateUtils;
import com.yiyn.ask.wechat.dto.WechatRefundResponseDto;
import com.yiyn.ask.wechat.dto.WechatResultDto;
import com.yiyn.ask.wechat.service.WechatRefundServiceImpl;
import com.yiyn.ask.xcx.account.po.AccountFlowPo;
import com.yiyn.ask.xcx.account.po.AccountPo;
import com.yiyn.ask.xcx.account.service.impl.AccountService;
import com.yiyn.ask.xcx.consult.po.ConsultLogPo;
import com.yiyn.ask.xcx.consult.po.ConsultPo;
import com.yiyn.ask.xcx.consult.service.impl.ConsultService;

@Component
public class RefundTask {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private ConsultService consultService;
	
	@Autowired
	private WechatRefundServiceImpl wechatRefundService;
	
	@Autowired
	private AccountService accountService;
	
	@Scheduled(cron = "0 0 0/1 * * ? ") // 每小时整点执行一次
	/**
	 * 付款后24小时内用户无回答问题，更新订单状态为自动退款，并且退款给用户
	 */
    public void taskRefund() throws Exception {
		logger.info("超时退款定时任务执行，时间为：" + DateUtils.formatFullPattern(new Date()));
        // 查找需要自动退款的订单 规则为：超出24小时并且咨询师没有回答过的订单可以自动退款
		List<ConsultPo> pList = consultService.getRefundConsult();
		// 调用接口发起自动退款
		if(pList != null){
			for(ConsultPo p:pList){
				 logger.info("需要退款的订单id分别为：" + p.getId() + "金额为：" + p.getPrice());
				 String odd_num = p.getOdd_num();
				 String num = p.getPrice();
				 String user_m = p.getUser_pay_money();
				 int discount = p.getDiscount();
				 WechatResultDto<WechatRefundResponseDto> re = wechatRefundService.refund(odd_num, new BigDecimal(user_m), new BigDecimal(user_m));
				 
				 // 调用成功则更新状态
				 if(re.isSuccess()){
					// 更新订单状态为超时退款
					 String user_b_no = p.getUser_b_no();
					 logger.info("退款已成功，订单id为：" + p.getId());
					 ConsultPo updP = new ConsultPo();
					 updP.setId(p.getId());
					 updP.setStatus(ConsultStatuEnum.EXPIRED_REFUND.getCode());
					 updP.setUpdated_by("超时退款C端后台处理更新");
					 updP.setRefund_time(new Date());
					 consultService.updConsult(updP);
					 
					// 修改账户表数据
					// 并对user_account更新余额
					AccountPo accountPo = accountService.getAccountInfo(user_b_no);
					accountPo.setBalance(accountPo.getBalance().subtract(NumberUtils.createBigDecimal(num)));
					accountService.updateByIdAfterCancel(accountPo);
					
					// 记录日志
					ConsultLogPo t = new ConsultLogPo();
					t.setLog_type(ConsultStatuEnum.EXPIRED_REFUND.getCode());
					t.setLog_desc("24小时超时自动退款");
					t.setConsult_id(String.valueOf(p.getId()));
					t.setLog_user_type(LogUserTypeEnum.USER_BG.getCode());
					consultService.insConsultLog(t);
					
					AccountFlowPo flowP = new AccountFlowPo();
					flowP.setAccount_id(accountPo.getId());
					flowP.setJournal_money(new Double(num));
					flowP.setJournal_dir("2");// 1：流入，2：流出
					flowP.setJournal_type("3");// 1：用户支付，2：提现：3：退款
					flowP.setOrder_id(odd_num);
					flowP.setPay_type("WXPAY");
					flowP.setPay_time(SPDateUtils.formatDateTimeDefault(new Date()));
					flowP.setJournal_remark("24小时超时自动退款");
					flowP.setPay_status("1");//1:成功；2：待处理
					flowP.setPay_channel_no("WXXCX");
					accountService.insAccountFlow(flowP);
					
					// 更新平台总账户，如果有使用优惠券，则把金额加回来
					// 如果有优惠券信息，更新总账户金额，并且更新流水表
				    if(discount > 0 && !StringUtils.isEmptyString(p.getCoupon_relid())){
					  AccountPo account_admin = accountService.getAccountInfoByUserNo("super_admin");
					  AccountFlowPo total_p = new AccountFlowPo();
					  total_p.setAccount_id(account_admin.getId());
					  total_p.setJournal_money(new Double(discount));
					  total_p.setJournal_dir("1");// 1：流入，2：流出
					  total_p.setJournal_type("5");// 1：用户支付，2：提现：3：退款   4:优惠券金额从平台账户中扣除 5:退款后平台总账户要加回来
					  total_p.setOrder_id(odd_num);
					  total_p.setPay_type("WXPAY");
					  total_p.setPay_time(SPDateUtils.formatDateTimeDefault(new Date()));
					  total_p.setJournal_remark("微信小程序支付退款插入，总账户收入");
					  total_p.setPay_status("1");//1:成功；2：待处理
					  total_p.setPay_channel_no("WXXCX");
					  accountService.insAccountFlow(total_p);
					  
					  // 更新B端用户账户余额--按照支付金额+优惠金额入账
					  account_admin.setBalance(account_admin.getBalance().subtract(new BigDecimal(discount)));
					  accountService.updateByIdAfterCancel(account_admin);
				  }
				}
			 }
			
		}
    }
}
