package yl.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class RingChart {
    public RingChart() {
        this.createChart();
    }

    @SuppressWarnings("deprecation")
    public void createChart() {
        JFreeChart chart = ChartFactory.createRingChart(null, this.createDataset(), true, false, false);
        chart.getLegend().setVisible(false);
        // 环形图
        RingPlot ringplot = (RingPlot) chart.getPlot();
        ringplot.setOutlineVisible(false);
        //{2}表示显示百分比
        ringplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}"));
        ringplot.setBackgroundPaint(new Color(253,253,253));
        ringplot.setOutlineVisible(false);
        //设置标签样式
        ringplot.setLabelFont(new Font("宋体", Font.BOLD, 15));
        ringplot.setSimpleLabels(true);
        ringplot.setLabelLinkPaint(Color.WHITE);
        ringplot.setLabelOutlinePaint(Color.WHITE);
        ringplot.setLabelLinksVisible(false);
        ringplot.setLabelShadowPaint(null);
        ringplot.setLabelOutlinePaint(new Color(0,true));
        ringplot.setLabelBackgroundPaint(new Color(0,true));
        ringplot.setLabelPaint(Color.WHITE);
        
        ringplot.setSectionOutlinePaint(Color.WHITE);
        ringplot.setSeparatorsVisible(true);
        ringplot.setSeparatorPaint(Color.WHITE);
        ringplot.setShadowPaint(new Color(253,253,253));
        ringplot.setSectionDepth(0.58);
        ringplot.setStartAngle(90);
        ringplot.setDrawingSupplier(new DefaultDrawingSupplier(
                new Paint[] { 
                        new Color(134, 212, 222), 
                        new Color(174, 145, 195), 
                        new Color(255, 162, 195),
                        new Color(249, 163, 86),
                        new Color(119, 173, 195)
                        },
                DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE, 
                DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE, 
                DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));

        FileOutputStream fos_jpg = null;
        try {
            fos_jpg = new FileOutputStream("d:\\ring.png");
            ChartUtilities.writeChartAsPNG(fos_jpg,chart, 235, 230, null);
            System.out.println("成功");
            
            //以下由于jfreechart设置背景色后，背景会有留白，直接将目标图片截取
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ChartUtilities.writeChartAsPNG(baos,chart, 240, 230, null);
            
            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));
            BufferedImage sub = bi.getSubimage(5, 0, 230, 225);
            ImageIO.write(sub, "png", new File("d:\\ring_sub.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos_jpg.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //测试数据
    public PieDataset createDataset() {
        DefaultPieDataset dataSet = new DefaultPieDataset();
        int i, j;
        String[] name = { "私募股权","房地产","基金","现金宝宝","股票" };
        int[] value = { 20, 20, 20, 30, 10 };
        for (i = 0, j = 0; i < name.length && j < value.length; i++, j++) {
            dataSet.setValue(name[i], value[j]);
        }
        return dataSet;
    }

    public static void main(String[] args) {

        new RingChart();
    }
}
