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
		Box baseBox = Box.createHorizontalBox();// �Ȳ���ˮƽ���з�ʽ��Box�����������������(BaseBox)
		contentPane.add(baseBox);

		/*
		 * ������ֱ���з�ʽ��Box��������õ�һ���������ť
		 */
		Box vBox = Box.createVerticalBox();
		JButton b = new JButton("first");
		vBox.add(b);
		b = new JButton("third");
		b.setMaximumSize(new Dimension(100, 150));
		vBox.add(b);
		baseBox.add(vBox);

		/*
		 * ������ֱ���з�ʽ��Box��������õڶ�����һ��ˮƽ���з�ʽ��Box���
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
		 * �����������һ����ť����ˮƽ���з�ʽ��Box�����
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
        // �������3���������ɣ���ˮƽ��������
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
        //�������ڰ������������ؼ�
        JLabel sourceLabel;//���ֱ�ǩ
        JScrollPane sourceListScroller;//������

        //���ֱ�ǩ
        sourceLabel = new JLabel("�˶���Ŀ");
        sourceLabel.setAlignmentY(TOP_ALIGNMENT);
        sourceLabel.setBorder(BorderFactory.createEmptyBorder(4, 5, 0, 5));
        
        // ����һ���б������˶���Ŀ
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        listModel.addElement("100��");
        listModel.addElement("200��");
        listModel.addElement("400��");
        listModel.addElement("��Զ");
        listModel.addElement("����");
        listModel.addElement("Ǧ��");
        
        JList<String> sourceList = new JList<String>(listModel);
        sourceList
                .setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        sourceList.setVisibleRowCount(5);//��ʼ״̬����5�пɼ�

        //������
        sourceListScroller = new JScrollPane(sourceList);
        sourceListScroller
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sourceListScroller.setAlignmentY(TOP_ALIGNMENT);
        
        //��ʼ���������
        sportPanel = new JPanel();        
        sportPanel.setLayout(new BoxLayout(sportPanel, BoxLayout.Y_AXIS));// ��ֱ����
        sportPanel.setBorder(BorderFactory.createBevelBorder(1));
        
        sportPanel.add(sourceLabel);// �������ֱ�ǩ��
        sportPanel.add(sourceListScroller);// �����˶���Ŀ�б�

    }

    private void setMiddlePanel() {
        //����������2����ť
        JButton toTargetButton = new JButton(">>");
        JButton toSourceButton = new JButton("<<");

        //���������
        middlePanel = new JPanel();
        middlePanel.setBorder(BorderFactory.createBevelBorder(1));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));//�����Ϊ��ֱ����
        
        middlePanel.add(toTargetButton);// ��ӵ�һ����ť>>
        middlePanel.add(Box.createRigidArea(new Dimension(15, 15)));// �м����һ����������rigidArea
        middlePanel.add(toSourceButton);// ��ӵڶ�����ť<<
    }

    private void setQueryPanel() {
        
        //����������2���ؼ�
        JLabel targetLabel;
        JScrollPane targetListScroller;
        
        // ���ֱ�ǩ
        targetLabel = new JLabel("��ѯ��Ŀ");
        targetLabel.setAlignmentY(TOP_ALIGNMENT);
        targetLabel.setBorder(BorderFactory.createEmptyBorder(4, 5, 0, 5));
        
        // �����б��ѯ��Ŀ
//        DefaultListModel<String> targetListModel = new DefaultListModel<String>();
//        targetListModel.addElement("100��");
//        JList<String> targetList = new JList<String>(targetListModel);
//        targetList
//                .setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        //������
//        targetListScroller = new JScrollPane(targetList);
//        targetListScroller
//                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        targetListScroller.setAlignmentY(TOP_ALIGNMENT);


        //��������岼��
        queryPanel = new JPanel();        
        queryPanel.setLayout(new BoxLayout(queryPanel, BoxLayout.Y_AXIS));// ��ֱ����
        queryPanel.setBorder(BorderFactory.createBevelBorder(1));


        queryPanel.add(targetLabel);//������ֱ�ǩ
        queryPanel.add(targetListScroller);//��ӹ�����
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
