//package yl.swing.calendar;
//
//import java.awt.BorderLayout;  
//import java.awt.FlowLayout;  
//import java.awt.GridBagConstraints;  
//import java.awt.GridBagLayout;  
//import java.awt.GridLayout;  
//import java.awt.Label;  
//import java.awt.event.ActionEvent;  
//import java.awt.event.ActionListener;  
//import java.awt.event.MouseEvent;  
//import java.awt.event.MouseListener;  
//  
//import javax.swing.JButton;  
//import javax.swing.JComboBox;  
//import javax.swing.JLabel;  
//import javax.swing.JPanel;  
//import javax.swing.SwingConstants;  
//  
//import cn.net.iem.calendar.JCalendarChooser;  
//import cn.net.iem.view.EditJTextField;  
//import cn.net.iem.view.loggin.Loggin;  
///* 
//     * @param time      ����ʱ�� 
//     * @param longitude ���� 
//     * @param altitude  γ�� 
//     * @param depth     ��� 
//     * @param magnitude �� 
//     * @param location  �ο�λ�� 
// * */  
//public class SearchPane extends JPanel implements ActionListener,MouseListener {  
//    //�༭����  
//    //private JComboBox regionComBox;                
//    private EditJTextField minTimeField,maxTimeField,minLongitudeField,maxLongitudeField,minAltitudeField,maxAltitudeField,  
//                           minDepthField,maxDepthField,minMagnitudeField,maxMagnitudeField,locationField;  
//    private JButton searchBut;  
//    //����༭���Ժ�Ľ��  
//    private String minTime;  
//    private String maxTime;  
//    private float minLongitude;  
//    private float maxLongitude;  
//    private float minAltitude;  
//    private float maxAltitude;  
//    private int minDepth;  
//    private int maxDepth;  
//    private float minMagnitude;  
//    private float maxMagnitude;  
//    private String location;  
//    public SearchPane(){  
//        super();  
//        initial(10);  
//        create();  
//        addListener();  
//    }  
//    /** 
//     *��ʼ�������� 
//     * */  
//    private void initial(int size){  
//        minTimeField=new EditJTextField(size);  
//        maxTimeField=new EditJTextField(size);  
//        minLongitudeField=new EditJTextField(size);  
//        maxLongitudeField=new EditJTextField(size);  
//        minAltitudeField=new EditJTextField(size);  
//        maxAltitudeField=new EditJTextField(size);  
//        minDepthField=new EditJTextField(size);  
//        maxDepthField=new EditJTextField(size);  
//        minMagnitudeField=new EditJTextField(size);  
//        maxMagnitudeField=new EditJTextField(size);  
//        locationField=new EditJTextField(size);  
//        searchBut=new JButton("��ѯ");  
//    }  
//    /** 
//     *�����Ӽ����� 
//     * */  
//    private void addListener(){  
//        searchBut.addActionListener(this);  
//        minTimeField.addMouseListener(this);  
//        maxTimeField.addMouseListener(this);  
//    }   
//    /** 
//     *�������  
//     * */  
//    private void create(){    
//        JLabel label1=new JLabel("����ʱ�䣺",JLabel.RIGHT);  
//        JLabel label3=new JLabel("���ȣ�",JLabel.RIGHT);  
//        JLabel label4=new JLabel("γ�ȣ�",JLabel.RIGHT);  
//        JLabel label5=new JLabel("���(ǧ��)��",JLabel.RIGHT);  
//        JLabel label6=new JLabel("��(M)��",JLabel.RIGHT);  
//        JLabel label7=new JLabel("λ��(֧��ģ����ѯ)��",JLabel.RIGHT);    
//        JPanel pane1=new JPanel();  
//        pane1.add(searchBut);  
//        JPanel pane2=new JPanel();  
//        pane2.setLayout(new GridBagLayout());  
//        //���������鲼�ֹ������Ĳ���  
//        GridBagConstraints constraints=new GridBagConstraints();  
//        //ʹ�������鲼����ӿؼ�  
//        Loggin.add(pane2, label1,           constraints, 0,0,1,1,0,0,0,0);   
//        Loggin.add(pane2, minTimeField,     constraints, 1,0,2,1,0,0,0,0);   
//        Loggin.add(pane2, new JLabel(" �� "), constraints, 3,0,1,1,0,0,0,0);   
//        Loggin.add(pane2, maxTimeField,     constraints, 4,0,2,1,0,0,0,0);   
//        Loggin.add(pane2, new JLabel("        "),   constraints, 6,0,2,1,0,0,0,0);   
//        Loggin.add(pane2, label3,           constraints, 8,0,1,1,0,0,0,0);   
//        Loggin.add(pane2, minLongitudeField,constraints, 9,0,2,1,0,0,0,0);   
//        Loggin.add(pane2, new JLabel(" �� "), constraints,11,0,1,1,0,0,0,0);  
//        Loggin.add(pane2, maxLongitudeField,constraints,12,0,2,1,0,0,0,0);        
//        Loggin.add(pane2, label4,           constraints, 0,1,1,1,0,0,0,0);   
//        Loggin.add(pane2, minAltitudeField, constraints, 1,1,2,1,0,0,0,0);   
//        Loggin.add(pane2, new JLabel(" �� "), constraints, 3,1,1,1,0,0,0,0);   
//        Loggin.add(pane2, maxAltitudeField, constraints, 4,1,2,1,0,0,0,0);   
//        Loggin.add(pane2, new JLabel("        "),   constraints, 6,1,2,1,0,0,0,0);   
//        Loggin.add(pane2, label5,           constraints, 8,1,1,1,0,0,0,0);   
//        Loggin.add(pane2, minDepthField,    constraints, 9,1,2,1,0,0,0,0);   
//        Loggin.add(pane2, new JLabel(" �� "), constraints,11,1,1,1,0,0,0,0);  
//        Loggin.add(pane2, maxDepthField,    constraints,12,1,2,1,0,0,0,0);     
//        Loggin.add(pane2, label6,           constraints, 0,2,1,1,0,0,0,0);   
//        Loggin.add(pane2, minMagnitudeField,constraints, 1,2,2,1,0,0,0,0);   
//        Loggin.add(pane2, new JLabel(" �� "), constraints, 3,2,1,1,0,0,0,0);   
//        Loggin.add(pane2, maxMagnitudeField,constraints, 4,2,2,1,0,0,0,0);   
//        Loggin.add(pane2, new JLabel("        "),   constraints, 6,2,2,1,0,0,0,0);   
//        Loggin.add(pane2, label7,           constraints, 8,2,1,1,0,0,0,0);   
//        Loggin.add(pane2, locationField,    constraints, 9,2,5,1,0,0,0,0);   
//          
//        this.setLayout(new BorderLayout());  
//        this.add(pane2,BorderLayout.NORTH);   
//        this.add(pane1,BorderLayout.SOUTH);  
//    }  
//    @Override  
//    public void actionPerformed(ActionEvent e) {  
//        if(e.getSource()==searchBut){  
//            //�򿪲�ѯ����  
//            new SearchFrame().setVisible(true);  
//        }  
//    }  
//    @Override  
//    public void mouseClicked(MouseEvent e) {  
//        // TODO Auto-generated method stub  
//        if(e.getSource()==minTimeField&&e.getButton()==MouseEvent.BUTTON1){  
//            JCalendarChooser myJCalendar = new JCalendarChooser(minTimeField);  
//            int returnValue = myJCalendar.showOpenDialog();  
//            if (returnValue == JCalendarChooser.CLEAR_OPTION)  
//                minTimeField.setText("");  
//            else if (returnValue == JCalendarChooser.APPROVE_OPTION)  
//                minTimeField.setText(myJCalendar.getDate());  
//        }else if(e.getSource()==maxTimeField&&e.getButton()==MouseEvent.BUTTON1){  
//            JCalendarChooser myJCalendar = new JCalendarChooser(maxTimeField);  
//            int returnValue = myJCalendar.showOpenDialog();  
//            if (returnValue == JCalendarChooser.CLEAR_OPTION)  
//                maxTimeField.setText("");  
//            else if (returnValue == JCalendarChooser.APPROVE_OPTION)  
//                maxTimeField.setText(myJCalendar.getDate());  
//        }  
//    }  
//    public void mouseEntered(MouseEvent e) {}  
//    public void mouseExited(MouseEvent e) {}  
//    public void mousePressed(MouseEvent e) {}  
//    public void mouseReleased(MouseEvent e) {}  
//} 