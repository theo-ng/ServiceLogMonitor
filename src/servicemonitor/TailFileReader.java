package servicemonitor;

import java.io.File;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerListenerAdapter;

public class TailFileReader extends TailerListenerAdapter{

	public static void main(String[] arg) {
		TailerListener listener = new TailFileReader();
		Tailer tailer = Tailer.create(new File("log.txt"), listener, 500);
		tailer.run();
	}
}
