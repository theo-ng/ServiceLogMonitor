package servicemonitor;

import java.io.File;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerListenerAdapter;

/**
 * @author Theo
 * TailFileReader using Apache Commons Tailer implementation of -tail f function
 * reads from the tail of a log file for an exception
 * and calls MyMailer to send an alert when exception is read
 */
public class LogFileMonitor extends TailerListenerAdapter {

	public void handle(String line) {
		String match = "Exception";
		if(line.contains(match)) {
//			System.out.println(line);
			ExceptionAlertMailer mymailer = new ExceptionAlertMailer(line);
			mymailer.sendMail();
		}
	}

	public static void main(String[] arg) {
		File log = new File("log.txt");

		try {
			TailerListener listener = new LogFileMonitor();
			Tailer tailer = new Tailer(log, listener, 500);
			Thread thread = new Thread(tailer);
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
