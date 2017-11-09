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

public class MatchDatFileLine2 {
	
	static String path = "C:/Users/Administrator/Desktop/拉萨数据挑错/2014-2016";
	
	public static void main(String[] args) {
		String phenomena = "BLSA2350-0110 SA0150-0220 0840-1020 1310-1345 -RA1425-1425 TS1503W-1505SW-1511NW-1516N-";
		
		System.out.println();
//		matchLine("");
		List<String> phenomenaList = 拆分演变字符串为不同的天气现象(phenomena);
		初始化heaps();
		把天气现象分类(phenomenaList);
		for (int i = 0; i < flags.length; i++) {
			String flag = flags[i];
			List<String> 一个现象的多个碎片 = heaps.get(flag);
			System.out.println(flag + " : " + CM.List2String2(一个现象的多个碎片));
		}
//		boolean jiangshui = 属于降水(phenomena);
//		System.out.println("jiangshui:" + jiangshui);
		
//		try {
//			endwith_();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String line = "2014-05-22	0	0	26.5	11.1	GA1037-1047 17(1037)210 1130-1251 19(1152)180 1306-19(1309)180";
//		System.out.println(StringUtils.indexOf(line, "\t", 0));
//		System.out.println(StringUtils.indexOf(line, "\t", 1));
//		System.out.println(StringUtils.indexOf(line, "\t", 2));
//		System.out.println(StringUtils.indexOf(line, "\t", 11));
//		System.out.println(CM.GetMatchIndex(line, "\t", 3));
//		int startIndex = CM.GetMatchIndex(line, "\t", 5);
//		System.out.println(line.substring(startIndex + 1, line.length()));
//		String s = "\t\t\tddd";
//		System.out.println(s.trim());
	}

	static Map<String, List<String>> heaps;
	private static void 初始化heaps() {
		heaps = new HashMap<String, List<String>>();
		for (int i = 0; i < flags.length; i++) {
			heaps.put(flags[i], new ArrayList<String>());
		}
	}
	private static void 把天气现象分类(List<String> phenomenaList) {
		for (int i = 0; i < phenomenaList.size(); i++) {
			try {
				String phenomena = phenomenaList.get(i);
				int flagIndex = 属于哪个天气现象(phenomena);
				heaps.get(flags[flagIndex]).add(phenomena);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void endwith_() throws IOException {
		File folder = new File(path);
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isFile()) {
				System.out.println("---------------- match file:" + file.getAbsolutePath());
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					matchLine(line);
				}
				br.close();
			}
		}
	}

	/**
	 * 1. 括号不配对的
	 * 2. -没有结束时间(-后是个空格、或\t)
	 * 3. TS最后结尾不能使-(TS1503W-1505SW-1511NW-1516N 或 TS1503W 1505SW-1511NW-1516N为正确, 时间后面必须有方位,
	 * 		方位最多两个大写字母)
	 * 4. 根据观测规范附表12: 同一天气现象必须写到一块
	 * 5. 演变大字符串开始的中文横杠: 观测规范<第五章 - 第三节 - 第一百零一条 第九目>
	 * 6. 纪要栏包含中文: 抛出异常
	 * 7. 降水量: 保留一位小数
	 * @throws IOException 
	 * 
	 */
	public static void matchLine(String line) {
		line = "2014-07-31	9.8	0	19.3	11.1	-SHRA1600-RA1630-1830-1940 DZ0425-0435-SHRA0454-0603 -SHRA1149-RA1319-1343 1416-1545 TS1149W-1154W-1156W-1157W-1201W-1204W-1207W-1208W-1212W-1214NW-1216NW-1221NW-1223Z-1225Z-1225N-1230Z-1233Z-1237N-1238NE-1239E-1243E-1250E-1252E-1255E-1304E-1310E 		";
		line = "2016-06-03	0	0	24.6	3.9	SA1345-1406 -SHRA1345-1410(0.0)1410 1455-1533(0.0)1533 GA1400 17(1400)260	1145自动观测数据出现缺失，1200发报软件出现故障，导致1200整点报文未按规定发出，造成缺测；于1218补发1200实况报；1200,1300,1400,1500实况报文采集备用观测数据；1518,27号，09号自观数据恢复，中间数据未恢复。 	";
		line = "2016-06-30	3.8		20.7	12.2	—-SHRA1940-2006(1.4)2007 SHRA0056-0237--SHRA0319-0347(2.0)0352 1438-1531(0.2)1531 TS1425NW-1444SE-1446Z-1452SE-1454NW-1508S-1521SW		";
		line = "2014-07-02	5.4	0	23.3	11.1	-SHRA0232-0312 SHRA1154-1201--SHRA1319-SHRA1338--SHRA1351-1435 TS0936N-0946N 1116N-1120NE-1129N-1135S-1138N-1140S-1141S-1148NW-1151N-1152S-1201W-1212Z-1220Z-1223S-1226SE-1230W-1237S-1241Z-1247N-1258Z-1313NW-1331NW-1407W-1421W-1431NW SHGS1208-SHGS1210-+SHGR1212--SHGR1215-1219	冰雹最大直径11MM,重量5克.	";
		String phe = "-SHRA1600-RA1630-1830-1940 DZ0425-0435-SHRA0454-0603 -SHRA1149-RA1319-1343 1416-1545 TS1149W-1154W-1156W-1157W-1201W-1204W-1207W-1208W-1212W-1214NW-1216NW-1221NW-1223Z-1225Z-1225N-1230Z-1233Z-1237N-1238NE-1239E-1243E-1250E-1252E-1255E-1304E-1310E 		";
		String ts = "TS1149W-1154W-1156W-1157W-1201W-1204W-1207W-1208W-1212W-1214NW-1216NW-1221NW-1223Z-1225Z-1225N-1230Z-1233Z-1237N-1238NE-1239E-1243E-1250E-1252E-1255E-1304E-1310E 		";
		// 拿到天气现象演变字符串
//		String phenomena = line.substring(CM.GetMatchIndex(line, "\t", 5) + 1, line.length());
		String phenomena = line.substring(CM.GetMatchIndex(line, "\t", 5) + 1, CM.GetMatchIndex(line, "\t", 6));
		if (StringUtils.isNotEmpty(phenomena.trim())) {
			System.out.println("<" + line + ">");
			System.out.println("phenomena:<" + phenomena + ">");
			// 把演变字符串拆除不同的"天气现象"
			String[] elements = phenomena.split("(([+]{0,2})|([-]{0,2}))([A-Z]{2,6})");
			System.out.println("-----------------------------------------------------------");
			for (int i = 0; i < elements.length; i++) {
				System.out.println(elements[i]);
			}
		}
	}
	
	public static List<String> 拆分演变字符串为不同的天气现象(String phenomenas) {
//		phenomenas = "—-SHRA1940-2006(1.4)2007 SHRA0056-0237--SHRA0319-0347(2.0)0352 1438-1531(0.2)1531 TS1425NW-1444SE-1446Z-1452SE-1454NW-1508S-1521SW";
//		phenomenas = "-SHRA0232-0312 SHRA1154-1201--SHRA1319-SHRA1338--SHRA1351-1435 TS0936N-0946N 1116N-1120NE-1129N-1135S-1138N-1140S-1141S-1148NW-1151N-1152S-1201W-1212Z-1220Z-1223S-1226SE-1230W-1237S-1241Z-1247N-1258Z-1313NW-1331NW-1407W-1421W-1431NW SHGS1208-SHGS1210-+SHGR1212--SHGR1215-1219";
//		System.out.println("phenomena.substring(232):" + phenomenas.substring(232));
		int index = -1;
		String regex = "(([+]{0,2})|([-]{0,2}))([A-Z!(W|NW|N|NE|E|SE|S|SW|Z)]{2,6})";
//		regex = "(([+]{0,2})|([-]{0,2}))([A-Z)]{2,6})^W^NW^N^NE^E^SE^S^SW^Z";
//		regex = "(([+]{0,2})|([-]{0,2}))([A-Z)]{2,6})&(!W!NW!N!NE!E!SE!S!SW!Z)";
//		regex = "(([+]{0,2})|([-]{0,2}))[0-9]{0}([A-Z)]{2,6})";
//		regex = "(([+]{0,2})|([-]{0,2}))(([A-Z]{2,6})+(^NW))";
//		regex = "((([+]{0,2})|([-]{0,2}))([A-Z]{2,6}))";
//		regex = "((([+]{0,2})|([-]{0,2}))([A-Z]{2,6}))|[^NW]";
//		regex = "(([+]{0,2})|([-]{0,2}))[^0-9]([A-Z]{2,6})";
		regex = "[\\s](([+]{0,2})|([-]{0,2}))([A-Z]{2,6})";
//		String[] elements = phenomena.split(regex);
		System.out.println("******************************************");
//		for (int i = 0; i < elements.length; i++) {
//			System.out.println(elements[i]);
//		}
//		String header = phenomena.substring(0, phenomena.indexOf(' '));
//		System.out.println("header:" + header);
//		phenomena = phenomena.substring(phenomena.indexOf(' '));
//		// SHRA0056-0237--SHRA0319-0347(2.0)0352 1438-1531(0.2)1531 TS1425NW-1444SE-1446Z-1452SE-1454NW-1508S-1521SW
//		System.out.println("phenomena:" + phenomena);
		
		regex = "[\\s](([+]{0,2})|([-]{0,2}))([A-Z]{2,6})";
		List<Integer> matchIndexes = CM.RegexMatchIndexes(regex, phenomenas);
		if (matchIndexes.get(0) != 0) {
			matchIndexes.add(0, 0);
		}
		matchIndexes.add(phenomenas.length());
		System.out.println("List2String:" + CM.List2String(matchIndexes));
		List<String> phenomenaList = new ArrayList<String>();
		System.out.println("####################################################");
		for (int i = 0; i < matchIndexes.size() - 1; i++) {
			int begin = matchIndexes.get(i);
			int end = matchIndexes.get(i + 1);
			String phenomena = phenomenas.substring(begin, end);
			System.out.println(phenomena.startsWith(" "));
			if (phenomena.startsWith(" ")) {
				phenomena = phenomena.substring(1, phenomena.length());
			}
			phenomenaList.add(phenomena);
		}
		System.out.println("####################################################");
		for (int i = 0; i < phenomenaList.size(); i++) {
			System.out.println(phenomenaList.get(i));
		}
		return phenomenaList;
	}
	
	public static void notContain() {
		String content = "TS1425NW-14hede44SE";
//		content = "hede";
		String regex = "";
		regex = "NW";
		regex = "((?!hede).)*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		System.out.println(matcher.matches());
	}
	
	static String[] words降水 = { "RA", "SHRA", "DZ", "FZRA", "FZDZ", "SN", "SHSN", "SHGS", "SG", "RASN", "SNRA", "SHRASN", "SHSNRA", "PL", "SHGR", "SHGS", "IC" };
	static String[] words烟尘 = { "FU", "DU", "HZ", "VA"};
	static String[] words吹雪 = { "BLSN", "DRSN"};
	static String[] words雷电 = { "TS"};
	static String[] words雾 = { "BR", "MIFG", "FG", "FZFG", "BCFG", "PRFG" };
	static String[] words风沙 = { "SA", "BLSA", "DRSA", "BLDU", "DRDU", "SS", "DS" };
	static String[] words风暴 = { "GA", "SQ", "FC", "PO" };
	static String[] words地面凝结 = { "FR", "RI", "VG" };
	static String[] words积雪 = { "PS"};
	static String[][] words = { words降水, words烟尘, words吹雪, words雷电, words雾, words风沙, words风暴, words地面凝结, words积雪};
	static String[] flags = {"降水", 		"烟尘", 		"吹雪", "雷电", 		"雾", 	"风沙", 		"风暴", 	"地面凝结", 		"积雪" };
	
	public static int 属于哪个天气现象(String content) throws Exception {
		int 天气现象索引 = -1;
		content = "—-SHRA1940-2006(1.4)2007";
		for (int i = 0; i < words.length; i++) {
			String flag天气现象 = flags[i];
			for (int j = 0; j < words[i].length; j++) {
				if (content.contains(words[i][j])) {
					// 已经匹配到天气现象、且不是当前组的天气现象(即: 一个content包含了多个天气现象组)
					if (天气现象索引 != -1 && !flags[天气现象索引].equals(flag天气现象)) {
						throw new Exception("包含了多个天气现象天气现象:" + content);
					}
					天气现象索引 = i;
				}
			}
		}
		if (天气现象索引 == -1) throw new Exception("不属于任何天气现象:" + content);
		return 天气现象索引;
	}
	
	public static void 是否交叉() {
		
		for (int i = 0; i < words.length - 1; i++) {
			String[] arr0 = words[i];
			String[] arr1 = words[i + 1];
			是否交叉(arr0, arr1);
		}
	}
	
	public static void 是否交叉(String[] arr0, String[] arr1) {
		for (int i = 0; i < arr0.length; i++) {
			String word0 = arr0[i];
			for (int j = 0; j < arr1.length; j++) {
				String word1 = arr1[j];
				if (word0.contains(word1) || word1.contains(word0)) {
					System.out.println("交叉:" + word0 + ", " + word1);
				}
			}
		}
	}

}
