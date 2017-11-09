package yl.ftp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaxFileCountOneTime {

	public static void main(String[] args) throws IOException {
		String pathname = "C:/Users/Administrator/Desktop/netmg/成都日志/log.log.2017-11-06";
		File log = new File(pathname);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(log), "utf-8"));
		String line = null;
		int maxCount = 0;
		String maxDate = null;
		while((line = br.readLine()) != null) {
			Matcher m = Pattern.compile("upload success, success count:[0-9]{1,8},").matcher(line);
			if (m.find()) {
				String date = line.substring(2, 21);
				String group = m.group();
				Matcher m1 = Pattern.compile("[0-9]{1,8}").matcher(group);
				m1.find();
				int count = Integer.parseInt(m1.group());
				if (count > maxCount) {
					maxCount = count;
					maxDate = date;
				}
			}
		}
		System.out.println("maxCount:" + maxCount);
		System.out.println("maxDate:" + maxDate);
		br.close();
	}
	
	

}
