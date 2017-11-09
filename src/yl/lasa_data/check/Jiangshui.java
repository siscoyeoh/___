package yl.lasa_data.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yl.CM;
import yl.log4j.MyLogger;


public class Jiangshui {

	public static void check(String piece, String yyyy_mm_dd) {
//		System.out.println(yyyy_mm_dd + "降水:" + piece);
		try {
			check___2(piece, yyyy_mm_dd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			MyLogger.error(e.getMessage());
			MyLogger.error(yyyy_mm_dd + " " + e.getMessage());
		}
	}
	
	/**
	 * 降水的匹配: <br/>
	 * 1. —-SN2245-2345(0.0)2345: 中文横杠+英文横杠+时间0+英文横杠+时间1+(降水量)+时间1, 降水量必须小数点后1位<br/>
	 * 2. —-SHSN2215--SN0040-0225: <br/>
	 * 3. —-SN2248-0233 0744-0940: <br/>
	 * 4. —-SN2243-2335
	 * 5. —-SN2240-SHSN(700)0128-(800)0152-(700)0221-SN(700)0336-(500)0409--SN(1500)0532-(3100)0640 0718-0820: (能见度)时间
	 * 6. 错误: RA(0.8)1920, 应为:1920RA(0.8)1920
	 * 7. -SHGS0945-1004 1010-1050: 中间空格表示停了(一般10分钟以上), 这样记录也可以:-SHGS0945-1004-1010-1050
	 * 8. 视为错误: -SHRA0981-1029(0.4)1029 1145--RA1344-1441(1.4)1441, 1145处应是一个时间段, 时间点成都不允许
	 * 9. 视为正确: -RA1350
	 * 0. 
	 * @param piece
	 * @param yyyy_mm_dd
	 * @throws Exception
	 * @Author Yang Lin
	 * @Date 2017年11月9日
	 * @Time 下午3:30:49
	 */
	private static void check___2(String piece, String yyyy_mm_dd) throws Exception {
		
	}
	
	private static void check___1(String piece, String yyyy_mm_dd) throws Exception {
		// 挑出降水量后两个时间不相等的, 正确格式应为: 0618-0633(0.5)0633
		while (piece.endsWith(" ")) piece = piece.substring(0, piece.length() - 1); // 剔除结尾一个或多个空格
		String regex0 = "[0-9]{4}[(]\\d{1,5}\\.\\d{0,9}[)][0-9]{4}";
		Matcher matcher0 = Pattern.compile(regex0).matcher(piece);
		while (matcher0.find()) {
			String result0 = matcher0.group(); // 0633(0.5)0633
			int index = matcher0.start();
			System.out.println(yyyy_mm_dd + "降水 piece:<" + piece + ">");
			String result0Full = piece.substring(index - 5, index + result0.length()); // 0618-0633(0.5)0633
//			System.out.println(result0Full);
			// 匹配出降水量, 并比较格式
			Matcher m0 = Pattern.compile("[(]\\d{1,8}\\.\\d{0,9}[)]").matcher(result0Full); // (0.5)
			if (m0.find()) {
				String raincount = m0.group();
//				System.out.println("raincount:" + raincount);
				Matcher m1 = Pattern.compile("[(]\\d{1,8}\\.\\d{1}[)]").matcher(raincount); // 0.5
				boolean rc1 = m1.find();
//				System.out.println("rc1:" + rc1);
				if (rc1) {
//					System.out.println("m1.group():" + m1.group());
				}
				else {
//					throw new Exception("格式错误:" + raincount);
					MyLogger.error(yyyy_mm_dd + " : " + "格式错误1:" + raincount);
				}
			}
			else {
//				throw new Exception("格式错误:" + result0Full);
				MyLogger.error(yyyy_mm_dd + " : " + "格式错误2:" + result0Full);
			}
			// 匹配出三个时间, 并比较顺序
			String time0 = piece.substring(index - 5, index - 1);
			String time1 = result0.substring(0, 4);
			String time2 = result0.substring(result0.length() - 4, result0.length());
			int t0 = CM.GetUTCMinutesOfDay(time0);
			int t1 = CM.GetUTCMinutesOfDay(time1);
			int t2 = CM.GetUTCMinutesOfDay(time2);
			
			// 处理结尾时间刚好是日期边界
			if (t2 == 0 && t1 > 0 && t0 > 0) { t2 = t1; }
			else if (t2 == 0 && t1 == 0 && t0 > 0) { t1 = t0; t2 = t0; }
			
			if (t0 <= t1 && t1 <= t2) {
				
			}
			else {
//				throw new Exception("时间顺序错误:" + result0Full);
				MyLogger.error(yyyy_mm_dd + " : " + "时间顺序错误3:" + result0Full);
			}
//			System.out.println("time0:" + time0);
//			System.out.println("time1:" + time1);
//			System.out.println("time2:" + time2);
		}
	}
	
	public static void main(String[]args) {
		String piece = "-SHRA0618-0633(0.50)0633 0758-0814 1152-1208 1241-1244 ";
		try {
			check(piece, "20461231");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("msg:" + e.getMessage());
		}
	}

}
