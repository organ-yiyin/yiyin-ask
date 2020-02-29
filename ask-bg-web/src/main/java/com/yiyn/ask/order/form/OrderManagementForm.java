package com.yiyn.ask.order.form;

import com.yiyn.ask.base.constants.ConsultStatuEnum;
import com.yiyn.ask.base.constants.DistributorSourceEnum;
import com.yiyn.ask.base.utils.PaginationUtils;

public class OrderManagementForm extends PaginationUtils {

	private ConsultStatuEnum[] consultStatusList = ConsultStatuEnum.values();
	
	private DistributorSourceEnum[] distributorSourceList = DistributorSourceEnum.values();

	public ConsultStatuEnum[] getConsultStatusList() {
		return consultStatusList;
	}

	public void setConsultStatusList(ConsultStatuEnum[] consultStatusList) {
		this.consultStatusList = consultStatusList;
	}

	public DistributorSourceEnum[] getDistributorSourceList() {
		return distributorSourceList;
	}

	public void setDistributorSourceList(DistributorSourceEnum[] distributorSourceList) {
		this.distributorSourceList = distributorSourceList;
	}
	
}
