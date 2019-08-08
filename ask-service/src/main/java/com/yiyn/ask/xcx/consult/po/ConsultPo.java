package com.yiyn.ask.xcx.consult.po;

import com.yiyn.ask.base.po.BasePo;
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
	
	private String problem_desc;
	
	private String problem_type;
	
	private String problem_imgs;
	private String problem_videos;
	
	private String status;
	
	private String status_show;
	
	private ConsultRefPo refPo;
	
	private UserPo userPo;
	
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

	public String getProblem_videos() {
		return problem_videos;
	}

	public void setProblem_videos(String problem_videos) {
		this.problem_videos = problem_videos;
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
}