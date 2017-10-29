package yl.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class BoxLayoutDemo extends JPanel {
	public BoxLayoutDemo() {
		JFrame f = new JFrame();

		Container contentPane = f.getContentPane();
		Box baseBox = Box.createHorizontalBox();// 先产生水平排列方式的Box组件，当作基底容器(BaseBox)
		contentPane.add(baseBox);

		/*
		 * 产生垂直排列方式的Box组件来安置第一与第三个按钮
		 */
		Box vBox = Box.createVerticalBox();
		JButton b = new JButton("first");
		vBox.add(b);
		b = new JButton("third");
		b.setMaximumSize(new Dimension(100, 150));
		vBox.add(b);
		baseBox.add(vBox);

		/*
		 * 产生垂直排列方式的Box组件来安置第二与另一个水平排列方式的Box组件
		 */
		Box vBox1 = Box.createVerticalBox();
		baseBox.add(vBox1);
		b = new JButton("second");
		b.setAlignmentX(Component.CENTER_ALIGNMENT);
		b.setMaximumSize(new Dimension(300, 50));
		vBox1.add(b);

		Box hBox = Box.createHorizontalBox();
		vBox1.add(hBox);

		/*
		 * 将第四与最后一个按钮加入水平排列方式的Box组件中
		 */
		Box vBox2 = Box.createVerticalBox();
		vBox2.add(Box.createVerticalStrut(50));
		vBox2.add(new JButton("fourth"));
		vBox2.add(Box.createVerticalStrut(50));
		hBox.add(vBox2);

		Box vBox3 = Box.createVerticalBox();
		vBox3.add(Box.createVerticalGlue());
		vBox3.add(new JButton("THis is the last button"));
		hBox.add(vBox3);

		f.setTitle("BoxLayout");
		f.pack();
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	public static void main(String[] args) {
		BoxLayoutDemo b = new BoxLayoutDemo();
	}
	
	

	// jdk 7
/*
    JPanel sportPanel;
    JPanel queryPanel;
    JPanel middlePanel;

    public BoxLayoutDemo() {
        // 主面板由3个子面板组成，在水平方向排列
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setSportPanel();
        this.setMiddlePanel();
        this.setQueryPanel();

        this.add(sportPanel);
        this.add(middlePanel);
        this.add(queryPanel);
    }

    private void setSportPanel() {
        System.out.println("setSportPanel called");
        //本函数内包含以下两个控件
        JLabel sourceLabel;//文字标签
        JScrollPane sourceListScroller;//滚动条

        //文字标签
        sourceLabel = new JLabel("运动项目");
        sourceLabel.setAlignmentY(TOP_ALIGNMENT);
        sourceLabel.setBorder(BorderFactory.createEmptyBorder(4, 5, 0, 5));
        
        // 创建一个列表，包含运动项目
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        listModel.addElement("100米");
        listModel.addElement("200米");
        listModel.addElement("400米");
        listModel.addElement("跳远");
        listModel.addElement("跳高");
        listModel.addElement("铅球");
        
        JList<String> sourceList = new JList<String>(listModel);
        sourceList
                .setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        sourceList.setVisibleRowCount(5);//初始状态保持5行可见

        //滚动条
        sourceListScroller = new JScrollPane(sourceList);
        sourceListScroller
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sourceListScroller.setAlignmentY(TOP_ALIGNMENT);
        
        //开始布局主面板
        sportPanel = new JPanel();        
        sportPanel.setLayout(new BoxLayout(sportPanel, BoxLayout.Y_AXIS));// 垂直布局
        sportPanel.setBorder(BorderFactory.createBevelBorder(1));
        
        sportPanel.add(sourceLabel);// 加入文字标签到
        sportPanel.add(sourceListScroller);// 加入运动项目列表

    }

    private void setMiddlePanel() {
        //本函数包含2个按钮
        JButton toTargetButton = new JButton(">>");
        JButton toSourceButton = new JButton("<<");

        //布局主面板
        middlePanel = new JPanel();
        middlePanel.setBorder(BorderFactory.createBevelBorder(1));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));//主面板为垂直布局
        
        middlePanel.add(toTargetButton);// 添加第一个按钮>>
        middlePanel.add(Box.createRigidArea(new Dimension(15, 15)));// 中间添加一个看不见的rigidArea
        middlePanel.add(toSourceButton);// 添加第二个按钮<<
    }

    private void setQueryPanel() {
        
        //本函数包含2个控件
        JLabel targetLabel;
        JScrollPane targetListScroller;
        
        // 文字标签
        targetLabel = new JLabel("查询项目");
        targetLabel.setAlignmentY(TOP_ALIGNMENT);
        targetLabel.setBorder(BorderFactory.createEmptyBorder(4, 5, 0, 5));
        
        // 创建列表查询项目
//        DefaultListModel<String> targetListModel = new DefaultListModel<String>();
//        targetListModel.addElement("100米");
//        JList<String> targetList = new JList<String>(targetListModel);
//        targetList
//                .setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        //滚动条
//        targetListScroller = new JScrollPane(targetList);
//        targetListScroller
//                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        targetListScroller.setAlignmentY(TOP_ALIGNMENT);


        //设置主面板布局
        queryPanel = new JPanel();        
        queryPanel.setLayout(new BoxLayout(queryPanel, BoxLayout.Y_AXIS));// 垂直布局
        queryPanel.setBorder(BorderFactory.createBevelBorder(1));


        queryPanel.add(targetLabel);//添加文字标签
        queryPanel.add(targetListScroller);//添加滚动条
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BoxlayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new BoxLayoutDemo());
        frame.pack();
        // frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
*/
}
