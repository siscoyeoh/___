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
		Connection con = null;// ����һ�����ݿ�����
		PreparedStatement pre = null;// ����Ԥ����������һ�㶼�������������Statement
		ResultSet result = null;// ����һ�����������
		try {
			Class.forName(jdbcdriver);// ����Oracle��������
			System.out.println("��ʼ�����������ݿ⣡");
//			String url = url;// 127.0.0.1�Ǳ�����ַ��XE�Ǿ����Oracle��Ĭ�����ݿ���
//			String username = "netmg";// �û���,ϵͳĬ�ϵ��˻���
//			String password = "netmg";// �㰲װʱѡ���õ�����
			con = DriverManager.getConnection(url, username, password);// ��ȡ����
			System.out.println("���ӳɹ���");
			/*
			 * String sql =
			 * "SELECT * FROM NM_HIS_DATA2 WHERE GATHER_TIME = ?";//
			 * Ԥ������䣬������������� pre = con.prepareStatement(sql);// ʵ����Ԥ�������
			 * pre.setLong(1, 1507681808293L);// ���ò�����ǰ���1��ʾ�����������������Ǳ�������������
			 * result = pre.executeQuery();// ִ�в�ѯ��ע�������в���Ҫ�ټӲ���
			 * 
			 * while (result.next()) { // ���������Ϊ��ʱ System.out.println("IP��ַ:" +
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
//				// ��һ������ļ�������رգ���Ϊ���رյĻ���Ӱ�����ܡ�����ռ����Դ
//				// ע��رյ�˳�����ʹ�õ����ȹر�
//				if (result != null)
//					result.close();
//				if (pre != null)
//					pre.close();
//				if (con != null)
//					con.close();
//				System.out.println("���ݿ������ѹرգ�");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		return con;
	}
}
