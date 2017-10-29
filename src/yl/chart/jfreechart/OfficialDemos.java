package yl.chart.jfreechart;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InterfaceAddress;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class OfficialDemos {

	public static void main(String[] args) {
//		System.out.println(new Date());
//		System.out.println("args:" + args);
//		System.out.println("args.length:" + args.length);
//		if (args.length >= 1) {
//			System.out.println(args[0]);
//		}
//		String classNameSelected = args[0];
//		test(classNameSelected);
		
		// ‰÷»æÕº∆¨
//		for (int index = 0; index < names.length; index++) {
//			test(index, "demo." + names[index]);
//        	try {
//				Thread.sleep(500L);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
		System.out.println(names.length);
		for (int i = 0; i < wrongIndex.length; i++) {
			int wrongI = wrongIndex[i];
			test(wrongI, "demo." + names[wrongI]);
	    	try {
				Thread.sleep(1000L);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public static void test(int index, String classNameSelected) {
		try {
//        	Object demo = Class.forName(classNameSelected).newInstance();
//			Method method = demo.getMethod("pack", new Class[] {});
//			method.invoke(null, new Object[] { new String[]{} });
//        	Method pack = Class.forName(classNameSelected).getMethod("pack");
//        	pack.invoke(demo);
//        	Method setVisible = Class.forName(classNameSelected).getMethod("setVisible", new Class[] { boolean.class });
//        	setVisible.invoke(demo, new Object[] { true });
        	Constructor<?> constructor = Class.forName(classNameSelected).getConstructor(String.class);
        	Object demo = constructor.newInstance("");
        	Method pack = Class.forName(classNameSelected).getMethod("pack");
        	pack.invoke(demo);
        	Method setCloseOperation = Class.forName(classNameSelected).getMethod("setDefaultCloseOperation", new Class[] { int.class });
        	setCloseOperation.invoke(demo, new Object[] { JFrame.DO_NOTHING_ON_CLOSE });
        	Method setVisible = Class.forName(classNameSelected).getMethod("setVisible", new Class[] { boolean.class });
        	setVisible.invoke(demo, new Object[] { true });
        	
        	try {
				Thread.sleep(500L);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	// ±£¥ÊÕº∆¨
//            BufferedImage  bi = new BufferedImage(localBarChart3DDemo2.getWidth(),
//        				localBarChart3DDemo2.getHeight(), BufferedImage.TYPE_INT_ARGB);
//            Graphics2D  g2d = bi.createGraphics();
//            localBarChart3DDemo2.paint(g2d);
//            ImageIO.write(bi, "PNG", new File("c:/localBarChart3DDemo2.png"));
        	int width = 0;
        	int height = 0;
        	int imageType = BufferedImage.TYPE_INT_ARGB;
        	Method getWidth = Class.forName(classNameSelected).getMethod("getWidth");
        	Method getHeight = Class.forName(classNameSelected).getMethod("getHeight");
        	width = (Integer) getWidth.invoke(demo);
        	height = (Integer) getHeight.invoke(demo);
        	BufferedImage  bi = new BufferedImage(width, height, imageType);
            Graphics2D  g2d = bi.createGraphics();
//            localBarChart3DDemo2.paint(g2d);
//            Method paint = Class.forName(classNameSelected).getMethod("paint", new Class[] { Graphics2D.class });
//            Method paint = Class.forName(classNameSelected).getDeclaredMethod("paint", new Class[] { Graphics2D.class });
//            Method paint = Class.forName("java.awt.Window").getMethod("paint", new Class[] { Graphics2D.class });
//            Method paint = Class.forName("java.awt.Window").getDeclaredMethod("paint", new Class[] { Graphics2D.class });
//            Method paint = Class.forName("java.awt.Window").getDeclaredMethod("isActive");
            Method paint = Class.forName("java.awt.Container").getDeclaredMethod("paint", new Class[] { java.awt.Graphics.class });
//            paint.invoke(demo, g2d);
            paint.invoke(demo, g2d);
            String indexStr = index < 10 ? "00" + index : (index < 100 ? "0" + index : "" + index);
            try {
				ImageIO.write(bi, "PNG", new File("c:/" + indexStr + " - " + classNameSelected + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	static int[] wrongIndex = {
		66,
		67,
		68,
		92,
		93,
		112,
		144,
		168,
		205,
		216,
		262
	};
	
	static String[] names = {
			"AnnotationDemo1",
			"AnnotationDemo2",
			"AreaChartDemo1",
			"AxisOffsetsDemo1",
			"BarChart3DDemo1",
			"BarChart3DDemo2",
			"BarChart3DDemo3",
			"BarChart3DDemo4",
			"BarChartDemo1",
			"BarChartDemo10",
			"BarChartDemo11",
			"BarChartDemo2",
			"BarChartDemo3",
			"BarChartDemo4",
			"BarChartDemo5",
			"BarChartDemo6",
			"BarChartDemo7",
			"BarChartDemo8",
			"BarChartDemo9",
			"BoxAndWhiskerChartDemo1",
			"BoxAndWhiskerChartDemo2",
			"BubbleChartDemo1",
			"BubbleChartDemo2",
			"CandlestickChartDemo1",
			"CategoryLabelPositionsDemo1",
			"CategoryMarkerDemo1",
			"CategoryMarkerDemo2",
			"CategoryPointerAnnotationDemo1",
			"CategoryStepChartDemo1",
			"ClusteredXYBarRendererDemo1",
			"CombinedCategoryPlotDemo1",
			"CombinedCategoryPlotDemo2",
			"CombinedTimeSeriesDemo1",
			"CombinedXYPlotDemo1",
			"CombinedXYPlotDemo2",
			"CombinedXYPlotDemo3",
			"CombinedXYPlotDemo4",
			"CombinedXYPlotDemo5",
			"CompassDemo1",
			"CompassFormatDemo1",
			"CompassFormatDemo2",
			"CrossSectionDemo1",
			"CrosshairDemo1",
			"CrosshairDemo2",
			"CrosshairDemo3",
			"CrosshairDemo4",
			"CylinderChartDemo1",
			"CylinderChartDemo2",
			"DefaultXYDatasetDemo1",
			"DefaultXYDatasetDemo2",
			"DeviationRendererDemo1",
			"DeviationRendererDemo2",
			"DeviationRendererDemo3",
			"DialDemo1",
			"DialDemo2",
			"DialDemo3",
			"DialDemo4",
			"DialDemo5",
			"DifferenceChartDemo1",
			"DifferenceChartDemo2",
			"DualAxisDemo1",
			"DualAxisDemo2",
			"DualAxisDemo3",
			"DualAxisDemo4",
			"DualAxisDemo5",
			"DualAxisDemo6",
			"DynamicDataDemo1",
			"DynamicDataDemo2",
			"DynamicDataDemo3",
			"EventFrequencyDemo1",
			"Function2DDemo1",
			"GanttDemo1",
			"GanttDemo2",
			"GanttDemo3",
			"GradientPaintTransformerDemo1",
			"GridBandDemo1",
			"HideSeriesDemo1",
			"HideSeriesDemo2",
			"HideSeriesDemo3",
			"HighLowChartDemo1",
			"HighLowChartDemo2",
			"HighLowChartDemo3",
			"HistogramDemo1",
			"HistogramDemo2",
			"IntervalBarChartDemo1",
			"ItemLabelDemo1",
			"ItemLabelDemo2",
			"ItemLabelDemo3",
			"ItemLabelDemo4",
			"ItemLabelDemo5",
			"LayeredBarChartDemo1",
			"LayeredBarChartDemo2",
			"LegendTitleToImageDemo1",
			"LegendTitleToImageDemo2",
			"LegendWrapperDemo1",
			"LineChart3DDemo1",
			"LineChartDemo1",
			"LineChartDemo2",
			"LineChartDemo3",
			"LineChartDemo4",
			"LineChartDemo5",
			"LineChartDemo6",
			"LineChartDemo7",
			"LineChartDemo8",
			"LogAxisDemo1",
			"LogarithmicAxisDemo1",
			"LogarithmicAxisDemo2",
			"MarkerDemo1",
			"MarkerDemo2",
			"MeterChartDemo1",
			"MeterChartDemo2",
			"MeterChartDemo3",
			"MeterChartDemo4",
			"MinMaxCategoryPlotDemo1",
			"MouseListenerDemo1",
			"MouseListenerDemo2",
			"MouseListenerDemo3",
			"MouseListenerDemo4",
			"MouseOverDemo1",
			"MovingAverageDemo1",
			"MultipleAxisDemo1",
			"MultipleAxisDemo2",
			"MultipleAxisDemo3",
			"MultipleAxisDemo4",
			"MultipleDatasetDemo1",
			"MultiplePieChartDemo1",
			"MultiplePieChartDemo2",
			"MultiplePieChartDemo3",
			"MultiplePieChartDemo4",
			"NormalDistributionDemo1",
			"NormalDistributionDemo2",
			"OverlaidBarChartDemo1",
			"OverlaidBarChartDemo2",
			"OverlaidXYPlotDemo1",
			"OverlaidXYPlotDemo2",
			"ParetoChartDemo1",
			"PeriodAxisDemo1",
			"PeriodAxisDemo2",
			"PeriodAxisDemo3",
			"PieChart3DDemo1",
			"PieChart3DDemo2",
			"PieChart3DDemo3",
			"PieChartDemo1",
			"PieChartDemo2",
			"PieChartDemo3",
			"PieChartDemo4",
			"PieChartDemo5",
			"PieChartDemo6",
			"PieChartDemo7",
			"PieChartDemo8",
			"PlotOrientationDemo1",
			"PlotOrientationDemo2",
			"PolarChartDemo1",
			"PopulationChartDemo1",
			"PriceVolumeDemo1",
			"PriceVolumeDemo2",
			"RegressionDemo1",
			"RelativeDateFormatDemo1",
			"RelativeDateFormatDemo2",
			"RingChartDemo1",
			"ScatterPlotDemo1",
			"ScatterPlotDemo2",
			"ScatterPlotDemo3",
			"ScatterPlotDemo4",
			"ScatterRendererDemo1",
			"SlidingCategoryDatasetDemo1",
			"SlidingCategoryDatasetDemo2",
			"SlidingGanttDatasetDemo1",
			"SparklineDemo1",
			"SpiderWebChartDemo1",
			"StackedAreaChartDemo1",
			"StackedBarChart3DDemo1",
			"StackedBarChart3DDemo2",
			"StackedBarChart3DDemo3",
			"StackedBarChart3DDemo4",
			"StackedBarChart3DDemo5",
			"StackedBarChartDemo1",
			"StackedBarChartDemo2",
			"StackedBarChartDemo3",
			"StackedBarChartDemo4",
			"StackedBarChartDemo5",
			"StackedBarChartDemo6",
			"StackedBarChartDemo7",
			"StackedBarDemo1",
			"StackedBarDemo2",
			"StackedBarDemo3",
			"StackedXYAreaChartDemo1",
			"StackedXYAreaChartDemo2",
			"StackedXYAreaChartDemo3",
			"StackedXYAreaRendererDemo1",
			"StackedXYBarChartDemo1",
			"StackedXYBarChartDemo2",
			"StackedXYBarChartDemo3",
			"StatisticalBarChartDemo1",
			"StatisticalLineChartDemo1",
			"SubCategoryAxisDemo1",
			"SurveyResultsDemo1",
			"SurveyResultsDemo2",
			"SurveyResultsDemo3",
			"SymbolAxisDemo1",
			"ThermometerDemo1",
			"ThermometerDemo2",
			"ThumbnailDemo1",
			"TimePeriodValuesDemo1",
			"TimePeriodValuesDemo2",
			"TimePeriodValuesDemo3",
			"TimeSeriesDemo1",
			"TimeSeriesDemo10",
			"TimeSeriesDemo11",
			"TimeSeriesDemo12",
			"TimeSeriesDemo13",
			"TimeSeriesDemo14",
			"TimeSeriesDemo2",
			"TimeSeriesDemo3",
			"TimeSeriesDemo4",
			"TimeSeriesDemo5",
			"TimeSeriesDemo6",
			"TimeSeriesDemo7",
			"TimeSeriesDemo8",
			"TimeSeriesDemo9",
			"TranslateDemo1",
			"VectorPlotDemo1",
			"VectorRendererDemo1",
			"WaterfallChartDemo1",
			"WindChartDemo1",
			"XIntervalSeriesCollectionDemo1",
			"XYAreaChartDemo1",
			"XYAreaChartDemo2",
			"XYAreaRenderer2Demo1",
			"XYBarChartDemo1",
			"XYBarChartDemo2",
			"XYBarChartDemo3",
			"XYBarChartDemo4",
			"XYBarChartDemo5",
			"XYBarChartDemo6",
			"XYBarChartDemo7",
			"XYBlockChartDemo1",
			"XYBlockChartDemo2",
			"XYBlockChartDemo3",
			"XYBoxAnnotationDemo1",
			"XYDrawableAnnotationDemo1",
			"XYErrorRendererDemo1",
			"XYErrorRendererDemo2",
			"XYLine3DRendererDemo1",
			"XYLineAndShapeRendererDemo1",
			"XYLineAndShapeRendererDemo2",
			"XYPointerAnnotationDemo1",
			"XYPolygonAnnotationDemo1",
			"XYSeriesDemo1",
			"XYSeriesDemo2",
			"XYSeriesDemo3",
			"XYShapeRendererDemo1",
			"XYSplineRendererDemo1",
			"XYStepAreaRendererDemo1",
			"XYStepRendererDemo1",
			"XYStepRendererDemo2",
			"XYTaskDatasetDemo1",
			"XYTaskDatasetDemo2",
			"XYTitleAnnotationDemo1",
			"YIntervalChartDemo1",
			"YIntervalChartDemo2",
			"YieldCurveDemo1"
	};

}
