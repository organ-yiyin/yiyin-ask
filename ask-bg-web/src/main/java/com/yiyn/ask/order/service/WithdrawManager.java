package com.yiyn.ask.order.service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiyn.ask.base.constants.WithDrawStatusEnum;
import com.yiyn.ask.base.constants.WithDrawTypeEnum;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.base.utils.date.SPDateUtils;
import com.yiyn.ask.base.utils.excel.ExcelUtil;
import com.yiyn.ask.xcx.account.dao.impl.UserWithdrawDaoImpl;

@Service
public class WithdrawManager {
	
	@Autowired
	private UserWithdrawDaoImpl userWithdrawDao;
	
	public Workbook downloadWithdrawExcel(PaginationUtils paramPage) throws Exception{
		
		List<Map> pos =  this.userWithdrawDao.searchByConditions_bg(paramPage);
		
		InputStream stream = WithdrawManager.class.getResourceAsStream("/template/withdraw.xls");
		Workbook workbook = ExcelUtil.initExcel(stream, ExcelUtil.EXCEL_2003 );
		
		Sheet valSheet = workbook.getSheetAt(0);
		CellStyle numberCellStyle = ExcelUtil.generateNumberCellStyle(workbook, false);
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short)10);
		font.setFontName("宋体");
		numberCellStyle.setFont(font);
		
		for(int i=0; i<pos.size(); i++){
			Map dataMap =pos.get(i);
			
			Row row = ExcelUtil.getRow(valSheet, i + 1);
			
			int cellIndex = 0;
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("ub_user_no"));
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("ub_user_name"));
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("ub_user_id_num"));
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("ub_bank_name"));
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("ub_bank_account"));
			Cell withdrawCell = ExcelUtil.getCell(row, cellIndex++);
			withdrawCell.setCellStyle(numberCellStyle);
			withdrawCell.setCellValue(((BigDecimal)dataMap.get("WITHDRAW")).doubleValue());
			
			Cell withdrawActCell = ExcelUtil.getCell(row, cellIndex++);
			withdrawActCell.setCellStyle(numberCellStyle);
			withdrawActCell.setCellValue(((BigDecimal)dataMap.get("WITHDRAW_ACT")).doubleValue());
			
			WithDrawTypeEnum withdrawType = WithDrawTypeEnum.findByCode((String)dataMap.get("WITHDRAW_TYPE"));
			ExcelUtil.setCellStringValue(row, cellIndex++, withdrawType == null ? "" : withdrawType.getName());
			
			WithDrawStatusEnum withdrawStatus = WithDrawStatusEnum.findByCode((Integer)dataMap.get("STATUS"));
			ExcelUtil.setCellStringValue(row, cellIndex++, withdrawStatus == null ? "" : withdrawStatus.getName());
			
			ExcelUtil.setCellStringValue(row, cellIndex++, SPDateUtils.formatDateTimeDefault((Date)dataMap.get("CREATED_TIME")));
			ExcelUtil.setCellStringValue(row, cellIndex++, SPDateUtils.formatDateTimeDefault((Date)dataMap.get("UPDATED_TIME")));
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("UPDATED_BY"));
		}
		
		return workbook;
	}
}
