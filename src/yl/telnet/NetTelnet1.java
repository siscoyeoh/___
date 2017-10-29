package yl.telnet;

import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

public class NetTelnet1 {
	private TelnetClient telnet = new TelnetClient();
	private InputStream in;
	private PrintStream out;
	private char prompt = '$';

	// ��ͨ�û�����
	public NetTelnet1(String ip, int port, String user, String password) {
		try {
			telnet.connect(ip, port);
			in = telnet.getInputStream();
			out = new PrintStream(telnet.getOutputStream());
			// ����root�û����ý�����
			this.prompt = user.equals("root") ? '#' : '$';
			login(user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** * ��¼ * * @param user * @param password */
	public void login(String user, String password) {
		readUntil("login:");
		write(user);
		readUntil("Password:");
		write(password);
		readUntil(prompt + " ");
	}

	/** * ��ȡ������� * * @param pattern * @return */
	public String readUntil(String pattern) {
		try {
			char lastChar = pattern.charAt(pattern.length() - 1);
			StringBuffer sb = new StringBuffer();
			char ch = (char) in.read();
			while (true) {
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						return sb.toString();
					}
				}
				ch = (char) in.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** * д���� * * @param value */
	public void write(String value) {
		try {
			out.println(value);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** * ��Ŀ�귢�������ַ��� * * @param command * @return */
	public String sendCommand(String command) {
		try {
			write(command);
			return readUntil(prompt + " ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** * �ر����� */
	public void disconnect() {
		try {
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println("����Telnet...");
			String ip = "192.168.8.44";
			int port = 23;
			String user = "ftpuser";
			String password = "ftpuser";
			NetTelnet1 telnet = new NetTelnet1(ip, port, user, password);
			telnet.sendCommand("export LANG=en");
			String r1 = telnet.sendCommand("cd E:");
			String r2 = telnet.sendCommand("pwd");
			String r3 = telnet.sendCommand("sh a.sh");
			System.out.println("��ʾ���");
			System.out.println(r1);
			System.out.println(r2);
			System.out.println(r3);
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
