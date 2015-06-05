package servicemonitor;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import java.util.Properties;
 
public class MyMailer {
	
	public static void main(String[] args) {
		Properties props = System.getProperties();
	    props.put("mail.smtp.starttls.enable", true); // added this line
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.user", "crazyokuni@gmail.com");
	    props.put("mail.smtp.password", "crazyokuni1991");
	    props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.auth", true);



	    Session session = Session.getInstance(props,null);
	    MimeMessage message = new MimeMessage(session);

	    System.out.println("Port: "+session.getProperty("mail.smtp.port"));

	    // Create the email addresses involved
	    try {
	        InternetAddress from = new InternetAddress("crazyokuni@gmail.com");
	        message.setSubject("Yes we can");
	        message.setFrom(from);
	        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("theodore.n.ng@gmail.com"));

	        // Create a multi-part to combine the parts
	        Multipart multipart = new MimeMultipart("alternative");

	        // Create your text message part
	        BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setText("some text to send");

	        // Add the text part to the multipart
	        multipart.addBodyPart(messageBodyPart);

	        // Create the html part
	        messageBodyPart = new MimeBodyPart();
	        String htmlMessage = "Our html text";
	        messageBodyPart.setContent(htmlMessage, "text/html");


	        // Add html part to multi part
	        multipart.addBodyPart(messageBodyPart);

	        // Associate multi-part with message
	        message.setContent(multipart);

	        // Send message
	        Transport transport = session.getTransport("smtps");
	        transport.connect("smtp.gmail.com", 465, "crazyokuni@gmail.com", "crazyokuni1991");
	        System.out.println("Transport: "+transport.toString());
	        transport.sendMessage(message, message.getAllRecipients());


	    } catch (AddressException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (MessagingException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
		
		
	}
		 
//		final String username = "crazyokuni@gmail.com";
//		final String password = "crazyokuni1991";
// 
//		Properties props = new Properties();
//		//TLS
////		props.put("mail.smtp.auth", "true");
////		props.put("mail.smtp.starttls.enable", "true");
////		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
////		props.put("mail.smtp.port", "587");
//		
//		//SSL
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.socketFactory.port", "587");
////		props.put("protocol", "smpts");
//		props.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.port", "587");
// 
//		Session session = Session.getInstance(props,
//		  new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(username, password);
//			}
//		  });
// 
//		try {
// 
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress("crazyokuni@gmail.com"));
//			message.setRecipients(Message.RecipientType.TO,
//				InternetAddress.parse("theodore.n.ng@gmail.com"));
//			message.setSubject("Testing Subject");
//			message.setText("Dear Mail Crawler,"
//				+ "\n\n No spam to my email, please!");
// 
//			Transport.send(message);
// 
//			System.out.println("Done");
// 
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//	}
}
