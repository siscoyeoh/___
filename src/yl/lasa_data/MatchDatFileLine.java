package yl.lasa_data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import yl.CM;
import yl.lasa_data.check.Dafeng;
import yl.lasa_data.check.Jiangshui;
import yl.lasa_data.check.Leibao;
import yl.log4j.MyLogger;

public class MatchDatFileLine {
	
//	static String path = "C:/Users/Administrator/Desktop/拉萨数据挑错/2014-2016";
//	static String path = "C:/Users/Administrator/Desktop/拉萨数据挑错/20171107(3)/20171106";
//	static String path = "C:/Users/Administrator/Desktop/拉萨数据挑错/20171108/20171106";
//	static String path = "C:/Users/Administrator/Desktop/拉萨数据挑错/未改之前数据及报错/日记录/日记录";
//	static String path = "C:/Users/Administrator/Desktop/拉萨数据挑错/20171109(2)/20171108";
	static String path = "C:\\Users\\Administrator\\Desktop\\extract_msg\\data\\day";
	
	static String[] words降水 = { 
		"RA", "SHRA", "DZ", "FZRA", "FZDZ", 
		"SN", "SHSN", "SHGS", "SG", "RASN",
		"SNRA", "SHRASN", "SHSNRA", "PL", "SHGR",
		"SHGS", "IC" };
	static String[] words烟尘 = { "FU", "DU", "HZ", "VA"};
	static String[] words吹雪 = { "BLSN", "DRSN"};
	static String[] words雷暴 = { "TS"};
	static String[] words雾 = { "BR", "MIFG", "FG", "FZFG", "BCFG", "PRFG" };
	static String[] words风沙 = { "SA", "BLSA", "DRSA", "BLDU", "DRDU", "SS", "DS" };
	static String[] words风暴 = { "GA", "SQ", "FC", "PO" };
	static String[] words地面凝结 = { "FR", "RI", "VG" };
	static String[] words积雪 = { "PS"};
	static String[] xxxxx大风 = { "GA"};
	static String[][] words = { words降水, words烟尘, words吹雪, words雷暴, words雾, words风沙, words风暴, words地面凝结, words积雪, xxxxx大风 };
	static String[] flags = {"降水", "烟尘", "吹雪", "雷暴", "雾", "风沙", "风暴", "地面凝结", "积雪", "大风" };
	/** 匹配索引天气现象简字的字符串, 可用方法allPhenomenaWords()生成(手动或自动) */
	static String allPhenomenaWords = "RA|SHRA|DZ|FZRA|FZDZ|SN|SHSN|SHGS|SG|RASN|SNRA|SHRASN|SHSNRA|PL|SHGR|SHGS|IC|FU|DU|HZ|VA|BLSN|DRSN|TS|BR|MIFG|FG|FZFG|BCFG|PRFG|SA|BLSA|DRSA|BLDU|DRDU|SS|DS|GA|SQ|FC|PO|FR|RI|VG|PS|GA";
	
	public static void main(String[] args) throws IOException {
		MyLogger.clear();
		
		handleAll();
		
//		String line = "2011-05-11	0	0	19.3	5.8	TS0738(SE)-0749(WS) -SHRA0748-0805 		";
//		handleOneDay(line);
		
//		String phenomenas = " VCSH2352-0005 -RA1955-2010 2254-2306 VCSH2352-0005 SHRA1337-1355--SHRA1413 TS1311SW-1316E-1325NE-1333E-1345S-1354Z-1403N-1412Z		";
//		String regex = "[\\s][—]{0,1}(([+]{0,2})|([-]{0,2}))([A-Z]{2,6})";
//		String allPhenomenaWords = "RA|SHRA|DZ|FZRA|FZDZ|SN|SHSN|SHGS|SG|RASN|SNRA|SHRASN|SHSNRA|PL|SHGR|SHGS|IC|FU|DU|HZ|VA|BLSN|DRSN|TS|BR|MIFG|FG|FZFG|BCFG|PRFG|SA|BLSA|DRSA|BLDU|DRDU|SS|DS|GA|SQ|FC|PO|FR|RI|VG|PS|GA";
//		regex = "[\\s][—]{0,1}(([+]{0,2})|([-]{0,2}))(" + allPhenomenaWords + ")";
//		List<String> pieces = splitDifferentPhenomena2(phenomenas);
//		System.out.println("------------------------");
//		for (int i = 0; i < pieces.size(); i++) {
//			System.out.println(pieces.get(i));
//		}
		
	}
	
	private static void handleAll() throws IOException {
		File folder = new File(path);
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isFile()) {
//				System.out.println("---------------- match file:" + file.getAbsolutePath());
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					if (line.contains("日降水量") && line.contains("日最大积雪深度")) {
						continue;
					}
					handleOneDay(line);
				}
				br.close();
			}
		}
	}
	
	static String yyyy_mm_dd;
	private static void handleOneDay(String line) {
		yyyy_mm_dd = line.substring(0, 10);
		try {
//			System.out.println("yyyy_mm_dd:<" + yyyy_mm_dd + ">");
			if (StringUtils.countMatches(line, "\t") != 7) 
				throw new Exception(yyyy_mm_dd + " 数据格式错误1(\\t错误)!");
			int start = CM.GetMatchIndex(line, "\t", 5);
			int end = CM.GetMatchIndex(line, "\t", 6);
			String phenomena = line.substring(start + 1, end);
			if (StringUtils.isEmpty(phenomena.trim())) return;
//			if (CM.ContainCN(phenomena)) {
//				throw new Exception(yyyy_mm_dd + ": 包含中文");
//			}
			handleOnePhenomena(phenomena);
		} catch (Exception e) {
			e.printStackTrace();
			MyLogger.error("" + yyyy_mm_dd + " " + e.getMessage());
		}
	}
	
	private static void handleOnePhenomena(String phenomena) throws Exception {
		List<String> phenomenaList = splitDifferentPhenomena2(phenomena);
		initialHeaps();
		heapDifferentPhenomena(phenomenaList);
		for (int i = 0; i < flags.length; i++) {
			String flag = flags[i];
			List<String> piecesOfOnePhenomena = heapsOneDay.get(flag);
			checkIllegalRecord(flag, piecesOfOnePhenomena);
		}
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------
	// --------------------------------------------------------------- 以下为针对具体的天气现象, 根据不同的记录规则(规范), 匹配记录是否合法
	// ---------------------------------------------------------------
	// ---------------------------------------------------------------
	private static void checkIllegalRecord(String flag,
			List<String> piecesOfOnePhenomena) {
		if (flag.equals("降水")) {
			check降水(piecesOfOnePhenomena);
		}
		else if(flag.equals("雷暴")) {
			check雷暴(piecesOfOnePhenomena);
		}
		else if(flag.equals("大风")) {
			check大风(piecesOfOnePhenomena);
		}
	}
	
	/**
	 * 
	 */
	private static void check降水(List<String> piecesOfOnePhenomena) {
		for (int i = 0; i < piecesOfOnePhenomena.size(); i++) {
			String piece = piecesOfOnePhenomena.get(i);
//			System.out.println("降水:" + piece);
			try {
				Jiangshui.check(piece, yyyy_mm_dd);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MyLogger.error(yyyy_mm_dd + ":" + e.getMessage());
			}
			
		}
	}
	
	private static void check雷暴(List<String> piecesOfOnePhenomena) {
		for (int i = 0; i < piecesOfOnePhenomena.size(); i++) {
			String piece = piecesOfOnePhenomena.get(i);
//			System.out.println("降水:" + piece);
			try {
				Leibao.check(piece, yyyy_mm_dd);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MyLogger.error(yyyy_mm_dd + ":" + e.getMessage());
			}
			
		}
	}
	
	private static void check大风(List<String> piecesOfOnePhenomena) {
		for (int i = 0; i < piecesOfOnePhenomena.size(); i++) {
			String piece = piecesOfOnePhenomena.get(i);
//			System.out.println("降水:" + piece);
			try {
				Dafeng.check(piece, yyyy_mm_dd);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MyLogger.error(yyyy_mm_dd + ":" + e.getMessage());
			}
			
		}
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------
	// --------------------------------------------------------------- 以下为初步拆解、匹配天气现象
	// ---------------------------------------------------------------
	// ---------------------------------------------------------------
	static Map<String, List<String>> heapsOneDay;
	private static void initialHeaps() {
		heapsOneDay = new HashMap<String, List<String>>();
		for (int i = 0; i < flags.length; i++) {
			heapsOneDay.put(flags[i], new ArrayList<String>());
		}
	}
	
	/** 根据不同的现象组, 进行"分堆" */
	private static void heapDifferentPhenomena(List<String> phenomenaList) throws Exception {
		for (int i = 0; i < phenomenaList.size(); i++) {
			String phenomena = phenomenaList.get(i);
			int flagIndex = getPhenomenaIndex(phenomena);
			heapsOneDay.get(flags[flagIndex]).add(phenomena);
		}
	}
	
	/** 把不同的天气现象记录split开 
	 * @throws Exception */
//	public static List<String> splitDifferentPhenomena(String phenomenas) {
//		String regex = "[\\s][—]{0,1}(([+]{0,2})|([-]{0,2}))([A-Z]{2,6})";
//		List<Integer> matchIndexes = CM.RegexMatchIndexes(regex, " " + phenomenas); // 开始加一个空格
//		if (matchIndexes.get(0) != 0) {
//			matchIndexes.add(0, 0);
//		}
//		matchIndexes.add(phenomenas.length());
//		List<String> phenomenaList = new ArrayList<String>();
//		for (int i = 0; i < matchIndexes.size() - 1; i++) {
//			int begin = matchIndexes.get(i);
//			int end = matchIndexes.get(i + 1);
//			String phenomena = phenomenas.substring(begin, end);
//			if (phenomena.startsWith(" ")) {
//				phenomena = phenomena.substring(1, phenomena.length());
//			}
//			phenomenaList.add(phenomena);
//		}
//		return phenomenaList;
//	}
	public static List<String> splitDifferentPhenomena2(String phenomenas) throws Exception {
		String regex = "[\\s][—]{0,1}(([+]{0,2})|([-]{0,2}))(" + allPhenomenaWords + ")";
		List<Integer> matchIndexes = CM.RegexMatchIndexes(regex, " " + phenomenas); // 开始加一个空格
		if (matchIndexes.size() == 0) throw new Exception(yyyy_mm_dd + " 未匹配到天气现象:<" + phenomenas + ">");
		if (matchIndexes.get(0) != 0) {
//			matchIndexes.add(0, 0);
			// 第一个匹配索引不是0, 重新指向0(把第一个现象之前的内容分配给第一个现象)
			matchIndexes.set(0, 0);
		}
		matchIndexes.add(phenomenas.length());
		List<String> phenomenaList = new ArrayList<String>();
		for (int i = 0; i < matchIndexes.size() - 1; i++) {
			int begin = matchIndexes.get(i);
			int end = matchIndexes.get(i + 1);
			String phenomena = phenomenas.substring(begin, end);
			if (phenomena.startsWith(" ")) {
				phenomena = phenomena.substring(1, phenomena.length());
			}
			phenomenaList.add(phenomena);
		}
		return phenomenaList;
	}
	
	/** 拿到天气现象的索引 */
	public static int getPhenomenaIndex(String phenomenaPiece) throws Exception {
		int phenomenaIndex = -1;
		for (int i = 0; i < words.length; i++) {
			String flag天气现象 = flags[i];
			for (int j = 0; j < words[i].length; j++) {
				// 拿到天气现象碎片的第一个简字, 据此判断该碎片属于哪个组
				String phenomenaFlag = matchPhenomenaFirstWord(phenomenaPiece);
				if (words[i][j].equals(phenomenaFlag)) {
					// 已经匹配到天气现象、且不是当前组的天气现象(即: 一个content包含了多个天气现象组)
//					if (phenomenaIndex != -1 && !flags[phenomenaIndex].equals(flag天气现象)) {
//						throw new Exception("包含了多个天气现象天气现象: " + phenomenaPiece);
//					}
					phenomenaIndex = i;
				}
			}
		}
		if (phenomenaIndex == -1) throw new Exception(yyyy_mm_dd + " 不能识别天气现象2: " + phenomenaPiece);
		return phenomenaIndex;
	}
	
	/** 匹配一个现象碎片的第一个简字 */
	private static String matchPhenomenaFirstWord(String phenomenaPiece) throws Exception {
		Pattern pattern = Pattern.compile("(([+]{0,2})|([-]{0,2}))(" + allPhenomenaWords + ")");
		Matcher matcher = pattern.matcher(phenomenaPiece);
		if (matcher.find()) {
			String phenomenaFlag = matcher.group();
			// 留下纯大写字母
			return phenomenaFlag.replaceAll("[^A-Z]", "");
		}
		System.out.println("phenomenaPiece:" + phenomenaPiece);
		throw new Exception(yyyy_mm_dd + " 不能识别天气现象3: " + phenomenaPiece);
	}
	
	/** 生成索引天气现象的匹配 */
	public static void allPhenomenaWords() {
		String all = "";
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length; j++) {
				all += words[i][j] + "|";
			}
		}
		System.out.println(all);
	}
	
	
	
	

	
	public static void readAllLine() throws IOException {
		File folder = new File(path);
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isFile()) {
//				System.out.println("---------------- match file:" + file.getAbsolutePath());
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					if (line.contains("日降水量") && line.contains("日最大积雪深度")) {
						continue;
					}
					if (line.contains("—")) System.out.println(line);
				}
				br.close();
			}
		}
	}

	/** 修改"大风"的时间点: 如,0813 19(0740)260 应修改为0813 19(0813)260 */
	public static void correctTimePoint() {
		
	}
}
