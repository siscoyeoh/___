package yl.swing.gridbaglayout.demo01;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Demo02 {
	
	JTextArea textArea;
	JScrollPane scrollPane;
	JLabel label = new JLabel("textField");
	
	private JPanel createPanel(){
	    JPanel panel = new JPanel();
	      
	    GridBagLayout gridBagLayou = new GridBagLayout();
	    GridBagConstraints gridBagConstraints = new GridBagConstraints();
	    panel.setLayout(gridBagLayou);
	      
	    textArea = new JTextArea("btm", 28, 24);
	    scrollPane = new JScrollPane(textArea);

	    textArea.setEditable(true);
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(false);
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    
	    panel.add(scrollPane);
	    panel.add(label);
	      
	    gridBagConstraints.fill=GridBagConstraints.BOTH;
	    gridBagConstraints.gridwidth=1;
	    gridBagConstraints.gridheight=2;
	    gridBagConstraints.insets=new Insets(5, 5, 5, 5);
	    gridBagConstraints.weightx=1;
	    gridBagConstraints.weighty=1;
	    gridBagConstraints.gridx=3;
	    gridBagConstraints.gridy=2;
	    gridBagLayou.setConstraints(scrollPane, gridBagConstraints);
	    
	      
	    gridBagConstraints.fill=GridBagConstraints.BOTH;
	    gridBagConstraints.gridwidth=4;
	    gridBagConstraints.gridheight=3;
	    gridBagConstraints.insets=new Insets(5, 5, 5, 5);
	    gridBagConstraints.weightx=1;
	    gridBagConstraints.weighty=1;
	    gridBagConstraints.gridx=0;
	    gridBagConstraints.gridy=6;
	    gridBagLayou.setConstraints(label, gridBagConstraints);
	      
	    return panel;
	}

	public static void main(String[] args) {
		JPanel panel = new Demo02().createPanel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.add(panel);
		frame.setVisible(true);
	}

}
