package com.qzn.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Authenticator {
	
	private static String SESSION_KEY_ACTIVE_USER = "active_user_authenticator";

	public static HttpServletRequest loadCurrentRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	public static void saveActiveUser(ActiveUser<?> activeUser) {
		HttpServletRequest request = loadCurrentRequest();
		request.getSession().setAttribute(SESSION_KEY_ACTIVE_USER, activeUser);
	}
	
	public static ActiveUser<?> loadActiveUser(HttpServletRequest request) {
		ActiveUser<?> activeUser = (ActiveUser<?>)request.getSession().getAttribute(SESSION_KEY_ACTIVE_USER);
		if (activeUser == null) {
			activeUser = new ActiveUser<>();
		}
		return activeUser;
	}

	public static ActiveUser<?> loadActiveUser() {
		return loadActiveUser(loadCurrentRequest());
	}

	public static void clearActiveUser() {
		HttpServletRequest request = loadCurrentRequest();
		request.getSession().removeAttribute(SESSION_KEY_ACTIVE_USER);
	}
	
}
