package com.yiyn.ask.c.handler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yiyn.ask.base.constants.ConsultStatuEnum;
import com.yiyn.ask.base.utils.PayUtils;
import com.yiyn.ask.base.utils.SMSUtils;
import com.yiyn.ask.base.utils.StringUtils;
import com.yiyn.ask.c.wechat.controller.XcxOAuthService;
import com.yiyn.ask.xcx.center.po.FormIdPo;
import com.yiyn.ask.xcx.center.service.impl.FormIdService;
import com.yiyn.ask.xcx.consult.po.ConsultPo;
import com.yiyn.ask.xcx.consult.service.impl.ConsultService;
import com.yiyn.ask.xcx.user.po.UserPo;
import com.yiyn.ask.xcx.user.service.impl.UserService;

@Controller
@RequestMapping("/notify")
public class XcxNotifyController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
	private ConsultService consultService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private SMSUtils smsUtils;
    
    @Autowired
    private FormIdService formIdService;
    
	@Autowired
	private XcxOAuthService oAuthService;
    
    //这里是支付回调接口，微信支付成功后会自动调用
    @RequestMapping(value = "/wxNotify.x", method = RequestMethod.POST)
    public String wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        logger.info(" notifyResultXml   : " + notityXml);
        Map<String,String> notifyResultMap = PayUtils.doXMLParse(notityXml);
        
        Map<String, String> responseMap = new HashMap<String, String>();
        responseMap.put("return_code", "FAIL");
        responseMap.put("return_msg", "NOTOK");
        
        String returnCode = (String) notifyResultMap.get("return_code");                // SUCCESS
        String resultCode = (String) notifyResultMap.get("result_code");                // SUCCESS/FAIL
        if("SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode)) {
            String appId = notifyResultMap.get("appid");
            String mchId = notifyResultMap.get("mch_id");
            String outTradeNo = notifyResultMap.get("out_trade_no");   // 商户订单号
	        String totalFee = notifyResultMap.get("total_fee");        // 金额
	        String timeEnd = notifyResultMap.get("time_end");          // 支付完成时间
	        String transaction_id = notifyResultMap.get("transaction_id"); //微信支付订单号
	        
	        // 根据商户订单id查询咨询单信息
	        ConsultPo p = consultService.getConsultByOdd_Num(outTradeNo);
	        
            if(totalFee.equals(StringUtils.subZeroAndDot(String.valueOf(new Double(p.getPrice()) * 100)))) {
            	// 订单状态是否为待支付
                if(ConsultStatuEnum.PAY_WAIT.getCode().equals(p.getStatus())) {
                	logger.info("订单状态是待支付状态====进入通知");
                	// 更新状态为成功，并且更新业务信息
                    String result = consultService.handlePayTradeOrder(ConsultStatuEnum.PAY.getCode(), transaction_id,timeEnd,p);
                    if("SUCCESS".equals(result)) {
                      responseMap.put("return_code", "SUCCESS");
                      responseMap.put("return_msg", "OK");
                      // 插入消息模版通知的form_id
            		  FormIdPo insP = new FormIdPo();
            		  insP.setForm_id(p.getPrepay_id());
            		  insP.setRel_user(p.getUser_c_no());
            		  insP.setSource("C");
            		  insP.setSend_num(3);// 微信支付通知可以发送三次
            		  formIdService.insert(insP);
                      
                      // 成功短信以及通知
              		  // C端给B端发消息  通知咨询师有人咨询 获取B端用户信息
              		  // 先发送短信
              		  if(!StringUtils.isEmptyString(p.getUserPo().getUser_phone())){
              			  smsUtils.sendNotice("【YIYN问答】您好，您收到一条付费咨询，请及时登录YIYN问答小程序查看", p.getUserPo().getUser_phone()); 
		      		  }
		      		  // 获取到的form_id用来保存发送用
		      		  // 后发通知
		      		  Map<String,String> param = new HashMap<String,String>();
		      		  FormIdPo formP = formIdService.getFormId(p.getUserPo().getOpen_id()) ;
		      			if(formP != null){
		      				// 真是的form_id从用户取得
		      				param.put("open_id", p.getUserPo().getOpen_id());
		      				param.put("form_id", formP.getForm_id());
		      				param.put("zxr", p.getRefPo().getName_m());// 咨询人姓名（妈妈名称）
		      				param.put("zxlx", p.getProblem_type());
		      				param.put("zxsj", p.getCreated_time_format());
		      				param.put("zxnr", p.getProblem_desc());
		      				param.put("url", "pages/consultation/consultation");
		      				boolean f = oAuthService.sendMsg(param);
		      				
		      				// 发送成功的情况下
		      				if(f){
		      					formIdService.updateById(formP);
		      				}else{
		      					formIdService.delForm(formP);
		      				}
		      			}
                    }
                // 如果是其他状态则代表通知已经成功
                }else {
                    responseMap.put("return_code", "SUCCESS");
                    responseMap.put("return_msg", "OK");
                }
            }
        }
        return this.map2XmlStr(responseMap);
    }
    
    private String map2XmlStr(Map<String, String> map) {
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>");
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for(Map.Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!StringUtils.isEmptyString(value)) {
                xml.append("<").append(key).append(">");
                xml.append(entry.getValue());
                xml.append("</").append(key).append(">");
            }
        }
        xml.append("</xml>");
        return xml.toString();
    }
}
