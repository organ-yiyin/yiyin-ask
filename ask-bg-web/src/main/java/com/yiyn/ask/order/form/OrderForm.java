package com.yiyn.ask.order.form;

import com.yiyn.ask.base.constants.ConsultStatuEnum;

public class OrderForm {
	
	private ConsultStatuEnum[] consultStatusList = ConsultStatuEnum.values();

	public ConsultStatuEnum[] getConsultStatusList() {
		return consultStatusList;
	}

	public void setConsultStatusList(ConsultStatuEnum[] consultStatusList) {
		this.consultStatusList = consultStatusList;
	}

}
