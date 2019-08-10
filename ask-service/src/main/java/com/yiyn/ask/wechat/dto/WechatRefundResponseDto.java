package com.yiyn.ask.wechat.dto;

public class WechatRefundResponseDto extends WechatBaseResponseDto{

	private String device_info;
	private String transaction_id;
	private String out_trade_no;
	private String out_refund_no;
	private String refund_id;
	private String refund_channel;
	private int refund_fee;
	private int settlement_refund_fee_$n;
	private int total_fee;
	private int settlement_total_fee;
	private String fee_type;
	private int cash_fee;
	
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public String getRefund_id() {
		return refund_id;
	}
	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}
	public String getRefund_channel() {
		return refund_channel;
	}
	public void setRefund_channel(String refund_channel) {
		this.refund_channel = refund_channel;
	}
	public int getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}
	public int getSettlement_refund_fee_$n() {
		return settlement_refund_fee_$n;
	}
	public void setSettlement_refund_fee_$n(int settlement_refund_fee_$n) {
		this.settlement_refund_fee_$n = settlement_refund_fee_$n;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public int getSettlement_total_fee() {
		return settlement_total_fee;
	}
	public void setSettlement_total_fee(int settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public int getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(int cash_fee) {
		this.cash_fee = cash_fee;
	}
	
	
}
