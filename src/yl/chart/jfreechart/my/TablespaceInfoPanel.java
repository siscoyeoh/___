package yl.chart.jfreechart.my;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class TablespaceInfoPanel extends JPanel {
	private static final long serialVersionUID = -7746042983521400938L;
	
	private static String usedStr = "已使用";
	private static String freeStr = "空闲";
	private static String title = "表空间"; // tablespace usage
	private static String domainAxisLabel = null; // "domainAxisLabel";
	private static String rangeAxisLabel = "使用率(%)"; // rangeAxisLabel
	
	public TablespaceInfoPanel() {
		
		CategoryDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setSize(new Dimension(200, 300));
		chartPanel.setMouseZoomable(false);
		this.add(chartPanel);
		this.setSize(200, 300);
	}
	
	private static CategoryDataset createDataset() {
		DefaultCategoryDataset tableUsageDataset = new DefaultCategoryDataset();
		tableUsageDataset.addValue(1.0D, usedStr, "aaa");
		tableUsageDataset.addValue(2.0D, freeStr, "aaa");
		tableUsageDataset.addValue(2.0D, usedStr, "bbb");
		tableUsageDataset.addValue(3.0D, freeStr, "bbb");
		tableUsageDataset.addValue(3.0D, usedStr, "ccc");
		tableUsageDataset.addValue(4.0D, freeStr, "ccc");
		tableUsageDataset.addValue(4.0D, usedStr, "ddd");
		tableUsageDataset.addValue(5.0D, freeStr, "ddd");
		tableUsageDataset.addValue(5.0D, usedStr, "eee");
		tableUsageDataset.addValue(6.0D, freeStr, "eee");
		
		return tableUsageDataset;
	}
	
	private static JFreeChart createChart(CategoryDataset dataset) {
		JFreeChart stackedBarChart = ChartFactory.createStackedBarChart(
				title, domainAxisLabel, rangeAxisLabel, dataset,
				PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot localCategoryPlot = (CategoryPlot)stackedBarChart.getPlot();
		
	    CategoryAxis localCategoryAxis = localCategoryPlot.getDomainAxis();
	    localCategoryAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
		
	    NumberAxis localNumberAxis = (NumberAxis)localCategoryPlot.getRangeAxis();
	    localNumberAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());
	    StackedBarRenderer localStackedBarRenderer = (StackedBarRenderer)localCategoryPlot.getRenderer();
	    localStackedBarRenderer.setRenderAsPercentages(true);
	    localStackedBarRenderer.setDrawBarOutline(false);
	    localStackedBarRenderer.setBaseItemLabelsVisible(true);
	    localStackedBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		return stackedBarChart;
	}
	
	public static void main(String[]args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500, 700);
		TablespaceInfoPanel p = new TablespaceInfoPanel();
		f.add(p);
		f.setVisible(true);
	}

}
