package yl.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TestPanel extends JFrame {
    public TestPanel() {
        // �������,Ĭ������ʽ����
        JPanel p1 = new JPanel();
        // ָ��Ϊ���񲼾�,4��3��
        p1.setLayout(new GridLayout(4, 3));
        for (int i = 1; i <=9; i++) {
            p1.add(new JButton("" + i));
        }
        p1.add(new JButton("0"));
        p1.add(new JButton("OK"));
        p1.add(new JButton("EXIT"));

        // �������,ָ���߿򲼾�
        JPanel p2 = new JPanel(new BorderLayout());
        // �������ı���,�߿򱱲�
        p2.add(new JTextField("�������ﰡ!!!"), BorderLayout.NORTH);
        // �������������,�߿��в�.
        p2.add(p1, BorderLayout.CENTER);

        // ���������,��ܵĲ���Ĭ�Ͼ��Ǳ߿򲼾�,���ָ��λ�ڿ������
        add(p2, BorderLayout.EAST);
        // �����Ӱ�ť,λ�ڿ���ܲ�.
        add(new JButton("����"), BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        TestPanel frame = new TestPanel();
        frame.setTitle("JPanel");
        frame.setSize(400, 260);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
