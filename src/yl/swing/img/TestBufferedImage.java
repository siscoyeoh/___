package yl.swing.img;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TestBufferedImage extends Canvas {
	private static final long serialVersionUID = -7640069567698727380L;

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		final MyCanvas mc = new MyCanvas();
		frame.add(mc);
		try {
			File file = new File("D:\\360°²È«ä¯ÀÀÆ÷ÏÂÔØ\\timg.jpg");
			BufferedImage bi = ImageIO.read(file);
			mc.setImage(bi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mc.repaint();
		//-------------
		
		JButton jButton = new JButton("test");
		
		jButton.addMouseListener(new MouseListener() {
			
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
				mc.repaint();
			}
		});
		frame.add(jButton);
		//-------------
		frame.setSize(400, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class MyCanvas extends Canvas {
	private static final long serialVersionUID = -998106910318922486L;
	private BufferedImage bi;
	private Image im;
	private int image_width;
	private int image_height;

	public void setImage(BufferedImage bi) {
		this.bi = bi;
		this.zoom();
	}

	public void paint(Graphics g) {
		g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	public void zoom() {
		image_width = bi.getWidth();
		image_height = bi.getHeight();
		im = bi.getScaledInstance(image_width, image_height, Image.SCALE_SMOOTH);
	}
}
