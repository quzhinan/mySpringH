package com.qzn.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.util.StringUtils;

public class DateUtil {

	public static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";
	public static final String DEFAULT_DATE_YYYYMM = "yyyy/MM";
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
	public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_FORMAT_YYYYMMDD = "yyyyMMdd";
	
	public static final String DATA_FORMAT_YYYYMMDD_JAPAN = "yyyy年MM月dd日";
	
	public static final String TIMESTAMP_FORMAT = "yyyyMMddHHmmss";
	
	public static final String DATETIME_FORMAT_LOG = "dd/MM/yyyy:HH:mm:ss+ 0900";
	
	public static String dateFormat(Date obj, String dateFormat) {
		String date = "";
		if(obj != null) {
			SimpleDateFormat sdf=new SimpleDateFormat(dateFormat, Locale.JAPAN); 
			return sdf.format(obj);
		}
		return date;
	}
	
	public static String getTimeStamp() {

        return toDateTimeString( getSysdate(), TIMESTAMP_FORMAT );
    }
	
	/**
     * 現在日付のDateオブジェクトを生成します。
     * このメソッドが返す時刻の精度は1秒単位です。java.util.Dateの持つミリ秒単位の精度は切り捨てられます。
     *
     * @return 現在日付のDateオブジェクトを返します。
     */
    public static Date getSysdate() {

        return new Date( ( System.currentTimeMillis() / 1000 ) * 1000 );
    }
    
    public static Date truncateDate( Date targetDate ) {

        return fromString( toDateString( targetDate ) );
    }
    
    public static String toDateString( Date date ) {

        String result;
        if ( date == null ) {
            result = "";
        } else {
            result = toDateTimeString( date, DEFAULT_DATE_FORMAT );
        }
        return result;
    }
    
    public static String toDateTimeString( Date d, String format ) {

        return ( new SimpleDateFormat( format ) ).format( d );
    }
    
    public static Date fromString( String dateString ) {

        Date result;
        if ( StringUtils.isEmpty( dateString ) ) {
            result = null;
        } else {
            result = fromString( dateString, false );
        }
        return result;
    }
    
    public static Date fromString( String dateString, boolean isDateTime ) {

        String formatString = DEFAULT_DATE_FORMAT;
        if ( isDateTime ) {
            formatString = DEFAULT_DATETIME_FORMAT;
        }
        return parseString( dateString, formatString );
    }
    
    public static Date fromString( String dateString, String formatString) {
        return parseString( dateString, formatString );
    }
    
    
    public static Date parseString( String dateString, String formatString ) {

        Date result = null;
        DateFormat pattern = new SimpleDateFormat( formatString );
        try {
            pattern.setLenient( false );
            result = pattern.parse( dateString );
        } catch ( ParseException pe ) {
            result = null;
        } catch ( NullPointerException npe ) {
            result = null;
        }
        return result;
    }
    
    public static Date getDateAfter(Date date, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(date);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
        return now.getTime();  
    }
    
   public static Date stringToDate(String string) {
		
	    DateFormat pattern = new SimpleDateFormat( DATE_FORMAT_YYYYMMDD );
		try {
			if (string != null) {
				return pattern.parse(string);
			}
		} catch (ParseException e) {
		}
		return null;
	}
   
   public static String dateToString( Date date ) {

       String result;
       if ( date == null ) {
           result = "";
       } else {
           result = toDateTimeString( date, DEFAULT_FORMAT_YYYYMMDD );
       }
       return result;
   }
   
   /**
    * 2つの日付の日数差を求めます。 endDateからstartDateを引いた差を返します。
    * endDateよりもstartDateの方が後の日付の場合はマイナスの値を返します。 JVMのデフォルトのタイムゾーンに従って処理します。
    * 時分秒以下は無視して計算します。 前提条件： startDate、endDateのいずれもnullでないこと。
    *
    * @param startDate
    *            計算開始日付 java.util.Date
    * @param endDate
    *            計算終了日付 java.util.Date
    * @return 2つの日付の差の日数
    * @throws IllegalArgumentException
    *             startDate、endDateのいずれかがnullの場合
    */
   public static int getDifferenceDays( Date startDate, Date endDate ) {

       if ( startDate == null || endDate == null ) {
           throw new IllegalArgumentException();
       }
       final long oneDateTime = 1000 * 60 * 60 * 24; // 1日ぶんのミリ秒
       Calendar cal = Calendar.getInstance();

       cal.setTime( endDate );
       cal.set( Calendar.HOUR_OF_DAY, 0 );
       cal.set( Calendar.MINUTE, 0 );
       cal.set( Calendar.SECOND, 0 );
       cal.set( Calendar.MILLISECOND, 0 );
       long dateTime = cal.getTimeInMillis();

       cal.setTime( startDate );
       cal.set( Calendar.HOUR_OF_DAY, 0 );
       cal.set( Calendar.MINUTE, 0 );
       cal.set( Calendar.SECOND, 0 );
       cal.set( Calendar.MILLISECOND, 0 );
       dateTime -= cal.getTimeInMillis();

       return (int) ( dateTime / oneDateTime );
   }
    
   /**
    * 指定日時に指定されたタイプ(月/日/時間)にX(月/日/時間)足した時間を返します。
    *
    * @param date
    *            基準日時
    * @param type
    *            タイプ(月/日/時間)
    * @param addition
    *            足す対象(月/日/時間)
    * @return 指定日時(date)にX(addition)(月/日/時間)足した(日付/時間)を返します。
    */
   public static Date addTime( Date date, int type, int addition ) {

       SimpleDateFormat format = new SimpleDateFormat( DEFAULT_DATE_FORMAT );
       format.format( date );
       Calendar c = format.getCalendar();
       c.add( type, addition );
       return c.getTime();
   }
   
	public static String[] getNearlyMonths(int months) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
		
		String[] result = new String[months];
		for (int i=0; i<months; i++) {
	        cale.add(Calendar.MONTH, -1);
	        result[months - i - 1] = dateFormat.format(cale.getTime());
		}
		
		return result;
	}
	
	/**
     * Dateから、該当年月の最終日を取得します
     *
     * @param date
     *            対象となるDateオブジェクト
     * @return 該当年月の最終日を返します。
     */
    public static Date getMonthEndDate( Date date ) {

        SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM" );
        format.format( date );
        Calendar c = format.getCalendar();
        
        Date endDate = immutableCopy(date);
        endDate = setTime(endDate, Calendar.DATE, c.getActualMaximum( Calendar.DATE ));
        return endDate;
    }
    
    /**
     * Dateから、該当年月の最小日を取得します
     *
     * @param date
     *            対象となるDateオブジェクト
     * @return 該当年月の最小日を返します。
     */
    public static Date getMonthFirstDay( Date date ) {

        SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM" );
        format.format( date );
        Calendar c = format.getCalendar();

        Date firstDay = immutableCopy(date);
        firstDay = setTime(firstDay, Calendar.DATE , c.getActualMinimum( Calendar.DATE ));
        return firstDay;
    }
    
    /**
     * 複製されたDateオブジェクトを返します。
     *
     * @param date
     *            対象となるDateオブジェクト
     * @return 複製されたDateオブジェクト。<br>
     *         dateがnullの場合はnullを返します。
     */
    public static Date immutableCopy( Date date ) {

        Date result = null;
        if ( date != null ) {
            result = new Date( date.getTime() );
        }
        return result;
    }

    /**
     * 時分秒を設定します。
     * <p>
     * <dl>
     * <dt><b>処理概要: </b></dt>
     * <dd>指定されたDateの日時に、指定された時分秒をセットします。</dd>
     * </dl>
     * </p>
     *
     * @param date
     * @param hour
     * @param minute
     * @param second
     * @return 引数dateに時分秒(hour,
     *         minute,second)が設定されたDateオブジェクト。引数Dateがnullの場合はnullを返します。
     */
    public static Date setTime( Date date, int hour, int minute, int second ) {

        Date result = null;
        if ( date != null ) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime( date );
            calendar.set( Calendar.HOUR_OF_DAY, hour );
            calendar.set( Calendar.MINUTE, minute );
            calendar.set( Calendar.SECOND, second );
            result = calendar.getTime();
        }
        return result;

    }

    public static Date setTime( Date date, int field, int value ) {

        Date result = null;
        if ( date != null ) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime( date );
            calendar.set( field, value );
            result = calendar.getTime();
        }
        return result;
    }
    
    public static String getPaymentDate(Date date) {
    	Date result = setTime(date, Calendar.DATE, 1);
    	DateFormat pattern = new SimpleDateFormat( "yyyy/MM/" );
    	if (getDD(date)>15) {
    		result = addTime(result, Calendar.MONTH, 1);
            return pattern.format( result ) + "15";
    	} else {
    		return pattern.format( result ) + "末日";
    	}
    }
    
    /**
     * Dateから日付(dd)を取得します。
     *
     * @param date
     *            対象となるDateオブジェクト
     * @return 日付(dd)を返します。
     */
    public static int getDD( Date date ) {

        DateFormat pattern = new SimpleDateFormat( "dd" );
        return Integer.valueOf(pattern.format( date ));
    }
}
