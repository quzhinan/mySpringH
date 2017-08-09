package com.qzn.controllers;

import javax.servlet.http.HttpServletRequest;

public class CurrentPage extends Page {

	public CurrentPage(HttpServletRequest request) {
		setViewName(makePageNameFromRequest(request));
	}

	public CurrentPage(HttpServletRequest request, Object... objects) {
		setViewName(makePageNameFromRequest(request));
		setKeysAndObjects(objects);
	}
}
