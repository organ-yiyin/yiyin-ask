package com.yiyn.ask.consultant.service;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiyn.ask.base.constants.ConsultingTypeEnum;
import com.yiyn.ask.base.constants.UserTypeEnum;
import com.yiyn.ask.base.constants.YesOrNoType;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.base.utils.excel.ExcelUtil;
import com.yiyn.ask.order.service.WithdrawManager;
import com.yiyn.ask.sys.dao.impl.UserBDaoImpl;
import com.yiyn.ask.sys.po.UserBPo;
import com.yiyn.ask.xcx.user.dao.impl.UserTagDaoImpl;
import com.yiyn.ask.xcx.user.po.UserTagPo;

@Service
public class ConsultantManager {
	
	@Resource(name="userBDao_bg")
	private UserBDaoImpl userBDao;
	
	@Autowired
	private UserTagDaoImpl userTagDao;
	
	public Workbook downloadWithdrawExcel(PaginationUtils paramPage) throws Exception{
		
		List<UserBPo> pos =  this.userBDao.searchByConditions(paramPage);
		
		InputStream stream = WithdrawManager.class.getResourceAsStream("/template/user_b.xls");
		Workbook workbook = ExcelUtil.initExcel(stream, ExcelUtil.EXCEL_2003 );
		
		Sheet valSheet = workbook.getSheetAt(0);
		CellStyle numberCellStyle = ExcelUtil.generateNumberCellStyle(workbook, false);
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short)10);
		font.setFontName("宋体");
		numberCellStyle.setFont(font);
		
		for(int i=0; i<pos.size(); i++){
			UserBPo userBPo =pos.get(i);
			
			Row row = ExcelUtil.getRow(valSheet, i + 1);
			
			int cellIndex = 0;
			ExcelUtil.setCellStringValue(row, cellIndex++, userBPo.getUser_no());
			ExcelUtil.setCellStringValue(row, cellIndex++, userBPo.getUser_name());
			ExcelUtil.setCellStringValue(row, cellIndex++, userBPo.getUser_phone());
			ExcelUtil.setCellStringValue(row, cellIndex++, userBPo.getUser_id_num());
			ExcelUtil.setCellStringValue(row, cellIndex++, userBPo.getBank_name());
			
			ExcelUtil.setCellStringValue(row, cellIndex++, userBPo.getBank_account());
			ExcelUtil.setCellStringValue(row, cellIndex++, userBPo.getWork_year());
			
			UserTypeEnum userType = UserTypeEnum.findEnumByCode(userBPo.getUser_type());
			ExcelUtil.setCellStringValue(row, cellIndex++, userType == null ? "" : userType.getName());
			ConsultingTypeEnum consultingType = ConsultingTypeEnum.findEnumByCode(userBPo.getAdvice_type());
			ExcelUtil.setCellStringValue(row, cellIndex++, consultingType == null ? "" : consultingType.getName());
			ExcelUtil.setCellStringValue(row, cellIndex++, userBPo.getAdvice_val() == null ? "" : userBPo.getAdvice_val().toPlainString());
			ExcelUtil.setCellStringValue(row, cellIndex++, userBPo.getAdvice_num() == null ? "" : userBPo.getAdvice_num().toString());
			
			YesOrNoType recommend = YesOrNoType.getByValue(userBPo.getRecommend());
			ExcelUtil.setCellStringValue(row, cellIndex++, recommend == null ? "" : recommend.getText());
			
			YesOrNoType is_hidden = YesOrNoType.getByValue(userBPo.getIs_hidden());
			ExcelUtil.setCellStringValue(row, cellIndex++, is_hidden == null ? "" : is_hidden.getText());
			
			List<UserTagPo> tagList = userTagDao.getUserTagList(userBPo.getUser_no());
			List<String> collect = tagList.stream().map(UserTagPo::getName).collect(Collectors.toList());
			String tags = String.join(",", collect);
			ExcelUtil.setCellStringValue(row, cellIndex++, tags);
			
		}
		
		return workbook;
	}
	
}
