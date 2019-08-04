package com.yiyn.ask.sys.form;

import com.yiyn.ask.base.constants.UserTypeEnum;
import com.yiyn.ask.base.utils.PaginationUtils;

public class UserManagementForm extends PaginationUtils{
	
	private UserTypeEnum[] userTypes = UserTypeEnum.values();
	
	public UserManagementForm(){
		super();
	}

	public UserTypeEnum[] getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(UserTypeEnum[] userTypes) {
		this.userTypes = userTypes;
	}

}
