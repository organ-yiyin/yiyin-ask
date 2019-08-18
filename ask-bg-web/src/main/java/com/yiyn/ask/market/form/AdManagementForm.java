package com.yiyn.ask.market.form;

import com.yiyn.ask.base.constants.AdPositionEnum;
import com.yiyn.ask.base.utils.PaginationUtils;

public class AdManagementForm extends PaginationUtils{
	
	private AdPositionEnum[] adPositions = AdPositionEnum.values();
	
	public AdManagementForm(){
		super();
	}

	public AdPositionEnum[] getAdPositions() {
		return adPositions;
	}

	public void setAdPositions(AdPositionEnum[] adPositions) {
		this.adPositions = adPositions;
	}

}
