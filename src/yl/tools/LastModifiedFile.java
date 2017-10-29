package yl.tools;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 一个文件夹内最新更新的文件
 * 
 * @Author Yang Lin
 * @Date 2017年9月28日
 * @Time 下午12:55:32
 */
public class LastModifiedFile {
	
	private static TreeMap<Long, String> tree;

	public static void main(String[] args) {
		tree = new TreeMap<Long, String>();
		String folderPath = ""; // 需要查找的文件夹
		folderPath = "E:/ws_jdk6/ftp_helper2/src/com/hgkj/ftp";
		folderPath = "E:/ws/ftp_helper2";
		int topN= 3; // 前N个
		File folder = new File(folderPath);
		seekFolder(folder);
		while (topN > 0 && tree.size() > 0) {
			Map.Entry<Long, String> e = tree.pollLastEntry();
			System.out.println(e.getValue() + ", " + new Date(e.getKey()));
			topN--;
		}
	}
	
	private static void seekFolder(File folder) {
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				if (!file.getName().endsWith(".java")) continue;
				long lm = file.lastModified();
				String absPath = file.getAbsolutePath();
				tree.put(lm, absPath);
			}
			else if (file.isDirectory()) {
				seekFolder(file);
			}
		}
	}

}
