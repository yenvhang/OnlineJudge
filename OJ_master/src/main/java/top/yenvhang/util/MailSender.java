package top.yenvhang.util;

import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailSender  {
	private static Session session;
	private static ArrayBlockingQueue<Message> mailQueue=new ArrayBlockingQueue<Message>(10);
	static
	{		System.out.println("初始化");
			Properties properties =new Properties();
			properties.setProperty("mail.host", "smtp.163.com");
			properties.setProperty("mail.transport.protocol","smtp");
			properties.setProperty("mail.smtp.auth", "true");
			session =Session.getInstance(properties,new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("tmac.back@163.com", "a123456");
				}
			});
			session.setDebug(true);
			//监听　邮箱队列。如果有代发送邮箱,则发送
			new Thread(new Runnable() {
				public void run() {
					while(true){
						Message msg= null;
						try {
							 msg= mailQueue.take();
							 Transport.send(msg);
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (MessagingException e) {
							try {
								mailQueue.put(msg);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							e.printStackTrace();
						}
					}
					
				}
			}).start();
	}
	
	public static void sendMessage(final Message msg) {
					try {
						mailQueue.put(msg);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
	}
	public  static Message createSimpleMail(String recipient,String subject,String body){
		
		MimeMessage message =new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("tmac.back@163.com"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(subject);
			message.setContent(body,"text/plain;charset=UTF-8");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return message;
	}
}
