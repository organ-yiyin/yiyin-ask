package com.yiyn.ask.xcx.user.po;

import java.util.List;

import com.yiyn.ask.base.po.BasePo;
import com.yiyn.ask.base.utils.StringUtils;

public class UserPo extends BasePo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_no;
	
	private String user_name;
	
	private String user_password;
	
	private String user_phone;
	
	private String user_type;
	
	private String user_headimg;
	
	private String work_year;
	private String user_desc;
	private String order_set;//接单设置
	private String skilled;
	private List<UserTagPo> tagList;
	private String advice_val;// 咨询单价
	private String advice_num;// 咨询数量
	private String advice_type; // 咨询类型
	private String type; // 更新数据库的类型
	
	private String consultEval; // 评价---实时算出
	private String consultCount; // 咨询量
	
	private String share_link; // 二维码分享链接
	private String is_hidden;// 是否隐藏
	private String advice_num_today;//统计今日接单量
	private String open_id; // B端用户小程序微信唯一标识
	private String unionid; // 关联id，多个移动公用一个
	
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getOpen_id() {
		return open_id;
	}
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	public String getShare_link() {
		return share_link;
	}
	public void setShare_link(String share_link) {
		this.share_link = share_link;
	}
	public String getConsultEval() {
		return StringUtils.subZeroAndDot(consultEval);
	}
	public void setConsultEval(String consultEval) {
		this.consultEval = StringUtils.subZeroAndDot(consultEval);
	}
	public String getConsultCount() {
		return consultCount;
	}
	public void setConsultCount(String consultCount) {
		this.consultCount = consultCount;
	}
	public String getSkilled() {
		return skilled;
	}
	public void setSkilled(String skilled) {
		this.skilled = skilled;
	}
	public String getAdvice_val() {
		return StringUtils.subZeroAndDot(advice_val);
	}
	public void setAdvice_val(String advice_val) {
		this.advice_val = StringUtils.subZeroAndDot(advice_val);
	}
	public String getAdvice_num() {
		return advice_num;
	}
	public void setAdvice_num(String advice_num) {
		this.advice_num = StringUtils.subZeroAndDot(advice_num);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrder_set() {
		return order_set;
	}
	public void setOrder_set(String order_set) {
		this.order_set = order_set;
	}
	public List<UserTagPo> getTagList() {
		return tagList;
	}
	public void setTagList(List<UserTagPo> tagList) {
		this.tagList = tagList;
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
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
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
	public String getIs_hidden() {
		return is_hidden;
	}
	public void setIs_hidden(String is_hidden) {
		this.is_hidden = is_hidden;
	}
	public String getAdvice_type() {
		return advice_type;
	}
	public void setAdvice_type(String advice_type) {
		this.advice_type = advice_type;
	}
	public String getAdvice_num_today() {
		return advice_num_today;
	}
	public void setAdvice_num_today(String advice_num_today) {
		this.advice_num_today = advice_num_today;
	}
}
