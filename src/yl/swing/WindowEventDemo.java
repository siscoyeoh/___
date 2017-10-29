package yl.swing;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class WindowEventDemo extends JFrame {

    WindowEventDemo() {
        // this����ʱJFrame�����࣬��JFrame ����Window��ϵ�е�һԱ���Ծ߱���Ӵ����¼��ķ���
        this.addWindowListener(new MyWindow());
    }

    public static void main(String[] args) {
        WindowEventDemo frame = new WindowEventDemo();
        frame.setTitle("�ҵĿ��");
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

// ʵ��WindowListener
class MyWindow implements WindowListener {
    // �����
    public void windowActivated(WindowEvent e) {
        System.out.println("�����");
    }

    // �رմ���
    public void windowClosed(WindowEvent e) {
        System.out.println("�رմ���");
    }

    // ���ڹرմ���
    public void windowClosing(WindowEvent e) {
        System.out.println("���ڹرմ���");
    }

    // ��Ϊ�ǻ����
    public void windowDeactivated(WindowEvent e) {
        System.out.println("��Ϊ�ǻ����");
    }

    // ��ԭ����
    public void windowDeiconified(WindowEvent e) {
        System.out.println("��ԭ����");
    }

    // ��С������
    public void windowIconified(WindowEvent e) {
        System.out.println(" ��С������");
    }

    // �򿪴���
    public void windowOpened(WindowEvent e) {
        System.out.println("�򿪴���");
    }

}
