package com.yiyn.ask.order.convert;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.yiyn.ask.order.form.ConsultationSheetForm;
import com.yiyn.ask.xcx.consult.po.ConsultPo;

public class ConsultationSheetConvert {
	
	public static ConsultationSheetForm convertToForm(ConsultPo source) {
		if(source == null) {
			return null;
		}
		
		ConsultationSheetForm target = new ConsultationSheetForm();
		BeanUtils.copyProperties(source, target);
		if(StringUtils.isNotEmpty(source.getProblem_imgs())){
			target.setProblem_img_list(source.getProblem_imgs().split(","));
		}
		if(StringUtils.isNotEmpty(source.getProblem_video())) {
			target.setProblem_video_list(source.getProblem_video().split(","));
		}
		
		return target;
	}
	
	public static ConsultPo convertToPo(ConsultationSheetForm source) {
		if(source == null) {
			return null;
		}
		
		ConsultPo target = new ConsultPo();
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
	public static List<ConsultationSheetForm> listConvertToForm(List<ConsultPo> pos){
			
		if(pos == null) {
			return null;
		}
		
		List<ConsultationSheetForm> forms = new ArrayList<>();
		pos.forEach(e -> {
			forms.add(convertToForm(e));
		});
		return forms;
	}
}
