package com.qzn.services.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qzn.daos.Dao;
import com.qzn.daos.UserDao;
import com.qzn.models.User;
import com.qzn.services.UserService;
@Service
public class UserServiceImpl extends AbstractService<User, Long>implements UserService {

	private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Override
	public Dao<User, Long> getDao() {
		return userDao;
	}

//	@Transactional(readOnly=false, rollbackFor=Exception.class)
//	public String searchPostAreaCode(String postAreaCode,String postAreaName,int page) throws Exception{
//		
//		return dataHelp.getJsonString();
//	}
}