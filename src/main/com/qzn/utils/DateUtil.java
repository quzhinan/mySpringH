package com.qzn.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static final String DATE_FORMAT_SLASHYYYYMMDD = "yyyy/MM/dd";
	public static final String DATE_FORMAT_SLASHYYYYMM = "yyyy/MM";
	public static final String DATETIME_FORMAT_SLASHYYYYMMDDHHMMSS = "yyyy/MM/dd HH:mm:ss";
	public static final String DATE_FORMAT_HYPHENYYYYMMDD = "yyyy-MM-dd";
	public static final String DATETIME_FORMAT_HYPHENYYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_FORMAT_YYYYMMDD = "yyyyMMdd";
	public static final String DATA_FORMAT_YYYYMMDD_CHINESE = "yyyy年MM月dd日";
	public static final String DATETIME_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String DATETIME_FORMAT_LOG = "yyyy:MM:dd:HH:mm:ss+ 0900";

	public enum CalendarType {
		YEAR(Calendar.YEAR), MONTH(Calendar.MONTH), DATE(Calendar.DATE), HOUR(Calendar.HOUR), MINUTE(
				Calendar.MINUTE), SECOND(Calendar.SECOND), MILLISECOND(Calendar.MILLISECOND);
		private int type;

		private CalendarType(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}
	}

	/**
	 * 系统当前时间生成 毫秒精确时间。
	 *
	 * @return 现在时间
	 */
	public static Date getSysdate() {

		return new Date((System.currentTimeMillis() / 1000) * 1000);
	}
	
	/**
	 * 系统当前时间戳生成 毫秒精确时间。
	 *
	 * @return 现在时间戳
	 */
	public static Timestamp getSysdateTime() {

		return new Timestamp((System.currentTimeMillis() / 1000) * 1000);
	}

	/**
	 * 获取时间字符串
	 * 
	 * @param date
	 *            要格式化的时间
	 * @param dateFormat
	 *            要转化的格式
	 * @return
	 */
	public static String getDateString(Date date, String dateFormat) {

		String result = null;
		DateFormat pattern = new SimpleDateFormat(dateFormat);
		if (date != null) {
			result = pattern.format(date);
		}
		return result;
	}

	public static Date parseDateString(String dateString, String dateFormat) {

		Date result = null;
		if (StringUtil.isNotBlank(dateString)) {
			DateFormat pattern = new SimpleDateFormat(dateFormat);
			try {
				pattern.setLenient(false);
				result = pattern.parse(dateString);
			} catch (Exception npe) {
				result = null;
			}
		}
		return result;
	}

	public static Timestamp parseDateTimeString(String dateTimeString) {
		Timestamp result = null;
		if (StringUtil.isNotBlank(dateTimeString)) {
			dateTimeString = dateTimeString.replaceAll("/", "-");
			try {
				result = Timestamp.valueOf(dateTimeString);
			} catch (Exception e) {
				result = null;
			}
		}
		return result;
	}

	/**
	 * 获取当前时间之后的时间
	 * 
	 * @param date
	 *            当前时间
	 * @param day
	 *            时间差
	 * @return 计算后的时间
	 */
	public static Date getDateAfter(Date date, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 获取日期差
	 *
	 * @param startDate
	 *            开始时间 java.util.Date
	 * @param endDate
	 *            结束时间 java.util.Date
	 * @return 时间差
	 * @throws IllegalArgumentException
	 *             空
	 */
	public static int getDifferenceDays(Date startDate, Date endDate) {

		if (startDate == null || endDate == null) {
			return 0;
		}
		final long oneDateTime = 1000 * 60 * 60 * 24;
		Calendar cal = Calendar.getInstance();

		cal.setTime(endDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long dateTime = cal.getTimeInMillis();

		cal.setTime(startDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		dateTime -= cal.getTimeInMillis();

		return (int) (dateTime / oneDateTime);
	}

	/**
	 * 指定日期时间添加
	 *
	 * @param date
	 *            处理日
	 * @param type
	 *            增加时间类型
	 * @param addition
	 *            增加数量
	 * @return 增加后的时间
	 */
	public static Date addTime(Date date, CalendarType type, int addition) {

		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_SLASHYYYYMMDD);
		format.format(date);
		Calendar c = format.getCalendar();
		c.add(type.getType(), addition);
		return c.getTime();
	}

	/**
	 * 设置指定时间
	 * 
	 * @param date
	 *            要设置的时间
	 * @param type
	 *            要设置的时间类型
	 * @param value
	 *            要设置的时间值
	 * @return 设置之后的时间
	 */
	public static Date setTime(Date date, CalendarType type, int value) {

		Date result = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(type.getType(), value);
			result = calendar.getTime();
		}
		return result;
	}

	/**
	 * 获取参数年月的最后日期
	 *
	 * @param date
	 *            要获取的日期
	 * 
	 * @return 最终日期
	 */
	public static Date getMonthEndDate(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");
		format.format(date);
		Calendar c = format.getCalendar();

		Date endDate = immutableCopy(date);
		endDate = setTime(endDate, CalendarType.DATE, c.getActualMaximum(Calendar.DATE));
		return endDate;
	}

	/**
	 * 当前年月的最小日期
	 *
	 * @param date
	 *            要获取的日期
	 * @return 最小日
	 */
	public static Date getMonthFirstDay(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");
		format.format(date);
		Calendar c = format.getCalendar();

		Date firstDay = immutableCopy(date);
		firstDay = setTime(firstDay, CalendarType.DATE, c.getActualMinimum(Calendar.DATE));
		return firstDay;
	}

	/**
	 * 日期复制
	 *
	 * @param date
	 *            要复制的日期
	 * @return 复制后的日期
	 */
	public static Date immutableCopy(Date date) {

		Date result = null;
		if (date != null) {
			result = new Date(date.getTime());
		}
		return result;
	}

	/**
	 * 时间设定
	 * 
	 * @param date
	 * @param hour
	 * @param minute
	 * @param second
	 * @return 参数设定时间
	 */
	public static Date setTime(Date date, int hour, int minute, int second) {

		Date result = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, hour);
			calendar.set(Calendar.MINUTE, minute);
			calendar.set(Calendar.SECOND, second);
			result = calendar.getTime();
		}
		return result;

	}

}
