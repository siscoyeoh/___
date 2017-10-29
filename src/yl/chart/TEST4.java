package yl.chart;

import java.awt.AWTException;

import java.awt.Color;

import java.awt.Dimension;

import java.awt.Frame;

import java.awt.Image;

import java.awt.MenuItem;

import java.awt.PopupMenu;

import java.awt.SystemTray;

import java.awt.Toolkit;

import java.awt.TrayIcon;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import java.util.Calendar;

import java.util.Random;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartPanel;

import org.jfree.chart.JFreeChart;

import org.jfree.chart.axis.NumberTickUnit;

import org.jfree.chart.axis.ValueAxis;

import org.jfree.chart.labels.ItemLabelAnchor;

import org.jfree.chart.labels.ItemLabelPosition;

import org.jfree.chart.labels.StandardXYItemLabelGenerator;

import org.jfree.chart.plot.XYPlot;

import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import org.jfree.chart.title.TextTitle;

import org.jfree.data.time.Millisecond;

import org.jfree.data.time.TimeSeries;

import org.jfree.data.time.TimeSeriesCollection;

import org.jfree.ui.TextAnchor;

public class TEST4 extends JFrame {

/**­

  * @author wanglong­

  * @description JfreeChart+ChartPanel实时曲线图­

  * @param args­

  */

private  TrayIcon trayIcon;

TimeSeries ts,ts2;

public static void main(String[] args) {

  // TODO Auto-generated method stub­

       new TEST4();

}

public TEST4()

{

  jbinit();

}

public  void jbinit()

{

  this.getContentPane().setLayout(null);

  this.setSize(600,400);

  this.setResizable(false);

  this.setLocationRelativeTo(null);

  this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//设置默认的关闭窗口的方式­

  MyTray();

  MyAction();

  MyChart();

  StartThread();

  this.show();

  

  

  

}

public void MyChart()

{

   ts=new TimeSeries("温度",Millisecond.class);

   ts2=new TimeSeries("湿度",Millisecond.class);

  TimeSeriesCollection tsc=new TimeSeriesCollection();

  tsc.addSeries(ts);

  tsc.addSeries(ts2);

  JFreeChart jfChart=ChartFactory.createTimeSeriesChart("实时曲线图","时间", "数值", tsc, true, true, true);

  TextTitle subTitle=new TextTitle(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+"年份");

  jfChart.addSubtitle(subTitle);//设置子标题­

  XYPlot xyPlot=(XYPlot)jfChart.getPlot();

  xyPlot.setDomainGridlinePaint(Color.red);//设置竖网格颜色­

  xyPlot.setRangeGridlinePaint(Color.orange);//设置横网格颜色­

  ValueAxis vx=xyPlot.getDomainAxis();

  //vx.setRange(0.0D,2000D);//设置Y轴范围­

   //坐标轴标尺值字体­

  //vx.setTickLabelFont(new Font("Serif",Font.CENTER_BASELINE,13));­

  //vx.setLabelFont(new Font("Serif",Font.CENTER_BASELINE,15));­

   //纵坐标间距设置­

        //vx.setTickUnit(new NumberTickUnit(20));­

  vx.setAutoRange(true);

  XYLineAndShapeRenderer xr=(XYLineAndShapeRenderer)xyPlot.getRenderer();

  xr.setSeriesPaint(0, Color.blue);//设置线的颜色­

  xr.setSeriesPaint(1, Color.green);

  xr.setBaseShapesVisible(true);//显示线上各个数据点­

  //显示数据点上的数据值­

  xr.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());

  xr.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,TextAnchor.BASELINE_RIGHT));

  xr.setBaseItemLabelsVisible(true);

  

  ChartPanel cp=new ChartPanel(jfChart);

  cp.setSize(new Dimension(550,350));

  this.getContentPane().add(cp);

}

   public void StartThread()

   {

    DataThread dt=new DataThread();

    dt.start();

   }

public void MyAction()

{

  this.addWindowListener(new WindowAdapter()

  {

   public void windowClosing(WindowEvent e) {

    int result=JOptionPane.showConfirmDialog(null, "确定要退出吗？","退出",JOptionPane.OK_CANCEL_OPTION);

    if(result==JOptionPane.OK_OPTION)

    {

    System.exit(0);

    }

    

   }

    public void windowIconified(WindowEvent e)

       {

       if(SystemTray.isSupported())

       {

        setVisible(false);

        SystemTray st=SystemTray.getSystemTray();

        try {

      st.add(trayIcon);//添加系统托盘­

     } catch (AWTException e1) {

      // TODO Auto-generated catch block­

      e1.printStackTrace();

     }

       }

    }

  });

}

  public void MyTray()

  {

   PopupMenu pm=new PopupMenu();

  MenuItem showItem=new MenuItem("SHOW");

  MenuItem exitItem=new MenuItem("EXIT");

  pm.add(showItem);

  pm.add(exitItem);

   Image image = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("wap.jpg"));

     trayIcon=new TrayIcon(image,"chart test",pm);

     showItem.addActionListener(new ActionListener()

     {

   @Override

   public void actionPerformed(ActionEvent e) {

    // TODO Auto-generated method stub­

    setVisible(true);

     setExtendedState(Frame.NORMAL);

    SystemTray.getSystemTray().remove(trayIcon);//移除系统托盘­

   }

   

      

     });

     

     exitItem.addActionListener(new ActionListener()

     {

      public void actionPerformed(ActionEvent e)

      {

       int result=JOptionPane.showConfirmDialog(null, "确实要退出吗？","退出",JOptionPane.OK_CANCEL_OPTION);

       if(result==JOptionPane.OK_OPTION)

       {

        System.exit(0);

       }

      }

      

     });

  }

  class DataThread extends Thread

  {

   public void run()

   {

    while(true)

    {

    Millisecond ms=new Millisecond();

    ts.add(ms,new Random().nextInt(500));

    ts2.add(ms,new Random().nextInt(500));

    try {

   this.sleep(30000);

  } catch (InterruptedException e) {

   // TODO Auto-generated catch block­

   e.printStackTrace();

  }

    }

   }

  }

}

