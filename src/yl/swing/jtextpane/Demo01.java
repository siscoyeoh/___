package yl.swing.jtextpane;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// 代码参见: 
// 
// https://github.com/lankeeper/TelnetClientExample
public class Demo01 {

	
	public static void main(String[]args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		JPanel telPanel = new Demo01().new TelnetPanel();
		frame.add(telPanel);
		frame.setVisible(true);
	}
	
	private void sendCommand(String cmd) {
		telInfoTextArea.append("\n" + cmd);
	}

	JPanel jPanel4;
	JTextField cmdField;
	JButton sendCMDBtn;
	static JTextArea telInfoTextArea;
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
			telInfoTextArea = new JTextArea(5, 5);
			telInfoTextArea.setBackground(Color.getHSBColor(1, 23, 34));
			telInfoTextArea.setEditable(false);
			telInfoTextArea.setLineWrap(true);
			telInfoTextArea.setWrapStyleWord(false);
			telInfoTextArea.setAutoscrolls(true);
			scrollPane = new JScrollPane(telInfoTextArea);
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

//					JScrollBar bar = scrollPane.getVerticalScrollBar();
//					System.out.println("bar.getMaximum():" + bar.getMaximum());
//					int val = Integer.parseInt(cmdField.getText());
//					bar.setValue(val);
					
				}
			});
			
			telInfoTextArea.getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					System.out.println("removeUpdate");
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					System.out.println("insertUpdate");
					telInfoTextArea.setCaretPosition(telInfoTextArea.getDocument().getLength());
//					JScrollBar bar = scrollPane.getVerticalScrollBar();
//					System.out.println("bar.getMaximum():" + bar.getMaximum());
//					bar.setValue(bar.getMaximum());
//					bar.setValue(10000);
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					System.out.println("changedUpdate");
//					JScrollBar bar = scrollPane.getVerticalScrollBar();
//					System.out.println("bar.getMaximum():" + bar.getMaximum());
//					bar.setValue(bar.getMaximum());
					
//					telInfoTextArea.setSelectionStart(telInfoTextArea.getText().length());
					
//					DefaultCaret caret = (DefaultCaret) telInfoTextArea.getCaret();
//					caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
				}
			});
			
		}
	}

//	@Override
//	public void isSelected(boolean isSelected) {
//		this.isSelected = isSelected;
//		System.out.println(this.getClass().getName() + "被选中:" + isSelected);
//	}

	
}

