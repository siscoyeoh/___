package yl.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CheckFtpLog {

	public static void main(String[] args) throws IOException {
		
		String folderPath = "C:/Users/Administrator/Desktop/������Ŀ��չ/ftp���� - �̰߳�/�ֳ���־�鿴-----/����/20170929";
		String fileName = "log.log";
		File log = new File(folderPath + File.separator + fileName);
		BufferedReader br = new BufferedReader(new FileReader(log));
		String line = null;
		while ((line = br.readLine()) != null) {
			// ��������ɸѡ
			if (line.contains("106.37.211.233") && line.contains("milliseconds")) {
				System.out.println(line);
			}
		}
		
	}

}
