package com.yiyn.ask.consultant.convert;

import org.springframework.beans.BeanUtils;

import com.yiyn.ask.base.po.AttachmentPo;
import com.yiyn.ask.consultant.form.ConsultantAttachmentForm;

public class ConsultantAttachmentConvert {
	
	public static AttachmentPo convertToPo(ConsultantAttachmentForm source) {
		if(source == null) {
			return null;
		}
		
		AttachmentPo target = new AttachmentPo();
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
}
