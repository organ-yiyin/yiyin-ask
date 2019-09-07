package com.yiyn.ask.base.convert;

import org.springframework.beans.BeanUtils;

import com.yiyn.ask.base.form.AttachmentForm;
import com.yiyn.ask.base.po.AttachmentPo;

public class AttachmentConvert {
	
	public static AttachmentPo convertToPo(AttachmentForm source) {
		if(source == null) {
			return null;
		}
		
		AttachmentPo target = new AttachmentPo();
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
}
