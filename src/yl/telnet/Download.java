package yl.telnet;

import javax.swing.*;
import java.awt.event.*;
 
import java.io.FileOutputStream;
import java.io.InputStream;
 
import java.net.BindException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.Date;
 
// http://bbs.csdn.net/topics/390286890
public class Download {
    public static void main(String [] args)
    {
        JFrame jFrame = new JFrame("��������");
        jFrame.setSize(600,600);
        jFrame.setLocation(100,100);
         
        JPanel jPanel = new JPanel();
        JLabel jLabel = new JLabel("����URL��");
        JLabel jLabel2 = new JLabel("���Դ�����");
        final JTextField jTextField = new JTextField("http://",20);
        final JTextField jTextField2 = new JTextField("0",5);
        JButton btnTest = new JButton("��ʼ����");
        JButton btnClear = new JButton("�������");
        jPanel.add(jLabel);
        jPanel.add(jTextField);
        jPanel.add(jLabel2);
        jPanel.add(jTextField2);
        jPanel.add(btnTest);
        jPanel.add(btnClear);
        jFrame.getContentPane().add(jPanel,"North");
         
        final JTextArea jTextArea = new JTextArea();
        jTextArea.setLineWrap(true);//�����Զ����й��� 
        jTextArea.setWrapStyleWord(true);//������в����ֹ��� 
         
        //ΪJTextArea��ӹ�����
        JScrollPane jsp = new JScrollPane(jTextArea);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
        jFrame.getContentPane().add(jsp,"Center");
         
        //��ӡϵͳ������Ϣ
        final String line = System.getProperty("line.seperator");
        jTextArea.append("------------------------������Ϣ-----------------------------"+"\n");
         
        try{
            jTextArea.append("����ip��ַ�� "+ InetAddress.getLocalHost().getHostAddress()+"\n");
        }catch (Exception e) {
            ;
        }
        jTextArea.append("����os���֣� "+ System.getProperty("os.name")+"\n");
        jTextArea.append("jre �汾�� "+ System.getProperty("java.version")+"\n");
        jTextArea.append("����ʱ�� ��"+(new Date()).toString()+"\n");
        jTextArea.append("------------------------------------------------------------------"+"\n");
        //����buttun����
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jTextArea.setText("");
            }
        });
        btnTest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String strurl = jTextField.getText();
                String strnum = jTextField2.getText();
                int testNum = Integer.valueOf(strnum).intValue(); 
                 
                for(int curNum = 1;curNum<=testNum;curNum++)
                {
                        try {
                            URL url = new URL(strurl);
                            URLConnection urlConnection=url.openConnection();
                            jTextArea.append("---------------------------------");
                            jTextArea.append(line);
                            jTextArea.append("\n");
                            jTextArea.append("���Ե�������"+url.getHost());
                            jTextArea.append("\n");
                            jTextArea.append(line);
                             
                            jTextArea.append("�����ܴ���  ��"+ Integer.toString(testNum));
                            jTextArea.append("    ���Ե�ǰ����  ��"+ Integer.toString(curNum));
                            jTextArea.append(line);
                            jTextArea.append("\n");
                             
                            jTextArea.append("����ʱ�� ��"+(new Date()).toString());
                            jTextArea.append(line);
                            jTextArea.append("\n");
                             
                             
                             
                            //jTextArea.append("contetType: "+urlConnection.getContentType());
                            jTextArea.append(line);
                            jTextArea.append("\n");
                            jTextArea.append("---------------------------------");
                            jTextArea.append(line);
                            jTextArea.append("\n");
                            jTextArea.setCaretPosition(jTextArea.getText().length());
                            /*//JTextArea.append("cntentLength "+urlConnection.getContentLength());
                            InputStream inputStream = urlConnection.getInputStream();
                            //InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                            //BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                             FileOutputStream fileOutputStream = new FileOutputStream("3.html");
                             
                            //String string2;
                            //while ((string2= bufferedReader.readLine())!=null) {
                                int data;
                                while((data=inputStream.read())!=-1)
                                {
                                //fileOutputStream.write(string2.getBytes());
                                //fileOutputStream.write(line.getBytes());
                                fileOutputStream.write(data);
                                 
                            }
                            inputStream.close();
                            fileOutputStream.close();*/
                             
                            //Thread.sleep(500);
                             
                             
                        } 
                        catch (BindException e1) 
                        {
                            ;
                        }
                        catch (ConnectException e2) 
                        {
                            ;
                        }
                        catch (NoRouteToHostException e3) 
                        {
                            ;
                        }
                        catch (PortUnreachableException e4) 
                        {
                            ;
                        }
                        catch (ProtocolException e5) 
                        {
                            ;
                        }
                        catch (SocketTimeoutException  e6) 
                        {
                            ;
                        }
                        catch (UnknownHostException e7) 
                        {
                            ;
                        }
                        catch (UnknownServiceException e8) 
                        {
                            ;
                        }
                        catch(Exception E)
                        {
                            ;
                        }
                         
                }
                 
                 
            }
        });
         
        jFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        jFrame.show();
         
    }
 
}
