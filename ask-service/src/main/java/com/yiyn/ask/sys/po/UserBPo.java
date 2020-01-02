package com.yiyn.ask.sys.po;

import java.math.BigDecimal;

import com.yiyn.ask.base.po.BasePo;

public class UserBPo extends BasePo {

	private static final long serialVersionUID = -7907500792410918167L;
	
	// 用户编号 登录号
	private String user_no;
	// 用户姓名
	private String user_name;
	// 用户密码 
	private String user_password;
	// 用户手机
	private String user_phone;
	// 身份证号
	private String user_id_num;
	// 开户行
	private String bank_name;
	// 银行账号
	private String bank_account;
	// 咨询师性质(1：内部 2：外部)
	private Integer user_type;
	// 用户头像
	private String user_headimg;
	// 从业年限
	private String work_year;
	// 用户简介
	private String user_desc;
	// 接单设置 1：不接单 2：接单
	private Integer order_set;
	// 咨询类型 1：哺育 ：2：泌乳 3：早产儿 9：所有
	private Integer advice_type;
	// 咨询单价
	private BigDecimal advice_val;
	// 咨询限量
	private Integer advice_num;
	// 二维码分享链接
	private String share_link;
	// 附件 附件上传（如资质证明材料，合同附件等）
	private String appendix_img;
	// 大咖推荐Y：推荐 N：不推荐
	private String recommend;
	// 开业时长 开业时长统计，按周期来（当点击类型为歇业的时候，统计上一次更新时间和当前的时间差，算出开业时长，累加进去）
	private Integer open_hours;
	// 最近开业时长 最近一次开业时长
	private Integer recent_open_hours;
	// 是否隐藏(Y:是,N:否)
	private String is_hidden;
	// 附加订单数（刷单用）
	private Integer add_orders;

	private Integer revision;

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

	public Integer getRevision() {
		return revision;
	}

	public void setRevision(Integer revision) {
		this.revision = revision;
	}

	public Integer getRecent_open_hours() {
		return recent_open_hours;
	}

	public void setRecent_open_hours(Integer recent_open_hours) {
		this.recent_open_hours = recent_open_hours;
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
