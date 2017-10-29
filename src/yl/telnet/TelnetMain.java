package yl.telnet;
// 
import java.io.InputStream;  
import java.io.PrintStream;  
  
import org.apache.commons.net.telnet.TelnetClient;  
  
//import com.telnet.constant.TelnetConstant;  
  
public class TelnetMain {  
      
    private TelnetClient telnet = new TelnetClient("VT100");  
  
    private InputStream in;  
  
    private PrintStream out;  
  
    private static final String DEFAULT_AIX_PROMPT = "#";  
    private static final String ENTER_COMMAND_ARROW = ">";  
    private static final String ENTER_COMMAND_BRACKETS = "]";  
    private static final String ENTER="\n";  
  
  
    /** 
     * telnet �˿� 
     */  
    private String port;  
  
    /** 
     * �û��� 
     */  
    private String user;  
  
    /** 
     * ���� 
     */  
    private String password;  
  
    /** 
     * IP ��ַ 
     */  
    private String ip;  
  
    public TelnetMain(String ip, String user, String password) {  
        this.ip = ip;  
        this.port = String.valueOf(23);  
        this.user = user;  
        this.password = password;  
    }  
  
    public TelnetMain(String ip, String port, String user, String password) {  
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
            telnet.setKeepAlive(true);  
            write(password);  
            String msg=readUntil(ENTER_COMMAND_ARROW);  
            System.out.println(msg);  
            write("system-view");  
            msg=readUntil("\n");  
            System.out.println(msg);  
            msg=readUntil("\n");  
            System.out.println(msg);  
            write("display interface ");  
            msg=readUntil("\n");  
            System.out.println(msg);  
            msg=readUntil(ENTER_COMMAND_BRACKETS);  
            System.out.println(msg);  
  
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
                //System.out.print(ch);// ---��Ҫע�͵�  
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
  
    public void write(String value) {  
        try {  
            out.println(value);  
            out.flush();  
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
  
    private String getNowDate() {  
        this.connect();  
        String nowDate = this.sendCommand("date|awk '{print $2,$3,$4}'");  
        String[] temp = nowDate.split("\r\n");  
        // ȥ�������ַ���  
        if (temp.length > 1) {  
            nowDate = temp[0];  
        } else {  
            nowDate = "";  
        }  
        this.disconnect();  
        return nowDate;  
    }  
  
    public static void main(String[] args) {  
        try {  
//            TelnetMain telnet = new TelnetMain("10.10.2.249", "Huawei", "Huawei");  
//            System.setOut(new PrintStream("D:/telnet.txt"));  
//            telnet.connect();  
//            telnet.disconnect();  
        	TelnetMain telnet = new TelnetMain("192.168.8.115", "ftpuser", "ftpuser");
//        	System.setOut(new PrintStream("D:/telnet.txt"));
        	System.out.println("--0");
        	telnet.connect();
        	System.out.println("--1");
        	telnet.disconnect();
        	System.out.println("--2");
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
} 
