package yl.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JScrollPaneDemo01 extends JFrame {
	JScrollPane pane;
	JButton button;

	public JScrollPaneDemo01() {
		super();
		pane = new JScrollPane(new JTextArea());
		button = new JButton("µã»÷");
		add(pane, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JScrollBar bar = pane.getVerticalScrollBar();
				bar.setValue(bar.getMaximum());
			}
		});
	}

	public static void main(String[] args) {
		JFrame frame = new Test();
		frame.setVisible(true);
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}