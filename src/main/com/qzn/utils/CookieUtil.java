package com.qzn.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	public static String getCookie(HttpServletRequest request, String cookieName) {
		String result = StringUtil.STRING_EMPTY;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					result = cookie.getValue();
				}
			}
		}
		return result;
	}

	public static void SetCookie(HttpServletResponse response, String cookieName, String value) {
		Cookie cookie = new Cookie(cookieName, value);
		cookie.setMaxAge(60 * 60 * 24 * 365);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
