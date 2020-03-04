package com.yiyn.ask.c.task;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yiyn.ask.base.utils.DateUtils;
import com.yiyn.ask.base.utils.SMSUtils;
import com.yiyn.ask.base.utils.StringUtils;
import com.yiyn.ask.xcx.consult.po.ConsultPo;
import com.yiyn.ask.xcx.consult.po.ConsultSheetSmsPo;
import com.yiyn.ask.xcx.consult.service.impl.ConsultService;

@Component
public class ConsultNotifyTask {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private ConsultService consultService;
	
	@Autowired
	private SMSUtils smsUtils;
	
	@Scheduled(cron = "0 0/10 * * * ? ") // 每10分钟执行一次
	/**
	 * 每10分钟执行一次，订单在咨询师第一次回复后，如果12小时候用户追问次数没有用完，自动短信和微信消息提醒用户追问。
	 */
    public void consultNotify() throws Exception {
		logger.info("12小时候用户追问定时任务执行，时间为：" + DateUtils.formatFullPattern(new Date()));
        // 查找需要自动退款的订单 规则为：超出24小时并且咨询师没有回答过的订单可以自动退款
		
		List<ConsultPo> pList = consultService.getConsultNotifyList();
		
		if(pList != null && pList.size() != 0){
			for(ConsultPo p:pList){
				if(!StringUtils.isEmptyString(p.getUserCPo().getUser_phone())){
        			smsUtils.sendNotice("【嘉问养育】您好！距离订单结束剩余12小时，您还有" + (2-p.getQues_num()) + "次追问机会，如有问题，请及时提交追问，过期视为自动放弃追问机会。谢谢！", p.getUserCPo().getUser_phone()); 
        			// 发完后插入短信发送
        			ConsultSheetSmsPo insP= new ConsultSheetSmsPo();
        			insP.setConsult_id(p.getId());
        			insP.setOperation_type("0");
        			insP.setSms_phone(p.getUserCPo().getUser_phone());
        			insP.setStatus("1");
        			
        			consultService.insConsultSheetSms(insP);
				}
			}
		}
    }
}
