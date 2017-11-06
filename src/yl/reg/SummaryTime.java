package yl.reg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SummaryTime {

	public static void main(String[] args) throws IOException {
		File data = new File("C:/A1.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(data), "utf-8"));
		String line = null;
		while ((line = br.readLine()) != null) {
			match(line);
		}
		br.close();
		
	}
	
	static void match(String content) {
		String regex = "[^0-9][0-9][0-9][0-9][^0-9]"; // 3位时间
		String regex2 = "[(](0-9){1,2}[:](0-9){1,2}[)][0-9][0-9][0-9][^0-9]"; // 前面的括弧内不含:
		regex = "[0-9][:][^0-9]{1,6}[0-9]";
//		String content = "ff133gg";
		String temp = content.split("\t")[3];
		temp = temp.replace("-----", "-");
		temp = temp.replace("----", "-");
		temp = temp.replace("---", "-");
		if (temp.startsWith("-- ")) {
			temp = temp.replaceFirst("-- ", "");
		}
		if (temp.startsWith(" ")) {
			temp = temp.replaceFirst(" ", "");
		}
		if (temp.startsWith(" ")) {
			temp = temp.replaceFirst(" ", "");
		}
		if (temp.startsWith(" ")) {
			temp = temp.replaceFirst(" ", "");
		}
		if (temp.startsWith(" ")) {
			temp = temp.replaceFirst(" ", "");
		}
		if (temp.startsWith(" ")) {
			temp = temp.replaceFirst(" ", "");
		}
		if (temp.startsWith(" ")) {
			temp = temp.replaceFirst(" ", "");
		}
		if (temp.startsWith("--") && 
				!(temp.startsWith("--RA") || 
						temp.startsWith("--SHRA") ||
						temp.startsWith("--SN"))) {
			// —
			System.out.println(content); // f＿－－—－　—
		}
//		System.out.println("—".equals("—"));
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(content);
//		if (matcher.find()) {
//			String r = matcher.group();
////			System.out.println(matcher.groupCount());
//			if (!r.endsWith(";")) {
//				System.out.println(r);
//			}
//		}
//		else {
//			
//		}
	}

}
