package servicemonitor;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * @author Theo
 * Mailer class to send alert using Gmail STMP.
 */
public class MyMailer {

	public static void main(String[] args) {
		// Recipient's email
		String to = "theodore.n.ng@gmail.com";

		// Sender's email and info
		String from = "crazyokuni@gmail.com";
		final String username = "crazyokuni@gmail.com";
		final String password = "crazyokuni1991";

		// Sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Issue Found in Log File");

			// Now set the actual message
			message.setText("Hello, this is automatically generated alert "
					+ "as an exception has been found in log.txt");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
