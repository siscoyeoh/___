package yl.telnet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.telnet.TelnetClient;

// http://www.cnblogs.com/djoker/p/6568982.html
public class Client {
	public static void main(String[] args){

        try {
        	String ip = "192.168.8.115";
        	ip = "192.168.8.115";
        	ip = "192.168.8.123";
        	int port = 23;
            TelnetClient tc = new TelnetClient();
//            tc.connect("192.168.8.44", 21);
            tc.connect(ip, port);
            InputStream in = tc.getInputStream();
            OutputStream os = tc.getOutputStream();
            
            System.out.print(readUntil(":", in));
            
            writeUtil("root", os);
            System.out.print(readUntil(":", in));
            
            writeUtil("freewificom", os);
            System.out.print(readUntil("root@WiAC:~#", in));
            
            
            writeUtil("ls -l / ", os);
            System.out.print(readUntil("root@WiAC:~#", in));
            
            writeUtil("ip addr ", os);
            System.out.print(readUntil("root@WiAC:~#", in));
            
            writeUtil("ip route ", os);
            System.out.print(readUntil("root@WiAC:~#", in));
            
//            System.out.println("---��ʼд---");
//            writeUtil("quit", os);
//            System.out.println("---д����,��ʼ��---");
//            System.out.println(readUntil("root@WiAC:~#", in));
//            System.out.println("---������---");
            
            
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }
    
    /**
     * д�������
     * @param cmd
     * @param os
     */
    public static void writeUtil(String cmd, OutputStream os){
        try {
            cmd = cmd + "\n";
            os.write(cmd.getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * ����ָ��λ��,�������¶�
     * @param endFlag
     * @param in
     * @return
     */
    public static String readUntil(String endFlag, InputStream in) {
        
        InputStreamReader isr = new InputStreamReader(in);
        
        char[] charBytes = new char[1024];
        int n = 0;
        boolean flag = false;
        String str = "";
        try {
            while((n = isr.read(charBytes)) != -1){
                for(int i=0; i< n; i++){
                    char c = (char)charBytes[i];
                    str += c;
                    //��ƴ�ӵ��ַ�����ָ�����ַ�����βʱ,���ڼ�����
                    if(str.endsWith(endFlag)){
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return str;
    }
}
