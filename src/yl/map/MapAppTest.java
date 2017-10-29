package yl.map;

import java.util.HashMap;    
import java.util.Iterator;    
import java.util.LinkedHashMap;    
import java.util.Map;    
import java.util.TreeMap;     
public class MapAppTest {    
/**  
* @Create on Nov 9, 2009 by lrm  
*/    
public static void main(String[] args) {    
   // TODO Auto-generated method stub    
   MapAppTest.noOrder();    
   MapAppTest.hasOrder();    
   MapAppTest.likedHashMap();    
}     
public static void noOrder() {    
   System.out.println("------����������------");    
   Map map = new HashMap();    
   map.put("1", "Level 1");    
   map.put("2", "Level 2");    
   map.put("3", "Level 3");    
   map.put("4", "Level 4");    
   map.put("F", "Level F");    
   map.put("Q", "Level Q");    
   Iterator it = map.entrySet().iterator();    
   while (it.hasNext()) {    
    Map.Entry e = (Map.Entry) it.next();    
    System.out.println("Key: " + e.getKey() + ";   Value: "    
      + e.getValue());    
   }    
}     
// ����(Ĭ�����򣬲���ָ��)    
public static void hasOrder() {    
   System.out.println("------���򣨵��ǰ�Ĭ��˳�䣬����ָ����------");    
   Map map = new TreeMap();    
   map.put("F", "Level F");    
   map.put("7", "Level 1");    
   map.put("8", "Level 2");    
   map.put("4", "Level 3");    
   map.put("4", "Level 4");    
   map.put("Q", "Level Q");    
   map.put("E", "Level E");    
   Iterator it = map.entrySet().iterator();    
   while (it.hasNext()) {    
    Map.Entry e = (Map.Entry) it.next();    
    System.out.println("Key: " + e.getKey() + ";   Value: "    
      + e.getValue());    
   }    
}     
public static void likedHashMap() {    
   System.out.println("------���򣨸��������˳�������------");    
   Map map = new LinkedHashMap();    
   map.put("F", "Level F");    
   map.put("7", "Level 1");    
   map.put("8", "Level 2");    
   map.put("4", "Level 3");    
   map.put("4", "Level 4");    
   map.put("Q", "Level Q");    
   map.put("E", "Level E");    
   Iterator it = map.entrySet().iterator();    
   while (it.hasNext()) {    
    Map.Entry e = (Map.Entry) it.next();    
    System.out.println("Key: " + e.getKey() + ";   Value: "    
      + e.getValue());    
   }    
}     
}     