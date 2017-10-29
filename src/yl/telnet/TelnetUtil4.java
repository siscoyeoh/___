package yl.telnet;

import java.io.InputStream;  
import java.io.PrintStream;  
  
import org.apache.commons.net.telnet.TelnetClient;  
  
public class TelnetUtil4 {  
    private TelnetClient telnet = new TelnetClient("VT220");// telnet��VT100 VT52  
                                                            // VT220 VTNT  
                                                            // ANSI��Э�顣  
    private InputStream in;  
    private PrintStream out;  
    private static final String DEFAULT_AIX_PROMPT = "C:\\Users\\Administrator>";  
    // telnet �˿�  
    private String port;  
    // �û���  
    private String user;  
    // ����  
    private String password;  
    // IP ��ַ  
    private String ip;  
    // ȱʡ�˿�  
    private static final int DEFAULT_TELNET_PORT = 23;  
  
    public TelnetUtil4(String ip, String user, String password) {  
        this.ip = ip;  
        this.port = String.valueOf(TelnetUtil4.DEFAULT_TELNET_PORT);  
        this.user = user;  
        this.password = password;  
    }  
  
    public TelnetUtil4(String ip, String port, String user, String password) {  
        this.ip = ip;  
        this.port = port;  
        this.user = user;  
        this.password = password;  
    }  
  
    /** 
     * @return boolean ���ӳɹ�����true�����򷵻�false 
     */  
    private boolean connect() {  
        boolean isConnect = true;  
        try {  
            telnet.connect(ip, Integer.parseInt(port));  
            in = telnet.getInputStream();  
            out = new PrintStream(telnet.getOutputStream());  
            /** Log the user on* */  
            readUntil("login: ");  
            write(user);  
            readUntil("password: ");  
            write(password);  
            /** Advance to a prompt */  
            readUntil(DEFAULT_AIX_PROMPT);  
        } catch (Exception e) {  
            isConnect = false;  
            e.printStackTrace();  
            return isConnect;  
        }  
        return isConnect;  
    }  
  
    public void su(String user, String password) {  
        try {  
            write("su" + " - " + user);  
            readUntil("Password:");  
            write(password);  
            readUntil(DEFAULT_AIX_PROMPT);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public String readUntil(String pattern) {  
        try {  
            char lastChar = pattern.charAt(pattern.length() - 1);  
            StringBuffer sb = new StringBuffer();  
            char ch = (char) in.read();  
            while (true) {  
                // System.out.print(ch);// ---��Ҫע�͵�  
                sb.append(ch);  
                if (ch == lastChar) {  
                    if (sb.toString().endsWith(pattern)) {  
                        // ������룬������ʾ��������  
                        byte[] temp = sb.toString().getBytes("iso8859-1");  
                        return new String(temp, "GBK");  
                    }  
                }  
                ch = (char) in.read();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    public void write(String value) {  
        try {  
            out.println(value);  
            out.flush();  
            // System.out.println(value);// ---��Ҫע�͵�  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public String sendCommand(String command) {  
        try {  
            write(command);  
            return readUntil(DEFAULT_AIX_PROMPT);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    private void disconnect() {  
        try {  
            telnet.disconnect();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    private String getIPConfig() {  
        this.connect();  
        String result = this.sendCommand("ipconfig");  
        this.disconnect();  
        // ȥ��������ʾ��  
        return result.substring(0, result.indexOf(DEFAULT_AIX_PROMPT));  
    }  
  
    private String getDir() {  
        this.connect();  
        String result = this.sendCommand("dir");  
        this.disconnect();  
        // ȥ��������ʾ��  
        return result.substring(0, result.indexOf(DEFAULT_AIX_PROMPT));  
    }  
  
    public static void main(String[] args) {  
        TelnetUtil4 telnet = new TelnetUtil4("192.168.8.115", "smbuser", "smbuser");  
        System.out.println(telnet.getDir());  
        System.out.println(telnet.getIPConfig());  
    }  
  
} 
