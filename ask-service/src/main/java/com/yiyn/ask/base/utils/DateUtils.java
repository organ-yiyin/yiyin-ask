package com.yiyn.ask.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang3.StringUtils;

public class DateUtils {
	
	/**定义常量**/
    public static final String DATE_JFP_STR="yyyyMM";
    public static final String DATE_DAY_STR = "yyyyMMdd";
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_SMALL_STR = "yyyy-MM-dd";
    public static final String DATE_KEY_STR = "yyMMddHHmmss";
    public static final String DATE_KEY_LONG_STR = "yyyyMMddHHmmss";
    public static final String DATE_WHOLE_FULL_STR = "yyyyMMddHHmmssS";
    public static final String DATE_WHOLE_FULL_STR2 = "yyyyMMddHHmmssSSS";
    
    public static final String DATE_CHN = "yyyy年M月d日 HH:mm";
    
    
     
    /**
     * 使用预设格式提取字符串日期
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate,DATE_FULL_STR);
    }
     
    /**
     * 使用用户格式提取字符串日期
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
    	if(StringUtils.isEmpty(strDate)){
    		return null;
    	}
    	
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String formatFullPattern(Date date){
    	return format(date,DATE_FULL_STR);
    }
    
    public static String format(Date date, String pattern){
    	if(date == null){
    		return "";
    	}
    	SimpleDateFormat df = new SimpleDateFormat(pattern);
    	return df.format(date);
    }
     
    /**
     * 两个时间比较
     * @param date
     * @return
     */
    public static int compareDateWithNow(Date date1){
        Date date2 = new Date();
        int rnum =date1.compareTo(date2);
        return rnum;
    }
    
    /**
     * 两个时间比较 date类型
     * @param date
     * @return
     */
    public static int compareDate(Date date1,Date date2){
        int rnum =date1.compareTo(date2);
        return rnum;
    }
     
    /**
     * 两个时间比较(时间戳比较)
     * @param date
     * @return
     */
    public static int compareDateWithNow(long date1){
        long date2 = dateToUnixTimestamp();
        if(date1>date2){
            return 1;
        }else if(date1<date2){
            return -1;
        }else{
            return 0;
        }
    }
     
 
    /**
     * 获取系统当前时间
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
        return df.format(new Date());
    }
     
    /**
     * 获取系统当前时间
     * @return
     */
    public static String getNowTime(String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        return df.format(new Date());
    }
     
    /**
     * 获取系统当前计费期
     * @return
     */
    public static String getJFPTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_JFP_STR);
        return df.format(new Date());
    }
     
    /**
     * 将指定的日期转换成Unix时间戳
     * @param String date 需要转换的日期 yyyy-MM-dd HH:mm:ss
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(DATE_FULL_STR).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }
     
    /**
     * 将指定的日期转换成Unix时间戳
     * @param String date 需要转换的日期 yyyy-MM-dd
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date, String dateFormat) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }
     
    /**
     * 将当前日期转换成Unix时间戳
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp() {
        long timestamp = new Date().getTime();
        return timestamp;
    }
     
     
    /**
     * 将Unix时间戳转换成日期
     * @param long timestamp 时间戳
     * @return String 日期字符串
     */
    public static String unixTimestampToDate(long timestamp) {
        SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }
	
    
    /**
     * 日期计算
     * 1则代表的是对年份操作，2是对月份操作，3是对星期操作，5是对日期操作，11是对小时操作，12是对分钟操作，13是对秒操作，14是对毫秒操作
     * @param originalCalendar
     * @param field Calendar.DAY_OF_MONTH
     * @param value
     * @return
     */
    public static Date calculateDate(Date date, int field, int value){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(field, value);
    	return c.getTime();
    }
    
    /**
     * 获取两者之间相差的天数
     * 
     * @param early
     * @param late
     * @return
     */
    public static int getMonthsBetweenDates(Date early, Date late){
		
		Date sEarly = parse(format(early,DATE_SMALL_STR),DATE_SMALL_STR);
		Date sLate = parse(format(late,DATE_SMALL_STR),DATE_SMALL_STR);
		
		Calendar earlyCalendar = Calendar.getInstance();
		earlyCalendar.setTime(sEarly);
		
		Calendar lateCalendar = Calendar.getInstance();
		lateCalendar.setTime(sLate);
		
		int result = 12*(lateCalendar.get(Calendar.YEAR) - earlyCalendar.get(Calendar.YEAR)) 
			+ (lateCalendar.get(Calendar.MONTH) - earlyCalendar.get(Calendar.MONTH));
		
		return result;
	}
    
    /**
     * 获取两者之间相差的天数
     * 
     * @param early
     * @param late
     * @return
     */
    public static int getDaysBetweenDates(String early, String late){
		Date sEarly = parse(early,DATE_SMALL_STR);
		Date sLate = parse(late,DATE_SMALL_STR);
		
		long t1 = sEarly.getTime();
		long t2 = sLate.getTime();
		
		long between_days=(t2 - t1)/(1000*60*60*24);  
		
		return (int)between_days;
	}
    
    /**
	 * 得到一个日期所在月的第一天和最后一天
	 * 
	 * @param sj
	 *            时间 格式YYYY-MM-DD
	 * @return
	 */
	public static String[] getMonthFirsAndLast(String sj) {
		String[] dayResult = { "", "" };
		String strtmp = sj;
		if (sj.length() >= 7) {
			strtmp = strtmp.substring(0, 7);
		}
		Calendar lastCal = Calendar.getInstance();
		// 得到给日期所在月的第一天
		dayResult[0] = strtmp + "-01";
		int n = Integer.parseInt(strtmp.substring(5, 7)) + 1;
		java.sql.Date curDate;
		if (n==13){
		    // 如果是12月的话，取下年一号，然后减一天
		    int nextYDay = Integer.valueOf(strtmp.substring(0, 4))+ 1;
            curDate = java.sql.Date.valueOf(nextYDay + "-01-01");
		}else if(n > 9) {
			// 得到给日期所在的月最后一天
			curDate = java.sql.Date.valueOf(strtmp.substring(0, 5)
					+ String.valueOf(n) + "-01");
		} else {
			curDate = java.sql.Date.valueOf(strtmp.substring(0, 5) + "0"
					+ String.valueOf(n) + "-01");
		}
		lastCal.setTime(curDate);
		lastCal.add(Calendar.DATE, -1);
		String dayStr = lastCal.get(Calendar.DATE) + "";
		if (dayStr.length() == 1) {
			dayStr = "0" + dayStr;
		}
		dayResult[1] = strtmp + "-" + dayStr;

		return dayResult;
	}
	
	/**
	 * 根据出生日期获取当前年龄
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
    public static int getAge(Date birthDay) throws Exception {  
        Calendar cal = Calendar.getInstance();  
        if (cal.before(birthDay)) {  
            throw new IllegalArgumentException(  
                    "The birthDay is before Now.It's unbelievable!");  
        }  
        int yearNow = cal.get(Calendar.YEAR);  
        int monthNow = cal.get(Calendar.MONTH);  
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
        cal.setTime(birthDay);   
  
        int yearBirth = cal.get(Calendar.YEAR);  
        int monthBirth = cal.get(Calendar.MONTH);  
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);   
  
        int age = yearNow - yearBirth;  
  
        if (monthNow <= monthBirth) {  
            if (monthNow == monthBirth) {  
                if (dayOfMonthNow < dayOfMonthBirth) age--;  
            }else{  
                age--;  
            }
        }  
        return age;  
    }
    
    /**
	 * 根据出生日期获取当前年龄 精确到xx岁xx月xx日
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
    public static String getAgeDetail(String strDate1,String strDate2) throws Exception {  
    	String age="";
        Calendar cal = Calendar.getInstance();  
        cal.setTime(parse(strDate1,DATE_SMALL_STR));
        int yearStart = cal.get(Calendar.YEAR);  
        int monthStart = cal.get(Calendar.MONTH);  
        int dayOfMonthStart = cal.get(Calendar.DAY_OF_MONTH);  
        cal.setTime(parse(strDate2,DATE_SMALL_STR));
  
        int yearEnd = cal.get(Calendar.YEAR);  
        int monthEnd = cal.get(Calendar.MONTH);  
        int dayOfMonthEnd = cal.get(Calendar.DAY_OF_MONTH);   
  
        int yearInterval = yearEnd - yearStart;  
  
        if(monthEnd < monthStart || (monthStart == monthEnd && dayOfMonthEnd < dayOfMonthStart)) yearInterval --;
        
        if(yearInterval > 0){
        	age = yearInterval + "岁";
        }
        
        int monthInteval = monthEnd -monthStart;
        int dayInteval = dayOfMonthEnd - dayOfMonthStart;
        if(monthInteval > 0){
        	if(dayInteval > 0){
        		age += monthInteval + "月" + dayInteval + "天";
        	}else if(dayInteval == 0){
        		age += monthInteval + "月";
        	}else{
        		int m = monthInteval -1;
        		if(m > 0){
        			age += m + "月" + (dayInteval+ 31) + "天";
        		}
        	}
        }else if (monthInteval == 0){
        	if(dayInteval > 0){
        		age += dayInteval + "天";
        	}else if(dayInteval == 0){
        	}else{
        		age += "11月" + (dayInteval + 31) + "天";
        	}
        }else{
        	if(dayInteval > 0){
        		age += (12 + monthInteval) + "月" + dayInteval + "天";
        	}else if(dayInteval == 0){
        		age += (12 + monthInteval) + "月";
        	}else{
        		age += (12 + monthInteval - 1) + "月" + (dayInteval + 31)  + "天";
        	}
        }
        
        return age;  
    }
    
    /**
	 * 比较两个日期String的大小
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
    public static boolean compareStrDate(String dateStr1,String dateStr2) throws Exception {  
        long date1 = parse(dateStr1,DATE_SMALL_STR).getTime();
        long date2 = parse(dateStr2,DATE_SMALL_STR).getTime();
        
        return date1 > date2 ? true:false;  
    }
    
    /**
     * 获取两个日期的月数差
     * @param dateStr1
     * @param dateStr2
     * @return
     * @throws Exception
     */
    public static int getMonthDiff(String dateStr1,String dateStr2) throws Exception {  
        Calendar cal1 = Calendar.getInstance(); 
        cal1.setTime(parse(dateStr1,DATE_SMALL_STR));
        Calendar cal2 = Calendar.getInstance();  
        cal2.setTime(parse(dateStr2,DATE_SMALL_STR));
        
        int year1 = cal1.get(Calendar.YEAR);  
        int month1 = cal1.get(Calendar.MONTH);  
        int dayOfMonth1 = cal1.get(Calendar.DAY_OF_MONTH);  
        
        int year2 = cal2.get(Calendar.YEAR);  
        int month2 = cal2.get(Calendar.MONTH);  
        int dayOfMonth2 = cal2.get(Calendar.DAY_OF_MONTH);  
        
        int yearInterval = year2 - year1;
        
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if(month2 < month1 || (month1 == month2 && dayOfMonth2 < dayOfMonth1)) yearInterval --;
		// 获取月数差值
		int monthInterval = (month2 + 12) - month1 ;
		if(dayOfMonth2 > dayOfMonth1) monthInterval ++;
		monthInterval %= 12;
		return yearInterval * 12 + monthInterval;
    }
	
	public static void main(String args[]) throws Exception{
		//Date end_today = DateUtils.calculateDate(new Date(),Calendar.DAY_OF_MONTH,300);
		System.out.println(DateUtils.getAgeDetail("2018-09-23","2019-08-22"));
	}
}
