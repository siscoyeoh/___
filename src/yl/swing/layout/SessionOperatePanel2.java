package yl.swing.layout;

import java.awt.Button;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class SessionOperatePanel2 extends JPanel {
	private static final long serialVersionUID = -5375137787698510397L;

	protected void makebutton(String name,
                              GridBagLayout gridbag,
                              GridBagConstraints c) {
        Button button = new Button(name);
        gridbag.setConstraints(button, c);
        add(button);
    }
	
	protected void makeInfoLabel(String name,
            GridBagLayout gridbag,
            GridBagConstraints c) {
		Label label = new Label(name + ":");
		label.setAlignment(Label.RIGHT);
//		JLabel label = new JLabel(name);
		gridbag.setConstraints(label, c);
		add(label);
	}
	
	protected void makeValueLabel(String name,
            GridBagLayout gridbag,
            GridBagConstraints c) {
		Label label = new Label(name);
		label.setAlignment(Label.LEFT);
		label.setFont(new Font("SansSerif", Font.BOLD, 14));
//		JLabel label = new JLabel(name);
		gridbag.setConstraints(label, c);
		add(label);
	}
 
    public void init() {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints constriants = new GridBagConstraints();
 
        setFont(new Font("SansSerif", Font.PLAIN, 14));
        setLayout(gridbag);
 
        constriants.fill = GridBagConstraints.BOTH;
        constriants.weightx = 1.0;
        makeInfoLabel("LableXXXX11", gridbag, constriants);
        makeValueLabel("LableXXXX12", gridbag, constriants);
        makeInfoLabel("LableXXXX21", gridbag, constriants);
        makeValueLabel("LableXXXX22", gridbag, constriants);
        makeInfoLabel("LableXXXX31", gridbag, constriants);
        constriants.gridwidth = GridBagConstraints.REMAINDER; //end row  
        makeValueLabel("LableXXXX32", gridbag, constriants);
 
         constriants.gridwidth = GridBagConstraints.REMAINDER; //end row  
        makebutton("Button7", gridbag, constriants);
 
        constriants.gridwidth = 1; //reset to the default  
        constriants.gridheight = 1;
        constriants.weighty = 1.0;
        makebutton("Button8", gridbag, constriants);
 
        constriants.weighty = 0.0; //reset to the default  
        constriants.gridwidth = GridBagConstraints.REMAINDER; //end row  
        constriants.gridheight = 1; //reset to the default  
        makebutton("Button9", gridbag, constriants);
        makebutton("Button10", gridbag, constriants);
 
        setSize(300, 100);
    }
 
    public static void main(String args[]) {
//          Frame f = new Frame("GridBagLayoutÊµÀý");
//          GridBagEx2 ex1 = new GridBagEx2();
// 
//          ex1.init();
// 
//          f.add("Center", ex1);
//          f.pack();
//          f.setSize(f.getPreferredSize());
//          f.setVisible(true);
    	JFrame f = new JFrame("GridBagLayoutÊµÀý");
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	SessionOperatePanel2 ex1 = new SessionOperatePanel2();
    	ex1.init();
    	f.add(ex1);
    	f.pack();
    	f.setSize(f.getPreferredSize());
    	f.setVisible(true);
    }
}
  