package yl.telnet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;

import org.apache.commons.net.telnet.TelnetClient;

public class SimpleTest {
	public static void main(String[]args) {
		 try {
	            TelnetClient telnetClient = new TelnetClient("vt200");  //ָ��Telnet�ն����ͣ�����᷵�������������Ļ�����
	            telnetClient.setDefaultTimeout(5000); //socket�ӳ�ʱ�䣺5000ms
	            telnetClient.connect("192.168.8.115",23);  //����һ������,Ĭ�϶˿���23
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
