package yl.swing.gridbaglayout.demo01;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Demo01 {
	
	JButton bt0 = new JButton("0");
	JButton bt1 = new JButton("1");
	JButton bt2 = new JButton("2");
	JButton bt3 = new JButton("3");
	JButton bt4 = new JButton("4");
	JButton bt5 = new JButton("5");
	JButton bt6 = new JButton("6");
	JButton bt7 = new JButton("7");
	JButton bt8 = new JButton("8");
	JButton bt9 = new JButton("9");
	JButton bt10 = new JButton("10");
	JButton bt11 = new JButton("11");
	JButton btsignleft = new JButton("btsignleft");
	JButton btsignright = new JButton("btsignright");
	JButton btp = new JButton("btp");
	JButton bte = new JButton("bte");
//	JButton btm = new JButton("btm");
	JTextArea btm = new JTextArea("btm", 12, 24);
	JButton btd = new JButton("btd");
	JButton btmp = new JButton("btmp");
	JButton btclear = new JButton("btclear");
	JButton bt = new JButton("bt");
	JTextField textField = new JTextField("textField");
	JButton btdot = new JButton("btdot");
	
	private JPanel createPanel(){  
	    JPanel panel = new JPanel();  
	      
	    GridBagLayout gbl = new GridBagLayout();  
	    GridBagConstraints gbs = new GridBagConstraints();  
	    panel.setLayout(gbl);  
	      
	    panel.add(bt7);panel.add(bt8);panel.add(bt9);panel.add(btp);  
	    panel.add(bt4);panel.add(bt5);panel.add(bt6);  
	    panel.add(bt1);panel.add(bt2);panel.add(bt9);panel.add(btp);  
	    panel.add(bt1);panel.add(bt2);panel.add(bt3);panel.add(btm);  
	    panel.add(bt0);panel.add(btdot);  
	    panel.add(btsignleft);panel.add(btsignright);panel.add(bte);panel.add(btmp);  
	    panel.add(btclear);panel.add(bt);               panel.add(btd);  
	    panel.add(textField);  
	      
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=0;gbs.gridy=0;  
	    gbl.setConstraints(bt7, gbs);  
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=1;gbs.gridy=0;  
	    gbl.setConstraints(bt8, gbs);  
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=2;gbs.gridy=0;  
	    gbl.setConstraints(bt9, gbs);  
	      
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=2;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=3;gbs.gridy=0;  
	    gbl.setConstraints(btp, gbs);     
	      
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=0;gbs.gridy=1;  
	    gbl.setConstraints(bt4, gbs);  
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=1;gbs.gridy=1;  
	    gbl.setConstraints(bt5, gbs);  
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=2;gbs.gridy=1;  
	    gbl.setConstraints(bt6, gbs);  
	      
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=0;gbs.gridy=2;  
	    gbl.setConstraints(bt1, gbs);  
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=1;gbs.gridy=2;  
	    gbl.setConstraints(bt2, gbs);  
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=2;gbs.gridy=2;  
	    gbl.setConstraints(bt3, gbs);  
	      
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=2;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=3;gbs.gridy=2;  
	    gbl.setConstraints(btm, gbs);  
	      
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=2;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=0;gbs.gridy=3;  
	    gbl.setConstraints(bt0, gbs);  
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=2;gbs.gridy=3;  
	    gbl.setConstraints(btdot, gbs);  
	      
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=0;gbs.gridy=4;  
	    gbl.setConstraints(btsignleft, gbs);  
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=1;gbs.gridy=4;  
	    gbl.setConstraints(btsignright, gbs);  
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=2;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=2;gbs.gridy=4;  
	    gbl.setConstraints(bte, gbs);  
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=3;gbs.gridy=4;  
	    gbl.setConstraints(btmp, gbs);  
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=0;gbs.gridy=5;  
	    gbl.setConstraints(btclear, gbs);  
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=1;gbs.gridy=5;  
	    gbl.setConstraints(bt, gbs);  
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=1;gbs.gridheight=1;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=3;gbs.gridy=5;  
	    gbl.setConstraints(btd, gbs);  
	      
	    gbs.fill=GridBagConstraints.BOTH;gbs.gridwidth=4;gbs.gridheight=3;  
	    gbs.insets=new Insets(5, 5, 5, 5);gbs.weightx=1;gbs.weighty=1;  
	    gbs.gridx=0;gbs.gridy=6;  
	    gbl.setConstraints(textField, gbs);  
	      
	    return panel;  
	}  

	public static void main(String[] args) {
		JPanel panel = new Demo01().createPanel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.add(panel);
		frame.setVisible(true);
	}

}
