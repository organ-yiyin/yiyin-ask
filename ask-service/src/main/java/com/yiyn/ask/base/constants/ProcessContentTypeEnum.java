package com.yiyn.ask.base.constants;

public enum ProcessContentTypeEnum {
	
//	text：文字
//	image：图片
//	vedio：视频
	TEXT("text"),IMG("image"),vedio("vedio");
    private final String name;
    
    private ProcessContentTypeEnum(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
