package com.qzn.services;

import java.util.Map;

import com.qzn.controllers.Pagination;
import com.qzn.models.User;

public interface UserService extends Service<User, Long> {

	Pagination<User> findAllUsersByPage(int pageSize, int startIndex, Map<String, String> search) throws Exception;

	User auth(String username, String password);

	User loadUser(Long id) throws Exception;

	void registerUser(User user) throws Exception;

	void resetPassword(String username) throws Exception;

	void saveUser(User user) throws Exception;

}
