package com.yiyn.ask.base.constants;

public enum WithDrawStatusEnum {
	// 提现状态 
	// 1：待审核
	// 2：已审核（自动打款，微信通知或者财务通知后状态变更）
	// 3：已打款
	// 4：驳回
	REVIEW_WAIT("1","待审核"), REVIEW("2","已审核"),PAYMENT("3","已打款"), REJECT("4","驳回");
	
	String code;
	String name;
	
	private WithDrawStatusEnum(String code, String name){
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
