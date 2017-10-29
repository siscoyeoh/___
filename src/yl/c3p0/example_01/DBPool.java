package yl.c3p0.example_01;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBPool {
    
    private static DBPool instance;

    private ComboPooledDataSource dataSource;
    
    static{
        instance = new DBPool();
    }

    private DBPool() {
        try {
            dataSource = new ComboPooledDataSource();
            Properties prop = new Properties();
            InputStream in = DBPool.class.getClassLoader().getResourceAsStream("db.properties");
            in = new FileInputStream(new File("db.properties"));
            prop.load(in);

            dataSource.setDriverClass(prop.getProperty("jdbcdriver"));
            dataSource.setJdbcUrl(prop.getProperty("url"));
            dataSource.setUser(prop.getProperty("username"));
            dataSource.setPassword(prop.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static DBPool getInstance(){
        return instance;
    }
    
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}