package com.hao.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:时间日期辅助类</p>
 */
public class DateHelper {
	private static final Log logger = LogFactory.getLog(DateHelper.class);
	/**
	 * 默认的日期样式：yyyy-MM-dd
	 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DEFAULT_SHORT_DATE_FORMAT = "yyyy-MM";
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final int YEAR = 1;
	public static final int MONTH = 2;
	public static final int DAY = 3;
	public static final int HOUR = 4;
	public static final int MINUTE = 5;
	public static final int SECOND = 6;
	public static final int MILLISECOND = 7;

	public DateHelper(){
	}
	/**
	 * 判断是否为正确的时间日期字符串
	 * @param in String : 待判断的字符串，如：1980-01-01 10:10:10
	 * @param format String : 时间日期格式，如：yyyy-MM-dd HH:mm:ss
	 * @return boolean : 如果正确则返回true，否则返回false
	 */
	public static boolean isDateFormat(final String in,final String format){
		if(in == null) return false;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			Date date = new Date(formatter.parse(in).getTime());
			//非法时间格式
			if(toString(date, format).equals(in) == false) return false;
			return true;
		}catch(ParseException pe){
			logger.warn("isDateFormat("+in+","+format+"):ParseException-->" + pe.getMessage());
			return false;
		}		
	}
	/**
	 * 判断是否为正确的时间日期字符串
	 * @param in String : 待判断的字符串，如：1980-01-01（符合yyyy-MM-dd格式）
	 * @return boolean : 如果正确则返回true，否则返回false
	 */	
	public static boolean isDateFormat(final String in){
		return isDateFormat(in,DEFAULT_DATE_FORMAT);
	}	
	/**
	 * 将字符串转换为java.sql.Date
	 * @param in String : 待转换的字符串，如：1980-01-01 10:10:10
	 * @param format String : 日期格式，如：yyyy-MM-dd HH:mm:ss
	 * @return Date : 转换后的日期，若字符串表示的是非法时间格式，则返回null
	 */
	public static Date toDate(final String in, final String format){
		if(isDateFormat(in,format) == false) return null;
		if(in == null) return null;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.parse(in);
		}catch(ParseException pe){
			logger.warn("toDate("+in+","+format+"):ParseException-->" + pe.getMessage());
			return null;
		}
	}
	/**
	 * 从Timestamp转换为Date
	 * @param in java.sql.Timestamp : 时间
	 * @return java.util.Date :
	 */
	public static Date fromTimestamp(final Timestamp in){
		if(in == null) return null;
		return new Date(in.getTime());
	}
	/**
	 * 将Date格式化输出
	 * @param in Date : 要转换成字符串的日期
	 * @param format : 格式，请参照java.text.SimpleDateFormat的格式
	 * @return String : 转换后的字符串形日期
	 */
	public static String toString(final Date in, final String format){
		if(in == null) return null;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.format(in);
		}catch(Exception e){
			SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			return formatter.format(in);
		}
	}
	/**
	 * 取得某年某月的天数
	 * @param year int : 年份（大于0）
	 * @param month int : 月份
	 * @return int : 年月所对应的天数
	 */
	public static int getDayCounts(final int year, final int month){
		switch(month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 2:
				if(isLeapYear(year)){
					return 29;
				}else{
					return 28;
				}
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			default:
				return 0;
		}
	}
	/**
	 * 是否为星期天
	 * @param year int : 年份
	 * @param month int : 月份
	 * @param day int : 天
	 * @return boolean : 如果是星期天返回true,否则返回false
	 */
	public static boolean isSunday(final int year,final int month, final int day){
		return (getDayofWeek(year,month,day) == 1);
	}
	/**
	 * 是否为星期六
	 * @param year int : 年份
	 * @param month int : 月份
	 * @param day int : 天
	 * @return boolean : 如果是星期六返回true,否则返回false
	 */
	public static boolean isSaturday(final int year, final int month, final int day){
		return (getDayofWeek(year,month,day) == 7);
	}
	/**
	 * 是否为周末(星期六，日)
	 * @param year int : 年份
	 * @param month int : 月份
	 * @param day int : 天
	 * @return boolean : 如果是周末则返回true,否则返回false
	 */
	public static boolean isWeekend(final int year, final int month, final int day){
		return (isSaturday(year,month,day) || isSunday(year,month,day));
	}
	private static int getDayofWeek(int year,int month,int day){
		Calendar c = Calendar.getInstance();
		c.set(year,month-1,day);
		return c.get(Calendar.DAY_OF_WEEK);		
	}
	
	/**
	 * 得到日期在一周的天数
	 * @param date Date : 具体日期
	 * @return int : 周几
	 */
	public static int getDayofWeek(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK)-1;
	}
	
	/**
	 * 得到星期几(中文)
	 * @param date Date : 具体日期
	 * @return int : 周几
	 */
	public static String getDate(Date date){
		int dayofWeek = getDayofWeek(date);
		switch(dayofWeek) {
		case 1:
			return "一";
		case 2:
			return "二";
		case 3:
			return "三";
		case 4:
			return "四";
		case 5:
			return "五";
		case 6:
			return "六";
		case 0:
			return "日";
			
		default:
			return null;
		}
	}
	
	/**
	 * 判断某年份是否为闰年
	 * @param year int : 年份（大于0）
	 * @return boolean : 如果是返回true，否则返回false
	 */
	public static boolean isLeapYear(final int year){
		if(year <= 0) return false;
		if(year % 4 != 0) return false;
		if(year % 100 == 0 && year % 400 != 0) return false;
		return true;
	}
	/**
	 * 得到当前时间的年份
	 * @return int : 当前年份
	 */
	public static int getNowYear(){
		Date date = new Date(getSystemTime());
		return get(date,YEAR);	
	}
	/**
	 * 得到当前时间的月份
	 * @return int : 当前月份：[1,12]
	 */
	public static int getNowMonth(){
		Date date = new Date(getSystemTime());
		return get(date,MONTH);	
	}
	/**
	 * 得到当前时间的日期
	 * @return int : 当前日期：[1,31]
	 */	
	public static int getNowDay(){
		Date date = new Date(getSystemTime());
		return get(date,DAY);	
	}
	/**
	 * 得到当前时间的小时
	 * @return int : 当前小时：[0,23]
	 */	
	public static int getNowHour(){
		Date date = new Date(getSystemTime());
		return get(date,HOUR);
	}
	/**
	 * 得到当前时间的分钟数
	 * @return int : 当前分钟：[0,59]
	 */
	public static int getNowMinute(){
		Date date = new Date(getSystemTime());
		return get(date,MINUTE);
	}
	/**
	 * 得到当前时间的秒钟数
	 * @return int : 当前秒种：[0,59]
	 */
	public static int getNowSecond(){
		Date date = new Date(getSystemTime());
		return get(date,SECOND);
	}
	/**
	 * 对时间作调整
	 * @param date Date : 需要做调整的时间日期
	 * @param field int : 需要调整的项，
	 * @param amount int : 调整量（可以为负数）
	 * @return Date : 调整后的时间
	 */
	public static Date add(final Date date, final int field, final int amount){
		if(date == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		switch(field) {
			case YEAR:
				c.add(Calendar.YEAR, amount);
				break;
			case MONTH: //1 - 12
				c.add(Calendar.MONTH, amount);
				break;
			case DAY:
				c.add(Calendar.DATE, amount);
				break;
			case HOUR:
				c.add(Calendar.HOUR, amount);
				break;
			case MINUTE:
				c.add(Calendar.MINUTE, amount);
				break;
			case SECOND:
				c.add(Calendar.SECOND, amount);
				break;
			case MILLISECOND:
				c.add(Calendar.MILLISECOND, amount);
				break;
			default:
				return null;
		}
		return new Date(c.getTimeInMillis());
	}
	/**
	 * 取得某日期各个位置的值
	 * @param date Date : 日期
	 * @param field int : 位置，具体参数本类的静态参数
	 * @return int : 相应位置的值
	 */
	public static int get(final Date date, final int field){
		if(date == null){ return 0; }
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		switch(field) {
			case YEAR:
				return c.get(Calendar.YEAR);
			case MONTH: //1 - 12
				return c.get(Calendar.MONTH) + 1;
			case DAY:
				return c.get(Calendar.DAY_OF_MONTH);
			case HOUR:
				return c.get(Calendar.HOUR_OF_DAY);
			case MINUTE:
				return c.get(Calendar.MINUTE);
			case SECOND:
				return c.get(Calendar.SECOND);
			case MILLISECOND:
				return c.get(Calendar.MILLISECOND);			
			default:
				return 0;
		}
	}	
	public static Timestamp toTimestamp(Date in){
		if(in == null) return null;
		return new Timestamp(in.getTime());		
	}
	public static Date toDate(Timestamp in){
		if(in == null) return null;
		return new Date(in.getTime());		
	}
	/**
	 * 得到指定年，月，日，时，分，秒的时间的毫秒数
	 * @param year int : 年[0..)
	 * @param month int : 月[0..11]
	 * @param day int : 日[1..31]
	 * @param hour int : 时[0..23]
	 * @param min int : 分[0..59]
	 * @param second int : 秒[0..59]
	 * @return long : 毫秒数
	 */
	public static long getTime(final int year, final int month,final int day,final int hour,final int min,final int second){
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, hour, min, second);
		return c.getTimeInMillis();
	}
	/**
	 * 取得当前系统时间（WEB服务器）
	 * @return long : 系统当前时间的毫妙数
	 */
	public static long getSystemTime(){
		return System.currentTimeMillis();
	}
	/**
	 * 取得当前系统时间（WEB服务器）
	 * @param format String : 时间显示的格式
	 * @return String : 用字符串表示的时间串
	 */
	public static String getSystemTime(final String format){
		return toString(new Date(getSystemTime()), format);
	}
	/**
	 * 得到今天的日期
	 * @return Date : 日期
	 */
	public static Date getToday(){
		return DateHelper.toDate(DateHelper.getSystemTime(DEFAULT_DATE_FORMAT), DEFAULT_DATE_FORMAT);
	}
	/**
	 * 判断是否在当前日期之前
	 * @param date String : 格式：yyyy-MM-dd
	 * @return boolean : 
	 */
	public static boolean isBeforeToday(final String date){
		Date d = DateHelper.toDate(date, DEFAULT_DATE_FORMAT);
		if(d == null) return false;
		return d.before(getToday());
	}
	/**
	 * 判断是否在当前日期之后
	 * @param date String : 格式：yyyy-MM-dd
	 * @return boolean : 
	 */
	public static boolean isAfterToday(final String date){
		Date d = DateHelper.toDate(date, DEFAULT_DATE_FORMAT);
		if(d == null) return false;
		return d.after(getToday());
	}	
	/**
	 * 判断是否为今天
	 * @param date String : 格式：yyyy-MM-dd
	 * @return boolean : 
	 */
	public static boolean isToday(final String date){
		Date d = DateHelper.toDate(date, DEFAULT_DATE_FORMAT);
		if(d == null) return false;
		return (d.getTime() == getToday().getTime());
	}
	public static int getDiffDay(Date bdate, Date edate) {	
		long day = 0;
		try {
		    Date date = DateHelper.toDate(DateHelper.toString(bdate, "yyyyMMdd"), "yyyyMMdd");
		    Date mydate = DateHelper.toDate(DateHelper.toString(edate, "yyyyMMdd"), "yyyyMMdd");
		    day = (Math.abs(date.getTime() - mydate.getTime())) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
		
		  return 0;
		}
		return (int)day;
	}
	/**
	 * 计算2个日期相差的天数，不够一天的算一天,负数为参照时间之前的时间
	 * 2016年4月6日
	 * @author cwz
	 * @param bdate   需要进行匹配，对比的时间
	 * @param edate   参照时间
	 * @return
	 */
	public static int getDiffDayOther(Date bdate, Date edate) {	
		long day = 0;
		try {
		    long longTime = bdate.getTime() - edate.getTime();
		    
		    day = longTime / (24 * 60 * 60 * 1000);
		    long temp = (Math.abs(longTime)) % (24 * 60 * 60 * 1000);
		    if(temp != 0){
		    	if(longTime > 0){
		    		day += 1;
		    	}else{
		    		day -= 1;
		    	}
		    }
		    
		} catch (Exception e) {
		
		  return 0;
		}
		return (int)day;
	}
	/**
	 * 计算2个日期相差的小时，不够一小时的算小时,负数为参照时间之前的时间
	 * 2016年4月6日
	 * @author cwz
	 * @param bdate   需要进行匹配，对比的时间
	 * @param edate   参照时间
	 * @return
	 */
	public static int getDiffHours(Date bdate, Date edate) {	
		long hours = 0;
		try {
		    long longTime = bdate.getTime() - edate.getTime();
		    
		    hours = longTime / (60 * 60 * 1000);
		    long temp = (Math.abs(longTime)) % (60 * 60 * 1000);
		    if(temp != 0){
		    	if(longTime > 0){
		    		hours += 1;
		    	}else{
		    		hours -= 1;
		    	}
		    }
		    
		} catch (Exception e) {
		
		  return 0;
		}
		return (int)hours;
	}
	/**
	 * 计算年龄
	 * @param bdate 生日
	 * @return
	 */
	public static int getAge(Date bdate) {
	    if (bdate==null) return 0;
	    int dd=getDiffDay(bdate,new Date());
	    
		return dd/365;
	}
	public static int getDiffYear(Date bdate, Date edate){
	    if (bdate==null||edate==null) return 0;
	    int byear=bdate.getYear();
	    int eyear=edate.getYear();
	    
		return eyear-byear;
	}
	/**
	 * 获取当前时间在本月的第一天的日期
	 * 2016年2月2日
	 * @author cwz
	 * @param date
	 * @return
	 */
	public static Date getDateMonthFirstDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return new Date(c.getTimeInMillis());
	}
	/**
	 * 获取当前时间在本月的最后一天的日期
	 * 2016年2月2日
	 * @author cwz
	 * @param date
	 * @return
	 */
	public static Date getDateMonthLastDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return new Date(c.getTimeInMillis());
	}
	
	/**
	 * 获取本周一的时间（每星期按周一为第一天）
	 * 2016年3月2日
	 * @author cwz
	 * @param date
	 * @return
	 */
	public static Date getNowWeekMonday(Date date) {    
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1); //解决周日会出现 并到下一周的情况    
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);    
        return cal.getTime();    
    }
	/**
	 * 获取本周日的时间（每星期按周日为最后一天）
	 * 2016年3月2日
	 * @author cwz
	 * @param date
	 * @return
	 */
	public static Date getNowWeekSunday(Date date) {    
        Date Monday = getNowWeekMonday(date);
        return add(Monday, 3, 6);
    }
	/**
	 * 获取上周一的时间（每星期按周一为第一天）
	 * 2016年3月2日
	 * @author cwz
	 * @param date
	 * @return
	 */
	public static Date getLastWeekMonday(Date date) {    
        Date Monday = getNowWeekMonday(date);
        return add(Monday, 3, -7);
    }
	/**
	 * 获取上周周日的时间（每星期按周日为最后一天）
	 * 2016年3月2日
	 * @author cwz
	 * @param date
	 * @return
	 */
	public static Date getLastWeekSunday(Date date) {    
        Date Monday = getNowWeekMonday(date);
        return add(Monday, 3, -1);
    }
	/**
	 * 获取time之前length个月的时间
	 * @param time   为最早的时间
	 * @param format 时间格式
	 * @param minMonth 初始时间
	 * @param length   时间长度
	 * @return
	 * author cwz
	 * 2017年3月17日
	 */
	public static List<String> getTimeList(String time,String format,String minMonth,int length){
		
        List<String> timeList = new ArrayList<String>();
        Date now = null;
        
        if(time != null && !"".equals(time)){//非空
        	now = DateHelper.toDate(time, format);
        }else{//当月
        	now = new Date();
        }
        
        int amount = 0;
        for(int i=0;i<length;i++){//获取之前月份的时间
        	Date d = DateHelper.add(now, 2, amount);
        	String t = DateHelper.toString(d, format);
        	if(minMonth.equals(t)){//如果最小日期，则跳出循环
        		break;
        	}
        	timeList.add(t);
        	amount -= 1;
        }
        
        return timeList;
	}
	
	
	/**
	 * 获得两个日期之间相差的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
    /**
     * 获得两个日期之间相差的分钟数
     * @param date1
     * @param date2
     * @return
     * author cwz
     * 2018年3月29日
     */
    public static int differentMinuteByMillisecond(Date date1,Date date2)
    {
    	int days = (int) ((date2.getTime() - date1.getTime()) / (1000*60));
    	if(((date2.getTime() - date1.getTime()) % (1000*60))>0){
    		days += 1;
    	}
        return days;
    }
	
    /**
     * 获得上个月最后一天的时间
     * @return
     */
	public static String getLastMonthDay() {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	    Calendar cale = Calendar.getInstance();   
        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
        String lastDay = format.format(cale.getTime());

        return lastDay;
    }
	

	
	
	public static void main(String[] args){
/*		List<String> list = DateHelper.getTimeList(null, "yyyy年MM月", "2016年07月", 10);
		for(String time:list){
			System.out.println("time===="+time);
		}*/
//		System.out.println(DateHelper.isDateFormat("2004-12-20 17:39:05","yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateHelper.toString(DateHelper.toDate("2004-12-20 17:39:05", "yyyy-MM-dd HH:mm:ss"),
//				"yyyy-MM-dd HH:mm"));
		//System.out.println(DateHelper.isSaturday(2005,12,3));
//		System.out.println(DateHelper.toDate("2006-7-22", "yyyy-M-d"));
//		System.out.println(DateHelper.getSystemTime("yyMMdd HH时mm分ss秒"));
		
		//String dd = "2013-02-28";
		//Date d = toDate(dd,"yyyy-MM-dd");
		//System.out.println("--"+(getAge(DateHelper.toDate("2006-7-22", "yyyy-M-d"))));
		//Date dt=new Date();
		/*
	    System.out.println(DateHelper.getNowWeekMonday(dt));
	    System.out.println(DateHelper.getNowWeekSunday(dt));
	    System.out.println(DateHelper.getLastWeekMonday(dt));
	    System.out.println(DateHelper.getLastWeekSunday(dt));
	    */
		//Date d=DateHelper.add(dt,2, -2);
		
		//System.out.println(DateHelper.toString(DateHelper.add(dt,3, 1), "yyyy-MM-dd HH:mm:ss"));
	
/*		Date bDate = DateHelper.toDate("2016-04-06 17:03:00", "yyyy-MM-dd HH:mm:ss");	
		Date dDate = DateHelper.toDate("2016-04-06 12:00:00", "yyyy-MM-dd HH:mm:ss");
		int h = DateHelper.getDiffHours(bDate, dDate);
		System.out.println(DateHelper.getDiffHours(bDate, dDate));
		
		System.out.println("==========="+DateHelper.add(dt, 4, h));*/
		//System.out.println("=getNowYear()="+getNowYear());
		
		/*
		Date add = DateHelper.add(new Date(),5,6);
		System.out.println("==add=="+add);
		
		
		
		*/
		/*
		String ddd = "2017-12-05 01:01:01";
		Date dtt = toDate(ddd,"yyyy-MM-dd HH:mm:ss");
		//System.out.println("==="+getDiffDayOther(dtt, dt));
		
		Date aaaa = toDate("2018-01-31 23:59:59", "yyyy-MM-dd HH:mm:ss");
		Date date1 = toDate("2018-02-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
		System.out.println(aaaa+"==="+dtt);
	
		System.out.println("==="+differentMinuteByMillisecond(aaaa, date1));
		*/
	}
}