package yl.swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

public class SplitPaneFrame extends JFrame {
	public SplitPaneFrame(){
		super("ʹ�÷ָ����");
		setBounds(100, 100, 200, 160);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		final JSplitPane hSplitPane = new JSplitPane();
		JButton button = new JButton("���");
		final JSplitPane vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JLabel top = new JLabel("����");
		JLabel bottom = new JLabel("����");
		vSplitPane.setTopComponent(top);
		vSplitPane.setBottomComponent(bottom);
		vSplitPane.setDividerLocation(40);
		hSplitPane.setLeftComponent(button);
		hSplitPane.setRightComponent(vSplitPane);
		hSplitPane.setDividerLocation(40);
		hSplitPane.setOneTouchExpandable(true);
		getContentPane().add(hSplitPane);
		}
		public static void main(String[] args) {
		// TODO Auto-generated method stub
		SplitPaneFrame demo = new SplitPaneFrame();
		demo.setSize(600, 450);
		}
}
