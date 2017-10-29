package yl.swing;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MouseEventDemo extends JFrame {
    MouseEventDemo() {
        JButton button = new JButton("ok");
        JTextArea text = new JTextArea();
        add(button, BorderLayout.NORTH);
        add(text, BorderLayout.CENTER);

        button.addMouseListener(new MouseListener() {

            // ��갴ť��������ͷ�ʱ���á�
            public void mouseReleased(MouseEvent e) {
                System.out.println("����ͷ�");

            }

            // ��갴��������ϰ���ʱ���á�
            public void mousePressed(MouseEvent e) {
                System.out.println("��갴�����");

            }

            // ����뿪���ʱ���á�
            public void mouseExited(MouseEvent e) {
                System.out.println("����뿪���");

            }

            // �����뵽�����ʱ���á�
            public void mouseEntered(MouseEvent e) {
                // ������
                System.out.println("���������");

            }

            // ��갴��������ϵ��������²��ͷţ�ʱ���á�
            public void mouseClicked(MouseEvent e) {
                System.out.println("��굥�����");

            }
        });
        text.addKeyListener(new KeyListener() {

            // ����ĳ����ʱ���ô˷�����
            public void keyTyped(KeyEvent e) {
                System.out.println("����ĳ����");
                if (e.getKeyChar() == 'q') {
                    System.exit(0);
                }
            }
            // �ͷ�ĳ����ʱ���ô˷�����
            public void keyReleased(KeyEvent e) {
                System.out.println("�����ͷ�");

            }

            // ����ĳ����ʱ���ô˷�����
            public void keyPressed(KeyEvent e) {
                System.out.println("���̰���");
            }
        });
    }

    public static void main(String[] args) {
        MouseEventDemo frame = new MouseEventDemo();
        frame.setTitle("����¼�");
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
