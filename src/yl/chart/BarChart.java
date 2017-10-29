package yl.chart;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart extends JFrame {
	//constructor
	 public BarChart(){
	         //构造DataSet
	         DefaultCategoryDataset DataSet = new DefaultCategoryDataset();
	         DataSet.addValue(300, "number", "apple");
	         DataSet.addValue(400, "number", "barara");
	         DataSet.addValue(250, "number", "pear");
	         DataSet.addValue(330, "number", "milk");
	         DataSet.addValue(420, "number", "cheese");
	         //创建柱形图
//	         JFreeChart chart = ChartFactory.createBarChart3D("Catogram",
//	                 "Fruit", "Sale", DataSet, PlotOrientation.VERTICAL, 
//	                 false, false, false);
	         JFreeChart chart = ChartFactory.createLineChart("Catogram",
	        		 "Fruit", "Sale", DataSet);
	        //用来放置图表
	         ChartPanel panel = new ChartPanel(chart);
	        JPanel jp = new JPanel();
	        jp.add(panel, BorderLayout.CENTER);
	        this.add(jp);
	        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        this.setBounds(100, 100, 700, 500);
	        this.setVisible(true);
	 }
	 public static void main(String [] args){
	      new BarChart();
	 }
}
