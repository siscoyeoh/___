package yl.map;

import java.util.Date;
import java.util.LinkedHashMap;

public class LinkedHashMapDemo {

	public static void main(String[] args) {
		LinkedHashMap<String, Object> linkedMap = new LinkedHashMap<String, Object>();
		linkedMap.put("A", "CHINA");
		linkedMap.put("F", new Date());
		linkedMap.put("B", 3.1415926F);
	}

}
