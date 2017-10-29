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
        //获得数据集
        DefaultPieDataset df=getDataSet2();
        //创建3D饼图  
        JFreeChart chart = ChartFactory.createPieChart3D("某驯养场动物数量",df, true, true, false);
        //设置图片背景色  
        chart.setBackgroundPaint(Color.white);
        //取得3D饼图对象  
        PiePlot3D plot = (PiePlot3D) chart.getPlot();  
        //指定显示的饼图上圆形(false)还椭圆形(true)  
        plot.setCircular(false); 
        //设置图形边框颜色  
        //plot.setBaseSectionOutlinePaint(Color.red);   
        // 图形边框粗细  
        plot.setBaseSectionOutlineStroke(new BasicStroke(0.7f));
        //设置旋转角度  
        plot.setStartAngle(290);
        //设置旋转方向  
        plot.setDirection(Rotation.CLOCKWISE);  
        //设置透明度  
        plot.setForegroundAlpha(0.9f);
        //设置标题字体 
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,25));
        chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,20));
        plot.setLabelFont((new Font("宋体", Font.PLAIN, 20))); 



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
        df.setValue("牛", 2);
        df.setValue("猪", 20);
        df.setValue("羊", 45);
        df.setValue("马", 10);
        df.setValue("兔", 9);
        df.setValue("鸡", 12);

        return df;
    } 
}
