package yl.swing;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CommonComponent extends JFrame {
	private static final long serialVersionUID = -6807072553988781519L;

	public CommonComponent() {

        // ���
        JPanel p1 = new JPanel();
        add(p1);

        // ��ǩ
        JLabel name = new JLabel("�û���:");
        p1.add(name);

        // �ı���
        JTextField field = new JTextField(8);
        p1.add(field);

        // ��ǩ
        JLabel passwd = new JLabel("����");
        p1.add(passwd);
        // ������
        JPasswordField pass = new JPasswordField(8);
        p1.add(pass);

        // ��ѡ��ť
        JLabel gender = new JLabel("�Ա�");
        p1.add(gender);
        JRadioButton male = new JRadioButton("��");
        JRadioButton female = new JRadioButton("Ů");
        // ��ѡ��ť��,ͬһ����ѡ��ť��Ļ���.
        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);
        // ע��,��ѡ��ť�鲻����ӽ�����
        p1.add(male);
        p1.add(female);

        // ��ѡ��
        JLabel like = new JLabel("����:");
        p1.add(like);
        JCheckBox eat = new JCheckBox("�Է�");
        JCheckBox movie = new JCheckBox("����Ӱ");
        JCheckBox sleep = new JCheckBox("˯��");
        p1.add(eat);
        p1.add(movie);
        p1.add(sleep);

        // �ı���
        JLabel info = new JLabel("���˼��");
        p1.add(info);
        JTextArea area = new JTextArea(20, 20);
        p1.add(area);

        // �б�
        String[] data = { "one", "two", "three" };
        JList list = new JList(data);
        p1.add(list);

        // ��ͨ��ť
        JButton button = new JButton("ע��");
        p1.add(button);

        // �˵���
        JMenuBar bar = new JMenuBar();
        // �˵�
        JMenu menu = new JMenu("�ļ�");
        // �˵�ѡ��
        JMenuItem myNew = new JMenuItem("�½�");
        JMenuItem myOpen = new JMenuItem("��");
        bar.add(menu);
        menu.add(myNew);
        menu.add(myOpen);
        add(bar, BorderLayout.NORTH);

    }

    public static void main(String[] args) {
        CommonComponent frame = new CommonComponent();
        frame.setTitle("�������");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ����Ӧ
        frame.pack();
        frame.setVisible(true);
    }
}
