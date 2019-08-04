package com.yiyn.ask.sys.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.yiyn.ask.sys.form.UserBForm;
import com.yiyn.ask.sys.po.UserBPo;

public class UserBConvert {
	
	public static UserBForm convertToForm(UserBPo source) {
		if(source == null) {
			return null;
		}
		
		UserBForm target = new UserBForm();
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
	public static UserBPo convertToPo(UserBForm source) {
		if(source == null) {
			return null;
		}
		
		UserBPo target = new UserBPo();
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
	public static List<UserBForm> listConvertToForm(List<UserBPo> pos){
			
		if(pos == null) {
			return null;
		}
		
		List<UserBForm> forms = new ArrayList<>();
		pos.forEach(e -> {
			forms.add(convertToForm(e));
		});
		return forms;
	}
}
