package yl;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;


/**
 * ͨ�÷���common method
 * 
 * @Authour Yang Lin
 * @Date: 2017��8��19��
 * @Time: ����3:34:55
 */
public class CM {


	/** ���һ��Ŀ�ʼʱ��� */
	public static long DayStartTime(Date date) {
		long start = 0;
		try {
			start = Str2Date(Date2StringFormat(date)).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return start;
	}
	
	
	/** ���һ��Ŀ�ʼʱ��� */
	public static long DayStartTime(String yyyy_MM_dd) {
		long start = 0;
		try {
			start = Str2Date(yyyy_MM_dd).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return start;
	}
	
	/** ����ʱ���ʽ: yyyy-MM-dd HH:mm:ss */
	public static String Date2StringFormat(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	/** һ��ʱ���(Date)�ڵ����(����)���� */
	public static int DayOrderOfMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY) * 60 + c.get(Calendar.MINUTE);
	}
	
	/**
	 * IPv4��ַתbyte[]
	 * @param ipv4
	 * @return
	 * @Authour Yang Lin
	 * @Date: 2017��8��22��
	 * @Time: ����4:40:40
	 */
	public static byte[] IPv4ToBytes(String ipv4) {
		String[] temp = ipv4.split("[.]");
		byte[] bs = new byte[4];
		bs[0] = (byte) Integer.parseInt(temp[0]);
		bs[1] = (byte) Integer.parseInt(temp[1]);
		bs[2] = (byte) Integer.parseInt(temp[2]);
		bs[3] = (byte) Integer.parseInt(temp[3]);
		return bs;
	}

	/**
	 * �Ƿ�ƥ��IPv4
	 * @param ipv4
	 * @return
	 * @Authour Yang Lin
	 * @Date: 2017��8��19��
	 * @Time: ����3:35:13
	 */
	public static boolean RegexMatcheIPv4(String ipv4) {
		String regex = "(([1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]){1}";// 127.0.0.1ƥ�䲻��
		regex = "((0|[1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}(0|[1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]){1}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ipv4);
		return matcher.matches();
	}
	
	/** ƥ��������ʱ�� */
	public static String RegexMatcheYYYYMMDDHHMI(String content) {
		String regex = "2[0-9][0-9][0-9]"
				+ "((0[1-9])|10|11|12)"
				+ "((0[1-9])|([1-2][0-9])|30|31)"
				+ "(([0-1][0-9])|(20|21|22|23))"
				+ "([0-5][0-9])";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	/** �ж��ļ���(ʱ��)�Ƿ���� */
	public static boolean BeyondTimezone(String fileName, Date now) {
		try {
			String fileFlag = RegexMatcheYYYYMMDDHHMI(fileName);
			if (fileFlag != null) {
				Date dateFlag = new SimpleDateFormat("yyyyMMddHHmm").parse(fileFlag);
				String last_minutes = System.getProperty("last_minutes");
				int minute = 10;
				minute = Integer.parseInt(last_minutes);
				if (now.getTime() - dateFlag.getTime() < minute * 60 * 1000L) {
					return false;
				}
			}
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/** ���ĳ�ꡢĳ�¸��µ����� */
	public static int GetDaysOfMonth(int yyyy,int MM){
		if(MM==1 || MM==3 || MM==5 || MM==7 || MM==8 || MM==10 || MM==12){
			return 31;
		}else if(MM==4 || MM==6 || MM==9 || MM==11){
			return 30;
		}else{
			//alert("2yue�ڴ�");
			if((yyyy%4==0&&yyyy%100!=0)||yyyy%400==0){
				return 29;
			}else{
				return 28;
			}
		}
		
	}
	
	public static long GetStartTimeMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return StartEndTimeMonth(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, 0)[0];
	}

	/** ����ĳ��Calendar, ���������·ݵĿ�ʼʱ��㡢����ʱ��� */
	public static long GetStartTimeMonth(Calendar calendar) {
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		c.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		return c.getTimeInMillis();
	}

	/**
	 * �������, �쳣����Ľ����Ϊ0
	 * @param fenzi
	 * @param fenmu
	 * @param times ������Ҫ�˵ı���(�����Ҫ�ٷֱ�, �ͳ�100)
	 * @return
	 * @Author Yang Lin
	 * @Date 2017��10��10��
	 * @Time ����9:05:04
	 */
	public static List<Integer> ListIntegerChu(List<Integer> fenzi,
			List<Integer> fenmu, Integer times) {
		System.out.println("Integer�������----------------------");
		System.out.println(List2String(fenzi));
		System.out.println(List2String(fenmu));
		System.out.println("Integer�������----------------------");
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < fenzi.size() && i < fenmu.size(); i++) {
			if (fenzi.get(i) != null && fenmu.get(i) != null && fenmu.get(i) != 0) {
				long chu = (new Long(fenzi.get(i)) * new Long(times)) / new Long(fenmu.get(i));
				result.add((int) chu);
			}
			else {
				result.add(new Integer(0));
			}
		}
		return result;
	}
	
	/** listת�ַ���, �ö��Ÿ��� */
	public static String List2String(List<?> list) {
		String result = "";
		if (list != null && list.size() > 0) {
			result = list.get(0).toString();
			for (int i = 1; i < list.size(); i++) {
				result += "," + list.get(i).toString();
			}
		}
		return result;
	}

	/**
	 * �����������, �쳣�ӽ��Ϊ0
	 * @param usageDay31
	 * @param usageDay01
	 * @return
	 */
	public static List<Integer> ListSubtract(List<Integer> usageDay31,
			List<Integer> usageDay01) {
		List<Integer> step = new ArrayList<Integer>();
		for (int ii = 0; ii < usageDay31.size() && ii < usageDay01.size(); ii++) {
			Integer temp31 = usageDay31.get(ii);
			Integer temp01 = usageDay01.get(ii);
			if (temp31 != null && temp01 != null && temp31 >= temp01) {
				step.add(temp31 - temp01);
			}
			else {
				step.add(0);
			}
		}
		return step;
	}

	/** ����һ��List&lt;List&lt;Integer&gt;&gt;������, �������size���Ӽ��ϵ�size */
	public static int MaxSizeListList(List<List<Integer>> usage) {
		int max = 0;
		for (List<Integer> list : usage) {
			if (CollectionUtils.isNotEmpty(list)) {
				int size = list.size();
				if (max < size) max = size;
			}
		}
		return max;
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param deltaMonth �������ȥ, ��������, 0Ϊ����
	 * @return
	 */
	public static long[] StartEndTimeMonth(int year, int month, int deltaMonth) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		// �ۼӵ�deltaMonth
		c.add(Calendar.MONTH, deltaMonth); 
		// 1��0ʱ0��0��000����
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		long month_start_time = GetStartTimeMonth(c);
		c.add(Calendar.MONTH, 1); // �����һ����
		long month_end_time = GetStartTimeMonth(c);
		
		return new long[] { month_start_time, month_end_time };
	}

	/** ���ڸ�ʽת������ 
	 * @throws ParseException */
	public static Date Str2Date(String yyyy_MM_dd) throws ParseException {
		Date date = new SimpleDateFormat(PP.SimpleDateFormat1).parse(yyyy_MM_dd);
		return date;
	}

	/** ���ڸ�ʽת������ 
	 * @throws ParseException */
	public static Date Str2Date2(String yyyy_MM_dd_HH_mm_ss) throws ParseException {
		Date date = new SimpleDateFormat(PP.SimpleDateFormat2).parse(yyyy_MM_dd_HH_mm_ss);
		return date;
	}

	/** treeMap.pollFirstEntry().getValue().toString() */
	public static String TreeMap2String(TreeMap<String, Object> treeMap) {
		if (treeMap == null || treeMap.size() == 0) return "";
		String result = treeMap.pollFirstEntry().getValue().toString();
		while (treeMap.size() > 0) {
			result += "," + treeMap.pollFirstEntry().getValue().toString();
		}
		return result;
	}
	
	public static List<Object> TreeMap2List(TreeMap<String, Object> treeMap) {
		List<Object> list = new ArrayList<Object>();
		if (treeMap != null && treeMap.size() > 0) {
			while (treeMap.size() > 0) {
				list.add(treeMap.pollFirstEntry().getValue().toString());
			}
		}
		return list;
	}

	/** List&lt;Object&gt;ת����List&lt;Integer&gt; */
	public static List<Integer> ListObject2Integer(List<Object> listObj) {
		List<Integer> listIngeger = new ArrayList<Integer>();
		for (int i = 0; i < listObj.size(); i++) {
			listIngeger.add(Integer.parseInt(listObj.get(i).toString()));
		}
		return listIngeger;
	}
	
	/** ���Զ��Ÿ�����stringת����List&lt;Integer&gt;, ������Ĭ��0 */
	public static List<Integer> String2ListInteger(String values) {
		List<Integer> list = new ArrayList<Integer>();
		if (StringUtils.isNotEmpty(values)) {
			String[] arr = values.split(",");
//			System.out.println("arr.length:" + arr.length);
			for (int i = 0; i < arr.length; i++) {
				Integer v = 0;
				if (StringUtils.isNotEmpty(arr[i])) {
					try {
						v = Integer.parseInt(arr[i].trim());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				list.add(v);
			}
		}
		return list;
	}
	
	/** ����һ��List&lt;Integer&gt;��ƽ��, ������Ĭ��0��ƽ��, ��ȥС��ȡ�� */
	public static Integer AvgListInteger(List<Integer> list) {
		Integer sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i) == null ? 0 : list.get(i);
		}
		return sum / list.size();
	}
	
	/**
	 * ͳ��һ��List&lt;Integer&gt;�и��ڱ�׼ֵ�ĸ���
	 * @param values
	 * @param standard
	 * @param countEqual �Ƿ�ͳ����ȵ�
	 * @return
	 */
	public static Integer CountOverStandard(List<Integer> values,
			Integer standard, Boolean countEqual) {
		int count = 0;
		for (Integer i : values) {
			if (i != null) {
				if (i > standard) {
					count++;
				}
				else if (countEqual && i == standard) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * ����List&lt;Integer&gt;�е����ֵ
	 * @param list
	 * @return
	 */
	public static Integer MaxListInteger(List<Integer> list) {
		Integer max = null;
		if (CollectionUtils.isNotEmpty(list)) {
			if (list.size() == 1) return list.get(0);
			max = list.get(0);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) > max) {
					max = list.get(i);
				}
			}
		}
		return max;
	}

	// --------------------------------------------------------------
	// --------------------------------------------------------------
	// -------------------------------------------------------------- ����ʹ�õĹ�������, ��ʼ
	// --------------------------------------------------------------
	// --------------------------------------------------------------

	/**
	 * δ�������·ݴ���ʹ���ʵ�Ԥ��ֵ
	 * @param steps (��ʷ�����·����ݵ�)����, ���������·�֮��
	 * @param baseUsage ��������, ÿ������ֵ�ڴ˻������ۼ�
	 * @return
	 */
	public static List<List<Integer>> DiskUsageForcast(List<List<Integer>> steps, List<Integer> baseUsage) {
		List<List<Integer>> usageForecast = new ArrayList<List<Integer>>(); // statisticsDiskUage2("127.0.0.1");
		for (int ii = 0; ii < steps.size(); ii++) {
			List<Integer> oneMonthStep = steps.get(ii);
			for (int jj = 0; jj < baseUsage.size() && jj < oneMonthStep.size(); jj++) {
				int forecast = baseUsage.get(jj) + oneMonthStep.get(jj);
				forecast = forecast < 0 ? 0 : (forecast > 100 ? 100 : forecast);
				baseUsage.set(jj, forecast);
			}
			String oneMonthForecastS = List2String(baseUsage);
			usageForecast.add(String2ListInteger(oneMonthForecastS));
		}
		return usageForecast;
	}

	// --------------------------------------------------------------
	// --------------------------------------------------------------
	// -------------------------------------------------------------- ����ʹ�õĹ�������, ����
	// --------------------------------------------------------------
	// --------------------------------------------------------------
	
	public static void main(String[]args) {
		
		try {
			Date date = new Date();
			date = Str2Date2("2017-10-18 23:59:59");
			int order = DayOrderOfMinute(date);
			System.out.println(order);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * 
usageDay31:41,60,64,74
usageDay01:38,59,61,71
result: 
		 * 
		 */
//		List<Integer> usageDay31 = CM.String2ListInteger("41,60,64,74");
//		List<Integer> usageDay01 = CM.String2ListInteger("38,59,61,71");
//		List<Integer> result = ListSubtract(usageDay31, usageDay01);
//		System.out.println(List2String(result));
		
//		List<Integer> baseUsage = new ArrayList<Integer>();
//		List<List<Integer>> steps = new ArrayList<List<Integer>>();
//		
//		baseUsage = String2ListInteger("10,20,30");
//		
//		steps.add(String2ListInteger("1,2,3"));
//		steps.add(String2ListInteger("1,2,3"));
//		steps.add(String2ListInteger("1,2,3"));
//		steps.add(String2ListInteger("1,2,3"));
//		
//		steps.add(String2ListInteger("1,2,3"));
//		steps.add(String2ListInteger("1,2,3"));
//		steps.add(String2ListInteger("1,2,3"));
//		steps.add(String2ListInteger("1,2,3"));
//		
//		steps.add(String2ListInteger("1,2,3"));
//		steps.add(String2ListInteger("1,2,3"));
//		steps.add(String2ListInteger("1,2,3"));
//		steps.add(String2ListInteger("1,2,3"));
//		
//		List<List<Integer>> usageForcast = DiskUsageForcast(steps, baseUsage);
//		for (int i = 0; i < usageForcast.size(); i++) {
//			System.out.println(List2String(usageForcast.get(i)));
//		}
		
//		long[] se = StartEndTimeMonth(2015, 9, 0);
//		System.out.println(new Date(se[0]));
//		System.out.println(new Date(se[1]));
		
		
//		String yyyy_MM_dd = year + "-" + month + "-01";
//		try {
//			Date date = Str2Date(yyyy_MM_dd);
//			System.out.println(date);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		List<Integer> list = new ArrayList<Integer>();
//		list.add(1);
//		list.add(12);
//		list.add(13);
//		System.out.println(List2String(list));
		
//		String s = "11,22,33,44,55,66";
//		List<Integer> list = String2ListInteger(s);
//		int count = CountOverStandard(list, 33, false);
//		System.out.println(count);
//		System.out.println(BeyondTimezone("awps201709161032.CCC", new Date()));
//		System.setProperty("last_minutes", 720 + "");
//		File file = new File("F:/yl/awps201708232032.CCC");
//		System.out.println(NeedToUpload(file, new Date()));
//	}
//		String content = "awps201708222400.CCC";
//		MySystem.println(RegexMatcheYYYYMMDDHHMI(content));
//	}
//		MySystem.println(RegexMatcheIPv4("0.0.0.0"));
//		MySystem.println(RegexMatcheIPv4("1.1.1.1"));
//		MySystem.println(RegexMatcheIPv4("255.255.255.0"));
//		MySystem.println(RegexMatcheIPv4("255.255.0.255"));
//		MySystem.println(RegexMatcheIPv4("255.0.255.255"));
//		MySystem.println(RegexMatcheIPv4("0.255.255.255"));
//		MySystem.println();
//		MySystem.println(RegexMatcheIPv4("255.255.255.256"));
//		MySystem.println(RegexMatcheIPv4("255.255.256.256"));
//		MySystem.println(RegexMatcheIPv4("255.256.255.255"));
//		MySystem.println(RegexMatcheIPv4("255.256.255.256"));
//		MySystem.println(RegexMatcheIPv4("255.256.256.255"));
//		MySystem.println(RegexMatcheIPv4("255.256.256.256"));
//		MySystem.println(RegexMatcheIPv4("256.255.255.255"));
//		MySystem.println(RegexMatcheIPv4("256.255.255.256"));
//		MySystem.println(RegexMatcheIPv4("256.255.256.255"));
//		MySystem.println(RegexMatcheIPv4("256.255.256.256"));
//		MySystem.println(RegexMatcheIPv4("256.256.255.255"));
//		MySystem.println(RegexMatcheIPv4("256.256.255.256"));
//		MySystem.println(RegexMatcheIPv4("256.256.256.255"));
//		MySystem.println(RegexMatcheIPv4("256.256.256.256"));
	}
		
}
