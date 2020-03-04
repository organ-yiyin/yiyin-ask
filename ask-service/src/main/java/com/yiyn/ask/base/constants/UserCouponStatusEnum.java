package com.yiyn.ask.base.constants;

public enum UserCouponStatusEnum {
	// 0：未使用 1：已使用 9：已过期
	NO_USERD("0"),USERD("1"),EXPIRE("9"),;
    
    private final String name;
    
    private UserCouponStatusEnum(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
