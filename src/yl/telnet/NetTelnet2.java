package yl.telnet;

/*�������������Ҫ��telnet��,��Ȼ�������õ���apache��Դ��,�������Լ�
 *�����ܲ����Լ���д�������Ѿ������ṩ�ˡ�
 *Ҫ�ǿ��ԵĻ������.��O(��_��)
 */
import java.io.InputStream;
import java.io.PrintStream;
import org.apache.commons.net.telnet.TelnetClient;

/**
 * ����apache net ��Դ����ʹ��telnet��ʽ��ȡAIX������Ϣ
 * 
 * @version 1.2
 */
public class NetTelnet2 {
	// Telnet����
	private TelnetClient telnet = new TelnetClient();
	private InputStream in;
	private PrintStream out;
	// ��ʾ����������telnet��AIX�����鿴
	private char prompt = '#';
	// telnet�˿�
	private String port;
	// �û�
	private String user;
	// ����
	private String password;
	// IP��ַ
	private String ip;

	public NetTelnet2() {
		try {
			// AIX����IP
			this.ip = "192.168.8.115";
			this.port = "23";
			this.password = "ftpuser";
			this.user = "ftpuser";
			telnet.connect(ip, Integer.parseInt(port));
			System.out.println("��ʼ��ȡ������...");
			in = telnet.getInputStream();
			out = new PrintStream(telnet.getOutputStream());
			// ��¼
			/*
			 * readUntil("login: "); write(user); readUntil("Password: ");
			 * write(password); readUntil(prompt + " ");
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ�������
	 * 
	 * @param pattern
	 * @return
	 */
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

	/**
	 * д
	 * 
	 * @param value
	 */
	public void write(String value) {
		try {
			out.println(value);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��Ŀ�귢�������ַ���
	 * 
	 * @param command
	 * @return
	 */
	public String sendCommand(String command) {
		try {
			write(command);
			return readUntil(prompt + " ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �ر�����
	 * 
	 */
	public void disconnect() {
		try {
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println("��ʼִ��telnet......");
			NetTelnet2 telnet = new NetTelnet2();
			// ͨ��aix����������������ơ���ȡ����
			// ������ "hostname"
			// ����Ϥ����Ĳο�<<AIX��������ֲ�>>
			System.out.println("��ʼ����hostname����");
			String result = telnet.sendCommand("hostname");
			System.out.println("��ʾ���");
			System.out.println(result);
			// ���һ��Ҫ�ر�
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
