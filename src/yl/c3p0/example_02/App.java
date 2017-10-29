package yl.c3p0.example_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

// ����ģʽ
// http://boonya.iteye.com/blog/1828298
 class App {
    public static void main(String[] args) {
    	/*


���Դ���:100
ʹ�����ӳ�:674
��ʹ�����ӳ�:3801

���Դ���:1000
ʹ�����ӳ�:3267
��ʹ�����ӳ�:43370

    	 */
    	
    	int times = 1000;
        // use c3p0 connection pool
    	Date date0 = new Date();
        try {
            for (int i = 0; i < times; i++) {
                long beginTime = System.currentTimeMillis();
                Connection con = DBManager.getConnection();
                // ִ�в�ѯ���
                String sql = "select * from stu";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                     System.out.println(rs.getString(2));
                }
                con.close();
                long endTime = System.currentTimeMillis();
                System.out.println(String.format("��%s��,%s", i, (endTime - beginTime)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // jdbc without using conection pool
    	Date date1 = new Date();
        try {
            for (int i = 0; i < times; i++) {
                long beginTime = System.currentTimeMillis();
                Properties prop = new Properties();
                InputStream in = App.class.getClassLoader().getResourceAsStream("db.properties");
                in = new FileInputStream(new File("db.properties"));
                prop.load(in);
                Class.forName(prop.getProperty("jdbcdriver"));

                Connection con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"),
                        prop.getProperty("password"));

                String sql = "select * from stu";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                     System.out.println(rs.getString(2));
                }
                con.close();
                long endTime = System.currentTimeMillis();
                System.out.println(String.format("��%s��,%s", i, (endTime - beginTime)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	Date date2 = new Date();
    	System.out.println("���Դ���:" + (times));
    	System.out.println("ʹ�����ӳ�:" + (date1.getTime() - date0.getTime()));
    	System.out.println("��ʹ�����ӳ�:" + (date2.getTime() - date1.getTime()));
    }
}