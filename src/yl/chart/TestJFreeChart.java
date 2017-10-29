package yl.chart;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class TestJFreeChart {
	public static void main(String[] args) throws IOException{ 
        CategoryDataset dataset = getDataSet2(); 
        JFreeChart chart = ChartFactory.createBarChart3D( 
                           "ˮ������ͼ", // ͼ�����
                           "ˮ��", // Ŀ¼�����ʾ��ǩ
                           "����", // ��ֵ�����ʾ��ǩ
                            dataset, // ���ݼ�
                            PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
                            true,  // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������ false)
                            false, // �Ƿ����ɹ���
                            false  // �Ƿ����� URL ����
                            ); 

        chart.setBackgroundPaint(Color.WHITE); 
        CategoryPlot plot = chart.getCategoryPlot(); 
        CategoryAxis domainAxis = plot.getDomainAxis(); 
        domainAxis.setAxisLineVisible(false); 
        NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("����", Font.PLAIN, 20));
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
        domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
        numberaxis.setLabelFont(new Font("����", Font.PLAIN, 12));
        chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));
       
        plot.setDomainAxis(domainAxis); 
        BarRenderer3D renderer = new BarRenderer3D(); 
        renderer.setBaseOutlinePaint(Color.BLACK);
        // ��ʾÿ��������ֵ�����޸ĸ���ֵ���������� 
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
        // ����ÿ��������������ƽ������֮����� 
        renderer.setItemMargin(0.1); 
        // ����������ֵ�ɼ�
        renderer.setBaseItemLabelsVisible(true);
        plot.setRenderer(renderer); 
        // ��������͸���� 
        plot.setForegroundAlpha(0.8f); 

        FileOutputStream fos_jpg = null; 
        try { 
            fos_jpg = new FileOutputStream("D:\\fruitt.jpg"); 
            ChartUtilities.writeChartAsJPEG(fos_jpg, 1, chart, 400, 300); 
        } finally { 
            try { 
                fos_jpg.close(); 
            } catch (Exception e) {} 
        } 
    }  
    /** 
    * ��ȡһ����ʾ�õ�������ݼ�����
    * @return 
    */ 
    private static CategoryDataset getDataSet2() { 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
        dataset.addValue(100, "����", "ƻ��"); 
        dataset.addValue(100, "�Ϻ�", "ƻ��"); 
        dataset.addValue(100, "����", "ƻ��"); 
        dataset.addValue(200, "����", "����"); 
        dataset.addValue(200, "�Ϻ�", "����"); 
        dataset.addValue(200, "����", "����"); 
        dataset.addValue(300, "����", "����"); 
        dataset.addValue(300, "�Ϻ�", "����"); 
        dataset.addValue(300, "����", "����"); 
        dataset.addValue(400, "����", "�㽶"); 
        dataset.addValue(400, "�Ϻ�", "�㽶"); 
        dataset.addValue(400, "����", "�㽶"); 
        dataset.addValue(500, "����", "��֦"); 
        dataset.addValue(500, "�Ϻ�", "��֦"); 
        dataset.addValue(500, "����", "��֦"); 
        return dataset; 
    } 
}
