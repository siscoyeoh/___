package yl.chart.exam01;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.Layer;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Log;

// http://developer.51cto.com/art/201112/308882.htm
public class TestMain {

	public static void main(String[] args) {
//		TimeSeriesCollection collection = createTimeSeries(list, dayOrweekOrmonth, log, shou, shu);
//		JFreeChart chart = createChartPress(xydataset, weekOrmonth, title, y, index, week, year, searchby, month, nodatamess, list, log, bp_shou, bp_shuzhang);
	}
	
	@SuppressWarnings("unused")
	private static JFreeChart createChartPress(XYDataset xydataset,  
            int weekOrmonth, String title, String y, String index, String week,  
            String year, int searchby, String month, String nodatamess,  
            List list, Log log, String bp_shou, String bp_shuzhang) {  
 
        // �п����û��ں���İ汾�й������벻������ֵ������Ϊ�˱�֤ͼƬ��ͼ�������������ȼ���  
        // �û�Ѫѹֵ�����ֵ��  
 
 
        double maxpress = 0;  
        double addmax = 50;  
        double min = 40;  
 
        if (list != null && list.size() > 0) {  
            Iterator<PressureBean> it = list.iterator();  
            while (it.hasNext()) {  
                PressureBean pres = it.next();  
                double sys = pres.getSyspress();  
                double dia = pres.getDiapress();  
 
                if (maxpress < sys) {  
                    maxpress = sys;  
 
                }  
 
                if (maxpress < dia)  
                    maxpress = dia;  
 
                if (min > sys) {  
                    min = sys;  
                }  
 
                if (min > dia)  
                    min = dia;  
 
            }  
 
            maxpress += addmax;  
            min -= 10;  
 
 
            log.info("high press value is =" + maxpress);  
 
        }  
          
        if (xydataset != null) {  
            int counts = xydataset.getItemCount(0);  
            if (counts == 0) {  
                xydataset = null;  
            }  
        }  
 
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title, "",  
                y, xydataset, true, true, false);  
        jfreechart.setBackgroundPaint(Color.white);  
          
 
        // ���ñ������ɫ  
        TextTitle text = new TextTitle(title);  
        text.setPaint(new Color(102, 102, 102));  
        jfreechart.setTitle(text);  
        XYPlot xyplot = jfreechart.getXYPlot();  
        xyplot.setBackgroundPaint(new Color(255, 253, 246));  
        xyplot.setOutlineStroke(new BasicStroke(1.5f)); // �߿��ϸ  
        ValueAxis vaxis = xyplot.getDomainAxis();  
        vaxis.setAxisLineStroke(new BasicStroke(1.5f)); // �������ϸ  
        vaxis.setAxisLinePaint(new Color(215, 215, 215)); // ��������ɫ  
        xyplot.setOutlineStroke(new BasicStroke(1.5f)); // �߿��ϸ  
        vaxis.setLabelPaint(new Color(10, 10, 10)); // �����������ɫ  
        vaxis.setTickLabelPaint(new Color(102, 102, 102)); // ��������ֵ��ɫ  
        vaxis.setLowerMargin(0.06d);// �������£��󣩱߾�  
        vaxis.setUpperMargin(0.14d);// �������£��ң��߾�,��ֹ���ߵ�һ�����ݿ����������ᡣ  
          
        //X��Ϊ���ڸ�ʽ��������ר�ŵĴ������ڵ��࣬  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();  
        if (weekOrmonth == 0) {//����Ϊ�̶ȣ�ʱ���ʽΪyyyy-MM-dd,��2008-02-06  
            dateaxis.setTickUnit(new DateTickUnit(DateTickUnit.DAY, 1, format));  
        } else if (weekOrmonth == 1) {//����Ϊ�̶ȣ�ʱ����ʾΪ 2009���4�ܣ���������SimpleDateFormat���÷���  
            //����Ϊ��������棬Ӣ�İ�ͼ���棬���˹��ʻ���������Щ�ɱ����Դ��������Դ���棬ע��һ�£������y��M��w��SimpleDateFormat�Ĺؼ��֣�  
            //��Ӣ�ı�ʾ09���3�ܾ���09W3����ô�������W��Ҫ�á�����������  
            format = new SimpleDateFormat("yyyy" + year + index + "w" + week);  
            dateaxis.setTickUnit(new DateTickUnit(DateTickUnit.DAY, 7, format));  
        } else if (weekOrmonth == 2) {//����Ϊ�̶ȣ�ʱ����ʾΪ09-02 ��09��2�£�  
            format = new SimpleDateFormat("yy-MM");  
            dateaxis  
                    .setTickUnit(new DateTickUnit(DateTickUnit.MONTH, 1, format));  
 
        }  
        dateaxis.setVerticalTickLabels(false); // ��Ϊtrue��ʾ��������ת����ֱ��  
        if (searchby == 6 || searchby == 3) {  
            dateaxis.setAutoTickUnitSelection(true); // ���ں����ǩ���࣬��������Ϊ�Զ���ʽ ��  
            dateaxis.setDateFormatOverride(format);  
        }  
        dateaxis.setTickMarkPosition(DateTickMarkPosition.START);  
 
        ValueAxis valueAxis = xyplot.getRangeAxis();  
        valueAxis.setUpperBound(maxpress);  
        valueAxis.setAutoRangeMinimumSize(1);  
        valueAxis.setLowerBound(min);  
        valueAxis.setAutoRange(false);  
 
        valueAxis.setAxisLineStroke(new BasicStroke(1.5f)); // �������ϸ  
        valueAxis.setAxisLinePaint(new Color(215, 215, 215)); // ��������ɫ  
        valueAxis.setLabelPaint(new Color(10, 10, 10)); // �����������ɫ  
        valueAxis.setTickLabelPaint(new Color(102, 102, 102)); // ��������ֵ��ɫ  
          
        xyplot.setRangeGridlinesVisible(true);  
        xyplot.setDomainGridlinesVisible(true);  
        xyplot.setRangeGridlinePaint(Color.LIGHT_GRAY);  
        xyplot.setDomainGridlinePaint(Color.LIGHT_GRAY);  
        xyplot.setBackgroundPaint(new Color(255, 253, 246));  
        xyplot.setNoDataMessage(nodatamess);//û������ʱ��ʾ������˵����  
        xyplot.setNoDataMessageFont(new Font("", Font.BOLD, 14));//����Ĵ�С�����塣  
        xyplot.setNoDataMessagePaint(new Color(87, 149, 117));//������ɫ  
        xyplot.setAxisOffset(new RectangleInsets(0d, 0d, 0d, 5d)); //  
 
        // add range marker(����ѹ������marker,��Χ�Ǵ�62��81)  
 
        double lowpress = 62;  
        double uperpress = 81;  
        IntervalMarker intermarker = new IntervalMarker(lowpress, uperpress);  
        intermarker.setPaint(Color.decode("#66FFCC"));// ���ɫ  
          
        intermarker.setLabelFont(new Font("SansSerif", 41, 14));  
        intermarker.setLabelPaint(Color.RED);  
        intermarker.setLabel(bp_shuzhang);  
 
        if (xydataset != null) {  
            xyplot.addRangeMarker(intermarker, Layer.BACKGROUND);  
        }  
    //(����ѹ������marker����Χ�Ǵ�102��120)  
        double lowpress1 = 102;  
        double uperpress1 = 120;  
        IntervalMarker inter = new IntervalMarker(lowpress1, uperpress1);  
        inter.setLabelOffsetType(LengthAdjustmentType.EXPAND);  
        inter.setPaint(Color.decode("#66FFCC"));// ���ɫ  
 
 
        inter.setLabelFont(new Font("SansSerif", 41, 14));  
        inter.setLabelPaint(Color.RED);  
        inter.setLabel(bp_shou);  
          
        if (xydataset != null) {  
            xyplot.addRangeMarker(inter, Layer.BACKGROUND); // ����Layer.BACKGROUND����maker�����������档  
        }  
 
        XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot  
                .getRenderer();  
        //��һ�����ߵ���ɫ  
        xylineandshaperenderer.setBaseItemLabelsVisible(true);  
        xylineandshaperenderer.setSeriesFillPaint(0, new Color(127, 128, 0));  
        xylineandshaperenderer.setSeriesPaint(0, new Color(127, 128, 0));  
 
        xylineandshaperenderer.setSeriesShapesVisible(0, true);  
        xylineandshaperenderer.setSeriesShapesVisible(1, true);  
 
        //�ڶ������ߵ���ɫ  
        xylineandshaperenderer.setSeriesFillPaint(1, new Color(254, 103, 0));  
        xylineandshaperenderer.setSeriesPaint(1, new Color(254, 103, 0));  
        xylineandshaperenderer.setSeriesShapesVisible(1, true);  
        xylineandshaperenderer.setSeriesVisible(2, false);//  
        xylineandshaperenderer.setSeriesVisible(3, false);//����ʾ�������  
 
        //���ߵĴ�ϸ��  
        StandardXYToolTipGenerator xytool = new StandardXYToolTipGenerator();  
        xylineandshaperenderer.setToolTipGenerator(xytool);  
        xylineandshaperenderer.setStroke(new BasicStroke(1.5f));  
 
        // ��ʾ�ڵ��ֵ  
        xylineandshaperenderer.setBaseItemLabelsVisible(true);  
        xylineandshaperenderer  
                .setBasePositiveItemLabelPosition(new ItemLabelPosition(  
                        ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));  
        xylineandshaperenderer  
                .setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());  
        xylineandshaperenderer.setBaseItemLabelPaint(new Color(102, 102, 102));// ��ʾ�۵���ֵ�������ɫ  
 
        return jfreechart;  
    } 
	
	public static TimeSeriesCollection createTimeSeries(  
            List<PressureBean> list, int dayOrweekOrmonth, Log log, String shou,String shu  
            ) {  
 
        TimeSeriesCollection timesers = new TimeSeriesCollection();  
 
        int mon = 1;  
        int day = 1;  
        int ye = 2000;  
        int week = 1;  
 
        // ������ʾ  
        if (dayOrweekOrmonth == 0) {  
 
            TimeSeries timeseries = new TimeSeries(shou,  
                    org.jfree.data.time.Day.class);  
            TimeSeries timeseries1 = new TimeSeries("c1",  
                    org.jfree.data.time.Day.class);  
 
            TimeSeries timeseriedia = new TimeSeries(shu,  
                    org.jfree.data.time.Day.class);  
            TimeSeries timeseriedia1 = new TimeSeries("d1",  
                    org.jfree.data.time.Day.class);  
 
            Iterator<PressureBean> it = list.iterator();  
            while (it.hasNext()) {  
                PressureBean pres = it.next();  
                String date = pres.getBpDate();  
 
                ye = Integer.parseInt(date.substring(0, 4));  
                mon = Integer.parseInt(date.substring(5, 7));  
                day = Integer.parseInt(date.substring(8, date.length()));  
                Day days = new Day(day, mon, ye);  
 
                double sys = pres.getSyspress();  
                double dia = pres.getDiapress();  
                if (sys != -1 && sys > 0) {  
                    timeseries.add(days, sys);  
                } else {  
                    timeseries1.add(days, null);  
                }  
                if (sys != -1 && sys > 0) {  
                    timeseriedia.add(days, dia);  
                } else {  
                    timeseriedia1.add(days, null);  
                }  
 
            }  
 
            timesers.addSeries(timeseries);  
            timesers.addSeries(timeseriedia);  
            timesers.addSeries(timeseries1);  
            timesers.addSeries(timeseriedia1);  
 
        } else if (dayOrweekOrmonth == 1) {//������ʾ  
            TimeSeries timeseries = new TimeSeries(shou,  
                    org.jfree.data.time.Week.class);  
            TimeSeries timeseries1 = new TimeSeries("c1",  
                    org.jfree.data.time.Week.class);  
 
            TimeSeries timeseriedia = new TimeSeries(shu,  
                    org.jfree.data.time.Week.class);  
            TimeSeries timeseriedia1 = new TimeSeries("d1",  
                    org.jfree.data.time.Week.class);  
 
            Iterator<PressureBean> it = list.iterator();  
            while (it.hasNext()) {  
                PressureBean pres = it.next();  
                String date = pres.getBpDate();  
 
                String[] spls = date.split("-");  
                if (spls.length == 2) {  
                    ye = Integer.parseInt(spls[0]);  
                    mon = Integer.parseInt(spls[1]);  
                } else {  
                    log.error("the date of weeks is wrong");  
                }  
 
                Week days = new Week(mon, ye);  
                double sys = pres.getSyspress();  
                double dia = pres.getDiapress();  
 
                if (sys != -1 && sys > 0) {  
                    timeseries.add(days, sys);  
                } else {  
                    timeseries1.add(days, null);  
                }  
                if (sys != -1 && sys > 0) {  
                    timeseriedia.add(days, dia);  
                } else {  
                    timeseriedia1.add(days, null);  
                }  
 
            }  
 
            timesers.addSeries(timeseries);  
            timesers.addSeries(timeseriedia);  
            timesers.addSeries(timeseries1);  
              
            timesers.addSeries(timeseriedia1);  
 
        } else {//������ʾ  
            TimeSeries timeseries = new TimeSeries(shou,  
                    org.jfree.data.time.Month.class);  
            TimeSeries timeseries1 = new TimeSeries("c1",  
                    org.jfree.data.time.Month.class);  
 
            TimeSeries timeseriedia = new TimeSeries(shu,  
                    org.jfree.data.time.Month.class);  
            TimeSeries timeseriedia1 = new TimeSeries("s",  
                    org.jfree.data.time.Month.class);  
 
            Iterator<PressureBean> it = list.iterator();  
            while (it.hasNext()) {  
                PressureBean pres = it.next();  
                String date = pres.getBpDate();  
 
                String[] spls = date.split("-");  
                if (spls.length == 2) {  
                    ye = Integer.parseInt(spls[0]);  
                    mon = Integer.parseInt(spls[1]);  
                } else {  
                    log.error("the date of weeks is wrong");  
                }  
 
                Month days = new Month(mon, ye);  
 
                double sys = pres.getSyspress();  
                double dia = pres.getDiapress();  
 
                if (sys != -1 && sys > 0) {  
                    timeseries.add(days, sys);  
                } else {  
                    timeseries1.add(days, null);  
                }  
                if (sys != -1 && sys > 0) {  
                    timeseriedia.add(days, dia);  
                } else {  
                    timeseriedia1.add(days, null);  
                }  
 
            }  
            timesers.addSeries(timeseries);  
            timesers.addSeries(timeseriedia);  
            timesers.addSeries(timeseries1);  
              
            timesers.addSeries(timeseriedia1);  
 
        }  
 
        return timesers;  
    } 

}
