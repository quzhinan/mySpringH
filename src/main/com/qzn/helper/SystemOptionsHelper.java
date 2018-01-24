package com.qzn.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.qzn.daos.UserDao;
import com.qzn.models.Master;
import com.qzn.services.SystemOptionsService;
import com.qzn.thread.defaultThread;
import com.qzn.utils.PropertyUtil;
import com.qzn.utils.StringUtil;

public class SystemOptionsHelper {

	private static List<Master> masterOptions = new ArrayList<Master>();
	/** Helper Instance */
	private static SystemOptionsHelper instance;

	@Autowired
	private SystemOptionsService systemOptionsService;

	@Autowired
	private UserDao userDao;

	public static SystemOptionsHelper getInstance() {
		return instance;
	}

	public void setInstance(SystemOptionsHelper helper) throws Exception {
		instance = helper;
	}

	public void init() throws Exception {
		initSystemData();
		startThread();
	}

	/** Helper Instance End */

	public void destroy() {
	}

	/**
	 * Thread
	 * 
	 * @throws Exception
	 */
	public synchronized void startThread() throws Exception {
		Integer threadNum = Integer.valueOf(PropertyUtil.getPropertyValue("thread.num"));
		defaultThread defaultThread = new defaultThread();
		for (int i = 1; i <= threadNum; i++) {
			Thread t = new Thread(defaultThread);
			t.start();
		}
	}

	public synchronized void initSystemData() throws Exception {
		masterOptions.clear();
		masterOptions.addAll(systemOptionsService.loadAllMasters());
	}

	public List<Master> getMaster(String code) {
		List<Master> result = new ArrayList<Master>();
		for (Master master : masterOptions) {
			if (master.getCode().equals(code)) {
				result.add(master);
			}
		}
		return result;
	}

	public Master getMaster(String code, String value) {
		Master result = null;
		for (Master master : masterOptions) {
			if (master.getCode().equals(code) && master.getValue().equals(value)) {
				result = master;
				break;
			}
		}
		return result;
	}

	public String getMasterLabel(String code, String value) {
		Master master = this.getMaster(code, value);
		return master == null ? StringUtil.STRING_EMPTY : master.getLabel();
	}

	public void checkEnvironmentVariable() {
		Map<String, String> environmentVariables = System.getenv();
		environmentVariables.get(StringUtil.STRING_EMPTY);
	}

	public boolean checkFilePathExist(String path) {
		File tmpFile = new File(path);
		if (tmpFile.exists()) {
			return true;
		} else {
			return false;
		}
	}

	public void checkDataSoucesContects() {
		try {
			Session session = userDao.openSession();
			Transaction t = session.beginTransaction();
			t.commit();
		} catch (Exception e) {
		}
	}

}
