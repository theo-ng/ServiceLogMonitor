package servicemonitor;

import java.io.File;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerListenerAdapter;

public class MyTailListener extends TailerListenerAdapter {

	public void handle(String line) {
//		System.out.println(line);
		String match = "Exception";
		if(line.contains(match))
			System.out.println("Send Alert");
	}

	public static void main(String[] arg) {
		File log = new File("log.txt");

		try {
			TailerListener listener = new MyTailListener();
			Tailer tailer = new Tailer(log, listener, 500);
			Thread thread = new Thread(tailer);
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
