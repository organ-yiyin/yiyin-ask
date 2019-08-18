package com.yiyn.ask.sys.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.yiyn.ask.sys.form.UserForm;
import com.yiyn.ask.sys.po.UserPo;

public class UserConvert {
	
	public static UserForm convertToForm(UserPo source) {
		if(source == null) {
			return null;
		}
		
		UserForm target = new UserForm();
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
	public static UserPo convertToPo(UserForm source) {
		if(source == null) {
			return null;
		}
		
		UserPo target = new UserPo();
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
	public static List<UserForm> listConvertToForm(List<UserPo> pos){
			
		if(pos == null) {
			return null;
		}
		
		List<UserForm> forms = new ArrayList<>();
		pos.forEach(e -> {
			forms.add(convertToForm(e));
		});
		return forms;
	}
}
