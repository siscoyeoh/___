package yl.map;

import java.util.Date;
import java.util.TreeMap;

public class TreeMapDemo {

	public static void main(String[] args) {
		TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
		treeMap.put("A", "CHINA");
		treeMap.put("F", new Date());
		treeMap.put("B", 3.1415926F);
	}

}
