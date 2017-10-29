package yl.swing;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class SplitPaneFrame2 extends JFrame {
	
	private static final long serialVersionUID = -6134037790624160214L;

	public SplitPaneFrame2() {
		super("系统运行监测");
		setBounds(100, 100, 200, 160);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		final JSplitPane hSplitPane = new JSplitPane();
		JLabel left = new JLabel("left");
		JLabel right = new JLabel("right");
		String topName = "监测";
		List<String> nodes = new ArrayList<String>();
		nodes.add("192.168.8.44");
		nodes.add("192.168.8.115");
		nodes.add("192.168.8.123");
		hSplitPane.setLeftComponent(InitialTree(topName, nodes));
		hSplitPane.setRightComponent(right);
		hSplitPane.setDividerLocation(220);
		hSplitPane.setOneTouchExpandable(true);
		getContentPane().add(hSplitPane);
		
	}
	
	private Component InitialTree(String topName, List<String> nodes) {

		DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(topName);
		for (int i = 0; i < nodes.size(); i++) {
			treeNode.add(new DefaultMutableTreeNode(nodes.get(i)));
		}
		final JTree tree = new JTree(treeNode);
		JFrame f = new JFrame("JTreeDemo");
		f.add(tree);
		f.setSize(300, 300);
		// f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 添加选择事件
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();

				if (node == null)
					return;

				Object object = node.getUserObject();
				if (node.isLeaf()) {
					User user = (User) object;
					System.out.println("你选择了：" + user.toString());
				}

			}
		});
		return tree;
	}
	
	public static void main(String[]s) {
		SplitPaneFrame2 demo2 = new SplitPaneFrame2();
		demo2.setSize(600, 450);
	}

}
