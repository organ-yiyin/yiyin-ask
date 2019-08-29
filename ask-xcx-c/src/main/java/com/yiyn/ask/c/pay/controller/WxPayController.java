package com.yiyn.ask.c.pay.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.yiyn.ask.base.constants.ConsultStatuEnum;
import com.yiyn.ask.base.utils.StringUtils;
import com.yiyn.ask.base.utils.ValidateCode;
import com.yiyn.ask.c.wechat.controller.XcxOAuthService;
import com.yiyn.ask.wechat.config.WeixinConfig;
import com.yiyn.ask.wechat.dto.WechatPayDto;
import com.yiyn.ask.wechat.dto.WechatPrepayResponseDto;
import com.yiyn.ask.wechat.dto.WechatResultDto;
import com.yiyn.ask.wechat.dto.WechatXcxDto;
import com.yiyn.ask.wechat.service.WechatPrepayService;
import com.yiyn.ask.xcx.consult.po.ConsultPo;
import com.yiyn.ask.xcx.consult.po.ConsultRefPo;
import com.yiyn.ask.xcx.consult.service.impl.ConsultService;
import com.yiyn.ask.xcx.user.service.impl.UserService;

@Controller
@RequestMapping("/wxpay")
public class WxPayController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ConsultService consultService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WeixinConfig wechatConfig;
	@Autowired
	private WechatPrepayService wechatPrepayService;
	
	@Value("#{configProperties['wechat.notifyurl']}")
	private String notify_url;
	
	/**
	 * 微信支付
	 * 
	 * @param request
	 * @param response
	 * @param regForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goWxPay.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String goWxPay(HttpServletRequest request,
			HttpServletResponse response,String sessionid,String user_no,String refid,
			String zfje,String ques_desc,String ques_type,String age_m,String age_b,
			String files,String videos) throws Exception {
		logger.info("goWxPay");
		Map<String,Object> m = new HashMap<String,Object>();

		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);	
		String business_id =  ValidateCode.randomCode(10);//10位随机字符串 内部订单id
		
		BigDecimal amount =new BigDecimal(zfje);
		logger.info("=========支付金额========:" + amount);
		String open_id = dto.getDb_open_id();
		logger.info("=========用户open_id========:" + open_id);
		// 先创建咨询单
		// 关联人信息查找
		//ConsultRefPo refPo = userService.getRefDetail(refid);
		ConsultPo orderRequset = new ConsultPo();
		orderRequset.setUser_c_no(open_id);
		orderRequset.setUser_b_no(user_no);
		orderRequset.setUser_ref_id(refid);
		orderRequset.setOdd_num(business_id);
		orderRequset.setSheet_age_b(age_b);
		orderRequset.setSheet_age_m(age_m);
		orderRequset.setPrice(zfje);
		orderRequset.setStatus(ConsultStatuEnum.PAY_WAIT.getCode());
		orderRequset.setProblem_desc(ques_desc);
		orderRequset.setProblem_imgs(files);
		orderRequset.setProblem_type(ques_type);
		orderRequset.setProblem_video(videos);
		orderRequset.setCreated_by(open_id);
		
		// 微信订单调用生成预支付订单
		WechatResultDto<WechatPrepayResponseDto> resDto = wechatPrepayService.getPrepay(business_id, "微信小程序支付", 
				"0.0.0.0", amount, notify_url, open_id);
		if(resDto.isSuccess()){
			//预支付生成成功插入订单表
			orderRequset.setPrepay_id(resDto.getResult().getPrepay_id());
			consultService.insConsult(orderRequset);
			String prepay_id = resDto.getResult().getPrepay_id();
			logger.info("=========跳转微信支付页面========:" + prepay_id);
			String nonceStr = ValidateCode.randomCode(10);
			String timeStamp = String.valueOf(new Date().getTime()/1000);
			
			WechatPayDto payDto = new WechatPayDto(wechatConfig);
			payDto.setNonceStr(nonceStr);
			payDto.setTimeStamp(timeStamp);
			payDto.setPackage_str("prepay_id=" + prepay_id);
			payDto.setSignType("MD5");
			payDto.setPaySign(payDto.createSign());
			
			m.put("status", "0");
			//m.put("ddid", prepay_id);
			m.put("payDto", payDto);
		}else{
			m.put("status", "10");//生成订单失败或者充电失败
			m.put("msg", "预支付订单生成失败！");
		}
		
		return new Gson().toJson(m);
	}
	
	/**
	 * 微信支付
	 * 
	 * @param request
	 * @param response
	 * @param regForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goWxPayById.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String goWxPayById(HttpServletRequest request,
			HttpServletResponse response,String prepay_id) throws Exception {
		logger.info("goWxPayById");
		
		Map<String,Object> m = new HashMap<String,Object>();
		String nonceStr = ValidateCode.randomCode(10);
		String timeStamp = String.valueOf(new Date().getTime()/1000);
		
		WechatPayDto payDto = new WechatPayDto(wechatConfig);
		payDto.setNonceStr(nonceStr);
		payDto.setTimeStamp(timeStamp);
		payDto.setPackage_str("prepay_id=" + prepay_id);
		payDto.setSignType("MD5");
		payDto.setPaySign(payDto.createSign());
		m.put("status", "0");
		m.put("payDto", payDto);
		
		return new Gson().toJson(m);
	}
}
