package yl.swing;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public static void main(String[] args) {
        MainPanel panel = new MainPanel();
        MainFrame frame = new MainFrame("Îå×ÓÆå");
        frame.setSize(680,680);
        panel.setBackground(Color.GRAY);
        frame.add(panel,BorderLayout.CENTER);
        panel.addMouseListener(panel);
        frame.setVisible(true);
    }
    public MainFrame(){
        super();
    }
    public MainFrame(String str){
        super(str);
    }
}
