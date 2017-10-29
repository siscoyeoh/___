package yl.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

public class PieChart {
	public static void main(String[] args) throws IOException{
        //������ݼ�
        DefaultPieDataset df=getDataSet2();
        //����3D��ͼ  
        JFreeChart chart = ChartFactory.createPieChart3D("ĳѱ������������",df, true, true, false);
        //����ͼƬ����ɫ  
        chart.setBackgroundPaint(Color.white);
        //ȡ��3D��ͼ����  
        PiePlot3D plot = (PiePlot3D) chart.getPlot();  
        //ָ����ʾ�ı�ͼ��Բ��(false)����Բ��(true)  
        plot.setCircular(false); 
        //����ͼ�α߿���ɫ  
        //plot.setBaseSectionOutlinePaint(Color.red);   
        // ͼ�α߿��ϸ  
        plot.setBaseSectionOutlineStroke(new BasicStroke(0.7f));
        //������ת�Ƕ�  
        plot.setStartAngle(290);
        //������ת����  
        plot.setDirection(Rotation.CLOCKWISE);  
        //����͸����  
        plot.setForegroundAlpha(0.9f);
        //���ñ������� 
        chart.getTitle().setFont(new Font("����",Font.BOLD,25));
        chart.getLegend().setItemFont(new Font("����",Font.BOLD,20));
        plot.setLabelFont((new Font("����", Font.PLAIN, 20))); 



        FileOutputStream fos_jpg = null; 
        try { 
            fos_jpg = new FileOutputStream("D:\\pig.jpg"); 
            ChartUtilities.writeChartAsJPEG(fos_jpg, 1, chart, 400, 300);
            //ChartUtilities.writeChartAsJPEG(fos_jpg,100,chart,400,300,null); 
        } finally { 
            try { 
                fos_jpg.close(); 
            } catch (Exception e) {} 
        }
    }

    private static DefaultPieDataset getDataSet2() { 
        DefaultPieDataset df=new DefaultPieDataset();
        df.setValue("ţ", 2);
        df.setValue("��", 20);
        df.setValue("��", 45);
        df.setValue("��", 10);
        df.setValue("��", 9);
        df.setValue("��", 12);

        return df;
    } 
}
