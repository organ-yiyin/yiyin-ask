package com.yiyn.ask.base.constants;

/**
 * 咨询类型
 * @author Administrator
 *
 */
public enum ConsultingTypeEnum {
	
	BUYU(1,"哺乳"),ZAOCHANER(2,"早产儿"),ALL(9,"所有");
	
	Integer code;
	String name;
	
	private ConsultingTypeEnum(Integer code, String name){
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static ConsultingTypeEnum findEnumByCode(Integer code) {
		if(code == null) {
			return null;
		}
		for(ConsultingTypeEnum status : ConsultingTypeEnum.values()) {
			if(status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}
	
}
