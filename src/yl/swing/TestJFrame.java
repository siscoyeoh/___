package yl.swing;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
 
import javax.swing.JFrame;
import javax.swing.JTextArea;
 
/** 只允许输入数字(大键盘数字) */
public class TestJFrame extends JFrame {
    private static final long serialVersionUID = 5324461998774938017L;
 
    private JTextArea textArea = null;
 
    public static void main(String[] args) {
        new TestJFrame();
    }
 
    public TestJFrame() {
        super("测试");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null); // 窗口居中
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        Container con = this.getContentPane();
        con.setLayout(null);
        textArea = new JTextArea();
        textArea.setBounds(48, 20, 300, 200);
        textArea.addKeyListener(new MyKeyListener());
        textArea.setEditable(false); // 禁止编辑
        textArea.setLineWrap(true);
        con.add(textArea);
 
        this.setVisible(true);
 
    }
 
    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            // 只能输入数字
            if (e.getKeyCode() >= '0' && e.getKeyCode() <= '9') {
                textArea.append(String.valueOf(e.getKeyChar()));
            }
        }
    }
}
