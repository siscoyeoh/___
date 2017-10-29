package yl.c3p0.example_02;

import java.beans.PropertyVetoException;  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;  
import java.sql.SQLException;  
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;  
/**
 * ���ܣ�ʹ��C3P0���ӳػ�ȡ���ݿ����Ӷ��� 
 * 
 * @Author Yang Lin
 * @Date 2017��10��16��
 * @Time ����3:43:28
 */
public class DBManager {  
      
    private static final DBManager instance = new DBManager();  
    private static ComboPooledDataSource cpds = new ComboPooledDataSource(true);   
      
    /** 
     * �˴����Բ����ã�����Ĭ��Ҳ�� 
     */  
    static{  
    	Properties prop = new Properties();
    	try {
    		System.out.println("new File(\"db2.properties\"):" + new File("db2.properties").exists());
			prop.load(new FileInputStream(new File("db2.properties")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        cpds.setDataSourceName("mydatasource");  
        cpds.setJdbcUrl(prop.getProperty("url"));  
        try {  
            cpds.setDriverClass(prop.getProperty("jdbcdriver"));  
        } catch (PropertyVetoException e) {  
            e.printStackTrace();  
        }  
        System.out.println("username:" + prop.getProperty("username"));
        cpds.setUser(prop.getProperty("username"));  
        cpds.setPassword(prop.getProperty("password"));  
        cpds.setMaxPoolSize(Integer.valueOf(prop.getProperty("maxPoolSize")));  
        cpds.setMinPoolSize(Integer.valueOf(prop.getProperty("minPoolSize")));  
        cpds.setAcquireIncrement(Integer.valueOf(prop.getProperty("acquireIncrement")));  
        cpds.setInitialPoolSize(Integer.valueOf(prop.getProperty("initialPoolSize")));  
        cpds.setMaxIdleTime(Integer.valueOf(prop.getProperty("maxIdleTime")));  
        System.out.println("maxIdleTime:" + prop.getProperty("maxIdleTime"));
    }  
      
    private DBManager(){}  
      
    public static DBManager getInstance(){  
        return instance;  
    }  
      
    public static Connection  getConnection(){  
        try {  
            return cpds.getConnection();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
}  