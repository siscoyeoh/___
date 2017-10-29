package yl.swing;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ShowBorderLayout extends JFrame {
    public ShowBorderLayout() {
        setLayout(new BorderLayout(5, 10));

        add(new JButton("��"), BorderLayout.WEST);
        add(new JButton("��"), BorderLayout.EAST);
        add(new JButton("��"), BorderLayout.SOUTH);
        add(new JButton("��"), BorderLayout.NORTH);
        add(new JButton("��"), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        ShowBorderLayout frame = new ShowBorderLayout();
        frame.setTitle("BorderLayout");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
