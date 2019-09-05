package com.yiyn.ask.base.constants;

import org.apache.commons.lang3.StringUtils;

public enum YesOrNoType {
	
	YES("Y","是"),NO("N","否");
	
	String code;
	String text;
	
	private YesOrNoType(String code, String text){
		this.code = code;
		this.text = text;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public static YesOrNoType[] generateNoAndYesOrder() {
		YesOrNoType[] array = {NO,YES};
		
		return array;
	}
	
	public static YesOrNoType getByValue(String pValue){
		if(StringUtils.isEmpty(pValue)) {
			return null;
		}
		
		for(YesOrNoType cs : YesOrNoType.values()){
			if(cs.getCode().equals(pValue)){
				return cs;
			}
		}
		
		return null;
	}
}
