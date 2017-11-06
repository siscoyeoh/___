package yl.chart.jfreechart.my;


import java.awt.Dimension;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class StackedBarChartDemo7 extends JFrame
{
	private static final long serialVersionUID = 8028651163854902454L;

public StackedBarChartDemo7(String paramString)
  {
    super(paramString);
    JPanel localJPanel = createStackedBarPanel();
    localJPanel.setPreferredSize(new Dimension(600, 450));
    setContentPane(localJPanel);
  }
  
  private static CategoryDataset createDataset()
  {
    DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
    localDefaultCategoryDataset.addValue(32.4D, "Series 1", "Category 1");
    localDefaultCategoryDataset.addValue(17.8D, "Series 2", "Category 1");
//    localDefaultCategoryDataset.addValue(27.7D, "Series 3", "Category 1");
    localDefaultCategoryDataset.addValue(43.2D, "Series 1", "Category 2");
    localDefaultCategoryDataset.addValue(15.6D, "Series 2", "Category 2");
//    localDefaultCategoryDataset.addValue(18.3D, "Series 3", "Category 2");
    localDefaultCategoryDataset.addValue(23.0D, "Series 1", "Category 3");
    localDefaultCategoryDataset.addValue(111.3D, "Series 2", "Category 3");
//    localDefaultCategoryDataset.addValue(25.5D, "Series 3", "Category 3");
    localDefaultCategoryDataset.addValue(13.0D, "Series 1", "Category 4");
    localDefaultCategoryDataset.addValue(11.8D, "Series 2", "Category 4");
//    localDefaultCategoryDataset.addValue(29.5D, "Series 3", "Category 4");
    return localDefaultCategoryDataset;
  }
  
  private static JFreeChart createChart(CategoryDataset paramCategoryDataset)
  {
    JFreeChart localJFreeChart = ChartFactory.createStackedBarChart("Stacked Bar Chart Demo 7", "Category", "Value", paramCategoryDataset, PlotOrientation.VERTICAL, true, true, false);
    CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
    NumberAxis localNumberAxis = (NumberAxis)localCategoryPlot.getRangeAxis();
    localNumberAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());
    StackedBarRenderer localStackedBarRenderer = (StackedBarRenderer)localCategoryPlot.getRenderer();
    localStackedBarRenderer.setRenderAsPercentages(true);
    localStackedBarRenderer.setDrawBarOutline(false);
    localStackedBarRenderer.setBaseItemLabelsVisible(true);
    localStackedBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
    return localJFreeChart;
  }
  
  public static JPanel createStackedBarPanel()
  {
    JFreeChart localJFreeChart = createChart(createDataset());
    return new ChartPanel(localJFreeChart);
  }
  
  public static void main(String[] paramArrayOfString)
  {
    StackedBarChartDemo7 localStackedBarChartDemo7 = new StackedBarChartDemo7("Stacked Bar Chart Demo 7");
    localStackedBarChartDemo7.pack();
    RefineryUtilities.centerFrameOnScreen(localStackedBarChartDemo7);
    localStackedBarChartDemo7.setVisible(true);
  }
}
