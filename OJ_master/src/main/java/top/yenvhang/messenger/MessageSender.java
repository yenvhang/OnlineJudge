//package top.yenvhang.messenger;
//
//
///**
//* Project Name:OJ_master
//* File Name:messageSender.java
//* Package Name:messenger
//* Date:2017年2月16日下午5:30:07
//* Copyright (c) 2017, Tony All Rights Reserved.
//*
//*/
//
//
//
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//
///**
//* ClassName:messageSender <br/>
//* Function: TODO ADD FUNCTION. <br/>
//* Reason: TODO ADD REASON. <br/>
//* Date: 2017年2月16日 下午5:30:07 <br/>
//* @author Tony
//* @version
//* @see
//*/ 
//
//public class MessageSender {
//	private static Logger logger = Logger.getLogger(MessageSender.class);  
//	@Autowired 
//	private JmsTemplate jmsTemplate; 
//	public void sendMessage(Map<String, Object> mapMessage) {
//long submissionId = (Long) mapMessage.get("submissionId");
//		
//		jmsTemplate.convertAndSend(mapMessage);
//		
//		logger.info("submission created submissionId:"+submissionId);
//	}
//
//}
//
