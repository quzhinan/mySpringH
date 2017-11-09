package com.qzn.utils;

public class StringUtils {

	/**
	 * 空文字列
	 */
	public static final String STRING_EMPTY = "";

	/**
	 * 半角空格
	 */
	public static final String HALF_SPACE = " ";

	/**
	 * 空值判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if (null == str || STRING_EMPTY.equals(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 非空值判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 文字列byte长度取得
	 * 
	 * @param value
	 *            文字列
	 * @return byte长度
	 * 
	 */
	public static int getByteLength(String value) {
		if (value == null || STRING_EMPTY.equals(value)) {
			return 0;
		}
		String tempVal = value.replaceAll("[^\\x00-\\xff]", "**");
		return tempVal.length();
	}

	/**
	 * 文字列相等判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean compare(String src, String dist) {
		if (null != src && null == dist || null == src && null != dist) {
			return false;
		}
		if (null == src && null == dist) {
			return true;
		}
		if (src.equals(dist)) {
			return true;
		}
		return false;
	}

	/**
	 * null转换为空
	 * 
	 * @param objIn
	 * @return
	 */
	public static String null2str(Object objIn) {
		if (null == objIn) {
			return STRING_EMPTY;
		}
		return objIn.toString();
	}

}
