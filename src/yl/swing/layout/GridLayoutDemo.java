package yl.swing.layout;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

// jdk 7
public class GridLayoutDemo {
	public static void main(String args[])  
    {
        JFrame frame = new JFrame("��񲼾�") ;  
        frame.setLayout(new GridLayout(3,5,13,13)) ;  //�ĸ��������ζ�Ӧ �� �� ˮƽ��࣬��ֱ���  
        JButton button = null ;  
        for(int i=0;i<20;i++)  
        {
            button = new JButton("��ť"+i) ;  
            frame.add(button) ;  
        }
        frame.pack() ;   //�����������Ҫ������С�Զ�����  
        frame.setVisible(true) ;  
    }
}
