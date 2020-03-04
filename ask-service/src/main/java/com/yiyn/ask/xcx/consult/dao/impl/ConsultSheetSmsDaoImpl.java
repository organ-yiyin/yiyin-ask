package com.yiyn.ask.xcx.consult.dao.impl;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.consult.po.ConsultSheetSmsPo;
@Repository("consultSheetSmsDao")
public class ConsultSheetSmsDaoImpl extends BaseDao<ConsultSheetSmsPo> {

	public String getNameStatement() {
		return "yiyin.consultSheetSms";
	}
}
