package com.qzn.services.hibernate;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qzn.controllers.Pagination;
import com.qzn.daos.Dao;
import com.qzn.daos.UserDao;
import com.qzn.models.User;
import com.qzn.services.UserService;

@Service
public class UserServiceImpl extends AbstractService<User, Long> implements UserService {

	private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public Dao<User, Long> getDao() {
		return userDao;
	}
	
	public Pagination<User> findAllUsersByPage(int pageSize, int startIndex, Map<String, String> search) throws Exception{
		return userDao.loadPage(pageSize, startIndex, search);
	}

}