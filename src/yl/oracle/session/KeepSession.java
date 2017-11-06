package yl.oracle.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import yl.jdbc.DBUtils;

public class KeepSession {
	
	public static void main(String[]args) {
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.GetConn115_netmg();
			ps = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT * FROM STU";
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString("NAME") + ", " + rs.getInt("AGE"));
				try {
					Thread.sleep(1 * 60 * 1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
				System.out.println("数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
