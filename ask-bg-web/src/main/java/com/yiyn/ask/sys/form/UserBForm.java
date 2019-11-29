package com.yiyn.ask.sys.form;

import java.math.BigDecimal;

import com.yiyn.ask.base.constants.ConsultingTypeEnum;
import com.yiyn.ask.base.constants.OrderSetEnum;
import com.yiyn.ask.base.constants.UserTypeEnum;
import com.yiyn.ask.base.constants.YesOrNoType;
import com.yiyn.ask.base.form.BaseForm;

public class UserBForm extends BaseForm{

	private static final long serialVersionUID = -6790539637785013005L;

	private String user_no;

	private String user_name;
	
	private String user_password;

	private String user_phone;
	
	private String bank_name;
	
	private String bank_account;
	
	private String user_id_num;

	private Integer user_type;

	private String user_headimg;

	private String work_year;

	private String user_desc;

	private Integer order_set;

	private Integer advice_type;

	private BigDecimal advice_val;

	private Integer advice_num;

	private String share_link;

	private String appendix_img;

	private String recommend;

	private Integer open_hours;

	private Integer recent_open_hours;

	private Integer revision;
	
	private String original_password;
	
	private Integer add_orders;
	
	private String is_hidden;
	
	private YesOrNoType[] yesOrNoTypes = YesOrNoType.values();
	
	private UserTypeEnum[] userTypes = UserTypeEnum.values();
	
	private OrderSetEnum[] orderSets = OrderSetEnum.values();
	
	private ConsultingTypeEnum[] consultingTypes = ConsultingTypeEnum.values();
	
	private String tags;
	
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_id_num() {
		return user_id_num;
	}

	public void setUser_id_num(String user_id_num) {
		this.user_id_num = user_id_num;
	}

	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}

	public String getUser_headimg() {
		return user_headimg;
	}

	public void setUser_headimg(String user_headimg) {
		this.user_headimg = user_headimg;
	}

	public String getWork_year() {
		return work_year;
	}

	public void setWork_year(String work_year) {
		this.work_year = work_year;
	}

	public String getUser_desc() {
		return user_desc;
	}

	public void setUser_desc(String user_desc) {
		this.user_desc = user_desc;
	}

	public Integer getOrder_set() {
		return order_set;
	}

	public void setOrder_set(Integer order_set) {
		this.order_set = order_set;
	}

	public Integer getAdvice_type() {
		return advice_type;
	}

	public void setAdvice_type(Integer advice_type) {
		this.advice_type = advice_type;
	}

	public BigDecimal getAdvice_val() {
		return advice_val;
	}

	public void setAdvice_val(BigDecimal advice_val) {
		this.advice_val = advice_val;
	}

	public Integer getAdvice_num() {
		return advice_num;
	}

	public void setAdvice_num(Integer advice_num) {
		this.advice_num = advice_num;
	}

	public String getShare_link() {
		return share_link;
	}

	public void setShare_link(String share_link) {
		this.share_link = share_link;
	}

	public String getAppendix_img() {
		return appendix_img;
	}

	public void setAppendix_img(String appendix_img) {
		this.appendix_img = appendix_img;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public Integer getOpen_hours() {
		return open_hours;
	}

	public void setOpen_hours(Integer open_hours) {
		this.open_hours = open_hours;
	}

	public Integer getRecent_open_hours() {
		return recent_open_hours;
	}

	public void setRecent_open_hours(Integer recent_open_hours) {
		this.recent_open_hours = recent_open_hours;
	}

	public Integer getRevision() {
		return revision;
	}

	public void setRevision(Integer revision) {
		this.revision = revision;
	}

	public UserTypeEnum[] getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(UserTypeEnum[] userTypes) {
		this.userTypes = userTypes;
	}

	public String getOriginal_password() {
		return original_password;
	}

	public void setOriginal_password(String original_password) {
		this.original_password = original_password;
	}

	public OrderSetEnum[] getOrderSets() {
		return orderSets;
	}

	public void setOrderSets(OrderSetEnum[] orderSets) {
		this.orderSets = orderSets;
	}

	public ConsultingTypeEnum[] getConsultingTypes() {
		return consultingTypes;
	}

	public void setConsultingTypes(ConsultingTypeEnum[] consultingTypes) {
		this.consultingTypes = consultingTypes;
	}

	public YesOrNoType[] getYesOrNoTypes() {
		return yesOrNoTypes;
	}

	public void setYesOrNoTypes(YesOrNoType[] yesOrNoTypes) {
		this.yesOrNoTypes = yesOrNoTypes;
	}

	public String getIs_hidden() {
		return is_hidden;
	}

	public void setIs_hidden(String is_hidden) {
		this.is_hidden = is_hidden;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public Integer getAdd_orders() {
		return add_orders;
	}

	public void setAdd_orders(Integer add_orders) {
		this.add_orders = add_orders;
	}
	
}
