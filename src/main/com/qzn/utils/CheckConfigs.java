package com.qzn.utils;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.qzn.daos.UserDao;

public class CheckConfigs implements ApplicationContextAware {

	@Autowired
	private UserDao userDao;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		try {
//			checkEnvironmentVariable();
			checkDataSoucesContects();
		} catch (Exception e) {
		}
	}

//	private void checkEnvironmentVariable() {
//		Map<String, String> environmentVariables = System.getenv();
//	}
//
//	private boolean validateFilePath(String path) {
//		File tmpFile = new File(path);
//		if (tmpFile.exists()) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	private void checkDataSoucesContects() {
		try (Session session = userDao.openSession()) {
			Transaction t = session.beginTransaction();
			t.commit();
		} catch (Exception e) {
		}
	}
}
