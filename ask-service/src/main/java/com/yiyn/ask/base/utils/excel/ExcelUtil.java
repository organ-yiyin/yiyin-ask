package com.yiyn.ask.base.utils.excel;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static final String EXCEL_2003 = "2003";
    public static final String EXCEL_2007 = "2007";

    public static Workbook initExcel(InputStream is, String version) throws Exception {

        // 初始化workbooks
        org.apache.poi.ss.usermodel.Workbook workbook = null;

        // Excel2003的场合
        if (EXCEL_2003.equals(version)) {
            workbook = new HSSFWorkbook(is);
        }
        // Excel2007的场合
        else if (EXCEL_2007.equals(version)) {
            workbook = new XSSFWorkbook(is);
        }

        return workbook;
    }

    public static Workbook createExcel() throws Exception {

        org.apache.poi.ss.usermodel.Workbook workbook = new HSSFWorkbook();

        return workbook;
    }

    public static Row getRow(Sheet sheet, int rowIndex) {
        return sheet.getRow(rowIndex) == null ? sheet.createRow(rowIndex) : sheet.getRow(rowIndex);
    }

    public static Cell getCell(Row row, int cellIndex) {
        return row.getCell(cellIndex) == null ? row.createCell(cellIndex) : row.getCell(cellIndex);
    }

    public static void setCellStringValue(Row row, int cellIndex, String value) {
        Cell cell = getCell(row, cellIndex);
        cell.setCellValue(value == null ? "" : value);
    }

    public static String getCellStringValue(Cell cell) {

        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return cell.getStringCellValue() == null ? "" : cell.getStringCellValue().trim();
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                Date theDate = cell.getDateCellValue();
                SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
                return dff.format(theDate);
            } else {
                return String.valueOf(Double.valueOf(cell.getNumericCellValue()).longValue());
            }

        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            return String.valueOf(cell.getNumericCellValue());
        }

        return "";
    }

    public static Date getCellDateValue(Cell cell) {

        if (DateUtil.isCellDateFormatted(cell) && cell.getDateCellValue() != null) {
            return cell.getDateCellValue();
        }

        return null;
    }

    public static double getCellDoubleValue(Cell cell) {

        String val = getCellStringValue(cell);

        if (StringUtils.isBlank(val)) {
            return 0;
        } else {
            return Double.parseDouble(val);
        }
    }

    /**
     * @param workbook
     */
    public static CellStyle generateCharacterCellStyle(Workbook workbook, boolean isTotal) {

        CellStyle cellstyle = workbook.createCellStyle();
        cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellstyle.setBottomBorderColor(HSSFColor.BLACK.index);
        cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellstyle.setLeftBorderColor(HSSFColor.BLACK.index);
        cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellstyle.setRightBorderColor(HSSFColor.BLACK.index);
        cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellstyle.setTopBorderColor(HSSFColor.BLACK.index);

        if (isTotal) {
            cellstyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cellstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        }

        return cellstyle;
    }

    public static CellStyle generateNumberCellStyle(Workbook workbook, boolean isTotal) {

        CellStyle cellstyle = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();

        cellstyle.setDataFormat(format.getFormat("#,##0.00"));
        cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        //cellstyle.setBorderBottom( HSSFCellStyle.BORDER_THIN );
        //cellstyle.setBottomBorderColor(HSSFColor.BLACK.index);
        //cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);   
        //cellstyle.setLeftBorderColor(HSSFColor.BLACK.index);   
        //cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);   
        //cellstyle.setRightBorderColor(HSSFColor.BLACK.index);   
        //cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);   
        //cellstyle.setTopBorderColor(HSSFColor.BLACK.index);

        if (isTotal) {
            cellstyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cellstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        }

        return cellstyle;
    }

    public static CellStyle generatePercentCellStyle(Workbook workbook, boolean isTotal) {

        CellStyle cellstyle = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();

        cellstyle.setDataFormat(format.getFormat("0.0000%"));
        cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellstyle.setBottomBorderColor(HSSFColor.BLACK.index);
        cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellstyle.setLeftBorderColor(HSSFColor.BLACK.index);
        cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellstyle.setRightBorderColor(HSSFColor.BLACK.index);
        cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellstyle.setTopBorderColor(HSSFColor.BLACK.index);

        if (isTotal) {
            cellstyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cellstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        }

        return cellstyle;
    }

    /**
     * 合并单元格
     * 
     * @param sheet
     * @param style
     * @param startRowIndex
     * @param endRowIndex
     * @param startCellIndex
     * @param endCellIndex
     */
    public static void mergeCells(Sheet sheet, CellStyle style, int startRowIndex, int endRowIndex, int startCellIndex,
                                  int endCellIndex) {

        CellRangeAddress region = new CellRangeAddress(startRowIndex, endRowIndex, startCellIndex, endCellIndex);
        sheet.addMergedRegion(region);

        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                ExcelUtil.getCell(ExcelUtil.getRow(sheet, i), j).setCellStyle(style);
            }
        }
    }

    /**
     * 获取合并单元格的值
     * 
     * @param sheet
     * @param cell
     * @return
     * @throws Exception
     */
    public static Cell getMergedCell(Sheet sheet, Cell cell) throws Exception {

        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {

            CellRangeAddress region = sheet.getMergedRegion(i);

            if (region.isInRange(cell.getRowIndex(), cell.getColumnIndex())) {
                return ExcelUtil.getCell(ExcelUtil.getRow(sheet, region.getFirstRow()), cell.getColumnIndex());
            }
        }

        return cell;

    }

    /**
     * 判断当前Cell是否是在合并格里面
     * 
     * @param sheet
     * @param cell
     * @return
     * @throws Exception
     */
    public static boolean isMergedCell(Sheet sheet, Cell cell) throws Exception {

        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {

            CellRangeAddress region = sheet.getMergedRegion(i);

            if (region.isInRange(cell.getRowIndex(), cell.getColumnIndex())) {
                return true;
            }
        }

        return false;
    }

}
