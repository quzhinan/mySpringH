package com.qzn.services.hibernate;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.qzn.controllers.Pagination;
import com.qzn.daos.Dao;
import com.qzn.daos.UserDao;
import com.qzn.models.Email;
import com.qzn.models.User;
import com.qzn.services.UserService;
import com.qzn.utils.DateUtil;
import com.qzn.utils.EmailUtil;
import com.qzn.utils.KeyUtil;
import com.qzn.utils.PropertyUtil;
import com.qzn.utils.RandomPasswordUtil;
import com.qzn.utils.VelocitiesUtil;

@Service
public class UserServiceImpl extends AbstractService<User, Long> implements UserService {

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
	public User loadUser(Long id) throws Exception {
		if (StringUtils.isEmpty(id)) {
			return new User();
		} else {
			User user = userDao.get(id);
			if (user != null) {
				return user;
			} else {
				return new User();
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUser(User user) throws Exception {
		userDao.save(user);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void registerUser(User user) throws Exception {
		User newUser = new User();
		String initPassword = RandomPasswordUtil.createPassword();
		String password = KeyUtil.md5(initPassword);
		Timestamp systime = new Timestamp(System.currentTimeMillis());
		newUser.setUsername(user.getUsername());
		newUser.setFullname(user.getFullname());
		newUser.setPassword(password);
		newUser.setAge(user.getAge());
		newUser.setSex(user.getSex());
		newUser.setEmail(user.getEmail());
		newUser.setPower(User.ALL_POWER);
		newUser.setLoginLockStatus(User.LOGIN_LOCK_STATUS_UNLOCK);
		newUser.setLoginErrorCount(0);
		newUser.setPasswordStatus(User.PASSWORD_STATUS_SYSINIT);
		newUser.setDeleteFlag(User.DELETE_FLAG_UNDELETE);
		newUser.setCreateDatetime(systime);
		newUser.setUpdateDatetime(systime);
		sendEmail(user.getEmail(), initPassword);
		userDao.save(newUser);
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
	
	public static void sendEmail(String toAddress, String password) throws Exception{
		Email email = new Email();
		email.setFromEmailAddress(PropertyUtil.getPropertyValue("mail.fromAddress"));
		email.setFromPersonName(PropertyUtil.getPropertyValue("mail.username"));
		email.setToEmailAddresses(new String[] { toAddress });
		email.setSubject("初期密码");
		Map<String, Object> velocityContext = new HashMap<>();
		velocityContext.put("content", password);
		String content = VelocitiesUtil.getVelocityText("email.vm", velocityContext);
		email.setContent(content);
		EmailUtil.sendEmail(email);
	}

}