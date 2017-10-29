package yl.swing.navigator.test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyTest01 extends JFrame{
	private static final long serialVersionUID = 181512416010218763L;
	static JPanel panel;
	static JButton menu1;
	static JButton menu2;
	static boolean menu1IsSpread = false;
	static boolean menu2IsSpread = false;
	static List<Component> menu1Subs;
	static List<Component> menu2Subs;

	public MyTest01() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(300, 600));
		
		contructPanel();
		
		this.add(panel);
		
		this.setVisible(true);
	}
	
	private void contructPanel() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(20, 1));
		
		menu1 = new JButton("²Ëµ¥1");menu1.setBackground(new Color(209, 222, 252));
		menu1Subs = new ArrayList<Component>();
		JButton sub11 = new JButton("sub11");sub11.setBackground(Color.WHITE);
		JButton sub12 = new JButton("sub12");sub12.setBackground(Color.WHITE);
		JButton sub13 = new JButton("sub13");sub13.setBackground(Color.WHITE);
		JButton sub14 = new JButton("sub14");sub14.setBackground(Color.WHITE);
		menu1Subs.add(sub11);
		menu1Subs.add(sub12);
		menu1Subs.add(sub13);
		menu1Subs.add(sub14);
		
		menu2 = new JButton("²Ëµ¥2");menu2.setBackground(new Color(209, 222, 252));
		menu2Subs = new ArrayList<Component>();
		JButton sub21 = new JButton("sub21");sub21.setBackground(Color.WHITE);//sub21.setBorder(null);
		JButton sub22 = new JButton("sub22");sub22.setBackground(Color.WHITE);//sub22.setBorder(null);
		JButton sub23 = new JButton("sub23");sub23.setBackground(Color.WHITE);//sub23.setBorder(null);
		JButton sub24 = new JButton("sub24");sub24.setBackground(Color.WHITE);//sub24.setBorder(null);
		menu2Subs.add(sub21);
		menu2Subs.add(sub22);
		menu2Subs.add(sub23);
		menu2Subs.add(sub24);
		
		menu1.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				repack(panel, menu1Subs, !menu1IsSpread, menu2Subs, false);
				menu1IsSpread = !menu1IsSpread;
				menu2IsSpread = false;
//				panel.repaint();
				panel.validate();
			}
		});
		menu2.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				repack(panel, menu1Subs, false, menu2Subs, !menu2IsSpread);
				menu2IsSpread = !menu2IsSpread;
				menu1IsSpread = false;
//				panel.repaint();
				panel.validate();
			}
		});
		
		repack(panel, menu1Subs, false, menu2Subs, false);
	}
	
	private void repack(JPanel panel, List<Component> subArr1, boolean show1, List<Component> subArr2, boolean show2 ) {
		panel.removeAll();
		panel.repaint();
		panel.add(menu1);
		if (show1) {
			for (int i = 0; i < subArr1.size(); i++) {
				panel.add(subArr1.get(i));
			}
		}
		panel.add(menu2);
		if (show2) {
			for (int i = 0; i < subArr2.size(); i++) {
				panel.add(subArr2.get(i));
			}
		}
	}
	
	public static void main(String[]a) {
		new MyTest01();
	}

}
