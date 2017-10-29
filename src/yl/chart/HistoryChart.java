package yl.chart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class HistoryChart extends JPanel {

	private static final long serialVersionUID = -5756669538226017600L;

	// constructor
	public HistoryChart() {
		// ����������ʽ
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// ���ñ�������
		standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));
		// ����ͼ��������
		standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15));
		// �������������
		standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15));
		// Ӧ��������ʽ
		ChartFactory.setChartTheme(standardChartTheme);

		// ����DataSet
		DefaultCategoryDataset DataSet = new DefaultCategoryDataset();
		DataSet.addValue(300, "number", "2017-09-01");
		DataSet.addValue(400, "number", "2017-09-02");
		DataSet.addValue(250, "number", "2017-09-03");
		DataSet.addValue(330, "number", "2017-09-04");
		DataSet.addValue(420, "number", "2017-09-05");
		// ��������ͼ
		// JFreeChart chart = ChartFactory.createBarChart3D("Catogram",
		// "Fruit", "Sale", DataSet, PlotOrientation.VERTICAL,
		// false, false, false);
		PlotOrientation po = PlotOrientation.VERTICAL;
		JFreeChart chart = ChartFactory.createLineChart("������ʷ����", "����", "����(MB)",
				DataSet, po, false, false, false);
		// ChartFactory.createLineChart
//		chart.setBackgroundPaint(Color.LIGHT_GRAY);
		// ��������ͼ��
		ChartPanel panel = new ChartPanel(chart);
		JPanel jp = new JPanel();
		jp.add(panel, BorderLayout.CENTER);
		this.add(jp);
//		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 700, 500);
		this.setVisible(true);
	}

//	public static void main(String[] args) {
//		new HistoryChart();
//	}
}
