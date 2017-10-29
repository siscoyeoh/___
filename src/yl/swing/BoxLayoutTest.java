package yl.swing;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoxLayoutTest {
	public static void main(String[] args) {
//		JFrame j = new JFrame();
//		j.setLayout(new BoxLayout(j, BoxLayout.Y_AXIS));
//		j.add(new JPanel());
//		j.add(new JPanel());
//
//		j.pack();
//		j.setVisible(true);
		
		JFrame jf = new JFrame();
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		jp.add(new JPanel());
		jp.add(new JPanel());
		
		jf.add(jp);

		jf.pack();
		jf.setVisible(true);
	}
}
