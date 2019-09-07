package com.yiyn.ask.order.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.yiyn.ask.order.form.UserEvalForm;
import com.yiyn.ask.xcx.user.po.UserEvalPo;

public class UserEvalConvert {
	
	public static UserEvalForm convertToForm(UserEvalPo source) {
		if(source == null) {
			return null;
		}
		
		UserEvalForm target = new UserEvalForm();
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
	public static UserEvalPo convertToPo(UserEvalForm source) {
		if(source == null) {
			return null;
		}
		
		UserEvalPo target = new UserEvalPo();
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
	public static List<UserEvalForm> listConvertToForm(List<UserEvalPo> pos){
			
		if(pos == null) {
			return null;
		}
		
		List<UserEvalForm> forms = new ArrayList<>();
		pos.forEach(e -> {
			forms.add(convertToForm(e));
		});
		return forms;
	}
}
