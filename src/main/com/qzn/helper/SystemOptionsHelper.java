package com.qzn.helper;

import com.qzn.thread.defaultThread;

public class SystemOptionsHelper {

	/** Helper Instance */
	private static SystemOptionsHelper instance;

	public static SystemOptionsHelper getInstance() {
		return instance;
	}

	public void setInstance(SystemOptionsHelper helper) throws Exception {
		instance = helper;
	}

	public void init(){
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
		Integer threadNum = 0;
		defaultThread defaultThread = new defaultThread();
		for (int i = 1; i <= threadNum; i++) {
			Thread t = new Thread(defaultThread);
			t.start();
		}
	}

}
