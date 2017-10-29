package yl.telnet.ui3;


import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;

// 
// http://fatalove.iteye.com/blog/815745
public class TelnetPanel  extends JPanel {
	private static final long serialVersionUID = -6383361834945569313L;

	public static void main(String[]s) {
		JFrame frame = new JFrame();
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		MyTelnetClient telnet = new MyTelnetClient();
//		frame.add(telnet);
////		Thread th = new Thread(telnet);
////		th.start();
//		frame.setVisible(true);
//		String host = "192.168.8.115";
//		host = "192.168.8.1";
//		telnet.connect(host, 23);

		String host = "192.168.8.115";
//		host = "192.168.8.1";
		MyTelnetClient telnet = new MyTelnetClient();
		telnet.connect(host, 23);
		Thread thread = new Thread(telnet);
		thread.start();
		
	}
	
}

class MyTelnetClient implements Runnable {

	private static final long serialVersionUID = 3617084954608716967L;
	// ����
	boolean debug = false;// debug���
	String host;// Զ������
	int port;// �˿�
	Socket socket;// ����
	InputStream in;// ������
	OutputStream out;// �����
	Thread threadReadIn;// ���������߳�
//	Font font;// ��ǰ����
//	Image backImg;// ����ͼƬ
//	int x, y;// ������
//	int chw, chh;// �ַ���С
//	int chd;// �ַ�ƫ��
//	int width, height;// Applet��С
	int w, h;// Applet��С (in chars)
	char ch[][];// ��ʾ���ַ�����
//	Graphics gr, bgr;// ǰ��ͼƬ
	String term = "dumb";
	boolean echo;// ��Ӧ��

	// reshape
	// ����Telnet�ͻ��������С
//	public void reshape(int nx, int ny, int nw, int nh) {
//		if (nw != width || nh != height) {
//			width = nw;
//			height = nh;
//			// ��������
//			gr = getGraphics();
//			gr.setColor(Color.black);
//			font = new Font("Courier", Font.PLAIN, 10);
//			if (font != null)
//				gr.setFont(font);
//			FontMetrics fnm = gr.getFontMetrics();
//			chw = fnm.getMaxAdvance();
//			chh = fnm.getHeight();
//			chd = fnm.getDescent();
//			// �滮����������
//			h = nh / chh;
//			w = nw / chw;
//			ch = new char[w][h];
//			// ���챳��ͼƬ
//			backImg = createImage(width, height);
//			bgr = backImg.getGraphics();
//			bgr.setFont(font);
//			bgr.setColor(Color.black);
//			clearch();
//		}
//		super.reshape(nx, ny, nw, nh);
//	}

	// connect
	// ��������
	public void connect(String givenhost, int givenport) {
		host = givenhost;
		port = givenport;
		if (debug)
			System.out.println("Height = " + String.valueOf(h));
		if (debug)
			System.out.println("Width  = " + String.valueOf(w));
		// ������ʾ
//		clearch();
		echo = true;
//		requestFocus();
		// ������
		try {
			try {
				if ((socket = new Socket(host, port)) == null) {
//					display("Failed to connect to host " + host + "\n");
					return;
				}
			} catch (UnknownHostException e) {
//				display("Host " + host + " not found\n");
				return;
			}
		} catch (IOException e) {
//			display("Failed to connect to host " + host + "\n");
			return;
		}
		try {
			in = socket.getInputStream();
			out = socket.getOutputStream();
		} catch (IOException e) {
			if (debug)
				System.out.println("Failed to get stream from socket");
			System.exit(5);
		}
//		display("Connected to " + host + "\n");
		if (debug)
			System.out.println("Connected to host");
		// ��������
		threadReadIn = new Thread(this);
		threadReadIn.start();
	}

	// disconnect
	// �ر�����
	void disconnect() {
		if (threadReadIn != null) {
//			display("\nDisconnected from " + host + "\n");
			threadReadIn.stop();
			threadReadIn = null;
			socket = null;
			in = null;
			out = null;
		}
	}

	// clearch
	// ����ַ��������������α�
//	void clearch() {
//		int i, j;
//		for (i = 0; i < w; i++)
//			for (j = 0; j < h; j++)
//				ch[i][j] = ' ';
//		x = y = 0;
//		bgr.setColor(Color.white);
//		bgr.fillRect(0, 0, width, height);
//		paint(gr);
//	}

	// keyDown
	// �����ť�¼�
	public boolean keyDown(Event e, int k) {
		if (out != null) {
			int kp = e.key;
			if (debug)
				System.out.println("Pressed key " + String.valueOf(kp));
			transmitch((char) kp);
			if (echo) {
				if (debug)
					System.out.println("Echo'd " + String.valueOf(kp));
//				displaych((char) kp);
			}
		}
		return true;
	}

	// mouseDown
	// ��������ý���
//	public boolean mouseDown(Event e, int x, int y) {
//		requestFocus();
//		return true;
//	}

	// paint
	// ����ͼƬ
//	public void paint(Graphics g) {
//		gr.drawImage(backImg, 0, 0, this);
//	}

	// renderchar
	// �Ķ��ַ�
//	void renderchar(char c, int x, int y, boolean back) {
//		gr.setColor(Color.white);
//		gr.fillRect(x * chw, y * chh, chw, chh);
//		gr.setColor(Color.black);
//		gr.drawString(String.valueOf(c), x * chw, (y + 1) * chh - chd);
//		if (back) {
//			bgr.setColor(Color.white);
//			bgr.fillRect(x * chw, y * chh, chw, chh);
//			bgr.setColor(Color.black);
//			bgr.drawString(String.valueOf(c), x * chw, (y + 1) * chh - chd);
//		}
//	}

	// run
	// �Ķ�����ʾ�ַ�
	public void run() {
		while (true)
			System.out.println(readch());
	}

	// readch
	// ����һ���ն˶����ַ�
	char readch() {
		int c = 0;
		try {
			c = in.read();
		} catch (IOException e) {
			shutdown();
		}
		if (c == -1)
			shutdown();
		if (debug)
			System.out.println("Got char " + String.valueOf(c) + " = "
					+ String.valueOf((char) c));
		return (char) c;
	}

	// shutdown
	// �ж�����
	void shutdown() {
//		display("\nConnection closed\n");
		socket = null;
		in = null;
		out = null;
		Thread.currentThread().stop();
	}

	// display
	// ��Telnet������ʾ�ַ���
//	void display(String str) {
//		int i;
//		for (i = 0; i < str.length(); i++)
//			displaych(str.charAt(i));
//	}

	// displaych
	// �ڵ�ǰ���λ����ʾһ���ַ�
//	void displaych(char c) {
//		if (c == '\n') {
//			// ����
//			renderchar(ch[x][y], x, y, false);// erase cursor
//			x = 0;
//			if (y == h - 1) {
//				gr.copyArea(0, chh, w * chw, (h - 1) * chh, 0, -chh);
//				gr.setColor(Color.white);
//				gr.fillRect(0, (h - 1) * chh, width, chh);
//				bgr.copyArea(0, chh, w * chw, (h - 1) * chh, 0, -chh);
//				bgr.setColor(Color.white);
//				bgr.fillRect(0, (h - 1) * chh, width, chh);
//				int i, j;
//				for (i = 0; i < w; i++) {
//					for (j = 0; j < h - 1; j++)
//						ch[i][j] = ch[i][j + 1];
//					ch[i][h - 1] = ' ';
//				}
//			} else
//				y++;
//		} else if (c == '\t') {// Tab
//			int i;
//			for (i = 8; i > x % 8; i--)
//				displaych(' ');
//		} else if (c == (char) 8) {// Backspace
//			renderchar(ch[x][y], x, y, false);// erase cursor
//			if (x != 0)
//				x--;
//		} else if (c >= 32 && c < 127) {// Some printable character
//			renderchar(c, x, y, true);
//			ch[x][y] = c;
//			if (x == w - 1)
//				displaych('\n');
//			else
//				x++;
//		} else if (c == 255) {
//			// Telnet IAC
//			char cmd = readch();
//			char opt = readch();
//			switch (opt) {
//			case 1: // echo
//				if (cmd == 251)
//					echo = false;
//				else if (cmd == 252)
//					echo = true;
//				break;
//
//			case 3: // supress go-ahead
//				break;
//
//			case 24: // terminal type
//				if (cmd == 253) {
//					// IAC WILL terminal-type
//					transmitch((char) 255);
//					transmitch((char) 251);
//					transmitch((char) 24);
//					// IAC SB terminal-type IS <term> IAC SE
//					transmitch((char) 255);
//					transmitch((char) 250);
//					transmitch((char) 24);
//					transmitch((char) 0);
//					transmit(term);
//					transmitch((char) 255);
//					transmitch((char) 240);
//				} else if (cmd == 250) {
//					while (readch() != 240)
//						;
//				}
//				break;
//
//			default: // some other command
//				if (cmd == 253) {
//					// IAC DONT whatever
//					transmitch((char) 255);
//					transmitch((char) 252);
//					transmitch((char) opt);
//				}
//				break;
//			}
//		}
//		renderchar('_', x, y, false);// draw cursor
//	}

	void transmit(String str) {
		int i;
		for (i = 0; i < str.length(); i++)
			transmitch(str.charAt(i));
	}

	void transmitch(char c) {
		if (c == '\n')
			transmitch('\r');
		try {
			out.write((int) c);
			out.flush();
		} catch (IOException e) {
		}
		;
		if (debug)
			System.out.println("Sent char " + String.valueOf((int) c) + " = "
					+ String.valueOf(c));
	}

}