package yl.swing;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class Jxxk extends JFrame {
	 private JTabbedPane tabbedPane;
	    private JLabel label1,label2,label3;
	    private JPanel panel1,panel2,panel3;
	  
	    public Jxxk()  
	    {
	        super("ѡ�����");setSize(400,300);
	  
	        Container c = getContentPane();
	        tabbedPane=new JTabbedPane();//����ѡ�������  
	        //������ǩ  
	        label1=new JLabel("��һ����ǩ�����",SwingConstants.CENTER);
	        label2=new JLabel("�ڶ�����ǩ�����",SwingConstants.CENTER);
	        label3=new JLabel("��������ǩ�����",SwingConstants.CENTER);
	        //�������  
	        panel1=new JPanel();
	        panel2=new JPanel();
	        panel3=new JPanel();
	  
	        panel1.add(label1);
	        panel2.add(label2);
	        panel3.add(label3);
	  
	        panel1.setBackground(Color.getColor("#000000"));
	        panel2.setBackground(Color.GRAY);
	        panel3.setBackground(Color.lightGray);
	        //����ǩ�����뵽ѡ���������  
	        tabbedPane.addTab("��ǩ1",null,panel1,"First panel");
	        tabbedPane.addTab("��ǩ2",null,panel2,"Second panel");
	        tabbedPane.addTab("��ǩ3",null,panel3,"Third panel");
	  
	        c.add(tabbedPane);
	        c.setBackground(Color.white);
	  
	        setVisible(true);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	      
	    public static void main(String args[])  
	    {
	        Jxxk d = new Jxxk();
	    }
}
