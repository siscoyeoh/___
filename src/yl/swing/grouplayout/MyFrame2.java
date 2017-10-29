package yl.swing.grouplayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

public class MyFrame2 extends JFrame {

	private static final long serialVersionUID = 4754875114434954242L;
	
	JLabel titleLabel;
	JLabel usernameLabel;
	JLabel passwordLabel;
	JTextField usernameTextField;
	JPasswordField passwordField;
	JRadioButton radioButton1;
	JRadioButton radioButton2;
	JButton loginButton;
	JButton button2;
	
	public MyFrame2() {
		this.setVisible(true);
		this.setSize(255, 220);
		this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		titleLabel = new JLabel("�û���¼");
		usernameLabel = new JLabel("�˺�: ");
		passwordLabel = new JLabel("����: ");
		usernameTextField = new JTextField();
		passwordField = new JPasswordField();
		radioButton1 = new JRadioButton("��ס����");
		radioButton2 = new JRadioButton("�Զ���¼");
		loginButton = new JButton("��¼");
		// Ϊָ����Container����GroupLayout
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		// ����GroupLayout��ˮƽ������, Խ�ȼ����ParrallelGroup���ȼ���Խ��
		GroupLayout.SequentialGroup horizontalGroup = layout.createSequentialGroup();
		horizontalGroup.addGap(5); // ��Ӽ��
		horizontalGroup.addGroup(layout.createParallelGroup()
				.addComponent(usernameLabel)
				.addComponent(passwordLabel));
		horizontalGroup.addGap(5);
		horizontalGroup.addGroup(layout.createParallelGroup()
				.addComponent(titleLabel)
				.addComponent(usernameTextField)
				.addComponent(radioButton1)
				.addComponent(radioButton2)
				.addComponent(loginButton)
				.addComponent(passwordField));
		horizontalGroup.addGap(5);
		// ����ˮƽ��
		layout.setHorizontalGroup(horizontalGroup);
		// ����GroupLayout�Ĵ�ֱ������,...
		GroupLayout.SequentialGroup verticalGroup = layout.createSequentialGroup();
		verticalGroup.addGap(10);
		verticalGroup.addGroup(layout.createParallelGroup()
				.addComponent(titleLabel));
		verticalGroup.addGap(10);
		verticalGroup.addGroup(layout.createParallelGroup()
				.addComponent(usernameLabel)
				.addComponent(usernameTextField));
		verticalGroup.addGap(5);
		verticalGroup.addGroup(layout.createParallelGroup()
				.addComponent(passwordLabel)
				.addComponent(passwordField));
		verticalGroup.addGroup(layout.createParallelGroup()
				.addComponent(radioButton1));
		verticalGroup.addGroup(layout.createParallelGroup()
				.addComponent(radioButton2));
		verticalGroup.addGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addComponent(loginButton));
		// ���ô�ֱ��
		layout.setVerticalGroup(verticalGroup);
	}
	
	public static void main(String[]args) {
		new MyFrame2();
	}

}
