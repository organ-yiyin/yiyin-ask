package com.yiyn.ask.customer.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiyn.ask.base.constants.GenderEnum;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.base.utils.date.SPDateUtils;
import com.yiyn.ask.base.utils.excel.ExcelUtil;
import com.yiyn.ask.order.service.WithdrawManager;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultRefDaoImpl;

@Service
public class CustomerManager {
	
	@Autowired
	private ConsultRefDaoImpl consultRefDao;
	
	public Workbook downloadCustomers(PaginationUtils paramPage) throws Exception{
		
		List<Map> consultRefPos = this.consultRefDao.searchByConditions_bg(paramPage);
		
		InputStream stream = WithdrawManager.class.getResourceAsStream("/template/user_c_ref.xls");
		Workbook workbook = ExcelUtil.initExcel(stream, ExcelUtil.EXCEL_2003 );
		
		Sheet valSheet = workbook.getSheetAt(0);
		CellStyle numberCellStyle = ExcelUtil.generateNumberCellStyle(workbook, false);
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short)10);
		font.setFontName("宋体");
		numberCellStyle.setFont(font);
		
		for(int i=0; i<consultRefPos.size(); i++){
			Map dataMap =consultRefPos.get(i);
			
			Row row = ExcelUtil.getRow(valSheet, i + 1);
			
			int cellIndex = 0;
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("uc_user_phone"));
			Date uc_reg_time = (Date)dataMap.get("uc_reg_time");
			ExcelUtil.setCellStringValue(row, cellIndex++, SPDateUtils.formatDateTimeDefault(uc_reg_time));
			
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("NAME_M"));
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("BIRTHDAY_M"));
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("SPECIAL_M"));
			
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("NAME_B"));
			GenderEnum gender = GenderEnum.findEnumByCode(Integer.parseInt((String)dataMap.get("SEX_B")));
			ExcelUtil.setCellStringValue(row, cellIndex++, gender == null ? "" : gender.getName());
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("BIRTHDAY_B"));
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("EDC_B"));
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("BIRTH_WEIGHT_B"));
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("SPECIAL_B"));
		}
		
		return workbook;
	}
}
