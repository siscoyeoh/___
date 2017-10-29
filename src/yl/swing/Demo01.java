package yl.swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// http://www.cnblogs.com/newwind/p/5651125.html
public class Demo01 extends JFrame {
	
	public static void main(String[]s) {
		test01();
	}
	
	public static void test01() {

		 // 创建JFrame
       JFrame frame = new JFrame("我的frame");
       // 窗口大小
       frame.setSize(600, 450);
       // 启动位置
       frame.setLocationRelativeTo(null); // 居中
       frame.setLocation(200, 100);
       
       // 创建按钮
       JButton btn = new JButton("确定按钮");
       btn.setSize(80, 25);
       // 添加按钮
       frame.add(btn);
       
       // 显示JFrame
       frame.setVisible(true);
       // 关闭时的操作
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
