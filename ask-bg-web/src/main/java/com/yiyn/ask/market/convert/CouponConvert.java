package com.yiyn.ask.market.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.yiyn.ask.base.utils.date.SPDateUtils;
import com.yiyn.ask.market.form.CouponForm;
import com.yiyn.ask.market.po.CouponPo;

public class CouponConvert {
	
	public static CouponForm convertToForm(CouponPo source) {
		if(source == null) {
			return null;
		}
		
		CouponForm target = new CouponForm();
		BeanUtils.copyProperties(source, target, new String[] {"start_date","end_date","post_start","post_end"});
		target.setStart_date(SPDateUtils.formatDateDefault(source.getStart_date()));
		target.setEnd_date(SPDateUtils.formatDateDefault(source.getEnd_date()));
		target.setPost_start(SPDateUtils.formatDateDefault(source.getPost_start()));
		target.setPost_end(SPDateUtils.formatDateDefault(source.getPost_end()));
		
		return target;
	}
	
	public static CouponPo convertToPo(CouponForm source) {
		if(source == null) {
			return null;
		}
		
		CouponPo target = new CouponPo();
		BeanUtils.copyProperties(source, target, new String[] {"start_date","end_date","post_start","post_end"});
		target.setStart_date(SPDateUtils.parseDateDefault(source.getStart_date()));
		target.setEnd_date(SPDateUtils.parseDateDefault(source.getEnd_date()));
		target.setPost_start(SPDateUtils.parseDateDefault(source.getPost_start()));
		target.setPost_end(SPDateUtils.parseDateDefault(source.getPost_end()));
		return target;
	}
	
	public static List<CouponForm> listConvertToForm(List<CouponPo> pos){
			
		if(pos == null) {
			return null;
		}
		
		List<CouponForm> forms = new ArrayList<>();
		pos.forEach(e -> {
			forms.add(convertToForm(e));
		});
		return forms;
	}
}
