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

// 单例模式
// 数据库连接池类，用于获取数据库连接。利用单例模式保证所有的连接都只通过一个连接池管理。
// http://www.cnblogs.com/mousewheel/p/6660238.html
 class App {
    public static void main(String[] args) {
//    	int a = 0;
//    	a++;
//    	File db_properties = new File("db.properties");
//    	System.out.println(db_properties.exists());
//    	if (a == 1) return;
    	/*

测试次数:1
使用连接池:393
不使用连接池:70

测试次数:10
使用连接池:497
不使用连接池:509

测试次数:15
使用连接池:473
不使用连接池:525

测试次数:100
使用连接池:718
不使用连接池:4404

测试次数:1000
使用连接池:3093
不使用连接池:42511

测试次数:1000
使用连接池:3401
不使用连接池:41736

测试次数:10000
使用连接池:28067
不使用连接池:419659

    	 */
    	
    	int times = 1000;
        // use c3p0 connection pool
    	Date date0 = new Date();
        try {
            for (int i = 0; i < times; i++) {
                long beginTime = System.currentTimeMillis();
                Connection con = DbUtil.getConnection();
                // 执行查询语句
                String sql = "select * from stu";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                     System.out.println(rs.getString(2));
                }
                con.close();
                long endTime = System.currentTimeMillis();
                System.out.println(String.format("第%s次,%s", i, (endTime - beginTime)));
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
                System.out.println(String.format("第%s次,%s", i, (endTime - beginTime)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	Date date2 = new Date();
    	System.out.println("测试次数:" + (times));
    	System.out.println("使用连接池:" + (date1.getTime() - date0.getTime()));
    	System.out.println("不使用连接池:" + (date2.getTime() - date1.getTime()));
    }
}