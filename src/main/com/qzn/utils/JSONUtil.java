package com.qzn.utils;

import org.codehaus.jackson.map.ObjectMapper;

public class JSONUtil {

	public static String convertJsonFromObject(Object object) throws Exception {
		ObjectMapper obj = new ObjectMapper();
		return obj.writeValueAsString(object);
	}
}