package yl.swing.img;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Graphics;

public class DrawImageFrame extends Frame {

	private static final long serialVersionUID = -3726398972307026414L;
	
	private Image img;
	private static DrawImageFrame mainFrame;

	public static void main(String[] args) {
		mainFrame = new DrawImageFrame();
		mainFrame.init();
	}

	private void init() {
		mainFrame.setTitle("MM的大头照");
		mainFrame.setBounds(0, 0, 390, 600);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		mainFrame.setBackground(Color.WHITE);
		try {
			img = getToolkit().createImage("D:\\360安全浏览器下载\\timg.jpg");
		} catch (Exception e) {
			System.out.println("File Not Found");
		}
//		mainFrame.addWindowListener(new WinListener());
		
	}

	@Override
	public void paint(Graphics g) {
		System.out.println("paint");
		g.drawImage(img, 20, 50, this);
	}

}
