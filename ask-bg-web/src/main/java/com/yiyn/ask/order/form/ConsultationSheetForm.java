package com.yiyn.ask.order.form;

import java.util.ArrayList;
import java.util.List;

import com.yiyn.ask.base.constants.AttachmentTypeEnum;
import com.yiyn.ask.base.constants.ConsultStatuEnum;
import com.yiyn.ask.base.constants.ContentTypeEnum;
import com.yiyn.ask.base.constants.GenderEnum;
import com.yiyn.ask.base.constants.LogUserTypeEnum;
import com.yiyn.ask.base.constants.SendTypeEnum;
import com.yiyn.ask.base.form.BaseForm;
import com.yiyn.ask.xcx.center.po.CodePo;

public class ConsultationSheetForm extends BaseForm {
	
	private static final long serialVersionUID = -9051878305444203630L;
	
	private String user_c_no;
	
	private String user_b_no;
	
	private String user_ref_id;
	
	private String sheet_age_m;//下订单时妈妈的年龄
	
	private String sheet_age_b;// 下订单时宝宝的年龄
	
	private String odd_num;
	/**
	 * 订单金额
	 */
	private String price;
	/**
	 * 实际支付金额
	 */
	private String user_pay_money;
	/**
	 * 优惠金额
	 */
	private Integer discount;
	// 咨询师收入金额
	private String deservedPrice;
	
	private String problem_desc;
	
	private String problem_type;
	
	private String problem_imgs;
	private String problem_video;
	
	private String status;
	
	private String status_show;
	
	private String pay_odd_num;
	
	private String prepay_id; //预支付订单id
	
	private int ques_num;
	
	private String[] problem_img_list;
	
	private String[] problem_video_list;

	private ConsultStatuEnum[] consultStatusList = ConsultStatuEnum.values();
	
	private AttachmentTypeEnum[] attachmentTypes = AttachmentTypeEnum.values();
	
	private GenderEnum[] genders = GenderEnum.values();
	
	private LogUserTypeEnum[] logUserTypes = LogUserTypeEnum.values();
	
	private List<CodePo> qus_types = new ArrayList<>();
	
	private ContentTypeEnum[] contentTypes = ContentTypeEnum.values();
	
	private SendTypeEnum[] sendTypes = SendTypeEnum.values();

	public ConsultStatuEnum[] getConsultStatusList() {
		return consultStatusList;
	}

	public void setConsultStatusList(ConsultStatuEnum[] consultStatusList) {
		this.consultStatusList = consultStatusList;
	}

	public AttachmentTypeEnum[] getAttachmentTypes() {
		return attachmentTypes;
	}

	public void setAttachmentTypes(AttachmentTypeEnum[] attachmentTypes) {
		this.attachmentTypes = attachmentTypes;
	}

	public GenderEnum[] getGenders() {
		return genders;
	}

	public void setGenders(GenderEnum[] genders) {
		this.genders = genders;
	}

	public LogUserTypeEnum[] getLogUserTypes() {
		return logUserTypes;
	}

	public void setLogUserTypes(LogUserTypeEnum[] logUserTypes) {
		this.logUserTypes = logUserTypes;
	}

	public List<CodePo> getQus_types() {
		return qus_types;
	}

	public void setQus_types(List<CodePo> qus_types) {
		this.qus_types = qus_types;
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

	public String getStatus_show() {
		return status_show;
	}

	public void setStatus_show(String status_show) {
		this.status_show = status_show;
	}

	public String getPay_odd_num() {
		return pay_odd_num;
	}

	public void setPay_odd_num(String pay_odd_num) {
		this.pay_odd_num = pay_odd_num;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public int getQues_num() {
		return ques_num;
	}

	public void setQues_num(int ques_num) {
		this.ques_num = ques_num;
	}

	public String[] getProblem_img_list() {
		return problem_img_list;
	}

	public void setProblem_img_list(String[] problem_img_list) {
		this.problem_img_list = problem_img_list;
	}

	public String[] getProblem_video_list() {
		return problem_video_list;
	}

	public void setProblem_video_list(String[] problem_video_list) {
		this.problem_video_list = problem_video_list;
	}

	public ContentTypeEnum[] getContentTypes() {
		return contentTypes;
	}

	public void setContentTypes(ContentTypeEnum[] contentTypes) {
		this.contentTypes = contentTypes;
	}

	public SendTypeEnum[] getSendTypes() {
		return sendTypes;
	}

	public void setSendTypes(SendTypeEnum[] sendTypes) {
		this.sendTypes = sendTypes;
	}

	public String getDeservedPrice() {
		return deservedPrice;
	}

	public void setDeservedPrice(String deservedPrice) {
		this.deservedPrice = deservedPrice;
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
