package com.qzn.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler implements HandlerExceptionResolver {

	private static final Logger log = LogManager.getLogger(ExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ex.printStackTrace();
		Map<String, Object> model = new HashMap<String, Object>();
		log.error(ex.getMessage(), ex);
		model.put("errorMessage", "发生系统错误。");
		model.put("isIndexPage", true);
		response.setStatus(500);
		return new ModelAndView("system.error", model);
	}
}
