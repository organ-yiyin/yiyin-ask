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

import com.yiyn.ask.base.utils.ClientCustomSSL;
import com.yiyn.ask.base.utils.date.DateFormatTemplate;
import com.yiyn.ask.base.utils.date.SPDateUtils;
import com.yiyn.ask.base.utils.dom4j.YiynDocumentHelper;
import com.yiyn.ask.wechat.config.WeixinConfig;
import com.yiyn.ask.wechat.dto.WechatRefundDto;
import com.yiyn.ask.wechat.dto.WechatRefundResponseDto;

/**
 * 退款
 * 
 * @author Administrator
 *
 */
@Service
public class WechatRefundServiceImpl {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String createOrderURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

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
	public WechatRefundResponseDto refund(String order_code, BigDecimal totalFee, BigDecimal refundFee)
			throws Exception {

		WechatRefundDto refundDto = new WechatRefundDto(weixinConfig);
		String prefixTime = SPDateUtils.format(new Date(), DateFormatTemplate.DATE_TIME_FORMAT_COMPACT_S);

		refundDto.setNonce_str(prefixTime + RandomStringUtils.random(10));
		refundDto.setOut_trade_no(order_code);
		refundDto.setOut_refund_no("TK_" + order_code);

		refundDto.setTotal_fee(totalFee.intValue());
		refundDto.setRefund_fee(refundFee.intValue());

		String xml = refundDto.getWechatXml();
		System.out.println("发送xml：" + xml);
		try {
			String refundResult = ClientCustomSSL.doRefund(weixinConfig, createOrderURL, xml);
			System.out.println("退款产生的json字符串：" + refundResult);

			WechatRefundResponseDto responseDto = this.parseRefundXml(refundResult);
			return responseDto;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public WechatRefundResponseDto parseRefundXml(String content) throws Exception {

		WechatRefundResponseDto reponseDto = new WechatRefundResponseDto();

		Document document = YiynDocumentHelper.parseText(content);

		Element rootElement = document.getRootElement();
		String return_code = rootElement.elementText("return_code");
		String return_msg = rootElement.elementText("return_msg");

		// 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
		if ("SUCCESS".equals(return_code)) {
			reponseDto.setReturn_code(return_code);
			reponseDto.setReturn_msg(return_msg);

			reponseDto.setResult_code(rootElement.elementText("result_code"));
			reponseDto.setErr_code(rootElement.elementText("err_code"));
			reponseDto.setErr_code_des(rootElement.elementText("err_code_des"));
			reponseDto.setAppid(rootElement.elementText("appid"));
			reponseDto.setMch_id(rootElement.elementText("mch_id"));
			reponseDto.setDevice_info(rootElement.elementText("device_info"));
			reponseDto.setNonce_str(rootElement.elementText("nonce_str"));
			reponseDto.setSign(rootElement.elementText("sign"));

			reponseDto.setTransaction_id(rootElement.elementText("transaction_id"));
			reponseDto.setOut_trade_no(rootElement.elementText("out_trade_no"));
			reponseDto.setOut_refund_no(rootElement.elementText("out_refund_no"));
			reponseDto.setRefund_id(rootElement.elementText("refund_id"));
			reponseDto.setRefund_channel(rootElement.elementText("refund_channel"));
			reponseDto.setRefund_fee(Integer.parseInt(rootElement.elementText("refund_fee")));
			// reponseDto.setSettlement_refund_fee_$n(Integer.parseInt(rootElement.elementText("settlement_refund_fee_$n")));
			reponseDto.setTotal_fee(Integer.parseInt(rootElement.elementText("total_fee")));
			// reponseDto.setSettlement_total_fee(Integer.parseInt(rootElement.elementText("settlement_total_fee")));
			reponseDto.setFee_type(rootElement.elementText("fee_type"));
			reponseDto.setCash_fee(Integer.parseInt(rootElement.elementText("cash_fee")));

		} else if ("FAIL".equals(return_code)) {
			reponseDto.setReturn_code(return_code);
			reponseDto.setReturn_msg(return_msg);
		}

		return reponseDto;
	}
}
