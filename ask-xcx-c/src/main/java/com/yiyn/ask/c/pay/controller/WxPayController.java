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
import com.yiyn.ask.base.constants.ProcessContentTypeEnum;
import com.yiyn.ask.base.constants.ProcessSendTypeEnum;
import com.yiyn.ask.base.constants.UserCouponStatusEnum;
import com.yiyn.ask.base.utils.DateUtils;
import com.yiyn.ask.base.utils.StringUtils;
import com.yiyn.ask.base.utils.ValidateCode;
import com.yiyn.ask.c.wechat.controller.XcxOAuthService;
import com.yiyn.ask.wechat.config.WeixinConfig;
import com.yiyn.ask.wechat.dto.WechatPayDto;
import com.yiyn.ask.wechat.dto.WechatPrepayResponseDto;
import com.yiyn.ask.wechat.dto.WechatResultDto;
import com.yiyn.ask.wechat.dto.WechatXcxDto;
import com.yiyn.ask.wechat.service.WechatPrepayService;
import com.yiyn.ask.xcx.account.po.AccountFlowPo;
import com.yiyn.ask.xcx.account.po.AccountPo;
import com.yiyn.ask.xcx.account.service.impl.AccountService;
import com.yiyn.ask.xcx.consult.po.ConsultLogPo;
import com.yiyn.ask.xcx.consult.po.ConsultPo;
import com.yiyn.ask.xcx.consult.po.ConsultProcessPo;
import com.yiyn.ask.xcx.consult.po.ConsultSheetRefPo;
import com.yiyn.ask.xcx.consult.service.impl.ConsultService;
import com.yiyn.ask.xcx.user.po.UserCouponPo;
import com.yiyn.ask.xcx.user.service.impl.UserService;

@Controller
@RequestMapping("/wxpay")
public class WxPayController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ConsultService consultService;
	
	@Autowired
	private UserService userService;
//	
//	@Autowired
//	private AccountService accountService;
	
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
			String files,String videos, int discount,
	        String couponid) throws Exception {
		logger.info("goWxPay");
		Map<String,Object> m = new HashMap<String,Object>();

		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);	
		String business_id =  ValidateCode.randomCode(10);//10位随机字符串 内部订单id
		
		BigDecimal amount =new BigDecimal(zfje);
		BigDecimal price = new BigDecimal(zfje).add(new BigDecimal(discount));
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
		orderRequset.setPrice(String.valueOf(price));
		orderRequset.setStatus(ConsultStatuEnum.PAY_WAIT.getCode());
		orderRequset.setProblem_desc(ques_desc);
		orderRequset.setProblem_imgs(files);
		orderRequset.setProblem_type(ques_type);
		orderRequset.setProblem_video(videos);
		orderRequset.setCoupon_relid(couponid);
		orderRequset.setDiscount(discount);
		orderRequset.setUser_pay_money(zfje);
		orderRequset.setCreated_by(open_id);
		
		// 微信订单调用生成预支付订单
		WechatResultDto<WechatPrepayResponseDto> resDto = wechatPrepayService.getPrepay(business_id, "微信小程序支付", 
				"0.0.0.0", amount, notify_url, open_id);
		if(resDto.isSuccess()){
			//预支付生成成功插入订单表
			orderRequset.setPrepay_id(resDto.getResult().getPrepay_id());
			// 获取id插入咨询单关联人表用
			consultService.insConsult(orderRequset);
			// 插入咨询单关联表
			ConsultSheetRefPo refp = new ConsultSheetRefPo();
			refp.setRef_id(refid);
			refp.setOdd_num(business_id);
			consultService.insConsultSheetRef(refp);
			
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
			
			// 如果有使用优惠券，更新优惠券的状态
			if(discount > 0 && !StringUtils.isEmptyString(couponid)){
				UserCouponPo p = new UserCouponPo();
				p.setStatus(UserCouponStatusEnum.USERD.getName());
				p.setId(new Long(couponid));
				userService.updUserCoupon(p);
			}
		}else{
			m.put("status", "10");//生成订单失败或者充电失败
			m.put("msg", "预支付订单生成失败！");
		}
		
		return new Gson().toJson(m);
	}
	
	/**
	 * 订单待支付直接根据订单去微信支付
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
	
//	/**
//	 * 如果支付金额为0，则免费使用。
//	 * 
//	 * @param request
//	 * @param response
//	 * @param regForm
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/goWxPayFree.x", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
//	@ResponseBody
//	public String goWxPayFree(HttpServletRequest request,
//			HttpServletResponse response,String sessionid,String user_no,String refid,
//			String zfje,String ques_desc,String ques_type,String age_m,String age_b,
//			String files,String videos, int discount,
//	        String couponid) throws Exception {
//		logger.info("goWxPay");
//		Map<String,Object> m = new HashMap<String,Object>();
//
//		WechatXcxDto dto = XcxOAuthService.getWechatXcxDto(sessionid);	
//		String business_id =  ValidateCode.randomCode(10);//10位随机字符串 内部订单id
//		
//		BigDecimal amount =new BigDecimal(zfje);
//		BigDecimal price = new BigDecimal(zfje).add(new BigDecimal(discount));
//		logger.info("=========支付金额========:" + amount);
//		String open_id = dto.getDb_open_id();
//		logger.info("=========用户open_id========:" + open_id);
//		// 先创建咨询单
//		// 关联人信息查找
//		//ConsultRefPo refPo = userService.getRefDetail(refid);
//		ConsultPo orderRequset = new ConsultPo();
//		orderRequset.setUser_c_no(open_id);
//		orderRequset.setUser_b_no(user_no);
//		orderRequset.setUser_ref_id(refid);
//		orderRequset.setOdd_num(business_id);
//		orderRequset.setSheet_age_b(age_b);
//		orderRequset.setSheet_age_m(age_m);
//		orderRequset.setPrice(String.valueOf(price));
//		orderRequset.setStatus(ConsultStatuEnum.PAY.getCode());
//		orderRequset.setProblem_desc(ques_desc);
//		orderRequset.setProblem_imgs(files);
//		orderRequset.setProblem_type(ques_type);
//		orderRequset.setProblem_video(videos);
//		orderRequset.setCoupon_relid(couponid);
//		orderRequset.setDiscount(discount);
//		orderRequset.setUser_pay_money(zfje);
//		orderRequset.setCreated_by(open_id);
//		
//		//预支付生成成功插入订单表
//		orderRequset.setPrepay_id("");
//		// 获取id插入咨询单关联人表用
//		consultService.insConsult(orderRequset);
//		// 插入咨询单关联表
//		ConsultSheetRefPo refp = new ConsultSheetRefPo();
//		refp.setRef_id(refid);
//		refp.setOdd_num(business_id);
//		consultService.insConsultSheetRef(refp);
//		
//		 // 插入咨询单进程（包含文字，图片，视频等）
//		  // 文字直接插入
//		  ConsultProcessPo textP = new ConsultProcessPo();
//		  textP.setConsultation_id(String.valueOf(p.getId()));
//		  textP.setContent(p.getProblem_desc());
//		  textP.setContent_type(ProcessContentTypeEnum.TEXT.getName());
//		  textP.setSend_type(ProcessSendTypeEnum.CUSTOMER.getName());
//		  
//		  this.insConsultProcess(textP);
//		  // 有图片和视频插入
//		  if(!StringUtils.isEmptyString(p.getProblem_imgs())){
//			  String[] imgs = p.getProblem_imgs().split(",");
//			  for(String img:imgs){
//				  ConsultProcessPo imgP = new ConsultProcessPo();
//				  imgP.setConsultation_id(String.valueOf(p.getId()));
//				  imgP.setContent(img);
//				  imgP.setContent_type(ProcessContentTypeEnum.IMG.getName());
//				  imgP.setSend_type(ProcessSendTypeEnum.CUSTOMER.getName());
//				  
//				  this.insConsultProcess(imgP);
//			  }
//		  }
//		  
//		  if(!StringUtils.isEmptyString(p.getProblem_video())){
//			  String[] videos = p.getProblem_video().split(",");
//			  for(String video:videos){
//				  ConsultProcessPo imgP = new ConsultProcessPo();
//				  imgP.setConsultation_id(String.valueOf(p.getId()));
//				  imgP.setContent(video);
//				  imgP.setContent_type(ProcessContentTypeEnum.VIDEO.getName());
//				  imgP.setSend_type(ProcessSendTypeEnum.CUSTOMER.getName());
//				  
//				  this.insConsultProcess(imgP);
//			  }
//		  }
//		  // 插入咨询单操作日志
//		  ConsultLogPo logp = new ConsultLogPo();
//		  logp.setLog_type(ConsultStatuEnum.PAY.getCode());
//		  logp.setConsult_id(String.valueOf(p.getId()));
//		  logp.setLog_desc("微信通知插入咨询单操作日志！");
//		  this.insConsultLog(logp);
//		  
//		  // 如果有优惠券信息，更新总账户金额，并且更新流水表
//		  if(p.getDiscount() > 0 && !StringUtils.isEmptyString(p.getCoupon_relid())){
//			  
//		  }
//		  
//		  // 插入账户流水，根据账户ID
//		  // 先根据用户编号和id查找账户id
//		  AccountPo account = accountDao.getAccountInfo(p.getUser_b_no());
//		  AccountFlowPo flowP = new AccountFlowPo();
//		  flowP.setAccount_id(account.getId());
//		  flowP.setJournal_money(new Double(p.getPrice()));
//		  flowP.setJournal_dir("1");// 1：流入，2：流出
//		  flowP.setJournal_type("1");// 1：用户支付，2：提现：3：退款
//		  flowP.setOrder_id(p.getOdd_num());
//		  flowP.setPay_type("WXPAY");
//		  flowP.setPay_time(end_time);
//		  flowP.setJournal_remark("微信小程序支付通知插入");
//		  flowP.setPay_status("1");//1:成功；2：待处理
//		  flowP.setPay_channel_no("WXXCX");
//		  accountFlowDao.insert(flowP);
//		  
//		  // 更新B端用户账户余额--按照支付金额+优惠金额入账
//		  account.setBalance(account.getBalance().add(new BigDecimal(p.getPrice())));
//		  accountDao.updateById(account);
//		
//		m.put("status", "0");
//		//m.put("ddid", prepay_id);
//		m.put("payDto", payDto);
//		
//		// 如果有使用优惠券，更新优惠券的状态
//		if(discount > 0 && !StringUtils.isEmptyString(couponid)){
//			UserCouponPo p = new UserCouponPo();
//			p.setStatus(UserCouponStatusEnum.USERD.getName());
//			p.setId(new Long(couponid));
//			userService.updUserCoupon(p);
//			
//			AccountPo account_admin = accountService.getAccountInfo("super_admin");
//		    AccountFlowPo totalP = new AccountFlowPo();
//		    totalP.setAccount_id(account_admin.getId());
//		    totalP.setJournal_money(new Double(discount));
//		    totalP.setJournal_dir("2");// 1：流入，2：流出
//		    totalP.setJournal_type("4");// 1：用户支付，2：提现：3：退款  4:优惠券扣除
//		    totalP.setOrder_id(p.getOdd_num());
//		    totalP.setPay_type("WXPAY");
//		    totalP.setPay_time(end_time);
//		    totalP.setJournal_remark("微信小程序支付通知插入，使用优惠券金额，总账户支出");
//		    totalP.setPay_status("1");//1:成功；2：待处理
//		    totalP.setPay_channel_no("WXXCX");
//		    accountFlowDao.insert(totalP);
//		  
//		  // 更新B端用户账户余额--按照支付金额+优惠金额入账
//		  account_admin.setBalance(account_admin.getBalance().subtract(new BigDecimal(p.getDiscount())));
//		  accountDao.updateById(account_admin);
//		}
//		
//		return new Gson().toJson(m);
//	}
}
