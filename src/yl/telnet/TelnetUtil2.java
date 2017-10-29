package yl.telnet;

import org.apache.commons.net.telnet.TelnetClient;
import java.io.*;
import java.nio.ByteBuffer;
public class TelnetUtil2 {
    String charset = null;
    byte[] buff = new byte[2048];
    TelnetClient telnetClient = new TelnetClient();
    BufferedReader telnetReader = null;
    BufferedWriter telnetWirter = null;
    InputStream telnetIn = null;
    OutputStream telnetOut = null;

    public TelnetUtil2() {
        telnetClient = new TelnetClient();
    }

    /**
     * ������������
     * @param ip
     * @param port
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public void connect(String ip, int port) throws UnsupportedEncodingException,IOException {
        telnetClient.connect(ip,port);
        setIOStream();
    }

    /**
     * ������������
      * @param ip
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public void connect(String ip) throws UnsupportedEncodingException,IOException {
        telnetClient.connect(ip);
        setIOStream();
    }

    void setIOStream() throws UnsupportedEncodingException {
        telnetIn = telnetClient.getInputStream();
        telnetOut = telnetClient.getOutputStream();
        if(null==charset){
            telnetReader = new BufferedReader(new InputStreamReader(telnetIn));
            telnetWirter = new BufferedWriter(new OutputStreamWriter(telnetOut));
        }else{
            telnetReader = new BufferedReader(new InputStreamReader(telnetIn, charset));
            telnetWirter = new BufferedWriter(new OutputStreamWriter(telnetOut, charset));
        }
    }

    /**
     * ��¼
     * @param user
     * @param passwd
     * @return �Ƿ��¼�ɹ�.
     * @throws IOException
     */
    public boolean login(String user,String passwd) throws IOException {
        String read = readString();
        for(int i=0; ; i++){
            if(-1==read.indexOf("login")){
                read = readString();
            }else{
                break;
            }
        }
        writeText(user);

        read = readString();
        for(int i=0; ; i++){
            if(-1==read.indexOf("Password")){
                read = readString();
            }else{
                break;
            }
        }
        writeText(passwd);

        for(;;){
            read = readString();
            //System.out.println("last:"+read);
            if(-1!=read.indexOf("Last")){
                return true;
            }else if(-1!=read.indexOf("incorrect")){
                return false;
            }
        }
    }

    /**
     * ����һ�����Է���,���д��
     * @throws IOException
     */
    public void show() throws IOException {
//        System.out.println(readString());
//        System.out.println(readString());
//        ByteBuffer tmp = ByteBuffer.allocate(1024);
//        byte[] buff = new byte[1024];
//        while(telnetIn.available()>0){
//            int readLen = readBytes(buff,0,1024);
//            tmp.put(buff,0,readLen);
//        }

//        System.out.println(new String(tmp.array()));
        System.out.println("1 "+readString());
        System.out.println("2 "+readString());
        System.out.println("3 "+readString());
        writeText("root");
        System.out.println("4 " + readString());
        writeText("123456");
        System.out.println("5 "+readString());
//        System.out.println("6 "+readString());
//        System.out.println("7 "+readString());

    }

    public int readBytes(byte[] buff, int offset, int len) throws IOException {
        return telnetIn.read(buff,offset,len);
    }

    /**
     * ��ȡ�ַ���<br/>
     * �൱��readByte()תΪ�ַ���
     * @return
     * @throws IOException
     */
    public String readString() throws IOException {
        int readLen = readBytes(this.buff, 0, this.buff.length);
        if(0<readLen)
            return new String(buff,0,readLen).trim();
        else
            return "";
    }

    /**
     * ��ȡһ��<br/>
     * �����������ͻ��˲���ͬһ�ֲ���ϵͳ�����ܵ��´˷�������ʧ�ܡ�
     * @return
     * @throws IOException
     */
    public String readLine() throws IOException {
        String read = telnetReader.readLine();
        return null==read?"":read.trim();
    }

    public void writeBytes(byte[] buff, int offset, int len) throws IOException {
        telnetOut.write(buff,offset,len);
    }

    /**
     * �������д�ַ���
     * @param text
     * @throws IOException
     */
    public void writeText(String text) throws IOException {
        telnetWirter.write(text);
        telnetWirter.write('\r');
        telnetWirter.write('\n');
        telnetWirter.flush();
    }

    /**
     * ִ����������ؽ��<br/>
     * �൱��: <br> 
     * writeText();  <br/>
     * return readString();
     * @param cmd
     * @return
     * @throws IOException
     */
    public String exec(String cmd) throws IOException {
        writeText(cmd);
        return readString();
    }

    String login1(String user,String passwd) throws IOException {
        String read = null;
        readString();
        readString();
        read = readString();

        if(-1!=read.indexOf("login")){
            writeText(user);
        }

        read = readString();
        if(-1!=read.indexOf("Password")){
            writeText(passwd);
        }

        read  = readString();
        read += readString();
        return read;

//        StringBuffer sb = new StringBuffer();
//        while(null!= (read = readString())){
//            sb.append(read);
//        }
//
//        return sb.toString();
    }

    /**
     * �ر�
     */
    public void close(){
        try{
            writeText("exit");
            writeText("exit");
            writeText("exit");
        }catch(Exception ex){
        }

        try {
            if(null!=telnetIn) telnetIn.close();
        } catch (Exception e) {
        }

        try {
            if(null!=telnetOut) telnetOut.close();
        } catch (Exception e) {
        }

        try {
            if(null!=telnetClient)telnetClient.disconnect();
        } catch (Exception e) {
        }
    }

    /**
     * ����telnetͨ��ʱ���ַ���<br/>
     * ע:���ַ�������������ַ���û�б�Ȼ��ϵ<br/>
     * �˷�������connect()ǰ����
     * @param charset
     */
    public void setCharset(String charset ){
        this.charset = charset;
    }

    /**
     * ��������buff��С,Ĭ��Ϊ2048�ֽ�.
     * @param size
     */
    public void setBufferSize(int size){
        this.buff = new byte[size];
    }
    
    public static void main(String[]args) {
    	String ip = "192.168.8.123";
    	String user = "Administrator";
    	String pwd = "123";
    	
    	ip = "192.168.8.115";
    	user = "smbuser";
    	pwd = "smbuser";
//    	t1();
    	t4(ip, user, pwd);
    }
    
    //
    // --------------------------- ����
    //
    static void t4(String ip, String user, String pwd){
        TelnetUtil2 tu = new TelnetUtil2();
        try {
            tu.connect(ip);
            System.out.println(tu.login(user, pwd));
            //tu.show();
            //System.out.println(tu.readString());
            //System.out.println(tu.exec("pwd"));

            System.out.println(tu.exec("echo \"123456789\">1.txt"));
            System.out.println(tu.exec("cat 1.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        tu.close();
    }

    static void t1(String ip, String user, String pwd){
        TelnetUtil2 tu = new TelnetUtil2();
        try {
            tu.connect(ip);
            System.out.println(tu.login(user, pwd));
            //tu.show();
            //System.out.println(tu.readString());
            System.out.println(tu.exec("pwd"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        tu.close();
    }

}

