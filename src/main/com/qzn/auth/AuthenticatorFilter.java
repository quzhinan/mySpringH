package com.qzn.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AuthenticatorFilter implements Filter {

	private static Log log = LogFactory.getLog(AuthenticatorFilter.class);
	
	private String indexPath;
	List<String> ignoreList = new ArrayList<String>();

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse) res;

		String uri = request.getRequestURI();
		
		boolean isAuthed = false;
		
		if (isIgnored(uri)) {
			isAuthed = true;
		} else {
			ActiveUser<?> user = Authenticator.loadActiveUser();
			if (user != null) {
				isAuthed = true;
			} else {
				log.warn("Not yet authenticated, The request uri is [" + uri +"].");
			}
		}
		
		if (isAuthed) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "" + indexPath);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		indexPath = config.getInitParameter("indexPath");
		ignoreList = Arrays.asList(config.getInitParameter("ignoreList").split(","));
	}

	private boolean isIgnored(String uri) {
		for (String ignore : ignoreList) {
			if (ignore.length() > 0 && StringUtils.contains(uri, ignore)) {
				return true;
			}
		}
		return false;
	}
}
