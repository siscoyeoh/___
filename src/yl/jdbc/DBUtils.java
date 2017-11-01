package yl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtils {
	
	public static Connection GetConn42_netmg() {
		String jdbcdriver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.8.42:1521:orcl";
		String username = "mytt";
		String password = "mytt";
		return connect(jdbcdriver, url, username, password);
	}
	
	public static Connection GetConn115_netmg() {
		String jdbcdriver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.8.115:1521:ZULSTEST";
		String username = "netmg";
		String password = "netmg";
		return connect(jdbcdriver, url, username, password);
	}

	public static Connection connect(String jdbcdriver, String url,
			String username, String password) {
		Connection con = null;// 创建一个数据库连接
		PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
		ResultSet result = null;// 创建一个结果集对象
		try {
			Class.forName(jdbcdriver);// 加载Oracle驱动程序
			System.out.println("开始尝试连接数据库！");
//			String url = url;// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
//			String username = "netmg";// 用户名,系统默认的账户名
//			String password = "netmg";// 你安装时选设置的密码
			con = DriverManager.getConnection(url, username, password);// 获取连接
			System.out.println("连接成功！");
			/*
			 * String sql =
			 * "SELECT * FROM NM_HIS_DATA2 WHERE GATHER_TIME = ?";//
			 * 预编译语句，“？”代表参数 pre = con.prepareStatement(sql);// 实例化预编译语句
			 * pre.setLong(1, 1507681808293L);// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
			 * result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
			 * 
			 * while (result.next()) { // 当结果集不为空时 System.out.println("IP地址:" +
			 * result.getString("AGENT_IP") + ", data_name:" +
			 * result.getString("DATA_NAME") + ", data_value:" +
			 * result.getString("DATA_VALUE")); }
			 */

//			String sql = "DELETE NM_HIS_DATA2 WHERE GATHER_TIME < ?";
//			pre = con.prepareStatement(sql);
//			pre.setLong(1, 1505627276796L);
//			pre.executeUpdate();
//			// System.out.println(pre.execute());

		} catch (Exception e) {
			e.printStackTrace();
		} 
//		finally {
//			try {
//				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
//				// 注意关闭的顺序，最后使用的最先关闭
//				if (result != null)
//					result.close();
//				if (pre != null)
//					pre.close();
//				if (con != null)
//					con.close();
//				System.out.println("数据库连接已关闭！");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		return con;
	}
}
