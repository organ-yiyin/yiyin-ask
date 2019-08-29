package com.yiyn.ask.base.constants;

public enum ProcessSendTypeEnum {
	
	SERVER("server"),CUSTOMER("customer");
    private final String name;
    
    private ProcessSendTypeEnum(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
