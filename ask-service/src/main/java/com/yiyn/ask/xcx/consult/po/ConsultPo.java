package com.yiyn.ask.xcx.consult.po;

import java.util.Date;

import com.yiyn.ask.base.po.BasePo;
import com.yiyn.ask.xcx.user.po.UserCPo;
import com.yiyn.ask.xcx.user.po.UserEvalPo;
import com.yiyn.ask.xcx.user.po.UserPo;

public class ConsultPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private int id;
	private String user_c_no;
	
	private String user_b_no;
	
	private String user_ref_id;
	
	private String sheet_age_m;//下订单时妈妈的年龄
	
	private String sheet_age_b;// 下订单时宝宝的年龄
	
	private String odd_num;
	
	private String price;
	
	private String user_pay_money;
	
	private Integer discount;
	
	private String problem_desc;
	
	private String problem_type;
	
	private String problem_imgs;
	private String problem_video;
	
	private Date refund_time;
	
	private String status;
	
	private String status_show;
	
	private String pay_odd_num;
	
	private String prepay_id; //预支付订单id
	
	private int ques_num;
	
	private UserEvalPo evalPo;
	
	private ConsultRefPo refPo;
	
	private UserPo userPo;
	
	private UserCPo userCPo;
	
	private String pay_time;
	
	private String coupon_relid;
	
	public UserCPo getUserCPo() {
		return userCPo;
	}

	public void setUserCPo(UserCPo userCPo) {
		this.userCPo = userCPo;
	}

	// 查看订单是否被服务人员回答过
	private int sfhd;
	
	// 是否可退单，订单结束后72小时内都可有退单按钮存在
	private int sfktd;
	
	public int getSfhd() {
		return sfhd;
	}

	public void setSfhd(int sfhd) {
		this.sfhd = sfhd;
	}

	public int getSfktd() {
		return sfktd;
	}

	public void setSfktd(int sfktd) {
		this.sfktd = sfktd;
	}

	public String getPay_odd_num() {
		return pay_odd_num;
	}

	public void setPay_odd_num(String pay_odd_num) {
		this.pay_odd_num = pay_odd_num;
	}

	public UserPo getUserPo() {
		return userPo;
	}

	public void setUserPo(UserPo userPo) {
		this.userPo = userPo;
	}

	public ConsultRefPo getRefPo() {
		return refPo;
	}

	public void setRefPo(ConsultRefPo refPo) {
		this.refPo = refPo;
	}

	public String getUser_c_no() {
		return user_c_no;
	}

	public void setUser_c_no(String user_c_no) {
		this.user_c_no = user_c_no;
	}

	public String getUser_b_no() {
		return user_b_no;
	}

	public void setUser_b_no(String user_b_no) {
		this.user_b_no = user_b_no;
	}

	public String getUser_ref_id() {
		return user_ref_id;
	}

	public void setUser_ref_id(String user_ref_id) {
		this.user_ref_id = user_ref_id;
	}

	public String getOdd_num() {
		return odd_num;
	}

	public void setOdd_num(String odd_num) {
		this.odd_num = odd_num;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProblem_desc() {
		return problem_desc;
	}

	public void setProblem_desc(String problem_desc) {
		this.problem_desc = problem_desc;
	}

	public String getProblem_type() {
		return problem_type;
	}

	public void setProblem_type(String problem_type) {
		this.problem_type = problem_type;
	}

	public String getProblem_imgs() {
		return problem_imgs;
	}

	public void setProblem_imgs(String problem_imgs) {
		this.problem_imgs = problem_imgs;
	}

	public String getProblem_video() {
		return problem_video;
	}

	public void setProblem_video(String problem_video) {
		this.problem_video = problem_video;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSheet_age_m() {
		return sheet_age_m;
	}

	public void setSheet_age_m(String sheet_age_m) {
		this.sheet_age_m = sheet_age_m;
	}

	public String getSheet_age_b() {
		return sheet_age_b;
	}

	public void setSheet_age_b(String sheet_age_b) {
		this.sheet_age_b = sheet_age_b;
	}

	public String getStatus_show() {
		return status_show;
	}

	public void setStatus_show(String status_show) {
		this.status_show = status_show;
	}

	public UserEvalPo getEvalPo() {
		return evalPo;
	}

	public void setEvalPo(UserEvalPo evalPo) {
		this.evalPo = evalPo;
	}

	public int getQues_num() {
		return ques_num;
	}

	public void setQues_num(int ques_num) {
		this.ques_num = ques_num;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getPay_time() {
		return pay_time;
	}

	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}

	public Date getRefund_time() {
		return refund_time;
	}

	public void setRefund_time(Date refund_time) {
		this.refund_time = refund_time;
	}

	public String getCoupon_relid() {
		return coupon_relid;
	}

	public void setCoupon_relid(String coupon_relid) {
		this.coupon_relid = coupon_relid;
	}

	public String getUser_pay_money() {
		return user_pay_money;
	}

	public void setUser_pay_money(String user_pay_money) {
		this.user_pay_money = user_pay_money;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
}
