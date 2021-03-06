package yl.swing.layout;

import java.applet.Applet;  
import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
 
public class GridBagEx1 extends Applet {  
	private static final long serialVersionUID = -5375137787698510397L;

	protected void makebutton(String name,  
                              GridBagLayout gridbag,  
                              GridBagConstraints c) {  
        Button button = new Button(name);  
        gridbag.setConstraints(button, c);  
        add(button);  
    }  
 
    public void init() {  
        GridBagLayout gridbag = new GridBagLayout();  
        GridBagConstraints constriants = new GridBagConstraints();  
 
        setFont(new Font("SansSerif", Font.PLAIN, 14));  
        setLayout(gridbag);  
 
        constriants.fill = GridBagConstraints.BOTH;  
        constriants.weightx = 1.0;  
        makebutton("Button1", gridbag, constriants);  
        makebutton("Button2", gridbag, constriants);  
        makebutton("Button3", gridbag, constriants);  
 
        constriants.gridwidth = GridBagConstraints.REMAINDER; //end row  
        makebutton("Button4", gridbag, constriants);  
 
        constriants.weightx = 0.0;                  //reset to the default  
        makebutton("Button5", gridbag, constriants); //another row  
 
        constriants.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row  
        makebutton("Button6", gridbag, constriants);  
 
          constriants.gridwidth = GridBagConstraints.REMAINDER; //end row  
        makebutton("Button7", gridbag, constriants);  
 
        constriants.gridwidth = 1;                //reset to the default  
        constriants.gridheight = 2;  
        constriants.weighty = 1.0;  
        makebutton("Button8", gridbag, constriants);  
 
        constriants.weighty = 0.0;                  //reset to the default  
        constriants.gridwidth = GridBagConstraints.REMAINDER; //end row  
        constriants.gridheight = 1;               //reset to the default  
        makebutton("Button9", gridbag, constriants);  
        makebutton("Button10", gridbag, constriants);  
 
        setSize(300, 100);  
    }  
 
    public static void main(String args[]) {  
          Frame f = new Frame("GridBagLayoutʵ��");  
          GridBagEx1 ex1 = new GridBagEx1();  
 
          ex1.init();  
 
          f.add("Center", ex1);  
          f.pack();  
          f.setSize(f.getPreferredSize());  
          f.setVisible(true);  
    	
    }  
}  
  