package yl.chart.yibai;

import java.awt.Font;
import java.util.Date;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class HistoryTendencyCurve2 extends JFrame {
	private static final long serialVersionUID = 7607528456862066357L;

	public HistoryTendencyCurve2(final String title) {
		super(title);
		final XYDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
		chartPanel.setMouseZoomable(true, false);
		setContentPane(chartPanel);
	}

	private XYDataset createDataset() {
		final TimeSeries series = new TimeSeries("History Tendency Curve");
//		long four_days_ago = new Date().getTime() - 4 * 24 * 60 * 60 * 1000L; 
//		Minute current = new Minute(new Date(four_days_ago));
		Minute current = new Minute(new Date());
		double value = 100.0;
		for (int i = 0; i < 4000; i++) {
			try {
				value = value + Math.random() - 0.5;
				series.add(current, new Double(value));
//				current = (Minute) current.next();
				current = (Minute) current.previous();
			} catch (SeriesException e) {
				System.err.println("Error adding to series");
			}
		}

		return new TimeSeriesCollection(series);
	}

	private JFreeChart createChart(final XYDataset dataset) {
		// 创建主题样式
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
		// 设置图例的字体
		standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
		// 设置轴向的字体
		standardChartTheme.setLargeFont(new Font("宋书", Font.BOLD, 18));
		// 应用主题样式
		ChartFactory.setChartTheme(standardChartTheme);
		return ChartFactory.createTimeSeriesChart(
				"磁盘使用率", 
				"",
				"使用率(%)", 
				dataset, 
				false, 
				false, 
				false);
	}

	public static void main(final String[] args) {
		final String title = "History Tendency Curve";
		final HistoryTendencyCurve2 demo = new HistoryTendencyCurve2(title);
		demo.pack();
//		RefineryUtilities.positionFrameRandomly(demo);
		demo.setVisible(true);
	}
}
