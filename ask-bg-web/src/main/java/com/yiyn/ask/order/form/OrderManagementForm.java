package com.yiyn.ask.order.form;

import com.yiyn.ask.base.constants.ConsultStatuEnum;
import com.yiyn.ask.base.utils.PaginationUtils;

public class OrderManagementForm extends PaginationUtils {

	private ConsultStatuEnum[] consultStatus = ConsultStatuEnum.values();

	public ConsultStatuEnum[] getConsultStatus() {
		return consultStatus;
	}

	public void setConsultStatus(ConsultStatuEnum[] consultStatus) {
		this.consultStatus = consultStatus;
	}

}
