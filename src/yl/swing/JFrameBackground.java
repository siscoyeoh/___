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
        setTitle("拆分窗口");
        setBounds(300, 200, 500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        jsp.setDividerLocation(0.2);// 在1/2处进行拆分
 
    }
 
    public static void main(String[] args) {
        new JFrameBackground();
    }
	
	
//	public JFrameBackground(){  
//		  this.setTitle("我的swing界面");   
//		  this.setLayout(new FlowLayout());  
//		  JPanel buttonPanel = new JPanel();  
//		  buttonPanel.setBorder(BorderFactory.createTitledBorder("分组框")); //设置面板边框，实现分组框的效果，此句代码为关键代码  
//		  
//		  buttonPanel.setBorder(BorderFactory.createLineBorder(Color.red));//设置面板边框颜色  
//		  JButton button = new JButton("我的按钮");  
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
