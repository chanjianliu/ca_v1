package sg.edu.iss.ca_v1.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mail")
public class MailController {
	
	@RequestMapping("/send")
	public String sendEmail() {
		
		// Create a mail sender 163
		JavaMailSenderImpl javaMailSender_163 = new JavaMailSenderImpl();
		javaMailSender_163.setHost("smtp.163.com");
		javaMailSender_163.setPort(25);
		javaMailSender_163.setUsername("springtestbox@163.com");
		javaMailSender_163.setPassword("BFLBDFEZDSAFERZO");
		
		// Create a mail sender 126
		JavaMailSenderImpl javaMailSender_126 = new JavaMailSenderImpl();
		javaMailSender_126.setHost("smtp.126.com");
		javaMailSender_126.setPort(25);
		javaMailSender_126.setUsername("springtestbox@126.com");
		javaMailSender_126.setPassword("BRHYMCVEVKEDSOLN");
		
		// Create a mail sender mailtrap
		JavaMailSenderImpl javaMailSender_mailtrap = new JavaMailSenderImpl();
		javaMailSender_mailtrap.setHost("smtp.mailtrap.io");
		javaMailSender_mailtrap.setPort(25);
		javaMailSender_mailtrap.setUsername("e29cc640fa90eb");
		javaMailSender_mailtrap.setPassword("fa9e5c7ab33336");
		
		// Create an email instance, choose a email server
		JavaMailSenderImpl javaMailSender = javaMailSender_126;
		
		// Set the email address we want to send to
		String[] mailAddressList = { "buwillow@gmail.com" };
		
		// Create an SimpleMailMessage
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom(javaMailSender.getUsername());
		smm.setTo(mailAddressList);
		smm.setSubject("Title here");
		smm.setText("This is a sample message");
		
		// Send mail ## Server not work.
		// javaMailSender.send(smm);
		
		// Record something
		System.out.println("Sent successfully");
		return "emailsent";
	}
}
