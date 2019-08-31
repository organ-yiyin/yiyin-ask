package com.yiyn.ask.base.constants;

/**
 * oss上文件夹名字
 * @author Administrator
 *
 */
public enum AttachmenetBucketEnum {
	
	CONSULTANT_ATTACHMENT(1, "咨询师附件","咨询师附件"),
	ORDER_ATTACHMENT(2, "订单附件","订单附件");
	
	Integer code;
	String name;
	String folder;
	
	private AttachmenetBucketEnum(Integer code, String name, String folder){
		this.code = code;
		this.name = name;
		this.folder = folder;
	}
	
	public static AttachmenetBucketEnum findAttachmenetBucketByCode(Integer code) {
		
		for(AttachmenetBucketEnum bucket : AttachmenetBucketEnum.values()) {
			if(bucket.getCode().equals(code)) {
				return bucket;
			}
		}
		return null;
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

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

}
