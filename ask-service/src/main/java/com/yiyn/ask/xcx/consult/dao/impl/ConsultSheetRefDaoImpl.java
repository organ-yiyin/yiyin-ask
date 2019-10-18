package com.yiyn.ask.xcx.consult.dao.impl;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.xcx.consult.po.ConsultSheetRefPo;
@Repository("consultSheetRefDao")
public class ConsultSheetRefDaoImpl extends BaseDao<ConsultSheetRefPo> {

	public String getNameStatement() {
		return "yiyin.consultSheetRef";
	}
}
