package yl.swing.grouplayout;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
/**
*
* @author Matt
*/
public class Build4 extends JFrame {
    
    private JPanel panel1;
    private final int FRAME_WIDTH = 470;
    private final int FRAME_HEIGHT = 560;
    private JLabel takeOffLabel, landLabel, takeOffQLabel, landQLabel;
    private JTextField takeOffTxt, landTxt;
    private JTextArea takeOffQ;
    private JButton addTOQ, addLQ;
    private JScrollPane takeOffScroll;
    private Border inputBorder;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem itemExit;
    
    public Build4(){
        
        this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addMenu();
        this.createComp();
        this.setLayout(new GridLayout(2,1));
    }
    
    public void addMenu(){
        
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        itemExit = new JMenuItem("Exit");
        //itemExit.addActionListener();
        fileMenu.add(itemExit);
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }

    public void createComp(){
        
        panel1 = new JPanel();
        
        takeOffLabel = new JLabel("Plane Taking Off:");
        landLabel = new JLabel("Plane Landing:");
        takeOffQLabel = new JLabel("Taking Off:");
        landQLabel = new JLabel("Landing:");
        
        takeOffTxt = new JTextField();
        landTxt = new JTextField();
        
        takeOffQ = new JTextArea(12,12);
        takeOffQ.setLineWrap(true);
        takeOffQ.setWrapStyleWord(true);
        
        takeOffScroll = new JScrollPane(takeOffQ);
        
        addTOQ = new JButton("Add to Queue");
        addLQ = new JButton("Add to Queue");
        
        inputBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Traffic Input"),
            BorderFactory.createEmptyBorder(5,3,5,3));
        
        GroupLayout p1Layout = new GroupLayout(panel1);
        p1Layout.setAutoCreateGaps(true);
        p1Layout.setAutoCreateContainerGaps(true);
        p1Layout.setHorizontalGroup(
            p1Layout.createSequentialGroup()
                .addGroup(p1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(takeOffLabel)
                    .addComponent(takeOffTxt, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                    .addComponent(addTOQ, GroupLayout.Alignment.CENTER)
                    .addComponent(landLabel)
                    .addComponent(landTxt, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                    .addComponent(addLQ, GroupLayout.Alignment.CENTER))
                .addGroup(p1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(takeOffQLabel)
                    .addComponent(takeOffScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        p1Layout.setVerticalGroup(
            p1Layout.createSequentialGroup()
                .addGroup(p1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGroup(p1Layout.createSequentialGroup()
                            .addComponent(takeOffLabel)
                            .addComponent(takeOffTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(addTOQ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(landLabel)
                            .addComponent(landTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(addLQ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))    
                        .addGroup(p1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addGroup(p1Layout.createSequentialGroup()
                                .addComponent(takeOffQLabel)
                                .addComponent(takeOffScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            ))
        );
        
        
        panel1.setLayout(p1Layout);
        panel1.setBorder(inputBorder);
        panel1.add(takeOffLabel);
        panel1.add(takeOffTxt);
        panel1.add(addTOQ);
        panel1.add(landLabel);
        panel1.add(landTxt);
        panel1.add(takeOffQLabel);
        panel1.add(landQLabel);
        panel1.add(addLQ);
        panel1.add(takeOffScroll);
        

        this.add(panel1);

    }
    
    public static void main(String[]args) {
    	new Build4().setVisible(true);
    }
    
}
