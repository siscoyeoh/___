package yl.telnet;

import javax.swing.*;
import java.awt.event.*;
 
 
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
public class Download2 {
    public static void main(String [] args)
    {
        JFrame jFrame = new JFrame("��������");
        jFrame.setSize(600,600);
        jFrame.setLocation(100,100);
         
        JPanel jPanel = new JPanel();
        JLabel jLabel = new JLabel("����URL��");
        final JTextField jTextField = new JTextField("http://",20);
        JButton btnTest = new JButton("��ʼ����");
        JButton btnClear = new JButton("�������");
        jPanel.add(jLabel);
        jPanel.add(jTextField);
        jPanel.add(btnTest);
        jPanel.add(btnClear);
//        jFrame.getContentPane().add(jPanel,"North");
        jFrame.getContentPane().add(jPanel,"South");
         
        final JTextArea jTextArea = new JTextArea();
        jTextArea.setLineWrap(true);//�����Զ����й��� 
        jTextArea.setWrapStyleWord(true);//������в����ֹ��� 
         
        //ΪJTextArea��ӹ�����
        JScrollPane jsp = new JScrollPane(jTextArea);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
        jFrame.getContentPane().add(jsp,"Center");
         
        jFrame.show();
         
    }
 
}
