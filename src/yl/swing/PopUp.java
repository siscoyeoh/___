package yl.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PopUp extends JFrame implements ActionListener {

	public JFrame frame;
	public JPanel panel;
	
	public PopUp(String text){
		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JLabel l1 = new JLabel(text);
		l1.setFont(new Font("Courier New",Font.PLAIN,42));
		JButton butt = new JButton("Okay");
			butt.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					dispose();
				}});
			JPanel buttonPane = new JPanel();

		add(panel);
		panel.add(l1,BorderLayout.NORTH);
		buttonPane.add(butt);
		panel.add(buttonPane,BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {}
	
	public static void main(String[]args) {
		new PopUp("µ¯³ö´°²âÊÔ,À´Ô´:https://github.com/RozziP/PopUp");
	}
}