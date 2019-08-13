package com.yiyn.ask.base.constants;

public enum SourceEnum {
	// 微信公众号   支付宝小程序  微信小程序
	WXGZH("10"),ZFBXCX("20"),WXXCX("30"),;
    
    private final String name;
    
    private SourceEnum(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
