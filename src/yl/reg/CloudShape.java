package yl.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CloudShape {
	
	public static void main(String[]a) {
		String cloud = "0AC 5000";
//		cloud = cloud.substring(1, cloud.length());
//		System.out.println(cloud);
		Pattern pattern = Pattern.compile("^[0-8][A-Z]{1}");
		Matcher matcher = pattern.matcher(cloud);
		if (matcher.find()) {
//			String result = matcher.group();
//			System.out.println(result);
			System.out.println(cloud.replaceAll("[0-9]{1,2}[A-Z]{2,3}", "").trim());
		}
		
	}

}
