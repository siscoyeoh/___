package yl.swing.grouplayout;

import java.awt.Container;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GLayout2 extends JFrame {

	private static final long serialVersionUID = -7964051155920182315L;
	
	public GLayout2() {
		super("Find");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		String laf = UIManager.getCrossPlatformLookAndFeelClassName();
		String laf = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(laf);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel label1 = new JLabel("Find What:");
		JTextField textField1 = new JTextField();
		JCheckBox caseCheckBox = new JCheckBox("Match Case");
		JCheckBox wholeCheckBox = new JCheckBox("Whole Words");
		JCheckBox wrapCheckBox = new JCheckBox("Wrap Around");
		JCheckBox backCheckBox = new JCheckBox("Search Backwards");
		JButton finbButton = new JButton("Find");
		JButton cancelButton = new JButton("Cancel");
		
		Container container = this.getContentPane();
		GroupLayout groupLayout = new GroupLayout(container);
		container.setLayout(groupLayout);
		
		// 自动设定组件、组之间的间隙
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		// LEADING:左对齐, BASELINE:底部对齐, CENTER:中心对齐
		GroupLayout.ParallelGroup hpg2a = groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
		hpg2a.addComponent(caseCheckBox);
		hpg2a.addComponent(wholeCheckBox);
		
		GroupLayout.ParallelGroup hpg2b = groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
		hpg2b.addComponent(wrapCheckBox);
		hpg2b.addComponent(backCheckBox);
		
		
	}

}
