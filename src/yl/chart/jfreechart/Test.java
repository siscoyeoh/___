package yl.chart.jfreechart;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		File folder = new File("C:\\Users\\Administrator\\Desktop\\__\\demoͼƬ");
		File[] files = folder.listFiles();
		List<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i <= 262; i++) {
			indexes.add(i);
		}
		for (int i = 0; i < indexes.size(); i++) {
			System.out.println(indexes.get(i));
		}
		for (int i = 0; i < files.length; i++) {
			String fileName = files[i].getName();
			if (fileName.endsWith("png")) {
				int index = Integer.parseInt(fileName.split("[-]")[0].trim());
//				System.out.println(index);
//				System.out.println(!indexes.contains(index));
//				if (!indexes.contains(new Integer(index))) {
//					System.out.println(index);
//				}
				indexes.remove(new Integer(index));
			}
		}
		System.out.println("--------------");
		for (int i = 0; i < indexes.size(); i++) {
			System.out.println(indexes.get(i) + ",");
		}
	}

}
