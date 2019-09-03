package com.yiyn.ask.c.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.yiyn.ask.base.constants.ConsultStatuEnum;
import com.yiyn.ask.base.utils.PayUtils;
import com.yiyn.ask.base.utils.StringUtils;
import com.yiyn.ask.xcx.consult.po.ConsultPo;
import com.yiyn.ask.xcx.consult.service.impl.ConsultService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/notify")
public class XcxNotifyController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
	private ConsultService consultService;
    
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
        	logger.info("成功通知+1");
            String appId = notifyResultMap.get("appid");
            String mchId = notifyResultMap.get("mch_id");
            String outTradeNo = notifyResultMap.get("out_trade_no");   // 商户订单号
	        String totalFee = notifyResultMap.get("total_fee");                // 金额
	        String timeEnd = notifyResultMap.get("time_end");                  // 支付完成时间
	        String transaction_id = notifyResultMap.get("transaction_id");    //微信支付订单号
	        
	        // 根据商户订单id查询咨询单信息
	        ConsultPo p = consultService.getConsultByOdd_Num(outTradeNo);
	        
            if(totalFee.equals(StringUtils.subZeroAndDot(String.valueOf(new Double(p.getPrice()) * 100)))) {
            	logger.info("通知金额和记录的金额一致");
            	// 订单状态是否为待支付
                if(ConsultStatuEnum.PAY_WAIT.getCode().equals(p.getStatus())) {
                	logger.info("订单状态是待支付状态");
                	// 更新状态为成功，并且更新业务信息
                    String result = consultService.handlePayTradeOrder(ConsultStatuEnum.PAY.getCode(), transaction_id,timeEnd,p);
                    if("SUCCESS".equals(result)) {
                        responseMap.put("return_code", "SUCCESS");
                        responseMap.put("return_msg", "OK");
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
