package yl.telnet;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;

import org.apache.commons.net.telnet.TelnetClient;

public class Main {

    public static void main(String[] args) {
        test1();
    }
    
    public static void test0() {
    	try {
        	String termtype = "vt200";
        	termtype = "VT100";
        	String ip = "127.0.0.1";
        	ip = "192.168.8.44";
        	ip = "192.168.8.115";
        	int port = 21;
            TelnetClient telnetClient = new TelnetClient(termtype);  //ָ��Telnet�ն����ͣ�����᷵�������������Ļ�����
            telnetClient.setDefaultTimeout(1000); //socket�ӳ�ʱ�䣺5000ms
            telnetClient.connect(ip,port);  //����һ������,Ĭ�϶˿���23
            InputStream inputStream = telnetClient.getInputStream(); //��ȡ�������
            PrintStream pStream = new PrintStream(telnetClient.getOutputStream());  //д�������
            byte[] bs = new byte[1024];
            int size;
            StringBuffer sBuffer = new StringBuffer(300);
            while(true) {     //��ȡServer�����������ݣ�ֱ��������½��ʶ�����ʱ����Ϊ���������û���
                size = inputStream.read(bs);
                if(-1 != size) {
                    sBuffer.append(new String(bs,0,size));
                    if(sBuffer.toString().trim().endsWith("login:")) {
                        break;
                    }
                }
            }
            System.out.println(sBuffer.toString());
            pStream.println("quit"); //д����
            pStream.flush(); //������͵�telnet Server
            if(null != pStream) {
                pStream.close();
            }
            telnetClient.disconnect();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void test1() {
    	try {
            TelnetClient telnetClient = new TelnetClient("vt200");  //ָ��Telnet�ն����ͣ�����᷵�������������Ļ�����
            telnetClient.setDefaultTimeout(5000); //socket�ӳ�ʱ�䣺5000ms
            String hostname = "127.0.0.1";
            hostname = "192.168.8.44";
            hostname = "192.168.8.115";
            hostname = "192.168.8.1";
            telnetClient.connect(hostname,23);  //����һ������,Ĭ�϶˿���23
            InputStream inputStream = telnetClient.getInputStream(); //��ȡ�������
            PrintStream pStream = new PrintStream(telnetClient.getOutputStream());  //д�������
            byte[] b = new byte[1024];
            int size;
            StringBuffer sBuffer = new StringBuffer(300);
            while(true) {     //��ȡServer�����������ݣ�ֱ��������½��ʶ�����ʱ����Ϊ���������û���
                size = inputStream.read(b);
                if(-1 != size) {
                    sBuffer.append(new String(b,0,size));
                    if(sBuffer.toString().trim().endsWith("login:")) {
                        break;
                    }
                }
            }
            System.out.println(sBuffer.toString());
            pStream.println("exit"); //д����
            pStream.flush(); //������͵�telnet Server
            if(null != pStream) {
                pStream.close();
            }
            telnetClient.disconnect();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
