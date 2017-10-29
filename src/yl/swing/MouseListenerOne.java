package yl.swing;

import java.awt.event.MouseEvent;  
import java.awt.event.MouseListener;  
import java.util.Date;

import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.JButton;  
import javax.swing.JOptionPane;  
/* 
 * Java Swing添加事件监听（一）匿名内部类方式 
 * */  
public class MouseListenerOne {  
  
    public static void main(String[] args) {  
        //1.创建一个JFrame：myFrame  
        JFrame myFrame = new JFrame();  
        //2.设置myFrame的属性：可见、大小  
        myFrame.setVisible(true);  
        myFrame.setSize(200, 200);  
        //3.创建一个JPanel：myPanel  
        JPanel myPanel = new JPanel();  
        //4.把myPanel放置在myFrame上  
        myFrame.add(myPanel);  
        //5.创建一个JButton：myButton  
        JButton myButton = new JButton("按钮1");  
        //6。把myButton放置在myPanel上  
        myPanel.add(myButton);  
          
        //7.给myButton添加事件监听：内部类方式（仔细看，特别是标点符号）  
//        myButton.addMouseListener(new MouseListener() {  
//                  
//            public void mouseReleased(MouseEvent e) {}            
//            public void mousePressed(MouseEvent e) {}             
//            public void mouseExited(MouseEvent e) {}          
//            public void mouseEntered(MouseEvent e) {}         
//            public void mouseClicked(MouseEvent e) {  
//                //一个弹框，此处不细说其语法  
////                JOptionPane.showMessageDialog(null,"内部类事件监听监听","注意",0,null);  
//            	System.out.println(new Date());
//            }  
//        });  
        

		// 设置按钮事件
        myButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
//				System.out.println("mouseReleased:" + new Date());
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
//				System.out.println("mousePressed:" + new Date());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
//				System.out.println("mouseExited:" + new Date());
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
//				System.out.println("mouseEntered:" + new Date());
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("mouseClicked:" + new Date());
			}
		});
		
		
    }  
} 