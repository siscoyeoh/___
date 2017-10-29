package yl.swing.jtextpane;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

// 代码参见: 
// 
// https://github.com/lankeeper/TelnetClientExample
public class Demo02 {

	
	public static void main(String[]args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		JPanel telPanel = new Demo02().new TelnetPanel();
		frame.add(telPanel);
		frame.setVisible(true);
	}
	
	private void sendCommand(String cmd) {
		try {
			cmd += "," + new Date().toString() + ";";
			SimpleAttributeSet attrset = new SimpleAttributeSet();
	        StyleConstants.setFontSize(attrset,24);
	        StyleConstants.setForeground(attrset, Color.BLUE);
			doc.insertString(doc.getLength(), cmd, attrset);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	JPanel jPanel4;
	JTextField cmdField;
	JButton sendCMDBtn;
	static JTextPane textPane;
	Document doc;
	JScrollPane scrollPane;
	
	class TelnetPanel extends JPanel {
		private static final long serialVersionUID = 2839821220405981016L;

		public TelnetPanel() {
			init();
		}

		public void init() {
			jPanel4 = new JPanel();
			cmdField = new JTextField("");
			sendCMDBtn = new JButton("发送命令");
			textPane = new JTextPane();
			textPane.setBackground(Color.getHSBColor(1, 23, 34));
			textPane.setEditable(false);
//			textPane.setLineWrap(true);
//			textPane.setWrapStyleWord(false);
			textPane.setAutoscrolls(true);
			doc = textPane.getDocument();
			scrollPane = new JScrollPane(textPane);
			scrollPane.setSize(100, 100);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			GridBagLayout layout = new GridBagLayout();
			this.setLayout(layout);
//			this.setSize(100, 200);
			this.add(jPanel4);
			this.add(cmdField);
			this.add(sendCMDBtn);
			this.add(scrollPane);
			GridBagConstraints bagConstraints = new GridBagConstraints();
			bagConstraints.fill = GridBagConstraints.BOTH;
			bagConstraints.gridwidth = 0;
			bagConstraints.weightx = 0;
			bagConstraints.weighty = 0;
			layout.setConstraints(jPanel4, bagConstraints);
			;
			bagConstraints.gridwidth = 4;
			bagConstraints.weightx = 1;
			bagConstraints.weighty = 0;
			layout.setConstraints(cmdField, bagConstraints);
			;
			bagConstraints.gridwidth = 0;
			bagConstraints.weightx = 0;
			bagConstraints.weighty = 0;
			layout.setConstraints(sendCMDBtn, bagConstraints);
			;
			;
			bagConstraints.gridwidth = 5;
			bagConstraints.weightx = 0;
			bagConstraints.weighty = 1;
			layout.setConstraints(scrollPane, bagConstraints);
			
			// 为组件添加监听
			sendCMDBtn.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {}
				
				@Override
				public void mousePressed(MouseEvent e) {}
				
				@Override
				public void mouseExited(MouseEvent e) {}
				
				@Override
				public void mouseEntered(MouseEvent e) {}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					String cmd = cmdField.getText();
					sendCommand(cmd);

					
				}
			});
			
		}
	}

	
}

