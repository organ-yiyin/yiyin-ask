package com.yiyn.ask.base.utils.date;

import java.util.Calendar;

/**
 * 已测试，
 * 方法功能注释可参考SPDateTimeUitls
 * 
 * @author SP2.0
 *
 */
public class SPCalendarUtils {
    
	public static String format(Calendar date, String format) {
    	return SPDateTimeUtils.format(SPDateTimeUtils.toDateTime(date), format);
    }
    public static Calendar parse(String time,String format) {
        return SPDateTimeUtils.toCalendar(SPDateTimeUtils.parse(time, format));
    }
    public static Calendar parseDateDefault(String time){
    	return parse(time, DateFormatTemplate.DATE_FORMAT_DEFAULT);
    }
    public static Calendar parseDateTimeDefault(String time){
    	return parse(time, DateFormatTemplate.DATE_TIME_FORMAT_DEFAULT);
    }
    public static String formatDateDefault(Calendar date){
    	return format(date,DateFormatTemplate.DATE_FORMAT_DEFAULT);
    }
    public static String formatDateTimeDefault(Calendar date){
    	return format(date,DateFormatTemplate.DATE_TIME_FORMAT_DEFAULT);
    }
    public static Calendar addSeconds(Calendar date, int num){
    	return SPDateTimeUtils.toCalendar(
    		SPDateTimeUtils.addSeconds(SPDateTimeUtils.toDateTime(date), num));
    }
    public static Calendar addMinutes(Calendar date, int num){
    	return SPDateTimeUtils.toCalendar(
    		SPDateTimeUtils.addMinutes(SPDateTimeUtils.toDateTime(date), num));
    }
    public static Calendar addHours(Calendar date, int num){
    	return SPDateTimeUtils.toCalendar(
    		SPDateTimeUtils.addHours(SPDateTimeUtils.toDateTime(date), num));
    }
    public static Calendar addDays(Calendar date, int num){
    	return SPDateTimeUtils.toCalendar(
    		SPDateTimeUtils.addDays(SPDateTimeUtils.toDateTime(date), num));
    }
    public static Calendar addMonths(Calendar date, int num){
    	return SPDateTimeUtils.toCalendar(
    		SPDateTimeUtils.addMonths(SPDateTimeUtils.toDateTime(date), num));
    }
    public static Calendar addYears(Calendar date, int num){
    	return SPDateTimeUtils.toCalendar(
    		SPDateTimeUtils.addYears(SPDateTimeUtils.toDateTime(date), num));
    }
    public static int durationYear(Calendar startTime, Calendar endTime){
    	return SPDateTimeUtils.durationYear(
    			SPDateTimeUtils.toDateTime(startTime), 
    			SPDateTimeUtils.toDateTime(endTime));
    }
    public static int durationMonth(Calendar startTime, Calendar endTime){
    	return SPDateTimeUtils.durationMonth(
    			SPDateTimeUtils.toDateTime(startTime), 
    			SPDateTimeUtils.toDateTime(endTime));
    }
    public static int durationDay(Calendar startTime, Calendar endTime){
    	return SPDateTimeUtils.durationDay(
    			SPDateTimeUtils.toDateTime(startTime), 
    			SPDateTimeUtils.toDateTime(endTime));
    }
	public static int durationHour(Calendar startTime, Calendar endTime){
		return SPDateTimeUtils.durationHour(
    			SPDateTimeUtils.toDateTime(startTime), 
    			SPDateTimeUtils.toDateTime(endTime));
	}
	public static int durationMinute(Calendar startTime, Calendar endTime){
		return SPDateTimeUtils.durationMinute(
    			SPDateTimeUtils.toDateTime(startTime), 
    			SPDateTimeUtils.toDateTime(endTime));
	}
	public static int durationSecond(Calendar startTime, Calendar endTime){
		return SPDateTimeUtils.durationSecond(
    			SPDateTimeUtils.toDateTime(startTime), 
    			SPDateTimeUtils.toDateTime(endTime));
	}
    
	@Deprecated
    public static Calendar plusNumToTime(Calendar date, int num, DateFieldType fieldType){
    	return SPDateTimeUtils.toCalendar(SPDateTimeUtils.plusNumToTime(SPDateTimeUtils.toDateTime(date), num, fieldType));
    }
}
