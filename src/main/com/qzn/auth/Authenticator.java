package com.qzn.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Authenticator {

	private final static String SESSION_KEY_ACTIVE_USER = "active_user_authenticator";

	// 获取request
	public static HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes) ra).getRequest();
	}

	// 获取session
	public static HttpSession getSession() {
		HttpServletRequest request = getRequest();
		return request.getSession();
	}

	public static void saveActiveUser(ActiveUser<?> activeUser) {
		HttpServletRequest request = getRequest();
		request.getSession().setAttribute(SESSION_KEY_ACTIVE_USER, activeUser);
	}

	public static ActiveUser<?> loadActiveUser(HttpServletRequest request) {
		ActiveUser<?> activeUser = (ActiveUser<?>) request.getSession().getAttribute(SESSION_KEY_ACTIVE_USER);
		if (activeUser == null) {
			activeUser = new ActiveUser<>();
		}
		return activeUser;
	}

	public static ActiveUser<?> loadActiveUser() {
		return loadActiveUser(getRequest());
	}

	public static void clearActiveUser() {
		HttpServletRequest request = getRequest();
		request.getSession().removeAttribute(SESSION_KEY_ACTIVE_USER);
	}

}
