package yl.swing.layout;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
 
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
 
public class Log extends JFrame {
    public static void main(String[] args) {
        Log log = new Log();
    }
    private JButton btLog;
    private JTextField tfUser;
    private JPasswordField tfPwd;
    private JCheckBox pwdKeep;
    private JComboBox adminType;
 
    public Log() {
        super("�̶��ʲ�����ϵͳ");
        super.setSize(380, 292);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centered(this);
        btLog = new JButton("��     ¼");
        btLog.setBounds(new Rectangle(93, 220, 180, 30));//�����ֱ�������x��y������
        this.setLayout(null);//���ò��ֹ�����Ϊ��
        this.add(btLog);
        tfUser = new JTextField();
        tfUser.setBounds(new Rectangle(73, 115, 220, 25));
        this.add(tfUser);
        tfPwd = new JPasswordField();
        tfPwd.setBounds(new Rectangle(73, 150, 220, 25));
        this.add(tfPwd);
        pwdKeep = new JCheckBox("��ס����");
        pwdKeep.setBounds(new Rectangle(68, 185, 110, 25));
        this.add(pwdKeep);
        adminType = new JComboBox(new String[] { "��ְͨԱ", "����Ա", "�߼�����Ա" });
        adminType.setBounds(new Rectangle(183, 185, 100, 25));
        this.add(adminType);
 
    }
//���־��з���
    public void centered(Container container) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int w = container.getWidth();
        int h = container.getHeight();
        container.setBounds((screenSize.width - w) / 2,
                (screenSize.height - h) / 2, w, h);
    }
}
