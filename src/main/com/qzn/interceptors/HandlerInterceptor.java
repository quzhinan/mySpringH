package com.qzn.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HandlerInterceptor extends HandlerInterceptorAdapter {

	public final String ADMIN_MEDICALCHECKUP_MENU = "tiles.view.admin.medicalCheckup.menu";
	public final String ADMIN_MEDICALCHECKUP_RESULT = "tiles.view.admin.medicalCheckupResult";
	public final String ADMIN_MEDICALCHECKUP_EDIT = "tiles.view.admin.medicalCheckupEdit";
	public final String ADMIN_MEDICALCHECKUP_NEW = "tiles.view.admin.medicalCheckupNew";
	public final String ADMIN_MEDICALCHECKUP_ENSURE = "tiles.view.admin.medicalCheckupLoginEnsure";
	public final String ADMIN_MEDICALCHECKUP_RESULT_SP = "tiles.view.admin.smartphone.medicalCheckupResult";
	public final String ADMIN_MEDICALCHECKUP_EDIT_SP = "tiles.view.admin.smartphone.medicalCheckupEdit";
	public final String ADMIN_MEDICALCHECKUP_NEW_SP = "tiles.view.admin.smartphone.medicalCheckupNew";
	public final String ADMIN_MEDICALCHECKUP_ENSURE_SP = "tiles.view.admin.smartphone.medicalCheckupLoginEnsure";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
