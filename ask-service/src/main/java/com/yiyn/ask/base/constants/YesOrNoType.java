package com.yiyn.ask.base.constants;

public enum YesOrNoType {
	
	YES("Y","是"),NO("N","否");
	
	String value;
	String text;
	
	private YesOrNoType(String value, String text){
		this.value = value;
		this.text = text;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
		
		for(YesOrNoType cs : YesOrNoType.values()){
			if(cs.getValue().equals(pValue)){
				return cs;
			}
		}
		
		return null;
	}
}
