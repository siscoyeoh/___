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
            TelnetClient telnetClient = new TelnetClient("vt200");  //指明Telnet终端类型，否则会返回来的数据中文会乱码
            telnetClient.setDefaultTimeout(5000); //socket延迟时间：5000ms
            String hostname = "127.0.0.1";
            hostname = "192.168.8.44";
            hostname = "192.168.8.115";
            hostname = "192.168.8.1";
            telnetClient.connect(hostname,23);  //建立一个连接,默认端口是23
            InputStream inputStream = telnetClient.getInputStream(); //读取命令的流
            PrintStream pStream = new PrintStream(telnetClient.getOutputStream());  //写命令的流
            byte[] b = new byte[1024];
            int size;
            StringBuffer sBuffer = new StringBuffer(300);
            while(true) {     //读取Server返回来的数据，直到读到登陆标识，这个时候认为可以输入用户名
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
//            pStream.println("exit"); //写命令
//            pStream.flush(); //将命令发送到telnet Server
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
    	TelnetClient telnetClient = new TelnetClient("vt200");  //指明Telnet终端类型，否则会返回来的数据中文会乱码
        telnetClient.setDefaultTimeout(60 * 1000); //socket延迟时间：5000ms
        String hostname = "127.0.0.1";
        hostname = "192.168.8.115";
        hostname = "192.168.8.1";
        telnetClient.connect(hostname,23);  //建立一个连接,默认端口是23
        InputStream telIn = telnetClient.getInputStream(); //读取命令的流
        PrintStream pStream = new PrintStream(telnetClient.getOutputStream());  //写命令的流
        
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
					pStream.println(cmdLine); //写命令
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
					System.out.println(telnetEcho); // 显示telnet返回信息
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }
    
    
    public static void test2() throws IOException {
    	ServerSocket serverSocket=new ServerSocket(9999);  
        /*accept方法是阻塞的*/  
        Socket server=serverSocket.accept();  
        while(true)  
        {  
            /*getInputStream方法是阻塞的*/  
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
//    		TelnetClient telnet = new TelnetClient("vt200");  //指明Telnet终端类型，否则会返回来的数据中文会乱码
//    		telnet.setDefaultTimeout(60 * 1000); //socket延迟时间：5000ms
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