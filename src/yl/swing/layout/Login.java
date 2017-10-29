package yl.swing.layout;

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
	private JLabel jLabel1, jLabel2, jLabel3;
	private JButton jConnect, jCancel;
	private JTextField jUID;
	private JPasswordField jPwd;

	Login() {
		super("登录界面");
		jLabel1 = new JLabel("家庭收入管理系统");
		jLabel2 = new JLabel("用户名");
		jLabel3 = new JLabel("密码");
		jConnect = new JButton("连接");
		jCancel = new JButton("取消");
		jUID = new JTextField(15);
		jPwd = new JPasswordField(15);

		Box title = Box.createHorizontalBox();
		Box userName = Box.createHorizontalBox();
		Box password = Box.createHorizontalBox();
		Box submitButton = Box.createHorizontalBox();
		title.add(jLabel1);
		userName.add(jLabel2);
		userName.add(jUID);
		password.add(jLabel3);
		password.add(jPwd);
		submitButton.add(jConnect);
		submitButton.add(jCancel);
		this.setLayout(new FlowLayout());
		this.add(title);
		this.add(userName);
		this.add(password);
		this.add(submitButton);
		title.setVisible(true);
		userName.setVisible(true);
		password.setVisible(true);
		submitButton.setVisible(true);
		this.setLocation(50, 50);
		this.setSize(500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Login();
	}
}
