package yl.telnet;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.telnet.TelnetClient;

public class _Test {
	
	public static void main(String[]args) {
		try {
			TelnetClient client = new TelnetClient();
			client.connect("192.168.8.123");
			
//			client.sendCommand(command);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
