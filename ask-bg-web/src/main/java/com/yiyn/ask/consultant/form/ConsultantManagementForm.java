package com.yiyn.ask.consultant.form;

import com.yiyn.ask.base.constants.UserTypeEnum;
import com.yiyn.ask.base.utils.PaginationUtils;

public class ConsultantManagementForm extends PaginationUtils{
	
	private UserTypeEnum[] userTypes = UserTypeEnum.values();
	
	public ConsultantManagementForm(){
		super();
	}

	public UserTypeEnum[] getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(UserTypeEnum[] userTypes) {
		this.userTypes = userTypes;
	}

}
