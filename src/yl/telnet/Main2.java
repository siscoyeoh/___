package yl.telnet;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

import org.apache.commons.net.telnet.TelnetClient;

// http://www.cnblogs.com/visoncheng/p/3665194.html
public class Main2 {
    
    
    
    public static void main(String[]a) throws SocketException, IOException {
//    	System.out.println(new Date());
//    	new Main2().mainRun();
//    	test1();
    	test2();
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
//                    if(sBuffer.toString().trim().endsWith("login:")) {
//                        break;
//                    }
                    System.out.println(sBuffer.toString());
                }
            }
//            System.out.println(sBuffer.toString());
//            pStream.println("exit"); //д����
//            pStream.flush(); //������͵�telnet Server
//            if(null != pStream) {
//                pStream.close();
//            }
//            telnetClient.disconnect();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void mainRun() throws SocketException, IOException {
    	TelnetClient telnetClient = new TelnetClient("vt200");  //ָ��Telnet�ն����ͣ�����᷵�������������Ļ�����
        telnetClient.setDefaultTimeout(60 * 1000); //socket�ӳ�ʱ�䣺5000ms
        String hostname = "127.0.0.1";
        hostname = "192.168.8.115";
        hostname = "192.168.8.1";
        telnetClient.connect(hostname,23);  //����һ������,Ĭ�϶˿���23
        InputStream telIn = telnetClient.getInputStream(); //��ȡ�������
        PrintStream pStream = new PrintStream(telnetClient.getOutputStream());  //д�������
        
        InputStream sysIn = System.in;
        Thread systemReader = new Thread(new SystemReader(sysIn, pStream));
        Thread telnetReader = new Thread(new TelnetReader(telIn));
        systemReader.start();
        telnetReader.start();
        System.out.println(new Date());
    }
    
    class SystemReader extends Thread {
    	private InputStream inputStream;
    	private PrintStream pStream;
    	
    	SystemReader(InputStream inputStream, PrintStream pStream) {
    		this.inputStream = inputStream;
    		this.pStream = pStream;
    	}
    	
    	@Override
    	public void run() {
    		
    		String cmdLine = null;
    		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
    		try {
				while ((cmdLine = br.readLine()) != null) {
					System.out.println(cmdLine);
					pStream.println(cmdLine); //д����
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    class TelnetReader extends Thread {
    	
    	private InputStream inputStream;
    	
    	TelnetReader(InputStream inputStream) {
    		this.inputStream = inputStream;
    	}
    	
    	@Override
    	public void run() {
    		
    		String telnetEcho = null;
    		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
    		try {
				while ((telnetEcho = br.readLine()) != null) {
					System.out.println(telnetEcho); // ��ʾtelnet������Ϣ
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }
    
    
    public static void test2() throws IOException {
    	ServerSocket serverSocket=new ServerSocket(9999);  
        /*accept������������*/  
        Socket server=serverSocket.accept();  
        while(true)  
        {  
            /*getInputStream������������*/  
            InputStream in=server.getInputStream();  
            DataInputStream dataIn=new DataInputStream(in);  
            String str=dataIn.readLine();  
            if(str != null)  
            {  
                System.out.println(str);  
            }  
              
        }  
    }
    
    public static void test3() {
//    	try {
//    		TelnetClient telnet = new TelnetClient("vt200");  //ָ��Telnet�ն����ͣ�����᷵�������������Ļ�����
//    		telnet.setDefaultTimeout(60 * 1000); //socket�ӳ�ʱ�䣺5000ms
//            String hostname = "127.0.0.1";
//            hostname = "192.168.8.115";
//            hostname = "192.168.8.1";
//    	InputStream in = telnet.getInputStream();
//    	OutputStream out = new PrintStream(telnet.getOutputStream());
//    	readUntil("login:");
//    	write(username); 
//    	write("\r\n"); 
//    	readUntil("password:"); 
//    	write(password); write("\r\n");             
//    	Success.sendCommand("ipconfig");                 
//    	write("\r\n");         }         
//    catch (IOException e)         {             
//    	e.printStackTrace();             
//    	System.exit(1);         
//    	}         
//    	IOUtil.readWrite(telnet.getInputStream(), telnet.getOutputStream(),System.in, System.out);
    }
    
}