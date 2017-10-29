package yl.telnet;
// http://blog.csdn.net/simplty/article/details/47102615
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.telnet.TelnetClient;

/** 
 * 
 * ʹ��java����telnet���в�����ע��
1.telnet��VT100 VT52 VT220 VTNT ANSI��Э�顣
����vt100��
2.vt100������(ansi������)���˵�����,���Թ��ˣ�Ҳ�����ڷ������ò�Ҫ��
�����˶���һЩ���롣����\033[***һ����ĸ��β�ĸ�ʽ��
3.������������⡣
new String(old.getBytes("ISO8859-1"),"GBK")��
4.����ж϶�ȡ������ˡ�
һ��readUntil(),����ʹ���̡߳�
5.ѡ��telnet��java�����⣬���кܶ࣬����appache(commons-net-3.1.jar), ganymed(ganymed-ssh2-build210.jar),javaexpect(smart-0.1-SNAPSHOT-jar-with-dependencies.jar)
��ʹ��appache��javaexpect�д���vt100��������ˣ���û����ϸ�о���
6.writeҪflush()�ŷ��͡�
 * 
 * 
 * telnet�����ࡣʹ��appache��net.Telnet������vt100���ƴ��루��ansi�����룩���м򵥹��ˡ� 
 *  
 * @author chruan 
 * @version 1.0 
 */  
public class TelnetHelper_bak {
    Object lock = new Object();
    TelnetClient telnet = null;
    String hostname;
    int hostport = 23;
    String user;
    String password;
    private InputStream in;
    private PrintStream out;
    private static final String ORIG_CODEC = "ISO8859-1";
    private static final String TRANSLATE_CODEC = "GBK";

    public TelnetHelper_bak(String hostname, int hostport, String user,  
            String password) throws SocketException, IOException {
        super();
        this.hostname = hostname;
        this.hostport = hostport;
        this.user = user;
        this.password = password;

        telnet = new TelnetClient("VT100");// VT100 VT52 VT220 VTNT ANSI  
        telnet.connect(hostname, hostport);
        in = telnet.getInputStream();
        out = new PrintStream(telnet.getOutputStream());

        readUntil("login: ");
        write(user);
        write("\n");
        readUntil("Password: ");
        write(password);
        write("\n");
    }



    private void restartTerminal() {
        try {
            readUntil(">");
            write("telnet 0.0.7.74\n");
            readUntil("login: ");
            write("dd\n", 500);
            readToEnd();

            write("dff\n", 200);
            readToEnd();

        }catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
    }



    public void readToEnd() {
        ReadThread readThread = new ReadThread();
        readThread.start();
        try {
            readThread.join();
        }catch (Exception e) {
        }
        readThread = null;
    }

    public void readUntil(String str) {
        char last = str.charAt(str.length() - 1);
        String[] ss;
        try {
            StringBuffer sb = new StringBuffer();
            char c;
            int code = -1;
            boolean ansiControl = false;
            boolean start = true;
            while ((code = (in.read())) != -1) {
                c = (char) code;
                if (c == '\033') {//vt100�����붼����\033��ͷ�ġ�  
                    ansiControl = true;
                    int code2 = in.read();
                    char cc = (char) code2;
                    if (cc == '[' || cc == '(') {
                    }
                }
                if (!ansiControl) {
                    if (c == '\r') {
                    //�������������е�ÿһ�䷴��
                        String olds = new String(sb.toString().getBytes(  
                                ORIG_CODEC), TRANSLATE_CODEC);
                        System.out.println(olds);
                        if (sb.lastIndexOf(str) != -1) {
                            break;
                        }
                        sb.delete(0, sb.length());
                    }else if (c == '\n')  
                        ;
                    else  
                        sb.append(c);
                    if (sb.lastIndexOf(str) != -1) {
                        break;
                    }
                }

                if (ansiControl) {
                    if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')  
                            || c == '"') {
                        ansiControl = false;
                    }
                }
            }
            System.out.println(new String(sb.toString().getBytes(ORIG_CODEC),  
                    TRANSLATE_CODEC));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(String s) {
        try {
            out.write(s.getBytes());
            out.flush();
            System.out.println(s);
        }catch (Exception e) {
        }
    }

    public void write(String s, int sleep) {
        write(s);
        try {
            Thread.sleep(sleep);
        }catch (Exception e) {
        }
    }


    /** 
     * ���֮�����ر� 
     */  
    public void close() {
        if (out != null)  
            out.close();
        if (in != null)  
            try {
                in.close();
            }catch (IOException e1) {
            }
        if (telnet != null)  
            try {
                telnet.disconnect();
            }catch (IOException e) {
            }
    }

    public void doJob() {
        // restartTerminal();
        counter();
    }
    private void counter() {
    //��ʾ��һ̨������Զ�̵�¼��һ̨�����
        try {
            readUntil("bash-2.05b$ ");
            write("ssh loc@192.168.0.1\n");
            readUntil("loc@192.168.0.1's password: ");
            write("nodee\n");
            readUntil("[nodee@dz-05 ~]$ ");
            write("ll\n");
            readToEnd();



        }catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
    }


    public static void main(String[] args) {
        String hostname = "192.168.0.2";
        int hostport = 23;
        String user = "username";
        String password = "pwd";
        
        hostname = "192.168.8.115";
        user = "ftpuser";
        password = "ftpuser";
        
        TelnetHelper_bak helper = null;
        try {
            helper = new TelnetHelper_bak(hostname, hostport, user, password);
            helper.doJob();

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (helper != null)  
                helper.close();
        }
    }

    /**
     * ��ȡ���̣߳�����������̡߳���ֹ��ȡʱ�����ˣ���ʱ��������ȡ���߳� 
     * 
     */
    class ReadThread extends Thread {
        public void run() {
            synchronized (lock) {//ֻ��һ����ȡ  
                SubReadThread sub = new SubReadThread();
                sub.start();
                int last = sub.count;
                while (true) {
                    try {
                        Thread.sleep(100);
                    }catch (InterruptedException e) {
                    }
                    if (last == sub.count) {
                        sub.stop();
                        break;
                    }else {
                        last = sub.count;
                    }
                }
                String s = sub.sb.toString();
                try {
                    System.out.println(new String(s.getBytes(ORIG_CODEC),  
                            TRANSLATE_CODEC));
                }catch (UnsupportedEncodingException e) {
                    System.out.println(s);
                }
                sub = null;
            }

//          System.out.println("===========ReadThread end=============");
        }
    }

    /** 
     * ��ȡ���̣߳����ʵ�ʶ�ȡ 
     * 
     */  
    class SubReadThread extends Thread {
        int count = 0;
        StringBuffer sb = new StringBuffer();

        public void read() {
            try {
                char c;
                int code = -1;
                boolean ansiControl = false;
                boolean start = true;
                while ((code = (in.read())) != -1) {
                    count++;
                    c = (char) code;
                    if (c == '\033') {
                        ansiControl = true;
                        int code2 = in.read();
                        char cc = (char) code2;
                        count++;
                        if (cc == '[' || cc == '(') {
                        }
                    }
                    if (!ansiControl) {
                        if (c == '\r') {
                            String olds = new String(sb.toString().getBytes(  
                                    ORIG_CODEC), TRANSLATE_CODEC);
                            System.out.println(olds);
                            sb.delete(0, sb.length());
                        }else if (c == '\n')  
                            ;
                        else  
                            sb.append(c);
                    }

                    if (ansiControl) {
                        if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')  
                                || c == '"') {
                            ansiControl = false;
                        }
                    }
                }
            }catch (Exception e) {
            }
        }

        public void run() {
            read();
        }
    }
}
