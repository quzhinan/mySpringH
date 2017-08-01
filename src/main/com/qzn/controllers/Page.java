package com.qzn.controllers;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;

public class Page extends ModelAndView {

	private static final String TILES_NAME_SUFFIX = ".page";
	private static final String WEB_URL_SUFFIX = ".htm";
	private static final String JSP_PATH_MAP_REGX = "^(([^-]+-)+)([^-]+-[^-]+)$";
	private static final String JSP_PATH_MAP_REGX_SHORT = "^(([^-]+-)+)([^-]+)$";
	private static Pattern JSP_PATH_MAP_PATTERN = Pattern.compile(JSP_PATH_MAP_REGX);
	private static Pattern JSP_PATH_MAP_PATTERN_SHORT = Pattern.compile(JSP_PATH_MAP_REGX_SHORT);
	
	public Page() {
	}

	protected String mapLogicJspToPath(String logicName) {
		Matcher matcher = JSP_PATH_MAP_PATTERN.matcher(logicName);
		if (matcher.find()) {
			return matcher.group(1).replaceAll("-", ".") + matcher.group(3);
		}
		matcher = JSP_PATH_MAP_PATTERN_SHORT.matcher(logicName);
		if (matcher.find()) {
			return matcher.group(1).replaceAll("-", ".") + matcher.group(3);
		}
		return logicName;
	}
	
	public Page(String page) {
		setViewName(mapLogicJspToPath(page) + TILES_NAME_SUFFIX);
	}

	public Page(String page, Object...objects) {
		setViewName(mapLogicJspToPath(page) + TILES_NAME_SUFFIX);
		setKeysAndObjects(objects);
	}

	@SuppressWarnings("unchecked")
	protected void setKeysAndObjects(Object...objects) {
		for (int i=0; i<objects.length; i++) {
			if (objects[i] instanceof Map) {
				super.addAllObjects((Map<String, ?>)objects[i]);
			} else {
				super.addObject(""+objects[i], objects[i+1]);
				i++;
			}
		}
	}
	
	protected String makePageNameFromRequest(String actionName, Object...objects) {
		String result = "redirect:/" + actionName.replaceAll("-", "/") + WEB_URL_SUFFIX;
		String prefix = "?";
		for (int i=0; i<objects.length; i++) {
			result += prefix + objects[i] + "=" + objects[i+1];
			prefix = "&";
			i++;
		}
		return result;
	}
	
	protected String makePageNameFromRequest(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		uri = StringUtils.substringBeforeLast(uri, ".");
		String action = uri.substring(contextPath.length() + 1);
		String[] actionList = action.split("/");
		String pageName = StringUtils.EMPTY;
		for (int i = actionList.length - 1; i >= 0; i--) {
			if (pageName.length() == 0) {
				pageName = pageName + actionList[i];
			} else if (pageName.length() > 0) {
				if (i <= actionList.length - 3 || actionList.length < 3) {
					pageName = actionList[i] + "." + pageName;
				} else {
					pageName = actionList[i] + "-" + pageName;
				}
			}
		}
		return pageName + TILES_NAME_SUFFIX;
	}
	
}
