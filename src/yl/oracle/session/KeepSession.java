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
				// ��һ������ļ�������رգ���Ϊ���رյĻ���Ӱ�����ܡ�����ռ����Դ
				// ע��رյ�˳�����ʹ�õ����ȹر�
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
				System.out.println("���ݿ������ѹرգ�");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
