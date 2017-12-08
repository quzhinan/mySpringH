package com.qzn.controllers;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.qzn.auth.ActiveUser;
import com.qzn.auth.Authenticator;
import com.qzn.models.User;
import com.qzn.utils.StringUtil;

public class AbstractController {

	@Autowired
	@Qualifier("messageSource")
	protected MessageSource messages;

	public String getI18nMessage(String code) {
		Locale locale = LocaleContextHolder.getLocale();
		return messages.getMessage(code, null, locale);
	}

	public String getI18nMessage(String code, Object[] args, String defaultMessage) {
		Locale locale = LocaleContextHolder.getLocale();
		return messages.getMessage(code, args, defaultMessage, locale);
	}

	public String getI18nMessage(String code, Object[] args) {
		Locale locale = LocaleContextHolder.getLocale();
		return messages.getMessage(code, args, locale);
	}

	public String getI18nMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		return messages.getMessage(code, args, defaultMessage, locale);
	}

	public String getI18nMessage(String code, Object[] args, Locale locale) {
		return messages.getMessage(code, args, locale);
	}

	public String getI18nMessage(String code, Locale locale) {
		return messages.getMessage(code, null, locale);
	}

	protected Page Page(String page) {
		return new Page(page);
	}

	protected Page Page(String page, Object... objects) {
		return new Page(page, objects);
	}

	protected Page CurrentPage() {
		return new CurrentPage(Authenticator.getRequest());
	}

	protected Page CurrentPage(Object... objects) {
		return new CurrentPage(Authenticator.getRequest(), objects);
	}

	protected Page RedirectPage(String actionName) {
		return new RedirectPage(actionName);
	}

	protected Page RedirectPage(String actionName, Object... objects) {
		return new RedirectPage(actionName, objects);
	}

	protected User getLoginUser() {
		return (User) Authenticator.loadActiveUser().getUserInfo();
	}

	protected void setLoginUser(User user) {
		Authenticator.saveActiveUser(new ActiveUser<>(user));
	}

	protected String getLoginUid() {
		String userId = StringUtil.STRING_EMPTY;
		if (this.getLoginUser() != null) {
			userId = getLoginUser().getId().toString();
		}
		return userId;
	}
	
	protected HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes) ra).getRequest();
	}

	protected HttpSession getSession() {
		HttpServletRequest request = getRequest();
		return request.getSession();
	}

	@ModelAttribute
	public void preMethod(Model model, HttpServletRequest request, HttpServletResponse response) {

	}

}
