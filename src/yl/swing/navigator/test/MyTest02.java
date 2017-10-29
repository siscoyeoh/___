package yl.swing.navigator.test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyTest02 extends JFrame{
	private static final long serialVersionUID = 181512416010218763L;
	private JPanel panel;
	private JButton menu1;
	private JButton menu2;
	private boolean menu1IsSpread = false;
	private boolean menu2IsSpread = false;
	private List<Component> menu1Subs;
	private List<Component> menu2Subs;

	public MyTest02(List<String> menuItems, List<List<String>> subItems) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(300, 600));
		
		contructPanel(menuItems, subItems);
		
		this.add(panel);
		
		this.setVisible(true);
	}
	
	private void contructPanel(List<String> menuItems, List<List<String>> subItems) {
		Font itemFont = new Font("Default",Font.PLAIN,12);
		Font menuFont = new Font("Default",Font.BOLD,14);
		panel = new JPanel();
		panel.setLayout(new GridLayout(20, 1));
		
		menu1 = new JButton(menuItems.get(0));
		menu1.setBackground(new Color(209, 222, 252));
		menu1.setFont(menuFont);
		menu1Subs = new ArrayList<Component>();
		for (int i = 0; i < subItems.get(0).size(); i++) {
			JButton sub1_i = new JButton(subItems.get(0).get(i));
			sub1_i.setBackground(Color.WHITE);
			sub1_i.setFont(itemFont);
			sub1_i.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {
					
				}
			});;
			menu1Subs.add(sub1_i);
		}
		
		menu2 = new JButton(menuItems.get(1));
		menu2.setBackground(new Color(209, 222, 252));
		menu2.setFont(menuFont);
		menu2Subs = new ArrayList<Component>();
		for (int i = 0; i < subItems.get(1).size(); i++) {
			JButton sub2_i = new JButton(subItems.get(1).get(i));
			sub2_i.setBackground(Color.WHITE);
			sub2_i.setFont(itemFont);
			menu2Subs.add(sub2_i);
		}
		
		menu1.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				repack(panel, 
						menu1Subs, !menu1IsSpread, 
						menu2Subs, false);
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
				repack(panel, 
						menu1Subs, false, 
						menu2Subs, !menu2IsSpread);
				menu2IsSpread = !menu2IsSpread;
				menu1IsSpread = false;
//				panel.repaint();
				panel.validate();
			}
		});
		
		repack(panel, menu1Subs, false, menu2Subs, false);
	}
	
	private void repack(JPanel panel, 
			List<Component> subArr1, boolean show1, 
			List<Component> subArr2, boolean show2 ) {
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
		List<String> menuItems = new ArrayList<String>();
		menuItems.add("最新文件备份");
		menuItems.add("历史文件备份");
		List<List<String>> subItems = new ArrayList<List<String>>();
		List<String> sub1 = new ArrayList<String>();
		sub1.add("192.168.8.44");
		subItems.add(sub1);
		List<String> sub2 = new ArrayList<String>();
		sub2.add("192.168.8.44");
		sub2.add("192.168.8.115");
		subItems.add(sub2);
		new MyTest02(menuItems, subItems);
	}

}
