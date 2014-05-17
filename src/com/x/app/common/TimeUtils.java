package com.x.app.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * Description:时间处理工具包
 * </p>
 * 
 * @author Chenkm
 * @version 1.0
 * @date 2014年5月17日
 */
public class TimeUtils {

	private static DateFormat DF_YYYY = new SimpleDateFormat("yyyy");
	private static DateFormat DF_YYYY_MM = new SimpleDateFormat("yyyy-MM");
	private static DateFormat DF_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat DF_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 获取系统时间
	 * <ul>
	 * <li>sign=0 yyyy</li>
	 * <li>sign=1 yyyy-MM</li>
	 * <li>sign=2 yyyy-MM-dd</li>
	 * <li>sign=3 yyyy-MM-dd HH:mm:ss</li>
	 * </ul>
	 * 
	 * @param sign
	 * @return
	 */
	public static String getNow(int sign) {
		Calendar now = Calendar.getInstance();
		switch (sign) {
		case 0:
			return DF_YYYY.format(now.getTime());
		case 1:
			return DF_YYYY_MM.format(now.getTime());
		case 2:
			return DF_YYYY_MM_DD.format(now.getTime());
		case 3:
			return DF_YYYY_MM_DD_HH_MM_SS.format(now.getTime());
		default:
			return DF_YYYY_MM_DD.format(now.getTime());
		}
	}

	/**
	 * 获取系统时间
	 * <ul>
	 * <li>sign=0 yyyy</li>
	 * <li>sign=1 yyyy-MM</li>
	 * <li>sign=2 yyyy-MM-dd</li>
	 * <li>sign=3 yyyy-MM-dd HH:mm:ss</li>
	 * </ul>
	 * 
	 * @param sign
	 * @return string
	 */
	public static String dateFormat(Date dt, int sign) {
		switch (sign) {
		case 0:
			return DF_YYYY.format(dt);
		case 1:
			return DF_YYYY_MM.format(dt);
		case 2:
			return DF_YYYY_MM_DD.format(dt);
		case 3:
			return DF_YYYY_MM_DD_HH_MM_SS.format(dt);
		default:
			return DF_YYYY_MM_DD.format(dt);
		}
	}

	/**
	 * 将字符串转位日期类型
	 * <ul>
	 * <li>sign=0 yyyy</li>
	 * <li>sign=1 yyyy-MM</li>
	 * <li>sign=2 yyyy-MM-dd</li>
	 * <li>sign=3 yyyy-MM-dd HH:mm:ss</li>
	 * </ul>
	 * 
	 * @param sign
	 * @return date
	 */
	public static Date toDate(String dt, int sign) {
		try {
			switch (sign) {
			case 0:
				return DF_YYYY.parse(dt);
			case 1:
				return DF_YYYY_MM.parse(dt);
			case 2:
				return DF_YYYY_MM_DD.parse(dt);
			case 3:
				return DF_YYYY_MM_DD_HH_MM_SS.parse(dt);
			default:
				return DF_YYYY_MM_DD.parse(dt);
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 当前时间相加或相减天数返回
	 * 
	 * @param day
	 *            /-day
	 * @return yyyy-MM-dd
	 */
	public static String getDay(int day) {
		Calendar time = Calendar.getInstance();
		time.add(Calendar.DATE, day); // 当前时间相减去天数返回最新日期
		return DF_YYYY_MM_DD.format(time.getTime());
	}

	/**
	 * 以友好的方式显示时间
	 * 
	 * @param sdate
	 * @return
	 */
	public static String friendly_time(String sdate) {
		Date time = toDate(sdate, 3);
		if (time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否同一天
		String curDate = dateFormat(cal.getTime(), 2);
		String paramDate = dateFormat(time, 2);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0) {
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
			} else {
				ftime = hour + "小时前";
			}
			return ftime;
		}

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
			else
				ftime = hour + "小时前";
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天";
		} else if (days > 2 && days <= 10) {
			ftime = days + "天前";
		} else if (days > 10) {
			ftime = dateFormat(time, 2);
		}
		return ftime;
	}

	/**
	 * 判断给定字符串时间是否为今日
	 * @param sdate
	 * @return
	 */
	public static boolean isToday(String sdate) {
		boolean b = false;
		Date time = toDate(sdate, 2);
		String today = getNow(2);
		if (time != null) {
			String stime = dateFormat(time, 2);
			if (stime.equals(today)) {
				b = true;
			}
		}
		return b;
	}

}
