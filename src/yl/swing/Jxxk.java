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
	        super("选项卡窗口");setSize(400,300);
	  
	        Container c = getContentPane();
	        tabbedPane=new JTabbedPane();//创建选项卡面板对象  
	        //创建标签  
	        label1=new JLabel("第一个标签的面板",SwingConstants.CENTER);
	        label2=new JLabel("第二个标签的面板",SwingConstants.CENTER);
	        label3=new JLabel("第三个标签的面板",SwingConstants.CENTER);
	        //创建面板  
	        panel1=new JPanel();
	        panel2=new JPanel();
	        panel3=new JPanel();
	  
	        panel1.add(label1);
	        panel2.add(label2);
	        panel3.add(label3);
	  
	        panel1.setBackground(Color.getColor("#000000"));
	        panel2.setBackground(Color.GRAY);
	        panel3.setBackground(Color.lightGray);
	        //将标签面板加入到选项卡面板对象上  
	        tabbedPane.addTab("标签1",null,panel1,"First panel");
	        tabbedPane.addTab("标签2",null,panel2,"Second panel");
	        tabbedPane.addTab("标签3",null,panel3,"Third panel");
	  
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
