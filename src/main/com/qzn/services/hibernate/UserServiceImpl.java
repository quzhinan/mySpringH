package com.qzn.services.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qzn.controllers.Pagination;
import com.qzn.daos.Dao;
import com.qzn.daos.UserDao;
import com.qzn.models.User;
import com.qzn.services.UserService;
import com.qzn.utils.DateUtil;
import com.qzn.utils.KeyUtil;
import com.qzn.utils.PropertyUtil;

@Service
public class UserServiceImpl extends AbstractService<User, Long> implements UserService {

	private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public Dao<User, Long> getDao() {
		return userDao;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Pagination<User> findAllUsersByPage(int pageSize, int startIndex, Map<String, String> search)
			throws Exception {
		return userDao.loadPage(pageSize, startIndex, search);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUser(User user) throws Exception {
		userDao.save(user);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public User auth(String username, String password) {

		User result = null;
		if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
			User user = userDao.findByProperty("username", username);

			if (user != null && !StringUtils.isEmpty(user.getPassword())) {
				if (user.getLoginLockStatus() == User.LOGIN_LOCK_STATUS_LOCKING) {
					String time = PropertyUtil.getPropertyValue("login.error.clear.time");
					Date clearTime = DateUtil.addTime(user.getUpdateDatetime(), Calendar.MINUTE,
							Integer.parseInt(time));
					if (clearTime.getTime() <= new Date().getTime()) {
						user.setLoginErrorCount(0);
						user.setLoginLockStatus(User.LOGIN_LOCK_STATUS_UNLOCK);
						userDao.update(user);
					} else {
						result = user;
					}
				}
				if (KeyUtil.md5(password).equals(user.getPassword())) {
					result = user;
				} else {
					int errorCount = user.getLoginErrorCount() + 1;
					String maxcount = PropertyUtil.getPropertyValue("login.error.count");
					if (errorCount >= Integer.parseInt(maxcount)) {
						user.setLoginLockStatus(User.LOGIN_LOCK_STATUS_LOCKING);
					}
					user.setLoginErrorCount(errorCount);
					userDao.update(user);
				}
			}
		}
		return result;
	}

}