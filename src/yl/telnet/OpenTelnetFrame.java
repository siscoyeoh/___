package yl.telnet;

import java.io.IOException;

public class OpenTelnetFrame {

	public static void main(String[] args) {
		String os = System.getProperty("os.name");  
		System.out.println(os);
		if (os.toLowerCase().startsWith("win")) {
			// Windows操作系统
			try {
//				Runtime.getRuntime().exec("cmd.exe /c start telnet 192.168.8.115");
				Runtime.getRuntime().exec("cmd.exe /c start telnet 192.168.8.115 smbuser sbmuser");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		else if (os.toLowerCase().startsWith("linux")) {
			//  Linux操作系统
			// ...
		}
	}

}
