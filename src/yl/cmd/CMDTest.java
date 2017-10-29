package yl.cmd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CMDTest {

	public static void main(String[] args) throws IOException {
		File cmd = new File("cmd.txt");
		BufferedReader reader = new BufferedReader(new FileReader(cmd));
		String commandStr = reader.readLine();
		Process process = Runtime.getRuntime().exec(commandStr);
		InputStream in = process.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;
//		System.out.println("br:" + br);
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}

		reader.close();
		in.close();
		br.close();
	}
	
	
}
