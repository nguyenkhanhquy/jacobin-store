package com.jacobin.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtilGmail {

	public static void sendMail(String to, String from, String subject, String body, boolean bodyIsHTML)
			throws MessagingException, UnsupportedEncodingException {

		// 1 - get a mail session
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtps.host", "smtp.gmail.com");
		props.put("mail.smtps.port", 465);
		props.put("mail.smtps.auth", "true");
		props.put("mail.smtps.quitwait", "false");
		Session session = Session.getDefaultInstance(props);
		// session.setDebug(true);

		// 2 - create a message
		Message message = new MimeMessage(session);
		message.setSubject(subject);
		if (bodyIsHTML) {
			message.setContent(body, "text/html");
		} else {
			message.setText(body);
		}
		// 3 - address the message
		Address fromAddress = new InternetAddress(from, "Jacobin Store");
		Address toAddress = new InternetAddress(to);
		message.setFrom(fromAddress);
		message.setRecipient(Message.RecipientType.TO, toAddress);

		// 4 - send the message
		String username = System.getenv("GMAIL_USERNAME");
		String password = System.getenv("GMAIL_PASSWORD");
		
		Transport transport = session.getTransport();
		transport.connect(username, password);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
}
