package yl.swing.grouplayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GroupLayoutSimpleDemo extends JFrame{
	private static final long serialVersionUID = 1L;
	private GroupLayout layout;
	private GroupLayout.SequentialGroup seqgroup;
	private GroupLayout.ParallelGroup pargroup;
	private JButton a;
	private JButton b;
	private JButton c;
	private JPanel panel; // doesn't work with this.getContentPane()
	
	public GroupLayoutSimpleDemo(){
		a = new JButton("A");
		b = new JButton("B");
		c = new JButton("C");
		panel = new JPanel();
		layout = new GroupLayout(panel);  // panel.setLayout(layout); gives error doesn't work
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		seqgroup = layout.createSequentialGroup();
		pargroup = layout.createParallelGroup();
		
		pargroup.addComponent(a).addComponent(c);
		seqgroup.addComponent(a).addComponent(b).addComponent(c);
		
		layout.setHorizontalGroup(seqgroup); // only set seqgroup for example
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GroupLayoutSimpleDemo();
            }
        }); // Erzeugt einen neuen Thread, der eine Instanz von JFrame erzeugt
	}
}