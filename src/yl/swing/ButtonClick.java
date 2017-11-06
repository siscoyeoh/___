package yl.swing;

import java.awt.Button;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonClick {
	
	public static void main(String[]args) {
		JFrame f = new JFrame();
		f.setSize(600, 450);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		JPanel p = new JPanel();
		final JButton b1 = new JButton("°´Å¥1");
		final JButton b2 = new JButton("°´Å¥2");
		b1.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					System.out.println(b1.getText());
					b2.doClick();
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
		b2.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
//				if (e.getButton() == MouseEvent.BUTTON1) {
//					System.out.println(new Date());
//				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				System.out.println(new Date());
			}
		});
		p.add(b1);
		p.add(b2);
		f.add(p);
		
		try {
			Thread.sleep(3 * 1000L);
//			b2.doClick(1000);
//			b2.
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
