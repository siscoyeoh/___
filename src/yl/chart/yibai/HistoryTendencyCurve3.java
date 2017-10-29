package yl.chart.yibai;

import java.awt.Font;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class HistoryTendencyCurve3 extends JFrame {
	private static final long serialVersionUID = 7607528456862066357L;
	final ChartPanel chartPanel;
	public JPanel getJPanel() {
		return chartPanel;
	}

	public HistoryTendencyCurve3(final String title) {
		super(title);
		final XYDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
		chartPanel.setMouseZoomable(true, false);
		setContentPane(chartPanel);
	}

	private XYDataset createDataset() {
		final TimeSeries series0 = new TimeSeries("series0");
//		long four_days_ago = new Date().getTime() - 4 * 24 * 60 * 60 * 1000L; 
//		Minute current = new Minute(new Date(four_days_ago));
		Date now = new Date();
		Minute current0 = new Minute(now);
		double value0 = 100.0;
		for (int i = 0; i < 4000; i++) {
			try {
				value0 = value0 + Math.random() - 0.5;
				series0.add(current0, new Double(value0));
//				current = (Minute) current.next();
				current0 = (Minute) current0.previous();
			} catch (SeriesException e) {
				System.err.println("Error adding to series");
			}
		}

		final TimeSeries series = new TimeSeries("series1");
//		long four_days_ago = new Date().getTime() - 4 * 24 * 60 * 60 * 1000L; 
//		Minute current = new Minute(new Date(four_days_ago));
		Minute current = new Minute(now);
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
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series0);
		dataset.addSeries(series);
		
		return dataset;
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
				true, 
				false, 
				false);
	}

	public static void main(final String[] args) {
		final String title = "History Tendency Curve";
		final HistoryTendencyCurve3 demo = new HistoryTendencyCurve3(title);
		demo.pack();
//		RefineryUtilities.positionFrameRandomly(demo);
		demo.setVisible(true);
	}
}
