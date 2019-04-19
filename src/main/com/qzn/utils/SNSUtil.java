package com.qzn.utils;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class SNSUtil {
	private Map<String, MessageAttributeValue> smsAttributes;

	public Map<String, MessageAttributeValue> getDefaultSMSAttributes() {
		if (smsAttributes == null) {
			smsAttributes = new HashMap<>();
			smsAttributes.put("AWS.SNS.SMS.SenderID",
					new MessageAttributeValue().withStringValue("1").withDataType("String"));
			smsAttributes.put("AWS.SNS.SMS.MaxPrice",
					new MessageAttributeValue().withStringValue("0.05").withDataType("Number"));
			smsAttributes.put("AWS.SNS.SMS.SMSType",
					new MessageAttributeValue().withStringValue("Transactional").withDataType("String"));
		}
		return smsAttributes;
	}

	public PublishResult sendSMSMessage(String phoneNumber, String message) {
		return sendSMSMessage(phoneNumber, message, getDefaultSMSAttributes());
	}

	public PublishResult sendSMSMessage(String phoneNumber, String message,
			Map<String, MessageAttributeValue> smsAttributes) {
		AWSCredentials awsCredentials = new AWSCredentials() {
			@Override
			public String getAWSAccessKeyId() {
				return "xxxxxxxx";
			}

			@Override
			public String getAWSSecretKey() {
				return "xxxxxxxx";
			}
		};
		AWSCredentialsProvider provider = new AWSCredentialsProvider() {
			@Override
			public AWSCredentials getCredentials() {
				return awsCredentials;
			}

			@Override
			public void refresh() {
			}
		};
		AmazonSNS amazonSNS = null;
		try {
			amazonSNS = AmazonSNSClientBuilder.standard().withCredentials(provider).withRegion("us-east-1").build();
		} catch (Exception e) {

		}
		return amazonSNS.publish(new PublishRequest().withMessage(message).withPhoneNumber(phoneNumber)
				.withMessageAttributes(smsAttributes));
	}

	public static void main(String[] args) {
		SNSUtil shortMessage = new SNSUtil();
		PublishResult publishResult = shortMessage.sendSMSMessage("+8613958942952", "test1");
		System.out.println(publishResult);
	}
}
