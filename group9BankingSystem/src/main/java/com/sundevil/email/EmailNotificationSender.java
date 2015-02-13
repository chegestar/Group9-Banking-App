package com.sundevil.email;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * This class is used to send out notification emails.
 * 
 * @author SatyaSwaroop
 *
 */
@Service
public class EmailNotificationSender {

	private static final Logger logger = LoggerFactory
			.getLogger(EmailNotificationSender.class);

	@Autowired
	@Qualifier("mailSender")
	private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mailSender2")
	private JavaMailSender mailSender2;
	
	static int  count = 0;

	public void sendNotificationEmail(String emailaddress, String subject, String msgText) {    	
		try {
			if(emailaddress.contains("@gmail.com")||emailaddress.contains("@asu.edu"))
			{
				
			if(count%2==0){
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(new InternetAddress(emailaddress));
			helper.setSubject(subject);
			
			//Adding tail to the message text
			msgText += "\n Thank you\n";
			logger.info("Sending");
			helper.setText(msgText);
			mailSender.send(message);
			logger.info("Send email to " + emailaddress + " with subject \"" + subject + "\"");
			}
			else
			{
				MimeMessage message = mailSender2.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setTo(new InternetAddress(emailaddress));
				helper.setSubject(subject);
				
				//Adding tail to the message text
				msgText += "\n Thank you\n";
				logger.info("Sending");
				helper.setText(msgText);
				mailSender.send(message);
				logger.info("Send email to " + emailaddress + " with subject \"" + subject + "\"");
			}
			}
			else
			{
				throw new MessagingException("only gmail and asu email id's, please enter valid ones");
			}
		} catch (MessagingException ex) {
			logger.error("Notification email could not be sent.", ex);
		}
	}
	
	public void sendNotificationMobile(String contact, String subject, String msgText) {    	
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(new InternetAddress(contact+"@txt.att.net"));
			helper.setSubject(subject);
			
			//Adding tail to the message text
			msgText += "\n It is valid only for 5min \n Please keep it secure";
			logger.info("Sending");
			helper.setText(msgText);
			mailSender.send(message);
			logger.info("Send email to " + contact + " with subject \"" + subject + "\"");
		} catch (MessagingException ex) {
			logger.error("Notification email could not be sent.", ex);
		}
	}
}
