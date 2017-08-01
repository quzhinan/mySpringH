package com.qzn.controllers;

public class RedirectPage extends Page {

	public RedirectPage(String actionName) {
		setViewName(makePageNameFromRequest(actionName));
	}

	public RedirectPage(String actionName, Object...objects) {
		setViewName(makePageNameFromRequest(actionName, objects));
	}
}
