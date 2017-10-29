package yl.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class AdapterDemo extends JFrame {
    AdapterDemo() {
        addWindowListener(new MyAdapter());
    }

    public static void main(String[] args) {
        AdapterDemo frame = new AdapterDemo();
        frame.setTitle("ÎÒµÄ¿ò¼Ü");
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    class MyAdapter extends WindowAdapter {
        public void windowActivated(WindowEvent e) {
            System.out.println("windowActivated....");
        }
    }

}
