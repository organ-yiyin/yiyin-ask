package com.yiyn.ask.order.service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiyn.ask.base.constants.ConsultStatuEnum;
import com.yiyn.ask.base.constants.DistributorSourceEnum;
import com.yiyn.ask.base.constants.GenderEnum;
import com.yiyn.ask.base.constants.UserTypeEnum;
import com.yiyn.ask.base.utils.PaginationUtils;
import com.yiyn.ask.base.utils.date.SPDateUtils;
import com.yiyn.ask.base.utils.excel.ExcelUtil;
import com.yiyn.ask.xcx.center.dao.impl.CodeDaoImpl;
import com.yiyn.ask.xcx.center.po.CodePo;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultantSheetBgDaoImpl;

@Service
public class OrderManager {
	
	@Resource(name = "consultSheetDao_bg")
	private ConsultantSheetBgDaoImpl consultantSheetBgDao;
	
	@Autowired
	private CodeDaoImpl codeDao;
	
	public Workbook downloadOrdersExcel(PaginationUtils paramPage) throws Exception{
		
		List<CodePo> qus_types = codeDao.findCodeList("QUS_TYPE");
		List<Map> pos = this.consultantSheetBgDao.searchByConditions_bg(paramPage);
		
		InputStream stream = WithdrawManager.class.getResourceAsStream("/template/order.xls");
		Workbook workbook = ExcelUtil.initExcel(stream, ExcelUtil.EXCEL_2003 );
		
		Sheet valSheet = workbook.getSheetAt(0);
		CellStyle numberCellStyle = ExcelUtil.generateNumberCellStyle(workbook, false);
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short)10);
		font.setFontName("宋体");
		numberCellStyle.setFont(font);
		
		for(int i=0; i<pos.size(); i++){
			Map dataMap =pos.get(i);
			
			Row row = ExcelUtil.getRow(valSheet, i + 2);
			
			int cellIndex = 0;
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("c_user_phone"));
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("ucr_name_m"));
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("ucr_birthday_m"));
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("ucr_special_m"));
			
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("ucr_name_b"));
			String ucr_sex_b = (String)dataMap.get("ucr_sex_b");
			if(StringUtils.isEmpty(ucr_sex_b)) {
				ExcelUtil.setCellStringValue(row, cellIndex++, "");
			}
			else {
				GenderEnum gender = GenderEnum.findEnumByCode(Integer.valueOf(ucr_sex_b));
				ExcelUtil.setCellStringValue(row, cellIndex++, gender == null ? "" : gender.getName());
			}
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("ucr_birthday_b"));
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("ucr_edc_b"));
			String ucr_birth_weight_b = (String)dataMap.get("ucr_birth_weight_b");
			ExcelUtil.setCellStringValue(row, cellIndex++, ucr_birth_weight_b);
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("ucr_special_b"));
			
			Date c_reg_time = (Date)dataMap.get("c_reg_time");
			ExcelUtil.setCellStringValue(row, cellIndex++, SPDateUtils.formatDateTimeDefault(c_reg_time));

			Cell orderIndex = ExcelUtil.getCell(row, cellIndex++);
			orderIndex.setCellStyle(numberCellStyle);
			orderIndex.setCellValue(((Long)dataMap.get("ID")).toString());
			
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("ODD_NUM"));
			
			ConsultStatuEnum orderStatus = ConsultStatuEnum.buildConsulantStatusByCode((String)dataMap.get("STATUS"));
			ExcelUtil.setCellStringValue(row, cellIndex++, orderStatus == null ? "" : orderStatus.getName());
			
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("b_user_name"));
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("b_user_phone"));
			
			Date created_time = (Date)dataMap.get("CREATED_TIME");
			ExcelUtil.setCellStringValue(row, cellIndex++, SPDateUtils.formatDateTimeDefault(created_time));
			Date pay_time = (Date)dataMap.get("PAY_TIME");
			ExcelUtil.setCellStringValue(row, cellIndex++, SPDateUtils.formatDateTimeDefault(pay_time));
			Date refund_time = (Date)dataMap.get("REFUND_TIME");
			ExcelUtil.setCellStringValue(row, cellIndex++, SPDateUtils.formatDateTimeDefault(refund_time));
			
			// 订单金额
			BigDecimal price = BigDecimal.valueOf((Double)dataMap.get("PRICE"));
			Cell priceCell = ExcelUtil.getCell(row, cellIndex++);
			priceCell.setCellStyle(numberCellStyle);
			priceCell.setCellValue(price.doubleValue());
			
			// 优惠金额
			//Integer couponAmount = dataMap.get("ucc_amount") == null ? 0 : (Integer)dataMap.get("ucc_amount");
			Cell couponAmountCell = ExcelUtil.getCell(row, cellIndex++);
			couponAmountCell.setCellStyle(numberCellStyle);
			couponAmountCell.setCellValue((Integer)dataMap.get("DISCOUNT"));
			
			// 实际支付
			BigDecimal user_pay_money = BigDecimal.valueOf((Double)dataMap.get("USER_PAY_MONEY"));
			Cell actualAmountCell = ExcelUtil.getCell(row, cellIndex++);
			actualAmountCell.setCellStyle(numberCellStyle);
			actualAmountCell.setCellValue(user_pay_money.doubleValue());
			
			Cell cPriceCell = ExcelUtil.getCell(row, cellIndex++);
			cPriceCell.setCellStyle(numberCellStyle);
			UserTypeEnum userTypeEnum = UserTypeEnum.findEnumByCode((Integer)dataMap.get("b_user_type"));
			//BigDecimal cPrice = price.multiply(NumberUtils.createBigDecimal("0.7"));
			BigDecimal cPrice = price.multiply(userTypeEnum.getPercent());
			cPriceCell.setCellValue(cPrice.doubleValue());
			
			ExcelUtil.setCellStringValue(row, cellIndex++, "微信支付");
			String sdis_source = (String)dataMap.get("sdis_source");
			DistributorSourceEnum disSource = DistributorSourceEnum.findByCode(sdis_source);
			ExcelUtil.setCellStringValue(row, cellIndex++, disSource == null ? "" : disSource.getName());
			ExcelUtil.setCellStringValue(row, cellIndex++, (String)dataMap.get("sdis_dis_name"));
			
			CodePo codePo = qus_types.stream().filter(e-> e.getValue().equals((String)dataMap.get("PROBLEM_TYPE"))).findFirst().get();
			ExcelUtil.setCellStringValue(row, cellIndex++, codePo == null ? "" : codePo.getName());
		}
		
		return workbook;
	}
}
