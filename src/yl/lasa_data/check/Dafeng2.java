package yl.lasa_data.check;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yl.CM;
import yl.lasa_data.MatchDatFileLine;
import yl.log4j.MyLogger;

public class Dafeng2 {
	public static void check(String piece, String yyyy_mm_dd) {
		System.out.println(yyyy_mm_dd + "大风:" + piece);
		check___(piece, yyyy_mm_dd);
	}
	private static void check___(String piece, String yyyy_mm_dd) {
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
//		// 时间顺序不对
//		Integer lastIndex = null;
//		Matcher mTime = Pattern.compile("[0-9]{4}").matcher(piece);
//		while (mTime.find()) {
//			try {
//				String hhmm = mTime.group();
//				Integer nowIndex = CM.GetUTCMinutesOfDay(hhmm);
//				if (lastIndex != null) {
//					if (nowIndex != 0 && lastIndex > nowIndex) {
////						throw new Exception(yyyy_mm_dd + " : " + "TS时间错误:" + hhmm);
//						MyLogger.error(yyyy_mm_dd + " : " + "TS时间错误:" + hhmm);
//					}
//				}
//				lastIndex = nowIndex;
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
	static String REG_timeS_timeE_speed_timeM_direct = "(([0-1][0-9])|20|21|22|23)[0-5][0-9]"
			+ "-(([0-1][0-9])|20|21|22|23)[0-5][0-9]"
			+ "\\s[0-9]{1,2}\\((([0-1][0-9])|20|21|22|23)[0-5][0-9]\\)[0-9]{1,2}0"; // 开始时间-结束时间 风速(时间)风向
	static String REG_time_speed_timeM_direct = "(([0-1][0-9])|20|21|22|23)[0-5][0-9]"
			+ "\\s[0-9]{1,2}\\((([0-1][0-9])|20|21|22|23)[0-5][0-9]\\)[0-9]{1,2}0"; // 开始时间 风速(时间)风向
	public static void seekWindSpeed(String piece, String yyyy_mm_dd) {
		String regex = "[0-9]{1,2}\\((([0-1][0-9])|20|21|22|23)[0-5][0-9]\\)[0-9]{1,2}0"; // 风速(时间)风向
		regex = "(([0-1][0-9])|20|21|22|23)[0-5][0-9]"
				+ "-(([0-1][0-9])|20|21|22|23)[0-5][0-9]"
				+ "\\s[0-9]{1,2}\\((([0-1][0-9])|20|21|22|23)[0-5][0-9]\\)[0-9]{1,2}0"; // 开始时间-结束时间 风速(时间)风向
		regex = "(([0-1][0-9])|20|21|22|23)[0-5][0-9]"
//				+ "-(([0-1][0-9])|20|21|22|23)[0-5][0-9]"
				+ "\\s[0-9]{1,2}\\((([0-1][0-9])|20|21|22|23)[0-5][0-9]\\)[0-9]{1,2}0"; // 开始时间 风速(时间)风向
//		regex = "\\(";
//		regex = "\\([0-9]{4}\\)";
		regex = ""
				+ "^[\\s]{0,1}[—]{0,1}(([+]{0,2})|([-]{0,2}))GA"
//				+ "((" + REG_timeS_timeE_speed_timeM_direct + ")|(" + REG_time_speed_timeM_direct + ")){1}"
				+ "((" + REG_timeS_timeE_speed_timeM_direct + ")|(" + REG_time_speed_timeM_direct + ")){1}"
				+ "((\\s" + REG_timeS_timeE_speed_timeM_direct + ")|(\\s" + REG_time_speed_timeM_direct + ")){0,}$"
//				+ "(\\s(" + REG_timeS_timeE_speed_timeM_direct + ")|(" + REG_time_speed_timeM_direct + ")){0,}";
//				+ "(\\s(" + REG_timeS_timeE_speed_timeM_direct + ")|(" + REG_time_speed_timeM_direct + ")){0,}"
				;
//		System.out.println("allreg:<" + regex + ">");
		Matcher m = Pattern.compile(regex).matcher(piece);
		while (m.find()) {
//			System.out.println(m.group());
			String result = m.group();
			System.out.println("result:<" + result + ">");
		}
	}
	
	public static void matchAaaOrBbb() {
		String regexA = "[A-C]{1,3}"; 
		String regexa = "[a-c]{1,3}"; 
		String regex = "";
		Matcher m = Pattern.compile(regexa).matcher("sfaaAAAfsddsfBBBafdcccBBBAAA");
		System.out.println("--------------------");
		while (m.find()) {
//			System.out.println(m.group());
			String result = m.group();
			System.out.println("result:" + result);
		}
	}
	
	public static void main(String[]args) {
		String piece = "TS1117SE-1131SE 1210S-1241S ";
		piece = "TS1940NW-1848Z-1954N-1010NE 1046SW-1057SW ";
		piece = "GA1651 18(1651)110 0837 17(0837)190 0926-0929 20(0926)190 0947 17(0947)180 1000-1019 19(2359)190";
		piece = "GA1632-1636 17(1635)180 1651 18(1651)110 0837 17(0837)190 0926-0929 20(0926)190 0947 17(0947)180 1000-1019 19(2359)190";
//		piece = "GA1632-1636 17(1635)180";
//		piece = " 1651 18(1651)110 0837 17(0837)190 0926-0929 20(0926)190";
		piece = "GA0850 17(0850)190";
		try {
//			check(piece, "20461231");
			seekWindSpeed(piece, "20461231");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("msg:" + e.getMessage());
		}
//		try {
//			MatchDatFileLine.readAllLine();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		matchAaaOrBbb();
	}

}
