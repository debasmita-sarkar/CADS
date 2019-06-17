package com.cads.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	private static String fromAddress = "champak.83@gmail.com";

	private static String password = "CD@google";

	private static final String serverAddress = "smtp.gmail.com";

	private static final int port = 587;

	public static boolean sendMail(String toAddress, String subject, String body) {

		Properties props = new Properties();  
		props.put("mail.smtp.host",serverAddress);  
		props.put("mail.smtp.auth", "true");  
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(props,  
				new javax.mail.Authenticator() {  
			protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication(fromAddress,password);  
			}  
		});  

		//Compose the message  
		try {  
			MimeMessage message = new MimeMessage(session);  
			message.setFrom(new InternetAddress(fromAddress));  
			InternetAddress to = new InternetAddress(toAddress);
			message.addRecipient(Message.RecipientType.TO,to); 
			//later change it to actual subject received as param
			message.setSubject("Emailing from CADS");  
			//later change it to actual body received as param

			message.setText("This is simple program of sending email using JavaMail API");  

			//send the message  
			Transport.send(message);  

			System.out.println("message sent successfully...");  

		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}  

		return true;

	}
	
	public static void main(String args[]) {
		sendMail("debasmita.das.sarkar@gmail.com","Wow!","you have got mail!!");
	}

}
