package com.qzn.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jp.iesolutions.core.models.BufferEmail;
import jp.iesolutions.core.services.BufferEmailService;
import jp.iesolutions.core.web.helpers.SystemOptionsHelper;

public class MailSendThread extends BaseThread  {

	private static final Logger logger = LogManager.getLogger(MailSendThread.class);

	private static final int sleepSecond = SystemOptionsHelper.getInstance().getEnvironmentIntegerValue("mail.send.param","thread.nodata.sleep.second",30);
	
	private static final String processName = "通知履歴送信処理：";
	
	//private static final String MAIL_SEND_TYPE_NAME = " メール送信  ";
	
	//private static final String PUSH_SEND_TYPE_NAME = " PUSH通知 ";
	
	// 処理待ち通知IDリスト
	private List<String> readyList = new ArrayList<String>();
	
	// 処理中通知IDリスト
	private List<String> doingList = new ArrayList<String>();
	
	// 処理待ち通知マップ
	Map<String,BufferEmail> readyMap = new HashMap<String,BufferEmail>();
	
	
	private BufferEmailService bufferEmailService;
	
	public MailSendThread(BufferEmailService bufferEmailService) {
		this.bufferEmailService = bufferEmailService;
	}
	
	@Override
	public void run() {
		
		BufferEmail bufferEmail = null;
		logger.info(processName + Thread.currentThread().getName() + "　処理開始");
		while(true) {
			try {
				bufferEmail = getNextEmail(bufferEmail);
				if (bufferEmail!=null) {
 					logger.info(processName + Thread.currentThread().getName() + "　通知履歴対象ID：" + bufferEmail.getId().toString() + "処理開始");
 					bufferEmailService.sendEmail(bufferEmail);
 					logger.info(processName + Thread.currentThread().getName() + "　通知履歴対象ID：" + bufferEmail.getId().toString() + "処理終了");
				}
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
			
		}
	}

	public synchronized BufferEmail getNextEmail(BufferEmail lastHistory) {
		BufferEmail emailHistory = null;
		
		// 前回処理したデータを処理中データから削除する
		if (lastHistory!=null && lastHistory.getId() !=null) {
			doingList.remove(lastHistory.getId().toString());
		}
		
		// データなし場合、データを再度取得する
		while (readyList == null || readyList.size() == 0) {
			try {
				// 未処理データを再取得する
				List<BufferEmail> emailList = bufferEmailService.loadBufferEmail(SystemOptionsHelper.getInstance().
						getEnvironmentIntegerValue("mail.send.param","search.limit.count",1000));
				
				if (emailList!=null && emailList.size()>0) {
					List<BufferEmail> removeList = new ArrayList<BufferEmail>();
					if (doingList!=null && doingList.size()>0) {
						for (BufferEmail temp : emailList) {
							if (doingList.contains(temp.getId().toString())) {
								removeList.add(temp);
							}
						}
						// 既に処理中のデータを処理待ちデータから削除する
						emailList.removeAll(removeList);
					}
					
					// データを処理待ちデータに設定する
					for (BufferEmail temp : emailList) {
						readyList.add(temp.getId().toString());
						readyMap.put(temp.getId().toString(), temp);
					}
				}
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
			
			if (readyList == null || readyList.size() == 0) {
				try {
					logger.info(String.format(processName + "処理待ちデータがありません、%s秒をスリープします。", String.valueOf(sleepSecond)));
					// スリープする
					Thread.sleep(sleepSecond*1000);
				} catch (Exception ex) {
					logger.error(ex.getMessage(),ex);
				}
			}
		}
		
		// 処理末データから１件を取得する
		String id = readyList.get(0);
		readyList.remove(id);
		emailHistory = readyMap.get(id);
		readyMap.remove(id);
		
		// 取得したデータを処理中に追加する
		doingList.add(id);
		
		return emailHistory;
	}
	
}
