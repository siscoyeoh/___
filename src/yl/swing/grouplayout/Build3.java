package yl.swing.grouplayout;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
/**
*
* @author Matt
*/
public class Build3 extends JFrame {
    
	private static final long serialVersionUID = 8473248439570097179L;
	
	private JPanel panel1, panel2;
    private final int FRAME_WIDTH = 470;
    private final int FRAME_HEIGHT = 560;
    private JTextArea displayLogTextArea;
    private JButton refreshLogsBtn, deleteSelectedLogBtn;
    private JScrollPane stripScroll;
    private Border inputBorder, displayBorder;
    
    public Build3(){
        
        this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.createComp();
        this.setLayout(new GridLayout(2,1));
    }

    public void createComp(){
        
        panel1 = new JPanel();
        panel2 = new JPanel();
        
        displayLogTextArea = new JTextArea(10,35);
        displayLogTextArea.setLineWrap(true);
        displayLogTextArea.setWrapStyleWord(true);
        
        stripScroll = new JScrollPane(displayLogTextArea);
        
        refreshLogsBtn = new JButton("刷新日志列表");
        deleteSelectedLogBtn = new JButton("删除选中文件");
        
        inputBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("操作日志"),
            BorderFactory.createEmptyBorder(5,3,5,3));
        displayBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("显示日志文件"),
            BorderFactory.createEmptyBorder(5,3,5,3));
        
        GroupLayout p1Layout = new GroupLayout(panel1);
        p1Layout.setAutoCreateGaps(true);
        p1Layout.setAutoCreateContainerGaps(true);
        p1Layout.setHorizontalGroup(
            p1Layout.createSequentialGroup()
                .addGroup(p1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(refreshLogsBtn, GroupLayout.Alignment.CENTER)
                    .addComponent(deleteSelectedLogBtn, GroupLayout.Alignment.CENTER))
        );
        p1Layout.setVerticalGroup(
            p1Layout.createSequentialGroup()
                .addGroup(p1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGroup(p1Layout.createSequentialGroup()
                            .addComponent(refreshLogsBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteSelectedLogBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))    
                        .addGroup(p1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)))
        );
        
        GroupLayout p2Layout = new GroupLayout(panel2);
        p2Layout.setAutoCreateGaps(true);
        p2Layout.setAutoCreateContainerGaps(true);
        p2Layout.setHorizontalGroup(
            p2Layout.createSequentialGroup()
                .addGroup(p2Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(stripScroll, GroupLayout.Alignment.CENTER))
        );
        p2Layout.setVerticalGroup(
            p2Layout.createSequentialGroup()
                .addComponent(stripScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        
        panel1.setLayout(p1Layout);
        panel1.setBorder(inputBorder);
        panel1.add(refreshLogsBtn);
        panel1.add(deleteSelectedLogBtn);
        
        panel2.setLayout(p2Layout);
        panel2.setBorder(displayBorder);
        panel2.add(stripScroll);

        this.add(panel1);
        this.add(panel2);
//        JPanel panel = new JPanel();
//        panel.add(panel1);
//        panel.add(panel2);
//        panel.setSize(FRAME_WIDTH,FRAME_HEIGHT);
//        this.add(panel);

    }
    
    public static void main(String[]args) {
    	new Build3().setVisible(true);
    }
    
}
