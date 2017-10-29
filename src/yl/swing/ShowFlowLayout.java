package yl.swing;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ShowFlowLayout extends JFrame {

    public ShowFlowLayout() {
        super.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));

        add(new JLabel("–’√˚:"));
        add(new JTextField(8));
        add(new JLabel("” œ‰:"));
        add(new JTextField(8));
        add(new JLabel("µÁª∞:"));
        add(new JTextField(8));
        
    }

    public static void main(String[] args) {
        ShowFlowLayout frame = new ShowFlowLayout();
        frame.setTitle("FlowLayout");
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
