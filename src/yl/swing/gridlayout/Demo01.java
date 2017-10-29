package yl.swing.gridlayout;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Demo01 {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		JPanel panel = createPanel();
		frame.add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(600, 450);
		
	}
	
	private static JPanel createPanel() {
		JPanel panel = new JPanel();
		
		JLabel label = new JLabel("sssssssssssss");
		JTextArea textArea = new JTextArea("", 12, 24);
		
		GridLayout layout = new GridLayout(6, 1);
		panel.setLayout(layout);
		panel.add(label);
		panel.add(textArea);
		panel.add(new JLabel("fffffffffffff"));
		panel.add(new JTextArea("a", 12, 24));
		panel.add(new JLabel("ggggggggggggggg"));
		panel.add(new JTextArea("b", 12, 24));
		
		return panel;
	}

}
