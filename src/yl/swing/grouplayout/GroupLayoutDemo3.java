package yl.swing.grouplayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GroupLayoutDemo3 extends JPanel {
	
	private static final long serialVersionUID = -3979002306250179703L;

	public GroupLayoutDemo3() {
		
		JLabel mess = new JLabel("ÏûÏ¢....");
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");
		
		GroupLayout layout = new GroupLayout(this);  
		this.setLayout(layout);  
		layout.setAutoCreateGaps(true);  
		          
		layout.setHorizontalGroup(layout.createSequentialGroup()  
		        .addGap(10)  
		        .addGroup(layout.createParallelGroup()  
		                .addComponent(mess)  
		                .addGap(30)  
		                .addGroup(layout.createSequentialGroup()  
		                        .addGap(10)  
		                        .addComponent(okButton)  
		                        .addGap(10)  
		                        .addComponent(cancelButton))));  
		          
		layout.setVerticalGroup(layout.createParallelGroup()  
		        .addGap(10)  
		        .addGroup(layout.createSequentialGroup()  
		                .addGap(5)  
		                .addComponent(mess)  
		                .addGroup(layout.createParallelGroup()  
		                        .addGap(140)  
		                        .addComponent(okButton)  
		                        .addGap(10)  
		                        .addComponent(cancelButton))));  
	}
	
	public static void main(String[]args) {
		JFrame frame = new JFrame();
		JPanel panel = new GroupLayoutDemo3();
		frame.add(panel);
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
