package com.qzn.auth;

import java.io.Serializable;

public interface UserInfo extends Serializable {

	Long getId();

	String getUsername();

	String getFullname();

	String getEmail();

}
