package com.qzn.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AopInterceptor {
	private static Logger log = LoggerFactory.getLogger(AopInterceptor.class);

	public void logOutputBeforeMethod() {
		log.info("方法开始执行");
	}

	public void logOutputAfterReturn() {
		log.info("方法正常执行完毕");
	}

	public void logOutputAfterThrows() {
		log.error("方法出现异常");
	}

	public void logOutputAfterMethod() {
		log.info("方法结束");
	}

}
