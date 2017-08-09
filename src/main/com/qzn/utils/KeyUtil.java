package com.qzn.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class KeyUtil {

	private static DateFormat data_format = new SimpleDateFormat("ssddmmyyyyHHSSSMM");
	private static DateFormat data_format_timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	private static String[] key_map_alpha = {
			"a", "k", "2", "u", "0", "s", "5", "r",
			"p", "1", "j", "t", "l", "4", "i", "q", "w",
			"v", "b", "7", "8", "e", "h", "6", "f", "z",
			"c", "n", "d", "3", "x", "m", "y", "9", "g",
			};
	private static int key_map_alpha_count = key_map_alpha.length;
	
	private static int random(int min, int max) {
		return (int)(min+Math.random()*(max-min+1));
	}

	public static String generate(int length) {
		String result = "";
		return result;
	}

	public static String generateTimestampKey() {
		return data_format_timestamp.format(new Date()) + random(10000000, 99999999);
	}

	public static String generateProductKey() {
		StringBuffer result = new StringBuffer(); 
		Long value = Long.valueOf(data_format.format(new Date()) + random(10, 99));
		while (value > 0) {
			result.append(key_map_alpha[(int) (value % key_map_alpha_count)]);
			value = value / key_map_alpha_count;
		}
		while (result.length() < 16) {
			result.append(key_map_alpha[random(0, key_map_alpha_count-1)]);
		}
		return result.toString().toUpperCase();
	}
	
	public static String md5(String value) {
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		String password = md5PasswordEncoder.encodePassword(value.toLowerCase(), null);
		return password;
	}
}
