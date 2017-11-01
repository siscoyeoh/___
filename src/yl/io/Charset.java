package yl.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import yl.jdbc.DBUtils;


public class Charset {

	public static void main(String[] args) throws IOException, SQLException {
		Connection conn = DBUtils.GetConn42_netmg();
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM \"MYTT\".\"ELE_REMARK\" WHERE REMARK1 LIKE '—%'";
		ResultSet rs = st.executeQuery(sql);
		File data = new File("C:/201101_day.dat");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(data), "utf-8"));
		while (rs.next()) {
			String remark1 = rs.getString("REMARK1");
			bw.write(remark1);
		}
		bw.flush();
		bw.close();
		
//		String cn_ = "—"; // -
//		System.out.println(cn_.compareTo("-"));
//		System.out.println(cn_.compareTo("—"));
//		System.out.println(cn_.compareTo("—"));
//		File data = new File("C:/201101_day.dat");
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(data), "utf-8"));
//		bw.write(cn_);
//		bw.flush();
//		bw.close();
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(
//				new FileInputStream(data), "utf-8"));
//		System.out.println(br.readLine());
//		br.close();
	}

}
