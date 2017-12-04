package com.qzn.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebUtil {

	public static final String WEBUTIL_DEFAULT_ENCODING = "UTF-8";

	private static List<String> acquirerSecurityIpList;

	private static String HTTP_HEADER_KEY_CSP = "Content-Security-Policy";

	private static String HTTP_HEADER_KEY_STS = "Strict-Transport-Security";

	private static String HTTP_HEADER_KEY_XCTO = "X-Content-Type-Optinos";

	private static String HTTP_HEADER_KEY_XSSP = "X-XSS-Protection";

	/**
	 * 安全header设置
	 * 
	 * @param response
	 */
	public static void setSecurityHttpHeader(HttpServletResponse response) {

		if (StringUtil.isNotBlank(PropertyUtil.getPropertyValue("content.security.policy"))) {
			response.setHeader(HTTP_HEADER_KEY_CSP, PropertyUtil.getPropertyValue("content.security.policy"));
		}

		if (StringUtil.isNotBlank(PropertyUtil.getPropertyValue("strict.transport.security"))) {
			response.setHeader(HTTP_HEADER_KEY_STS, PropertyUtil.getPropertyValue("strict.transport.security"));
		}

		if (StringUtil.isNotBlank(PropertyUtil.getPropertyValue("XContent.type.options"))) {
			response.setHeader(HTTP_HEADER_KEY_XCTO, PropertyUtil.getPropertyValue("XContent.type.options"));
		}

		if (StringUtil.isNotBlank(PropertyUtil.getPropertyValue("XXss.protection"))) {
			response.setHeader(HTTP_HEADER_KEY_XSSP, PropertyUtil.getPropertyValue("XXss.protection"));
		}

	}

	public static boolean checkAcquirerSecurityIp(String ip) {
		if (acquirerSecurityIpList == null) {
			String ipListStr = PropertyUtil.getPropertyValue("acquirer.security.ip");
			if (StringUtils.isNotEmpty(ipListStr)) {
				acquirerSecurityIpList = Arrays.asList(ipListStr.split(";"));
			} else {
				acquirerSecurityIpList = new ArrayList<String>();
			}
		}
		if (acquirerSecurityIpList.isEmpty()) {
			return true;
		}
		return acquirerSecurityIpList.contains(ip);
	}

	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	/**
	 * 用指定编码对url进行编码
	 * 
	 * @param str
	 *            要进行编码的url
	 * @param encoding
	 *            编码格式
	 * @return 编码后的url
	 */
	public static String urlEncode(String str, String encoding) {

		if (str == null || str.length() == 0) {
			return str;
		}

		String result = StringUtil.STRING_EMPTY;

		try {

			result = URLEncoder.encode(str, encoding);

		} catch (UnsupportedEncodingException e) {

			Logger logger = LogManager.getLogger(WebUtil.class);
			logger.error(e.getMessage(), e);
			result = StringUtil.STRING_EMPTY;
		}

		return result;
	}
}
