package com.qzn.daos.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.qzn.daos.UserDao;
import com.qzn.models.AdminUser;

@Repository
public class UserDaoImpl extends AbstractDao<AdminUser, Long> implements UserDao {

	private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

	// @Override
	// public Class<User> getModelClass() throws DataAccessException {
	// return User.class;
	// }

}