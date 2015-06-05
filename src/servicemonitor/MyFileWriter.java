package servicemonitor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileWriter {

	
	// Emulates a log file for a service
	public void writetoFile() {
		String data = "hello";
		int count = 0;
		String start = "--------------";
		String error = "Found Null pointer Exception--" + "\n"
				+ "Line number 664" + "\n";

		try {
			File file = new File("log.txt");
			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			// Header for start of log file
			if (file.length() == 0)
				bufferWriter.append(start + "\n");

			if (file.exists()) {
				while (true) {
					StringBuilder sb = new StringBuilder();
					//Don't add a counter to string when less than 2
					if (count < 2) {
						sb.append(data + "\n");
						bufferWriter.append(sb);
						count++;
						// Print an exception every 3rd write
					} else if(count%3==0){
						sb.append(data+(count++)+"\n");
						bufferWriter.append(sb);
						bufferWriter.append(error);
					} else {
						sb.append(data+(count++)+"\n");
						bufferWriter.append(sb);
					}
//					System.out.println("Writing to file");
					bufferWriter.flush();
					//Writes every 15 seconds
					Thread.sleep(15000);
				}
			}
//			System.out.println("done");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] a) {
		MyFileWriter mfw = new MyFileWriter();
		mfw.writetoFile();
	}

}
