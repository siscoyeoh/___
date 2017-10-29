package yl.telnet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.apache.commons.net.telnet.TelnetClient;
 
public class TelnetUtil3 {
    /** �½�һ��TelnetClient���� */
    private TelnetClient telnetClient = new TelnetClient();
    /** ϵͳ��ʾ���� */
    private final String osTag = "\r\n";
    /** get Value ϵͳ��ʾ���� */
    private final String getValOsTag = "END\r\n";
    /** �����������շ�����Ϣ */
    private InputStream in;
    /** �� ������д�� ���� */
    private PrintStream out;
 
    /**
     * @param ip : telnet��IP��ַ
     * @param port : �˿ںţ�Ĭ��11211
     */
    public TelnetUtil3(String ip, Integer port) {
       try {
           telnetClient.connect(ip, port);
           in = telnetClient.getInputStream();
           out = new PrintStream(telnetClient.getOutputStream());
       } catch (Exception e) {
           System.out.println("[telnet] connect error: connect to [" + ip + ":" + port + "] fail!");
       }
    }
 
    public TelnetUtil3(String ip) {
       try {
           telnetClient.connect(ip, 11211);
           in = telnetClient.getInputStream();
           out = new PrintStream(telnetClient.getOutputStream());
       } catch (Exception e) {
           System.out.println("[telnet] connect error: connect to [" + ip + ":" + 11211 + "] fail!");
       }
    }
 
    /**
     * ִ��telnet����
     *
     * @param command
     * @return
     */
    public String execute(String command) {
       write(command);
       StringBuffer sb = new StringBuffer();
       String osTagX = osTag;
       if (command.startsWith("get")) {
           osTagX = getValOsTag;
       }
       try {
           char ch = (char) in.read();
           int isEnd = 0;
           while (true) {
              sb.append(ch);
              if (ch == osTagX.charAt(isEnd)) {
                  isEnd++;
                  if (sb.toString().endsWith(osTagX) && isEnd == osTagX.length())
                     return sb.toString();
              } else {
                  isEnd = 0;
              }
              ch = (char) in.read();
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       return "error! when the program execute";
    }
 
    /**
     * ��telnet��������������
     *
     * @param command
     */
    public void write(String command) {
       try {
           out.println(command);
           out.flush();
           System.out.println("[telnet] ��ӡ����ִ�е�telnet����:" + command);
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
 
    /**
     * �ر�Telnet����
     */
    public void disconnect() {
       try {
           Thread.sleep(10);
           telnetClient.disconnect();
       } catch (InterruptedException e1) {
           e1.printStackTrace();
       } catch (IOException e2) {
           e2.printStackTrace();
       }
    }
 
    /**
     * ���ڲ���
     *
     * @param url
     * @param port
     */
    public static void testGet(String url, Integer port) {
       System.out.println("----------------------------" + url + ":" + port + "----------------------------");
       TelnetUtil3 telnetTest = new TelnetUtil3(url, port);
       String result = telnetTest.execute("get apl");
       System.out.println(result);
 
       String result2 = telnetTest.execute("get wll");
       System.out.println(result2);
       telnetTest.disconnect();
    }
   
    /**
     * �洢������������ջ������������
     * @param url
     * @param port
     */
    public static void clearCache(String url,Integer port){
       System.out.println("[telnet] �洢������������ջ������������[" + url + ":" + port + "]----------------------------");
       TelnetUtil3 telnetTest = new TelnetUtil3(url, port);
       String result = telnetTest.execute("flush_all");
       System.out.println(result);
       telnetTest.disconnect();
    }
   
    public static void main(String[] args) {
//       testGet("192.168.101.193", 11211);
//       testGet("192.168.101.152", 11211);
//       clearCache("192.168.101.193", 11211);
//       clearCache("192.168.101.193", 12000);
    	testGet("192.168.8.115", 21);
    }
}
