package yl.c3p0.example_01;

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
// ���ݿ����ӳ��࣬���ڻ�ȡ���ݿ����ӡ����õ���ģʽ��֤���е����Ӷ�ֻͨ��һ�����ӳع���
// http://www.cnblogs.com/mousewheel/p/6660238.html
 class App {
    public static void main(String[] args) {
//    	int a = 0;
//    	a++;
//    	File db_properties = new File("db.properties");
//    	System.out.println(db_properties.exists());
//    	if (a == 1) return;
    	/*

���Դ���:1
ʹ�����ӳ�:393
��ʹ�����ӳ�:70

���Դ���:10
ʹ�����ӳ�:497
��ʹ�����ӳ�:509

���Դ���:15
ʹ�����ӳ�:473
��ʹ�����ӳ�:525

���Դ���:100
ʹ�����ӳ�:718
��ʹ�����ӳ�:4404

���Դ���:1000
ʹ�����ӳ�:3093
��ʹ�����ӳ�:42511

���Դ���:1000
ʹ�����ӳ�:3401
��ʹ�����ӳ�:41736

���Դ���:10000
ʹ�����ӳ�:28067
��ʹ�����ӳ�:419659

    	 */
    	
    	int times = 1000;
        // use c3p0 connection pool
    	Date date0 = new Date();
        try {
            for (int i = 0; i < times; i++) {
                long beginTime = System.currentTimeMillis();
                Connection con = DbUtil.getConnection();
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