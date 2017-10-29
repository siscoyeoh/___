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
		
		titleLabel = new JLabel("用户登录");
		usernameLabel = new JLabel("账号: ");
		passwordLabel = new JLabel("密码: ");
		usernameTextField = new JTextField();
		passwordField = new JPasswordField();
		radioButton1 = new JRadioButton("记住密码");
		radioButton2 = new JRadioButton("自动登录");
		loginButton = new JButton("登录");
		// 为指定的Container创建GroupLayout
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		// 创建GroupLayout的水平连续组, 越先加入的ParrallelGroup优先级别越高
		GroupLayout.SequentialGroup horizontalGroup = layout.createSequentialGroup();
		horizontalGroup.addGap(5); // 添加间隔
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
		// 设置水平组
		layout.setHorizontalGroup(horizontalGroup);
		// 创建GroupLayout的垂直连续组,...
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
		// 设置垂直组
		layout.setVerticalGroup(verticalGroup);
	}
	
	public static void main(String[]args) {
		new MyFrame2();
	}

}
