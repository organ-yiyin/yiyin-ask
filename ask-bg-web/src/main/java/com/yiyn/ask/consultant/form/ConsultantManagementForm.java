package com.yiyn.ask.consultant.form;

import com.yiyn.ask.base.constants.ConsultingTypeEnum;
import com.yiyn.ask.base.constants.OrderSetEnum;
import com.yiyn.ask.base.constants.UserTypeEnum;
import com.yiyn.ask.base.utils.PaginationUtils;

public class ConsultantManagementForm extends PaginationUtils{
	
	private UserTypeEnum[] userTypes = UserTypeEnum.values();
	
	private OrderSetEnum[] orderSets = OrderSetEnum.values();
	
	private ConsultingTypeEnum[] consultingTypes = ConsultingTypeEnum.values();
	
	public ConsultantManagementForm(){
		super();
	}

	public UserTypeEnum[] getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(UserTypeEnum[] userTypes) {
		this.userTypes = userTypes;
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

}
