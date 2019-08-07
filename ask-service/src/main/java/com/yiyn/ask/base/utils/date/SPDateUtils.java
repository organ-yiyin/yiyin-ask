package com.yiyn.ask.base.utils.date;

import java.util.Date;

/**
 * 已测试，
 * 方法功能注释可参考SPDateTimeUitls
 * 
 * @author SP2.0
 *
 */
public class SPDateUtils {
    
    public static String format(Date date, String format) {
    	return SPDateTimeUtils.format(SPDateTimeUtils.toDateTime(date), format);
    }
    public static Date parse(String time,String format) {
        return SPDateTimeUtils.toDate(SPDateTimeUtils.parse(time, format));
    }
    public static Date parseDateDefault(String time){
    	return parse(time, DateFormatTemplate.DATE_FORMAT_DEFAULT);
    }
//	public static Date parseDateISO(String time){
//		return parse(time, DateFormatTemplate.DATE_TIME_FORMAT_ISO8601);
//	}
    public static Date parseDateTimeDefault(String time){
    	return parse(time, DateFormatTemplate.DATE_TIME_FORMAT_DEFAULT);
    }
//    public static Date parseDateTimeISO8601(String time){
//    	return SPDateTimeUtils.toDate(SPDateTimeUtils.parseDateTimeISO8601(time));
//    }
    public static String formatDateDefault(Date date){
    	return format(date,DateFormatTemplate.DATE_FORMAT_DEFAULT);
    }
    public static String formatDateTimeDefault(Date date){
    	return format(date,DateFormatTemplate.DATE_TIME_FORMAT_DEFAULT);
    }
    public static Date ignoreTime(Date date){
    	return parseDateDefault(formatDateDefault(date));
    }
    public static Date addSeconds(Date date, int num){
    	return SPDateTimeUtils.toDate(
    		SPDateTimeUtils.addSeconds(SPDateTimeUtils.toDateTime(date), num));
    }
    public static Date addMinutes(Date date, int num){
    	return SPDateTimeUtils.toDate(
    		SPDateTimeUtils.addMinutes(SPDateTimeUtils.toDateTime(date), num));
    }
    public static Date addHours(Date date, int num){
    	return SPDateTimeUtils.toDate(
    		SPDateTimeUtils.addHours(SPDateTimeUtils.toDateTime(date), num));
    }
    public static Date addDays(Date date, int num){
    	return SPDateTimeUtils.toDate(
    		SPDateTimeUtils.addDays(SPDateTimeUtils.toDateTime(date), num));
    }
    public static Date addMonths(Date date, int num){
    	return SPDateTimeUtils.toDate(
    		SPDateTimeUtils.addMonths(SPDateTimeUtils.toDateTime(date), num));
    }
    public static Date addYears(Date date, int num){
    	return SPDateTimeUtils.toDate(
    		SPDateTimeUtils.addYears(SPDateTimeUtils.toDateTime(date), num));
    }
    public static int durationYear(Date startTime, Date endTime){
    	return SPDateTimeUtils.durationYear(
    			SPDateTimeUtils.toDateTime(startTime), 
    			SPDateTimeUtils.toDateTime(endTime));
    }
    public static int durationMonth(Date startTime, Date endTime){
    	return SPDateTimeUtils.durationMonth(
    			SPDateTimeUtils.toDateTime(startTime), 
    			SPDateTimeUtils.toDateTime(endTime));
    }
    public static int durationDay(Date startTime, Date endTime){
    	return SPDateTimeUtils.durationDay(
    			SPDateTimeUtils.toDateTime(startTime), 
    			SPDateTimeUtils.toDateTime(endTime));
    }
	public static int durationHour(Date startTime, Date endTime){
		return SPDateTimeUtils.durationHour(
    			SPDateTimeUtils.toDateTime(startTime), 
    			SPDateTimeUtils.toDateTime(endTime));
	}
	public static int durationMinute(Date startTime, Date endTime){
		return SPDateTimeUtils.durationMinute(
    			SPDateTimeUtils.toDateTime(startTime), 
    			SPDateTimeUtils.toDateTime(endTime));
	}
	public static int durationSecond(Date startTime, Date endTime){
		return SPDateTimeUtils.durationSecond(
    			SPDateTimeUtils.toDateTime(startTime), 
    			SPDateTimeUtils.toDateTime(endTime));
	}

	@Deprecated
    public static Date plusNumToTime(Date date, int num, DateFieldType fieldType){
    	return SPDateTimeUtils.toDate(SPDateTimeUtils.plusNumToTime(SPDateTimeUtils.toDateTime(date), num, fieldType));
    }
	@Deprecated
    public static Date minusNumToTime(Date date, int num, DateFieldType fieldType){
    	return SPDateTimeUtils.toDate(SPDateTimeUtils.minusNumToTime(SPDateTimeUtils.toDateTime(date), num, fieldType));
    }
}
