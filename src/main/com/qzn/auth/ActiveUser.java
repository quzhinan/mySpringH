package com.qzn.auth;

import java.io.Serializable;

public class ActiveUser<T extends Manageable> implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean is_authorized = false;
	private T user_info = null;

	public ActiveUser() {
	}

	public ActiveUser(T userInfo) {
		if (userInfo != null) {
			is_authorized = true;
			user_info = userInfo;
		}
	}

	public boolean isAuthorized() {
		return is_authorized;
	}

	public T getUserInfo() {
		return user_info;
	}
}
