package com.qzn.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jp.iesolutions.core.models.Environment;
import jp.iesolutions.core.models.Master;
import jp.iesolutions.core.services.BufferEmailService;
import jp.iesolutions.core.services.SystemOptionsService;
import jp.iesolutions.core.thread.MailSendThread;

public class SystemOptionsHelper {

	/* System Parameter List */
	private static List<Master> masterOptions = new ArrayList<Master>();
	private static List<Environment> environmentOptions =  new ArrayList<Environment>();
	/* System Parameter List End */
	
	@Autowired
	private SystemOptionsService systemOptionsService;
	
	@Autowired
	private BufferEmailService bufferEmailService;

	/** Helper Instance */
	private static SystemOptionsHelper instance;
	public static SystemOptionsHelper getInstance() { return instance; }
	public void setInstance(SystemOptionsHelper helper) throws Exception {
		instance = helper;
	}
	/** Helper Instance End */

	public void init() throws Exception {
		instance.refresh();
		startMailSendThread();
	}
	
	public void destroy() {
	}
	
	/**
	 * メール送信Threadを起動する
	 * @throws Exception
	 */
	public synchronized void startMailSendThread() throws Exception {
		Integer threadNum = SystemOptionsHelper.getInstance().getEnvironmentIntegerValue("mail.send.param", "thread.num", 5);
		MailSendThread mailSendThread = new MailSendThread(bufferEmailService);
		for (int i= 1;i<=threadNum; i++) {
			Thread t = new Thread(mailSendThread);
	        t.start();
		}
	}
	
	public synchronized void refresh() throws Exception {
		masterOptions.clear();
		environmentOptions.clear();
		masterOptions.addAll(systemOptionsService.loadAllMasters());
		environmentOptions.addAll(systemOptionsService.loadAllEnvironments());
	}
	
	public List<Master> getMasters(String code) {
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
		return master == null ? "" : master.getLabel();
	}
	
	public List<Environment> getEnvironments(String key) {
		List<Environment> result = new ArrayList<>();
		for (Environment environment : environmentOptions) {
			if (environment.getKey().equals(key)) {
				result.add(environment);
			}
		}
		return result;
	}

	public Environment getEnvironment(String key, String name) {
		Environment result = null;
		for (Environment environment : environmentOptions) {
			if (environment.getKey().equals(key) && environment.getName().equals(name)) {
				result = environment;
				break;
			}
		}
		return result;
	}

	public String getEnvironmentStringValue(String key, String name) {
		Environment environment = this.getEnvironment(key, name);
		return environment == null ? "" : environment.getValue();
	}

	public Integer getEnvironmentIntegerValue(String key, String name) {
		Environment environment = this.getEnvironment(key, name);
		return environment == null ? null : Integer.valueOf(environment.getValue());
	}
	
	public Integer getEnvironmentIntegerValue(String key, String name, Integer defaultValue) {
		Integer result = getEnvironmentIntegerValue(key, name); 
		return result == null ? defaultValue : result;
	}
}
