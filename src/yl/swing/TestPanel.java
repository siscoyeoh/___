package yl.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TestPanel extends JFrame {
    public TestPanel() {
        // 创建面板,默认是流式布局
        JPanel p1 = new JPanel();
        // 指定为网格布局,4行3列
        p1.setLayout(new GridLayout(4, 3));
        for (int i = 1; i <=9; i++) {
            p1.add(new JButton("" + i));
        }
        p1.add(new JButton("0"));
        p1.add(new JButton("OK"));
        p1.add(new JButton("EXIT"));

        // 创建面板,指定边框布局
        JPanel p2 = new JPanel(new BorderLayout());
        // 面板添加文本域,边框北部
        p2.add(new JTextField("我在这里啊!!!"), BorderLayout.NORTH);
        // 面板添加其他面板,边框中部.
        p2.add(p1, BorderLayout.CENTER);

        // 框架添加面板,框架的布局默认就是边框布局,面板指定位于框架西部
        add(p2, BorderLayout.EAST);
        // 框架添加按钮,位于框架总部.
        add(new JButton("哈哈"), BorderLayout.CENTER);

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
