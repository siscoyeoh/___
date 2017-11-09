package yl.lasa_data.check;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yl.CM;
import yl.lasa_data.MatchDatFileLine;
import yl.log4j.MyLogger;

public class Dafeng {
	public static void check(String piece, String yyyy_mm_dd) {
//		System.out.println(yyyy_mm_dd + "大风:" + piece);
//		try {
//			check___(piece, yyyy_mm_dd);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
////			MyLogger.error(e.getMessage());
//			MyLogger.error(yyyy_mm_dd + " " + e.getMessage());
//		}
	}
	private static void check___(String piece, String yyyy_mm_dd) throws Exception {
//		if (piece.endsWith(" ")) piece = piece.substring(0, piece.length() - 1); // 剔除结尾一个空格
		while (piece.endsWith(" ")) piece = piece.substring(0, piece.length() - 1); // 剔除结尾一个或多个空格
//		seekWindSpeed(piece, yyyy_mm_dd);
		String regexFull = 
				"^[\\s]{0,1}[—]{0,1}(([+]{0,2})|([-]{0,2}))GA"
				+ "(((([0-1][0-9])|20|21|22|23)[0-5][0-9]-(([0-1][0-9])|20|21|22|23)[0-5][0-9]\\s[0-9]{1,2}\\((([0-1][0-9])|20|21|22|23)[0-5][0-9]\\)[0-9]{1,2}0)|((([0-1][0-9])|20|21|22|23)[0-5][0-9]\\s[0-9]{1,2}\\((([0-1][0-9])|20|21|22|23)[0-5][0-9]\\)[0-9]{1,2}0)){1}"
				+ "((\\s(([0-1][0-9])|20|21|22|23)[0-5][0-9]-(([0-1][0-9])|20|21|22|23)[0-5][0-9]\\s[0-9]{1,2}\\((([0-1][0-9])|20|21|22|23)[0-5][0-9]\\)[0-9]{1,2}0)|(\\s(([0-1][0-9])|20|21|22|23)[0-5][0-9]\\s[0-9]{1,2}\\((([0-1][0-9])|20|21|22|23)[0-5][0-9]\\)[0-9]{1,2}0)){0,}$";
		Matcher mFull = Pattern.compile(regexFull).matcher(piece);
		boolean matchFull = mFull.find();
		if (matchFull) {
			String fullTS = mFull.group();
			if (fullTS.length() != piece.length()) {
				// 匹配不上整个piece
				MyLogger.error(yyyy_mm_dd + " : 大风录入错误1:<" + fullTS + ">");
			}
		}
		else {
			// 匹配不上整个piece
			MyLogger.error(yyyy_mm_dd + " : 大风录入错误2:<" + piece + ">");
		}
		// 时间顺序不对
		// 格式1(开始时间-结束时间): 最大风时间不在"开始-结束"时间内
		{
			Matcher m = Pattern.compile("(([0-1][0-9])|20|21|22|23)[0-5][0-9]-(([0-1][0-9])|20|21|22|23)[0-5][0-9]\\s[0-9]{1,2}\\((([0-1][0-9])|20|21|22|23)[0-5][0-9]\\)[0-9]{1,2}0").matcher(piece);
			while (m.find()) {
//				System.out.println(m.group());
				String result = m.group();
				// 匹配出3个时间的hour_order
				List<Integer> order012 = new ArrayList<Integer>();
				Matcher m012 = Pattern.compile("([0-1][0-9]|20|21|22|23)[0-5][0-9]").matcher(result);
				while (m012.find()) {
					order012.add(CM.GetUTCMinutesOfDay(m012.group()));
				}
				int order0 = order012.get(0);
				int order1 = order012.get(1);
				int order2 = order012.get(2);
				if (order1 == 0 && order2 == 0) {
					order1 = 1440;
					order2 = 1440;
				}
				else if (order1 == 0) {
					order1 = 1440;
				}
				if (order0 <= order2 && order2 <= order1) {
					// 时间顺序合法
				}
				else {
					// 时间顺序非法
					throw new Exception(yyyy_mm_dd + " 时间顺序错误:<" + result + ">");
				}
			}
		}
		// 格式2(开始时间): 最大风时间不等于"开始时间"时间内
		{
			Matcher m = Pattern.compile("([^\\-](([0-1][0-9])|20|21|22|23)[0-5][0-9]\\s[0-9]{1,2}\\((([0-1][0-9])|20|21|22|23)[0-5][0-9]\\)[0-9]{1,2}0)").matcher(piece);
			while (m.find()) {
				String result = m.group();
				result = result.substring(1, result.length());
				System.out.println("result:" + result);
				// 匹配出2个时间的hour_order
				List<Integer> order012 = new ArrayList<Integer>();
				Matcher m012 = Pattern.compile("([0-1][0-9]|20|21|22|23)[0-5][0-9]").matcher(result);
				while (m012.find()) {
					order012.add(CM.GetUTCMinutesOfDay(m012.group()));
				}
				int order0 = order012.get(0);
				int order1 = order012.get(1);
				if (order0 == order1) {
					// 时间顺序合法
				}
				else {
					// 时间顺序非法
					throw new Exception(yyyy_mm_dd + " 时间点错误:<" + result + ">");
				}
			}
		}
	}
	
	public static void main(String[]args) throws Exception {
		String piece = "";
		piece = "GA1651 18(1651)110 0837 17(0837)190 0926-0929 20(0926)190 0947 17(0947)180 1000-1019 19(1016)190 ";
		piece = "GA2253-2304 17(2300)070";
		check___(piece, "yyyy_mm_dd");
	}

}
