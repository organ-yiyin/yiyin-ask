package com.yiyn.ask.market.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.yiyn.ask.market.form.AdForm;
import com.yiyn.ask.market.po.AdPo;

public class AdConvert {
	
	public static AdForm convertToForm(AdPo source) {
		if(source == null) {
			return null;
		}
		
		AdForm target = new AdForm();
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
	public static AdPo convertToPo(AdForm source) {
		if(source == null) {
			return null;
		}
		
		AdPo target = new AdPo();
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
	public static List<AdForm> listConvertToForm(List<AdPo> pos){
			
		if(pos == null) {
			return null;
		}
		
		List<AdForm> forms = new ArrayList<>();
		pos.forEach(e -> {
			forms.add(convertToForm(e));
		});
		return forms;
	}
}
