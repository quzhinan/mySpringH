package com.qzn.helper;

import com.qzn.thread.defaultThread;
import com.qzn.utils.PropertyUtil;

public class SystemOptionsHelper {

	/** Helper Instance */
	private static SystemOptionsHelper instance;

	public static SystemOptionsHelper getInstance() {
		return instance;
	}

	public void setInstance(SystemOptionsHelper helper) throws Exception {
		instance = helper;
	}

	public void init() throws Exception {
		startThread();
	}

	/** Helper Instance End */

	public void destroy() {
	}

	/**
	 * Threadを起動する
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

}
