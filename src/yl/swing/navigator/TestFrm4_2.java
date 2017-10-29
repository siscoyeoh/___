package yl.swing.navigator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class TestFrm4_2 extends JFrame{
	private static final long serialVersionUID = -3318564815960879075L;
	private JButton btn1,btn3;
    private JPanel pNorth,pSouth,subMenuContainer;
    private JScrollPane pCenter;
    private JButton[] btn = null;
    private static boolean expandBtn1=false;
    private static boolean expandBtn3=false;
    
    public TestFrm4_2(){
        btn1=new JButton("Grade1 menu1");
        btn1.setBackground(Color.CYAN);
        btn3=new JButton("Grade1 menu3");
        btn3.setBackground(Color.CYAN);
        btn3.addActionListener(new ActionHandler());
        
        pNorth=new JPanel();
        pNorth.setLayout(new GridLayout(2,1));
        pSouth=new JPanel();
        pSouth.setLayout(new GridLayout(2,1));
        pSouth.setOpaque(false);
        pSouth.setBackground(Color.RED);
        subMenuContainer=new JPanel();
        subMenuContainer.setLayout(new GridLayout(5,1));
     
        btn=new JButton[5];        
        for(int i=0;i<btn.length;i++){
            btn[i]=new JButton("[菜单"+i+"]");
            btn[i].setBackground(Color.WHITE);
        }
        
        this.setLayout(new BorderLayout());
        
        pNorth.add(btn1); pNorth.add(btn3); 
        for(int i=0;i<btn.length;i++){
            subMenuContainer.add(btn[i]);
        }
        pCenter=new JScrollPane(subMenuContainer);
        pCenter.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pCenter.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        
        this.add(pNorth,"North");
        this.add(pCenter,"Center");
        this.add(pSouth,"South");
       
        this.setVisible(true);
        this.setSize(500,600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
    
    private class ActionHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(btn3 == e.getSource()){
                if(expandBtn3){//折叠
                   pNorth.setLayout(new GridLayout(3,1));
                   for(int i=0;i<btn.length;i++){
                       subMenuContainer.add(btn[i]);
                   }     
                   validate();
                   getContentPane().repaint();
                   expandBtn3=false;
                } else{//展开
                    for(int i=0;i<btn.length;i++){
                        subMenuContainer.remove(btn[i]);
                    }
                    pSouth.removeAll();
                    pNorth.setLayout(new GridLayout(5,1));
                    pNorth.repaint();
                    pCenter.repaint();
                    pSouth.repaint();
                    validate();
                    getContentPane().repaint();
                    expandBtn3=true;
                }
            }
            else if (btn1 == e.getSource()) {
            	 if(expandBtn1){//折叠
                     pNorth.setLayout(new GridLayout(3,1));
                     for(int i=0;i<btn.length;i++){
                         subMenuContainer.add(btn[i]);
                     }     
                     validate();
                     getContentPane().repaint();
                     expandBtn1=false;
                  } else{//展开
                      for(int i=0;i<btn.length;i++){
                          subMenuContainer.remove(btn[i]);
                      }
                      pSouth.removeAll();
                      pNorth.setLayout(new GridLayout(5,1));
                      pNorth.repaint();
                      pCenter.repaint();
                      pSouth.repaint();
                      validate();
                      getContentPane().repaint();
                      expandBtn1=true;
                  }
            }
        }
        
    }
    
    public static void main(String[] args) {
       
        new TestFrm4_2();
    }

}