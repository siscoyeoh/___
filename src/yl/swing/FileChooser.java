package yl.swing;

import javax.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;//�������¼��ر�����
import java.awt.event.ActionListener;//�������¼��ر�����

public class FileChooser {
	// extends JPanel implements ActionListener
	static JFrame frame = new JFrame("");
	static JPanel panel = new JPanel();
	JButton jb = new JButton("�ϴ�");
	JFileChooser chooser;
	String choosertitle;

	public static void main(String s[]) {
		new FileChooser().Show();
	}

	public void Show() {
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result;
				chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle(choosertitle);
				System.out.println("---" + choosertitle);
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				
				if (chooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
					System.out.println("getCurrentDirectory(): "
							+ chooser.getCurrentDirectory());
					System.out.println("getSelectedFile() : "
							+ chooser.getSelectedFile());
				} else {
					System.out.println("No Selection ");
				}
			}
		});
		// frame.addWindowListener(new WindowAdapter() {
		// public void windowClosing(WindowEvent e) {
		// System.exit(0);
		// }
		// });
		panel.add(jb);
		frame.add(panel, "Center");
		frame.setSize(320, 240);
		frame.setVisible(true);
	}
}