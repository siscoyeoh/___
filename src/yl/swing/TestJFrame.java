package yl.swing;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
 
import javax.swing.JFrame;
import javax.swing.JTextArea;
 
/** ֻ������������(���������) */
public class TestJFrame extends JFrame {
    private static final long serialVersionUID = 5324461998774938017L;
 
    private JTextArea textArea = null;
 
    public static void main(String[] args) {
        new TestJFrame();
    }
 
    public TestJFrame() {
        super("����");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null); // ���ھ���
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        Container con = this.getContentPane();
        con.setLayout(null);
        textArea = new JTextArea();
        textArea.setBounds(48, 20, 300, 200);
        textArea.addKeyListener(new MyKeyListener());
        textArea.setEditable(false); // ��ֹ�༭
        textArea.setLineWrap(true);
        con.add(textArea);
 
        this.setVisible(true);
 
    }
 
    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            // ֻ����������
            if (e.getKeyCode() >= '0' && e.getKeyCode() <= '9') {
                textArea.append(String.valueOf(e.getKeyChar()));
            }
        }
    }
}
