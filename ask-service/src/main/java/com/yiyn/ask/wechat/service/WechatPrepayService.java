package com.yiyn.ask.wechat.service;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.entity.StringEntity;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiyn.ask.base.utils.date.DateFormatTemplate;
import com.yiyn.ask.base.utils.date.SPDateUtils;
import com.yiyn.ask.base.utils.dom4j.YiynDocumentHelper;
import com.yiyn.ask.base.utils.http.HttpClientPostUtils;
import com.yiyn.ask.wechat.config.WeixinConfig;
import com.yiyn.ask.wechat.dto.WechatPrepayRequestDto;
import com.yiyn.ask.wechat.dto.WechatPrepayResponseDto;

/**
 * 微信支付生成预订单
 * 
 * @author Administrator
 *
 */
@Service
public class WechatPrepayService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	@Autowired
	private WeixinConfig wechatConfig;

	/**
	 * 生成预订单
	 * 
	 * @param order_code
	 *            订单号(唯一)
	 * @param comment
	 *            订单描述
	 * @param ipAddress
	 *            客户ip
	 * @param totalAmount
	 *            总金额
	 * @param notifyUrl
	 *            回调通知地址
	 * @param openId
	 *            该公众号下某一微信号的openId
	 * @return
	 * @throws Exception
	 */
	public WechatPrepayResponseDto getPrepay(String order_code, String comment, String ipAddress,
			BigDecimal totalAmount, String notifyUrl, String openId) throws Exception {

		// String prefixTime = YiynDateUtils.format(new Date(),
		// YiynDateUtils.DATE_KEY_STR);
		String prefixTime = SPDateUtils.format(new Date(), DateFormatTemplate.DATE_TIME_FORMAT_COMPACT_S);

		WechatPrepayRequestDto prepayDto = new WechatPrepayRequestDto(wechatConfig);
		prepayDto.setNonce_str(prefixTime + RandomStringUtils.random(10));
		// prepayDto.setBody(orderDetailsBo.getOrderBo().getNote());
		prepayDto.setBody(comment);
		prepayDto.setOut_trade_no(order_code);
		// prepayDto.setTotal_fee(orderDetailsBo.getOrderBo().getNetpay().multiply(BigDecimal.valueOf(100)).intValue());
		prepayDto.setTotal_fee(totalAmount.multiply(BigDecimal.valueOf(100)).intValue());
		prepayDto.setSpbill_create_ip(ipAddress);
		prepayDto.setNotify_url(notifyUrl);
		prepayDto.setTrade_type("JSAPI");
		prepayDto.setOpenid(openId);

		String wechatXml = prepayDto.getWechatXml();
		logger.info("wechatXml:");
		logger.info(wechatXml);
		String content = HttpClientPostUtils.getHttpPostContentByEntity(createOrderURL,
				new StringEntity(wechatXml, "UTF-8"), "UTF-8");
		logger.info("wechatResponseXml:");
		logger.info(content);

		return this.parsePrepayXml(content);
	}

	public WechatPrepayResponseDto parsePrepayXml(String content) throws Exception {

		WechatPrepayResponseDto prepayDto = new WechatPrepayResponseDto();
		Document document = YiynDocumentHelper.parseText(content);

		Element rootElement = document.getRootElement();
		if ("SUCCESS".equals(rootElement.elementText("return_code"))
				&& "SUCCESS".equals(rootElement.elementText("result_code"))) {
			String prepay_id = rootElement.elementText("prepay_id");
			// String code_url = rootElement.elementText("code_url");

			prepayDto.setPrepay_id(prepay_id);
			// prepayDto.setCode_url(code_url);
		} else {

		}
		return prepayDto;
	}

}
