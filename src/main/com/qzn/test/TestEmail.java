package com.qzn.test;

import com.qzn.utils.Email;
import com.qzn.utils.SpringEmailUtil;

public class TestEmail {
	public static void main(String[] args){
		sendEmailTest();
	}
	
	public static void sendEmailTest(){
		Email email = new Email();	
		email.setFromEmailAddress("liuxin@iesolutions.com.cn");
		email.setFromPersonName("sbsbsb");
		email.setToEmailAddresses(new String[]{"quzhinan@iesolutions.com.cn"});
		email.setSubject("测试");
		email.setContent("ceshidddda");
		try {
			SpringEmailUtil.sendEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
