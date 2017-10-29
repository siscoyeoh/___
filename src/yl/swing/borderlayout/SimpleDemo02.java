package yl.swing.borderlayout;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class SimpleDemo02 {
	
	public static void main(String[]args) {
		test02();
	}
	
	public static void test02() {
		JPanel panel = new JPanel();
		panel.setSize(600, 300);
		panel.add(new JTextArea("", 12, 24));
		JLabel label = new JLabel("sssssssssssssssssss");

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 450);
		frame.add(panel, BorderLayout.CENTER);
		frame.add(label, BorderLayout.SOUTH);
		frame.setVisible(true);
	}
	
	public static void test01() {
		JPanel panel = new JPanel();
		JTextArea textArea = new JTextArea("", 12, 24);
		JLabel label = new JLabel("sssssssssssssssssss");
		panel.add(textArea, BorderLayout.CENTER);
		panel.add(label, BorderLayout.SOUTH);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 450);
		frame.add(panel);
		frame.setVisible(true);
	}
	
}
