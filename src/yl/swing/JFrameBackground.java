package yl.swing;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class JFrameBackground extends JFrame {
	
	JPanel jp1, jp2;
	 
    public JFrameBackground() {
        jp1 = new JPanel();
        jp1.setBackground(Color.gray);
        jp2 = new JPanel();
        jp2.setBackground(Color.lightGray);
        JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jp1, jp2);
        add(jsp);
        setTitle("��ִ���");
        setBounds(300, 200, 500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        jsp.setDividerLocation(0.2);// ��1/2�����в��
 
    }
 
    public static void main(String[] args) {
        new JFrameBackground();
    }
	
	
//	public JFrameBackground(){  
//		  this.setTitle("�ҵ�swing����");   
//		  this.setLayout(new FlowLayout());  
//		  JPanel buttonPanel = new JPanel();  
//		  buttonPanel.setBorder(BorderFactory.createTitledBorder("�����")); //�������߿�ʵ�ַ�����Ч�����˾����Ϊ�ؼ�����  
//		  
//		  buttonPanel.setBorder(BorderFactory.createLineBorder(Color.red));//�������߿���ɫ  
//		  JButton button = new JButton("�ҵİ�ť");  
//		  buttonPanel.add(button);  
//		  this.setSize(300, 300);  
//		  this.getContentPane().add(buttonPanel);  
//		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
//		  this.setVisible(true);  
//		 }  
//		   
//		 public static void main(String[] args) {  
//		  new JFrameBackground();  
//		 }  
}
