package com.qzn.daos;

import java.util.Map;

import com.qzn.controllers.Pagination;
import com.qzn.models.User;

public interface UserDao extends Dao<User, Long> {

	Pagination<User> loadPage(int pageSize, int startIndex, Map<String, String> search) throws Exception;
}
