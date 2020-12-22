package com.team9.motors.mail;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.team9.motors.model.Inventory;
import com.team9.motors.model.ProductState;
import com.team9.motors.model.Role;
import com.team9.motors.model.User;
import com.team9.motors.repository.UserRepository;

public class JavaMailUtil {

	@Autowired
	private static UserRepository urepo;

	public static void sendEmail(Inventory inventory) {
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
		String[] mailAddressList = findAdminEmail();
		// Create an SimpleMailMessage
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom("Management System");
		smm.setTo(mailAddressList);
		smm.setSubject("Product Reorder Reminder");
		String mailText = "This is a massage from system. \n The number of product ["
		+inventory.getProduct().getName()
		+"] goes down to ["
		+inventory.getQuantity()
		+"]. The Supplier is ["
		+inventory.getProduct().getSupplier().getName()
		+"]. \nPlease reoder the products soon";
		smm.setText(mailText);
		try {
			javaMailSender.send(smm);
			System.out.println("Sended successfully");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Send failure");
		}

	}

	public static void changeProductState(Inventory inventory) {

		if (inventory.getQuantity() <= inventory.getProduct().getReorderLevel()
				&& inventory.getProductState() == ProductState.InStock) {
			sendEmail(inventory);
			inventory.setProductState(ProductState.BelowReorderLevel);
		}
//		else if (inventory.getQuantity() <= inventory.getProduct().getReorderLevel()
//				&& inventory.getProductState() == ProductState.BelowReorderLevel) {
//			// do nothing
//		} else if (inventory.getQuantity() <= inventory.getProduct().getReorderLevel()
//				&& inventory.getProductState() == ProductState.ReorderPlaced) {
//			// inventory.setProductState(ProductState.InStock);
//		}
	}
	
	public static String[] findAdminEmail() {
		List<User> ulist  = urepo.findUsersByRole(Role.ADMIN);
		String[] mailList = new String[ulist.size()];
		
		int counter = 0;
		for (User user : ulist) {
			mailList[counter] = user.getEmail();
			counter++;
		}
		return mailList;
	}

}
