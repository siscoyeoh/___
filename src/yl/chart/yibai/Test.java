package yl.chart.yibai;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test {

	public static void main(String[] args) {
		JFrame frame = new JFrame("00112233");
		frame.setSize(900, 600);
		JPanel panel = new HistoryTendencyCurve3("").getJPanel();
		frame.add(panel);
		frame.setVisible(true);
	}

}
