package com.qzn.services;

import java.util.Map;

import com.qzn.controllers.Pagination;
import com.qzn.models.User;

public interface UserService extends Service<User, Long> {

	Pagination<User> findAllUsersByPage(int pageSize, int startIndex, Map<String, String> search) throws Exception;
	
}
