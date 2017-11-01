package yl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;


public class TestMain {
	public static void main(String[] args) {
		
		
//		MapUtils.
		
		
//		String str = "kkk;lll;;;ooo;";
//		String[] arr = str.split(";", 0);
//		System.out.println(arr.length);
		
//		String sql = "SELECT A.TABLESPACE_NAME, TOTAL, FREE, TOTAL-FREE AS USED FROM \n" +
//				"(SELECT TABLESPACE_NAME, SUM(BYTES)/1024/1024 AS TOTAL FROM DBA_DATA_FILES GROUP BY TABLESPACE_NAME) A, \n" +
//				"(SELECT TABLESPACE_NAME, SUM(BYTES)/1024/1024 AS FREE FROM DBA_FREE_SPACE GROUP BY TABLESPACE_NAME) B\n" +
//				"WHERE A.TABLESPACE_NAME = B.TABLESPACE_NAME";
//		System.out.println(sql);
		
//		Class<?> flag = String.class;
//		setFlag(flag);
		
//		System.out.println("--------");
//		TabName tn = TabName.SysMonitor;
//		System.out.println(tn); // SysMonitor
//		System.out.println(tn.name()); // SysMonitor
//		System.out.println(tn.getDescription()); // 运行监控
//		System.out.println(tn.getTabIndex()); // 0
//		System.out.println(tn.toString()); // SysMonitor
//		System.out.println("--------");
		
//		List<Integer> list = new ArrayList<Integer>();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		int[] ii = new int[] { 1, 2, 3 };
//		try {
//			// java.lang.IndexOutOfBoundsException: Index: 3, Size: 3
//			System.out.println(list.get(3));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			// java.lang.ArrayIndexOutOfBoundsException: 3
//			System.out.println(ii[3]);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		Integer a = 20;
//		Integer b = 5;
//		for (int i = 0; i < 10; i++) {
//			try {
//				int c = a / (b - i);
//				System.out.println(i);
//			} 
////			catch (Exception e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//			finally {
//				System.out.println("A");
//			}
//		}
		
//		historyDiskUsageRate();
		
//		Calendar now = Calendar.getInstance();
//		for (int ii = 1; ii <= 12; ii++) {
//			now.add(Calendar.MONTH, -1);
//			System.out.println(now.getTimeInMillis());
//			System.out.println(new Date(now.getTimeInMillis()) + "\n");
//		}
		
//		long sleep = 1 * 24 * 60 * 60 * 1000L;
//		Date date0 = new Date();
//		Date date1 = new Date(date0.getTime() + sleep);
//		System.out.println(date0);
//		System.out.println(date1);
		
//		while (true) {
//			System.out.println(0);
//		}

//		double value = 100.0;
//		for (int i = 0; i < 4000; i++) {
//			try {
//				value = value + Math.random() - 0.5;
//				System.out.println("value:" + value);
//				System.out.println("new Double(value):" + new Double(value));
//			} catch (Exception e) {
//				System.err.println("Error adding to series");
//			}
////			System.out.println(Math.random());
//		}
//		System.out.println(new Date().getTime());
//		System.out.println(new Date(1505627376795L));
//		System.out.println(25170178 * 100);
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(4/5);
//		double pi = Math.PI;
//		double r = Math.random();
//		CM cm = new CM();
//		String cmName = cm.getClass().getSimpleName();
//		System.out.println(cmName);
		
//		System.out.println((Long.MAX_VALUE + "").length());
//		try
//		{ 
//		System.out.println("本机的IP = <" + InetAddress.getLocalHost().getHostAddress() + ">");
//		} catch (UnknownHostException e)
//		{ 
//		e.printStackTrace();
//		}
//		intOrInteger(1);
//		intOrInteger(new Integer(1));
		
//		File folder = new File("F:\\yl\\ftpfolder\\copyfiles");
//		File[] files = folder.listFiles();
//		for (File file : files) {
//			if (file.getName().length() != 20) {
//				System.out.println(file.getName());
//			}
//		}
		
//		int i = 1;
//		i--;
//		System.out.println(i);
		
//		for (int i = 100; i < 600; i++) {
//			System.out.println("<filter suffix=\"." + i + "\" needToUpload=\"1\"/>");
//		}
		
//		String ftpHost = "192.168.8.44";
//		String port = "21";
//		String username = "ftpuser";
//		String password = "ftpuser";
//		FTPClient client = FTPUtils.GetFTPClient(ftpHost, port, username, password);
//		try {
//			boolean chgDir = client.changeWorkingDirectory("copyfiles");
//			System.out.println("chgDir:" + chgDir);
//			chgDir = client.changeWorkingDirectory("copyfiles");
//			System.out.println("chgDir:" + chgDir);
//			chgDir = client.changeWorkingDirectory("copyfiles");
//			System.out.println("chgDir:" + chgDir);
//			chgDir = client.changeWorkingDirectory("copyfiles");
//			System.out.println("chgDir:" + chgDir);
//			chgDir = client.changeWorkingDirectory("copyfiles");
//			System.out.println("chgDir:" + chgDir);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
	
//	public static void intOrInteger(int i) {
//		System.out.println("int");
//	}
	
	public static void historyDiskUsageRate() {
		long[] ms = { 
				1505531350515L, 1502852950515L, 1500174550515L, 1497582550515L,
				1494904150515L, 1492312150515L, 1489633750515L, 1487214550515L,
				1484536150515L, 1481857750515L, 1479265750515L, 1476587350515L
				};
		int r0 = 70, r1 = 80, r2 = 90; // , r3 = 98;
		for (int index = 0; index < ms.length; index++) {
			
			double step = Math.random() * 5;
			r0 -= (int) (step) < 0 ? 0 : (int) (step);
			step = Math.random() * 5;
			r1 -= (int) (step) < 0 ? 0 : (int) (step);
			step = Math.random() * 5;
			r2 -= (int) (step) < 0 ? 0 : (int) (step);
//			step = Math.random() * 5;
//			r3 -= (int) (step) < 0 ? 0 : (int) (step);
			
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(ms[index]);
			String timeStr = "" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) + " " + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + "";
			String sql = "INSERT INTO \"NETMG\".\"NM_HIS_DATA2\" (\n" +
					"	\"GATHER_TIME\",\n" +
					"	\"AGENT_IP\",\n" +
					"	\"DATA_NAME\",\n" +
					"	\"DATA_VALUE\",\n" +
					"	\"GATHER_TIME2\"\n" +
					")\n" +
					"VALUES\n" +
					"	(\n" +
					"		'" + ms[index] + "',\n" +
//					"		'192.168.8.44',\n" +
					"		'127.0.0.1',\n" +
					"		'DISK_USAGE_RATE',\n" +
//					"		'" + r0 + "," + r1 + "," + r2 + "," + r3 + "',\n" +
					"		'" + r0 + "," + r1 + "," + r2 + "',\n" +
					"		TO_DATE (\n" +
					"			'" + timeStr + "',\n" +
					"			'SYYYY-MM-DD HH24:MI:SS'\n" +
					"		)\n" +
					"	)";
			System.out.println(sql + ";");
		}
	}
	
	public static void setFlag(Class<?> flag) {
		System.out.println(flag);
	}
	
	public static void intOrInteger(Integer i) {
		System.out.println("Integer");
		System.out.println(i == null);
	}

	enum TabName {
		SysMonitor("运行监控", 0), History("历史曲线", 1);
		private String desc;
		private int index;
		private TabName(String desc, int index) {
			this.desc = desc;
			this.index = index;
		}
		public String getDescription() {
			return desc;
		}
		public int getTabIndex() {
			return index;
		}
		@Override
		public String toString() {
			 return name();
		};
	}
	
	

}
