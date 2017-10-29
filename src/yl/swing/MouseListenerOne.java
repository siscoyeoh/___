package yl.swing;

import java.awt.event.MouseEvent;  
import java.awt.event.MouseListener;  
import java.util.Date;

import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.JButton;  
import javax.swing.JOptionPane;  
/* 
 * Java Swing����¼�������һ�������ڲ��෽ʽ 
 * */  
public class MouseListenerOne {  
  
    public static void main(String[] args) {  
        //1.����һ��JFrame��myFrame  
        JFrame myFrame = new JFrame();  
        //2.����myFrame�����ԣ��ɼ�����С  
        myFrame.setVisible(true);  
        myFrame.setSize(200, 200);  
        //3.����һ��JPanel��myPanel  
        JPanel myPanel = new JPanel();  
        //4.��myPanel������myFrame��  
        myFrame.add(myPanel);  
        //5.����һ��JButton��myButton  
        JButton myButton = new JButton("��ť1");  
        //6����myButton������myPanel��  
        myPanel.add(myButton);  
          
        //7.��myButton����¼��������ڲ��෽ʽ����ϸ�����ر��Ǳ����ţ�  
//        myButton.addMouseListener(new MouseListener() {  
//                  
//            public void mouseReleased(MouseEvent e) {}            
//            public void mousePressed(MouseEvent e) {}             
//            public void mouseExited(MouseEvent e) {}          
//            public void mouseEntered(MouseEvent e) {}         
//            public void mouseClicked(MouseEvent e) {  
//                //һ�����򣬴˴���ϸ˵���﷨  
////                JOptionPane.showMessageDialog(null,"�ڲ����¼���������","ע��",0,null);  
//            	System.out.println(new Date());
//            }  
//        });  
        

		// ���ð�ť�¼�
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