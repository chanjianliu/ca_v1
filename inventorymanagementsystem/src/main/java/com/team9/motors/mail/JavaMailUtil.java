package com.team9.motors.mail;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class JavaMailUtil {
	public static void sendEmail() {
		// Create a mail sender gmail
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setPort(587);
		javaMailSender.setUsername("zhuhaokun3@gmail.com");
		javaMailSender.setPassword("springmail123");
		// Set authentication and TLS
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		javaMailSender.setJavaMailProperties(properties);
		// Set the email address we want to send to
		String[] mailAddressList = { "zhuhaokun2@gmail.com", "e0641594@u.nus.edu" };
		// Create an SimpleMailMessage
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom("Management System");
		smm.setTo(mailAddressList);
		smm.setSubject("Product Reorder Reminder");
		smm.setText("This is a massage from system" + "The number of product [Water] goes down to [0]"
				+ "Please reoder the products soon");

		try {
			javaMailSender.send(smm);
			System.out.println("Sended successfully");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Send failure");
		}

	}

}
