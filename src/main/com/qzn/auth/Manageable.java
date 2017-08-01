package com.qzn.auth;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public interface Manageable extends Serializable {

	Long getId();
	String getFullname();
	
	boolean isHasRole(HttpServletRequest request, String type, String level);
	
}
