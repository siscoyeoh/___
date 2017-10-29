package yl.swing.style;

import java.awt.*;
import javax.swing.*;

import org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;

import java.awt.event.*;

public class Select {
	private JFrame jf;
	private JButton jb1, jb2;
	private JLabel lab;
	private JPanel jp;
	MyThread Mt = null;

	public Select() {
		jf = new JFrame("Ëæ»ú³éºÅÆ÷ v1.0");
		jb1 = new JButton("start");
		jb2 = new JButton("stop");
		lab = new JLabel();
		jp = new JPanel();
		init();
		show();
		eventHandle();
	}

	public void init() {
		jp.add(jb1);
		jp.add(jb2);
		jf.add(jp, BorderLayout.SOUTH);
		jf.add(lab);
		Font font = new Font("Dialog", Font.BOLD, 100);
		lab.setFont(font);
		lab.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void show() {
		jf.setSize(410, 400);
		jf.setLocation(300, 200);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void eventHandle() {
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jb1.setEnabled(false);
				Mt = new MyThread(lab);
				Mt.start();
			}
		});

		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mt.stopThread();
				jb1.setEnabled(true);
			}
		});
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceAutumnLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel());
UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceBusinessLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceChallengerDeepLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceCremeCoffeeLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceCremeLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceDustCoffeeLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceDustLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceEmeraldDuskLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceMagmaLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceMistSilverLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceModerateLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceNebulaLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceRavenLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceSaharaLookAndFeel());
//UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceTwilightLookAndFeel());
				} catch (Exception e) {
				}
				new Select();
			}
		});

	}

	/*
	 * public static void main(String[] args) throws
	 * UnsupportedLookAndFeelException { UIManager.setLookAndFeel(new
	 * org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel());
	 * //UIManager.setLookAndFeel(new McWinLookAndFeel()); new Select(); }
	 */
}

class MyThread extends Thread {
	int num;
	boolean flag = true;
	JLabel label = null;

	public MyThread(JLabel lab) {
		label = lab;
		num = 10;
	}

	public void run() {
		while (flag) {
			int i = ((int) (Math.random() * 10000)) % num + 1;
			// System.out.println(i);
			label.setText(i + "");
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void stopThread() {
		flag = false;
	}
}