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

		 // ����JFrame
       JFrame frame = new JFrame("�ҵ�frame");
       // ���ڴ�С
       frame.setSize(600, 450);
       // ����λ��
       frame.setLocationRelativeTo(null); // ����
       frame.setLocation(200, 100);
       
       // ������ť
       JButton btn = new JButton("ȷ����ť");
       btn.setSize(80, 25);
       // ��Ӱ�ť
       frame.add(btn);
       
       // ��ʾJFrame
       frame.setVisible(true);
       // �ر�ʱ�Ĳ���
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
