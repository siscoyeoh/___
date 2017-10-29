package yl.swing;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * images�ļ��з�����Ŀ��Ŀ¼ ��./��Ϊ��ǰ��Ŀ��Ŀ��
 */
public class QuestionnaireFrame extends JFrame {

	/**
* 
*/
	private static final long serialVersionUID = 1L;
	private SystemTray tray;
	private TrayIcon trayIcon;
	
	public static void main(String[]s) {
		new QuestionnaireFrame().setVisible(true);;
	}

	public QuestionnaireFrame() {
		super();
		// �������
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setSize(800, 600);
		// ��ʼ����Ļ����
		setLocationRelativeTo(null);
		// ʹ�����X�����˳�����
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("�ʾ����ϵͳ");
		// ���ñ���ͼ��
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("/images/hgkj.ico");
		this.setIconImage(img);
		// ���ô��ڹر��¼�����
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// ������ͼ����ӵ�ϵͳ������ʵ����
				try {
					tray.add(trayIcon);
					System.out.println("�ر�");
					setVisible(false);
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
			}

		});
		if (SystemTray.isSupported()) {
			System.out.println("�� ��tray");
			tray();
		}
	}

	private void tray() {
		// ��ñ�����ϵͳ���̵�ʵ��
		tray = SystemTray.getSystemTray();
		// ��ʾ�������е�ͼ��
		ImageIcon icon = new ImageIcon("./images/title.png");
		// ����һ���Ҽ�����ʽ�˵�
		PopupMenu pop = new PopupMenu();
		MenuItem exit = new MenuItem("�رշ���");
		pop.add(exit);
		trayIcon = new TrayIcon(icon.getImage(), "�ʾ����ϵͳ\n��Ȩ���У��Ϻ���ʢ", pop);
		// ������Ҫ��û�лᵼ��ͼƬ��ʾ������
		trayIcon.setImageAutoSize(true);
		trayIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					tray.remove(trayIcon);
					setVisible(true);
					// ��ԭ��ԭ���Ĵ��ڣ���������ʾ��������
					setExtendedState(NORMAL);
				}
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tray.remove(trayIcon);
				System.exit(0);
			}
		});
	}
}
