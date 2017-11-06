package yl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MakeSession115 {
	
	
	public static Connection GetConn115_netmg() {
		String jdbcdriver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.8.115:1521:ZULSTEST";
		String username = "myshop";
		String password = "123456";
		return connect(jdbcdriver, url, username, password);
	}

	private static Connection connect(String jdbcdriver, String url,
			String username, String password) {
		Connection con = null;// 创建一个数据库连接
		try {
			Class.forName(jdbcdriver);// 加载Oracle驱动程序
			System.out.println("开始尝试连接数据库！");
			con = DriverManager.getConnection(url, username, password);// 获取连接
			System.out.println("连接成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	private static void query() {
		
	}
}
