package yl.swing.grouplayout;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;

public class GroupLayoutDemo2 extends JFrame {
	JPanel main;
	JButton first,second;
	JButton third,fourth,fifth,sixth,eight;
	JTextField seventh;
	public GroupLayoutDemo2(){
		super("Group Layout");
		BorderLayout mainPanel = new BorderLayout();
		setLayout(mainPanel);
		
		add(setUP(),BorderLayout.NORTH);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		}
	
	public static void main(String[] args) {
		GroupLayoutDemo2 layout = new GroupLayoutDemo2();
	}
	
	private Container setUP(){
		first = new JButton("First");
		second = new JButton("Second");
		third = new JButton("Third");
		fourth = new JButton("Fourth");
		fifth = new JButton("Fifth");
		sixth = new JButton("Sixth");
		seventh = new JTextField("Seventh");
		eight = new JButton("Eight");
		
		main = new JPanel();
		GroupLayout layout = new GroupLayout(main);
		main.setSize(400, 300);
		main.setVisible(true);
		main.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.linkSize(SwingConstants.HORIZONTAL, second,fifth,sixth,seventh);;
		layout.linkSize(SwingConstants.VERTICAL,second,seventh);
		layout.setHorizontalGroup(
				   layout.createSequentialGroup()
				   	 // .addComponent(first)
				      /*.addComponent(second)
				      .addComponent(third)*/
				      
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				           .addComponent(second)
				           .addComponent(fifth)
				           .addComponent(sixth))
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					      
					           .addComponent(third)
					           .addComponent(seventh))
				     
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					           .addComponent(first)
					           .addComponent(fourth)
					           .addComponent(eight))
				      
				);
			
		
				layout.setVerticalGroup(
				   layout.createSequentialGroup()
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    	   //.addComponent(first)
				    	   .addComponent(second)
				           .addComponent(third)
				           .addComponent(first))
				    		  
				      
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    	   //.addComponent(first)
					    	   .addComponent(fifth)
					           .addComponent(fourth))
				      
				      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    	   //.addComponent(first)
					    	   .addComponent(sixth)
					           .addComponent(seventh)
					           .addComponent(eight))
				      
				      /*.addComponent(fourth)
				      .addComponent(fifth)*/
				);
				
		
		
		return main;
		
	}
}