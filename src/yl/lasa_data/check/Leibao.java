package yl.lasa_data.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yl.CM;
import yl.log4j.MyLogger;

public class Leibao {

	public static void check(String piece, String yyyy_mm_dd) {
//		System.out.println(yyyy_mm_dd + "雷暴:" + piece);
//		check___(piece, yyyy_mm_dd);
	}
	private static void check___(String piece, String yyyy_mm_dd) {
		System.out.println(yyyy_mm_dd + "雷暴:" + piece);
		while (piece.endsWith(" ")) piece = piece.substring(0, piece.length() - 1); // 剔除结尾一个或多个空格
		String regexFull = "^[-|+]{0,1}TS([0-9]{4})(SE|SW|NW|NE|E|S|W|N|E|Z)" + 
				"([\\s|-]([0-9]{4})(SE|SW|NW|NE|E|S|W|N|E|Z)){0,}";
		Matcher mFull = Pattern.compile(regexFull).matcher(piece);
		boolean isMatchFull = mFull.find();
		if (isMatchFull) {
			String fullTS = mFull.group();
			// 匹配不上整个piece
			if (fullTS.length() != piece.length()) {
				MyLogger.error(yyyy_mm_dd + " : 雷暴录入错误1:" + fullTS);
			}
		}
		else {
			MyLogger.error(yyyy_mm_dd + " : 雷暴录入错误2:" + piece);
		}
		// 时间顺序不对
		Integer lastIndex = null;
		Matcher mTime = Pattern.compile("[0-9]{4}").matcher(piece);
		while (mTime.find()) {
			try {
				String hhmm = mTime.group();
				Integer nowIndex = CM.GetUTCMinutesOfDay(hhmm);
				if (lastIndex != null) {
					if (nowIndex != 0 && lastIndex > nowIndex) {
//						throw new Exception(yyyy_mm_dd + " : " + "TS时间错误:" + hhmm);
						MyLogger.error(yyyy_mm_dd + " : " + "TS时间错误:" + hhmm);
					}
				}
				lastIndex = nowIndex;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MyLogger.error(yyyy_mm_dd + " " + e.getMessage());
			}
		}
	}
	
	public static void main(String[]args) {
		String piece = "TS1117SE-1131SE 1210S-1241S ";
		piece = "TS1940NW-1848Z-1954N-1010NE 1046SW-1057SW ";
		try {
			check(piece, "20461231");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("msg:" + e.getMessage());
		}
	}

}
