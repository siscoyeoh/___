package yl.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;

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

public class CommonComponent2 extends JFrame {
    public CommonComponent2() {
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

        // ���
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2, 2, 5, 5));
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

        JPanel p2 = new JPanel();
        // ��ѡ��ť
        JLabel gender = new JLabel("�Ա�");
        p2.add(gender);
        JRadioButton male = new JRadioButton("��");
        JRadioButton female = new JRadioButton("Ů");
        // ��ѡ��ť��,ͬһ����ѡ��ť��Ļ���.
        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);
        // ע��,��ѡ��ť�鲻����ӽ�����
        p2.add(male);
        p2.add(female);

        JPanel p3 = new JPanel();
        // ��ѡ��
        JLabel like = new JLabel("����:");
        p3.add(like);
        JCheckBox eat = new JCheckBox("�Է�");
        JCheckBox movie = new JCheckBox("����Ӱ");
        JCheckBox sleep = new JCheckBox("˯��");
        p3.add(eat);
        p3.add(movie);
        p3.add(sleep);

        JPanel p4 = new JPanel(new GridLayout(1, 0, 5, 5));
        // �ı���
        JLabel info = new JLabel("���˼��:");
        p4.add(info);
        JTextArea area = new JTextArea(50, 10);
        p4.add(area);

        JPanel p5 = new JPanel(new BorderLayout());
        // �б�
        String[] data = { "one", "two", "three" };
        JList list = new JList(data);
        p5.add(list, BorderLayout.WEST);

        JPanel p6 = new JPanel();
        // ��ͨ��ť
        JButton button = new JButton("ע��");
        p6.add(button);
        JButton button2 = new JButton("ȡ��");
        p6.add(button2);

        setLayout(new GridLayout(7, 1, 5, 5));
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(p6);

    }

    public static void main(String[] args) {
        CommonComponent frame = new CommonComponent();
        frame.setTitle("�������");
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ����Ӧ
        // frame.pack();
        frame.setVisible(true);
    }
}
