package yl.yibai.swing;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class SwingMenuDemo {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel; 

   public SwingMenuDemo(){
      prepareGUI();
   }

   public static void main(String[] args){
      SwingMenuDemo  swingMenuDemo = new SwingMenuDemo();     
      swingMenuDemo.showPopupMenuDemo();
   }
   
   private void prepareGUI(){
      mainFrame = new JFrame("Java SWING Examples - by www.yiibai.com");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));

      headerLabel = new JLabel("",JLabel.CENTER );
      statusLabel = new JLabel("",JLabel.CENTER);        

      statusLabel.setSize(350,100);
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }

   private void showPopupMenuDemo(){
      final JPopupMenu editMenu = new JPopupMenu("Edit"); 

      JMenuItem cutMenuItem = new JMenuItem("Cut");
      cutMenuItem.setActionCommand("Cut");

      JMenuItem copyMenuItem = new JMenuItem("Copy");
      copyMenuItem.setActionCommand("Copy");

      JMenuItem pasteMenuItem = new JMenuItem("Paste");
      pasteMenuItem.setActionCommand("Paste");

//      MenuItemListener menuItemListener = new MenuItemListener();

//      cutMenuItem.addActionListener(menuItemListener);
//      copyMenuItem.addActionListener(menuItemListener);
//      pasteMenuItem.addActionListener(menuItemListener);
      cutMenuItem.addActionListener(null);
      copyMenuItem.addActionListener(null);
      pasteMenuItem.addActionListener(null);

      editMenu.add(cutMenuItem);
      editMenu.add(copyMenuItem);
      editMenu.addSeparator();
      editMenu.add(pasteMenuItem);   

      mainFrame.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {            
            editMenu.show(mainFrame, e.getX(), e.getY());
         }               
      });
      mainFrame.add(editMenu); 
      mainFrame.setVisible(true);
   }
}