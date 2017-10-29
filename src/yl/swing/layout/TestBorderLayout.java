package yl.swing.layout;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TestBorderLayout extends JFrame {
	JButton jButton, jButton2, jButton3, jButton4, jButton5;

	public TestBorderLayout() {
		jButton = new JButton("�м�");
		jButton2 = new JButton("����");
		jButton3 = new JButton("����");
		jButton4 = new JButton("����");
		jButton5 = new JButton("�ϱ�");

		this.add(jButton, BorderLayout.CENTER); // ���ֵ��м�
		this.add(jButton2, BorderLayout.NORTH); // ���ֵı���
		this.add(jButton3, BorderLayout.WEST); // ���ֵ�����
		this.add(jButton4, BorderLayout.EAST); // ���ֵĶ���
		this.add(jButton5, BorderLayout.SOUTH); // ���ֵ��ϱ�

		this.setTitle("��ʾ�߽粼�ֹ�����");
		this.setSize(300, 200);
		this.setLocation(200, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		TestBorderLayout testBorderLayout = new TestBorderLayout();
	}
}
