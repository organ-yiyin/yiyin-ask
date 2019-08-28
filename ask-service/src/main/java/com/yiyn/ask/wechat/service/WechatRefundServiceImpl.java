package com.yiyn.ask.wechat.service;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiyn.ask.base.utils.date.DateFormatTemplate;
import com.yiyn.ask.base.utils.date.SPDateUtils;
import com.yiyn.ask.base.utils.dom4j.YiynDocumentHelper;
import com.yiyn.ask.base.utils.http.ClientCustomSSL;
import com.yiyn.ask.wechat.config.WeixinConfig;
import com.yiyn.ask.wechat.dto.WechatRefundDto;
import com.yiyn.ask.wechat.dto.WechatRefundResponseDto;
import com.yiyn.ask.wechat.dto.WechatResultDto;

/**
 * 退款
 * 
 * @author Administrator
 *
 */
@Service
public class WechatRefundServiceImpl {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String refundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	@Autowired
	private WeixinConfig weixinConfig;

	/**
	 * 退款
	 * 
	 * @param order_code
	 *            要退款的订单号
	 * @param totalFee
	 *            订单总金额
	 * @param refundFee
	 *            要退款的金额
	 * @return
	 * @throws Exception
	 */
	public WechatResultDto<WechatRefundResponseDto> refund(String order_code, BigDecimal totalFee, BigDecimal refundFee)
			throws Exception {

		WechatRefundDto refundDto = new WechatRefundDto(weixinConfig);
		String prefixTime = SPDateUtils.format(new Date(), DateFormatTemplate.DATE_TIME_FORMAT_COMPACT_S);

		refundDto.setNonce_str(prefixTime + RandomStringUtils.randomAlphabetic(10));
		refundDto.setOut_trade_no(order_code);
		refundDto.setOut_refund_no("TK_" + order_code);

		refundDto.setTotal_fee(totalFee.multiply(BigDecimal.valueOf(100l)).intValue());
		refundDto.setRefund_fee(refundFee.multiply(BigDecimal.valueOf(100l)).intValue());

		String xml = refundDto.getWechatXml();
		System.out.println("发送xml：" + xml);
		try {
			String refundResult = ClientCustomSSL.doRefund(weixinConfig, refundUrl, xml);
			System.out.println("退款产生的json字符串：" + refundResult);

			WechatResultDto<WechatRefundResponseDto> responseDto = this.parseRefundXml(refundResult);
			return responseDto;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public WechatResultDto<WechatRefundResponseDto> parseRefundXml(String content) throws Exception {
		
		WechatResultDto<WechatRefundResponseDto> result = new WechatResultDto<WechatRefundResponseDto>();
		WechatRefundResponseDto reponseDto = new WechatRefundResponseDto();
		System.out.println("refund content : " + content);
		Document document = YiynDocumentHelper.parseText(content);
		Element rootElement = document.getRootElement();
		String return_code = rootElement.elementText("return_code");
		String return_msg = rootElement.elementText("return_msg");
		reponseDto.setReturn_code(return_code);
		reponseDto.setReturn_msg(return_msg);
		
		// 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
		if ("SUCCESS".equals(return_code)) {
			if("SUCCESS".equals(reponseDto.getResult_code())) {
				reponseDto.setAppid(rootElement.elementText("appid"));
				reponseDto.setMch_id(rootElement.elementText("mch_id"));
				reponseDto.setNonce_str(rootElement.elementText("nonce_str"));
				reponseDto.setSign(rootElement.elementText("sign"));
				// SUCCESS/FAIL 
				reponseDto.setResult_code(rootElement.elementText("result_code"));
				reponseDto.setErr_code(rootElement.elementText("err_code"));
				reponseDto.setErr_code_des(rootElement.elementText("err_code_des"));
				reponseDto.setDevice_info(rootElement.elementText("device_info"));
				reponseDto.setTransaction_id(rootElement.elementText("transaction_id"));
				reponseDto.setOut_trade_no(rootElement.elementText("out_trade_no"));
				reponseDto.setOut_refund_no(rootElement.elementText("out_refund_no"));
				reponseDto.setRefund_id(rootElement.elementText("refund_id"));
				reponseDto.setRefund_channel(rootElement.elementText("refund_channel"));
				reponseDto.setRefund_fee(Integer.parseInt(rootElement.elementText("refund_fee")));
				reponseDto.setTotal_fee(Integer.parseInt(rootElement.elementText("total_fee")));
				reponseDto.setFee_type(rootElement.elementText("fee_type"));
				reponseDto.setCash_fee(Integer.parseInt(rootElement.elementText("cash_fee")));
				result.setSuccess(true);
			}
			else {
				
				reponseDto.setErr_code(rootElement.elementText("err_code"));
				reponseDto.setErr_code_des(rootElement.elementText("err_code_des"));
				result.setMessage(reponseDto.getErr_code_des());
				result.setSuccess(false);
			}

		} else {
			result.setSuccess(false);
			result.setMessage(return_msg);
		}
		result.setResult(reponseDto);
		return result;
	}
}
