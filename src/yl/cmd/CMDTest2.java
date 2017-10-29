package yl.cmd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CMDTest2 {

	public static void main(String[] args) throws IOException {
		File cmd = new File("");
		BufferedReader reader = new BufferedReader(new FileReader(cmd));
		String commandStr= reader.readLine();
		commandStr = "ipconfig";
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
	
	public static void test01() throws IOException {
		String commandStr= "ping www.baidu.com";
		commandStr = "ipconfig";
		Process process = Runtime.getRuntime().exec(commandStr);
		InputStream in = process.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;
//		System.out.println("br:" + br);
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
	}

	public static void test02() throws IOException {
		String commandStr= "ping www.baidu.com";
		commandStr= "java -version";
		commandStr= "dir";
		String JAVA_PATH="C:/Program Files/Java/jdk1.6.0_45/bin/java.exe";
		JAVA_PATH="javahome";
	    String CLASS_PATH="d:/javafiles/Hello";	
	    commandStr= JAVA_PATH + " -cp " + CLASS_PATH;
		Process process = Runtime.getRuntime().exec(commandStr);
		InputStream in = process.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;
//		System.out.println("br:" + br);
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
	}
	
}
