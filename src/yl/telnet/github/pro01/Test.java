package yl.telnet.github.pro01;

import java.io.IOException;
// https://github.com/lbw100/JavaTelnetClient
public class Test {

	public static void main(String[] args) {
		String server = "192.168.8.123";
		String user = "Administrator";
		String password = "123"; 
		
		server = "192.168.8.115";
		user = "ftpuser";
		password = "ftpuser";
		
		String prompt = "$";
		int timeout = 3 * 1000;
		try {
			JavaTelnetClient client = 
					new JavaTelnetClient(server, user, password, prompt, timeout);
			System.out.println(111);
			client.write("hostname");
			System.out.println(222);
			client.readUntil(prompt);
			System.out.println(333);
			client.disconnect();
			System.out.println(444);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
