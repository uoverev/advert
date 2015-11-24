package com.advert.tool.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.time.DateUtils;

import com.better.framework.utils.Dates;
import com.google.common.collect.Lists;

public final class DateUtil {

	public static final Calendar calendar = Calendar.getInstance();
	public final static String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public final static String FORMAT_MM_DD = "MM-dd";
	public final static String FORMAT_YYYYMMDD = "yyyyMMdd";
	public final static String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static String YYYYMMDDHHMMSS="yyyyMMddHHmmss";
	public final static String SQL_FORMAT_YYYY_MM_DD_HH_MM_SS = "%Y-%m-%d %T"; // '%Y-%m-%d
	public final static String SQL_FORMAT_YYYY_MM_DD = "%Y-%m-%d";
	public final static String FORMAT_YYYY_YEAR_MM_MONETH_DD_DAY_HH_MM_SS = "yyyy年MM月dd日HH:mm:ss";
	
	public static SimpleDateFormat sdf_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
	public static SimpleDateFormat sdf_YYYY_MM_DD = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
	public static SimpleDateFormat sdf_MM_DD = new SimpleDateFormat(FORMAT_MM_DD);
	public static SimpleDateFormat sdf_YYYYMMDDHHMMSS = new SimpleDateFormat(YYYYMMDDHHMMSS);
	public static SimpleDateFormat dt_YYYYMMDD = new SimpleDateFormat(FORMAT_YYYYMMDD);
//	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	public static final SimpleDateFormat Y2S_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * <p>Description:将字符串转换成日期</p>
	 * @param date
	 * @param format 可使用DateUtil类中已初始化的format
	 * @return
	 * @throws ParseException
	 * @author tangqiang on 2015年4月7日
	 */
	public static Date parseDate(String date,SimpleDateFormat format) throws ParseException{
		if(StringUtil.isEmpty(date)) return null;
		return format.parse(date);
	}
	
	/**
	 * <p>Description:将字符串转换成日期，使用默认的格式：yyyy-MM-dd HH:mm:ss</p>
	 * @param date
	 * @return
	 * @throws ParseException
	 * @author tangqiang on 2015年4月8日
	 */
	public static Date parseDateDefault(String date) throws ParseException{
		return parseDate(date, sdf_YYYY_MM_DD_HH_MM_SS);
	}
	/**
	 * <p>Description:将字符串转换成日期,使用自定义格式</p>
	 * @param date
	 * @param pattern
	 * @return
	 * @throws ParseException
	 * @author tangqiang on 2015年4月8日
	 */
	public static Date parseDate(String date,String pattern) throws ParseException{
		SimpleDateFormat ft = new SimpleDateFormat(pattern);
		return parseDate(date, ft);
	}
	/**
	 * <p>Description:将日期转换成字符串,</p>
	 * @param date
	 * @param format 可使用DateUtil类中已初始化的format
	 * @return
	 * @author tangqiang on 2015年4月8日
	 */
	public static String formatDate(Date date,SimpleDateFormat format){
		if(date == null) return null;
		return format.format(date);
	}
	
	/**
	 * <p>Description:将日期转换成字符串，使用默认的格式：yyyy-MM-dd HH:mm:ss</p>
	 * @param date
	 * @return
	 * @author tangqiang on 2015年4月8日
	 */
	public static String formatDateDefault(Date date){
		return formatDate(date, sdf_YYYY_MM_DD_HH_MM_SS);
	}
	public static String formatDateNowDefault(){
		return formatDateDefault(new Date());
	}
	/**
	 * <p>Description:将日期转换成字符串,使用自定义格式</p>
	 * @param date
	 * @param pattern
	 * @return
	 * @author tangqiang on 2015年4月8日
	 */
	public static String formatDate(Date date,String pattern){
		SimpleDateFormat ft = new SimpleDateFormat(pattern);
		return formatDate(date, ft);
	}
	
    /**
     * <p>Description:判断是否是今天</p>
     * @param date
     * @return
     * @author tangqiang on 2015年4月8日
     */
    public static boolean isCurrentDay(Date date) {
        return isSameDay(date, Calendar.getInstance().getTime());
    }
	/**
	 * <p>Description:判断是否是昨天</p>
	 * @param date
	 * @return
	 * @author tangqiang on 2015年4月8日
	 */
	public static boolean isYestoday(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		return isSameDay(date, calendar.getTime());
	}
	
	/**
	 * <p>Description:判断是否是今年</p>
	 * @param date
	 * @return
	 * @author tangqiang on 2015年4月8日
	 */
	public static boolean isCurrentYear(Date date){
		return isSameYear(date, Calendar.getInstance().getTime());
	}
	
	/**
	 * <p>Description: 如果是今天，则显示今天 22:22:22;如果是昨天，则显示昨天 22:22:22</p>
	 * <p>如果是今年，则显示 02-02 22:22:22 ; 如果是其他日期，则显示2012-02-02 22:22:22</p>
	 * @param date
	 * @return
	 * @author tangqiang on 2015年4月8日
	 * @throws ParseException 
	 */
	public static String printWithFormat(String date) throws ParseException{
		Date day = parseDate(date, sdf_YYYY_MM_DD_HH_MM_SS);
		return printWithFormat(day);
	}
	
	/**
	 * <p>Description: 如果是今天，则显示今天 22:22:22;如果是昨天，则显示昨天 22:22:22</p>
	 * <p>如果是今年，则显示 02-02 22:22:22 ; 如果是其他日期，则显示2012-02-02 22:22:22</p>
	 * @param date
	 * @return
	 * @author tangqiang on 2015年4月8日
	 * @throws ParseException 
	 */
	public static String printWithFormat(Date date){
		String echoStr = "";
		if(isCurrentDay(date)){
			echoStr ="今天 "+Dates.format(date, "HH:mm:ss");
		}else if(isYestoday(date)){
			echoStr ="昨天 "+Dates.format(date,"HH:mm:ss");
		}else if(DateUtil.isCurrentYear(date)){
			echoStr=Dates.format(date, "MM-dd HH:mm:ss");
		}else{
			echoStr = Dates.format(date,"yyyy-MM-dd HH:mm:ss");
		}
		return echoStr;
	}
	
	/**
	 * <p>Description:本周起始时间：周日的00:00:00</p>
	 * @param date
	 * @return
	 * @author fuxiaofeng on 2015年3月17日
	 */
	public static Date getNowWeekDayStart(Date date) {
		Calendar cal = Calendar.getInstance();
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date weekDay = cal.getTime();
		Date start = Dates.dayStart(weekDay);
		return start;
	}
	public static void main(String[] args) throws Exception{
		Date now = new Date();
		//System.out.println(getNowWeekDayStart(now));
		//System.out.println(getNowWeekDayEnd(now));
		System.out.println(toDifferMinute(DateUtil.parseDate("2015-05-15 10:23:59", DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS),now));
	}
	/**
	 * <p>Description:本周结束时间：周六的23:23:59</p>
	 * @param date
	 * @return
	 * @author fuxiaofeng on 2015年3月17日
	 */
	public static Date getNowWeekDayEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		cal.add(Calendar.DAY_OF_WEEK, 1);
		Date weekDay = cal.getTime();
		Date start = Dates.dayEnd(weekDay);
		return start;
	}
	
	/**
	 * <p>Description:图表坐标X轴的日期显示</p>
	 * @param date
	 * @return
	 * @author fuxiaofeng on 2015年3月17日
	 */
	public static List<String> echoChartXDate(Date date){
		List<String> list = Lists.newArrayList();
		date = getNowWeekDayStart(date);
		for(int i=1;i<=7;i++){
			String str = "";
			Date day = DateUtils.addDays(date, i-1);
			if(i==7 && isCurrentYear(date)){
				str = formatDate(day, sdf_MM_DD)+"（周日）";
			}else if(i==7){
				str = sdf_YYYY_MM_DD.format(day)+"（周日）";
			}
			if(i==1 && isCurrentYear(date)){
				str = formatDate(day, sdf_MM_DD)+"（周一）";
			}else if(i==1){
				str = formatDate(day, sdf_YYYY_MM_DD)+"（周一）";
			}
			if(i==2 && isCurrentYear(date)){
				str = formatDate(day, sdf_MM_DD)+"（周二）";
			}else if(i==2){
				str = formatDate(day, sdf_YYYY_MM_DD)+"（周二）";
			}
			if(i==3 && isCurrentYear(date)){
				str = formatDate(day, sdf_MM_DD)+"（周三）";
			}else if(i==3){
				str = formatDate(day, sdf_YYYY_MM_DD)+"（周三）";
			}
			if(i==4 && isCurrentYear(date)){
				str = formatDate(day, sdf_MM_DD)+"（周四）";
			}else if(i==4){
				str = formatDate(day, sdf_YYYY_MM_DD)+"（周四）";
			}
			if(i==5 && isCurrentYear(date)){
				str = formatDate(day, sdf_MM_DD)+"（周五）";
			}else if(i==5){
				str = formatDate(day, sdf_YYYY_MM_DD)+"（周五）";
			}
			if(i==6 && isCurrentYear(date)){
				str = formatDate(day, sdf_MM_DD)+"（周六）";
			}else if(i==6){
				str = formatDate(day, sdf_YYYY_MM_DD)+"（周六）";
			}
			list.add(str);
		}
		return list;
	}
	
	@Deprecated
	public static String formatAll(Date date) {
		return formatDate(date, sdf_YYYYMMDDHHMMSS);
//		return sdf_YYYYMMDDHHMMSS.format(date);
	}
	
	/**
	 * <p>方法概要:将日期转换为String串,格式为yyyy-MM-dd HH:mm:ss</p>
	 * @param dt
	 * @return
	 */
	@Deprecated
	public static String toString(Date dt) {
		return formatDate(dt, FORMAT_YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 方法概要:将日期转换为String串
	 * 
	 * @param dt
	 * @param sFmt
	 * @return
	 */
	@Deprecated
	public static String toString(Date dt, String sFmt) {
		if (dt == null)
			return "";
		else {
			return formatDate(dt, sFmt);
//			SimpleDateFormat formatter = new SimpleDateFormat(sFmt);
//			return formatter.format(dt).toString();
		}
	}

	/**
	 * 方法概要:将String转Date
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	@Deprecated
	public static Date toDate(String date) {
		if (StringUtil.isEmpty(date)) {
			return new Date();
		}
//		DateFormat df = getDateFormat();
		Date da = null;
		try {
			da = parseDate(date,sdf_YYYY_MM_DD_HH_MM_SS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		try {
//			da = df.parse(date);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		return da;
	}

	/**
	 * 方法概要:将String转Date
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	@Deprecated
	public static Date toDate(String date, String format) {
//		DateFormat df = getDateFormat(format);
		Date da = null;
		try {
//			da = df.parse(date);
			da = parseDate(date, format);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return da;
	}

	/**
	 * 函数名称: toDifferYear 函数功能描述: 判断相差多少年
	 * 
	 * @param startyear
	 * @param lastyear
	 * @return //TODO <描述该参数的含义>
	 */
	public static int toDifferYear(Date startyear, Date lastyear) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startyear);
		int startyears = cal.get(Calendar.YEAR);

		Calendar endca = Calendar.getInstance();
		endca.setTime(lastyear);
		int lastyears = endca.get(Calendar.YEAR);

		int result = lastyears - startyears;
		return result;
	}

	/**
	 * 
	 * Timestamp转换成字符串
	 * 
	 * @param date
	 *            时间
	 * @return String 转换后的字符串形式
	 * @auther <a href="mailto:lihanpei@feinno.com">lihanpei</a>
	 * @since 2011-9-17 下午01:50:55
	 */
	public static String timestamp2str(Timestamp date) {
		return timestamp2str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获得传入日期对应上月最后一天的日期
	 * 
	 * @return
	 * @auther <a href="mailto:lihanpei@feinno.com">lihanpei</a>
	 * @return Date 2012-2-8 上午09:18:08
	 */
	public static Date getPreviousMonthEnd(Date date) {

		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);

		lastDate.add(Calendar.MONTH, -1);// 减一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		return lastDate.getTime();
	}

	/**
	 * 返回传入日期的季度顺序
	 * 
	 * @param date
	 * @return
	 * @auther <a href="mailto:lihanpei@feinno.com">lihanpei</a>
	 * @return int 2012-2-8 上午09:49:41
	 */
	public static int getQuarterOrder(Date date) {
		Calendar cDate = Calendar.getInstance();
		cDate.setTime(date);
		int iMonth = cDate.get(Calendar.MONTH);
		if (iMonth >= 1 && iMonth < 4) {
			return 1;
		} else if (iMonth >= 4 && iMonth < 7) {
			return 2;
		} else if (iMonth >= 7 && iMonth < 10) {
			return 3;
		} else if (iMonth >= 10 && iMonth < 13) {
			return 4;
		} else {
			return -1;
		}
	}

	/**
	 * 得到昨天的时间，格式为yyyy-MM-dd
	 * 
	 * @return
	 * @auther <a href="mailto:lihanpei@feinno.com">lihanpei</a>
	 *         2011-10-19 下午04:24:29
	 */
	public static Date yesterday() {

		Date sdate = new Date();
		long myTime = (sdate.getTime() / 1000) - 60 * 60 * 24;
		sdate.setTime(myTime * 1000);
		return sdate;
	}
	
	/**
	 * <p>Description:将秒转换为HH:mm:ss的字符串格式</p>
	 * @param sec
	 * @return
	 * @author tangqiang on 2015年4月8日
	 */
	public static String secToTime(int sec){
		String timeStr = null;  
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (sec <= 0)
            return "00:00";
        else {
            minute = sec / 60;
            if (minute < 60) {
                second = sec % 60;
                timeStr = NumberUtil.unitFormat(minute) + ":" + NumberUtil.unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = sec - hour * 3600 - minute * 60;
                timeStr = NumberUtil.unitFormat(hour) + ":" + NumberUtil.unitFormat(minute) + ":" + NumberUtil.unitFormat(second);
            }  
        }  
        return timeStr;  
	}
	
	/**
	 * 检查传入日期的当前月份是否为整月 规则：满月：每月1号至15号（含15号当日）之间 不是满月：每月15号——月底
	 * 
	 * @param date
	 * @return
	 * @auther <a href="mailto:lihanpei@feinno.com">lihanpei</a>
	 * @return boolean 2012-2-7 下午03:58:10
	 */
	public static boolean isFullMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		if (dayOfMonth <= 15)
			return true;
		return false;
	}

	/**
	 * 
	 * Timestamp转换成字符串
	 * 
	 * @param date
	 *            时间
	 * @return String 转换后的字符串形式
	 * @auther <a href="mailto:lihanpei@feinno.com">lihanpei</a>
	 * @since 2011-9-17 下午01:50:55
	 */
	public static String timestamp2str(Timestamp date, String dateFormat) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(dateFormat);
			String dateStr = df.format(date);
			Date newDate = java.sql.Date.valueOf(dateStr.substring(0, 10));
			String newDateStr = sdf_YYYY_MM_DD.format(newDate);
			return newDateStr;
		}
		return null;
	}

	/**
	 * 函数名称: toDifferYear 函数功能描述: 判断相差多少天
	 * 
	 * @param startyear
	 * @param lastyear
	 * @return //TODO <描述该参数的含义>
	 */
	public static int toDifferDay(Date startyear, Date lastyear) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startyear);

		Calendar endca = Calendar.getInstance();
		endca.setTime(lastyear);

		long daterange = endca.getTimeInMillis() - cal.getTimeInMillis();
		long time = 1000 * 3600 * 24; // A day in milliseconds
		int resutl = (int) (daterange / time);
		return resutl;
	}
	/**
	 * 函数名称: toDifferYear 函数功能描述: 判断相差多少小时
	 * 
	 * @param startyear
	 * @param lastyear
	 * @return //TODO <描述该参数的含义>
	 */
	public static int toDifferHour(Date startyear, Date lastyear) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startyear);

		Calendar endca = Calendar.getInstance();
		endca.setTime(lastyear);

		long daterange = endca.getTimeInMillis() - cal.getTimeInMillis();
		long time = 1000 * 3600; // A day in milliseconds
		int resutl = (int) (daterange / time);
		return resutl;
	}
	
	/**
	 * 函数名称: toDifferMinute 函数功能描述: 判断相差多少分钟
	 * 如果startyear小时lastyear返回正整数
	 * 示例：
	 * startyear:2015-05-15 10:23:02
	 * lastyear :2015-05-15 10:24:03或2015-05-15 10:24:59
	 * 结果都只返回1
	 * @param startyear
	 * @param lastyear
	 * @return //TODO lastyear比startyear早几分钟
	 */
	public static int toDifferMinute(Date startyear, Date lastyear) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startyear);

		Calendar endca = Calendar.getInstance();
		endca.setTime(lastyear);

		long daterange = endca.getTimeInMillis() - cal.getTimeInMillis();
		long time = 1000 * 60; // A day in milliseconds
		int resutl = (int) (daterange / time);
		return resutl;
	}
	
	/**
	 * 函数名称: toDifferNow 函数功能描述: 同现在比较的天数
	 * 
	 * @param startyear
	 * @return //TODO <描述该参数的含义>
	 */
	public static int toDifferNowYear(Date startyear) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startyear);
		int startyears = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_YEAR);

		Calendar endca = Calendar.getInstance();
		endca.setTime(new Date());
		int lastyears = endca.get(Calendar.YEAR);
		int lastday = endca.get(Calendar.DAY_OF_YEAR);
		int result = lastyears - startyears;
		if (lastday < day) {
			result = result - 1;
		}

		return result;
	}

	/**
	 * 函数名称: toDifferNow 函数功能描述: 同现在比较的天数
	 * 
	 * @param startyear
	 * @return //TODO <描述该参数的含义>
	 */
	public static int toDifferNowDay(Date startyear) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startyear);
		// int startyears = cal.get(Calendar.YEAR);

		Calendar endca = Calendar.getInstance();
		endca.setTime(new Date());
		// int lastyears = endca.get(Calendar.YEAR);

		long daterange = endca.getTimeInMillis() - cal.getTimeInMillis();
		long time = 1000 * 3600 * 24; // A day in milliseconds
		int resutl = (int) (daterange / time);
		// int result = lastyears - startyears;
		return resutl;
	}

	/**
	 * 方法概要:获取格式信息
	 * 
	 * @param format
	 * @return
	 */
	public static DateFormat getDateFormat(String format) {
		DateFormat df = null;
		if (format == null || format.trim().equals("")) {
			df = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
		} else {
			df = new SimpleDateFormat(format);
		}
		return df;
	}

	public static DateFormat getDateFormat() {
		return getDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 方法概要:增加年
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	public static String addYearsToString(Date date, int years, String format) {
		date = addYears(date, years);
		String strdate = toString(date, format);
		return strdate;
	}

	/**
	 * 方法概要:增加年
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addYears(Date date, int years) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + years);//
		// 让日期加1
		calendar.add(Calendar.YEAR, years);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 方法概要:增加月
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addMonths(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 方法概要:增加日期
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);// 让日期加1
		date = calendar.getTime();
		return date;
	}

	/**
	 * 方法概要:增加小时
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addHours(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hours);// 让日期加1
		date = calendar.getTime();
		return date;
	}

	/**
	 * 方法概要:增加分钟
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addMinute(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);// 让日期加1
		date = calendar.getTime();
		return date;
	}

	/**
	 * 年龄转出生日期
	 * 
	 * @param age
	 * @return
	 * @auther <a href="mailto:lihanpei@feinno.com">Hanpei Li</a> 2010-11-30
	 *         上午10:23:01
	 */
	public static Date getInt2Date(int age) {
		Calendar cal = Calendar.getInstance();
		int currentDate = cal.get(Calendar.YEAR);
		cal.set(Calendar.YEAR, currentDate - age);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		return cal.getTime();
	}

	/**
	 * 通过生日计算年龄
	 * 
	 * @param birthday
	 *            生日
	 * @return
	 * @auther <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a> Apr 23, 2011
	 *         3:03:10 PM
	 */
	public static int getAge(Date birthday) {
		if (birthday == null) {
			return 0;
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(birthday);
		Calendar c2 = Calendar.getInstance();
		int y = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		// int size = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		// if (size < 0) {
		// y--;
		// } else if (size == 0) {
		// int dsize = c2.get(Calendar.DATE) - c1.get(Calendar.DATE);
		// if (dsize < 0) {
		// y--;
		// }
		// }
		return y;
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	@Deprecated
	public static Date strToDate(String strDate) {
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		ParsePosition pos = new ParsePosition(0);
//		Date strtodate = formatter.parse(strDate, pos);
		Date strtodate = null;
		try {
			strtodate = parseDate(strDate, "yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return strtodate;
	}

	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 */
	public static String getNextDay(String nowdate, String delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			Date d = strToDate(nowdate);
			long myTime = (d.getTime() / 1000) + StringUtil.stringToInteger(delay) * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @param k
	 * @return
	 */
	@Deprecated
	public static String dateToStr(java.util.Date dateDate) {
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		String dateString = formatter.format(dateDate);
		String dateString = formatDate(dateDate, "yyyy-MM-dd");
		return dateString;
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.util.Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	public static int getDayofWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取当前时间或者具体时间所在周的周几对应的日期字符串,格式为yyyy-MM-dd
	 * 
	 * @param sdate
	 *            可为null
	 * @param num
	 *            可为null或者"",则返回所在周周一，传下面的值，返回对应的周几 "0"：周日 "1"：周一 "2"：周二 "3"：周三
	 *            "4"：周四 "5"：周五 "6"：周六
	 * @return String 格式为yyyy-MM-dd
	 * @auther <a href="mailto:zhangsongnw@feinno.com">Zhang Song</a> 2011-7-19
	 *         上午10:02:44
	 */
	public static Date getWeekOfChina(Date sdate, int num) {
		// 再转换为时间
		if (sdate == null) {
			sdate = new Date();
		}
		Date thisday = sdate;
		boolean sunday = false;
		Calendar c = Calendar.getInstance();
		if (getDayofWeek(thisday) == 1) {// 如果为周日，减一天
			thisday = addDays(thisday, -7);
			sunday = true;
		}

		c.setTime(thisday);

		switch (num) {
		case 2:// 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			break;
		case 3:// 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
			break;
		case 4:// 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
			break;
		case 5:// 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
			break;
		case 6:// 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
			break;
		case 7:// 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			break;
		case 1:// 返回星期日所在的日期
			if (!sunday) {
				c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			}
			break;
		}
		thisday = c.getTime();
		thisday = addDays(thisday, 1);
		return thisday;
	}

	/**
	 * 在日期签名加0
	 * 
	 * @param day
	 * @return
	 */
	public static String getZeroDay(int day) {
		String rtn = "";
		if (day <= 9) {
			rtn = "0" + day;
		} else {
			rtn = "" + day;
		}
		return rtn;
	}

	/**
	 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
	 * 
	 * @param sdate
	 * @param num
	 * @return
	 */
	public static String getWeek(String sdate, String num) {
		// 再转换为时间
		Date dd = strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (num.equals("1")) // 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		else if (num.equals("2")) // 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		else if (num.equals("3")) // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		else if (num.equals("4")) // 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		else if (num.equals("5")) // 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		else if (num.equals("6")) // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		else if (num.equals("0")) // 返回星期日所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 判断是否润年
	 * 
	 * @param ddate
	 * @return
	 */
	public static boolean isLeapYear(String ddate) {

		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		Date d = strToDate(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * 获取一个月的最后一天
	 * 
	 * @param dat
	 * @return
	 */
	public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(dat)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	@Deprecated
	public static String getStringDateShort() {
		Date currentTime = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		String dateString = formatter.format(currentTime);
		String dateString = formatDate(currentTime,"yyyy-MM-dd");
		return dateString;
	}

	public static String getOKDate(String sdate) {
		if (sdate == null || sdate.equals(""))
			return getStringDateShort();

		// if (!VeStr.Isdate(sdate)) {
		// sdate = getStringDateShort();
		// }
		// 将“/”转换为“-”
		// sdate = VeStr.Replace(sdate, "/", "-");
		// 如果只有8位长度，则要进行转换
		if (sdate.length() == 8)
			sdate = sdate.substring(0, 4) + "-" + sdate.substring(4, 6) + "-" + sdate.substring(6, 8);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(sdate, pos);
		String dateString = formatter.format(strtodate);
		return dateString;
	}

	public static String getNextMonthDay(String sdate, int m) {
		sdate = getOKDate(sdate);
		int year = StringUtil.stringToInteger(sdate.substring(0, 4));
		int month = StringUtil.stringToInteger(sdate.substring(5, 7));
		month = month + m;
		if (month < 0) {
			month = month + 12;
			year = year - 1;
		} else if (month > 12) {
			month = month - 12;
			year = year + 1;
		}
		String smonth = "";
		if (month < 10)
			smonth = "0" + month;
		else
			smonth = "" + month;
		return year + "-" + smonth + "-01";
	}

	/**
	 * 返回一个月中有多少个周几
	 * 
	 * @param yearmonth
	 *            yyyy-MM
	 * @param 1-6:周一到周6, 0:周日
	 * @return
	 */
	public static List<String> getWeekDaysOfMonth(String yearmonth, String weekdayin) {
		List<String> list = new ArrayList<String>();
		String nextmonthfirstday = yearmonth + "-01";
		String nextmonthlastday = getEndDateOfMonth(nextmonthfirstday);
		long days = getDays(nextmonthlastday, nextmonthfirstday);
		String nowmonthstr = nextmonthfirstday.substring(0, 7);
		String weekday = "";
		for (int i = 1; i <= days; i++) {
			String nowday = nowmonthstr + "-" + getZeroDay(i);
			weekday = getWeek(nowday, weekdayin);
			long daysweek = getDays(weekday, nowday);
			if (daysweek > 1) {
				weekday = getNextDay(getWeek(nowday, weekdayin), "-7");
			} else {
				daysweek = getDays(weekday, nowday);
			}
			if (!list.contains(weekday)) {
				list.add(weekday);
			}

		}
		String firstweek = list.get(0);
		if (!firstweek.startsWith(yearmonth)) {
			list.remove(0);
		}
		return list;
	}

	public static Date convertToDate(XMLGregorianCalendar cal) {
		if (null == cal) {
			return null;
		}
		GregorianCalendar ca = cal.toGregorianCalendar();
		return ca.getTime();
	}

	@SuppressWarnings("static-access")
	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date cal) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(cal);
		javax.xml.datatype.DatatypeFactory dtf = null;
		try {
			dtf = DatatypeFactory.newInstance();
			return dtf.newXMLGregorianCalendar(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH) + 1,
					calendar.get(calendar.DAY_OF_MONTH), calendar.get(calendar.HOUR_OF_DAY),
					calendar.get(calendar.MINUTE), calendar.get(calendar.SECOND), calendar.get(calendar.MILLISECOND),
					calendar.get(calendar.ZONE_OFFSET) / (1000 * 60));
		} catch (DatatypeConfigurationException e) {
			return null;
		}
	}

	public static int getYear(Date date) {
		return get(date, 1);
	}

	public static int getMonth(Date date) {
		return get(date, 2) + 1;
	}

	public static int getDay(Date date) {
		return get(date, 5);
	}

	public static synchronized int get(Date date, int field) {
		calendar.setTime(date);
		return calendar.get(field);
	}

	public static int getHour(Date date) {
		return get(date, 11);
	}

	public static int getMinute(Date date) {
		return get(date, 12);
	}

	public static int getSecond(Date date) {
		return get(date, 13);
	}

	/**
	 * @param date
	 * @return long
	 */
	public static long parseDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * Method formatYearMonthDay.
	 * 
	 * @param date
	 *            format: yyyy-MM-dd
	 * @return String
	 */
	public  static String formatYearMonthDay(java.util.Date date) {
		return sdf_YYYY_MM_DD.format(date);
	}

	/**
	 * Method formatDate.
	 * 
	 * @param dt
	 *            format: yyyy-MM-dd HH:mm:ss.sss
	 * @return String
	 */
	@Deprecated
	public  static String formatDate(java.util.Date dt) {
		return formatDate(dt, sdf_YYYY_MM_DD_HH_MM_SS);
//		return sdf_YYYY_MM_DD_HH_MM_SS.format(dt);
	}
	
	/**
	 * <p>Description:</p>
	 * @param dt
	 * @return  format: yyyyMMddHHmmss
	 * @author fuxiaofeng on 2015年4月1日
	 */
	@Deprecated
	public  static String formatDateAll(java.util.Date dt) {
		return sdf_YYYYMMDDHHMMSS.format(dt);
	}
	/**
	 * Method formateSimpleDate.
	 * 
	 * @param date
	 *            foramt: yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	@Deprecated
	public  static String formateSimpleDate(java.util.Date date) {
		return formatDateDefault(date);
//		return sdf_YYYY_MM_DD_HH_MM_SS.format(date);
	}

	/**
	 * Method parseDate.
	 * 
	 * @param s
	 *            format:yyyy-MM-dd HH:mm:ss.sss
	 * @return Date
	 */
	public  static java.util.Date parseDate(String s) {
		try {
			return sdf_YYYY_MM_DD_HH_MM_SS.parse(s);
		} catch (java.text.ParseException e) {
			throw new IllegalArgumentException("日期格式错误:" + s + " format:yyyy-MM-dd HH:mm:ss.sss");
		}
	}

	/**
	 * Method customizeParseDate.
	 * 
	 * @param dateTime
	 * @param dateFormat
	 * @return Date
	 */
	public static java.util.Date customizeParseDate(String dateTime, String dateFormat) {
		try {
			SimpleDateFormat simpleFormat = new SimpleDateFormat(dateFormat);
			return simpleFormat.parse(dateTime);
		} catch (java.text.ParseException e) {
			throw new IllegalArgumentException("日期格式错误:" + dateTime + " format:" + dateFormat);
		}
	}

	/**
	 * Method parseDateTime.
	 * 
	 * @param datetime
	 * @return Date
	 */
	public  static java.util.Date parseDateTime(String datetime) {
		try {
			return sdf_YYYY_MM_DD_HH_MM_SS.parse(datetime);
		} catch (java.text.ParseException e) {
			throw new IllegalArgumentException("日期格式错误:" + datetime + " format:yyyy-MM-dd HH:mm:ss");
		}
	}

	/**
	 * Method getCurrentDateTime. get Current DateTime，format: yyyy-MM-dd
	 * HH:mm:ss
	 * 
	 * @return String
	 */
	public  static String getCurrentDateTime() {
		String dateTime = null;
		try {
			Calendar sysTime = Calendar.getInstance();
			dateTime = sdf_YYYY_MM_DD_HH_MM_SS.format(sysTime.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateTime;
	}

	/**
	 * <pre>
	 * 返回系统时间
	 * @return
	 * @author <a href="mailto:liyouliang@feinno.com">李友良</a>
	 * 2010-11-24 上午03:07:39
	 * </pre>
	 */
	public static Date now() {
		return new Date();
	}

	/**
	 * 取得当天的零点时间
	 * 
	 * @return
	 * @auther <a href="mailto:zhouhongfu@feinno.com">Hongfu Zhou</a> 2010-11-30
	 *         上午09:15:21
	 */
	public static Date getZeroDate() {
		String date = toString(new Date(), FORMAT_YYYY_MM_DD);
		return toDate(date, FORMAT_YYYY_MM_DD);
	}

	/**
	 * Method getCurrentDateTimeMillisecond. get Current DateTime，format:
	 * yyyy-MM-dd HH:mm:ss.sss
	 * 
	 * @return String
	 */
	public  static String getCurrentDateTimeMillisecond() {
		String dateTime = null;
		try {
			Calendar sysTime = Calendar.getInstance();
			dateTime = sdf_YYYY_MM_DD_HH_MM_SS.format(sysTime.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateTime;
	}

	/**
	 * java.util.datez转换成字符串
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 * @auther <a href="mailto:lihanpei@feinno.com">lihanpei</a> 2011-9-26
	 *         上午09:36:25
	 */
	public static String utilDate2Str(Date date, String dateFormat) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(dateFormat);
			String dateStr = df.format(date);
			Date newDate = java.sql.Date.valueOf(dateStr.substring(0, 10));
			String newDateStr = sdf_YYYY_MM_DD.format(newDate);
			return newDateStr;
		}
		return null;
	}

	/**
	 * 
	 * 字符串转TimeStamp
	 * 
	 * @param dateString
	 *            数据字符串
	 * @return dateFormat 字符串格式
	 * @auther <a href="mailto:lihanpei@feinno.com">lihanpei</a>
	 * @since 2011-9-17 下午01:50:55
	 */
	public static Timestamp stringToTimestamp(String dateString, String dateFormat) {
		if (!StringUtil.isEmpty(dateString)) {
			SimpleDateFormat df = new SimpleDateFormat(dateFormat);
			try {
				Date date = df.parse(dateString);
				Timestamp ts = Timestamp.valueOf(sdf_YYYY_MM_DD_HH_MM_SS.format(date));
				return ts;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获得当期时间
	 * 
	 * @return
	 * @auther <a href="mailto:lihanpei@feinno.com">lihanpei</a> 2011-9-17
	 *         上午10:27:58
	 */
	public static java.sql.Date nowSqlDate() {
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		return sqlDate;
	}

	/**
	 * 
	 * 字符串转TimeStamp
	 * 
	 * @param dateString
	 * @return
	 * @auther <a href="mailto:lihanpei@feinno.com">lihanpei</a> 2011-9-13
	 *         上午11:25:52
	 */
	public static Timestamp stringToTimestamp(String dateString) {
		if (dateString != null && !"".equals(dateString)) {
			Timestamp ts = Timestamp.valueOf(dateString);
			return ts;
		}
		return null;
	}

	/**
	 * 
	 * 把字符串转换为java.sql.Date
	 * 
	 * @param dateStr
	 * @return
	 * @auther <a href="mailto:lihanpei@feinno.com">lihanpei</a> 2011-9-17
	 *         上午11:32:43
	 */
	public static java.sql.Date convertStringToSqlDate(String dateStr) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlDate = null;
		try {
			java.util.Date date = bartDateFormat.parse(dateStr);
			sqlDate = new java.sql.Date(date.getTime());

		} catch (Exception ex) {
			ex.getMessage();
		}
		return sqlDate;
	}

	/**
	 * 获得当前的时间
	 * 
	 * @return
	 * @auther <a href="mailto:lihanpei@feinno.com">lihanpei</a> 2011-9-17
	 *         上午10:26:07
	 */
	public static Timestamp nowTime() {
		Date date = new Date();
		Timestamp nousedate = new Timestamp(date.getTime());

		return nousedate;
	}

	public static String getChineseMonthDay(Date date) {
		int day = getDay(date);
		int month = getMonth(date);
		return month + "月" + day + "日";
	}
	
	// 秒转换成两位的时间，格式：HH:mm:ss
	public static String turnSecondsToTimestring(int seconds) {
			String result = "";
			int hour = 0, min = 0, second = 0;
			hour = seconds / 3600;
			min = (seconds - hour * 3600) / 60;
			second = seconds - hour * 3600 - min * 60;
			if (hour < 10) {
				result += "0" + hour + ":";
			} else {
				result += hour + ":";
			}
			if (min < 10) {
				result += "0" + min + ":";
			} else {
				result += min + ":";
			}
			if (second < 10) {
				result += "0" + second;
			} else {
				result += second;
			}
			return result;
		}
	
	// 秒转换成两位的时间，格式：HH:mm:ss
	//TODO 需要与上面的方法和并
    public static String turnSecondsToCnTimes(int seconds) {
        String result = "";
        int hour = 0, min = 0, second = 0;
        hour = seconds / 3600;
        min = (seconds - hour * 3600) / 60;
        second = seconds - hour * 3600 - min * 60;
        if (hour < 10) {
            result += "0" + hour + "小时";
        } else {
            result += hour + "小时";
        }
        if (min < 10) {
            result += "0" + min + "分";
        } else {
            result += min + "分";
        }
        if (second < 10) {
            result += "0" + second+"秒";
        } else {
            result += second+"秒";
        }

        return result;

    }
	/**
	 * <p>Description:日期比较</p>
	 * @param one 第一个日期
	 * @param another 被比较的日期
	 * @return 0:相等;1:第一个大于被比较的;-1:第一个小于被比较的
	 * @author tangqiang on 2015年1月15日
	 */
	public static int compare(Date one,Date another){
		if(one==null) return -1;
		if(another==null) return 1;
		long o = one.getTime();
		long a = another.getTime();
		if(o==a) return 0;
		if(o>a) return 1;
		return -1;
	}
	
	/**
	 * <p>Description:将秒转换成时分秒形式</p>
	 * @param seconds 秒
	 * @return
	 * @author tangqiang on 2015年1月23日
	 */
	public static Integer[] toHMS(int seconds){
		Integer[] time = new Integer[3];
//		StringBuffer s = new StringBuffer();
		if(seconds > 3600){//小时
			int HH = seconds/3600;
			time[0] = HH;
//			s.append(HH).append("小时");
			seconds = seconds - 3600*HH;
		}else{
			time[0] = 0;
		}
		if(seconds > 60){
			int MM = seconds/60;
			time[1] = MM;
//			s.append(MM).append("分钟");
			seconds = seconds - MM * 60;
		}else{
//			s.append("0分钟");
			time[1] = 0;
		}
		time[2] = seconds;
//		s.append(seconds).append("秒");
//		return s.toString();
		return time;
	}

	/**
	 * <p>Description:判断是否同一天</p>
	 * @param date1
	 * @param date2
	 * @return
	 * @author tangqiang on 2015年4月8日
	 */
    public static boolean isSameDay(Date date1, Date date2) {
    	return DateUtils.isSameDay(date1, date2);
//        if (date1 == null || date2 == null) {
//            throw new IllegalArgumentException("日期不能为空");
//        }
//        Calendar cal1 = Calendar.getInstance();
//        cal1.setTime(date1);
//        Calendar cal2 = Calendar.getInstance();
//        cal2.setTime(date2);
//        return isSameDay(cal1, cal2);
    }
    /**
     * <p>Description:判断是否同一天</p>
     * @param cal1
     * @param cal2
     * @return
     * @author tangqiang on 2015年4月8日
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
    	return DateUtils.isSameDay(cal1, cal2);
//        if (cal1 == null || cal2 == null) {
//            throw new IllegalArgumentException("日期不能为空");
//        }
//        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
//                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
//                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
    /**
     * <p>Description:判断是否同一年</p>
     * @param cal1
     * @param cal2
     * @return
     * @author tangqiang on 2015年4月8日
     */
    public static boolean isSameYear(Calendar cal1, Calendar cal2){
    	if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("日期不能为空");
        }
    	return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR));
    }
    
    /**
     * <p>Description:判断是否同一年</p>
     * @param date1
     * @param date2
     * @return
     * @author tangqiang on 2015年4月8日
     */
    public static boolean isSameYear(Date date1, Date date2){
    	if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("日期不能为空");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameYear(cal1, cal2);
    }
}
