package yl.swing.layout;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

// jdk 7
public class GridLayoutDemo {
	public static void main(String args[])  
    {
        JFrame frame = new JFrame("表格布局") ;  
        frame.setLayout(new GridLayout(3,5,13,13)) ;  //四个数字依次对应 行 列 水平间距，垂直间距  
        JButton button = null ;  
        for(int i=0;i<20;i++)  
        {
            button = new JButton("按钮"+i) ;  
            frame.add(button) ;  
        }
        frame.pack() ;   //根据组件所需要的面板大小自动调整  
        frame.setVisible(true) ;  
    }
}
